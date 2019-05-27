package com.blumeglobal.shipmentmanagement.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blumeglobal.shipmentmanagement.dao.repositories.AdvanceShipmentNoticeRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.E2EShipmentRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.EquipmentWORepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.LocationMasterRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentExceptionRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentMilestonesRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.StopRepository;
import com.blumeglobal.shipmentmanagement.dm.ShipmentEvent;
import com.blumeglobal.shipmentmanagement.dm.ShipmentEventRepository;
import com.blumeglobal.shipmentmanagement.enums.ActiveShipmentStatusColor;
import com.blumeglobal.shipmentmanagement.enums.ExceptionCode;
import com.blumeglobal.shipmentmanagement.enums.ExceptionSeverity;
import com.blumeglobal.shipmentmanagement.enums.LocationType;
import com.blumeglobal.shipmentmanagement.enums.LocationTypeDesc;
import com.blumeglobal.shipmentmanagement.enums.MovementDesc;
import com.blumeglobal.shipmentmanagement.enums.ShipmentExceptionType;
import com.blumeglobal.shipmentmanagement.enums.ShipmentReferenceType;
import com.blumeglobal.shipmentmanagement.enums.ShipmentViewType;
import com.blumeglobal.shipmentmanagement.enums.WorkorderStatus;
import com.blumeglobal.shipmentmanagement.exceptions.DataNotFoundException;
import com.blumeglobal.shipmentmanagement.model.AdvanceShipmentNotice;
import com.blumeglobal.shipmentmanagement.model.E2EShipment;
import com.blumeglobal.shipmentmanagement.model.IShipmentFreightDetails;
import com.blumeglobal.shipmentmanagement.model.LocationMaster;
import com.blumeglobal.shipmentmanagement.model.Shipment;
import com.blumeglobal.shipmentmanagement.model.ShipmentException;
import com.blumeglobal.shipmentmanagement.model.ShipmentMilestone;
import com.blumeglobal.shipmentmanagement.model.ShipmentReferences;
import com.blumeglobal.shipmentmanagement.model.Stop;
import com.blumeglobal.shipmentmanagement.model.mapper.E2EShipmentMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.LocationMasterMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentEventMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentExceptionMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentLegDetailsMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentMilestoneMapper;
import com.blumeglobal.shipmentmanagement.response.ActiveShipmentResponse;
import com.blumeglobal.shipmentmanagement.response.E2EShipmentDetailsResponse;
import com.blumeglobal.shipmentmanagement.response.E2EShipmentResponse;
import com.blumeglobal.shipmentmanagement.response.LocationAPIResponse;
import com.blumeglobal.shipmentmanagement.response.LocationResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentEventResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentExceptionResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentFreightDetailResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentLegDetailsResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentMilestoneResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentSummaryResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentWOResponse;
import com.blumeglobal.shipmentmanagement.service.IShipmentMilestoneService;
import com.blumeglobal.shipmentmanagement.service.IShipmentViewService;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;
import com.blumeglobal.shipmentmanagement.utils.DateUtil;
import com.blumeglobal.shipmentmanagement.utils.ShipmentUtil;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;


/**
 * The ShipmentServiceImpl implements to manage the shipment.
 *
 */
@Service
@REZ1Logger
@REZ1PerformanceLogger
public class ShipmentViewServiceImpl implements IShipmentViewService {
    private static final Logger logger = LogManager.getLogger(ShipmentViewServiceImpl.class);

    public static final String AddrSplitChar = "|";
    
    @Autowired
    E2EShipmentRepository e2eShipmentRepository;

    @Autowired
    AdvanceShipmentNoticeRepository asnRepository;

    @Autowired
    E2EShipmentMapper e2eShipmentMapper;

    @Autowired
    ShipmentUtil shipmentUtil;

    @Autowired
    ShipmentRepository shipmentRepository;

    @Autowired
    ShipmentLegDetailsMapper shipmentLegDetailsMapper;

    @Autowired
    ShipmentMapper shipmentMapper;

    @Autowired
    ShipmentMilestonesRepository shipmentMilestonesRepository;

    @Autowired
    ShipmentMilestoneMapper shipmentMilestoneMapper;

    @Autowired
    ShipmentEventRepository shipmentEventRepository;

    @Autowired
    ShipmentEventMapper shipmentEventMapper;

    @Autowired
    ShipmentExceptionRepository shipmentExceptionRepository;

    @Autowired
    ShipmentExceptionMapper shipmentExceptionMapper;

    @Autowired
    LocationMasterRepository locationMasterRepository;

    @Autowired
    EquipmentWORepository equipmentWORepository;

    @Autowired
    IShipmentMilestoneService shipmentMilestoneService;

    @Autowired
    StopRepository stopRepository;

    @Autowired
    LocationMasterMapper locationMaster;

    @Override
    public ShipmentSummaryResponse getShipmentViewSummary(String organizationCode) throws Exception {
        try {
            List<E2EShipmentResponse> shipmentDetails = new ArrayList<>();
            ShipmentSummaryResponse summary = new ShipmentSummaryResponse();

            // E2E shipment use only organization code
            if (organizationCode == null || organizationCode.isEmpty()) {

                if (logger.isDebugEnabled()) {
                    logger.debug("exiting shipmentList()");
                }
                throw new DataNotFoundException(ShipmentConstants.EMPTY_ORG_CODE_MESSAGE);
            }

            // only date older up to 90 days will be fetched.
            Date fromDate = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(fromDate);
            c.add(Calendar.DATE, -90);
            fromDate = c.getTime();
            List<E2EShipment> e2eShipments = null;

            List<String> organizationCodes = Arrays.asList(organizationCode.split("\\s*,\\s*"));

            e2eShipments = e2eShipmentRepository.getActiveE2EShipmentsSummary(new Date(), fromDate, organizationCodes);
            if (e2eShipments != null && !e2eShipments.isEmpty()) {
                try {
                    e2eShipments.forEach(e2eShipment -> {
                        E2EShipmentResponse shipmentResponse =
                                e2eShipmentMapper.map(e2eShipment, E2EShipmentResponse.class);
                        setActiveShipmentDetail(shipmentResponse, e2eShipment);
                        shipmentDetails.add(shipmentResponse);
                        summary.setActiveE2EShipmentCount((long) shipmentDetails.size());
                    });
                } catch (Exception e) {
                    logger.error("Exception inside ShipmentViewSersServiceImpl.e2eshipmentList.Catch_Block:"
                            + e.getMessage());
                }

            } else {
                summary.setActiveE2EShipmentCount(0L);
            }

            shipmentDetails.clear();
            e2eShipments =
                    e2eShipmentRepository.getExceptionE2EShipmentsSummary(new Date(), fromDate, organizationCodes);
            if (e2eShipments != null && !e2eShipments.isEmpty()) {
                try {
                    e2eShipments.forEach(e2eShipment -> {
                        E2EShipmentResponse shipmentResponse =
                                e2eShipmentMapper.map(e2eShipment, E2EShipmentResponse.class);
                        setActiveShipmentDetail(shipmentResponse, e2eShipment);
                        shipmentDetails.add(shipmentResponse);
                        summary.setTotalExceptionCount((long) shipmentDetails.size());
                    });
                } catch (Exception e) {
                    logger.error("Exception inside ShipmentViewSersServiceImpl.e2eshipmentList.Catch_Block:"
                            + e.getMessage());
                }

            } else {
                summary.setTotalExceptionCount(0L);
            }

            shipmentDetails.clear();
            e2eShipments =
                    e2eShipmentRepository.getCompletedE2EShipmentsSummary(new Date(), fromDate, organizationCodes);
            if (e2eShipments != null && !e2eShipments.isEmpty()) {
                try {
                    e2eShipments.forEach(e2eShipment -> {
                        E2EShipmentResponse shipmentResponse =
                                e2eShipmentMapper.map(e2eShipment, E2EShipmentResponse.class);
                        setActiveShipmentDetail(shipmentResponse, e2eShipment);
                        shipmentDetails.add(shipmentResponse);
                        summary.setLastdayCompletedShipmentCount((long) shipmentDetails.size());
                    });
                } catch (Exception e) {
                    logger.error("Exception inside ShipmentViewSersServiceImpl.e2eshipmentList.Catch_Block:"
                            + e.getMessage());
                }

            } else {
                summary.setLastdayCompletedShipmentCount(0L);
            }
            return summary;
        } catch (Exception e) {
            logger.error("Exception inside ShipmentViewSersServiceImpl.e2eshipmentList.Catch_Block:" + e.getMessage());
            throw e;
        }

    }

    @Override
    public List<E2EShipmentResponse> e2eshipmentList(int startRow, int endRow, String orgnizationCode,
            String viewType) {
        List<E2EShipmentResponse> shipmentDetails = new ArrayList<>();


        // E2E shipment use only organization code
        if (orgnizationCode == null || orgnizationCode.isEmpty()) {

            if (logger.isDebugEnabled()) {
                logger.debug("exiting shipmentList()");
            }
            throw new DataNotFoundException(ShipmentConstants.EMPTY_ORG_CODE_MESSAGE);
        }

        // only date older up to 90 days will be fetched.
        Date fromDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(fromDate);
        c.add(Calendar.DATE, -90);
        fromDate = c.getTime();
        List<E2EShipment> e2eShipments = null;
        List<String> organizationCodes = Arrays.asList(orgnizationCode.split("\\s*,\\s*"));
        if (ShipmentViewType.ACTIVE.name().equalsIgnoreCase(viewType)) {
            e2eShipments = e2eShipmentRepository.getActiveE2EShipments(startRow, endRow, new Date(), fromDate,
                    organizationCodes);
        } else if (ShipmentViewType.EXCEPTION.name().equalsIgnoreCase(viewType)) {
            e2eShipments = e2eShipmentRepository.getExceptionE2EShipments(startRow, endRow, new Date(), fromDate,
                    organizationCodes);
        } else if (ShipmentViewType.COMPLETEDLASTDAY.name().equalsIgnoreCase(viewType)) {
            e2eShipments = e2eShipmentRepository.getCompletedE2EShipments(startRow, endRow, new Date(), fromDate,
                    organizationCodes);
        }

        if (e2eShipments != null) {
            try {
                e2eShipments.forEach(e2eShipment -> {
                    E2EShipmentResponse shipmentResponse =
                            e2eShipmentMapper.map(e2eShipment, E2EShipmentResponse.class);
                    setActiveShipmentDetail(shipmentResponse, e2eShipment);
                    shipmentDetails.add(shipmentResponse);
                });
            } catch (Exception e) {
                logger.error(
                        "Exception inside ShipmentViewSersServiceImpl.e2eshipmentList.Catch_Block:" + e.getMessage());
            }

        }

        return shipmentDetails;
    }

    private void setActiveShipmentDetail(E2EShipmentResponse e2eShipmentResponse, E2EShipment e2eShipment) {
        List<Shipment> shipments =
                shipmentRepository.getNotCancelledShipmentByE2EShipmentId(e2eShipment.getShipmentId());
        // removeEmptyContainerShipmenFromShipments(shipments, e2eShipmentResponse);
        if (shipments.isEmpty())
            return;
        Long shipmentExceptonCount =
                shipmentExceptionRepository.getE2eShipmentExceptionCount(e2eShipment.getShipmentId());
        int completedShipments = 0;
        for (int i = 0; i < shipments.size(); i++) {
            Shipment sh = shipments.get(i);
            if (!isShipmentCompleted(sh)) {
                ActiveShipmentResponse activeShipment = shipmentMapper.map(sh, ActiveShipmentResponse.class);
                e2eShipmentResponse.setFreightDescription(sh.getFreightDescription());
                activeShipment.setTotalShipments(shipments.size());
                activeShipment.setCompletedShipments(completedShipments);
                if (shipmentExceptonCount > 0)
                    activeShipment.setWithE2eShipmentException(Boolean.TRUE);
                e2eShipmentResponse.setActiveShipment(activeShipment);

                Shipment nextShipment = null;
                if (i + 1 < shipments.size())
                    nextShipment = shipments.get(i + 1);
                String statusColor = getActiveShipmentStatusColor(e2eShipment.getShipmentId(), sh, nextShipment);

                activeShipment.setShipmentException(getShipmentDisplayException(sh.getShipmentId()));

                activeShipment.setStatusColor(statusColor);

                if (!WorkorderStatus.Active.name().equalsIgnoreCase(sh.getStatus())) {
                    if (i == 0) {
                        activeShipment.setCurrentMoveDesc(MovementDesc.BOOKING.getDescription());
                        activeShipment.setNextMoveDesc(MovementDesc.PICKUP.getDescription());
                    } else {
                        activeShipment.setCurrentMoveDesc(MovementDesc.REST.getDescription());
                        activeShipment.setNextMoveDesc(MovementDesc.TOSHIP.getDescription());
                    }
                } else {
                    activeShipment.setCurrentMoveDesc(MovementDesc.INTRANSIT.getDescription());
                    activeShipment.setNextMoveDesc(MovementDesc.SHIPPED.getDescription());
                }
                break;
            }
            completedShipments++;
        }
        // completed e2e shipment
        if (e2eShipmentResponse.getActiveShipment() == null) {
            Shipment sh = shipments.get(shipments.size() - 1);
            ActiveShipmentResponse activeShipment = shipmentMapper.map(sh, ActiveShipmentResponse.class);
            activeShipment.setTotalShipments(shipments.size());
            activeShipment.setCompletedShipments(shipments.size());
            e2eShipmentResponse.setFreightDescription(sh.getFreightDescription());
            if (shipmentExceptonCount > 0)
                activeShipment.setWithE2eShipmentException(Boolean.TRUE);
            e2eShipmentResponse.setActiveShipment(activeShipment);
            activeShipment.setCurrentMoveDesc(MovementDesc.REST.getDescription());
            activeShipment.setLastMoveDesc(MovementDesc.DELIVERED.getDescription());

            String statusColor = getActiveShipmentStatusColor(e2eShipment.getShipmentId(), sh, null);
            activeShipment.setStatusColor(statusColor);

            activeShipment.setShipmentException(getShipmentDisplayException(sh.getShipmentId()));

            List<ShipmentMilestone> smls =
                    shipmentMilestonesRepository.findByShipmentIdOrderByShipmentMilestoneIdDesc(sh.getShipmentId());
            for (ShipmentMilestone sm : smls) {
                if (sm.getActualDate() != null && sm.getMilestone().isDisplay() && sm.getMilestone().isWithLeadTime()) {
                    activeShipment.setActualDate(sm.getActualDate());
                    break;
                }
            }
        }
    }

    private ShipmentExceptionResponse getShipmentDisplayException(Long shipmentId) {
        ShipmentExceptionResponse ser = null;
        List<ShipmentException> exceptions =
                shipmentExceptionRepository.getDisplayShipmentExceptionsByShipmentId(shipmentId);
        ShipmentException shipmentException = null;
        for (ShipmentException se : exceptions) {
            if (shipmentException == null) {
                shipmentException = se;
            } else if (ShipmentExceptionType.getException(se.getExceptionType()) != null
                    && ShipmentExceptionType.getException(shipmentException.getExceptionType()) != null
                    && ShipmentExceptionType.getException(se.getExceptionType()).getPriority() < ShipmentExceptionType
                            .getException(shipmentException.getExceptionType()).getPriority()) {
                shipmentException = se;
            }
        }
        if (shipmentException != null) {
            ser = shipmentExceptionMapper.map(shipmentException, ShipmentExceptionResponse.class);
            if (shipmentException.getShipmentMilestoneId() != null) {
                Optional<ShipmentMilestone> smlOptional =
                        shipmentMilestonesRepository.findById(shipmentException.getShipmentMilestoneId());
                if (smlOptional.isPresent()) {
                    ser.setExpectedDate(smlOptional.get().getPlannedDate());
                }
            }

            ShipmentExceptionType exceptionType =
                    ShipmentExceptionType.getException(shipmentException.getExceptionType());
            ser.setPriority(exceptionType != null ? ExceptionSeverity.getPriority(exceptionType.getPriority()).name()
                    : ExceptionSeverity.Low.name());
        }
        return ser;
    }

    private String getActiveShipmentStatusColor(Long e2eShipmentId, Shipment activeShipment, Shipment nextShipment) {
        /// red - exception and leadtime + current > expected eta
        /// yellow - exception and current + leadtime <= expected eta
        /// green - no exception
        String statusColor = ActiveShipmentStatusColor.GREEN.name();
        Double leadTime = null;
        String leadTimeUom = null;

        List<ShipmentException> shipmentExceptions =
                shipmentExceptionRepository.getDisplayShipmentExceptionsByE2eShipmentId(e2eShipmentId);
        if (!shipmentExceptions.isEmpty()) {

            // shipmentId
            List<ShipmentMilestone> smls = shipmentMilestonesRepository
                    .findByShipmentIdOrderByShipmentMilestoneIdDesc(activeShipment.getShipmentId());

            Date leadMilestoneActualDate = null;
            for (ShipmentMilestone sm : smls) {
                if (sm.getActualDate() != null && sm.getMilestone().isDisplay() && sm.getMilestone().isWithLeadTime()) {
                    leadMilestoneActualDate = sm.getActualDate();
                    leadTime = sm.getEstimatedLeadTime();
                    leadTimeUom = sm.getUom();
                    break;
                }
            }
            if (leadMilestoneActualDate != null) {
                if (!leadMilestoneActualDate.after(activeShipment.getExpectedDeliveryDate())) {
                    statusColor = ActiveShipmentStatusColor.YELLOW.name();
                } else {
                    if (WorkorderStatus.Completed.name().equalsIgnoreCase(activeShipment.getStatus())) {
                        statusColor = ActiveShipmentStatusColor.RED.name();
                    } else {
                        // shipment is active/assigned
                        // use next shipment leadtime to estimate eta for yellow
                        if (nextShipment != null) {
                            List<ShipmentMilestone> nextSmls = shipmentMilestonesRepository
                                    .findByShipmentIdOrderByShipmentMilestoneIdDesc(nextShipment.getShipmentId());
                            Double nextLeadTime = null;
                            String nextLeadTimeUom = null;
                            for (ShipmentMilestone sm : nextSmls) {
                                if (sm.getMilestone().isDisplay() && sm.getMilestone().isWithLeadTime()) {
                                    nextLeadTime = sm.getEstimatedLeadTime();
                                    nextLeadTimeUom = sm.getUom();
                                    break;
                                }
                            }
                            if (nextLeadTime != null) {
                                // default HOUR
                                Date calculatedEta = DateUtil.getDateAfterHours(nextLeadTime.intValue());
                                if (!"HR".equals(nextLeadTimeUom)) {
                                    calculatedEta = DateUtil.getAfterDates(new Date(), nextLeadTime.intValue());
                                }
                                if (calculatedEta.after(activeShipment.getExpectedDeliveryDate())) {
                                    statusColor = ActiveShipmentStatusColor.RED.name();
                                } else {
                                    statusColor = ActiveShipmentStatusColor.YELLOW.name();
                                }
                            } else {
                                statusColor = ActiveShipmentStatusColor.RED.name();
                            }
                        } else {
                            statusColor = ActiveShipmentStatusColor.RED.name();
                        }
                    }
                }
            } else {
                if (leadTime != null) {
                    // default HOUR
                    Date calculatedEta = DateUtil.getDateAfterHours(leadTime.intValue());
                    if (!"HR".equals(leadTimeUom)) {
                        calculatedEta = DateUtil.getAfterDates(new Date(), leadTime.intValue());
                    }
                    if (calculatedEta.after(activeShipment.getExpectedDeliveryDate())) {
                        statusColor = ActiveShipmentStatusColor.RED.name();
                    } else {
                        statusColor = ActiveShipmentStatusColor.YELLOW.name();
                    }
                } else {
                    statusColor = ActiveShipmentStatusColor.RED.name();
                }
            }

        }
        return statusColor;
    }

    /**
     * remove empty shipments from view and update e2e shipment origin/destination for removed empty shipments
     * 
     * @param shipments
     * @param e2eShipmentResponse
     */
    @SuppressWarnings("unused")
    private void removeEmptyContainerShipmenFromShipments(List<Shipment> shipments,
            E2EShipmentResponse e2eShipmentResponse) {
        if (shipments.isEmpty())
            return;
        if (isOriginYardMovement(shipments.get(0))) {
            Shipment firstSh = shipments.get(0);
            e2eShipmentResponse.setOrigin(firstSh.getDestination());
            shipments.remove(firstSh);
        }
        if (shipments.size() > 1 && isDestinationYardMovement(shipments.get(shipments.size() - 1))) {
            Shipment lastSh = shipments.get(shipments.size() - 1);
            e2eShipmentResponse.setDestination(lastSh.getOrigin());
            shipments.remove(lastSh);
        }

    }

    private boolean isShipmentCompleted(Shipment sh) {
        if (sh.getStatus() != null && sh.getStatus().equalsIgnoreCase(WorkorderStatus.Completed.name()))
            return true;
        return false;
    }

    public ShipmentWOResponse findShipmentbyId(Long id) {
        try {
            if (id != null) {
                Shipment shipment = shipmentRepository.findByShipmentId(id);
                if (shipment == null)
                    throw shipmentUtil.throwDataNotFoundException(ExceptionCode.SHIPMENTEVENTNOTFOUNDCUSTOM.toString(),
                            ShipmentConstants.SHIPMENT_ID_DATA_AVAILABILITY_MESSAGE);
                else
                    return shipmentMapper.map(shipment, ShipmentWOResponse.class);
            } else
                throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(),
                        ShipmentConstants.SHIPMENT_ID_NULL_MESSAGE);
        } catch (Exception ex) {
            logger.error(
                    "Exception inside ShipmentViewSersServiceImpl.findShipmentLegById.Catch_Block:" + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public List<ShipmentWOResponse> getShipmentbyE2EShipmentId(Long id) {
        if (id == null)
            throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTINVALID.toString(),
                    "E2E Shipment ID should not be null");
        List<ShipmentWOResponse> results = new ArrayList<>();
        List<Shipment> shipments = shipmentRepository.getShipmentByE2EShipmentId(id);
        if (shipments == null)
            return results;
        shipments.forEach(shipment -> {
            ShipmentWOResponse shipmentResponse = shipmentMapper.map(shipment, ShipmentWOResponse.class);
            results.add(shipmentResponse);
        });
        return results;
    }

    @Override
    public E2EShipmentDetailsResponse findE2EShipmentDetailsByRef(String ref, String organizationCode) {

        List<String> organizationCodes = Arrays.asList(organizationCode.split("\\s*,\\s*"));

        List<Shipment> shipments = shipmentRepository.findShipmentsByShipmentRef(ref, organizationCodes);
        if (shipments == null || shipments.isEmpty())
            throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTNOTFOUND.toString(),
                    "No shipment found");
        E2EShipmentDetailsResponse shipmentDetails;
        try {
            Shipment firstshipment = shipments.get(0);
            E2EShipment e2eShipment = e2eShipmentRepository.findById(firstshipment.getE2eShipmentId()).get();
            shipmentDetails = e2eShipmentMapper.map(e2eShipment, E2EShipmentDetailsResponse.class);
            shipmentDetails.setBookingNumber(firstshipment.getReferenceValue(ShipmentReferenceType.BOOKINGNUM));
            shipmentDetails.setFreightDescription(firstshipment.getFreightDescription());
            AdvanceShipmentNotice asn = asnRepository.getShipmentByShipmentNumberUnitIdOrgCode(
                    e2eShipment.getShipmentNumber(), e2eShipment.getShipmentId(), organizationCode, "N");
            if (asn != null)
                shipmentDetails.setAsnNumber(asn.getAsnNumber());


            shipments = shipmentRepository.getShipmentByE2EShipmentId(e2eShipment.getShipmentId());

            if (shipments.size() == 1 && isOriginYardMovement(shipments.get(0)))
                return null;
            // checkLookupValues(shipments);//This fix is not needed for generic shipment service
            // TODO this should be replaced with message consumer for shipment event message
            // shipmentMilestoneService.processShipmentEvents(shipments, e2eShipment);

            // final List<Long> emptyYardShipmentIds=removeYardFromE2EOriginDestinationDisplay(shipments,
            // shipmentDetails);

            List<ShipmentLegDetailsResponse> shipmentLegs = mapShipmentToLegDetails(shipments);
            shipmentDetails.setLegs(shipmentLegs);
            List<ShipmentEvent> shipmentEvents = findShipmentEventsByReferences(shipments);
            shipmentDetails.setEvents(mapEventResponses(shipmentEvents, shipments));

            List<ShipmentException> exceptions = shipmentExceptionRepository
                    .getDisplayShipmentExceptionsByE2eShipmentId(e2eShipment.getShipmentId());
            // List<ShipmentException> exceptionsWithoutEmptyYardShipment=exceptions.stream()
            // .filter(exception -> !emptyYardShipmentIds.contains(exception.getShipmentId()))
            // .collect(Collectors.toList());
            List<ShipmentExceptionResponse> exceptionResponses = new ArrayList<>();
            for (ShipmentException se : exceptions) {
                ShipmentExceptionResponse ser = shipmentExceptionMapper.map(se, ShipmentExceptionResponse.class);
                if (se.getShipmentMilestoneId() != null) {
                    Optional<ShipmentMilestone> smlOptional =
                            shipmentMilestonesRepository.findById(se.getShipmentMilestoneId());
                    if (smlOptional.isPresent()) {
                        ser.setExpectedDate(smlOptional.get().getPlannedDate());
                    }
                }
                
                //Set exceptionLocation
                ser = setExceptionLocation(ser);
                
                 
                exceptionResponses.add(ser);
            }
            shipmentDetails.setExceptions(exceptionResponses);



            List<ShipmentMilestone> shipmentMilestones = shipmentMilestonesRepository
                    .findByE2eShipmentIdOrderByShipmentMilestoneId(e2eShipment.getShipmentId());

            setShipmentMilestones(shipmentDetails, shipmentMilestones, shipments);

        } catch (Exception ex) {
            logger.error("Exception inside ShipmentViewSersServiceImpl.findE2EShipmentDetailsByRef.Catch_Block:"
                    + ex.getMessage(), ex);
            throw shipmentUtil.throwInValidDataException(ExceptionCode.CATCHBLOCKRUNTIMEEXCEPTION.toString(),
                    "No shipment found");
        }
        return shipmentDetails;
    }
    
    public ShipmentExceptionResponse setExceptionLocation(ShipmentExceptionResponse ser) {
    	try {
            LocationAPIResponse locationAPIResponse = shipmentUtil.getLocationsByLocationId(ser.getExceptionLocation());
			if(locationAPIResponse != null) {
				if(locationAPIResponse.getName() != null && locationAPIResponse.getName().length() != 0) {
					
					ser.setExceptionLocation(locationAPIResponse.getName());
					
				} else if((ser.getAddress1() == null || ser.getAddress1().length() == 0) 
						|| (ser.getCity() == null || ser.getCity().length() == 0) 
						|| (ser.getState() == null || ser.getState().length() == 0)) {
					
					ser.setAddress1(locationAPIResponse.getAddress1());
					ser.setCity(locationAPIResponse.getCity());
					ser.setState(locationAPIResponse.getState());
					
					String address = "";
					
					if(ser.getAddress1() == null || ser.getAddress1().length() == 0) {
						return ser;
	                }
					address = ser.getAddress1();
					
					if(ser.getCity() != null || ser.getCity().length() != 0) {
						address = address + ", " + ser.getCity();
					}
					
					if(ser.getState() != null || ser.getState().length() != 0) {
						address = address + ", " + ser.getState();
					}
				
                	ser.setExceptionLocation(address);
					
				} else {
					
					String address = "";
					
					if(ser.getAddress1() == null || ser.getAddress1().length() == 0) {
						return ser;
	                }
					address = ser.getAddress1();
					
					if(ser.getCity() != null || ser.getCity().length() != 0) {
						address = address + ", " + ser.getCity();
					}
					
					if(ser.getState() != null || ser.getState().length() != 0) {
						address = address + ", " + ser.getState();
					}
				
                	ser.setExceptionLocation(address);
                	
                }
			}
		} catch (Exception e) {
			logger.error("Exception occurred while creating ShipmentViewServiceImpl.getShipmentDisplayException - exception: ", e);
		}

    	return ser;
    }
    
    private List<ShipmentEventResponse> mapEventResponses(List<ShipmentEvent> shipmentEvents,
            List<Shipment> shipments) {
        Map<String, String> woNumberToLegModeMap = new HashMap<>();
        shipments.forEach(sh -> {
            woNumberToLegModeMap.put(sh.getReferenceValue(ShipmentReferenceType.WORKORDERNUM), sh.getLegMode());
        });
        List<ShipmentEventResponse> results = new ArrayList<>();
        for (ShipmentEvent se : shipmentEvents) {
            ShipmentEventResponse eventResponse = shipmentEventMapper.map(se, ShipmentEventResponse.class);
            eventResponse.setShipmentLegMode(woNumberToLegModeMap.get(se.getWorkOrderNumber()));
            results.add(eventResponse);
        }
        return results;
    }

    private void setShipmentMilestones(E2EShipmentDetailsResponse shipmentDetails,
            Iterable<ShipmentMilestone> shipmentMilestones, List<Shipment> shipments) throws Exception {
        List<LocationResponse> milestoneLocations = new ArrayList<>();

        Map<Long, Shipment> shipmentIdMap = new HashMap<>();

        for (Shipment shipment : shipments) {
            if (!WorkorderStatus.Cancelled.name().equals(shipment.getStatus())) {
                shipmentIdMap.put(shipment.getShipmentId(), shipment);
                LocationResponse locOrigin = new LocationResponse(shipment.getOriginLocationId());
                locOrigin.setLocation(shipment.getOrigin());

                // Set Location Type___________________________________________
                String locationType = getLocationType(shipment.getOrigin(), shipment.getOriginAddress1(),
                        shipment.getOriginAddress2(), shipment.getOriginCity(), shipment.getOriginState(),
                        shipment.getOriginZipcode(), shipment.getOriginCountry(), shipment.getLegMode(),
                        shipment.getOrigin(), shipment.getOrigin_LocationId());

                locOrigin.setLocationType(locationType);

                if ((shipment.getOrigin() != null || shipment.getOriginAddress1() != null)
                        && shipment.getOriginCity() != null) {
                    locOrigin.setAddress1(shipment.getOriginAddress1());
                    locOrigin.setAddress2(shipment.getOriginAddress2());
                    locOrigin.setCity(shipment.getOriginCity());
                    locOrigin.setState(shipment.getOriginState());
                    locOrigin.setZipcode(shipment.getOriginZipcode());
                    locOrigin.setCountry(shipment.getOriginCountry());
                } else {// only lookup if no address details
                    populateLocationResponse(locOrigin, shipment.getOriginLocationId());
                }

                if (!milestoneLocations.contains(locOrigin))
                    milestoneLocations.add(locOrigin);

                List<Stop> shipmentStops = stopRepository.findByShipmentOrderByStopNumber(shipment);
                if (shipmentStops.size() > 2) {
                    for (int i = 1; i < shipmentStops.size() - 1; i++) {
                        Stop shStop = shipmentStops.get(i);
                        String locationUuid = shipmentUtil.getLocationIdAll(shStop.getName(), shStop.getAddress1(),
                                shStop.getAddress2(), shStop.getCity(), shStop.getState(), shStop.getPostalCode(),
                                shStop.getCountry());
                        LocationResponse loc = new LocationResponse(locationUuid);
                        loc.setLocation(shStop.getName());
                        String locType = shipmentUtil.getLocationType(locationUuid);
                        if (locType == null) {
                            loc.setLocationType(LocationTypeDesc.DC.getLocationTypeDescription());
                            // loc.setLocationType("UNKNOWN");
                        } else {
                            loc.setLocationType(LocationTypeDesc.getTypeDesc(locType));
                        }
                        setLocationResponse(loc, shStop);
                        if (!milestoneLocations.contains(loc))
                            milestoneLocations.add(loc);
                    }
                }

                LocationResponse locDest = new LocationResponse(shipment.getDestinationLocationId());

                locDest.setLocation(shipment.getDestination());

                // Set Location Type___________________________________________

                String locagtionType2 = getLocationType(shipment.getDestination(), shipment.getDestinationAddress1(),
                        shipment.getDestinationAddress2(), shipment.getDestinationCity(),
                        shipment.getDestinationState(), shipment.getDestinationZipcode(),
                        shipment.getDestinationCountry(), shipment.getLegMode(), shipment.getDestination(),
                        shipment.getDestination_LocationId());

                locDest.setLocationType(locagtionType2);

                if ((shipment.getDestination() != null || shipment.getDestinationAddress1() != null)
                        && shipment.getDestinationCity() != null) {
                    locDest.setAddress1(shipment.getDestinationAddress1());
                    locDest.setAddress2(shipment.getDestinationAddress2());
                    locDest.setCity(shipment.getDestinationCity());
                    locDest.setState(shipment.getDestinationState());
                    locDest.setZipcode(shipment.getDestinationZipcode());
                    locDest.setCountry(shipment.getDestinationCountry());
                } else {
                    populateLocationResponse(locDest, shipment.getDestinationLocationId());
                }


                if (!milestoneLocations.contains(locDest))
                    milestoneLocations.add(locDest);
            }
        }

        Map<String, List<ShipmentMilestoneResponse>> shipmentMilestoneMap = new LinkedHashMap<>();
        shipmentMilestones.forEach(shMilestone -> {

            if (shMilestone.getMilestone().isDisplay() && shipmentIdMap.containsKey(shMilestone.getShipmentId())) {
                Shipment shipment = shipmentIdMap.get(shMilestone.getShipmentId());
                String key = shMilestone.getLocationId();
                if (key != null) {
                    key = key.replaceAll(Pattern.quote(ShipmentUtil.AddrSplitChar + ShipmentUtil.AddrSplitChar),
                            ShipmentUtil.AddrSplitChar);
                } else {
                    key = shMilestone.getLocationName();
                }

                if (shMilestone.getLocationId() != null
                        && (shMilestone.getLocationId().equalsIgnoreCase(shipment.getOrigin())
                                || shMilestone.getLocationId().equalsIgnoreCase(shipment.getOriginLocationId()))) {
                    key = shipment.getOriginLocationId();
                } else if (shMilestone.getLocationId() != null
                        && (shMilestone.getLocationId().equalsIgnoreCase(shipment.getDestination())
                                || shMilestone.getLocationId().equalsIgnoreCase(shipment.getDestinationLocationId()))) {
                    key = shipment.getDestinationLocationId();
                }
                /// FIXME refactor this part and milestone creation part to use new location field columns directly
                String addressKey = shipmentUtil.getLocationIdAsStringSeparatedByPipe(shipment.getDestinationAddress1(),
                        shipment.getDestinationAddress2(), shipment.getDestinationCity(), shipment.getBillToState(),
                        shipment.getDestinationZipcode(), shipment.getBillToCountry());

                List<ShipmentMilestoneResponse> milestones = shipmentMilestoneMap.get(key);

                if (milestones == null) {
                    milestones = new ArrayList<>();
                    shipmentMilestoneMap.put(key, milestones);
                }
                ShipmentMilestoneResponse smr =
                        shipmentMilestoneMapper.map(shMilestone, ShipmentMilestoneResponse.class);
                List<ShipmentException> shipmentExceptions =
                        shipmentExceptionRepository.findByShipmentMilestoneId(shMilestone.getShipmentMilestoneId());
                if (!shipmentExceptions.isEmpty()) {
                    smr.setExceptionCat(shipmentExceptions.get(0).getCategory());
                }

                String locationDetails = shipmentUtil.formatLocation(addressKey);
                smr.setLocationDetails(locationDetails);

                milestones.add(smr);

            }
        });


        shipmentDetails.setMilestoneLocations(milestoneLocations);
        shipmentDetails.setShipmentMilestoneMap(shipmentMilestoneMap);
    }

    private void setLocationResponse(LocationResponse loc, Stop shStop) {
        loc.setAddress1(shStop.getAddress1());
        loc.setAddress2(shStop.getAddress2());
        loc.setCity(shStop.getCity());
        loc.setState(shStop.getState());
        loc.setZipcode(shStop.getPostalCode());
        loc.setCountry(shStop.getCountry());
    }

    private String getLocationType(String name, String address1, String address2, String city, String state,
            String postalCode, String country, String legMode, String position, String locationId) {

        // commenting the location services as it is unstable. Given a situation where location service is fetching
        // incorrect
        // location type the flow is not reaching location master. This leads to an issue in shipment details page(Icons
        // don't show up)
        // Once location service is stable this can be uncommented

        // add null check and enable it. Location Master table data is not copied during Production deployment
        String locationTypeDesc = null;
		/*
		 * if (locationId != null && !locationId.isEmpty()) { try { LocationAPIResponse
		 * location = shipmentUtil.getLocationsByLocationId(locationId); if (location !=
		 * null && location.getLocationType() != null) { String type =
		 * location.getLocationType(); locationTypeDesc =
		 * LocationTypeDesc.getTypeDesc(type); } else { String type =
		 * shipmentUtil.getLocationType(name, address1, address2, city, state,
		 * postalCode, country); if (type != null) { locationTypeDesc =
		 * LocationTypeDesc.getTypeDesc(type); } } } catch (Exception e) { logger.error(
		 * "Exception inside ShipmentViewSersServiceImpl.getLocationType.Catch_Block:" +
		 * e.getMessage()); } }
		 * 
		 * if (locationTypeDesc != null) return locationTypeDesc;
		 */


        // Commenting the below line as it is taking the wrong info

        /*
         * switch (legMode) { case ShipmentConstants.LEGMODE_DRAY: locType = LocationTypeDesc.MFG_PLANT.locationType;
         * locTypeDesc = LocationTypeDesc.MFG_PLANT.locationTypeDescription; break;
         * 
         * case ShipmentConstants.LEGMODE_OCEAN: locType = LocationTypeDesc.PORT.locationType; locTypeDesc =
         * LocationTypeDesc.PORT.locationTypeDescription; break;
         * 
         * case ShipmentConstants.LEGMODE_RAIL: locType = LocationTypeDesc.RAIL_YARD.locationType; locTypeDesc =
         * LocationTypeDesc.RAIL_YARD.locationTypeDescription; break;
         * 
         * case ShipmentConstants.LEGMODE_AIR: locType = LocationTypeDesc.AIRPORT.locationType; locTypeDesc =
         * LocationTypeDesc.AIRPORT.locationTypeDescription; break;
         * 
         * default: locType = LocationTypeDesc.DC.locationType; locTypeDesc =
         * LocationTypeDesc.DC.locationTypeDescription; break; }
         * 
         */

        // TODO remove this after location service stable
        LocationMaster locationType1 = locationMasterRepository.getLocationTypeByLocationName(position.toLowerCase());
        if (locationType1 != null) {
            locationTypeDesc = locationType1.getTypeDescription();
        }
        if (locationTypeDesc != null)
            return locationTypeDesc;
        else
            return LocationTypeDesc.DC.getLocationTypeDescription();
    }



    private void populateLocationResponse(LocationResponse loc, String locationId) {
        LocationAPIResponse locationAPIResponse = null;
        List<LocationAPIResponse> respList = null;
        try {
            if (null == locationId) {
                respList = new ArrayList<LocationAPIResponse>();
                locationMaster.map(loc, locationAPIResponse);
                respList.add(locationAPIResponse);
                locationId = shipmentUtil.getLocationIdByAddress(respList);
            }
            locationAPIResponse = shipmentUtil.getLocationsByLocationId(locationId);
            if (locationAPIResponse == null) {
                loc.setAddress1("");
                loc.setAddress2("");
                loc.setCity("");
                loc.setState("");
                loc.setZipcode("");
                loc.setCountry("");
            } else {
                loc.setAddress1(locationAPIResponse.getAddress1());
                loc.setAddress2(locationAPIResponse.getAddress2());
                loc.setCity(locationAPIResponse.getCity());
                loc.setState(locationAPIResponse.getState());
                loc.setZipcode(locationAPIResponse.getPostalCode());
                loc.setCountry(locationAPIResponse.getCountry());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("Exception inside ShipmentViewSersServiceImpl.populateLocationResponse.Catch_Block:"
                    + e.getMessage());
        }



        /*
         * if(allLocFields!=null&&allLocFields.indexOf(ShipmentUtil.AddrSplitChar)>0) { String[]
         * addr=allLocFields.split(Pattern.quote(ShipmentUtil.AddrSplitChar));
         * 
         * if(addr.length==6) { loc.setAddress1(addr[0]); loc.setAddress2(addr[1]); loc.setCity(addr[2]);
         * loc.setState(addr[3]); loc.setZipcode(addr[4]); loc.setCountry(addr[5]); }
         * 
         * }
         */
    }

    // check and set any lookup values that are missing from workorder request object
    @SuppressWarnings("unused")
    private void checkLookupValues(List<Shipment> shipments) {
        for (Shipment shipment : shipments) {
            if (shipment.getReferenceId() != null && shipment.getReferenceId().intValue() != 0) {
                if (ShipmentUtil.isNullString(shipment.getFreightDescription())) {
                    String freightDescription =
                            equipmentWORepository.selectShipmentFrieghtDescription(shipment.getReferenceId());
                    logger.debug("The value for freight: =" + freightDescription);
                    if (freightDescription != null) {
                        shipment.setFreightDescription(freightDescription);
                        shipmentRepository.save(shipment);
                    }
                }
                //
                // check shipment status from workorder status
                // TODO need verify workorder status update by shipment event and check if shipment status can rely
                // totally
                // on workorder status
                // Currently, it only use initial workorder status and cancelled status
                String status = shipmentRepository.getWorkorderStatusById(shipment.getReferenceId());
                if (ShipmentUtil.isNullString(shipment.getStatus())
                        || (status != null && WorkorderStatus.Cancelled.name().equals(status))
                        || (status != null && WorkorderStatus.Assigned.name().equals(status)
                                && WorkorderStatus.Unassigned.name().equals(shipment.getStatus()))) {
                    shipment.setStatus(status);
                    shipmentRepository.save(shipment);
                }

                // check stops
                shipmentMilestoneService.addPlannedStopsFromDM(shipment);
            }
        }
    }

    /**
     * 
     * @param shipments
     * @param shipmentDetails
     * @return empty yard shipmentIds
     */
    @SuppressWarnings("unused")
    private List<Long> removeYardFromE2EOriginDestinationDisplay(List<Shipment> shipments,
            E2EShipmentDetailsResponse shipmentDetails) {
        List<Long> emptyShipmentIds = new ArrayList<>();
        if (isOriginYardMovement(shipments.get(0))) {
            Shipment firstSh = shipments.get(0);
            shipmentDetails.setOrigin(firstSh.getDestination());
            shipmentDetails.setOriginAddress1(firstSh.getDestinationAddress1());
            shipmentDetails.setOriginAddress1(firstSh.getDestinationAddress2());
            shipmentDetails.setOriginCity(firstSh.getDestinationCity());
            shipmentDetails.setOriginState(firstSh.getDestinationState());
            shipmentDetails.setOriginZipCode(firstSh.getDestinationZipcode());
            shipmentDetails.setOriginCountry(firstSh.getDestinationCountry());
            shipments.remove(firstSh);
            emptyShipmentIds.add(firstSh.getShipmentId());
        }
        if (shipments.size() > 1 && isDestinationYardMovement(shipments.get(shipments.size() - 1))) {
            Shipment lastSh = shipments.get(shipments.size() - 1);
            shipmentDetails.setDestination(lastSh.getOrigin());
            shipmentDetails.setDestinationAddress1(lastSh.getOriginAddress1());
            shipmentDetails.setDestinationAddress1(lastSh.getOriginAddress2());
            shipmentDetails.setDestinationCity(lastSh.getOriginCity());
            shipmentDetails.setDestinationState(lastSh.getOriginState());
            shipmentDetails.setDestinationZipCode(lastSh.getOriginZipcode());
            shipmentDetails.setDestinationCountry(lastSh.getOriginCountry());
            shipments.remove(lastSh);
            emptyShipmentIds.add(lastSh.getShipmentId());
        }
        return emptyShipmentIds;
    }

    private boolean isOriginYardMovement(Shipment shipment) {
        /*
         * String destLocationId=ShipmentUtil.getLocation(shipment.getDestinationAddress1(),
         * shipment.getDestinationAddress2(), shipment.getDestinationCity(), shipment.getDestinationState(),
         * shipment.getDestinationZipcode(), shipment.getDestinationCountry());
         * 
         * LocationMaster locationType =
         * locationMasterRepository.getLocationType(destLocationId.toLowerCase().replaceAll(" ", ""));
         * if(locationType!=null&&LocationType.MFG_PLANT.name().equals(locationType.getLocationType())) return true;
         * return false;
         */

        String destLocationType = shipmentUtil.getLocationType(shipment.getDestination(),
                shipment.getDestinationAddress1(), shipment.getDestinationAddress2(), shipment.getDestinationCity(),
                shipment.getDestinationState(), shipment.getDestinationZipcode(), shipment.getDestinationCountry());

        if (destLocationType != null && LocationType.MFG_PLANT.name().equals(destLocationType))
            return true;
        return false;
    }

    private boolean isDestinationYardMovement(Shipment shipment) {
        /*
         * String destLocationId=ShipmentUtil.getLocation(shipment.getDestinationAddress1(),
         * shipment.getDestinationAddress2(), shipment.getDestinationCity(), shipment.getDestinationState(),
         * shipment.getDestinationZipcode(), shipment.getDestinationCountry());
         * 
         * LocationMaster locationType =
         * locationMasterRepository.getLocationType(destLocationId.toLowerCase().replaceAll(" ", ""));
         * if(locationType!=null&&LocationType.CONTAINER_YARD.name().equals(locationType.getLocationType())) return
         * true; return false;
         */

        String destLocationType = shipmentUtil.getLocationType(shipment.getDestination(),
                shipment.getDestinationAddress1(), shipment.getDestinationAddress2(), shipment.getDestinationCity(),
                shipment.getDestinationState(), shipment.getDestinationZipcode(), shipment.getDestinationCountry());

        if (destLocationType != null && LocationType.CONTAINER_YARD.name().equals(destLocationType))
            return true;
        return false;
    }


    private List<ShipmentLegDetailsResponse> mapShipmentToLegDetails(List<Shipment> shipments) {
        List<ShipmentLegDetailsResponse> shipmentLegs = new ArrayList<>();
        for (Shipment shipment : shipments) {
            ShipmentLegDetailsResponse legDetail =
                    shipmentLegDetailsMapper.map(shipment, ShipmentLegDetailsResponse.class);
            // set pickupDate to first shipment milestone estimated date
            List<ShipmentMilestone> milestones =
                    shipmentMilestonesRepository.findByShipmentIdOrderByEstimatedDate(shipment.getShipmentId());
            if (milestones.size() > 0) {
                legDetail.setPickupDate(milestones.get(0).getEstimatedDate());
                if (milestones.size() > 1) {
                    Date lastMilestoneDate = milestones.get(milestones.size() - 1).getEstimatedDate();
                    legDetail.setEta(lastMilestoneDate);
                } else {
                    legDetail.setEta(shipment.getExpectedDeliveryDate());
                }
            }
            shipment.getShipmentReferences().forEach(ref -> {
                setLegDetailRefvalue(ref, legDetail);
            });
            shipmentLegs.add(legDetail);
        }
        return shipmentLegs;
    }


    private void setLegDetailRefvalue(ShipmentReferences ref, ShipmentLegDetailsResponse legDetail) {
        if (ShipmentReferenceType.SHIPMENTREFNUM.name().equals(ref.getShipmentRefType()))
            legDetail.setShipmentReferenceNumber(ref.getShipmentRefValue());
        else if (ShipmentReferenceType.BILLOFLADING.name().equals(ref.getShipmentRefType()))
            legDetail.setBillOfLadingNumber(ref.getShipmentRefValue());
        else if (ShipmentReferenceType.RAILBILLINGNUMBER.name().equals(ref.getShipmentRefType()))
            legDetail.setRailBillingNumber(ref.getShipmentRefValue());
        else if (ShipmentReferenceType.HOUSEAIRWAYBILLNUM.name().equals(ref.getShipmentRefType()))
            legDetail.setHouseAirwayBillNumber(ref.getShipmentRefValue());
        else if (ShipmentReferenceType.MASTERAIRWAYBILLNUMBER.name().equals(ref.getShipmentRefType()))
            legDetail.setMasterAirwayBillNumber(ref.getShipmentRefValue());
        else if (ShipmentReferenceType.WORKORDERNUM.name().equals(ref.getShipmentRefType()))
            legDetail.setWorkOrderNumber(ref.getShipmentRefValue());
        else if (ShipmentReferenceType.BOOKINGNUM.name().equals(ref.getShipmentRefType()))
            legDetail.setBookingNumber(ref.getShipmentRefValue());
    }

    private List<ShipmentEvent> findShipmentEventsByReferences(List<Shipment> shipments) {
        List<ShipmentEvent> shipmentEvents = new ArrayList<>();
        Set<String> workordernumber = new HashSet<>();
        Set<String> billofladingnumber = new HashSet<>();
        Set<String> shipmentRefNum = new HashSet<>();
        Set<String> unitId = new HashSet<>();
        for (Shipment shipment : shipments) {
            shipment.getShipmentReferences().forEach(refr -> {
                if (ShipmentReferenceType.WORKORDERNUM.name().equals(refr.getShipmentRefType()))
                    workordernumber.add(refr.getShipmentRefValue());
                if (ShipmentReferenceType.BILLOFLADING.name().equals(refr.getShipmentRefType()))
                    billofladingnumber.add(refr.getShipmentRefValue());
                if (ShipmentReferenceType.SHIPMENTREFNUM.name().equals(refr.getShipmentRefType()))
                    shipmentRefNum.add(refr.getShipmentRefValue());
            });
            if(shipment.getUnitId()!=null) {
                unitId.add(shipment.getUnitId());
            }
        }

        if (workordernumber.isEmpty() && billofladingnumber.isEmpty()&&shipmentRefNum.isEmpty()&&unitId.isEmpty()) {
            return shipmentEvents;
        } else if (workordernumber.isEmpty()) {
            workordernumber.add("");
        } else if (billofladingnumber.isEmpty()) {
            billofladingnumber.add("");
        }else if (shipmentRefNum.isEmpty()) {
            shipmentRefNum.add("");
        }else if (unitId.isEmpty()) {
            unitId.add("");
        }
        // shipmentEvents = shipmentEventRepository.searchShipmentEventsByWorkOrderNumbers(workordernumber);
       shipmentEvents = shipmentEventRepository.searchShipmentEventsByRefs(workordernumber, billofladingnumber, shipmentRefNum, unitId);
//        System.out.println(shipmentEventRepository.searchShipmentEventsByRefs(workordernumber, billofladingnumber, shipmentRefNum, unitId));

        return shipmentEvents;
    }

    @Override
    public List<E2EShipmentResponse> searchE2EShipmentByRefTypeValue(String refType, String refValue,
            String organizationCode) {
        List<String> organizationCodes = Arrays.asList(organizationCode.split("\\s*,\\s*"));
        List<E2EShipment> e2eShipments = e2eShipmentRepository
                .findE2EShipmentByRefTypeValue(refType.trim().toUpperCase(), refValue.trim(), organizationCodes);

        List<E2EShipmentResponse> e2EShipmentResponses = new ArrayList<E2EShipmentResponse>();

        for (E2EShipment e2eShipment : e2eShipments) {
            E2EShipmentResponse shipmentResponse = e2eShipmentMapper.map(e2eShipment, E2EShipmentResponse.class);
            setActiveShipmentDetail(shipmentResponse, e2eShipment);
            e2EShipmentResponses.add(shipmentResponse);
        }

        return e2EShipmentResponses;

    }



    @Override
    public List<ShipmentFreightDetailResponse> shipmentFreightList(int startRow, int endRow, String organizationCode) {
        // only date older up to 90 days will be fetched.
        Date fromDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(fromDate);
        c.add(Calendar.DATE, -90);
        fromDate = c.getTime();
        List<String> organizationCodes = Arrays.asList(organizationCode.split("\\s*,\\s*"));
        List<IShipmentFreightDetails> queryResults = e2eShipmentRepository.getShipmentFreightDetails(startRow, endRow,
                new Date(), fromDate, organizationCodes);
        List<ShipmentFreightDetailResponse> shipmentFreightDetails = new ArrayList<>();
        Map<String, E2EShipmentResponse> e2eShipmentMap = new HashMap<>();
        for (IShipmentFreightDetails sf : queryResults) {
            ShipmentFreightDetailResponse oneData = new ShipmentFreightDetailResponse(sf.getShipmentId(),
                    sf.getBookingNumber(), sf.getFreightDescription(), sf.getShipmentReferenceNumber(), sf.getStatus(),
                    sf.getConsignee(), sf.getOrigin(), sf.getOriginAddress1(), sf.getOriginAddress2(),
                    sf.getOriginCity(), sf.getOriginState(), sf.getOriginZipCode(), sf.getOriginCountry(),
                    sf.getDestination(), sf.getDestinationAddress1(), sf.getDestinationAddress2(),
                    sf.getDestinationCity(), sf.getDestinationState(), sf.getDestinationZipCode(),
                    sf.getDestinationCountry(), sf.getPurchaseOrderNumber(), sf.getSkuNumber(), sf.getRequestedDate(),
                    sf.getPromisedDate(), sf.getLogisticProvider(), sf.getPoBuyerName(), sf.getAsnNumber(),
                    sf.getOrderStatus(), sf.getUnitId(), sf.getRequestedQuantity(), sf.getPromisedQuantity(),
                    sf.getPoItemId());

            if (e2eShipmentMap.containsKey(sf.getShipmentId() + "")) {
                if (ShipmentConstants.ACTIVE.equals(oneData.getStatus())) {
                    oneData.setOrderStatus(ShipmentConstants.ACTIVE);
                    oneData.setActiveShipment(e2eShipmentMap.get(sf.getShipmentId() + "").getActiveShipment());
                }
            } else {
                E2EShipmentResponse shipmentResponse = new E2EShipmentResponse();
                if (sf.getShipmentId() != null) {// expect e2e shipment ID NULL here
                    Optional<E2EShipment> e2eShipmentObj = e2eShipmentRepository.findById(sf.getShipmentId());
                    if (e2eShipmentObj != null) {// expect e2e shipment NULL here - due to some data issue
                        E2EShipment e2eShipment = e2eShipmentObj.get();
                        setActiveShipmentDetail(shipmentResponse, e2eShipment);
                        e2eShipmentMap.put(sf.getShipmentId() + "", shipmentResponse);
                    }
                }
                if (shipmentResponse.getActiveShipment() != null
                        && ShipmentConstants.ACTIVE.equals(oneData.getStatus())) {
                    oneData.setOrderStatus(ShipmentConstants.ACTIVE);
                    oneData.setActiveShipment(shipmentResponse.getActiveShipment());
                }
            }

            shipmentFreightDetails.add(oneData);
        }
        return shipmentFreightDetails;
    }



    /**
     * Search API for Purchase Order Summary Page which search by fields
     * 
     * @param dataType -> search data type - PO, ASN, SKU, SHIPMENTREFNUM, UNITID
     * @param searchValue -> value of dataType
     * @param organizationCode
     * @return List of ShipmentFreightDetailResponse
     */
    @Override
    public List<ShipmentFreightDetailResponse> searchShipmentFreightDetailsByRefTypeValue(String dataType,
            String searchValue, String organizationCode) {
        List<IShipmentFreightDetails> queryResults =
                e2eShipmentRepository.searchShipmentFreightDetails(dataType, searchValue, organizationCode);

        List<ShipmentFreightDetailResponse> shipmentFreightDetails = new ArrayList<>();
        Map<String, E2EShipmentResponse> e2eShipmentMap = new HashMap<>();
        for (IShipmentFreightDetails sf : queryResults) {
            ShipmentFreightDetailResponse oneData = new ShipmentFreightDetailResponse(sf.getShipmentId(),
                    sf.getBookingNumber(), sf.getFreightDescription(), sf.getShipmentReferenceNumber(), sf.getStatus(),
                    sf.getConsignee(), sf.getOrigin(), sf.getOriginAddress1(), sf.getOriginAddress2(),
                    sf.getOriginCity(), sf.getOriginState(), sf.getOriginZipCode(), sf.getOriginCountry(),
                    sf.getDestination(), sf.getDestinationAddress1(), sf.getDestinationAddress2(),
                    sf.getDestinationCity(), sf.getDestinationState(), sf.getDestinationZipCode(),
                    sf.getDestinationCountry(), sf.getPurchaseOrderNumber(), sf.getSkuNumber(), sf.getRequestedDate(),
                    sf.getPromisedDate(), sf.getLogisticProvider(), sf.getPoBuyerName(), sf.getAsnNumber(),
                    sf.getOrderStatus(), sf.getUnitId(), sf.getRequestedQuantity(), sf.getPromisedQuantity(),
                    sf.getPoItemId());
            if (e2eShipmentMap.containsKey(sf.getShipmentId() + "")) {
                if (ShipmentConstants.ACTIVE.equals(oneData.getStatus())) {
                    oneData.setOrderStatus(ShipmentConstants.ACTIVE);
                    oneData.setActiveShipment(e2eShipmentMap.get(sf.getShipmentId() + "").getActiveShipment());
                }
            } else {
                E2EShipmentResponse shipmentResponse = new E2EShipmentResponse();
                if (sf.getShipmentId() != null) {// expect e2e shipment ID NULL here
                    Optional<E2EShipment> e2eShipmentObj = e2eShipmentRepository.findById(sf.getShipmentId());
                    if (e2eShipmentObj != null) {// expect e2e shipment NULL here - due to some data issue
                        E2EShipment e2eShipment = e2eShipmentObj.get();
                        setActiveShipmentDetail(shipmentResponse, e2eShipment);
                        e2eShipmentMap.put(sf.getShipmentId() + "", shipmentResponse);
                    }
                }
                if (shipmentResponse.getActiveShipment() != null
                        && ShipmentConstants.ACTIVE.equals(oneData.getStatus())) {
                    oneData.setOrderStatus(ShipmentConstants.ACTIVE);
                    oneData.setActiveShipment(shipmentResponse.getActiveShipment());
                }
            }
            shipmentFreightDetails.add(oneData);
        }

        return shipmentFreightDetails;
    }

    @Override
    public E2EShipmentResponse getE2EShipmentStatusToOrderDetails(String shipmentNumber, String organizationCode) {
        try {

            E2EShipmentResponse shipmentDetails = new E2EShipmentResponse();


            // E2E shipment use only organization code
            if (organizationCode == null || organizationCode.isEmpty()) {

                if (logger.isDebugEnabled()) {
                    logger.debug("exiting shipmentList()");
                }
                throw new DataNotFoundException(ShipmentConstants.EMPTY_ORG_CODE_MESSAGE);
            }

            // only date older up to 90 days will be fetched.
            Date fromDate = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(fromDate);
            c.add(Calendar.DATE, -90);
            fromDate = c.getTime();
            List<String> organizationCodes = Arrays.asList(organizationCode.split("\\s*,\\s*"));
            E2EShipment e2eShipments = e2eShipmentRepository.getE2EShipmentStatusToOrderDetails(new Date(), fromDate,
                    shipmentNumber, organizationCodes);

            if (e2eShipments != null) {
                try {

                    shipmentDetails = e2eShipmentMapper.map(e2eShipments, E2EShipmentResponse.class);
                    setActiveShipmentDetail(shipmentDetails, e2eShipments);


                } catch (Exception e) {
                    logger.error(
                            "Exception inside ShipmentViewSersServiceImpl.getE2EShipmentStatusToOrderDetails.Catch_Block:"
                                    + e.getMessage());
                }

            }

            return shipmentDetails;

        } catch (Exception e) {
            logger.error("Exception inside ShipmentViewSersServiceImpl.getE2EShipmentStatusToOrderDetails.Catch_Block:"
                    + e.getMessage());
            throw e;
        }

    }
}

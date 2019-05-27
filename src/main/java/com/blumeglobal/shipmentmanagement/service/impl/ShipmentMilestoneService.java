package com.blumeglobal.shipmentmanagement.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blumeglobal.shipmentmanagement.dao.repositories.E2EShipmentRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.LocationMasterRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.MilestonesRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.RoutingRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentExceptionRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentMilestonesRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.StopRepository;
import com.blumeglobal.shipmentmanagement.dm.ShipmentEvent;
import com.blumeglobal.shipmentmanagement.dm.ShipmentEventRepository;
import com.blumeglobal.shipmentmanagement.dm.WoStop;
import com.blumeglobal.shipmentmanagement.dm.WoStopRepository;
import com.blumeglobal.shipmentmanagement.enums.ExceptionCategory;
import com.blumeglobal.shipmentmanagement.enums.ExceptionCode;
import com.blumeglobal.shipmentmanagement.enums.LocationType;
import com.blumeglobal.shipmentmanagement.enums.MilestoneTiedLocation;
import com.blumeglobal.shipmentmanagement.enums.ShipmentExceptionType;
import com.blumeglobal.shipmentmanagement.enums.ShipmentMode;
import com.blumeglobal.shipmentmanagement.enums.ShipmentReferenceType;
import com.blumeglobal.shipmentmanagement.enums.WorkorderStatus;
import com.blumeglobal.shipmentmanagement.model.E2EShipment;
import com.blumeglobal.shipmentmanagement.model.Milestone;
import com.blumeglobal.shipmentmanagement.model.Routing;
import com.blumeglobal.shipmentmanagement.model.Shipment;
import com.blumeglobal.shipmentmanagement.model.ShipmentException;
import com.blumeglobal.shipmentmanagement.model.ShipmentMilestone;
import com.blumeglobal.shipmentmanagement.model.Stop;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentMilestoneMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.StopMapper;
import com.blumeglobal.shipmentmanagement.request.ShipmentExceptionRequest;
import com.blumeglobal.shipmentmanagement.request.common.GenericShipmentRequest;
import com.blumeglobal.shipmentmanagement.response.GenericUpdateResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentMilestoneForRuleEngine;
import com.blumeglobal.shipmentmanagement.response.ShipmentMilestoneResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentMilestonesResponse;
import com.blumeglobal.shipmentmanagement.service.IShipmentMilestoneService;
import com.blumeglobal.shipmentmanagement.utils.DateUtil;
import com.blumeglobal.shipmentmanagement.utils.ShipmentUtil;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

@Service
@REZ1Logger
@REZ1PerformanceLogger
public class ShipmentMilestoneService implements IShipmentMilestoneService {
    private static final Logger logger = LogManager.getLogger(ShipmentMilestoneService.class);
    @Autowired
    RoutingRepository routingRepository;

    @Autowired
    ShipmentRepository shipmentRepository;

    @Autowired
    ShipmentUtil shipmentUtil;

    @Autowired
    MilestonesRepository milestonesRepository;

    @Autowired
    ShipmentMilestonesRepository shipmentMilestonesRepository;

    @Autowired
    ShipmentMilestoneMapper shipmentMilestoneMapper;

    @Autowired
    E2EShipmentRepository e2eShipmentRepository;

    @Autowired
    ShipmentEventRepository shipmentEventRepository;

    @Autowired
    LocationMasterRepository locationMasterRepository;

    @Autowired
    ShipmentEventServiceImpl shipmentEventServiceImpl;

    @Autowired
    ShipmentExceptionRepository shipmentExceptionRepository;

    @Autowired
    WoStopRepository woStopRepository;

    @Autowired
    StopRepository stopRepository;

    @Autowired
    StopMapper stopMapper;

    @Override
    public List<ShipmentMilestoneResponse> getShipmentMilestones(Long shipmentId) throws Exception {
        if (shipmentId == null)
            throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTINVALID.toString(),
                    "Shipment ID should not be null");

        Shipment shipment = shipmentRepository.findByShipmentId(shipmentId);
        if (shipment == null)
            throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTNOTFOUND.toString(),
                    "No Shipment found by shipment ID " + shipmentId);
        List<ShipmentMilestone> shipmentMilestones =
                shipmentMilestonesRepository.findByShipmentIdOrderByEstimatedDate(shipmentId);
        if (!shipmentMilestones.isEmpty()) {
            return shipmentMilestoneMapper.mapAsList(shipmentMilestones, ShipmentMilestoneResponse.class);
        }

        return shipmentMilestoneMapper.mapAsList(createShipmentMilestones(shipment), ShipmentMilestoneResponse.class);
    }

    @Override
    public List<ShipmentMilestone> createShipmentMilestones(Shipment shipment) {
        logger.info("Entering ShipmentMilestoneService.createShipmentMilestones");
        List<ShipmentMilestone> results = new ArrayList<>();
        List<Milestone> milestones = milestonesRepository
                .getMilestoneByModeAndOrganization(shipment.getLegMode().toLowerCase(), shipment.getOrganizationCode());
        Date previousMilestoneEta = getParentShipmentEta(shipment);
        List<ShipmentMilestone> destMilestones = new ArrayList<>();
        for (Milestone milestone : milestones) {
            ShipmentMilestone sml = new ShipmentMilestone();
            sml.setMilestone(milestone);
            sml.setShipmentId(shipment.getShipmentId());
            sml.setE2eShipmentId(shipment.getE2eShipmentId());
            sml.setModeOfTransport(shipment.getLegMode());
            sml.setMilestoneName(milestone.getMilestoneName());

            if (milestone.getTiedToLocation().equals(MilestoneTiedLocation.Origin.name())) {
                if (shipment.getOriginLocationId() == null) {
                    sml.setLocationId(shipmentUtil.getLocationIdAsStringSeparatedByPipe(shipment.getOriginAddress1(),
                            shipment.getOriginAddress2(), shipment.getOriginCity(), shipment.getOriginState(),
                            shipment.getOriginZipcode(), shipment.getOriginCountry()));
                } else {
                    sml.setLocationId(shipment.getOriginLocationId());
                }

                Date estimatedDate = getLeadTimeAdjustedEta(sml, previousMilestoneEta);
                sml.setEstimatedDate(estimatedDate);

                // only set planned date initially at shipment milestone creation
                sml.setPlannedDate(estimatedDate);

                // Parshwa Map new Fields
                sml.setAddress1(shipment.getOriginAddress1());
                sml.setAddress2(shipment.getOriginAddress2());
                sml.setCity(shipment.getOriginCity());
                sml.setState(shipment.getOriginState());
                sml.setZipcode(shipment.getOriginZipcode());
                sml.setCountry(shipment.getOriginCountry());
                sml.setUnlocode(shipment.getOriginUnlocode());

                sml.setLocationName(shipment.getOrigin());
                sml.setLocationType(shipmentUtil.getLocationType(shipment.getOrigin(), shipment.getOriginAddress1(),
                        shipment.getOriginAddress2(), shipment.getOriginCity(), shipment.getOriginState(),
                        shipment.getOriginZipcode(), shipment.getOriginCountry()));

            } else {
                if (shipment.getDestination_LocationId() == null) {
                    sml.setLocationId(shipmentUtil.getLocationIdAsStringSeparatedByPipe(
                            shipment.getDestinationAddress1(), shipment.getDestinationAddress2(),
                            shipment.getDestinationCity(), shipment.getDestinationState(),
                            shipment.getDestinationZipcode(), shipment.getDestinationCountry()));
                } else {
                    sml.setLocationId(shipment.getDestinationLocationId());
                }

                setMilestoneLeadTime(shipment, milestone, sml);

                Date estimatedDate = getLeadTimeAdjustedEta(sml, previousMilestoneEta);
                sml.setEstimatedDate(estimatedDate);

                sml.setPlannedDate(estimatedDate);


                // Parshwa Map new Fields
                sml.setAddress1(shipment.getDestinationAddress1());
                sml.setAddress2(shipment.getDestinationAddress2());
                sml.setCity(shipment.getDestinationCity());
                sml.setState(shipment.getDestinationState());
                sml.setZipcode(shipment.getDestinationZipcode());
                sml.setCountry(shipment.getDestinationCountry());
                sml.setUnlocode(shipment.getDestinationUnlocode());

                sml.setLocationName(shipment.getDestination());
                sml.setLocationType(shipmentUtil.getLocationType(shipment.getDestination(),
                        shipment.getDestinationAddress1(), shipment.getDestinationAddress2(),
                        shipment.getDestinationCity(), shipment.getDestinationState(), shipment.getDestinationZipcode(),
                        shipment.getDestinationCountry()));
                destMilestones.add(sml);
            }

            shipmentMilestonesRepository.save(sml);
            results.add(sml);
            previousMilestoneEta = sml.getEstimatedDate();
        }
        // adjust transit lead time based on destination ETA if there is no estimated lead time
        // between origin and destination
        adjustDestinationMilestoneEta(destMilestones, shipment);
        return results;
    }

    @Override
    public void updateShipmentMilestones(GenericShipmentRequest createShipmentRequest, Shipment shipment) {
        logger.info("Entering ShipmentMilestoneService.updateShipmentMilestones");
        try {
            List<ShipmentMilestone> milestones = shipmentMilestonesRepository
                    .findByE2eShipmentIdOrderByShipmentMilestoneId(shipment.getE2eShipmentId());
            Date pickUpDate = createShipmentRequest.getScheduledPickupDate();
            Date deliveryDate = createShipmentRequest.getScheduledDeliveryDate();
            if (null != milestones && milestones.size() > 0) {
                for (ShipmentMilestone milestone : milestones) {
                    if (createShipmentRequest.getPortOfLoading().equalsIgnoreCase(milestone.getLocationName())) {
                        pickUpDate = getLeadTimeAdjustedEta(milestone, pickUpDate);
                        milestone.setPlannedDate(pickUpDate);
                        milestone.setEstimatedDate(pickUpDate);
                    }
                    if (createShipmentRequest.getPortOfDischarge().equalsIgnoreCase(milestone.getLocationName())) {
                        deliveryDate = getLeadTimeAdjustedEta(milestone, deliveryDate);
                        milestone.setPlannedDate(deliveryDate);
                        milestone.setEstimatedDate(deliveryDate);
                    }
                }
                shipmentMilestonesRepository.saveAll(milestones);
            }
        } catch (Exception e) {
            logger.error("Exception in updating shipment mile stones" + e);
        }
    }

    private void adjustDestinationMilestoneEta(List<ShipmentMilestone> destMilestones, Shipment shipment) {
        if (destMilestones == null || destMilestones.isEmpty())
            return;
        else {
            ShipmentMilestone lastMilestone = destMilestones.get(destMilestones.size() - 1);
            boolean noTransitLeadSetup = false;
            for (ShipmentMilestone sml : destMilestones) {
                if (sml.getMilestone() != null && sml.getMilestone().isWithLeadTime()
                        && (sml.getEstimatedLeadTime() == null || sml.getEstimatedLeadTime().intValue() == 0)) {
                    noTransitLeadSetup = true;
                    break;
                }
            }

            if (noTransitLeadSetup && shipment.getExpectedDeliveryDate().after(lastMilestone.getPlannedDate())) {
                Long diff = DateUtil.getTimeDiff(lastMilestone.getPlannedDate(), shipment.getExpectedDeliveryDate());
                lastMilestone.setEstimatedDate(shipment.getExpectedDeliveryDate());
                lastMilestone.setPlannedDate(shipment.getExpectedDeliveryDate());
                shipmentMilestonesRepository.save(lastMilestone);
                if (destMilestones.size() > 1) {
                    for (int i = 0; i < destMilestones.size() - 1; i++) {
                        ShipmentMilestone sml = destMilestones.get(i);
                        sml.setEstimatedDate(DateUtil.getDateAfterMilliseconds(sml.getEstimatedDate(), diff));
                        sml.setPlannedDate(DateUtil.getDateAfterMilliseconds(sml.getPlannedDate(), diff));
                        shipmentMilestonesRepository.save(sml);
                    }
                }

            }



        }
    }

    private void setMilestoneLeadTime(Shipment shipment, Milestone milestone, ShipmentMilestone sml) {
        if (milestone.isWithLeadTime()) {
            String originAdd = ShipmentUtil.getLocationRoute(shipment.getOriginAddress1(), shipment.getOriginAddress2(),
                    shipment.getOriginCity(), shipment.getOriginState(), shipment.getOriginZipcode(),
                    shipment.getOriginCountry());
            String destAdd = ShipmentUtil.getLocationRoute(shipment.getDestinationAddress1(),
                    shipment.getDestinationAddress2(), shipment.getDestinationCity(), shipment.getDestinationState(),
                    shipment.getDestinationZipcode(), shipment.getDestinationCountry());
            String transMode = sml.getModeOfTransport();

            List<Routing> route = new ArrayList<>();
            route = routingRepository.getRoutingRecord(transMode.toLowerCase().trim(),
                    originAdd.toLowerCase().trim().replaceAll(" ", ""),
                    destAdd.toLowerCase().trim().replaceAll(" ", ""));

            if (route.size() > 0) {
                Double transittime = route.get(0).getTransitTime();
                sml.setEstimatedLeadTime(transittime);
                String uom = route.get(0).getUom();
                sml.setUom(uom);
            }
        } else {
            sml.setEstimatedLeadTime(0D);
        }
    }

    /**
     * process shipment events in le_shipmentevents to update shipment status, shipment milestone and check exception
     */
    @Override
    public void processShipmentEvents(List<Shipment> shipments, E2EShipment e2eShipment) {
        if (shipments == null || shipments.isEmpty())
            return;
        List<Shipment> previousShipments = new ArrayList<>();
        for (Shipment sh : shipments) {
            String woNumber = sh.getReferenceValue(ShipmentReferenceType.WORKORDERNUM);
            String billOfLading = sh.getReferenceValue(ShipmentReferenceType.BILLOFLADING);
            String bookingNumber = sh.getReferenceValue(ShipmentReferenceType.BOOKINGNUM);
            String shipmentReferenceNumber=sh.getReferenceValue(ShipmentReferenceType.SHIPMENTREFNUM);
            String unitId=sh.getUnitId();
            if (woNumber != null || billOfLading != null||unitId!=null
                    ||bookingNumber!=null||shipmentReferenceNumber!=null) {
                List<ShipmentEvent> events = shipmentEventRepository
                        .searchLastShipmentEventsByRefs(woNumber, billOfLading, bookingNumber, 
                                shipmentReferenceNumber, unitId);

                List<ShipmentMilestone> shipmentMilestones =
                        shipmentMilestonesRepository.findByShipmentIdOrderByShipmentMilestoneIdDesc(sh.getShipmentId());
                if (!events.isEmpty()) {
                    for (ShipmentMilestone shipmentMilestone : shipmentMilestones) {
                        // allow update actual date
                        // if(shipmentMilestone.getActualDate()==null) {
                        for (ShipmentEvent event : events) {
                            if (event.getEventCode() != null && shipmentMilestone.getMilestone().getEventCode()
                                    .equalsIgnoreCase(event.getEventCode())) {
                                // find event stop from shipmentId, stop name from stop table
                                Stop eventStop = stopRepository.findByShipmentAndName(sh, event.getLocation());
                                boolean samelocation = isSameEventLocation(shipmentMilestone, event, eventStop);
                                if (samelocation) {
                                    shipmentMilestone.setActualDate(event.getEventTime());
                                    if (!ShipmentUtil.isNullString(event.getLocation())) {
                                        shipmentMilestone.setLocationId(event.getLocation());
                                    }
                                    shipmentMilestonesRepository.save(shipmentMilestone);
                                    // check to remove exception added for shipment before events
                                    removeShipmentException(event, sh, shipmentMilestone);
                                    break;
                                }
                            }
                        }
                        // }
                    }
                    // update shipment and e2e_shipment status
                    ShipmentMilestone lastMilestone = shipmentMilestones.get(0);
                    if (lastMilestone.getActualDate() != null
                            && !WorkorderStatus.Completed.name().equalsIgnoreCase(sh.getStatus())) {
                        sh.setStatus(WorkorderStatus.Completed.name());
                        shipmentRepository.save(sh);
                        for (Shipment prevSh : previousShipments) {
                            if (!WorkorderStatus.Completed.name().equalsIgnoreCase(prevSh.getStatus())) {
                                prevSh.setStatus(WorkorderStatus.Completed.name());
                                shipmentRepository.save(prevSh);
                            }
                        }
                    } else {
                        for (ShipmentMilestone sm : shipmentMilestones) {
                            if (sm.getActualDate() != null) {
                                // mark as active is shipment is not active
                                if (!WorkorderStatus.Active.name().equalsIgnoreCase(sh.getStatus())
                                        && !WorkorderStatus.Completed.name().equalsIgnoreCase(sh.getStatus())) {
                                    sh.setStatus(WorkorderStatus.Active.name());
                                    shipmentRepository.save(sh);
                                }
                                // ignore if origin empty yard movement is active for setting e2e shipment status
                                if (!WorkorderStatus.Active.name().equalsIgnoreCase(e2eShipment.getStatus())) {
                                    e2eShipment.setStatus(WorkorderStatus.Active.name());
                                    e2eShipmentRepository.save(e2eShipment);
                                }
                                // if(!isDestinationYardMovement(sh)) {//bypass destination yard shipment, but no
                                // drop-off event for DRAY to close shipment
                                for (Shipment prevSh : previousShipments) {
                                    if (!WorkorderStatus.Completed.name().equalsIgnoreCase(prevSh.getStatus())) {
                                        prevSh.setStatus(WorkorderStatus.Completed.name());
                                        shipmentRepository.save(prevSh);
                                    }
                                }
                                // }
                                break;
                            }
                        } ;
                    }
                }

                checkShipmentException(sh, shipmentMilestones);
            }
            previousShipments.add(sh);
        }
        boolean allShipmentsCompleted = true;
        for (Shipment sh : shipments) {
            if (!WorkorderStatus.Completed.name().equals(sh.getStatus())) {
                allShipmentsCompleted = false;
                break;
            }
        }
        if (allShipmentsCompleted) {
            if (!WorkorderStatus.Completed.name().equalsIgnoreCase(e2eShipment.getStatus())) {
                e2eShipment.setStatus(WorkorderStatus.Completed.name());
                e2eShipmentRepository.save(e2eShipment);
            }
        }

        // adjust remaining not executed milestone ETA date by last actual date
        adjustE2eShipmentEtaByActualDates(e2eShipment.getShipmentId());
        // check exceptions
        for (Shipment sh : shipments) {
            List<ShipmentMilestone> shipmentMilestones =
                    shipmentMilestonesRepository.findByShipmentIdOrderByShipmentMilestoneIdDesc(sh.getShipmentId());
            checkShipmentException(sh, shipmentMilestones);
        }

    }

    private boolean isSameEventLocation(ShipmentMilestone sms, ShipmentEvent event, Stop eventStop) {
        boolean samelocation = false;
        if (sms.getLocationId() != null && event.getLocation() != null
                && sms.getLocationId().replaceAll(Pattern.quote(ShipmentUtil.AddrSplitChar), ",").replaceAll(",", "")
                        .equalsIgnoreCase(event.getLocation().replaceAll(",", ""))) {
            samelocation = true;
        } else if (sms.getLocationName() != null && event.getLocation() != null && sms.getLocationName()
                .replaceAll(" ", "").equalsIgnoreCase(event.getLocation().replaceAll(" ", ""))) {
            samelocation = true;
        } else {
            String smsUuid = sms.getLocationId();
            String eventUuid = null;
            if (smsUuid == null) {
                smsUuid = shipmentUtil.getLocationIdAll(sms.getLocationName(), sms.getAddress1(), sms.getAddress2(),
                        sms.getCity(), sms.getState(), sms.getZipcode(), sms.getCountry());
            }
            if (eventUuid == null) {
                if (eventStop != null && eventStop.getLocationId() != null) {
                    eventUuid = eventStop.getLocationId();
                } else if (eventStop != null && eventStop.getAddress1() != null) {

                } else {
                    eventUuid = shipmentUtil.getLocationIdAll(event.getLocation(), null, null, event.getCity(),
                            event.getState(), null, event.getCountry());
                }
            }

            if (smsUuid != null && eventUuid != null && eventUuid.equals(eventUuid)) {
                samelocation = true;
            }
        }

        return samelocation;
    }

    private void adjustE2eShipmentEtaByActualDates(Long shipmentId) {
        logger.info("Entering ShipmentMilestoneService.adjustE2eShipmentEtaByActualDates");
        List<ShipmentMilestone> shipmentMilestones =
                shipmentMilestonesRepository.findByE2eShipmentIdOrderByShipmentMilestoneId(shipmentId);

        for (int i = shipmentMilestones.size() - 1; i >= 0; i--) {
            ShipmentMilestone sml = shipmentMilestones.get(i);
            if (sml.getActualDate() != null) {
                int dateDiff = DateUtil.getDiffInDays(sml.getPlannedDate(), sml.getActualDate());
                if (dateDiff != 0) {
                    for (int j = i; j < shipmentMilestones.size(); j++) {
                        ShipmentMilestone smlUpdt = shipmentMilestones.get(j);
                        Date newEta = DateUtil.getAfterDates(smlUpdt.getPlannedDate(), dateDiff);
                        smlUpdt.setEstimatedDate(newEta);
                        shipmentMilestonesRepository.save(smlUpdt);
                    }
                    break;
                }
            }
        }

        for (int i = 0; i < shipmentMilestones.size(); i++) {
            ShipmentMilestone sml = shipmentMilestones.get(i);
            if (sml.getActualDate() == null && DateUtil.getDiffInDays(sml.getEstimatedDate(), new Date()) > 0) {
                int delayDay = DateUtil.getDiffInDays(sml.getPlannedDate(), new Date());
                for (int j = i; j < shipmentMilestones.size(); j++) {
                    ShipmentMilestone smlUpdt = shipmentMilestones.get(j);
                    Date newEta = DateUtil.getAfterDates(smlUpdt.getPlannedDate(), delayDay);
                    smlUpdt.setEstimatedDate(newEta);
                    shipmentMilestonesRepository.save(smlUpdt);
                }
                break;
            }
        }

    }

    private void removeShipmentException(ShipmentEvent event, Shipment shipment, ShipmentMilestone shipmentMilestone) {
        // Date shipmentExpecetedDeliveryDate = shipment.getExpectedDeliveryDate();
        Date shipmentExpecetedDate = shipmentMilestone.getPlannedDate() != null ? shipmentMilestone.getPlannedDate()
                : shipment.getExpectedDeliveryDate();
        Date eventTime = event.getEventTime();
        if (DateUtil.isSameDate(shipmentExpecetedDate, eventTime)) {
            // check if there exists a same exception by shipmentMilestoneId
            // compare date only.
            List<ShipmentException> exceptions =
                    shipmentExceptionRepository.findByShipmentMilestoneId(shipmentMilestone.getShipmentMilestoneId());
            for (ShipmentException se : exceptions) {
                shipmentExceptionRepository.delete(se);
            }
        }
    }

    @SuppressWarnings("unused")
    // use rule engine to create shipment exception
    private void checkShipmentException(Shipment shipment, List<ShipmentMilestone> shipmentMilestones) {
        //turn on internal exception check only for DRAY mode
        if(!ShipmentMode.isDrayMode(shipment.getLegMode())) return;
        for (ShipmentMilestone sm : shipmentMilestones) {
            // TODO need add rule to exclude some milestone for checking exceptions such as proof of delivery
            if ("Proof of Delivery".equalsIgnoreCase(sm.getMilestoneName()))
                continue;
            String exceptionType = null;
            ExceptionCategory exceptionCategory = ExceptionCategory.ALERT;

            if (sm.getActualDate() != null && sm.getPlannedDate() != null
                    && !DateUtil.isSameDate(sm.getPlannedDate(), sm.getActualDate())) {
                if (DateUtil.isBeforeTargetDate(sm.getPlannedDate(), sm.getActualDate())) {
                    exceptionType = ShipmentExceptionType.EARLY_MILESTONE.getDescription();
                } else {
                    exceptionType = ShipmentExceptionType.LATE_MILESTONE.getDescription();
                    exceptionCategory = ExceptionCategory.EXCEPTION;
                }
            } else if (sm.getActualDate() == null && sm.getPlannedDate() != null) {
                if (sm.getPlannedDate() != null
                        && DateUtil.isAfterTargetDate(sm.getPlannedDate(), sm.getEstimatedDate())) {
                    exceptionType = ShipmentExceptionType.LATE_MILESTONE.getDescription();
                } else if (DateUtil.isAfterTargetDate(sm.getPlannedDate(), new Date())) {
                    exceptionType = ShipmentExceptionType.LATE_MILESTONE.getDescription();
                }
                if (DateUtil.isAfterTargetDate(sm.getPlannedDate(), new Date())) {
                    exceptionCategory = ExceptionCategory.EXCEPTION;
                }
            }
            if (exceptionType != null && exceptionType.equals(ShipmentExceptionType.LATE_MILESTONE.getDescription())
                    && sm.getMilestoneName().equalsIgnoreCase("Customs Release")) {
                exceptionType = ShipmentExceptionType.CUSTOMS_DELAY.getDescription();
            }
            if (exceptionType != null) {

                // check if there exists a same exception by shipmentMilestoneId
                List<ShipmentException> exceptions =
                        shipmentExceptionRepository.findByShipmentMilestoneId(sm.getShipmentMilestoneId());

                if (exceptions.isEmpty()) {
                    ShipmentExceptionRequest shipmentException = new ShipmentExceptionRequest();
                    shipmentException.setOccuranceDate(sm.getActualDate() != null ? sm.getActualDate() : new Date());
                    shipmentException.setE2eShipmentId(shipment.getE2eShipmentId());
                    shipmentException.setModeOfTransport(shipment.getLegMode());
                    shipmentException.setExceptionType(exceptionType);
                    shipmentException.setExceptionNotes(sm.getMilestoneName());
                    shipmentException.setCategory(exceptionCategory.name());
                    shipmentException.setShipmentId(shipment.getShipmentId());
                    if (sm.getLocationId().indexOf(ShipmentUtil.AddrSplitChar) > 0) {
                        shipmentException.setExceptionLocation(sm.getLocationId().substring(0,
                                sm.getLocationId().indexOf(ShipmentUtil.AddrSplitChar)));
                    } else {
                        shipmentException.setExceptionLocation(sm.getLocationId());
                    }

                    shipmentException.setShipmentMilestoneId(sm.getShipmentMilestoneId());
                    try {
                        shipmentEventServiceImpl.createShipmentException(shipmentException);
                    } catch (Exception e) {
                        logger.error("Exception inside ShipmentMilestoneService.checkShipmentException.Catch_Block:"
                                + e.getMessage());
                    }
                } else {
                    // update exception occurance date with actual date
                    if (sm.getActualDate() != null) {
                        ShipmentException shipmentException = exceptions.get(0);
                        shipmentException.setOccuranceDate(sm.getActualDate());
                        shipmentException.setExceptionType(exceptionType);
                        shipmentException.setCategory(exceptionCategory.name());
                        shipmentExceptionRepository.save(shipmentException);
                    }
                }
            }
        }
    }



    @Override
    public List<ShipmentMilestoneResponse> updateShipmentMilestoneAndStatusByRef(String refType, String refValue) {
        List<E2EShipment> e2eShipments =
                e2eShipmentRepository.findE2EShipmentByRefTypeValue(refType.toUpperCase(), refValue);
        if (e2eShipments == null || e2eShipments.isEmpty())
            throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTNOTFOUND.toString(),
                    "No shipment found");

        try {
            E2EShipment e2eShipment = e2eShipments.get(0);
            List<Shipment> shipments = shipmentRepository.getShipmentByE2EShipmentId(e2eShipment.getShipmentId());
            processShipmentEvents(shipments, e2eShipment);
            List<ShipmentMilestone> shipmentMilestones = shipmentMilestonesRepository
                    .findByE2eShipmentIdOrderByShipmentMilestoneId(e2eShipment.getShipmentId());
            return shipmentMilestoneMapper.mapAsList(shipmentMilestones, ShipmentMilestoneResponse.class);

        } catch (Exception ex) {
            logger.error("Exception inside ShipmentMilestoneService.updateShipmentMilestoneAndStatusByRef.Catch_Block:"
                    + ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public GenericUpdateResponse updateShipmentMilestoneAndStatus() {
        logger.info("Entering ShipmentMilestoneService.updateShipmentMilestoneAndStatus");
        GenericUpdateResponse updateResponse = new GenericUpdateResponse();
        updateResponse.setRecordType("Shipment workorder number");
        List<String> updatedRecords = new ArrayList<>();
        List<String> notUpdatedRecords = new ArrayList<>();
        List<Shipment> shipments = shipmentRepository.findAllShipmentsWithoutShipmentMilestones();
        Date startTime = new Date();
        for (Shipment sh : shipments) {
            try {
                getShipmentMilestones(sh.getShipmentId());
                updatedRecords.add(sh.getReferenceValue(ShipmentReferenceType.WORKORDERNUM));
            } catch (Exception e) {
                notUpdatedRecords.add(sh.getReferenceValue(ShipmentReferenceType.WORKORDERNUM));
            }
        }
        Date endTime = new Date();

        Long runtimeSeconds = (endTime.getTime() - startTime.getTime()) / 1000;
        updateResponse.setNumberOfRecordsUpdated(updatedRecords.size());
        updateResponse.setUpdatedRecords(updatedRecords);
        updateResponse.setNotUpdatedRecords(notUpdatedRecords);
        updateResponse.setRuntimeSeconds(runtimeSeconds);
        logger.info("Exiting ShipmentMilestoneService.updateShipmentMilestoneAndStatus");
        return updateResponse;
    }

    @Override
    public GenericUpdateResponse updateAllShipmentStatusAndActuals() {
        logger.info("Entering ShipmentMilestoneService.updateAllShipmentStatusAndActuals");
        GenericUpdateResponse updateResponse = new GenericUpdateResponse();
        updateResponse.setRecordType("Shipment Reference Number");
        List<String> updatedRecords = new ArrayList<>();
        List<String> notUpdatedRecords = new ArrayList<>();
        List<E2EShipment> e2eshipments = e2eShipmentRepository.findNotCompletedShipment();
        Date startTime = new Date();
        for (E2EShipment e2eShipment : e2eshipments) {
            try {
                List<Shipment> shipments = shipmentRepository.getShipmentByE2EShipmentId(e2eShipment.getShipmentId());
                processShipmentEvents(shipments, e2eShipment);
                updatedRecords.add(e2eShipment.getShipmentNumber());
            } catch (Exception e) {
                notUpdatedRecords.add(e2eShipment.getShipmentNumber());
            }
        }
        Date endTime = new Date();

        Long runtimeSeconds = (endTime.getTime() - startTime.getTime()) / 1000;
        updateResponse.setNumberOfRecordsUpdated(updatedRecords.size());
        updateResponse.setUpdatedRecords(updatedRecords);
        updateResponse.setNotUpdatedRecords(notUpdatedRecords);
        updateResponse.setRuntimeSeconds(runtimeSeconds);
        logger.info("Exiting ShipmentMilestoneService.updateAllShipmentStatusAndActuals");
        return updateResponse;
    }

    @Override
    public void processShipmentEvent(ShipmentEvent upload) {

        Shipment shipment = findShipmentFromEvent(upload);
        if (shipment != null) {
            try {
                E2EShipment e2eShipment = e2eShipmentRepository.findById(shipment.getE2eShipmentId()).get();
                List<Shipment> shipments = shipmentRepository.getShipmentByE2EShipmentId(e2eShipment.getShipmentId());
                processShipmentEvents(shipments, e2eShipment);
            } catch (Exception ex) {
                logger.error(
                        "Exception inside ShipmentMilestoneService.processShipmentEventForWorkorderNumber.Catch_Block:"
                                + ex.getMessage());
                throw ex;
            }
        }

    }

    private Shipment findShipmentFromEvent(ShipmentEvent se) {
        if (se.getWorkOrderNumber() != null && !se.getWorkOrderNumber().isEmpty()) {
            return filterByEventReference(shipmentRepository.getShipmentFromWorkOrderNumber(se.getWorkOrderNumber()),
                    se);
        } else if (se.getBillOfLadingNumber() != null && !se.getBillOfLadingNumber().isEmpty()) {
            return filterByEventReference(
                    shipmentRepository.getShipmentFromBillOfLadingNumber(se.getBillOfLadingNumber()), se);
        } else if (se.getBookingNumber() != null && !se.getBookingNumber().isEmpty()) {
            return filterByEventReference(shipmentRepository.getShipmentFromBookingNumber(se.getBookingNumber()), se);
        } else if (se.getShipmentReferenceNumber() != null && !se.getShipmentReferenceNumber().isEmpty()) {
            return filterByEventReference(
                    shipmentRepository.getShipmentFromShipmentReferenceNumber(se.getShipmentReferenceNumber()), se);
        } else if (se.getUnitId() != null && !se.getUnitId().isEmpty()) {
            return filterByEventReference(
                    shipmentRepository.getShipmentFromUnitId(se.getUnitId()), se);
        }
        return null;

    }


    // get most matching shipment
    private Shipment filterByEventReference(List<Shipment> shipments, ShipmentEvent se) {
        if (shipments == null || shipments.isEmpty())
            return null;
        if (shipments.size() == 1)
            return shipments.get(0);
        if (se.getWorkOrderNumber() != null && !se.getWorkOrderNumber().isEmpty()) {
            shipments = filterShipmentByRef(shipments, ShipmentReferenceType.WORKORDERNUM, se.getWorkOrderNumber());
            if (shipments.size() == 1)
                return shipments.get(0);
        }
        if (se.getBillOfLadingNumber() != null && !se.getBillOfLadingNumber().isEmpty()) {
            shipments = filterShipmentByRef(shipments, ShipmentReferenceType.BILLOFLADING, se.getBillOfLadingNumber());
            if (shipments.size() == 1)
                return shipments.get(0);
        }
        if (se.getBookingNumber() != null && !se.getBookingNumber().isEmpty()) {
            shipments = filterShipmentByRef(shipments, ShipmentReferenceType.BOOKINGNUM, se.getBookingNumber());
            if (shipments.size() == 1)
                return shipments.get(0);
        }
        if (se.getShipmentReferenceNumber() != null && !se.getShipmentReferenceNumber().isEmpty()) {
            shipments = filterShipmentByRef(shipments, ShipmentReferenceType.SHIPMENTREFNUM,
                    se.getShipmentReferenceNumber());
            if (shipments.size() == 1)
                return shipments.get(0);
        }
        if (shipments.isEmpty())
            return null;
        else
            return shipments.get(0);
    }

    private List<Shipment> filterShipmentByRef(List<Shipment> shipments, ShipmentReferenceType refType,
            String refValue) {
        Iterator<Shipment> itr = shipments.iterator();
        while (itr.hasNext()) {
            Shipment sh = itr.next();
            String wrkNum = sh.getReferenceValue(refType);
            if (!refValue.equals(wrkNum)) {
                itr.remove();
            }
        }
        return shipments;
    }

    @Override
    public void addPlannedStopsFromDM(Shipment shipment) {
        // add shipment stop from DM stop table
        // use stop location to lookup lacationmaster for location type.
        // add destion only DRAY/RAIL available
        // add milestones for each stop
        // TODO remove/update stop
        List<WoStop> woStops = woStopRepository.findByWorkorderIdOrderByStopNumber(shipment.getReferenceId());
        List<Stop> shipmentStops = stopRepository.findByShipmentOrderByStopNumber(shipment);

        if (woStops.size() == shipmentStops.size())
            return;

        // Assume workorder/shipment cannot not change origin and destination. i.e. No change for first and last stop.
        for (WoStop woStop : woStops) {
            boolean notfound = true;
            for (Stop shstop : shipmentStops) {
                String shStopLocUuid =
                        shipmentUtil.getLocationIdAll(shstop.getName(), shstop.getAddress1(), shstop.getAddress2(),
                                shstop.getCity(), shstop.getState(), shstop.getPostalCode(), shstop.getCountry());
                String woStopLocUuid =
                        shipmentUtil.getLocationIdAll(woStop.getName(), woStop.getAddress1(), shstop.getAddress2(),
                                woStop.getCity(), woStop.getState(), woStop.getPostalCode(), woStop.getCountry());
                if (shstop.getStopNumber() == woStop.getStopNumber() && woStopLocUuid != null
                        && woStopLocUuid.equals(shStopLocUuid)) {
                    notfound = false;
                    break;
                }
            }
            if (notfound) {
                Stop shipmentStop = stopMapper.map(woStop, Stop.class);
                shipmentStop.setShipment(shipment);
                stopRepository.save(shipmentStop);
            }
        }

        shipmentStops = stopRepository.findByShipmentOrderByStopNumber(shipment);
        if (shipmentStops.size() > 2) {
            updateShipmentMilestones(shipmentStops, shipment);
        }
    }

    @Override
    public void updateShipmentMilestones(List<Stop> shipmentStops, Shipment shipment) {
        if (shipmentStops == null || shipmentStops.size() < 3)
            return;
        // delete shipment milestones
        List<ShipmentMilestone> intialMilestones =
                shipmentMilestonesRepository.findByShipmentIdOrderByEstimatedDate(shipment.getShipmentId());
        shipmentMilestonesRepository.deleteAll(intialMilestones);
        E2EShipment e2eShipment = e2eShipmentRepository.findById(shipment.getE2eShipmentId()).get();
        int railLocationCount = 0;

        Date previousMilestoneEta = getParentShipmentEta(shipment);
        for (int i = 1; i < shipmentStops.size(); i++) {
            Stop prevStop = shipmentStops.get(i - 1);
            Stop curStop = shipmentStops.get(i);
            String prevLocationType =
                    shipmentUtil.getLocationType(prevStop.getName(), prevStop.getAddress1(), prevStop.getAddress2(),
                            prevStop.getCity(), prevStop.getState(), prevStop.getPostalCode(), prevStop.getCountry());
            String curLocationType =
                    shipmentUtil.getLocationType(curStop.getName(), curStop.getAddress1(), curStop.getAddress2(),
                            curStop.getCity(), curStop.getState(), curStop.getPostalCode(), curStop.getCountry());
            // only DRAY and RAIL for extra stops
            ShipmentMode currentMode = ShipmentMode.DRAY;
            if (prevLocationType != null && prevLocationType.equals(LocationType.RAIL_YARD.name())
                    && curLocationType != null && curLocationType.equals(LocationType.RAIL_YARD.name())) {
                currentMode = ShipmentMode.RAIL;
                railLocationCount++;
            }
            if (i == 1) {
                prevStop.setModeOfTransport(currentMode.name());
                stopRepository.save(prevStop);
            }
            curStop.setModeOfTransport(currentMode.name());
            stopRepository.save(curStop);
            previousMilestoneEta =
                    addMilestoneForStops(prevStop, curStop, currentMode, shipment, e2eShipment, previousMilestoneEta);

        }
        if (railLocationCount > 0) {
            shipment.setLegMode(ShipmentMode.INTERMODAL.name());
            shipmentRepository.save(shipment);
        }
    }

    private Date getParentShipmentEta(Shipment shipment) {
        Shipment parentShipment = shipmentRepository.getParentShipmentByShipmentId(shipment.getShipmentId());
        if (parentShipment == null) {
            return shipment.getSchedulePickupDate() != null ? shipment.getSchedulePickupDate()
                    : shipment.getWorkOrderCreatedDate();
        }
        List<ShipmentMilestone> smls = shipmentMilestonesRepository
                .findByShipmentIdOrderByShipmentMilestoneIdDesc(parentShipment.getShipmentId());

        Date previousMilestoneEta = parentShipment.getExpectedDeliveryDate();
        if (!smls.isEmpty() && smls.get(0).getEstimatedDate() != null) {
            previousMilestoneEta = smls.get(0).getEstimatedDate();
        }
        return previousMilestoneEta;
    }

    private Date addMilestoneForStops(Stop prevStop, Stop curStop, ShipmentMode currentMode, Shipment shipment,
            E2EShipment e2eShipment, Date previousMilestoneEta) {
        logger.info("Entering ShipmentMilestoneService.addMilestoneForStops");
        List<Milestone> milestones = milestonesRepository
                .getMilestoneByModeAndOrganization(currentMode.name().toLowerCase(), shipment.getOrganizationCode());
        for (Milestone milestone : milestones) {
            ShipmentMilestone sml = new ShipmentMilestone();
            sml.setMilestone(milestone);
            sml.setShipmentId(shipment.getShipmentId());
            sml.setE2eShipmentId(shipment.getE2eShipmentId());
            sml.setModeOfTransport(currentMode.name());
            sml.setMilestoneName(milestone.getMilestoneName());

            if (milestone.getTiedToLocation().equals(MilestoneTiedLocation.Origin.name())) {
                if (shipment.getOriginLocationId() == null) {
                    sml.setLocationId(shipmentUtil.getLocationIdAsStringSeparatedByPipe(prevStop.getAddress1(),
                            prevStop.getAddress2(), prevStop.getCity(), prevStop.getState(), prevStop.getPostalCode(),
                            prevStop.getCountry()));
                } else {
                    sml.setLocationId(shipment.getOriginLocationId());
                }

                Date apptDate = null;
                if (shipment.getReferenceId() != null) {
                    apptDate = woStopRepository.getStopAppointmentDate(shipment.getReferenceId(),
                            prevStop.getStopNumber());
                }
                apptDate = apptDate != null ? apptDate : prevStop.getScheduledGateInStart();

                Date estimatedDate = apptDate != null ? apptDate : previousMilestoneEta;

                estimatedDate = getLeadTimeAdjustedEta(sml, estimatedDate);
                sml.setEstimatedDate(estimatedDate);

                sml.setPlannedDate(estimatedDate);

                // Parshwa Map new Fields
                sml.setAddress1(shipment.getOriginAddress1());
                sml.setAddress2(shipment.getOriginAddress2());
                sml.setCity(shipment.getOriginCity());
                sml.setState(shipment.getOriginState());
                sml.setZipcode(shipment.getOriginZipcode());
                sml.setCountry(shipment.getOriginCountry());
                sml.setUnlocode(shipment.getOriginUnlocode());

                sml.setLocationName(shipment.getOrigin());
                sml.setLocationType(shipmentUtil.getLocationType(prevStop.getName(), prevStop.getAddress1(),
                        prevStop.getAddress2(), prevStop.getCity(), prevStop.getState(), prevStop.getPostalCode(),
                        prevStop.getCountry()));

            } else {
                if (shipment.getDestination_LocationId() == null) {
                    sml.setLocationId(shipmentUtil.getLocationIdAsStringSeparatedByPipe(curStop.getAddress1(),
                            curStop.getAddress2(), curStop.getCity(), curStop.getState(), curStop.getPostalCode(),
                            curStop.getCountry()));
                } else {
                    sml.setLocationId(shipment.getDestinationLocationId());
                }

                Date apptDate = null;
                if (shipment.getReferenceId() != null) {
                    apptDate =
                            woStopRepository.getStopAppointmentDate(shipment.getReferenceId(), curStop.getStopNumber());
                }
                apptDate = apptDate != null ? apptDate : curStop.getScheduledGateInStart();
                setMilestoneLeadTime(prevStop, curStop, milestone, sml);
                // Date estimatedDate = previousMilestoneEta!=null?previousMilestoneEta:apptDate;
                Date estimatedDate = apptDate != null ? apptDate : previousMilestoneEta;

                estimatedDate = getLeadTimeAdjustedEta(sml, estimatedDate);
                sml.setEstimatedDate(estimatedDate);

                sml.setPlannedDate(estimatedDate);

                // Parshwa Map new Fields
                sml.setAddress1(shipment.getDestinationAddress1());
                sml.setAddress2(shipment.getDestinationAddress2());
                sml.setCity(shipment.getDestinationCity());
                sml.setState(shipment.getDestinationState());
                sml.setZipcode(shipment.getDestinationZipcode());
                sml.setCountry(shipment.getDestinationCountry());
                sml.setUnlocode(shipment.getDestinationUnlocode());


                sml.setLocationName(shipment.getDestination());
                sml.setLocationType(
                        shipmentUtil.getLocationType(curStop.getName(), curStop.getAddress1(), curStop.getAddress2(),
                                curStop.getCity(), curStop.getState(), curStop.getPostalCode(), curStop.getCountry()));
            }


            shipmentMilestonesRepository.save(sml);
            previousMilestoneEta = sml.getEstimatedDate();
        }
        return previousMilestoneEta;
    }

    private Date getLeadTimeAdjustedEta(ShipmentMilestone sml, Date eta) {
        Date calculatedEta = eta;
        // adjust eta with transit lead time (currently configured from routing table)
        if (sml.getEstimatedLeadTime() != null && calculatedEta != null) {
            if ("DAY".equals(sml.getUom())) {
                calculatedEta = DateUtil.getAfterDates(calculatedEta, sml.getEstimatedLeadTime().intValue());
            } else {
                calculatedEta = DateUtil.getDateAfterHours(calculatedEta, sml.getEstimatedLeadTime().intValue());
            }
        }

        // use leadtime from milestone table for non-transit milestones
        if (sml.getMilestone() != null && sml.getMilestone().getLeadTime() != null) {
            if ("DAY".equals(sml.getUom())) {
                calculatedEta = DateUtil.getAfterDates(calculatedEta, sml.getMilestone().getLeadTime().intValue());
            } else {
                calculatedEta = DateUtil.getDateAfterHours(calculatedEta, sml.getMilestone().getLeadTime().intValue());
            }
        }

        return calculatedEta;
    }

    private void setMilestoneLeadTime(Stop prevStop, Stop curStop, Milestone milestone, ShipmentMilestone sml) {
        if (milestone.isWithLeadTime()) {
            String originAdd = ShipmentUtil.getLocationRoute(prevStop.getAddress1(), prevStop.getAddress2(),
                    prevStop.getCity(), prevStop.getState(), prevStop.getPostalCode(), prevStop.getCountry());
            String destAdd = ShipmentUtil.getLocationRoute(curStop.getAddress1(), curStop.getAddress2(),
                    curStop.getCity(), curStop.getState(), curStop.getPostalCode(), curStop.getCountry());
            String transMode = sml.getModeOfTransport();

            List<Routing> route = new ArrayList<>();
            route = routingRepository.getRoutingRecord(transMode.toLowerCase().trim(),
                    originAdd.toLowerCase().trim().replaceAll(" ", ""),
                    destAdd.toLowerCase().trim().replaceAll(" ", ""));

            if (route.size() > 0) {
                Double transittime = route.get(0).getTransitTime();
                sml.setEstimatedLeadTime(transittime);
                String uom = route.get(0).getUom();
                sml.setUom(uom);
            }
        } else {
            sml.setEstimatedLeadTime(0D);
        }
    }

    @Override
    public ShipmentMilestonesResponse getShipmentMilestonesByRef(String refType, String refValue) {
        List<E2EShipment> e2eShipments =
                e2eShipmentRepository.findE2EShipmentByRefTypeValue(refType.toUpperCase(), refValue);
        if (e2eShipments == null || e2eShipments.isEmpty())
            throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTNOTFOUND.toString(),
                    "No shipment found");

        try {
            E2EShipment e2eShipment = e2eShipments.get(0);
            ShipmentMilestonesResponse result = new ShipmentMilestonesResponse();
            result.setStatus(e2eShipment.getStatus());
            result.setE2eshipmentId(e2eShipment.getShipmentId());

            List<ShipmentMilestone> shipmentMilestones = shipmentMilestonesRepository
                    .findByE2eShipmentIdOrderByShipmentMilestoneId(e2eShipment.getShipmentId());
            List<ShipmentMilestoneForRuleEngine> smls =
                    shipmentMilestoneMapper.mapAsList(shipmentMilestones, ShipmentMilestoneForRuleEngine.class);
            result.setShipmentMilestoneLocations(smls);
            return result;
        } catch (Exception ex) {
            logger.error("Exception inside ShipmentMilestoneService.updateShipmentMilestoneAndStatusByRef.Catch_Block:"
                    + ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public void processUpdateAfterDeleteEvent(ShipmentEvent shipmentEvent) {
        // reset actual date for shipment milestone for the deleted event and delete shipmentexception for the shipment
        // milestone if any
        Shipment shipment = findShipmentFromEvent(shipmentEvent);
        if (shipment == null)
            return;
        List<ShipmentMilestone> shipmentMilestones =
                shipmentMilestonesRepository.findByShipmentIdOrderByEstimatedDate(shipment.getShipmentId());
        for (ShipmentMilestone sm : shipmentMilestones) {
            if (sm.getMilestone().getEventCode().contentEquals(shipmentEvent.getEventCode())) {
                sm.setActualDate(null);
                shipmentMilestonesRepository.save(sm);
                // delete shipment exception
                List<ShipmentException> exceptions =
                        shipmentExceptionRepository.findByShipmentMilestoneId(sm.getShipmentMilestoneId());
                for (ShipmentException se : exceptions) {
                    shipmentExceptionRepository.delete(se);
                }
                break;
            }
        }

        // and re-evaluate shipment milestones dates
        processShipmentEvent(shipmentEvent);
    }

    @Override
    public List<ShipmentMilestone> reCreateShipmentMilestones(Shipment shipment) {

        List<ShipmentMilestone> shipmentMilestones =
                shipmentMilestonesRepository.findByShipmentIdOrderByEstimatedDate(shipment.getShipmentId());
        shipmentMilestonesRepository.deleteAll(shipmentMilestones);

        return createShipmentMilestones(shipment);
    }


}

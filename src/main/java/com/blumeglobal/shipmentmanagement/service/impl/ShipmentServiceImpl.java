package com.blumeglobal.shipmentmanagement.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blumeglobal.shipmentmanagement.dao.repositories.E2EShipmentRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.EquipmentWORepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.RoutingRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentRelationshipRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentWorkOrderRepository;
import com.blumeglobal.shipmentmanagement.enums.ExceptionCode;
import com.blumeglobal.shipmentmanagement.enums.ShipmentMode;
import com.blumeglobal.shipmentmanagement.enums.ShipmentReferenceIdType;
import com.blumeglobal.shipmentmanagement.enums.ShipmentReferenceType;
import com.blumeglobal.shipmentmanagement.enums.WorkorderStatus;
import com.blumeglobal.shipmentmanagement.exceptions.InValidDataException;
import com.blumeglobal.shipmentmanagement.model.E2EShipment;
import com.blumeglobal.shipmentmanagement.model.Shipment;
import com.blumeglobal.shipmentmanagement.model.ShipmentReferences;
import com.blumeglobal.shipmentmanagement.model.ShipmentRelationship;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentModelMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentReferenceMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentRequestMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.WorkorderOceanLegMapper;
import com.blumeglobal.shipmentmanagement.request.ShipmentReferenceRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentWorkOrderRequest;
import com.blumeglobal.shipmentmanagement.response.LocationAPIResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentResponse;
import com.blumeglobal.shipmentmanagement.service.IShipmentMilestoneService;
import com.blumeglobal.shipmentmanagement.service.IShipmentService;
import com.blumeglobal.shipmentmanagement.service.IStopService;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;
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
public class ShipmentServiceImpl implements IShipmentService {
    private static final Logger logger = LogManager.getLogger(ShipmentServiceImpl.class);

    @Autowired
    EquipmentWORepository equipmentWORepository;

    @Autowired
    ShipmentRepository shipmentRepository;

    @Autowired
    ShipmentModelMapper shipmentModelMapper;

    @Autowired
    E2EShipmentRepository e2eShipmentRepository;

    @Autowired
    ShipmentReferenceMapper shipmentReferenceMapper;

    @Autowired
    ShipmentRelationshipRepository shipmentRelationshipRepository;

    @Autowired
    ShipmentWorkOrderRepository shipmentWorkOrderRepository;

    @Autowired
    ShipmentRequestMapper shipmentWorkOrderMapper;

    @Autowired
    WorkorderOceanLegMapper workorderOceanLegMapper;

    @Autowired
    IShipmentMilestoneService shipmentMilestoneService;

    @Autowired
    RoutingRepository routingRepository;

    @Autowired
    ShipmentUtil newshipmentUtil;

    @Autowired
    private IStopService stopService;


    public static final String SOURCE_SYSTEM = "System";

    /**
     * This ShipmentServiceImpl.createShipmentWO method is used to create shipment using ShipmentWorkOrderRequest
     * request
     * 
     * @param createWorkOrderRequest This is used to pass ShipmentWorkOrderRequest request data
     * @return ShipmentResponse upload To get response of ShipmentResponse
     * @throws Exception Handle the exceptions
     */
    @Override
    public ShipmentResponse createShipmentWO(ShipmentWorkOrderRequest createWorkOrderRequest) throws Exception {
        try {
            logger.info("Came to createShipmentWO service impl, Arrived at: " + new Date());
            String freightDescription;
            logger.info("Entering into ShipmentServiceImpl.createShipmentWO");
            if (createWorkOrderRequest.getEta() == null)
                throw new InValidDataException("ETA date is required");

            ShipmentRequest createRequest = shipmentWorkOrderMapper.map(createWorkOrderRequest, ShipmentRequest.class);
            // set Dray as Default mode
            if (!ShipmentMode.isValid(createRequest.getLegMode()))
                createRequest.setLegMode(ShipmentMode.DRAY.name());
            createRequest.setReferenceType(ShipmentReferenceIdType.WORKORDER.name());

            // ShipmentReference
            createRequest.setShipmentReferences(shipmentReferenceWOEntry(createWorkOrderRequest));

            freightDescription = equipmentWORepository.selectShipmentFrieghtDescription(createRequest.getReferenceId());
            if (logger.isDebugEnabled()) {
                logger.debug("The value for freight:", freightDescription);
            }

            createRequest.setFreightDescription(freightDescription);
            // if request referenceIdType is WORKORDER and refrerenceId exists in Shipment table
            // call update shipment method.
            Shipment existingShipment = null;
            if (ShipmentReferenceIdType.WORKORDER.name().equals(createRequest.getReferenceType())
                    && createRequest.getReferenceId() != null) {
                existingShipment = shipmentRepository.getShipmentByReferenceIdType(createRequest.getReferenceId(),
                        createRequest.getReferenceType());
            }
            if (existingShipment != null) {
                return updateShipment(createRequest, existingShipment.getShipmentId());
            } else {
                ShipmentResponse shipmentResponseObj = createShipment(createRequest);
                // Disable internally generated ocean shipment
                // ShipmentResponse oceanShipmentResponseObj=generateOceanLegShipment(createWorkOrderRequest);
                // Default system generated ocean leg is child of DRAY shipment from workorder. This is correct for
                // export.
                // For import, DRAY is the child of ocean shipment
                // if(createWorkOrderRequest.getCategory()!=null&&createWorkOrderRequest.getCategory().startsWith("Import")
                // &&oceanShipmentResponseObj!=null) {
                // setOceanImportShipmentRelation(shipmentResponseObj, oceanShipmentResponseObj);
                // }
                return shipmentResponseObj;
            }

        } catch (Exception ex) {
            logger.error("Exception inside ShipmentServiceImpl.createShipmentWO.Catch_Block:" + ex.getMessage(), ex);
            ex.printStackTrace();
            throw ex;
        }
    }

    @SuppressWarnings("unused")
    private void setOceanImportShipmentRelation(ShipmentResponse shipmentResponseObj,
            ShipmentResponse oceanShipmentResponseObj) {
        Iterable<ShipmentRelationship> oceanItr =
                shipmentRelationshipRepository.findByShipmentId(Long.valueOf(oceanShipmentResponseObj.getId()));
        oceanItr.forEach(shipmentrelation -> {
            shipmentrelation.setParentId(null);
            shipmentRelationshipRepository.save(shipmentrelation);
        });

        Iterable<ShipmentRelationship> drayItr =
                shipmentRelationshipRepository.findByShipmentId(Long.valueOf(shipmentResponseObj.getId()));
        drayItr.forEach(shipmentrelation -> {
            shipmentrelation.setParentId(Long.valueOf(oceanShipmentResponseObj.getId()));
            shipmentRelationshipRepository.save(shipmentrelation);
        });

    }

    /**
     * This ShipmentServiceImpl.shipmentReferenceWOEntry method is used for ShipmentReference create API from
     * ShipmentWorkOrderRequest Request
     * 
     * @param createWorkOrderRequest This is used to pass ShipmentWorkOrderRequest request data
     * @return Set of ShipmentReferenceRequest data To get response of ShipmentResponse
     */
    public Set<ShipmentReferenceRequest> shipmentReferenceWOEntry(ShipmentWorkOrderRequest createWorkOrderRequest) {
        logger.info("Entering into ShipmentServiceImpl.shipmentReferenceWOEntry");

        Set<ShipmentReferenceRequest> shipmentReferenceRequestSet = new TreeSet<>();

        if (createWorkOrderRequest.getShipmentReferenceNumber() != null) {
            ShipmentReferenceRequest shipmentReferenceRequestObj = new ShipmentReferenceRequest();
            shipmentReferenceRequestObj.setShipmentRefType(ShipmentReferenceType.SHIPMENTREFNUM.toString());
            shipmentReferenceRequestObj.setShipmentRefValue(createWorkOrderRequest.getShipmentReferenceNumber());
            shipmentReferenceRequestSet.add(shipmentReferenceRequestObj);
        }

        if (createWorkOrderRequest.getWorkordernumber() != null) {
            ShipmentReferenceRequest shipmentReferenceRequestObj = new ShipmentReferenceRequest();
            shipmentReferenceRequestObj.setShipmentRefType(ShipmentReferenceType.WORKORDERNUM.toString());
            shipmentReferenceRequestObj.setShipmentRefValue(createWorkOrderRequest.getWorkordernumber());
            shipmentReferenceRequestSet.add(shipmentReferenceRequestObj);
        }

        if (createWorkOrderRequest.getHouseAirWayBillNumber() != null) {
            ShipmentReferenceRequest shipmentReferenceRequestObj = new ShipmentReferenceRequest();
            shipmentReferenceRequestObj.setShipmentRefType(ShipmentReferenceType.HOUSEAIRWAYBILLNUM.toString());
            shipmentReferenceRequestObj.setShipmentRefValue(createWorkOrderRequest.getHouseAirWayBillNumber());
            shipmentReferenceRequestSet.add(shipmentReferenceRequestObj);
        }

        if (createWorkOrderRequest.getBillofladingnumber() != null) {
            ShipmentReferenceRequest shipmentReferenceRequestObj = new ShipmentReferenceRequest();
            shipmentReferenceRequestObj.setShipmentRefType(ShipmentReferenceType.BILLOFLADING.toString());
            shipmentReferenceRequestObj.setShipmentRefValue(createWorkOrderRequest.getBillofladingnumber());
            shipmentReferenceRequestSet.add(shipmentReferenceRequestObj);
        }

        if (createWorkOrderRequest.getRailbillingnumber() != null) {
            ShipmentReferenceRequest shipmentReferenceRequestObj = new ShipmentReferenceRequest();
            shipmentReferenceRequestObj.setShipmentRefType(ShipmentReferenceType.RAILBILLINGNUMBER.toString());
            shipmentReferenceRequestObj.setShipmentRefValue(createWorkOrderRequest.getRailbillingnumber());
            shipmentReferenceRequestSet.add(shipmentReferenceRequestObj);
        }

        if (createWorkOrderRequest.getMasterAirWayBillNumber() != null) {
            ShipmentReferenceRequest shipmentReferenceRequestObj = new ShipmentReferenceRequest();
            shipmentReferenceRequestObj.setShipmentRefType(ShipmentReferenceType.MASTERAIRWAYBILLNUMBER.toString());
            shipmentReferenceRequestObj.setShipmentRefValue(createWorkOrderRequest.getMasterAirWayBillNumber());
            shipmentReferenceRequestSet.add(shipmentReferenceRequestObj);
        }

        if (createWorkOrderRequest.getBookingnumber() != null) {
            ShipmentReferenceRequest shipmentReferenceRequestObj = new ShipmentReferenceRequest();
            shipmentReferenceRequestObj.setShipmentRefType(ShipmentReferenceType.BOOKINGNUM.toString());
            shipmentReferenceRequestObj.setShipmentRefValue(createWorkOrderRequest.getBookingnumber());
            shipmentReferenceRequestSet.add(shipmentReferenceRequestObj);
        }

        logger.info("Ending into ShipmentServiceImpl.shipmentReferenceWOEntry");

        return shipmentReferenceRequestSet;
    }

    /**
     * This ShipmentServiceImpl.createShipment method is used to create shipment using ShipmentRequest request
     * 
     * @param createRequest This is used to pass ShipmentRequest request data
     * @return ShipmentResponse upload To get response of ShipmentResponse
     * @throws Exception Handle the exceptions
     */
    @Override
    public ShipmentResponse createShipment(ShipmentRequest createRequest) throws Exception {
        try {
            logger.info("Entering into ShipmentServiceImpl.createShipment");

            if (createRequest == null) {
                if (logger.isDebugEnabled()) {
                    logger.debug(
                            "ShipmentServiceImpl.createShipment - Throwing Exception as Invalid Event Code Passed");
                }

                throw newshipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(),
                        ShipmentConstants.EMPTY_SHIPMENT_REQUEST_CREATE_MESSAGE);
            }

            String status = ShipmentConstants.DONE_MESSAGE;
            String message = ShipmentConstants.COMPLETE_MESSAGE;

            Shipment upload = createShipmentFromRequestData(createRequest);

            // save E2EShipment
            E2EShipment e2eShipment = getLinkedE2EShipment(createRequest);
            if (e2eShipment != null) {
                // update e2e_shipment origin/destination
                updateE2EShipmentOriginDestination(e2eShipment, upload);
                upload.setE2eShipmentId(e2eShipment.getShipmentId());
                shipmentRepository.save(upload);
            } else {
                // add new E2E_Shipment
                addNewE2EShipment(createRequest, upload);
            }

            // search shipment with same e2e_shipmentid
            List<Shipment> linkedShipments = shipmentRepository.getShipmentByE2EShipmentId(upload.getE2eShipmentId());

            addShipmentRelationShip(linkedShipments, upload);

            checkLookupValues(linkedShipments);
            if (logger.isDebugEnabled()) {
                logger.debug("Entering into ShipmentServiceImpl.createShipment - Data Inserted Successfully");
            }

            shipmentMilestoneService.createShipmentMilestones(upload);

            if (createRequest.getShipmentStopListRequest() != null
                    && createRequest.getShipmentStopListRequest().getStopList() != null
                    && !createRequest.getShipmentStopListRequest().getStopList().isEmpty()) {
                stopService.createStop(createRequest.getShipmentStopListRequest(), upload.getShipmentId());
            } else {
                shipmentMilestoneService.addPlannedStopsFromDM(upload);
            }


            logger.debug("Entering into ShipmentServiceImpl.createShipment - Data Inserted Successfully");
            ShipmentResponse shipmentResponse = new ShipmentResponse();
            String id = upload.getShipmentId().toString();
            shipmentResponse.setId(id);
            shipmentResponse.setStatus(status);
            shipmentResponse.setMessage(message);
            /// ShipmentEventResponse createEventsRes = shipmenteventMapper.map(upload,
            /// ShipmentResponse.class);
            if (logger.isDebugEnabled()) {
                logger.debug("Entering into ShipmentServiceImpl.createShipment - Mapping Shipment to CreateResponse");
            }

            return shipmentResponse;

        } catch (Exception ex) {
            logger.error("Exception inside ShipmentServiceImpl.createShipment.Catch_Block:" + ex.getMessage(), ex);
            throw ex;
        }
    }

    private void addNewE2EShipment(ShipmentRequest createRequest, Shipment upload) {
        E2EShipment e2eShipmentReq = shipmentModelMapper.map(createRequest, E2EShipment.class);
        if (newshipmentUtil.parseBooleanValue(createRequest.getIsHazmat()))
            e2eShipmentReq.setIsHazmat("Y");
        else
            e2eShipmentReq.setIsHazmat("N");
        if (newshipmentUtil.parseBooleanValue(createRequest.getIsTempControlled()))
            e2eShipmentReq.setIsTemperatureController("Y");
        else
            e2eShipmentReq.setIsTemperatureController("N");

        // set e2eShipment reference from ShipmentRequest
        String bookingNumber = null;
        for (ShipmentReferenceRequest shipmentRef : createRequest.getShipmentReferences()) {
            if (ShipmentReferenceType.SHIPMENTREFNUM.name().equals(shipmentRef.getShipmentRefType())) {
                e2eShipmentReq.setShipmentNumber(shipmentRef.getShipmentRefValue());
            } else if (ShipmentReferenceType.isBillNumberType(shipmentRef.getShipmentRefType())) {
                e2eShipmentReq.setWayBillNumber(shipmentRef.getShipmentRefValue());
            } else if (ShipmentReferenceType.BOOKINGNUM.name().equals(shipmentRef.getShipmentRefType())) {
                bookingNumber = shipmentRef.getShipmentRefValue();
            }
        }
        if (ShipmentUtil.isNullString(e2eShipmentReq.getShipmentNumber()) && bookingNumber != null) {
            e2eShipmentReq.setShipmentNumber(bookingNumber);
        } else if (ShipmentUtil.isNullString(e2eShipmentReq.getShipmentNumber())
                && e2eShipmentReq.getWayBillNumber() != null) {
            e2eShipmentReq.setShipmentNumber(e2eShipmentReq.getWayBillNumber());
        }
        if (ShipmentUtil.isNullString(e2eShipmentReq.getWayBillNumber())) {
            e2eShipmentReq.setWayBillNumber(e2eShipmentReq.getShipmentNumber());
        }
        if (ShipmentUtil.isNullString(e2eShipmentReq.getStatus())) {
            e2eShipmentReq.setStatus(WorkorderStatus.Unassigned.name());
        }
        e2eShipmentReq.setDeleteFlag("N");
        ///updated to add the create date time always as its not creating issue in UI.
        if(e2eShipmentReq.getCreatedDateTime() == null) {
        	e2eShipmentReq.setCreatedDateTime(new Date());
        }
        E2EShipment e2eShipmentUpload = e2eShipmentRepository.save(e2eShipmentReq);
        upload.setE2eShipmentId(e2eShipmentUpload.getShipmentId());
        shipmentRepository.save(upload);

    }

    /**
     * This ShipmentServiceImpl.createShipmentFromRequestData method is used to create shipmentReference using
     * ShipmentRequest request
     * 
     * @param createRequest This is used to pass ShipmentRequest request data
     * @return ShipmentResponse upload To get response of ShipmentResponse
     */
    private Shipment createShipmentFromRequestData(ShipmentRequest createRequest) {
        Shipment createReq = shipmentModelMapper.map(createRequest, Shipment.class);

        createReq.setHazmat(newshipmentUtil.parseBooleanValue(createRequest.getIsHazmat()));
        createReq.setTempControlled(newshipmentUtil.parseBooleanValue(createRequest.getIsTempControlled()));
        if (ShipmentUtil.isNullString(createReq.getStatus()))
            createReq.setStatus(WorkorderStatus.Unassigned.name());
        setShipmentReferences(createRequest, createReq);
        createReq.setLegMode(createReq.getLegMode().toUpperCase());
        
        Shipment shipment = createReq;
        shipment = setLocationIdInShipment(createReq);
        
        return shipmentRepository.save(shipment);

        // return shipmentRepository.save(createReq);
    }

    /**
     * This ShipmentServiceImpl.setLocationIdInShipment method is used to create shipmentReference using ShipmentRequest
     * request
     * 
     * @param createReq This is used to pass Shipment data
     * @return Shipment To get response of Shipment Object
     */
    private Shipment setLocationIdInShipment(Shipment createReq) {
        try {
            logger.info("Entering into ShipmentServiceImpl.setLocationIDInShipment and set all datafields");

            // Origin Address
            List<LocationAPIResponse> originLocationAPIResponses = new ArrayList<>();

            LocationAPIResponse originLocationAPIResponse = new LocationAPIResponse();
            originLocationAPIResponse.setName(createReq.getOrigin());
            originLocationAPIResponse.setAddress1(createReq.getOriginAddress1());
            originLocationAPIResponse.setAddress2(createReq.getOriginAddress2());
            originLocationAPIResponse.setCity(createReq.getOriginCity());
            originLocationAPIResponse.setState(createReq.getOriginState());
            originLocationAPIResponse.setPostalCode(createReq.getOriginZipcode());
            originLocationAPIResponse.setCountry(createReq.getOriginCountry());
            originLocationAPIResponse.setUuid("");
            // originLocationAPIResponse.setLocationType("Distribution Center");
            originLocationAPIResponses.add(originLocationAPIResponse);

            String origin_LocationId = newshipmentUtil.getLocationIdByAddress(originLocationAPIResponses);
            if (origin_LocationId == null || origin_LocationId.trim().length() == 0) {
                logger.error(new StringBuffer("UUID Not Found for Shipment Origin Location - Address 1 :")
                        .append(originLocationAPIResponse.getAddress1()).append(" /n Address 2:")
                        .append(originLocationAPIResponse.getAddress2()).append(" /n City: ")
                        .append(originLocationAPIResponse.getCity()).append(" /n State: ")
                        .append(originLocationAPIResponse.getState()).append(" /n Postal Code: ")
                        .append(originLocationAPIResponse.getPostalCode()).append(" /n Country: ")
                        .append(originLocationAPIResponse.getCountry()));
                
                 origin_LocationId = originLocationAPIResponse.getAddress1()+" | "+
                		originLocationAPIResponse.getAddress2()+" | "+
                		originLocationAPIResponse.getCity()+" | "+
                		originLocationAPIResponse.getState();
                		
                createReq.setOrigin_LocationId(origin_LocationId);
                
            } else {
            	createReq.setOrigin_LocationId(origin_LocationId);
            }
            // Destination Address
            List<LocationAPIResponse> destinationLocationAPIResponses = new ArrayList<>();

            LocationAPIResponse destinationLocationAPIResponse = new LocationAPIResponse();
            destinationLocationAPIResponse.setName(createReq.getDestination());
            destinationLocationAPIResponse.setAddress1(createReq.getDestinationAddress1());
            destinationLocationAPIResponse.setAddress2(createReq.getDestinationAddress2());
            destinationLocationAPIResponse.setCity(createReq.getDestinationCity());
            destinationLocationAPIResponse.setState(createReq.getDestinationState());
            destinationLocationAPIResponse.setPostalCode(createReq.getDestinationZipcode());
            destinationLocationAPIResponse.setCountry(createReq.getDestinationCountry());
            destinationLocationAPIResponse.setUuid("");
            // destinationLocationAPIResponse.setLocationType("Factory");
            destinationLocationAPIResponses.add(destinationLocationAPIResponse);

            String destination_LocationId = newshipmentUtil.getLocationIdByAddress(destinationLocationAPIResponses);
            if (destination_LocationId == null || destination_LocationId.trim().length() == 0) {
                logger.error(new StringBuffer("UUID Not Found for Shipment Destination Location - Address 1 :")
                        .append(destinationLocationAPIResponse.getAddress1()).append(" /n Address 2:")
                        .append(destinationLocationAPIResponse.getAddress2()).append(" /n City: ")
                        .append(destinationLocationAPIResponse.getCity()).append(" /n State: ")
                        .append(destinationLocationAPIResponse.getState()).append(" /n Postal Code: ")
                        .append(destinationLocationAPIResponse.getPostalCode()).append(" /n Country: ")
                        .append(destinationLocationAPIResponse.getCountry()));
                
                destination_LocationId = destinationLocationAPIResponse.getAddress1()+" | "+
                		destinationLocationAPIResponse.getAddress2()+" | "+
                		destinationLocationAPIResponse.getCity()+" | "+
                		destinationLocationAPIResponse.getState(); 
                createReq.setDestination_LocationId(destination_LocationId);
                
            } else {
            	createReq.setDestination_LocationId(destination_LocationId);
            }

            return createReq;
        } catch (Exception e) {
            logger.error("Exception occurred while creating setLocationIDInShipment - exception: ", e);
            return createReq;
//            throw e;
        }
    }

    private void setShipmentReferences(ShipmentRequest createRequest, Shipment createReq) {
        // ShipmentReference
        Set<ShipmentReferenceRequest> shipmentReferenceRequestObj = createRequest.getShipmentReferences();
        Set<ShipmentReferences> setShipmentReferencesObj = new HashSet<>();

        if (shipmentReferenceRequestObj != null) {
            for (ShipmentReferenceRequest shipmentReferenceRequest : shipmentReferenceRequestObj) {
                ShipmentReferences shipmentReferencesReq =
                        shipmentReferenceMapper.map(shipmentReferenceRequest, ShipmentReferences.class);
                shipmentReferencesReq.setShipmentReferencesLink(createReq);
                shipmentReferencesReq.setSource(createReq.getSource());
                setShipmentReferencesObj.add(shipmentReferencesReq);
            }
            createReq.setShipmentReferences(setShipmentReferencesObj);
        }
    }

    /**
     * This ShipmentServiceImpl.updateShipment method is used to create shipment using ShipmentRequest request
     * 
     * @param createRequest This is used to pass ShipmentRequest request data
     * @return ShipmentResponse upload To get response of ShipmentResponse
     * @throws Exception Handle the exceptions
     */
    @Override
    public ShipmentResponse updateShipment(ShipmentRequest createRequest, Long shipmentId) throws Exception {
        try {
            logger.info("Entering into ShipmentServiceImpl.updateShipment");

            if (createRequest == null) {
                if (logger.isDebugEnabled()) {
                    logger.debug(
                            "ShipmentServiceImpl.updateShipment - Throwing Exception as Invalid Event Code Passed");
                }

                throw newshipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(),
                        ShipmentConstants.EMPTY_SHIPMENT_REQUEST_UPDATE_MESSAGE);
            }

            String status = ShipmentConstants.DONE_MESSAGE;
            String message = ShipmentConstants.COMPLETE_MESSAGE;
            Shipment createReq = shipmentModelMapper.map(createRequest, Shipment.class);

            Optional<Shipment> shipmentObj = shipmentRepository.findById(shipmentId);
            Shipment upload = null;

            if (shipmentObj.isPresent()) {
                createReq.setShipmentId(shipmentId);
                createReq.setHazmat(newshipmentUtil.parseBooleanValue(createRequest.getIsHazmat()));
                createReq.setTempControlled(newshipmentUtil.parseBooleanValue(createRequest.getIsTempControlled()));

                // TODO do not allow update references or need add more rule on updating E2E shipment references
                // and no update for shipment relationship
                createReq.setShipmentReferences(shipmentObj.get().getShipmentReferences());
                createReq.setLegMode(createReq.getLegMode().toUpperCase());
                upload = shipmentRepository.save(createReq);

                E2EShipment e2eShipment = getLinkedE2EShipment(createRequest);
                if (e2eShipment != null) {
                    // update e2e_shipment origin/destination
                    updateE2EShipmentOriginDestination(e2eShipment, upload);
                    upload.setE2eShipmentId(e2eShipment.getShipmentId());
                    shipmentRepository.save(upload);
                }

            } else {
                return createShipment(createRequest);
            }
            if (logger.isDebugEnabled()) {
                logger.debug("Entering into ShipmentServiceImpl.updateShipment - Data Updated/Inserted Successfully");
            }

            ShipmentResponse shipmentResponse = new ShipmentResponse();
            String id = upload.getShipmentId().toString();
            shipmentResponse.setId(id);
            shipmentResponse.setStatus(status);
            shipmentResponse.setMessage(message);
            if (logger.isDebugEnabled()) {
                logger.debug("Entering into ShipmentServiceImpl.updateShipment - Mapping Shipment to CreateResponse");
            }

            return shipmentResponse;
        } catch (Exception ex) {
            logger.error("Exception inside ShipmentServiceImpl.updateShipment.Catch_Block:", ex);
            throw ex;
        }
    }

    /**
     * This ShipmentServiceImpl.addShipmentRelationShip method is used to create shipmentRelationship using Shipment
     * request
     * 
     * @param shipment This is used to pass Shipment request data Handle the exceptions
     */
    private void addShipmentRelationShip(List<Shipment> linkedShipments, Shipment shipment) {
        if (logger.isDebugEnabled()) {
            logger.debug(" entering addShipmentRelationShip", shipment.getShipmentId());
        }

        try {

            // TODO the following is simply linear relation. Need more rules on multiple
            // parent/children relation
            Shipment prevShipment = null;
            Shipment nextShipment = null;
            Integer index = linkedShipments.indexOf(shipment);
            if (index > 0)
                prevShipment = linkedShipments.get(index - 1);
            if (index < (linkedShipments.size() - 1))
                nextShipment = linkedShipments.get(index + 1);

            // TODO use origin and destination locationId check shipment relationship

            // if there is a closest previous shipment, set it as parent id otherwise parent
            // id is null
            ShipmentRelationship relationShip = new ShipmentRelationship();
            relationShip.setCreatedBy(shipment.getCreatedBy());
            relationShip.setCreateDate(new Date());
            relationShip.setLastModifiedBy(shipment.getCreatedBy());
            relationShip.setLastModified(new Date());
            relationShip.setShipmentId(shipment.getShipmentId());
            if (prevShipment != null) {
                relationShip.setParentId(prevShipment.getShipmentId());
            } else {
                relationShip.setParentId(null);
            }
            shipmentRelationshipRepository.save(relationShip);

            // if there is a closest next shipment, update its parent_id to current shipment
            if (nextShipment != null) {
                Iterable<ShipmentRelationship> srItr =
                        shipmentRelationshipRepository.findByShipmentId(nextShipment.getShipmentId());
                srItr.forEach(shipmentrelation -> {
                    shipmentrelation.setParentId(shipment.getShipmentId());
                    shipmentRelationshipRepository.save(shipmentrelation);
                });
            }

        } catch (Exception ex) {
            logger.error("Exception inside ShipmentServiceImpl.addShipmentRelationShip.Catch_Block:", ex);
            throw ex;
        }
    }

    // check and set any lookup values that are missing from workorder request
    // object
    private void checkLookupValues(List<Shipment> shipments) {
        for (Shipment shipment : shipments) {
            if (ShipmentUtil.isNullString(shipment.getFreightDescription())) {
                String freightDescription =
                        equipmentWORepository.selectShipmentFrieghtDescription(shipment.getReferenceId());
                logger.debug("The value for freight: =" + freightDescription);
                if (freightDescription != null) {
                    shipment.setFreightDescription(freightDescription);
                    shipmentRepository.save(shipment);
                }
            }
            // check shipment status from workorder status
            String status = shipmentRepository.getWorkorderStatusById(shipment.getReferenceId());
            if (status != null) {
                shipment.setStatus(status);
                shipmentRepository.save(shipment);
            }
        }
    }

    /**
     * create system generated ocean shipment
     * 
     * @param createWorkOrderRequest
     */
    @SuppressWarnings("unused")
    private ShipmentResponse generateOceanLegShipment(ShipmentWorkOrderRequest createWorkOrderRequest) {
        logger.info("Entering into ShipmentServiceImpl.generateOceanLegShipment");

        try {
            if (!hasOceanLegData(createWorkOrderRequest))
                return null;

            ShipmentRequest createRequest = workorderOceanLegMapper.map(createWorkOrderRequest, ShipmentRequest.class);
            createRequest.setSource(SOURCE_SYSTEM);
            createRequest.setReferenceType(null);
            createRequest.setLegMode(ShipmentMode.OCEAN.name());

            createRequest.setShipmentReferences(shipmentReferenceWOEntry(createWorkOrderRequest));

            return createShipment(createRequest);
        } catch (Exception e) {
            logger.error("Entering into ShipmentServiceImpl.generateOceanLegShipment", e);
        }
        return null;
    }

    /**
     * Check if workorder request data has Ocean shipment data
     * 
     * @param createWorkOrderRequest
     * @return
     */
    private boolean hasOceanLegData(ShipmentWorkOrderRequest createWorkOrderRequest) {
        if (!ShipmentUtil.isNullString(createWorkOrderRequest.getPortofdischarge())
                && !ShipmentUtil.isNullString(createWorkOrderRequest.getPortofloading())
                && !ShipmentUtil.isNullString(createWorkOrderRequest.getVessel())) {
            return true;
        } else {
            return false;
        }
    }

    private E2EShipment getLinkedE2EShipment(ShipmentRequest createRequest) {
        if (logger.isDebugEnabled()) {
            logger.debug(" --- search e2e shipment data createRequest.getShipmentNumber() - "
                    + createRequest.getShipmentNumber() + " createRequest.getBolNumbers() - "
                    + createRequest.getBolNumbers());
        }

        E2EShipment e2eShipment = e2eShipmentRepository.searchE2EShipmentByShipmentRefs(
                createRequest.getShipmentNumber(), createRequest.getBolNumbers(), createRequest.getOrganizationCode());
        return e2eShipment;
    }

    private void updateE2EShipmentOriginDestination(E2EShipment e2eShipment, Shipment newShipment) {
        // if shipment is before E2E_Shipment, update origin
        if (ShipmentUtil.getShipmentCompareDate(newShipment)
                .before(ShipmentUtil.getShipmentCompareDate(e2eShipment))) {
            e2eShipment.setOrigin(newShipment.getOrigin());
            e2eShipment.setOriginAddress1(newShipment.getOriginAddress1());
            e2eShipment.setOriginAddress2(newShipment.getOriginAddress2());
            e2eShipment.setOriginCity(newShipment.getOriginCity());
            e2eShipment.setOriginCountry(newShipment.getOriginCountry());
            e2eShipment.setOriginState(newShipment.getOriginState());
            e2eShipment.setOriginZipCode(newShipment.getOriginZipcode());
            e2eShipment.setExpectdPickupDate(newShipment.getSchedulePickupDate());
        } else {
            // if shipment if after E2E_Shipment, update destination
            e2eShipment.setDestination(newShipment.getDestination());
            e2eShipment.setDestinationAddress1(newShipment.getDestinationAddress1());
            e2eShipment.setDestinationAddress2(newShipment.getDestinationAddress2());
            e2eShipment.setDestinationCity(newShipment.getDestinationCity());
            e2eShipment.setDestinationCountry(newShipment.getDestinationCountry());
            e2eShipment.setDestinationState(newShipment.getDestinationState());
            e2eShipment.setDestinationZipCode(newShipment.getDestinationZipcode());
            e2eShipment.setExpectedDeliveryDate(newShipment.getExpectedDeliveryDate());
        }
        e2eShipmentRepository.save(e2eShipment);
    }

}

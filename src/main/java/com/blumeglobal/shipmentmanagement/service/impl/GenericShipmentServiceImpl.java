package com.blumeglobal.shipmentmanagement.service.impl;

import java.math.BigDecimal;
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

import com.blumeglobal.shipmentmanagement.dao.repositories.ChargeRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.E2EShipmentRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.EquipmentOnShipmentRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.EquipmentWORepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.RoutingRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentReferenceRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentRelationshipRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentStatusRepository;
import com.blumeglobal.shipmentmanagement.dao.repositories.ShipmentWorkOrderRepository;
import com.blumeglobal.shipmentmanagement.enums.ExceptionCode;
import com.blumeglobal.shipmentmanagement.enums.LocationType;
import com.blumeglobal.shipmentmanagement.enums.ShipmentMode;
import com.blumeglobal.shipmentmanagement.enums.ShipmentReferenceIdType;
import com.blumeglobal.shipmentmanagement.enums.ShipmentReferenceType;
import com.blumeglobal.shipmentmanagement.enums.WorkorderStatus;
import com.blumeglobal.shipmentmanagement.exceptions.InValidDataException;
import com.blumeglobal.shipmentmanagement.external.hub.adapter.IOrganizationAdapter;
import com.blumeglobal.shipmentmanagement.model.Charge;
import com.blumeglobal.shipmentmanagement.model.E2EShipment;
import com.blumeglobal.shipmentmanagement.model.EquipmentOnShipment;
import com.blumeglobal.shipmentmanagement.model.Shipment;
import com.blumeglobal.shipmentmanagement.model.ShipmentReferences;
import com.blumeglobal.shipmentmanagement.model.ShipmentRelationship;
import com.blumeglobal.shipmentmanagement.model.mapper.GenericShipmentRequestMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentModelMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentReferenceMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.ShipmentRequestMapper;
import com.blumeglobal.shipmentmanagement.model.mapper.WorkorderOceanLegMapper;
import com.blumeglobal.shipmentmanagement.request.ShipmentReferenceRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentReferencesRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentStatusRequest;
import com.blumeglobal.shipmentmanagement.request.ShipmentWorkOrderRequest;
import com.blumeglobal.shipmentmanagement.request.common.ChargeDetailRequest;
import com.blumeglobal.shipmentmanagement.request.common.GenericShipmentRequest;
import com.blumeglobal.shipmentmanagement.response.LocationAPIResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentResponse;
import com.blumeglobal.shipmentmanagement.service.IGenericShipmentService;
import com.blumeglobal.shipmentmanagement.service.IShipmentMilestoneService;
import com.blumeglobal.shipmentmanagement.service.IStopService;
import com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants;
import com.blumeglobal.shipmentmanagement.utils.BlumeVisibilityUtil;
import com.blumeglobal.shipmentmanagement.utils.ShipmentUtil;
import com.blumeglobal.shipmentmanagement.validator.ShipmentRequestValidator;
import com.rez1.common.annotations.REZ1Logger;
import com.rez1.common.annotations.REZ1PerformanceLogger;

/**
 * The GenericShipmentSerhviceImpl implements to manage the shipment.
 *
 */
@Service
@REZ1Logger
@REZ1PerformanceLogger
public class GenericShipmentServiceImpl implements IGenericShipmentService {
    private static final Logger logger = LogManager.getLogger(GenericShipmentServiceImpl.class);

    @Autowired
    GenericShipmentRequestMapper genericShipmentRequestMapper;

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
    ShipmentUtil shipmentUtil;

    @Autowired
    private IStopService stopService;

    @Autowired
    private EquipmentOnShipmentRepository equipmentRepository;

    @Autowired
    private ChargeRepository chargeRepository;
    
    @Autowired
    private IOrganizationAdapter organizationAdapter;
    
    @Autowired
    ShipmentReferenceRepository shipmentReferenceRepository;
    
    @Autowired
    ShipmentStatusRepository shipmentStatusRepository;
    

    @Autowired
    ShipmentRequestValidator shipmentRequestValidator;
    
    @Autowired
    BlumeVisibilityUtil blumeVisibilityUtil;
    
    public static final String SOURCE_SYSTEM = "System";

    /**
     * This GenericShipmentServiceImpl.createShipment method is used to create shipment using GenericShipmentRequest
     * request
     * 
     * @param createShipmentRequest This is used to pass GenericShipmentRequest request data
     * @return ShipmentResponse upload To get response of ShipmentResponse
     * @throws Exception Handle the exceptions
     */
    @Override
    public ShipmentResponse createShipment(GenericShipmentRequest createShipmentRequest) throws Exception {
        try {
            logger.info("Came to createShipment service impl, Arrived at: " + new Date());
            logger.info("Entering into GenericShipmentServiceImpl.createShipment");

            List<String> errorMsgList = new ArrayList<>();
            StringBuilder errorMsg = new StringBuilder("");
            boolean validflag = shipmentRequestValidator.validateCreateShipmentRequest(createShipmentRequest, errorMsgList, errorMsg);
            
            if (!validflag) {
                logger.error(
                        "GenericShipmentServiceImpl.createShipment for API - GenericShipmentRequest has validation failure");
                throw blumeVisibilityUtil.throwInValidDataException(ShipmentConstants.INPUT_VALIDATION_ERROR, errorMsgList.toString());
            }

            ShipmentRequest createRequest =
                    genericShipmentRequestMapper.map(createShipmentRequest, ShipmentRequest.class);
            // set Dray as Default mode
            if (!ShipmentMode.isValid(createRequest.getLegMode()))
                createRequest.setLegMode(ShipmentMode.DRAY.name());
            // createRequest.setReferenceType(ShipmentReferenceIdType.WORKORDER.name());

            // ShipmentReference
            createRequest.setShipmentReferences(extractShipmentReferences(createShipmentRequest));
            if(createRequest.getShipmentReferences().isEmpty()) {
                throw new InValidDataException("At least one shipment reference value is required");
            }

            //set workorderid and workordertype as referenceid/referenceType for shipment created from workorder
            setWorkorderId(createRequest);

            
           // Not working locally.Make sure to uncomment this. 
            Long organizationId = 19875l;
              //organizationAdapter.getOrganizationIdByCode(createRequest.getOriginatorCode());
              
              
            if(organizationId!=null) createRequest.setOrganizationCode(organizationId+"");

            // if request referenceIdType is WORKORDER and refrerenceId exists in Shipment table
            // call update shipment method.
            Shipment existingShipment = checkExistingShipment(createRequest);
            
            if (existingShipment != null) {
            	
            	// Set Shipment StatusId field
            	existingShipment = setShipmentStatusId(existingShipment);
        		
            	setKeptAttributes(createRequest, existingShipment);
                ShipmentResponse shipmentResponse = updateShipment(createRequest, existingShipment);
                if (shipmentResponse.getId() != null) {
					
					  List<EquipmentOnShipment> equipments =
					  equipmentRepository.findByShipmentLegId(existingShipment.getShipmentId()); if
					  (!equipments.isEmpty()) { updateEquipmentOnShipment(createShipmentRequest,
					  equipments); } else { addEquipmentOnShipment(createShipmentRequest,
					  shipmentResponse); }
                    
                    
                    setLocationUuidInShipmentRequest(createShipmentRequest, shipmentResponse);
                    
                    // add workorder number as reference
                    Set<ShipmentReferences> shrefs=existingShipment.getShipmentReferences();
                    String woNumber = "WO" + shipmentResponse.getId();
                    ShipmentReferences ref = new ShipmentReferences();
                    ref.setShipmentRefType(ShipmentReferenceType.WORKORDERNUM.name());
                    ref.setShipmentRefValue(woNumber);
                    ref.setShipmentReferencesLink(existingShipment);
                    
                    if(!shrefs.contains(ref)) {
                        existingShipment.setReferenceType(ShipmentReferenceIdType.WORKORDER.name());
                        existingShipment.getShipmentReferences().add(ref);
                        shipmentRepository.save(existingShipment);
                    }
                    ShipmentReferenceRequest sr = new ShipmentReferenceRequest();
                    sr.setShipmentRefType(ShipmentReferenceType.WORKORDERNUM.name());
                    sr.setShipmentRefValue(woNumber);
                    createShipmentRequest.getShipmentReferences().add(sr);
                }
                // Updating mile stone for update operation
                if(createShipmentRequest.isUpdate()||shipmentResponse.getId() != null) {
                   //shipmentMilestoneService.updateShipmentMilestones(createShipmentRequest,existingShipment);
                    //TODO need refine the above updateShipmentMilestones. Use the following method to update shipment milestone.  
                    shipmentMilestoneService.reCreateShipmentMilestones(existingShipment);
                }
                return shipmentResponse;

            } else {
                ShipmentResponse shipmentResponseObj = createShipment(createRequest);
                // create equipmentOnShipment
                if (shipmentResponseObj.getId() != null) {
                    addEquipmentOnShipment(createShipmentRequest, shipmentResponseObj);
                    setLocationUuidInShipmentRequest(createShipmentRequest, shipmentResponseObj);
                }

                return shipmentResponseObj;
            }

        } catch (Exception ex) {
        	ex.printStackTrace();
            logger.error("Exception inside GenericShipmentServiceImpl.createShipment.Catch_Block:" + ex.getMessage(),
                    ex);
            throw ex;
        }
    }

    private void setKeptAttributes(ShipmentRequest createRequest, Shipment existingShipment) {
        // TODO Add more attributes that are not updated
        if(existingShipment.getReferenceId()!=null) {
            createRequest.setReferenceId(existingShipment.getReferenceId());
        }
        if(existingShipment.getReferenceType()!=null) {
            createRequest.setReferenceType(existingShipment.getReferenceType());
        }
        
    }

    private void setWorkorderId(ShipmentRequest createRequest) {
        Set<ShipmentReferenceRequest> refs = createRequest.getShipmentReferences();
        for(ShipmentReferenceRequest sr:refs) {
            if(ShipmentReferenceType.WORKORDERID.name().equalsIgnoreCase(sr.getShipmentRefType())) {
                createRequest.setReferenceType(ShipmentReferenceIdType.WORKORDER.name());
                createRequest.setReferenceId(Long.valueOf(sr.getShipmentRefValue()));
            }else if(ShipmentReferenceType.WORKORDERNUM.name().equalsIgnoreCase(sr.getShipmentRefType())) {
                createRequest.setReferenceType(ShipmentReferenceIdType.WORKORDER.name());
            }
        }
    }

    private void setLocationUuidInShipmentRequest(GenericShipmentRequest createShipmentRequest,
            ShipmentResponse shipmentResponseObj) {
        Shipment shipment = shipmentRepository.findByShipmentId(Long.valueOf(shipmentResponseObj.getId()));
        createShipmentRequest.getShipFrom().setUuid(shipment.getOrigin_LocationId());
        createShipmentRequest.getShipTo().setUuid(shipment.getDestination_LocationId());
    }

    private void updateEquipmentOnShipment(GenericShipmentRequest createShipmentRequest,
            List<EquipmentOnShipment> equipments) {
        EquipmentOnShipment existingEquipment = equipments.get(0);
        List<Charge> charges =
                chargeRepository.findByEquipmentOnShipmentId(existingEquipment.getEquipmentOnShipmentId());
        chargeRepository.deleteAll(charges);

        EquipmentOnShipment eqonsh = genericShipmentRequestMapper
                .map(createShipmentRequest.getShipmentEquipment(), EquipmentOnShipment.class);
        eqonsh.setEquipmentOnShipmentId(existingEquipment.getEquipmentOnShipmentId());
        eqonsh.setShipmentLegId(existingEquipment.getShipmentLegId());
        eqonsh.setCreatedBy(existingEquipment.getCreatedBy());
        eqonsh.setCreatedDate(existingEquipment.getCreatedDate());

        
        List<ChargeDetailRequest> chargeRequests = createShipmentRequest.getShipmentEquipment().getCharge();
        List<Charge> newCharges = genericShipmentRequestMapper.mapAsList(chargeRequests, Charge.class);
        for(Charge ch:newCharges) {
            ch.setEquipmentOnShipment(eqonsh);
            ch.setEquipmentOnShipmentId(existingEquipment.getEquipmentOnShipmentId());
        }
        eqonsh.setCharge(newCharges);
        equipmentRepository.save(eqonsh);
    }

    private void addEquipmentOnShipment(GenericShipmentRequest createShipmentRequest,
            ShipmentResponse shipmentResponse) {
        EquipmentOnShipment eqonsh = genericShipmentRequestMapper
                .map(createShipmentRequest.getShipmentEquipment(), EquipmentOnShipment.class);
        eqonsh.setShipmentLegId(Long.parseLong(shipmentResponse.getId()));

        List<ChargeDetailRequest> chargeRequests = createShipmentRequest.getShipmentEquipment().getCharge();
        List<Charge> charges = genericShipmentRequestMapper.mapAsList(chargeRequests, Charge.class);
        for(Charge ch:charges) {
            ch.setEquipmentOnShipment(eqonsh);
            ch.setEquipmentOnShipmentId(eqonsh.getEquipmentOnShipmentId());
        }
        eqonsh.setCharge(charges);
        equipmentRepository.save(eqonsh);
    }

    private Shipment checkExistingShipment(ShipmentRequest createRequest) {
        Shipment existingShipment = null;
        if (ShipmentReferenceIdType.WORKORDER.name().equals(createRequest.getReferenceType())
                && createRequest.getReferenceId() != null) {
            existingShipment = shipmentRepository.getShipmentByReferenceIdType(createRequest.getReferenceId(),
                    createRequest.getReferenceType());
        } else {
            for (ShipmentReferenceRequest sr : createRequest.getShipmentReferences()) {
                List<Shipment> shipments = shipmentRepository.findShipmentsByRefTypeValue(sr.getShipmentRefType(),
                        sr.getShipmentRefValue());
                for(Shipment tempShipment:shipments) {
                    if (tempShipment != null && isSameOriginLocation(tempShipment, createRequest)) {
                        existingShipment = tempShipment;
                        break;
                    }
                }
            }

        }
        return existingShipment;
    }

    private boolean isSameOriginLocation(Shipment existingShipment, ShipmentRequest createRequest) {
        if(createRequest.getOriginUnlocode()!=null&&existingShipment.getOriginUnlocode()!=null
                &&createRequest.getOriginUnlocode().equalsIgnoreCase(existingShipment.getOriginUnlocode())) return true;
        
        
        if(createRequest.getOrigin()!=null&&existingShipment.getOrigin()!=null) {
            String createRequestNameString=createRequest.getOrigin()+createRequest.getOriginCity()+
                    createRequest.getOriginState()+createRequest.getOriginZipcode();
            String existingShipmentNameString=existingShipment.getOrigin()+existingShipment.getOriginCity()+
                    existingShipment.getOriginState()+existingShipment.getOriginZipcode();
            if(createRequestNameString.equalsIgnoreCase(existingShipmentNameString)) return true;
        }
        
        if(createRequest.getOriginAddress1()!=null&&existingShipment.getOriginAddress1()!=null) {
            String createRequestAddressString=createRequest.getOriginAddress1()+createRequest.getOriginAddress2()+createRequest.getOriginCity()+
                    createRequest.getOriginState()+createRequest.getOriginZipcode()+createRequest.getOriginCountry();
            String existingShipmentAddressString=existingShipment.getOriginAddress1()+existingShipment.getOriginAddress2()+existingShipment.getOriginCity()+
                    existingShipment.getOriginState()+existingShipment.getOriginZipcode()+existingShipment.getOriginCountry();
            if(createRequestAddressString.equalsIgnoreCase(existingShipmentAddressString)) return true;
        }

        String requestOriginUuid=createRequest.getOrigin_LocationId();
        String existingOriginUuid=existingShipment.getOrigin_LocationId();
        if(requestOriginUuid==null) {
            requestOriginUuid = shipmentUtil.getLocationIdAll(createRequest.getOrigin(), 
                    createRequest.getOriginAddress1(),
                    createRequest.getOriginAddress2(), createRequest.getOriginCity(), 
                    createRequest.getOriginState(), createRequest.getOriginZipcode(), 
                    createRequest.getOriginCountry());
            createRequest.setOrigin_LocationId(requestOriginUuid);
        }
        if(existingOriginUuid==null) {
            existingOriginUuid = shipmentUtil.getLocationIdAll(existingShipment.getOrigin(),
                    existingShipment.getOriginAddress1(),
                    existingShipment.getOriginAddress2(), existingShipment.getOriginCity(),
                    existingShipment.getOriginState(), existingShipment.getOriginZipcode(),
                    existingShipment.getOriginCountry());
        }

        if(requestOriginUuid!=null&&existingOriginUuid!=null
            &&existingOriginUuid.equals(requestOriginUuid)) return true;
    
        
        return false;
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


    public Set<ShipmentReferenceRequest> extractShipmentReferences(GenericShipmentRequest createShipmentRequest) {
        logger.info("Entering into GenericShipmentServiceImpl.extractShipmentReferences");
        Set<ShipmentReferenceRequest> shipmentReferenceRequestSet = new TreeSet<>();
        for (ShipmentReferenceRequest sr : createShipmentRequest.getShipmentReferences()) {
            if (!ShipmentUtil.isNullString(sr.getShipmentRefValue())
                    && !ShipmentUtil.isNullString(sr.getShipmentRefType())) {
                shipmentReferenceRequestSet.add(sr);
            }
        }

        logger.info("Ending into GenericShipmentServiceImpl.extractShipmentReferences");
        return shipmentReferenceRequestSet;
    }

    /**
     * This GenericShipmentServiceImpl.createShipment method is used to create shipment using ShipmentRequest request
     * 
     * @param createRequest This is used to pass ShipmentRequest request data
     * @return ShipmentResponse upload To get response of ShipmentResponse
     * @throws Exception Handle the exceptions
     */

    public ShipmentResponse createShipment(ShipmentRequest createRequest) throws Exception {
        try {
            logger.info("Entering into GenericShipmentServiceImpl.createShipment");

            if (createRequest == null) {
                if (logger.isDebugEnabled()) {
                    logger.debug(
                            "GenericShipmentServiceImpl.createShipment - Throwing Exception as Invalid Event Code Passed");
                }

                throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(),
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

            //checkLookupValues(linkedShipments);//This fix is not needed for generic shipment service
            if (logger.isDebugEnabled()) {
                logger.debug("Entering into GenericShipmentServiceImpl.createShipment - Data Inserted Successfully");
            }

            shipmentMilestoneService.createShipmentMilestones(upload);

            if (createRequest.getShipmentStopListRequest() != null
                    && createRequest.getShipmentStopListRequest().getStopList() != null
                    && !createRequest.getShipmentStopListRequest().getStopList().isEmpty()) {
                stopService.createStop(createRequest.getShipmentStopListRequest(), upload.getShipmentId());
            } else {
                shipmentMilestoneService.addPlannedStopsFromDM(upload);
            }


            logger.debug("Entering into GenericShipmentServiceImpl.createShipment - Data Inserted Successfully");
            ShipmentResponse shipmentResponse = new ShipmentResponse();
            String id = upload.getShipmentId().toString();
            shipmentResponse.setId(id);
            shipmentResponse.setStatus(status);
            shipmentResponse.setMessage(message);
            /// ShipmentEventResponse createEventsRes = shipmenteventMapper.map(upload,
            /// ShipmentResponse.class);
            if (logger.isDebugEnabled()) {
                logger.debug(
                        "Entering into GenericShipmentServiceImpl.createShipment - Mapping Shipment to CreateResponse");
            }

            return shipmentResponse;

        } catch (Exception ex) {
            logger.error("Exception inside GenericShipmentServiceImpl.createShipment.Catch_Block:" + ex.getMessage(),
                    ex);
            throw ex;
        }
    }

    private void addNewE2EShipment(ShipmentRequest createRequest, Shipment upload) {
        E2EShipment e2eShipmentReq = shipmentModelMapper.map(createRequest, E2EShipment.class);
        if(upload.getOrigin()!=null&&e2eShipmentReq.getOrigin()==null) {
            e2eShipmentReq.setOrigin(upload.getOrigin());
        }
        if(upload.getDestination()!=null&&e2eShipmentReq.getDestination()==null) {
            e2eShipmentReq.setDestination(upload.getDestination());
        }
        if (shipmentUtil.parseBooleanValue(createRequest.getIsHazmat()))
            e2eShipmentReq.setIsHazmat("Y");
        else
            e2eShipmentReq.setIsHazmat("N");
        if (shipmentUtil.parseBooleanValue(createRequest.getIsTempControlled()))
            e2eShipmentReq.setIsTemperatureController("Y");
        else
            e2eShipmentReq.setIsTemperatureController("N");

        // set e2eShipment reference from ShipmentRequest
        e2eShipmentReq.setShipmentNumber(createRequest.getShipmentNumber());
        
        e2eShipmentReq.setWayBillNumber(createRequest.getWayBillNumber());
        if(e2eShipmentReq.getWayBillNumber()==null) {
            e2eShipmentReq.setWayBillNumber(e2eShipmentReq.getShipmentNumber());
        }
       
        if (ShipmentUtil.isNullString(e2eShipmentReq.getStatus())) {
            e2eShipmentReq.setStatus(WorkorderStatus.Unassigned.name());
        }
        e2eShipmentReq.setDeleteFlag("N");
        ///updated to add the create date time always as its should not create issue in UI.
       
        /*if(e2eShipmentReq.getCreatedDateTime() == null) {
        	e2eShipmentReq.setCreatedDateTime(new Date());
        }*/
        e2eShipmentReq.setCreatedDateTime(new Date());
        E2EShipment e2eShipmentUpload = e2eShipmentRepository.save(e2eShipmentReq);
        upload.setE2eShipmentId(e2eShipmentUpload.getShipmentId());
        shipmentRepository.save(upload);

    }
    
    /**
     * This GenericShipmentServiceImpl.createShipmentFromRequestData method is used to create shipmentReference using
     * ShipmentRequest request
     * 
     * @param createRequest This is used to pass ShipmentRequest request data
     * @return ShipmentResponse upload To get response of ShipmentResponse
     */
    private Shipment createShipmentFromRequestData(ShipmentRequest createRequest) {
        Shipment createReq = shipmentModelMapper.map(createRequest, Shipment.class);

        createReq.setHazmat(shipmentUtil.parseBooleanValue(createRequest.getIsHazmat()));
        createReq.setTempControlled(shipmentUtil.parseBooleanValue(createRequest.getIsTempControlled()));
        if (ShipmentUtil.isNullString(createReq.getStatus())) {
            createReq.setStatus(WorkorderStatus.Created.name());
        }
        checkAssignedStatus(createRequest, createReq);
        setShipmentReferences(createRequest, createReq);
        createReq.setLegMode(createReq.getLegMode().toUpperCase());
        Shipment shipment = createReq;
        try {
            shipment = setLocationIdInShipment(createReq);
            // error Handling
        } catch (Exception e) {
            logger.error("Exception occurred while creating createShipmentFromRequestData - exception: ", e);
            // throw e;
        }
        
        
        // Set Shipment StatusId field
        shipment = setShipmentStatusId(shipment);
		
        
        return shipmentRepository.save(shipment);

        // return shipmentRepository.save(createReq);
    }

    private void checkAssignedStatus(ShipmentRequest createRequest, Shipment createReq) {
        if(!ShipmentUtil.isNullString(createRequest.getCarrier())
                ||!ShipmentUtil.isNullString(createRequest.getReceiverName())) {
            if(ShipmentUtil.isNullString(createReq.getStatus())
                    ||WorkorderStatus.Created.name().equals(createReq.getStatus())
                    ||WorkorderStatus.Unassigned.name().equals(createReq.getStatus())
                    ) {
                createReq.setStatus(WorkorderStatus.Assigned.name());
            }
            if(ShipmentUtil.isNullString(createRequest.getCarrier())){
                createReq.setCarrier(createRequest.getReceiverName());
            }
        }
    }

    /**
     * This GenericShipmentServiceImpl.setLocationIdInShipment method is used to create shipmentReference using
     * ShipmentRequest request
     * 
     * @param createReq This is used to pass Shipment data
     * @return Shipment To get response of Shipment Object
     */
    private Shipment setLocationIdInShipment(Shipment createReq) throws Exception {
        try {
            logger.info("Entering into GenericShipmentServiceImpl.setLocationIDInShipment and set all datafields");
            if(!ShipmentUtil.isNullString(createReq.getOriginUnlocode())) {
                populateOriginLocationByUnlocode(createReq, createReq.getOriginUnlocode());
            }else {
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

                String origin_LocationId = shipmentUtil.getLocationIdByAddress(originLocationAPIResponses);
                if (origin_LocationId == null || origin_LocationId.trim().length() == 0) {
                    logger.error(new StringBuffer("UUID Not Found for Shipment Origin Location - Address 1 :")
                            .append(originLocationAPIResponse.getAddress1()).append(" /n Address 2:")
                            .append(originLocationAPIResponse.getAddress2()).append(" /n City: ")
                            .append(originLocationAPIResponse.getCity()).append(" /n State: ")
                            .append(originLocationAPIResponse.getState()).append(" /n Postal Code: ")
                            .append(originLocationAPIResponse.getPostalCode()).append(" /n Country: ")
                            .append(originLocationAPIResponse.getCountry()));
                }
                createReq.setOrigin_LocationId(origin_LocationId);
                if(createReq.getOrigin()==null) createReq.setOrigin(createReq.getOriginAddress1());
            }
            
            if(!ShipmentUtil.isNullString(createReq.getDestinationUnlocode())) {
                populateDestinationLocationByUnlocode( createReq, createReq.getDestinationUnlocode());
            }else {
                
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

                String destination_LocationId = shipmentUtil.getLocationIdByAddress(destinationLocationAPIResponses);
                if (destination_LocationId == null || destination_LocationId.trim().length() == 0) {
                    logger.error(new StringBuffer("UUID Not Found for Shipment Destination Location - Address 1 :")
                            .append(destinationLocationAPIResponse.getAddress1()).append(" /n Address 2:")
                            .append(destinationLocationAPIResponse.getAddress2()).append(" /n City: ")
                            .append(destinationLocationAPIResponse.getCity()).append(" /n State: ")
                            .append(destinationLocationAPIResponse.getState()).append(" /n Postal Code: ")
                            .append(destinationLocationAPIResponse.getPostalCode()).append(" /n Country: ")
                            .append(destinationLocationAPIResponse.getCountry()));
                }               
                createReq.setDestination_LocationId(destination_LocationId);
                if(createReq.getDestination()==null) createReq.setDestination(createReq.getDestinationAddress1());
            }
            
            return createReq;
        } catch (Exception e) {
            logger.error("Exception occurred while creating setLocationIDInShipment - exception: ", e);
            throw e;
        }
    }
    
    private void populateOriginLocationByUnlocode(Shipment createReq, String unlocode) {
        try {
            List<LocationAPIResponse> locations= shipmentUtil.getLocationsByUnlocode(unlocode); 
            LocationAPIResponse locationAPIResponse=getLocationType(createReq, locations);
            if(locationAPIResponse!=null) {
                createReq.setOriginUnlocode(unlocode);
                createReq.setOrigin(locationAPIResponse.getName());
                createReq.setOriginAddress1(locationAPIResponse.getAddress1());
                createReq.setOriginAddress2(locationAPIResponse.getAddress2());
                createReq.setOriginCity(locationAPIResponse.getCity());
                createReq.setOriginState(locationAPIResponse.getState());
                createReq.setOriginZipcode(locationAPIResponse.getPostalCode());
                createReq.setOriginCountry(locationAPIResponse.getCountry());
                createReq.setOrigin_LocationId(locationAPIResponse.getUuid());   
            }
        } catch (Exception e) {
            logger.error("Exception inside ShipmentViewSersServiceImpl.populateOriginLocationByUnlocode.Catch_Block:"
                    + e.getMessage());
        }

    }

    private LocationAPIResponse getLocationType(Shipment createReq, List<LocationAPIResponse> locations) {
        LocationAPIResponse locationAPIResponse=null;
        if (locations != null&&!locations.isEmpty()) {
            for(LocationAPIResponse loc:locations) {
                if(ShipmentMode.OCEAN.name().equalsIgnoreCase(createReq.getLegMode())
                        &&LocationType.PORT.name().equalsIgnoreCase(loc.getLocationType())) {
                    locationAPIResponse=loc;
                    break;
                }else if(ShipmentMode.RAIL.name().equalsIgnoreCase(createReq.getLegMode())
                        &&LocationType.RAIL_YARD.name().equalsIgnoreCase(loc.getLocationType())) {
                    locationAPIResponse=loc;
                    break;
                }else if(ShipmentMode.AIR.name().equalsIgnoreCase(createReq.getLegMode())
                        &&LocationType.AIRPORT.name().equalsIgnoreCase(loc.getLocationType())) {
                    locationAPIResponse=loc;
                    break;
                }
            }
            if(locationAPIResponse==null) locationAPIResponse=locations.get(0);
        }
        return locationAPIResponse;
    }

    private void populateDestinationLocationByUnlocode(Shipment createReq, String unlocode) {
        try {
            List<LocationAPIResponse> locations= shipmentUtil.getLocationsByUnlocode(unlocode);     
            LocationAPIResponse locationAPIResponse=getLocationType(createReq, locations);
            if(locationAPIResponse!=null) {
                createReq.setDestinationUnlocode(unlocode);
                createReq.setDestination(locationAPIResponse.getName());
                createReq.setDestinationAddress1(locationAPIResponse.getAddress1());
                createReq.setDestinationAddress2(locationAPIResponse.getAddress2());
                createReq.setDestinationCity(locationAPIResponse.getCity());
                createReq.setDestinationState(locationAPIResponse.getState());
                createReq.setDestinationZipcode(locationAPIResponse.getPostalCode());
                createReq.setDestinationCountry(locationAPIResponse.getCountry());
                createReq.setDestination_LocationId(locationAPIResponse.getUuid());
            }
        } catch (Exception e) {
            logger.error("Exception inside ShipmentViewSersServiceImpl.populateDestinationLocationByUnlocode.Catch_Block:"
                    + e.getMessage());
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
     * This GenericShipmentServiceImpl.updateShipment method is used to create shipment using ShipmentRequest request
     * 
     * @param createRequest This is used to pass ShipmentRequest request data
     * @return ShipmentResponse upload To get response of ShipmentResponse
     * @throws Exception Handle the exceptions
     */

    public ShipmentResponse updateShipment(ShipmentRequest createRequest, Shipment existingShipment) throws Exception {
        try {
            logger.info("Entering into GenericShipmentServiceImpl.updateShipment");

            if (createRequest == null) {
                if (logger.isDebugEnabled()) {
                    logger.debug(
                            "GenericShipmentServiceImpl.updateShipment - Throwing Exception as Invalid Event Code Passed");
                }

                throw shipmentUtil.throwInValidDataException(ExceptionCode.SHIPMENTEVENTINVALIDCUSTOM.toString(),
                        ShipmentConstants.EMPTY_SHIPMENT_REQUEST_UPDATE_MESSAGE);
            }

            String status = ShipmentConstants.DONE_MESSAGE;
            String message = ShipmentConstants.COMPLETE_MESSAGE;
            Shipment createReq = shipmentModelMapper.map(createRequest, Shipment.class);

            Optional<Shipment> shipmentObj = shipmentRepository.findById(existingShipment.getShipmentId());
            Shipment upload = null;

            if (shipmentObj.isPresent()) {
                createReq.setShipmentId(existingShipment.getShipmentId());
                createReq.setHazmat(shipmentUtil.parseBooleanValue(createRequest.getIsHazmat()));
                createReq.setTempControlled(shipmentUtil.parseBooleanValue(createRequest.getIsTempControlled()));

                // TODO do not allow update references or need add more rule on updating E2E shipment references
                // and no update for shipment relationship
                createReq.setShipmentReferences(shipmentObj.get().getShipmentReferences());
                createReq.setLegMode(createReq.getLegMode().toUpperCase());
                checkAssignedStatus(createRequest, createReq);
                
                //set location uuid 
                Shipment shipment = createReq;
                try {
                    shipment = setLocationIdInShipment(createReq);
                } catch (Exception e) {
                    logger.error("Exception occurred while creating updateShipment - exception: ", e);
                }
                
                
                // Set Shipment StatusId field
                shipment = setShipmentStatusId(shipment);
				
                upload = shipmentRepository.save(shipment);

                E2EShipment e2eShipment = getLinkedE2EShipment(createRequest);
                if (e2eShipment != null) {
                    // update e2e_shipment origin/destination
                    updateE2EShipmentOriginDestination(e2eShipment, upload);
                    upload.setE2eShipmentId(e2eShipment.getShipmentId());
                    shipmentRepository.save(upload);
                } else {
                    addNewE2EShipment(createRequest, upload);
                }

            } else {
                return createShipment(createRequest);
            }
            if (logger.isDebugEnabled()) {
                logger.debug(
                        "Entering into GenericShipmentServiceImpl.updateShipment - Data Updated/Inserted Successfully");
            }

            ShipmentResponse shipmentResponse = new ShipmentResponse();
            String id = upload.getShipmentId().toString();
            shipmentResponse.setId(id);
            shipmentResponse.setStatus(status);
            shipmentResponse.setMessage(message);
            if (logger.isDebugEnabled()) {
                logger.debug(
                        "Entering into GenericShipmentServiceImpl.updateShipment - Mapping Shipment to CreateResponse");
            }

            return shipmentResponse;
        } catch (Exception ex) {
            logger.error("Exception inside GenericShipmentServiceImpl.updateShipment.Catch_Block:", ex);
            throw ex;
        }
    }

    /**
     * This GenericShipmentServiceImpl.addShipmentRelationShip method is used to create shipmentRelationship using
     * Shipment request
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
            
            
            //TODO, check duplicates - need refactor this  
            boolean noduplicate=true;
            List<ShipmentRelationship> srs =
                    shipmentRelationshipRepository.findByShipmentId(shipment.getShipmentId());
            for(ShipmentRelationship sr:srs) {
                if(relationShip.getParentId()==null&& sr.getParentId()==null) {
                    noduplicate=false;
                    break;
                }
                if(relationShip.getParentId()!=null&& sr.getParentId()!=null
                        &&relationShip.getParentId().intValue()==sr.getParentId().intValue()) {
                    noduplicate=false;
                    break;
                }
            }
            if(noduplicate) shipmentRelationshipRepository.save(relationShip);

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
            logger.error("Exception inside GenericShipmentServiceImpl.addShipmentRelationShip.Catch_Block:", ex);
            throw ex;
        }
    }

    // check and set any lookup values that are missing from workorder request
    // object
    private void checkLookupValues(List<Shipment> shipments) {
        for (Shipment shipment : shipments) {
            if (shipment.getReferenceId() != null&&shipment.getReferenceId().intValue()!=0) {
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
        List<String> bls = createRequest.getBolNumbers();
        if (bls != null && bls.isEmpty())
            bls.add(Math.random() + "");

        E2EShipment e2eShipment = e2eShipmentRepository.searchE2EShipmentByShipmentRefs(
                createRequest.getShipmentNumber(), bls, createRequest.getOrganizationCode());
        return e2eShipment;
    }

    private void updateE2EShipmentOriginDestination(E2EShipment e2eShipment, Shipment newShipment) {
        // if shipment is before E2E_Shipment, update origin
        if (ShipmentUtil.getShipmentCompareDate(newShipment).before(ShipmentUtil.getShipmentCompareDate(e2eShipment))) {
            e2eShipment.setOrigin(newShipment.getOrigin());
            e2eShipment.setOriginAddress1(newShipment.getOriginAddress1());
            e2eShipment.setOriginAddress2(newShipment.getOriginAddress2());
            e2eShipment.setOriginCity(newShipment.getOriginCity());
            e2eShipment.setOriginCountry(newShipment.getOriginCountry());
            e2eShipment.setOriginState(newShipment.getOriginState());
            e2eShipment.setOriginZipCode(newShipment.getOriginZipcode());
            e2eShipment.setExpectdPickupDate(newShipment.getSchedulePickupDate());
            e2eShipment.setOriginUnlocode(newShipment.getOriginUnlocode());
            if(newShipment.getOrigin()!=null&&e2eShipment.getOrigin()==null) {
                e2eShipment.setOrigin(newShipment.getOrigin());
            }
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
            e2eShipment.setDestinationUnlocode(newShipment.getDestinationUnlocode());
            if(newShipment.getDestination()!=null&&e2eShipment.getDestination()==null) {
                e2eShipment.setDestination(newShipment.getDestination());
            }
        }
        if(newShipment.getOrganizationCode()!=null&&e2eShipment.getOrganizationCode()==null) {
            e2eShipment.setOrganizationCode(newShipment.getOrganizationCode());
        }

        e2eShipment.setStatus(newShipment.getStatus());
        e2eShipmentRepository.save(e2eShipment);
    }


    /**
	 * Update shipment status through API
	 * 
	 * @param shipmentStatusRequest - This is request which has shipment references and status details
	 * @param shipmentId - This is shipment ID
	 * @return ShipmentResponse Shipment Response after save
	 * @throws Exception - Handle service exceptions
	 */
	public ShipmentResponse updateShipmentStatus(ShipmentStatusRequest shipmentStatusRequest) throws Exception {
		ShipmentResponse shipmentResponse = new ShipmentResponse();
        
		List<ShipmentReferencesRequest> shipmentReferences = shipmentStatusRequest.getShipmentReferences();
		
		if(shipmentReferences == null) {
	        shipmentResponse.setStatus(ShipmentConstants.EMPTY_SHIPMENTSTATUS_REQUEST_MESSAGE);
	        shipmentResponse.setMessage(ShipmentConstants.COMPLETE_MESSAGE);
	        return shipmentResponse;
		}
		
		for(ShipmentReferencesRequest shipmentReferencesRequest : shipmentReferences) {
			List<BigDecimal> shipmentIds = new ArrayList<>();
			
			try {
				shipmentIds = shipmentReferenceRepository.findShipmentIdByRefTypeValue(shipmentReferencesRequest.getShipmentRefType(), 
					shipmentReferencesRequest.getShipmentRefValue());
			} catch(Exception e) {
				e.printStackTrace();
				
				shipmentResponse.setStatus(ShipmentConstants.NO_RECORD_FIND_IN_SHIPMENTREFERENCE_MESSAGE);
		        shipmentResponse.setMessage(ShipmentConstants.COMPLETE_MESSAGE);
		        return shipmentResponse;
			}
			
			if(shipmentIds.size() == 0 || shipmentIds == null) {
				shipmentResponse.setStatus(ShipmentConstants.NO_RECORD_FIND_IN_SHIPMENTREFERENCE_MESSAGE);
		        shipmentResponse.setMessage(ShipmentConstants.COMPLETE_MESSAGE);
		        return shipmentResponse;
			}
			
			for(BigDecimal shipmentId : shipmentIds) {
				Long id = new Long(shipmentId.longValue());
				Shipment shipment = new Shipment();
				
				try {
					shipment = shipmentRepository.findByShipmentId(id);
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				
				if(shipment  == null) {
					continue;
				}
				
				if(shipment.getStatus().equals(shipmentStatusRequest.getStatus())) {
					continue;
				}
				shipment.setStatus(shipmentStatusRequest.getStatus());
				
				
				// Set Shipment StatusId field
				shipment = setShipmentStatusId(shipment);
				
				shipmentRepository.save(shipment);
				
			}
			
		}
		
		
        shipmentResponse.setStatus(ShipmentConstants.DONE_MESSAGE);
        shipmentResponse.setMessage(ShipmentConstants.COMPLETE_MESSAGE);
   
        return shipmentResponse;
	}
	
	
	private Shipment setShipmentStatusId(Shipment shipment) {
		// Set Shipment StatusId field
		try {
			Long shipmentStatusId = shipmentStatusRepository.findShipmentStatusIdBydescription(shipment.getStatus());
			shipment.setStatusId(shipmentStatusId);
		} catch(Exception e) {
			logger.error("Exception occurred while updateShipment API and set Shipment statusID - exception: ", e);
		}
		return shipment;
	}
	
}

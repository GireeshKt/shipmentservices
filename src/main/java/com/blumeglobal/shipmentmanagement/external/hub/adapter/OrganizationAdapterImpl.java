package com.blumeglobal.shipmentmanagement.external.hub.adapter;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.blumeglobal.shipmentmanagement.exceptions.AdapterServiceException;
import com.blumeglobal.shipmentmanagement.external.hub.common.HubServicesHandlerProxy;
import com.blumeglobal.shipmentmanagement.external.hub.endpoint.IDispatchManagerFacade;
import com.blumeglobal.shipmentmanagement.external.hub.endpoint.response.OrganizationResponse;
import com.ias.hub.core.dto.OrganizationDTO;



public class OrganizationAdapterImpl implements IOrganizationAdapter {
    private static final Logger logger = LogManager.getLogger(OrganizationAdapterImpl.class);
    @Autowired
    private IDispatchManagerFacade dispatchManagerFacadeClient;

    @Override
    public boolean validateOriginatorCode(String code) throws AdapterServiceException {


        OrganizationResponse organizationName =
                (OrganizationResponse) (HubServicesHandlerProxy.invokeHubService("dispatchManagerFacadeClient",
                        "getAllOriginatorOrReceivers", new Object[] {"Originator", "DMS"}));
        List<OrganizationDTO> orgList = organizationName.getData();
        for (OrganizationDTO orgDto : orgList) {
            if (orgDto.getCode().equals(code)) {


                return true;
            }
        }

        return false;
    }


    @Override
    public boolean validateReceiverCode(String code) throws AdapterServiceException {


        OrganizationResponse organizationName =
                (OrganizationResponse) (HubServicesHandlerProxy.invokeHubService("dispatchManagerFacadeClient",
                        "getAllOriginatorOrReceivers", new Object[] {"Receiver", "DMS"}));
        List<OrganizationDTO> orgList = organizationName.getData();
        for (OrganizationDTO orgDto : orgList) {
            if (orgDto.getCode().equals(code)) {

                return true;
            }
        }

        return false;
    }


    @Override
    public Long getOrganizationIdByCode(String orgCode) throws AdapterServiceException {
        Long organizationId = null;
        try {
            //OrganizationResponse orgResponse = dispatchManagerFacadeClient.getOrganization(orgCode);
             OrganizationResponse orgResponse =
             (OrganizationResponse) (HubServicesHandlerProxy.invokeHubService("dispatchManagerFacadeClient",
             "getOrganization", new Object[] {orgCode}));

            if (orgResponse != null && orgResponse.getData() != null) {

                List<OrganizationDTO> orgList = orgResponse.getData();
                if (!orgList.isEmpty()) {
                    organizationId = orgList.get(0).getDbid();
                }
            }
        } catch (Exception e) {
            logger.error(
                    "Error in OrganizationAdapterImpl.getOrganizationIdByCode for parameter: orgCode = " + orgCode);
            logger.error("Error in OrganizationAdapterImpl.getOrganizationIdByCode. Message -   " + e.getMessage());
        }
        logger.info("Hub lookup for organization code: "+orgCode +" found id: "+organizationId);
        return organizationId;
    }

}

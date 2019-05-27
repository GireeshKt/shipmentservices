package com.blumeglobal.shipmentmanagement.external.hub.adapter;


import com.blumeglobal.shipmentmanagement.exceptions.AdapterServiceException;



public interface IOrganizationAdapter {

	/**
	 * 
	 *
	 * @param Originator Code
	 * @return true/false to indicate whether the code exists in hub
	 * @throws AdapterServiceException the adapter service exception
	 */


	public boolean validateOriginatorCode(String code) throws AdapterServiceException;
	
	
	/**
	 * 
	 *
	 * @param Receiver Code
	 * @return true/false to indicate whether the code exists in hub
	 * @throws AdapterServiceException the adapter service exception
	 */
	
	
	public boolean validateReceiverCode(String code) throws AdapterServiceException;


    Long getOrganizationIdByCode(String organizationCode) throws AdapterServiceException;



}

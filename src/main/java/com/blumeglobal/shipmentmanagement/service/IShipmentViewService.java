package com.blumeglobal.shipmentmanagement.service;

import java.util.List;

import com.blumeglobal.shipmentmanagement.response.E2EShipmentDetailsResponse;
import com.blumeglobal.shipmentmanagement.response.E2EShipmentResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentFreightDetailResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentSummaryResponse;
import com.blumeglobal.shipmentmanagement.response.ShipmentWOResponse;

public interface IShipmentViewService {
	ShipmentSummaryResponse getShipmentViewSummary(String orgnizationCode) throws Exception;
	List<E2EShipmentResponse> e2eshipmentList(int startRow, int endRow, String orgnizationCode, String viewType);
	ShipmentWOResponse findShipmentbyId(Long id);
	List<ShipmentWOResponse> getShipmentbyE2EShipmentId(Long id);
	E2EShipmentDetailsResponse findE2EShipmentDetailsByRef(String ref, String organizationCode);
	List<E2EShipmentResponse> searchE2EShipmentByRefTypeValue(String refType, String refValue, String organizationCode);
	List<ShipmentFreightDetailResponse> shipmentFreightList(int startRow, int endRow, String orgnizationCode);
	List<ShipmentFreightDetailResponse> searchShipmentFreightDetailsByRefTypeValue(String refType, String refValue, String organizationCode);
	E2EShipmentResponse getE2EShipmentStatusToOrderDetails(String shipmentNumber, String organizationCode);
}

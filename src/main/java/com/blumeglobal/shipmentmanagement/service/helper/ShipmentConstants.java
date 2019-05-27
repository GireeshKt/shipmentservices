package com.blumeglobal.shipmentmanagement.service.helper;

import org.springframework.stereotype.Component;
/**
 * ShipmentConstants component class is used to maintain constants for shipment
 *
 */
@Component
public class ShipmentConstants implements DispatchConstants {
	
	private ShipmentConstants() {
		
	}
	public static final String WARNING = "Warning";
	public static final String SUCCESS = "Success";
	public static final String ERROR = "Error";
	public static final String FAILED = "Failed";
	public static final String IGNORED = "Ignored";
	public static final String ACTIVE = "Active";
	
	
	public static final int EVENTCODE_LENGTH = 10;
	public static final int EVENTNAME_LENGTH = 50;
	public static final int EVENTDESC_LENGTH = 250;
	public static final int CATEGORY_LENGTH = 20;
	public static final int ORIGINATORCODE_LENGTH = 20;
	public static final int SERVICEPROVIDERCODE_LENGTH = 20;
	public static final int ORIGINATORNAME_LENGTH = 100;
	public static final int TRANSPORTMODE_LENGTH = 20;
	public static final int PUBLISHERCODE_LENGTH = 20;
	public static final int PUBLISHERTYPE_LENGTH = 50;
	public static final int ATTRIBUTENAME_LENGTH = 20;
	public static final int CREATEDBY_LENGTH = 50;
	public static final int MODIFIEDBY_LENGTH = 50;
	
	public static final int ONHAND_LENGTH = 50;
	public static final int HOUSEBILL_LENGTH = 50;
	public static final int MASTERBILL_LENGTH = 50;
	public static final int CONSIGNEE_LENGTH = 100;
	public static final int CARRIER_LENGTH = 50;
	public static final int FLIGHTNUMBER_LENGTH = 20;
	public static final int ORIGINCODE_LENGTH = 10;
	public static final int DESTINATIONCODE_LENGTH = 10;
	public static final int VESSEL_LENGTH = 50;
	public static final int VOYAGENUMBER_LENGTH = 20;
	public static final int PORTOFLOADING_LENGTH = 50;
	public static final int PORTOFDISCHARGE_LENGTH = 50;
	public static final int UNITTYPE_LENGTH = 20;
	public static final int FREIGHTLOCATION_LENGTH = 100;
	public static final int STATUS_LENGTH = 50;
	public static final int CONTACTOPERATOR_LENGTH = 50;
	public static final int NOTES_LENGHT = 250;
	
	public static final int WORKORDERNUMBER_LENGTH = 50;
	public static final int UNITID_LENGTH = 50;
	public static final int LOCATION_LENGTH = 100;
	public static final int REPORTEDBY_LENGTH = 50;
	public static final int REPORTSOURCE_LENGTH = 20;
	public static final int NOTES_LENGTH = 250;
	
	public static final String SHIPMENT_PUBLISHER_TYPE_RECEIVER = "Receiver";
	public static final String SHIPMENT_PUBLISHER_TYPE_FS = "Freight Station";
	public static final String SHIPMENT_QUERY_PARAM= "/purchaseordershipment?PO=%PO&POREF=%REF&SELLER=%SELLER&BUYER=%BUYER";
	public static final String SHIPMENT_HISTORY_PATH="/shipment/%ID/history";
	public static final String LOCATION_ERROR_STATUS="ERROR";
	public static final String LOCATION_ERROR_MESSAGE="The request does not have the location details to perform the delete action";
	public static final String LOCATION_SUCCESS_STATUS="Success";
	public static final String LOCATION_SUCCESS_MESSAGE="The Location Deleted Successfully";
	public static final String ROUTING_ERROR_STATUS="Failure";
	public static final String ROUTING_ERROR_MESSAGE="The Routing ID already Exist";
	public static final String LANE_ERROR_STATUS="Failure";
	public static final String LANE_ERROR_MESSAGE="The Lane ID already Exist";
	public static final String LANE_SUCCESS_STATUS="Success";
	public static final String LANE_SUCCESS_MESSAGE="Lane Updated Successfully... for laneId :";
	
	//kafkaShipmentEventServiceImpl
	public static final String REPORT_SOURCE = "OCEANEVENT";
	public static final String CODE_TYPE = "UNLOCODE";
	public static final String I = "In Gate";
	public static final String AE = "Loaded on Vessel";
	public static final String OA = "Out Gate to Consignee";
	public static final String AP = "Loaded on 2nd Vessel";
	public static final String AR = "Rail Arrival";
	public static final String AL = "Loaded on Rail";
	public static final String AV = "Available for Pick UP";
	public static final String AF = "Departed Pick Up Location";
	public static final String CR = "Carrier Release";
	public static final String CT = "Custom Release";
	public static final String EE = "Empty Container Pickup";
	public static final String RD = "Return Container";
	public static final String RL = "Rail Departure";
	public static final String VD = "Vessel Departure";
	public static final String VA = "Vessel Arrival";
	public static final String UV = "Unloaded From Vessel";
	public static final String UR = "Unloaded From Rail";
	
	//Create E2ESHIPMENTS
	public static final String HAZMAT_EXPORT_TEMPCONT_VALUE_ERROR="IsExport , IsHazmat,  IsTemperatureController should be either 'Y' or 'N' ";
	public static final String DESTCOUNTRY_ORIGINCOUNTRY_LENGTH_ERROR="DestinationCountry  and OriginCountry length should not greater than 2";
	public static final String SHIPMENT_CREATION_SUCCESS_MESSAGE="Shipment Created Successfully... Shipment ID :";
	public static final String SHIPMENT_CREATION_ERROR_MESSAGE="Unable to Create Shipment...";
	public static final String DUPLICATE_SHIPMENT_NUMBER_ORIGIN_DEST_ORG_ERROR_MESSAGE="Duplicate records found ... Like.. Shipment Number : %s Origin : %s Destination : %s OrganizationCode : %s ";
	public static final String INVALID_SHIPMENT_NUMBER_STATUS_ORIGIN_DEST_ERROR_MESSAGE="Invalid Input passed ... Like.. Shipment Number : %s Status : %s Origin : %s Destination : %s ";
		
	//Get All Shipments
	public static final String SHIPMENTS_DATA_AVAILABILITY_MESSAGE="No Shipment Data Available";
		
	//getShipmentById
	public static final String SHIPMENT_ID_DATA_AVAILABILITY_MESSAGE="No Shipment Data available for Shipment ID : ";
		
	//updateShipment
	public static final String INVALID_SHIPMENT_ID_ERROR_MESSAGE="Invalid Shipment Id : ";
	public static final String ASN_UPDATE_SUCCESS_MESSAGE="ASN Updated Succssfully...ASN ID : ";
	public static final String ASN_UPDATE_FAILURE_MESSAGE="Unable to Update ASN...Shipment ID : ";
	public static final String INVALID_FREIGHT_LINE_ID_SHIPMENT_ID_LINE_NUMBER_ERROR_MESSAGE="Invalid... Freight_Line_Id : %d Invalid Shipment Id for Freight Line Items: %d Or Invalid Item Number ";
	public static final String INVALID_SHIPMENT_NUMBER_STATUS_ORIGIN_DEST_ORG_ERROR_MESSAGE="Invalid Input passed ... Like.. Shipment Number : %s Status : %s Origin : %s Destination : %s OrganizationCode : %s";
	public static final String INVALID_SHIPMENT_NUMBER_ORIGIN_DEST_ERROR_MESSAGE="Invalid Input passed ... Like.. Shipment Number : %s Origin : %s Destination : %s ";
		
	//deleteShipmentById
	public static final String INVALID_INPUT_ERROR_MESSAGE="Invalid Input Passed";
		
	//uploadShipments
	public static final String IRRELEVANT_COLUMN_IN_CSV_FOR_EVENTS_ERROR_MESSAGE="Irrelevant column available in CSV for Events" ;
	public static final String COLUMN_MISSING_IN_CSV_FOR_EVENTS_ERROR_MESSAGE="Column Missing in CSV for Events" ;
	public static final String INVALID_COLUMNS_ERROR_MESSAGE="Column Missing in CSV for Events" ;
	public static final String NUMBER_OF_EVENTS_SUCCESS_MESSAGE="Successful...Number of Events Uploaded: " ;
	public static final String INSERT_RECORDS_ERROR_MESSAGE=" Not able to insert records.Some thing went wrong while Saving..." ;
	public static final String NO_DATA_FOUND_ERROR_MESSAGE="No data found in Template..." ;
	public static final String INSERT_RECORD_WITH_NUMBER_OF_RECORDS_ERROR_MESSAGE=" Not able to insert records. Trying to insert : %s records " ;
	public static final String INVALID_FILE_TYPE_MESSAGE="Invalid File Type for Shipment Events" ;
	public static final String INVALID_Path_TYPE_MESSAGE="Invalid File Path for Shipment Events" ;
		
	public static final String[] HEADER_ELEMENTS = new String[] { "Shipment Number*", "Shipping Company", "Buyer Name",
			"Way Bill Number", "ASN Number", "Container Id", "Tracking Number", "Status", "Delivery Date",
			"Expected Pickup Date", "Origin*", "Origin Address1", "Origin Address2", "Origin City",
			"Origin State", "Origin Zip", "Origin Country", "Destination *", "Destination Address1",
			"Destination Address2", "Destination City", "Destination State", "Destination Zip",
			"Destination Country", "Total Weight", "Transportation Method", "Shipment Number Part",
			"Shipment Number Items", "Number Of Line Items", "Line Items", "PO Number", "Item Number *",
			"Item Description", "Item Quantity", "Item Quantity UOM", "Price", "Content Description",
			"Weight Qualifier" };
	
	//getShipmentByShipmentNumberOrgCode
	public static final String SHIPMENT_NUMBER_NO_DATA_AVAILABILITY_MESSAGE="No Shipment Data available for shipmentNumber : ";
	
	//PurchaseOrderServiceImpl
	public static final String ORG_DATA_RETRIVE_SUCCESS_MESSAGE="Successfully retrieved list of Data Available for Organization code : ";
	public static final String ORG_DATA_RETRIVE_FAILURE_MESSAGE="No Data Available for  Organization code : ";
	public static final String INVALID_ORG_CODE_MESSAGE="Invalid Organization code :  ";
	public static final String EMPTY_ORG_CODE_MESSAGE="Organization code is Empty";
	public static final String PO_TRANSACTION_RETRIEVE_SUCCESS_MESSAGE="Successfully retrieved PO Transaction  for Line Item ID : %d and  Organization code : %s ";
	public static final String PO_TRANSACTION_RETRIEVE_FAILURE_MESSAGE="No PO Transaction Available for Line Item ID : %d and  Organization code : %s";
	public static final String INVALID_LINE_ID_ORG_CODE_MESSAGE="Invalid Line Item ID/Organization code :  %d / %s ";
	public static final String EMPTY_LINE_ID_ORG_CODE_MESSAGE="Line Item ID/Organization code is Empty";
	public static final String ORDER_LIST_RETRIEVE_SUCCESS_MESSAGE="Successfully retrieved List of Orders for Order ID : %d and  Organization code : %s ";
	public static final String ORDER_LIST_RETRIEVE_FAILURE_MESSAGE="No Purchase Orders Available for Order ID/Organization code : %d / %s ";
	public static final String INVALID_ORDER_ID_ORG_CODE_MESSAGE="Invalid Order ID/Organization code :  %d / %s  ";
	public static final String EMPTY_ORDER_ID_ORG_CODE_MESSAGE="Order ID/Organization code is Empty";
	public static final String ORDER_LIST_FOR_ORDER_NUMBER_ORG_CODE_SUCCESS_MESSAGE= "Successfully retrieved list of Purchase Orders Available for Order Number : %s  Organization code : %s ";
	public static final String ORDER_LIST_FOR_ORDER_NUMBER_ORG_CODE_FAILURE_MESSAGE= "No Purchase Orders Available for Order Number : %s Or Organization code : %s ";
	public static final String INVALID_ORDER_NUMBER_ORG_CODE_MESSAGE="Invalid purchase Order Number : %s  Or Organization code : %s ";
	public static final String EMPTY_ORDER_NUMBER_ORG_CODE_MESSAGE="Order Number/Organization code is Empty";
	public static final String NO_PO_AVAILABLE_MESSAGE="No Purchase Order Transactions Available";
	public static final String NO_PO_LINE_ITEMS_AVAILABLE_MESSAGE="No PO Line Items Available";
	public static final String CREATE_PO_SUCCESS_MESSAGE="Purchase Order Created Successfully...PO ID :";
	public static final String CREATE_PO_FAILURE_MESSAGE= "Unable to create Purchase Order: %d and %s" ;
	public static final String CREATE_PO_VALIDATION_FAILURE_MESSAGE= "Validation Failed To Create the Order";
	public static final String CREATE_PO_DUPLICATE_MESSAGE="Data Already Exist, kindly use UpdatePO API, to update the PO";
	public static final String EMPTY_USERNAME_ORG_CODE_MESSAGE="Username / Organization code is Empty";
	public static final String UPDATE_PO_SUCCESS_MESSAGE= "Purchase Order Updated Successfully... PO ID : ";
	public static final String UPDATE_PO_FAILURE_MESSAGE= "Unable to Update Purchase Order... PO ID : ";
	public static final String UPDATE_PO_VALIDATION_FAILURE_MESSAGE= "Validation Failed To Update the Order Based on Order ID";
	public static final String INVALID_PO_ID_ORG_CODE_MESSAGE= "Invalid Purchase Order ID : %d  / Organization Code : %s ";
	//public static final String DELETE_RECORD_SUCCESS_MESSAGE= "Successfully Record Deleted for the Id : %d  Organization_code : %s ";
	public static final String INVALID_INPUT_MESSAGE= "Invalid Input Passed";
	public static final String UPLOAD_PO_SUCCESS_ORDER_NUM_MESSAGE= "Successfully uploaded purchase order..Number of Orders Uploaded : ";
	public static final String NO_DATA_FOUND_TEMPLATE_MESSAGE= "No data found in Template...";
	public static final String DATA_RETRIEVE_ORDER_NUM_SKU_ID_ORG_SUCCESS_MESSAGE= "Successfully retrieved list of Data Available for Order Number / SKU ID / Organization code : %s / %s / %s ";
	public static final String DATA_RETRIEVE_ORDER_NUM_SKU_ID_ORG_FAILURE_MESSAGE= "No Data Available for  Order Number / SKU ID / Organization code : %s / %s /%s ";
	public static final String INVALID_ORDER_NUM_SKU_ID_ORG_FAILURE_MESSAGE= "Invalid Order Number / SKU ID / Organization code : %s / %s /%s ";
	public static final String EMPTY_ORDER_NUM_SKU_ID_ORG_FAILURE_MESSAGE= " Order Number / SKU ID / Organization code is Empty";
	public static final String INVALID_FILE_TYPE_UPLOAD_MESSAGE= "Invalid File type is uploaded";
	public static final String INVALID_FILE_NAME_TYPE_MESSAGE= "Invalid File name and type";
	public static final String PO_RECEIVED_DATE_NULL_ERROR_MESSAGE="PO Recieved Date should not be null...";
	
	//KafkaShipmentEventServiceImpl
	public static final String SENT_JSON_SHIPMENT_EVENT_DATA_SUCCESS_MESSAGE= "Sent json shipment events data to KafkaShipmentEventProducer";
	public static final String SET_STOPTYPE_MESSAGE= "Set StopType in DrayEventRequest";
	
	//KafkaShipmentServiceImpl
	public static final String SENT_JSON_SHIPMENT_DATA_SUCCESS_MESSAGE= "Sent json shipment data to KafkaShipmentProducer, as ISSHIPPED value is 1";
	public static final String SENT_JSON_SHIPMENT_DATA_FAILURE_MESSAGE= "Error!,Couldn't send json shipment data to KafkaShipmentProducer";
	public static final String SENT_JSON_SHIPMENT_DATA_IGNORE_MESSAGE= "Json Shipment data ignored, as ISSHIPPED value is 0";
	
	//ShipmentEventServiceImpl
	public static final String EVENTS_ADD_SUCCESS_MESSAGE= "Events has been successfully added";
	public static final String EVENTS_ADD_SUCCESS_WARNING_MESSAGE= "Events has been successfully added with warning.Work Order reference is not present for this shipment event";
	
	//ShipmentLegDetailsServiceImpl
	public static final String NO_SHIPMENT_LEG_DATA_SEARCH_MESSAGE= "No Shipment Leg Data is Available for the Search Criteria";
	public static final String NO_SHIPMENT_LEG_DATA_ID_MODE_MESSAGE= "No Shipment Leg Data is Available for the ID and Mode";
	public static final String SHIPMENT_WORKORDER_ID_NULL_MESSAGE= "Shipment WorkOrder ID should not be null";
	
	//ShipmentScheduleServiceImpl
	public static final String INVALID_SHIPMENT_SCHEDULE_MESSAGE= "Shipment Schedule Invalid";
	public static final String EMPTY_SHIPMENT_SCHEDULE_REQUEST_MESSAGE= "Shipment Schedule Request should not be Empty for update Shipment Schedule";
	public static final String INVALID_LANE_CODE_MESSAGE= "Lane Code Invalid";
	public static final String INVALID_ORIGIN_DESTINATION_MESSAGE= "Origin or Destination Invalid";
	public static final String INVALID_ORIGIN_MESSAGE= "Origin Invalid";
	public static final String INVALID_DESTINATION_MESSAGE= "Destination Invalid";
	public static final String INVALID_VESSEL_MESSAGE= "Vessel Name Invalid";
	public static final String DONE_MESSAGE= "done";
	public static final String COMPLETE_MESSAGE= "complete";
	
	//ShipmentServiceImpl
	public static final String INVALID_LEG_MODE_MESSAGE= "Invalid Legmode value: ";
	public static final String EMPTY_SHIPMENT_REQUEST_CREATE_MESSAGE= "Shipment Request should not be Empty for Create Shipment";
	public static final String EMPTY_SHIPMENT_REQUEST_UPDATE_MESSAGE="Shipment Request should not be Empty for update Shipment";
	public static final String EMPTY_SHIPMENTSTATUS_REQUEST_MESSAGE= "ShipmentStatusRequest should not be Empty for Update Shipment Status";
	public static final String NO_RECORD_FIND_IN_SHIPMENTREFERENCE_MESSAGE= "No Records found in ShipmentReference Table when find records based on ReferenceType and referenceValue - ShipmentIdByRefTypeValue Query run";
	
	//ShipmentStopServiceImpl
	public static final String INVALID_SHIPMENT_STOP_MESSAGE= "Shipment Stop Invalid";

	//ShipmentViewServiceImpl
	public static final String SHIPMENT_ID_NULL_MESSAGE= "Shipment ID should not be null";
	public static final String LEGMODE_DRAY= "DRAY";
	public static final String LEGMODE_OCEAN= "OCEAN";
	public static final String LEGMODE_RAIL= "RAIL";
	public static final String LEGMODE_AIR= "AIR";
	
	//ShipmentWOServiceImpl
	public static final String EMPTY_OFFICE_ID_MESSAGE= "Office Id cannot be empty ";
	public static final String UNABLE_TO_GET_WO_MESSAGE= "Unable to get workorders " ;
	public static final String INVALID_FACILITY_ID_MESSAGE= "Invalid facilityIds";
	public static final String INVALID_OFFICE_TYPE_MESSAGE= "Office type is invalid.";
	public static final String NO_RESULT_FOUND_MESSAGE= "No result found for the given input";
	public static final String SHIPMENT_DATA_NOT_AVAILABLE_FOR_SEARCH_MESSAGE= "shipment Data not available for the provided Search Criteria";
	
	//StopServiceImpl
	public static final String EMPTY_SHIPMENT_STOP_LIST_REQUEST_MESSAGE= "shipmentStopListRequest Request or List of StopRequest Request should not be Empty for Create Stop.";
	public static final String EMPTY_STOP_MAPPER_REQUEST_MESSAGE= "stopMapperRequest Request should not be Empty.";
	public static final String NULL_STOP_NUMBER_FOR_STOP_MODEL_MESSAGE= "StopNumber should not null for Stop Model.";
	public static final String RUN_TIME_ERROR_MESSAGE= "Runtime Exception occur. Program control goes to catch block.";

	//Input validation exception
	public static final String UNHABNDLED_RUNTIME_EXCEPTION= "Unhandled Runtime Exception";
	public static final String INPUT_VALIDATION_ERROR= "Input Validation Error";
}

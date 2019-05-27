package com.blumeglobal.shipmentmanagement.response;

import java.util.Date;
import java.util.List;
import java.util.Set;


public class E2EShipmentAPIResponse implements java.io.Serializable {
		
		private static final long serialVersionUID = 1L;
				
		private Long shipmentId;
		
		private String shipmentNumber;
		
		private String status;
		
		private String asnNumber;
		
		private Long shipmentNumberPart;
		
		private Long shipmentNumberTotal;
		
		private String shipmentOrderNumber;
		
		private Long numberOfUniqItems;
		
		private String shipmentType;
		
		private String consignor;
		
		private String consignee;
		
		private String shippingCompany;
		
		private String origin;
		
		private String originAddress1;
		
		private String originAddress2;
		
		private String originCity;
		
		private String originState;
		
		private String originZipCode;
		
		private String originCountry;
		
		private String originatorCode;
		
		private String originatorName;
		
		private String destination;
		
		private String destinationAddress1;
		
		
		private String destinationCity;
		
		private String destinationState;
		
		private String destinationZipCode;
		
		private String destinationCountry;
		
		private Date expectdPickupDate;
		
		private Date expectedDeliveryDate;
		
		private Long totalWeight;
		
		private String weigthUom;
		
		private Long totalVolume;
		
		private String volumeUom;
		
		private Long packageCount;
		
		private String packageType;
		
		private Long totalDeclaredValue;
		
		private String primaryMode;
		
		private String comments;
		
		private String isHazmat;
		
		private String isTemperatureController;
		
		private String sourceEntry;
		
		private String isExport;
		
		private Date podDate;
		
		private String unitId;
		
		private String billToName;
		
		private String billToAddress;
		
		private String billToCity;
		
		private String billToState;
		
		private String billToCountry;
		
		private String shipperToAddress;
		
		private String shipperToCity;
		
		private String shipperToState;
		
		private String shipperToCountry;
		
		private String consigneeToAddress;
		
		private String consineeToCity;
		
		private String consingeeToState;
		
		private String consigneeToCountry;
		
		private String createdBy;
		
		private Date createdDateTime;
		
		private String updatedBy;
		
		private Date updatedDateTime;
		
		private String organizationCode;
		
		private String transportationMethod;
		
		private String wayBillNumber;
		
		private String trackingNumber;
		
		private String deleteFlag;
		
		private Set<FreightLineItemsAPIResponse> freightLineItems;
		
		private List<ShipmentOrderLineResponse> shipmentOrderLines;
		
		public E2EShipmentAPIResponse() {
			
		}

		public Long getShipmentId() {
			return shipmentId;
		}

		public void setShipmentId(Long shipmentId) {
			this.shipmentId = shipmentId;
		}

		public String getShipmentNumber() {
			return shipmentNumber;
		}

		public void setShipmentNumber(String shipmentNumber) {
			this.shipmentNumber = shipmentNumber;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		

		public String getAsnNumber() {
			return asnNumber;
		}

		public void setAsnNumber(String asnNumber) {
			this.asnNumber = asnNumber;
		}

		public Long getShipmentNumberPart() {
			return shipmentNumberPart;
		}

		public void setShipmentNumberPart(Long shipmentNumberPart) {
			this.shipmentNumberPart = shipmentNumberPart;
		}

		public Long getShipmentNumberTotal() {
			return shipmentNumberTotal;
		}

		public void setShipmentNumberTotal(Long shipmentNumberTotal) {
			this.shipmentNumberTotal = shipmentNumberTotal;
		}

		public String getShipmentOrderNumber() {
			return shipmentOrderNumber;
		}

		public void setShipmentOrderNumber(String shipmentOrderNumber) {
			this.shipmentOrderNumber = shipmentOrderNumber;
		}

		public Long getNumberOfUniqItems() {
			return numberOfUniqItems;
		}

		public void setNumberOfUniqItems(Long numberOfUniqItems) {
			this.numberOfUniqItems = numberOfUniqItems;
		}

		public String getShipmentType() {
			return shipmentType;
		}

		public void setShipmentType(String shipmentType) {
			this.shipmentType = shipmentType;
		}

		public String getConsignor() {
			return consignor;
		}

		public void setConsignor(String consignor) {
			this.consignor = consignor;
		}

		public String getConsignee() {
			return consignee;
		}

		public void setConsignee(String consignee) {
			this.consignee = consignee;
		}

		public String getShippingCompany() {
			return shippingCompany;
		}

		public void setShippingCompany(String shippingCompany) {
			this.shippingCompany = shippingCompany;
		}

		public String getOrigin() {
			return origin;
		}

		public void setOrigin(String origin) {
			this.origin = origin;
		}

		public String getOriginAddress1() {
			return originAddress1;
		}

		public void setOriginAddress1(String originAddress1) {
			this.originAddress1 = originAddress1;
		}

		public String getOriginAddress2() {
			return originAddress2;
		}

		public void setOriginAddress2(String originAddress2) {
			this.originAddress2 = originAddress2;
		}

		public String getOriginCity() {
			return originCity;
		}

		public void setOriginCity(String originCity) {
			this.originCity = originCity;
		}

		public String getOriginState() {
			return originState;
		}

		public void setOriginState(String originState) {
			this.originState = originState;
		}

		public String getOriginZipCode() {
			return originZipCode;
		}

		public void setOriginZipCode(String originZipCode) {
			this.originZipCode = originZipCode;
		}

		public String getOriginCountry() {
			return originCountry;
		}

		public void setOriginCountry(String originCountry) {
			this.originCountry = originCountry;
		}

		public String getOriginatorCode() {
			return originatorCode;
		}

		public void setOriginatorCode(String originatorCode) {
			this.originatorCode = originatorCode;
		}

		public String getOriginatorName() {
			return originatorName;
		}

		public void setOriginatorName(String originatorName) {
			this.originatorName = originatorName;
		}

		public String getDestination() {
			return destination;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public String getDestinationAddress1() {
			return destinationAddress1;
		}

		public void setDestinationAddress1(String destinationAddress1) {
			this.destinationAddress1 = destinationAddress1;
		}

		public String getDestinationCity() {
			return destinationCity;
		}

		public void setDestinationCity(String destinationCity) {
			this.destinationCity = destinationCity;
		}

		public String getDestinationState() {
			return destinationState;
		}

		public void setDestinationState(String destinationState) {
			this.destinationState = destinationState;
		}

		public String getDestinationZipCode() {
			return destinationZipCode;
		}

		public void setDestinationZipCode(String destinationZipCode) {
			this.destinationZipCode = destinationZipCode;
		}

		public String getDestinationCountry() {
			return destinationCountry;
		}

		public void setDestinationCountry(String destinationCountry) {
			this.destinationCountry = destinationCountry;
		}

		public Date getExpectdPickupDate() {
			return expectdPickupDate;
		}

		public void setExpectdPickupDate(Date expectdPickupDate) {
			this.expectdPickupDate = expectdPickupDate;
		}

		public Date getExpectedDeliveryDate() {
			return expectedDeliveryDate;
		}

		public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
			this.expectedDeliveryDate = expectedDeliveryDate;
		}

		public Long getTotalWeight() {
			return totalWeight;
		}

		public void setTotalWeight(Long totalWeight) {
			this.totalWeight = totalWeight;
		}

		public String getWeigthUom() {
			return weigthUom;
		}

		public void setWeigthUom(String weigthUom) {
			this.weigthUom = weigthUom;
		}

		public Long getTotalVolume() {
			return totalVolume;
		}

		public void setTotalVolume(Long totalVolume) {
			this.totalVolume = totalVolume;
		}

		public String getVolumeUom() {
			return volumeUom;
		}

		public void setVolumeUom(String volumeUom) {
			this.volumeUom = volumeUom;
		}

		public Long getPackageCount() {
			return packageCount;
		}

		public void setPackageCount(Long packageCount) {
			this.packageCount = packageCount;
		}

		public String getPackageType() {
			return packageType;
		}

		public void setPackageType(String packageType) {
			this.packageType = packageType;
		}

		public Long getTotalDeclaredValue() {
			return totalDeclaredValue;
		}

		public void setTotalDeclaredValue(Long totalDeclaredValue) {
			this.totalDeclaredValue = totalDeclaredValue;
		}

		public String getPrimaryMode() {
			return primaryMode;
		}

		public void setPrimaryMode(String primaryMode) {
			this.primaryMode = primaryMode;
		}

		public String getComments() {
			return comments;
		}

		public void setComments(String comments) {
			this.comments = comments;
		}

		public String getIsHazmat() {
			return isHazmat;
		}

		public void setIsHazmat(String isHazmat) {
			this.isHazmat = isHazmat;
		}

		public String getIsTemperatureController() {
			return isTemperatureController;
		}

		public void setIsTemperatureController(String isTemperatureController) {
			this.isTemperatureController = isTemperatureController;
		}

		public String getSourceEntry() {
			return sourceEntry;
		}

		public void setSourceEntry(String sourceEntry) {
			this.sourceEntry = sourceEntry;
		}

		public String getIsExport() {
			return isExport;
		}

		public void setIsExport(String isExport) {
			this.isExport = isExport;
		}

		public Date getPodDate() {
			return podDate;
		}

		public void setPodDate(Date podDate) {
			this.podDate = podDate;
		}

		public String getUnitId() {
			return unitId;
		}

		public void setUnitId(String unitId) {
			this.unitId = unitId;
		}

		public String getBillToName() {
			return billToName;
		}

		public void setBillToName(String billToName) {
			this.billToName = billToName;
		}

		public String getBillToAddress() {
			return billToAddress;
		}

		public void setBillToAddress(String billToAddress) {
			this.billToAddress = billToAddress;
		}

		public String getBillToCity() {
			return billToCity;
		}

		public void setBillToCity(String billToCity) {
			this.billToCity = billToCity;
		}

		public String getBillToState() {
			return billToState;
		}

		public void setBillToState(String billToState) {
			this.billToState = billToState;
		}

		public String getBillToCountry() {
			return billToCountry;
		}

		public void setBillToCountry(String billToCountry) {
			this.billToCountry = billToCountry;
		}

		public String getShipperToAddress() {
			return shipperToAddress;
		}

		public void setShipperToAddress(String shipperToAddress) {
			this.shipperToAddress = shipperToAddress;
		}

		public String getShipperToCity() {
			return shipperToCity;
		}

		public void setShipperToCity(String shipperToCity) {
			this.shipperToCity = shipperToCity;
		}

		public String getShipperToState() {
			return shipperToState;
		}

		public void setShipperToState(String shipperToState) {
			this.shipperToState = shipperToState;
		}

		public String getShipperToCountry() {
			return shipperToCountry;
		}

		public void setShipperToCountry(String shipperToCountry) {
			this.shipperToCountry = shipperToCountry;
		}

		public String getConsigneeToAddress() {
			return consigneeToAddress;
		}

		public void setConsigneeToAddress(String consigneeToAddress) {
			this.consigneeToAddress = consigneeToAddress;
		}

		public String getConsineeToCity() {
			return consineeToCity;
		}

		public void setConsineeToCity(String consineeToCity) {
			this.consineeToCity = consineeToCity;
		}

		public String getConsingeeToState() {
			return consingeeToState;
		}

		public void setConsingeeToState(String consingeeToState) {
			this.consingeeToState = consingeeToState;
		}

		public String getConsigneeToCountry() {
			return consigneeToCountry;
		}

		public void setConsigneeToCountry(String consigneeToCountry) {
			this.consigneeToCountry = consigneeToCountry;
		}

		public String getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}

		public Date getCreatedDateTime() {
			return createdDateTime;
		}

		public void setCreatedDateTime(Date createdDateTime) {
			this.createdDateTime = createdDateTime;
		}

		public String getUpdatedBy() {
			return updatedBy;
		}

		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}

		public Date getUpdatedDateTime() {
			return updatedDateTime;
		}

		public void setUpdatedDateTime(Date updatedDateTime) {
			this.updatedDateTime = updatedDateTime;
		}

		

		public String getOrganizationCode() {
			return organizationCode;
		}

		public void setOrganizationCode(String organizationCode) {
			this.organizationCode = organizationCode;
		}

		public String getTransportationMethod() {
			return transportationMethod;
		}

		public void setTransportationMethod(String transportationMethod) {
			this.transportationMethod = transportationMethod;
		}

		public String getWayBillNumber() {
			return wayBillNumber;
		}

		public void setWayBillNumber(String wayBillNumber) {
			this.wayBillNumber = wayBillNumber;
		}

		public String getTrackingNumber() {
			return trackingNumber;
		}

		public void setTrackingNumber(String trackingNumber) {
			this.trackingNumber = trackingNumber;
		}

		

		public String getDeleteFlag() {
			return deleteFlag;
		}

		public void setDeleteFlag(String deleteFlag) {
			this.deleteFlag = deleteFlag;
		}

		public Set<FreightLineItemsAPIResponse> getFreightLineItems() {
			return freightLineItems;
		}

		public void setFreightLineItems(Set<FreightLineItemsAPIResponse> freightLineItems) {
			this.freightLineItems = freightLineItems;
		}

		public List<ShipmentOrderLineResponse> getShipmentOrderLines() {
			return shipmentOrderLines;
		}

		public void setShipmentOrderLines(List<ShipmentOrderLineResponse> shipmentOrderLines) {
			this.shipmentOrderLines = shipmentOrderLines;
		}

		}

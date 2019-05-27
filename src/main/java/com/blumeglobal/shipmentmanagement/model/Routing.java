package com.blumeglobal.shipmentmanagement.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ROUTING")
public class Routing {
	private static final long serialVersionUID = 1L;
	
	@Id
	//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lane_id")
	//	@SequenceGenerator(name = "seq_lane_id", allocationSize = 1, sequenceName = "SEQ_LANE_ID")
	@Column(name = "ROUTINGID", nullable = false, updatable = false, length = 50)
	private String routingid;
	//	ROUTINGID	VARCHAR2(50 BYTE)
	
	@Column(name = "MODEOFTRANSPORT", length = 20)
	private String modeOfTransport;
	//	MODEOFTRANSPORT	VARCHAR2(20 BYTE)
	
	@Column(name = "ORIGINLOCATIONID", length = 200)
	private String originLocationId;
	//	ORIGINLOCATIONID	VARCHAR2(200 BYTE)
	
	@Column(name = "DESTINATIONLOCATIONID", length = 200)
	private String destinationLocationId;
	//	DESTINATIONLOCATIONID	VARCHAR2(200 BYTE)
	
	@Column(name = "TRANSITTIME")
	private Double transitTime;
	//	TRANSITTIME	NUMBER(18,0)
	
	@Column(name = "UOM", length = 50)
	private String uom;
	//	UOM	VARCHAR2(50 BYTE)
	
	@Column(name = "LANEGROUPID", length = 50)
	private String laneGroupId;
	//	LANEGROUPID	VARCHAR2(50 BYTE)
	
	@Column(name = "ROUTEDESCRIPTION", length = 200)
	private String routeDescription;
	//	ROUTEDESCRIPTION	VARCHAR2(200 BYTE)
	
	@Column(name = "EQUIPMENTCLASS", length = 50)
	private String equipmentClass;
	//	EQUIPMENTCLASS	VARCHAR2(50 BYTE)
	
	@Column(name = "EQUIPMENT_SNT", length = 50)
	private String equipment_snt;
	//	EQUIPMENT_SNT	VARCHAR2(50 BYTE)
	
	@Column(name = "MAXIMUMWEIGHT")
	private Long maximumWeight;
	//	MAXIMUMWEIGHT	NUMBER
	
	@Column(name = "MAXIMUMVOLUME")
	private Long maximumVolume;
	//	MAXIMUMVOLUME	NUMBER
	
	@Column(name = "COMMODITY", length = 50)
	private String commodity;
	//	COMMODITY	VARCHAR2(50 BYTE)
	
	@Column(name = "TOTALCOST")
	private Long totalCost;
	//	TOTALCOST	NUMBER
	
	@Column(name = "ISACTIVE", length = 1)
	private String isActive;
	//	ISACTIVE	CHAR(1 BYTE)
	
	@Column(name = "STOPDURATION")
	private Long stopDuration;
	//	STOPDURATION	NUMBER
	
	@Column(name = "DEFAULT_CARRIER", length = 50)
	private String default_carrier;
	//	DEFAULT_CARRIER	VARCHAR2(50 BYTE)
	
	@Column(name = "SELECTIONCRITERIA", length = 50)
	private String selectionCriteria;
	//	SELECTIONCRITERIA	VARCHAR2(50 BYTE)
	
	@Column(name = "CAPACITY_BASIS", length = 50)
	private String capacity_basis;
	//	CAPACITY_BASIS	VARCHAR2(50 BYTE)
	
	@Column(name = "TOWNCROSSING", length = 1)
	private String townCrossing;
	//	TOWNCROSSING	CHAR(1 BYTE)
	
	
	@OneToMany(mappedBy = "routingLink", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<BookingDetails> bookingDetails;
	
	
	public Routing() {
		
	}


	public String getRoutingid() {
		return routingid;
	}


	public void setRoutingid(String routingid) {
		this.routingid = routingid;
	}


	public String getModeOfTransport() {
		return modeOfTransport;
	}


	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}


	public String getOriginLocationId() {
		return originLocationId;
	}


	public void setOriginLocationId(String originLocationId) {
		this.originLocationId = originLocationId;
	}


	public String getDestinationLocationId() {
		return destinationLocationId;
	}


	public void setDestinationLocationId(String destinationLocationId) {
		this.destinationLocationId = destinationLocationId;
	}


	public Double getTransitTime() {
		return transitTime;
	}


	public void setTransitTime(Double transitTime) {
		this.transitTime = transitTime;
	}


	public String getUom() {
		return uom;
	}


	public void setUom(String uom) {
		this.uom = uom;
	}


	public String getLaneGroupId() {
		return laneGroupId;
	}


	public void setLaneGroupId(String laneGroupId) {
		this.laneGroupId = laneGroupId;
	}


	public String getRouteDescription() {
		return routeDescription;
	}


	public void setRouteDescription(String routeDescription) {
		this.routeDescription = routeDescription;
	}


	public String getEquipmentClass() {
		return equipmentClass;
	}


	public void setEquipmentClass(String equipmentClass) {
		this.equipmentClass = equipmentClass;
	}


	public String getEquipment_snt() {
		return equipment_snt;
	}


	public void setEquipment_snt(String equipment_snt) {
		this.equipment_snt = equipment_snt;
	}


	public Long getMaximumWeight() {
		return maximumWeight;
	}


	public void setMaximumWeight(Long maximumWeight) {
		this.maximumWeight = maximumWeight;
	}


	public Long getMaximumVolume() {
		return maximumVolume;
	}


	public void setMaximumVolume(Long maximumVolume) {
		this.maximumVolume = maximumVolume;
	}


	public String getCommodity() {
		return commodity;
	}


	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}


	public Long getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(Long totalCost) {
		this.totalCost = totalCost;
	}


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	public Long getStopDuration() {
		return stopDuration;
	}


	public void setStopDuration(Long stopDuration) {
		this.stopDuration = stopDuration;
	}


	public String getDefault_carrier() {
		return default_carrier;
	}


	public void setDefault_carrier(String default_carrier) {
		this.default_carrier = default_carrier;
	}


	public String getSelectionCriteria() {
		return selectionCriteria;
	}


	public void setSelectionCriteria(String selectionCriteria) {
		this.selectionCriteria = selectionCriteria;
	}


	public String getCapacity_basis() {
		return capacity_basis;
	}


	public void setCapacity_basis(String capacity_basis) {
		this.capacity_basis = capacity_basis;
	}


	public String getTownCrossing() {
		return townCrossing;
	}


	public void setTownCrossing(String townCrossing) {
		this.townCrossing = townCrossing;
	}


	public Set<BookingDetails> getBookingDetails() {
		return bookingDetails;
	}


	public void setBookingDetails(Set<BookingDetails> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}
	
}

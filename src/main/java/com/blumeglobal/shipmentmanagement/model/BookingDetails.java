package com.blumeglobal.shipmentmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "BOOKINGDETAILS")
public class BookingDetails {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	@Column(name = "BOOKINGDETAILSID", length = 50)
	private Long bookingDetailsId;
	//	BOOKINGDETAILSID	VARCHAR2(50 BYTE)
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BOOKINGID", nullable = false)
	private Booking bookingLink;
	//	BOOKINGID	NUMBER
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LANEID", nullable = false)
	private Lane laneLink;
	//	LANEID	VARCHAR2(50 BYTE)
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LANEGROUPID", nullable = false)
	private LaneGroups laneGroupsLink;
	//	LANEGROUPID	VARCHAR2(50 BYTE)
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROUTINGID", nullable = false)
	private Routing routingLink;
	//	ROUTINGID	VARCHAR2(50 BYTE)
	
	
	@Column(name = "WORKORDERS", length = 200)
	private String workOrders;
	//	WORKORDERS	VARCHAR2(200 BYTE)
	
	
	@Column(name = "DEFAULTCARRIER", length = 50)
	private String defaultCarrier;
	//	DEFAULTCARRIER	VARCHAR2(50 BYTE)
	
	
	@Column(name = "CREATEDBY", length = 50)
	private String createdBy;
	//	CREATEDBY	VARCHAR2(50 BYTE)
	

	@Column(name = "ROUTEORIGINLOCATIONID", length = 200)
	private String routeOriginLocationId;
	//	ROUTEORIGINLOCATIONID	VARCHAR2(200 BYTE)
	
	
	@Column(name = "ROUTEDESTINATIONLOCATIONID", length = 200)
	private String routeDestinationLocationId;
	//	ROUTEDESTINATIONLOCATIONID	VARCHAR2(200 BYTE)
	
	
	@Column(name = "ROUTEMODEOFTRANSPORT", length = 50)
	private String routeModeOfTransport;
	//	ROUTEMODEOFTRANSPORT	VARCHAR2(50 BYTE)
	
	
	//	@Type(type="yes_no")
	@Column(name = "SYSTEMRECOMMENDEDROUTE", length = 1)
	private String systemRecommendedRoute;
	//	SYSTEMRECOMMENDEDROUTE	CHAR(1 BYTE)
	
	
	public BookingDetails() {
		
	}


	public Long getBookingDetailsId() {
		return bookingDetailsId;
	}


	public void setBookingDetailsId(Long bookingDetailsId) {
		this.bookingDetailsId = bookingDetailsId;
	}


	public Booking getBookingLink() {
		return bookingLink;
	}


	public void setBookingLink(Booking bookingLink) {
		this.bookingLink = bookingLink;
	}


	public Lane getLaneLink() {
		return laneLink;
	}


	public void setLaneLink(Lane laneLink) {
		this.laneLink = laneLink;
	}


	public LaneGroups getLaneGroupsLink() {
		return laneGroupsLink;
	}


	public void setLaneGroupsLink(LaneGroups laneGroupsLink) {
		this.laneGroupsLink = laneGroupsLink;
	}


	public Routing getRoutingLink() {
		return routingLink;
	}


	public void setRoutingLink(Routing routingLink) {
		this.routingLink = routingLink;
	}


	public String getWorkOrders() {
		return workOrders;
	}


	public void setWorkOrders(String workOrders) {
		this.workOrders = workOrders;
	}


	public String getDefaultCarrier() {
		return defaultCarrier;
	}


	public void setDefaultCarrier(String defaultCarrier) {
		this.defaultCarrier = defaultCarrier;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getRouteOriginLocationId() {
		return routeOriginLocationId;
	}


	public void setRouteOriginLocationId(String routeOriginLocationId) {
		this.routeOriginLocationId = routeOriginLocationId;
	}


	public String getRouteDestinationLocationId() {
		return routeDestinationLocationId;
	}


	public void setRouteDestinationLocationId(String routeDestinationLocationId) {
		this.routeDestinationLocationId = routeDestinationLocationId;
	}


	public String getRouteModeOfTransport() {
		return routeModeOfTransport;
	}


	public void setRouteModeOfTransport(String routeModeOfTransport) {
		this.routeModeOfTransport = routeModeOfTransport;
	}


	public String getSystemRecommendedRoute() {
		return systemRecommendedRoute;
	}


	public void setSystemRecommendedRoute(String systemRecommendedRoute) {
		this.systemRecommendedRoute = systemRecommendedRoute;
	}

}

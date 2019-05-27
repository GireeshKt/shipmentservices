package com.blumeglobal.shipmentmanagement.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ShipmentSchedule entity.
 */

@Entity
@Table(name = "SAILINGSCHEDULES")

public class ShipmentSchedule implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	@Column(name = "SAILINGSCHEDULEID")
	private Long shipmentScheduleId;
	
	@Column(name = "ORIGINLOCATIONID", length = 200)
	private String originLocationId;
	
	@Column(name = "DESTINATIONLOCATIONID", length = 200)
	private String destinationLocationId;
	
	@Column(name = "VESSEL", length = 50)
	private String vessel;
	
	@Column(name = "VOYAGE", length = 50)
	private String voyage;
	
	@Column(name = "ARRIVALDATE", length = 7)
	private Date arrivalDate;
	
	@Column(name = "DEPARTUREDATE", length = 7)
	private Date departureDate;
	
	@Column(name = "CARRIERNAME", length = 100)
	private String carrierName;
	
	@Column(name = "SCHEDULESOURCE", length = 100)
	private String scheduleSource;
	
	@Column(name = "SERVICELANE", length = 100)
	private String serviceLane;
	
	public ShipmentSchedule() {
		super();
	}
	
	public Long getshipmentScheduleId() {
		return shipmentScheduleId;
	}

	public void setshipmentScheduleId(Long shipmentScheduleId) {
		this.shipmentScheduleId = shipmentScheduleId;
	}
	
	public String getoriginLocationId() {
		return originLocationId;
	}

	public void setoriginLocationId(String originLocationId) {
		this.originLocationId = originLocationId;
	}
	
	public String getdestinationLocationId() {
		return destinationLocationId;
	}

	public void setdestinationLocationId(String destinationLocationId) {
		this.destinationLocationId = destinationLocationId;
	}
	
	public String getvessel() {
		return vessel;
	}

	public void setvessel(String vessel) {
		this.vessel = vessel;
	}
	
	public String getvoyage() {
		return voyage;
	}

	public void setvoyage(String voyage) {
		this.voyage = voyage;
	}
	
	public Date getarrivalDate() {
		return arrivalDate;
	}

	public void setarrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	public Date getdepartureDate() {
		return departureDate;
	}

	public void setdepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	
	public String getcarrierName() {
		return carrierName;
	}

	public void setcarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	
	public String getscheduleSource() {
		return scheduleSource;
	}

	public void setscheduleSource(String scheduleSource) {
		this.scheduleSource = scheduleSource;
	}
	
	public String getserviceLane() {
		return serviceLane;
	}

	public void setserviceLane(String serviceLane) {
		this.serviceLane = serviceLane;
	}
}
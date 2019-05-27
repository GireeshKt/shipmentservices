package com.blumeglobal.shipmentmanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SHIPMENTNOTIFICATION")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ShipmentNotification {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	@Column(name = "NOTIFICATIONID", nullable = false, updatable = false)
	private Long notificationId;

	@Column(name = "ORIGINATORID")
	private Long originatorId;
	
	@Column(name = "ORIGINATORCODE", length = 50)
	private String originatorCode;

	@Column(name = "SHIPPER", length = 50)
	private String shipper;

	@Column(name = "ISSHIPMENT", length = 1)
	private Long isShipment;

	@Column(name = "STATUS", length = 50)
	private String status;
	
	@Column(name = "CREATEDDATE")
	private Date createdDate;
	
	@Column(name = "CREATEDBY", length = 50)
	private String createdBy;
	
	public ShipmentNotification() {
		
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public Long getOriginatorId() {
		return originatorId;
	}

	public void setOriginatorId(Long originatorId) {
		this.originatorId = originatorId;
	}

	public String getOriginatorCode() {
		return originatorCode;
	}

	public void setOriginatorCode(String originatorCode) {
		this.originatorCode = originatorCode;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public Long getIsShipment() {
		return isShipment;
	}

	public void setIsShipment(Long isShipment) {
		this.isShipment = isShipment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

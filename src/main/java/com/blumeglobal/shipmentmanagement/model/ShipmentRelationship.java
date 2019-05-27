package com.blumeglobal.shipmentmanagement.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="SHIPMENTRELATIONSHIP")
public class ShipmentRelationship implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	@Column(name = "SHIPMENTRELATIONSHIPID")
	private Long shipmentRelationShipId;
		
	@Column(name = "SHIPMENTID")
	private Long shipmentId;
	
	@Column(name = "PARENTSHIPMENTID")
	private Long parentId;
	
	@Column(name = "CREATEDBY")
	private String createdBy;
	
	@Column(name = "CREATEDATE")
	private Date createDate;
	
	@Column(name = "LASTMODIFIEDBY")
	private String lastModifiedBy;
	
	@Column(name = "LASTMODIFIED")
	private Date lastModified;
	
	
	public ShipmentRelationship() {
		super();
	}

	public Long getShipmentRelationShipId() {
		return shipmentRelationShipId;
	}


	public void setShipmentRelationShipId(Long shipmentRelationShipId) {
		this.shipmentRelationShipId = shipmentRelationShipId;
	}

	public Long getParentId() {
		return parentId;
	}


	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getLastModifiedBy() {
		return lastModifiedBy;
	}


	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}


	public Date getLastModified() {
		return lastModified;
	}


	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Long getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}
	
}

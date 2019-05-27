package com.blumeglobal.shipmentmanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * EventClass entity.
 */
@Entity
@Table(name = "EVENTCLASS")
public class EventClass implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "EVENTCLASSID")
	private Long eventClassId;
	@Column(name = "EVENTCODE")
	private String eventCode;
	@Column(name = "EVENTNAME")
	private String eventName;
	@Column(name = "EVENTDESC")
	private String eventDesc;
	@Column(name = "CATEGORY")
	private String category;
	@Column(name = "ORIGINATORID")
	private Long originatorId;
	@Column(name = "ORIGINATORCODE")
	private String originatorCode;
	@Column(name = "ORIGINATORNAME")
	private String originatorName;
	@Column(name = "EFFECTIVEDATE")
	private Date effectiveDate;
	@Column(name = "EXPIREDATE",insertable=false,updatable=false)
	private Date expireDate;
	@Column(name = "CREATEDBY")
	private String createdBy;
	@Column(name = "CREATEDATE")
	private Date createDate;
	@Column(name = "LASTMODIFIEDBY")
	private String lastModifiedBy;
	@Column(name = "LASTMODIFYDATE")
	private Date lastModifyDate;
	@Column(name = "VERSION")
	private Long version;
	@Column(name = "PUBLISHERCODE")
	private String publisherCode;
	@Column(name = "ISFORWARDREQUESTED")
	private Long isForwardRequested;
	@Column(name = "ISVALIDATIONREQUIRED")
	private Long isValidationRequired; 
	
	public EventClass() {
		
	}
	public Long getEventClassId() {
		return eventClassId;
	}
	public void setEventClassId(Long eventClassId) {
		this.eventClassId = eventClassId;
	}
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public String getOriginatorName() {
		return originatorName;
	}
	public void setOriginatorName(String originatorName) {
		this.originatorName = originatorName;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
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
	public Date getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getPublisherCode() {
		return publisherCode;
	}
	public void setPublisherCode(String publisherCode) {
		this.publisherCode = publisherCode;
	}
	public Long getIsForwardRequested() {
		return isForwardRequested;
	}
	public void setIsForWardRequested(Long isForwardRequested) {
		this.isForwardRequested = isForwardRequested;
	}
	public Long getIsValidationRequired() {
		return isValidationRequired;
	}
	public void setIsValidationRequired(Long isValidationRequired) {
		this.isValidationRequired = isValidationRequired;
	}
	
	
}

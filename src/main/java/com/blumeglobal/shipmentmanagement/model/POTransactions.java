package com.blumeglobal.shipmentmanagement.model;

import java.sql.Timestamp;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PO_TRANSACTIONS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class POTransactions implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	@Column(name = "PO_TRANSACTION_ID", nullable = false, updatable = false)
	private Long poTransactionID;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PO_ITEM_ID", nullable = false)	
	private POLineItems poTransactionRef;
	
	@Column(name = "PO_STATUS")
	private String poStatus;
	
	@Column(name = "PO_TRANSACTION_STATUS")
	private String poTransactionStatus;
	
	@Column(name = "PO_TRANSACTION_DATE")
	private Timestamp poTransactionDate;
	
	@Column(name = "CREATED_ON",updatable = false)
	private Timestamp createdOn;

	@Column(name = "CREATED_BY", length = 180)
	private String createdBy;
	
	@Column(name = "UPDATED_ON")
	private Timestamp updatedOn;

	@Column(name = "UPDATED_BY", length = 180)
	private String updatedBy;
	
	@Column(name = "ORGANIZATION_CODE")
	private String organizationCode;
	
	@Column(name = "DELETE_FLAG")
	private String deleteFlag;

	public Long getPoTransactionID() {
		return poTransactionID;
	}

	public void setPoTransactionID(Long poTransactionID) {
		this.poTransactionID = poTransactionID;
	}

	public POLineItems getPoTransactionRef() {
		return poTransactionRef;
	}

	public void setPoTransactionRef(POLineItems poTransactionRef) {
		this.poTransactionRef = poTransactionRef;
	}

	public String getPoStatus() {
		return poStatus;
	}

	public void setPoStatus(String poStatus) {
		this.poStatus = poStatus;
	}

	public String getPoTransactionStatus() {
		return poTransactionStatus;
	}

	public void setPoTransactionStatus(String poTransactionStatus) {
		this.poTransactionStatus = poTransactionStatus;
	}

	public Timestamp getPoTransactionDate() {
		return poTransactionDate;
	}

	public void setPoTransactionDate(Timestamp poTransactionDate) {
		this.poTransactionDate = poTransactionDate;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}

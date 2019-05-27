package com.blumeglobal.shipmentmanagement.model;

import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name = "CHARGE")
public class Charge implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "CHARGEID", nullable = false, updatable = false)
    private Long chargeId;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "BILLTO")
    private String billTo;

    @Column(name = "COMMENTS")
    private String commments;

    @Column(name = "CURRENCY")
    private String currency;
    
    @Column(name = "BASECHARGE")
    private Double baseCharge;

    @Column(name = "FSCAMOUNT")
    private Double fscAmount;

    @Column(name = "FSCPERCENT")
    private Double fscPercent;

    @Column(name = "INVOICEABLEDATE")
    private Date invoiceableDate;

    @Column(name = "ISBILLABLE")
    private String isBillable;

    @Column(name = "ISINVOICED")
    private String isInvoiced;

    @Column(name = "SERVICECODE")
    private String serviceCode;

    @Column(name = "STOPID")
    private Long stopId;

    @Column(name = "EQUIPMENTONSHIPMENTID", insertable=false, updatable=false)
    private Long equipmentOnShipmentId;
    

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EQUIPMENTONSHIPMENTID", nullable = false)
    private EquipmentOnShipment equipmentOnShipment;

    @Column(name = "TRANSACTIONNUMBER")
    private String transactionNumber;

    @Column(name = "SOURCETYPE")
    private String sourceType;

    @Column(name = "CREATEDBY")
    private String createdBy;

    @Column(name = "CREATEDATE")
    private Date createdDate;

    @Column(name = "LASTMODIFIEDBY")
    private String lastModifiedBy;

    @Column(name = "LASTMODIFIED")
    private Date lastModified;

    public Long getChargeId() {
        return chargeId;
    }

    public void setChargeId(Long chargeId) {
        this.chargeId = chargeId;
    }


    public Double getAmount() {
        return amount;
    }


    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public String getBillTo() {
        return billTo;
    }


    public void setBillTo(String billTo) {
        this.billTo = billTo;
    }


    public String getCommments() {
        return commments;
    }


    public void setCommments(String commments) {
        this.commments = commments;
    }


    public String getCurrency() {
        return currency;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public Double getFscAmount() {
        return fscAmount;
    }


    public void setFscAmount(Double fscAmount) {
        this.fscAmount = fscAmount;
    }


    public Double getFscPercent() {
        return fscPercent;
    }


    public void setFscPercent(Double fscPercent) {
        this.fscPercent = fscPercent;
    }


    public Date getInvoiceableDate() {
        return invoiceableDate;
    }


    public void setInvoiceableDate(Date invoiceableDate) {
        this.invoiceableDate = invoiceableDate;
    }


    public String getIsBillable() {
        return isBillable;
    }


    public void setIsBillable(String isBillable) {
        this.isBillable = isBillable;
    }


    public String getIsInvoiced() {
        return isInvoiced;
    }


    public void setIsInvoiced(String isInvoiced) {
        this.isInvoiced = isInvoiced;
    }


    public String getServiceCode() {
        return serviceCode;
    }


    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }


    public Long getStopId() {
        return stopId;
    }


    public void setStopId(Long stopId) {
        this.stopId = stopId;
    }

    public Long getEquipmentOnShipmentId() {
        return equipmentOnShipmentId;
    }

    public void setEquipmentOnShipmentId(Long equipmentOnShipmentId) {
        this.equipmentOnShipmentId = equipmentOnShipmentId;
    }

    public EquipmentOnShipment getEquipmentOnShipment() {
        return equipmentOnShipment;
    }

    public void setEquipmentOnShipment(EquipmentOnShipment equipmentOnShipment) {
        this.equipmentOnShipment = equipmentOnShipment;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }


    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }


    public String getSourceType() {
        return sourceType;
    }


    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }


    public String getCreatedBy() {
        return createdBy;
    }


    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    public Date getCreatedDate() {
        return createdDate;
    }


    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public Double getBaseCharge() {
        return baseCharge;
    }

    public void setBaseCharge(Double baseCharge) {
        this.baseCharge = baseCharge;
    }



}

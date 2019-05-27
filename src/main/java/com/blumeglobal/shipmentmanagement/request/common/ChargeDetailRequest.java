package com.blumeglobal.shipmentmanagement.request.common;

import java.io.Serializable;
import java.util.Date;

public class ChargeDetailRequest implements Serializable{

    private static final long serialVersionUID = 1L;
    private Double baseCharge;
    private Double freightCharge;
    private Double fuelSurchargeTotal;
    private String currency;
    private String comments;
    private String billTo;
    private Double fuelSurchagePercent;
    private Date invoiceableDate;
    private String isBillable;
    private String isInvoiced;
    private String serviceCode;
    private String stopName;
    private String transactionNumber;
    private String sourceType;
    
    public Double getBaseCharge() {
        return baseCharge;
    }
    public void setBaseCharge(Double baseCharge) {
        this.baseCharge = baseCharge;
    }
    public Double getFreightCharge() {
        return freightCharge;
    }
    public void setFreightCharge(Double freightCharge) {
        this.freightCharge = freightCharge;
    }
    public Double getFuelSurchargeTotal() {
        return fuelSurchargeTotal;
    }
    public void setFuelSurchargeTotal(Double fuelSurchargeTotal) {
        this.fuelSurchargeTotal = fuelSurchargeTotal;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getBillTo() {
        return billTo;
    }
    public void setBillTo(String billTo) {
        this.billTo = billTo;
    }
    public Double getFuelSurchagePercent() {
        return fuelSurchagePercent;
    }
    public void setFuelSurchagePercent(Double fuelSurchagePercent) {
        this.fuelSurchagePercent = fuelSurchagePercent;
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
    public String getStopName() {
        return stopName;
    }
    public void setStopName(String stopName) {
        this.stopName = stopName;
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

}

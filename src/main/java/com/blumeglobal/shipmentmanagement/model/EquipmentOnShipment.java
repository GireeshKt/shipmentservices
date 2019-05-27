package com.blumeglobal.shipmentmanagement.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "EQUIPMENTONSHIPMENT")
public class EquipmentOnShipment implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "EQUIPMENTONSHIPMENTID", nullable = false, updatable = false)
    private Long equipmentOnShipmentId;

    @Column(name = "SHIPMENTLEGID")
    private Long shipmentLegId;

    @Column(name = "EQUIPMENTTYPECODE")
    private String equipmentTypeCode;

    @Column(name = "EQUIPMENTID")
    private Long equipmentId;

    @Column(name = "NETWEIGHT")
    private Double netWeight;

    @Column(name = "TAREWEIGHT")
    private Double tareWeight;

    @Column(name = "GROSSWEIGHT")
    private Double grossWeight;

    @Column(name = "WEIGHTUNITS")
    private String weightUnits;

    @Column(name = "WEIGHTUOM")
    private Long weightuom;

    @Column(name = "EQUIPMENTNUMBER")
    private String equipmentNumber;

    @Column(name = "FREIGHTDESCRIPTION")
    private String freightDescription;

    @Column(name = "SEAL")
    private String seal;

    @Column(name = "EMPTYRELEASENUMBER")
    private String emptyReleaseNumber;

    @Type(type="yes_no")
    @Column(name = "HAZMAT")
    private boolean hazmat;

    @Type(type="yes_no")
    @Column(name = "OVERWEIGHT")
    private boolean overweight;

    @Type(type="yes_no")
    @Column(name = "ISEMPTY")
    private boolean isEmpty;

    @Column(name = "STATUSID")
    private Long statusId;

    @Column(name = "VOLUME")
    private Double volume;

    @Column(name = "VOLUMEUOM")
    private Long volumeUom;

    @Column(name = "PIECECOUNT")
    private Long pieceCount;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "DIMENSIONUOM")
    private String dimensionUom;

    @Column(name = "HEIGHT")
    private Double height;

    @Column(name = "WIDTH")
    private Double width;

    @Column(name = "LENGTH")
    private Double length;

    @Column(name = "ASSOCIATEDUNITID")
    private String associatedUnitId;

    @Column(name = "CHASSISNUMBER")
    private String chassisNumber;

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
  
    @OneToMany(mappedBy = "equipmentOnShipment", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
    private List<Charge> charge;

    
    
    public EquipmentOnShipment() {
        super();
    }

    public Long getEquipmentOnShipmentId() {
        return equipmentOnShipmentId;
    }


    public void setEquipmentOnShipmentId(Long equipmentOnShipmentId) {
        this.equipmentOnShipmentId = equipmentOnShipmentId;
    }


    public Long getShipmentLegId() {
        return shipmentLegId;
    }


    public void setShipmentLegId(Long shipmentLegId) {
        this.shipmentLegId = shipmentLegId;
    }


    public String getEquipmentTypeCode() {
        return equipmentTypeCode;
    }


    public void setEquipmentTypeCode(String equipmentTypeCode) {
        this.equipmentTypeCode = equipmentTypeCode;
    }


    public Long getEquipmentId() {
        return equipmentId;
    }


    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }


    public Double getNetWeight() {
        return netWeight;
    }


    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }


    public Double getTareWeight() {
        return tareWeight;
    }


    public void setTareWeight(Double tareWeight) {
        this.tareWeight = tareWeight;
    }


    public Double getGrossWeight() {
        return grossWeight;
    }


    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }


    public String getWeightUnits() {
        return weightUnits;
    }


    public void setWeightUnits(String weightUnits) {
        this.weightUnits = weightUnits;
    }


    public Long getWeightuom() {
        return weightuom;
    }


    public void setWeightuom(Long weightuom) {
        this.weightuom = weightuom;
    }


    public String getEquipmentNumber() {
        return equipmentNumber;
    }


    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }


    public String getFreightDescription() {
        return freightDescription;
    }


    public void setFreightDescription(String freightDescription) {
        this.freightDescription = freightDescription;
    }


    public String getSeal() {
        return seal;
    }


    public void setSeal(String seal) {
        this.seal = seal;
    }


    public String getEmptyReleaseNumber() {
        return emptyReleaseNumber;
    }


    public void setEmptyReleaseNumber(String emptyReleaseNumber) {
        this.emptyReleaseNumber = emptyReleaseNumber;
    }


    public boolean isHazmat() {
        return hazmat;
    }


    public void setHazmat(boolean hazmat) {
        this.hazmat = hazmat;
    }


    public boolean isOverweight() {
        return overweight;
    }


    public void setOverweight(boolean overweight) {
        this.overweight = overweight;
    }


    public boolean isEmpty() {
        return isEmpty;
    }


    public void setEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }


    public Long getStatusId() {
        return statusId;
    }


    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }


    public Double getVolume() {
        return volume;
    }


    public void setVolume(Double volume) {
        this.volume = volume;
    }


    public Long getVolumeUom() {
        return volumeUom;
    }


    public void setVolumeUom(Long volumeUom) {
        this.volumeUom = volumeUom;
    }


    public Long getPieceCount() {
        return pieceCount;
    }


    public void setPieceCount(Long pieceCount) {
        this.pieceCount = pieceCount;
    }


    public String getComments() {
        return comments;
    }


    public void setComments(String comments) {
        this.comments = comments;
    }


    public String getDimensionUom() {
        return dimensionUom;
    }


    public void setDimensionUom(String dimensionUom) {
        this.dimensionUom = dimensionUom;
    }


    public Double getHeight() {
        return height;
    }


    public void setHeight(Double height) {
        this.height = height;
    }


    public Double getWidth() {
        return width;
    }


    public void setWidth(Double width) {
        this.width = width;
    }


    public Double getLength() {
        return length;
    }


    public void setLength(Double length) {
        this.length = length;
    }


    public String getAssociatedUnitId() {
        return associatedUnitId;
    }


    public void setAssociatedUnitId(String associatedUnitId) {
        this.associatedUnitId = associatedUnitId;
    }


    public String getChassisNumber() {
        return chassisNumber;
    }


    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
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

    public List<Charge> getCharge() {
        return charge;
    }

    public void setCharge(List<Charge> charge) {
        this.charge = charge;
    }



}

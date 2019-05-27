package com.blumeglobal.shipmentmanagement.model;

import java.io.Serializable;

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
@Table(name="SHIPMENTREFERENCES")
public class ShipmentReferences implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	@Column(name = "SHIPMENTREFLINKID")
	private Long shipmentRefLinkId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SHIPMENTID", nullable = false)
	private Shipment shipmentReferencesLink;
	
	@Column(name = "SHIPMENTREFTYPE")
	private String shipmentRefType;

	@Column(name = "SHIPMENTREFVALUE")
	private String shipmentRefValue;
	
	@Column(name = "SOURCE")
	private String source;


	public ShipmentReferences() {
		super();
	}

	public Long getShipmentRefLinkId() {
		return shipmentRefLinkId;
	}

	public void setShipmentRefLinkId(Long shipmentRefLinkId) {
		this.shipmentRefLinkId = shipmentRefLinkId;
	}

	public Shipment getShipmentReferencesLink() {
		return shipmentReferencesLink;
	}

	public void setShipmentReferencesLink(Shipment shipmentReferencesLink) {
		this.shipmentReferencesLink = shipmentReferencesLink;
	}

	public String getShipmentRefType() {
		return shipmentRefType;
	}

	public void setShipmentRefType(String shipmentRefType) {
		this.shipmentRefType = shipmentRefType;
	}

	public String getShipmentRefValue() {
		return shipmentRefValue;
	}

	public void setShipmentRefValue(String shipmentRefValue) {
		this.shipmentRefValue = shipmentRefValue;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((shipmentRefType == null) ? 0 : shipmentRefType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ShipmentReferences other = (ShipmentReferences) obj;
        if (shipmentRefType == null) {
            if (other.shipmentRefType != null)
                return false;
        } else if (!shipmentRefType.equals(other.shipmentRefType))
            return false;
        return true;
    }
	
}

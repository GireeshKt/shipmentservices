package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;

public class LocationAPIResponse implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		private String uuid;
	    private String address1;
	    private String address2;
	    private String city;
	    private String state;
	    private String postalCode;
	    private String country;
	    private String locationType;
	    private String name;
	    private String unlocode;
	    
	    
		public LocationAPIResponse() {
			
		}


		public String getUuid() {
			return uuid;
		}


		public void setUuid(String uuid) {
			this.uuid = uuid;
		}


		public String getAddress1() {
			return address1;
		}


		public void setAddress1(String address1) {
			this.address1 = address1;
		}


		public String getAddress2() {
			return address2;
		}


		public void setAddress2(String address2) {
			this.address2 = address2;
		}


		public String getCity() {
			return city;
		}


		public void setCity(String city) {
			this.city = city;
		}


		public String getState() {
			return state;
		}


		public void setState(String state) {
			this.state = state;
		}


		public String getPostalCode() {
			return postalCode;
		}


		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}


		public String getCountry() {
			return country;
		}


		public void setCountry(String country) {
			this.country = country;
		}


		public String getLocationType() {
			return locationType;
		}


		public void setLocationType(String locationType) {
			this.locationType = locationType;
		}


		public String getName() {
            return name;
        }


        public void setName(String name) {
            this.name = name;
        }


        public String getUnlocode() {
            return unlocode;
        }


        public void setUnlocode(String unlocode) {
            this.unlocode = unlocode;
        }


        @Override
		public String toString() {
			return "LocationAPIResponse {uuid:" + uuid + ", address1:" + address1 + ", address2:" + address2 + ", city:"
					+ city + ", state:" + state + ", postalCode:" + postalCode + ", country:" + country + ", locationType:"
					+ locationType + ", name:" + name+ ", unlocode:" + unlocode+ "}";
		}
}

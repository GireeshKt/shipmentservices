package com.blumeglobal.shipmentmanagement.response;

import java.util.List;

public class StatusResponse {
		private List<Long> valid;
		private List<Long> inValid;
		private String other;
		public StatusResponse() {
			
		}
		public List<Long> getValid() {
			return valid;
		}
		public void setValid(List<Long> poIds) {
			this.valid = poIds;
		}
		
		public List<Long> getInValid() {
			return inValid;
		}
		public void setInValid(List<Long> inValid) {
			this.inValid = inValid;
		}
		public String getOther() {
			return other;
		}
		public void setOther(String other) {
			this.other = other;
		}
		
		
}

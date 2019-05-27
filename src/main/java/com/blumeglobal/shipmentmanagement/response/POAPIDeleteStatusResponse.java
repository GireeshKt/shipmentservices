package com.blumeglobal.shipmentmanagement.response;

import java.util.List;

public class POAPIDeleteStatusResponse {
		private List<Long> valid;
		private List<Long> inValid;
		private String status;
		private String message;
		
		public POAPIDeleteStatusResponse() {
			
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
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
}

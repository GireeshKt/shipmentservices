package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

public class tempReq implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String job;
	
	public tempReq() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

}

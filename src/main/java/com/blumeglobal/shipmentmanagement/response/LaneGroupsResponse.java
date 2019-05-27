package com.blumeglobal.shipmentmanagement.response;

import java.io.Serializable;

public class LaneGroupsResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String lanegroupId;
	
//	private String laneRef;	
	
	private String laneId;
	
	private String isactive;
	
	private String majorMode;
	
	private String groupDescription;
	
	private String deafultGroup;
	
	public String getGroupDescription() {
		return groupDescription;
	}


	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}


	public String getDeafultGroup() {
		return deafultGroup;
	}


	public void setDeafultGroup(String deafultGroup) {
		this.deafultGroup = deafultGroup;
	}


	public LaneGroupsResponse() {
		
	}


	public String getLanegroupId() {
		return lanegroupId;
	}


	public void setLanegroupId(String lanegroupId) {
		this.lanegroupId = lanegroupId;
	}

//	public String getLaneRef() {
//		return laneRef;
//	}
//
//
//	public void setLaneRef(String laneRef) {
//		this.laneRef = laneRef;
//	}


	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}


	public String getMajorMode() {
		return majorMode;
	}


	public void setMajorMode(String majorMode) {
		this.majorMode = majorMode;
	}


	public String getLaneId() {
		return laneId;
	}


	public void setLaneId(String laneId) {
		this.laneId = laneId;
	}

	
	
}
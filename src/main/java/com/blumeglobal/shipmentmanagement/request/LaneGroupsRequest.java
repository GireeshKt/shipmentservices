package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

import javax.validation.constraints.Size;



public class LaneGroupsRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
//	@Size(max = 50, message = "Lane Group Id must be a maximum of 50 characters")
//	private String LanegroupId;
//	
//	@Size(max = 100, message = "Group Description must be a maximum of 100 characters")
//	private String groupDescription;
//	
//	@Size(max = 50, message = "Lane Link Id must be a maximum of 50 characters")
////	private String laneId;
//	private String laneRef;
//	@Size(max = 1, message = "Default Group must be a maximum of 1 characters")
//	private String deafultGroup;
//	
//	@Size(max = 1, message = "IsActive must be a maximum of 1 character")
//	private String isactive;
//	
//	@Size(max = 50, message = "Major Mode must be a maximum of 50 characters")
//	private String majorMode;
	
	@Size(max = 50, message = "Lane Group Id must be a maximum of 50 characters")
	private String lanegroupId;

	@Size(max = 100, message = "Group Description must be a maximum of 100 characters")
	private String groupDescription;

	@Size(max = 50, message = "LaneRef Id must be a maximum of 50 characters")
	private String laneRef;

	@Size(max = 1, message = "Default Group must be a maximum of 1 characters")
	private String deafultGroup;

	@Size(max = 1, message = "IsActive must be a maximum of 1 character")
	private String isactive;

	@Size(max = 50, message = "Major Mode must be a maximum of 50 characters")
	private String majorMode;
	
	public LaneGroupsRequest() {
		super();
	}
	

	public String getLanegroupId() {
		return lanegroupId;
	}



	public void setLanegroupId(String lanegroupId) {
		this.lanegroupId = lanegroupId;
	}



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

	public String getLaneRef() {
		return laneRef;
	}

	public void setLaneRef(String laneRef) {
		this.laneRef = laneRef;
	}

	
}
package com.blumeglobal.shipmentmanagement.enums;

public enum ExceptionSeverity {
	High(1), Medium(2), Low(3);
	private Integer level;

	private ExceptionSeverity(Integer level) {
	      this.level = level;
	  	}

	public Integer getLevel() {
		return level;
	}
	public static ExceptionSeverity getPriority(Integer input) {
		if(input==null) return Low;
		for(ExceptionSeverity ep:ExceptionSeverity.values()) {
			
			if(ep.getLevel().intValue()==input.intValue()) {
				return ep;
			}
		}
		return Low;
	}
}

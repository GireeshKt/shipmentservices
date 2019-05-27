package com.blumeglobal.shipmentmanagement.model;

public class ExceptionDataKey {

	private String appName;
	private String code;

	private String language;
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Override
	public String toString() {
		return "ExceptionDataKey [appName=" + appName + ", code=" + code + ", language=" + language + "]";
	}
}

package com.oseevol.measureme.model;

import java.util.Objects;

public class MeasurementsDto {
	
	private String websiteName;
	private long sessionId;
	private long requestTime;
	private long responseTime;
	private String webAppName;
	private String webAppVer;
	
	
	public String getWebsiteName() {
		return websiteName;
	}
	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}
	public long getSessionId() {
		return sessionId;
	}
	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
	public long getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(long requestTime) {
		this.requestTime = requestTime;
	}
	public long getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}
	public String getWebAppName() {
		return webAppName;
	}
	public void setWebAppName(String webAppName) {
		this.webAppName = webAppName;
	}
	public String getWebAppVer() {
		return webAppVer;
	}
	public void setWebAppVer(String webAppVer) {
		this.webAppVer = webAppVer;
	}
	@Override
	public String toString() {
		return "MeasurementsDto [websiteName=" + websiteName + ", sessionId=" + sessionId + ", requestTime="
				+ requestTime + ", responseTime=" + responseTime + ", webAppName=" + webAppName + ", webAppVer="
				+ webAppVer + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(requestTime, responseTime, sessionId, webAppName, webAppVer, websiteName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof MeasurementsDto))
			return false;
		MeasurementsDto other = (MeasurementsDto) obj;
		return requestTime == other.requestTime && responseTime == other.responseTime && sessionId == other.sessionId
				&& Objects.equals(webAppName, other.webAppName) && Objects.equals(webAppVer, other.webAppVer)
				&& Objects.equals(websiteName, other.websiteName);
	}
}

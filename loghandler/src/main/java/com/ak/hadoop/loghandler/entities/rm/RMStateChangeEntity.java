package com.ak.hadoop.loghandler.entities.rm;

import com.ak.hadoop.loghandler.entities.Entity;
import com.ak.hadoop.loghandler.utils.Utils;

public class RMStateChangeEntity implements Entity {

	private String dateTime;
	private String logLevel;
	private String applicationId;
	private String fromState;
	private String toState;
	private String event;
	
	
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime=Utils.nullToEmpty(dateTime);
	}
	public String getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(String logLevel) {
		this.logLevel=Utils.nullToEmpty(logLevel);
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId=Utils.nullToEmpty(applicationId);
	}
	public String getFromState() {
		return fromState;
	}
	public void setFromState(String fromState) {
		this.fromState=Utils.nullToEmpty(fromState);
	}
	public String getToState() {
		return toState;
	}
	public void setToState(String toState) {
		this.toState=Utils.nullToEmpty(toState);
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event=Utils.nullToEmpty(event);
	}
	@Override
	public String toString() {
		return "dateTime=" + dateTime + "\nlogLevel=" + logLevel + "\napplicationId=" + applicationId
				+ "\nfromState=" + fromState + "\ntoState=" + toState + "\nevent=" + event + "\n------------------------------------";
	}
	
	
	
	
	
	
}

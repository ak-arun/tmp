package com.ak.hadoop.loghandler.entities.rm;

import com.ak.hadoop.loghandler.entities.Entity;
import com.ak.hadoop.loghandler.utils.Utils;

public class YarnAttemptRegisterEntity implements Entity{

	private String dateTime;
	private String logLevel;
	private String attemptId;
	
	
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
	public String getAttemptId() {
		return attemptId;
	}
	public void setAttemptId(String attemptId) {
		this.attemptId=Utils.nullToEmpty(attemptId);
	}
	@Override
	public String toString() {
		return "dateTime=" + dateTime + "\nlogLevel=" + logLevel + "\nattemptId=" + attemptId
				+ "\n------------------------------------";
	}
	
	
	
}

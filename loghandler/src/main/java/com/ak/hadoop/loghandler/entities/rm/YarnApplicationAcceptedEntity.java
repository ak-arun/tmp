package com.ak.hadoop.loghandler.entities.rm;

import com.ak.hadoop.loghandler.entities.Entity;
import com.ak.hadoop.loghandler.utils.Utils;

public class YarnApplicationAcceptedEntity implements Entity {

	private String dateTime;
	private String logLevel;
	private String applicationId;
	private String user;
	private String queue;

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = Utils.nullToEmpty(dateTime);
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = Utils.nullToEmpty(logLevel);
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = Utils.nullToEmpty(applicationId);
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = Utils.nullToEmpty(user);
	}

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = Utils.nullToEmpty(queue);
	}

	@Override
	public String toString() {
		return "dateTime=" + dateTime + "\nlogLevel=" + logLevel + "\napplicationId=" + applicationId + "\nuser=" + user
				+ "\nqueue=" + queue + "\n------------------------------------";
	}

}

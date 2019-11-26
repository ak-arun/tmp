package com.ak.hadoop.loghandler.entities.hive;

import com.ak.hadoop.loghandler.entities.Entity;
import com.ak.hadoop.loghandler.utils.Utils;

public class HiveTezYarnEntity implements Entity{

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

	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = Utils.nullToEmpty(poolName);
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = Utils.nullToEmpty(threadId);
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = Utils.nullToEmpty(sessionName);
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = Utils.nullToEmpty(applicationId);
	}

	public String getDagName() {
		return dagName;
	}

	public void setDagName(String dagName) {
		this.dagName = Utils.nullToEmpty(dagName);
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = Utils.nullToEmpty(context);
	}

	public String getCallerType() {
		return callerType;
	}

	public void setCallerType(String callerType) {
		this.callerType = Utils.nullToEmpty(callerType);
	}

	public String getCallerId() {
		return callerId;
	}

	public void setCallerId(String callerId) {
		this.callerId = Utils.nullToEmpty(callerId);
	}

	private String dateTime;
	private String logLevel;
	private String poolName;
	private String threadId;
	private String sessionName;
	private String applicationId;
	private String dagName;
	private String context;
	private String callerType;
	private String callerId;
	
	@Override
	public String toString() {
		return "dateTime=" + dateTime + "\nlogLevel=" + logLevel + "\npoolName=" + poolName
				+ "\nthreadId=" + threadId + "\nsessionName=" + sessionName + "\napplicationId=" + applicationId
				+ "\ndagName=" + dagName + "\ncontext=" + context + "\ncallerType=" + callerType + "\ncallerId="
				+ callerId+ "\n------------------------------------";
	}

}

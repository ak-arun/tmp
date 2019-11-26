package com.ak.hadoop.loghandler.entities.hive;

import com.ak.hadoop.loghandler.entities.Entity;
import com.ak.hadoop.loghandler.utils.Utils;

public class HiveQueryCompletionEntity implements Entity {

	private String dateTime;
	private String logLevel;
	private String poolName;
	private String threadId;
	private String hiveQueryId;

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

	public String getHiveQueryId() {
		return hiveQueryId;
	}

	public void setHiveQueryId(String hiveQueryId) {
		this.hiveQueryId = Utils.nullToEmpty(hiveQueryId);
	}

	@Override
	public String toString() {
		return "dateTime=" + dateTime + "\nlogLevel=" + logLevel + "\npoolName=" + poolName + "\nthreadId=" + threadId
				+ "\nhiveQueryId=" + hiveQueryId + "\n------------------------------------";
	}

}

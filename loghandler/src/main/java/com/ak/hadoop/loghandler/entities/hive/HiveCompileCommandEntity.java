package com.ak.hadoop.loghandler.entities.hive;

import com.ak.hadoop.loghandler.entities.Entity;
import com.ak.hadoop.loghandler.utils.Utils;

public class HiveCompileCommandEntity implements Entity {

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = Utils.nullToEmpty(dateTime);
	}

	public String getPoolUuid() {
		return poolUuid;
	}

	public void setPoolUuid(String poolUuid) {
		this.poolUuid = Utils.nullToEmpty(poolUuid);
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

	public String getHiveQuery() {
		return hiveQuery;
	}

	public void setHiveQuery(String hiveQuery) {
		this.hiveQuery = Utils.nullToEmpty(hiveQuery);
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = Utils.nullToEmpty(logLevel);
	}


	@Override
	public String toString() {
		return "dateTime=" + dateTime + "\nlogLevel=" + logLevel +"\npoolUuid=" + poolUuid + "\npoolName=" + poolName + "\nthreadId=" + threadId
				+ "\nhiveQueryId=" + hiveQueryId + "\nhiveQuery=" + hiveQuery
				+ "\n------------------------------------";
	}

	private String dateTime;
	private String logLevel;
	private String poolUuid;
	private String poolName;
	private String threadId;
	private String hiveQueryId;
	private String hiveQuery;

}

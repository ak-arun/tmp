package com.ak.hadoop.loghandler.entities.rm;

import com.ak.hadoop.loghandler.entities.Entity;
import com.ak.hadoop.loghandler.utils.Utils;

public class YarnAllocationEntity implements Entity {

	private String dateTime;
	private String logLevel;
	private String attemptId;
	private String container;
	private String queue;
	private String memory;
	private String vCore;
	private String type;
	
	
	
	
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime =Utils.nullToEmpty(dateTime);
	}
	public String getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(String logLevel) {
		this.logLevel =Utils.nullToEmpty(logLevel);
	}
	public String getAttemptId() {
		return attemptId;
	}
	public void setAttemptId(String attemptId) {
		this.attemptId =Utils.nullToEmpty(attemptId);
	}
	public String getContainer() {
		return container;
	}
	public void setContainer(String container) {
		this.container =Utils.nullToEmpty(container);
	}
	public String getQueue() {
		return queue;
	}
	public void setQueue(String queue) {
		this.queue =Utils.nullToEmpty(queue);
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory =Utils.nullToEmpty(memory);
	}
	public String getvCore() {
		return vCore;
	}
	public void setvCore(String vCore) {
		this.vCore =Utils.nullToEmpty(vCore);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type =Utils.nullToEmpty(type);
	}
	@Override
	public String toString() {
		return "dateTime=" + dateTime + "\nlogLevel=" + logLevel + "\nattemptId=" + attemptId
				+ "\ncontainer=" + container + "\nqueue=" + queue + "\nmemory=" + memory + "\nvCore=" + vCore
				+ "\ntype=" + type+ "\n------------------------------------";
	}
	
	
	
	
}

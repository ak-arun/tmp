package com.ak.hadoop.loghandler;

import org.json.simple.parser.ParseException;

import com.ak.hadoop.loghandler.utils.EntityBuilder;

public class Driver {

	
public static void main(String[] args) throws ParseException {
		
		/*Entity parsedCommand =  EntityBuilder.build(LogEntity.HIVECOMPILE,"2019-11-25T15:27:44,389 INFO  [e637cb2c-63f9-45fb-8899-1452157b11d0HiveServer2-Handler-Pool: Thread-84]: ql.Driver (:()) - Compilingcommand(queryId=hive_20191125152744_105704a1-e14f-4008-a8d5-3419d1160e8a):select origin, count(*) from air.delay_ex_orc_part group by origin");
		System.out.println(parsedCommand);
		Entity parsedExecute = (HiveExecuteCommandEntity) EntityBuilder.build(LogEntity.HIVEEXECUTE,"2019-11-25T15:27:47,244 INFO  [HiveServer2-Background-Pool: Thread-133]:ql.Driver (:()) - Executing command(queryId=hive_20191125152744_105704a1-e14f-4008-a8d5-3419d1160e8a): select origin, count(*) from air.delay_ex_orc_part group by origin");
		System.out.println(parsedExecute);
		Entity parsedTezYarn = EntityBuilder.build(LogEntity.HIVETEZYARN,"2019-11-25T15:27:57,944 INFO  [HiveServer2-Background-Pool: Thread-133]:client.TezClient (:()) - Submitting dag to TezSession, sessionName=HIVE-e38a5f16-c915-4ed0-8de8-b1d067572f8f,applicationId=application_1574693128010_0005, dagName=select origin,count(*) from air.de...origin (Stage-1), callerContext={ context=HIVE,callerType=HIVE_QUERY_ID, callerId=hive_20191125152744_105704a1-e14f-4008-a8d5-3419d1160e8a }");
		System.out.println(parsedTezYarn);
		Entity  parsedQueryCompletion = EntityBuilder.build(LogEntity.HIVEQUERYEND,"2019-11-25T15:28:12,852 INFO  [HiveServer2-Background-Pool: Thread-133]:ql.Driver (:()) - Completed executingcommand(queryId=hive_20191125152744_105704a1-e14f-4008-a8d5-3419d1160e8a)");
		System.out.println(parsedQueryCompletion);
		Entity  rmStateChangeEntity = EntityBuilder.build(LogEntity.RMSTATECHANGE,"2019-11-25 15:27:50,510 INFO  rmapp.RMAppImpl (RMAppImpl.java:handle(912)) application_1574693128010_0005 State change from NEW_SAVING to SUBMITTED on event = APP_NEW_SAVED");
		System.out.println(rmStateChangeEntity);
		Entity yarnAppSubmitEntity = EntityBuilder.build(LogEntity.YARNAPPACCEPETED, "2019-11-25 15:27:50,515 INFO  capacity.CapacityScheduler (CapacityScheduler.java:addApplication(990)) - Accepted application application_1574693128010_0005 from user: hive, in queue: default");
		System.out.println(yarnAppSubmitEntity);
		Entity yarnAppAttemptRegisterEntity = EntityBuilder.build(LogEntity.YARNREGISTERATTEMPT, "2019-11-25 15:27:50,515 INFO resourcemanager.ApplicationMasterService (ApplicationMasterService.java:registerAppAttempt(479)) - Registering app attempt : appattempt_1574693128010_0005_000001");
		System.out.println(yarnAppAttemptRegisterEntity);
		Entity yarnAllocationEntity = EntityBuilder.build(LogEntity.YARNALLOCATION, "2019-11-25 15:27:50,531 INFO  allocator.AbstractContainerAllocator (AbstractContainerAllocator.java:getCSAssignmentFromAllocateResult(129)) - assignedContainer application attempt=appattempt_1574693128010_0005_000001 container=null queue=default clusterResource=<memory:27648, vCores:9> type=OFF_SWITCH requestedPartition=");
		System.out.println(yarnAllocationEntity);*/
		
		
		
		
		String COMPILE_PATTERN = "(.*?)\\s(.*?)\\[([a-z0-9\\-]{36})(.*?):.*?(Thread-.*?)\\].*?queryId=(.*?)\\):(.*)";
		String EXECUTE_PATTERN = "(.*?)\\s(.*?)\\[(.*?):.*(Thread.*?)\\].*=(.*?)\\).*?:(.*)";
		String HIVE_TEZ_YARN_PATTERN = "(.*?)\\s(.*?)\\[(.*?):.*?(Thread.*?)\\].*?sessionName=(.*?),.*?=(.*?),.*?=(.*?)\\(S.*?context=(.*?),callerType=(.*?),.*?=(.*?)\\}";
		String HIVE_QUERYCOMPLETED_PATTERN = "(.*?)\\s(.*?)\\s\\[(.*?):.*(Thread.*?)\\].*?queryId=(.*?)\\)";
		String RM_STATECHANGE_PATTERN = "([0-9-\\s,:]{23})\\s([A-Z]+)\\s.*?(application_[\\d]{13}_\\d{4}).*from(.*?)to(.*?)on.*?=(.*)";
		String YARN_APPACCEPTED_PATTERN = "([0-9-\\s,:]{23})\\s([A-Z]+)\\s.*?(application_.*?)\\s.*?from.*?:(.*)?,.*?queue:(.*)";
		String YARN_REGISTERATTEMPT_PATTERN = "([0-9-\\s,:]{23})\\s([A-Z]+)\\s.*?attempt :(.*)";
		String YARN_ALLOCATION_PATTERN = "([0-9-\\s,:]{23})\\s([A-Z]+)\\s.*?attempt=(.*?)container=(.*?)queue=(.*?)\\s.*?memory:(.*?),.*?vCores:(.*?)\\>.*?type=(.*?)\\s.*";
		
		
		String compileLog="2019-11-25T15:27:44,389 INFO  [e637cb2c-63f9-45fb-8899-1452157b11d0HiveServer2-Handler-Pool: Thread-84]: ql.Driver (:()) - Compilingcommand(queryId=hive_20191125152744_105704a1-e14f-4008-a8d5-3419d1160e8a):select origin, count(*) from air.delay_ex_orc_part group by origin";
		String executeLog="2019-11-25T15:27:47,244 INFO  [HiveServer2-Background-Pool: Thread-133]:ql.Driver (:()) - Executing command(queryId=hive_20191125152744_105704a1-e14f-4008-a8d5-3419d1160e8a): select origin, count(*) from air.delay_ex_orc_part group by origin";
		String tezYarnLog="2019-11-25T15:27:57,944 INFO  [HiveServer2-Background-Pool: Thread-133]:client.TezClient (:()) - Submitting dag to TezSession, sessionName=HIVE-e38a5f16-c915-4ed0-8de8-b1d067572f8f,applicationId=application_1574693128010_0005, dagName=select origin,count(*) from air.de...origin (Stage-1), callerContext={ context=HIVE,callerType=HIVE_QUERY_ID, callerId=hive_20191125152744_105704a1-e14f-4008-a8d5-3419d1160e8a }";
		String queryCompletionLog="2019-11-25T15:28:12,852 INFO  [HiveServer2-Background-Pool: Thread-133]:ql.Driver (:()) - Completed executingcommand(queryId=hive_20191125152744_105704a1-e14f-4008-a8d5-3419d1160e8a)";
		String rmStateChangeLog="2019-11-25 15:27:50,510 INFO  rmapp.RMAppImpl (RMAppImpl.java:handle(912)) application_1574693128010_0005 State change from NEW_SAVING to SUBMITTED on event = APP_NEW_SAVED";
		String yarnAppSubmitLog="2019-11-25 15:27:50,515 INFO  capacity.CapacityScheduler (CapacityScheduler.java:addApplication(990)) - Accepted application application_1574693128010_0005 from user: hive, in queue: default";
		String yarnAppAttemptRegisterLog="2019-11-25 15:27:50,515 INFO resourcemanager.ApplicationMasterService (ApplicationMasterService.java:registerAppAttempt(479)) - Registering app attempt : appattempt_1574693128010_0005_000001";
		String yarnAllocationLog="2019-11-25 15:27:50,531 INFO  allocator.AbstractContainerAllocator (AbstractContainerAllocator.java:getCSAssignmentFromAllocateResult(129)) - assignedContainer application attempt=appattempt_1574693128010_0005_000001 container=null queue=default clusterResource=<memory:27648, vCores:9> type=OFF_SWITCH requestedPartition=";
		
		
		// this is optional. If we can live without columns names and just go with the order of columns, we can use the getJson(logLine, patternString) method. 
		String compileColumns = "dateTime,logLevel,poolUuid,poolName,threadId,hiveQueryId,hiveQuery";
		String executeColumns="dateTime,logLevel,poolName,threadId,hiveQueryId,hiveQuery";
		String tezYarnColumns ="dateTime,logLevel,poolName,threadId,sessionName,applicationId,dagName,context,callerType,callerId";
		String queryCompletionColumns="dateTime,logLevel,poolName,threadId,hiveQueryId";
		String rmStateChangeColumns = "dateTime,logLevel,applicationId,fromState,toState,event";
		String yarnAppSubmitColumns="dateTime,logLevel,applicationId,user,queue";
		String yarnAppAttemptRegisterColumns="dateTime,logLevel,attemptId";
		String yarnAllocationColumns="dateTime,logLevel,attemptId,container,queue,memory,vCore,type";
		
		
		// create a generic json with columns 1,2,3, etc 
		// if needed assign column names to json 
		System.out.println(EntityBuilder.getJson(compileLog, COMPILE_PATTERN,compileColumns));
		System.out.println(EntityBuilder.getJson(executeLog, EXECUTE_PATTERN,executeColumns));
		System.out.println(EntityBuilder.getJson(tezYarnLog, HIVE_TEZ_YARN_PATTERN,tezYarnColumns));
		System.out.println(EntityBuilder.getJson(queryCompletionLog, HIVE_QUERYCOMPLETED_PATTERN,queryCompletionColumns));
		System.out.println(EntityBuilder.getJson(rmStateChangeLog, RM_STATECHANGE_PATTERN,rmStateChangeColumns));
		System.out.println(EntityBuilder.getJson(yarnAppSubmitLog, YARN_APPACCEPTED_PATTERN,yarnAppSubmitColumns));
		System.out.println(EntityBuilder.getJson(yarnAppAttemptRegisterLog, YARN_REGISTERATTEMPT_PATTERN,yarnAppAttemptRegisterColumns));
		System.out.println(EntityBuilder.getJson(yarnAllocationLog, YARN_ALLOCATION_PATTERN,yarnAllocationColumns));
		

		
	}
}

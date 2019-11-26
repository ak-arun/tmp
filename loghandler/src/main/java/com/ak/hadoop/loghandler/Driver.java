package com.ak.hadoop.loghandler;

import com.ak.hadoop.loghandler.entities.Entity;
import com.ak.hadoop.loghandler.entities.LogEntity;
import com.ak.hadoop.loghandler.entities.hive.HiveExecuteCommandEntity;
import com.ak.hadoop.loghandler.utils.EntityBuilder;

public class Driver {

	
public static void main(String[] args) {
		
		Entity parsedCommand =  EntityBuilder.build(LogEntity.HIVECOMPILE,"2019-11-25T15:27:44,389 INFO  [e637cb2c-63f9-45fb-8899-1452157b11d0HiveServer2-Handler-Pool: Thread-84]: ql.Driver (:()) - Compilingcommand(queryId=hive_20191125152744_105704a1-e14f-4008-a8d5-3419d1160e8a):select origin, count(*) from air.delay_ex_orc_part group by origin");
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
	}
}

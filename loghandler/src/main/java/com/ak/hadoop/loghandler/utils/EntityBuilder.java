package com.ak.hadoop.loghandler.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ak.hadoop.loghandler.entities.Entity;
import com.ak.hadoop.loghandler.entities.LogEntity;
import com.ak.hadoop.loghandler.entities.hive.HiveCompileCommandEntity;
import com.ak.hadoop.loghandler.entities.hive.HiveExecuteCommandEntity;
import com.ak.hadoop.loghandler.entities.hive.HiveQueryCompletionEntity;
import com.ak.hadoop.loghandler.entities.hive.HiveTezYarnEntity;
import com.ak.hadoop.loghandler.entities.rm.RMStateChangeEntity;
import com.ak.hadoop.loghandler.entities.rm.YarnAllocationEntity;
import com.ak.hadoop.loghandler.entities.rm.YarnApplicationAcceptedEntity;
import com.ak.hadoop.loghandler.entities.rm.YarnAttemptRegisterEntity;

public class EntityBuilder {

	private static final Pattern COMPILE_PATTERN = Pattern
			.compile("(.*?)\\s(.*?)\\[([a-z0-9\\-]{36})(.*?):.*?(Thread-.*?)\\].*?queryId=(.*?)\\):(.*)");
	private static final Pattern EXECUTE_PATTERN = Pattern
			.compile("(.*?)\\s(.*?)\\[(.*?):.*(Thread.*?)\\].*=(.*?)\\).*?:(.*)");
	private static final Pattern HIVE_TEZ_YARN_PATTERN = Pattern.compile(
			"(.*?)\\s(.*?)\\[(.*?):.*?(Thread.*?)\\].*?sessionName=(.*?),.*?=(.*?),.*?=(.*?)\\(S.*?context=(.*?),callerType=(.*?),.*?=(.*?)\\}");
	private static final Pattern HIVE_QUERYCOMPLETED_PATTERN = Pattern
			.compile("(.*?)\\s(.*?)\\s\\[(.*?):.*(Thread.*?)\\].*?queryId=(.*?)\\)");
	private static final Pattern RM_STATECHANGE_PATTERN = Pattern
			.compile("([0-9-\\s,:]{23})\\s([A-Z]+)\\s.*?(application_[\\d]{13}_\\d{4}).*from(.*?)to(.*?)on.*?=(.*)");

	private static final Pattern YARN_APPACCEPTED_PATTERN = Pattern
			.compile("([0-9-\\s,:]{23})\\s([A-Z]+)\\s.*?(application_.*?)\\s.*?from.*?:(.*)?,.*?queue:(.*)");

	private static final Pattern YARN_REGISTERATTEMPT_PATTERN = Pattern
			.compile("([0-9-\\s,:]{23})\\s([A-Z]+)\\s.*?attempt :(.*)");

	private static final Pattern YARN_ALLOCATION_PATTERN = Pattern.compile("([0-9-\\s,:]{23})\\s([A-Z]+)\\s.*?attempt=(.*?)container=(.*?)queue=(.*?)\\s.*?memory:(.*?),.*?vCores:(.*?)\\>.*?type=(.*?)\\s.*");

	public static Entity build(LogEntity logEntity, String logLine) {
		Entity e = null;
		switch (logEntity) {

		case HIVECOMPILE:
			e = buildHS2CompileEntity(logLine);
			break;
		case HIVEEXECUTE:
			e = buildHS2ExecuteEntity(logLine);
			break;
		case HIVEQUERYEND:
			e = buildHiveQueryCompletionEntity(logLine);
			break;
		case HIVETEZYARN:
			e = buildHiveTezYarnEntity(logLine);
			break;
		case RMSTATECHANGE:
			e = buildRMStateChangeEntity(logLine);
			break;
		case YARNAPPACCEPETED:
			e = buildYarnAppAccepted(logLine);
			break;

		case YARNREGISTERATTEMPT:
			e = buildYarnAttemptRegisterEntity(logLine);
			break;

		case YARNALLOCATION:
			e = buildYarnAllocationEntity(logLine);
			break;

		}
		return e;
	}

	private static YarnAllocationEntity buildYarnAllocationEntity(String logLine) {
		
		System.out.println(logLine);
		
		Matcher matcher;
		YarnAllocationEntity entity = new YarnAllocationEntity();
		matcher = YARN_ALLOCATION_PATTERN.matcher(logLine);
		if (matcher.matches()) {
			entity.setDateTime(matcher.group(1));
			entity.setLogLevel(matcher.group(2));
			entity.setAttemptId(matcher.group(3));
			entity.setContainer(matcher.group(4));
			entity.setQueue(matcher.group(5));
			entity.setMemory(matcher.group(6));
			entity.setvCore(matcher.group(7));
			entity.setType(matcher.group(8));
		}
		return entity;
	}

	private static YarnAttemptRegisterEntity buildYarnAttemptRegisterEntity(String logLine) {
		Matcher matcher;
		YarnAttemptRegisterEntity entity = new YarnAttemptRegisterEntity();
		matcher = YARN_REGISTERATTEMPT_PATTERN.matcher(logLine);
		if (matcher.matches()) {
			entity.setDateTime(matcher.group(1));
			entity.setLogLevel(matcher.group(2));
			entity.setAttemptId(matcher.group(3));
		}
		return entity;
	}

	private static YarnApplicationAcceptedEntity buildYarnAppAccepted(String yarnAccepted) {
		Matcher match;
		YarnApplicationAcceptedEntity entity = new YarnApplicationAcceptedEntity();
		match = YARN_APPACCEPTED_PATTERN.matcher(yarnAccepted);
		if (match.matches()) {
			entity.setDateTime(match.group(1));
			entity.setLogLevel(match.group(2));
			entity.setApplicationId(match.group(3));
			entity.setUser(match.group(4));
			entity.setQueue(match.group(5));
		}
		return entity;
	}

	private static HiveCompileCommandEntity buildHS2CompileEntity(String compileLog) {
		Matcher match;
		HiveCompileCommandEntity compileCommand = new HiveCompileCommandEntity();
		match = COMPILE_PATTERN.matcher(compileLog);
		if (match.matches()) {
			compileCommand.setDateTime(match.group(1));
			compileCommand.setLogLevel(match.group(2));
			compileCommand.setPoolUuid(match.group(3));
			compileCommand.setPoolName(match.group(4));
			compileCommand.setThreadId(match.group(5));
			compileCommand.setHiveQueryId(match.group(6));
			compileCommand.setHiveQuery(match.group(7));
		}
		return compileCommand;

	}

	private static HiveExecuteCommandEntity buildHS2ExecuteEntity(String executeLog) {
		Matcher match;
		HiveExecuteCommandEntity executeCommand = new HiveExecuteCommandEntity();
		match = EXECUTE_PATTERN.matcher(executeLog);
		if (match.matches()) {
			executeCommand.setDateTime(match.group(1));
			executeCommand.setLogLevel(match.group(2));
			executeCommand.setPoolName(match.group(3));
			executeCommand.setThreadId(match.group(4));
			executeCommand.setHiveQueryId(match.group(5));
			executeCommand.setHiveQuery(match.group(6));
		}
		return executeCommand;

	}

	private static HiveTezYarnEntity buildHiveTezYarnEntity(String yarnTezLog) {
		Matcher match;
		HiveTezYarnEntity yarnTezVO = new HiveTezYarnEntity();
		match = HIVE_TEZ_YARN_PATTERN.matcher(yarnTezLog);
		if (match.matches()) {
			yarnTezVO.setDateTime(match.group(1));
			yarnTezVO.setLogLevel(match.group(2));
			yarnTezVO.setPoolName(match.group(3));
			yarnTezVO.setThreadId(match.group(4));
			yarnTezVO.setSessionName(match.group(5));
			yarnTezVO.setApplicationId(match.group(6));
			yarnTezVO.setDagName(match.group(7));
			yarnTezVO.setContext(match.group(8));
			yarnTezVO.setCallerType(match.group(9));
			yarnTezVO.setCallerId(match.group(10));

		}
		return yarnTezVO;
	}

	private static HiveQueryCompletionEntity buildHiveQueryCompletionEntity(String hiveQueryCompletionLog) {
		Matcher match;
		HiveQueryCompletionEntity queryCompletionObj = new HiveQueryCompletionEntity();
		match = HIVE_QUERYCOMPLETED_PATTERN.matcher(hiveQueryCompletionLog);
		if (match.matches()) {
			queryCompletionObj.setDateTime(match.group(1));
			queryCompletionObj.setLogLevel(match.group(2));
			queryCompletionObj.setPoolName(match.group(3));
			queryCompletionObj.setThreadId(match.group(4));
			queryCompletionObj.setHiveQueryId(match.group(5));
		}
		return queryCompletionObj;
	}

	private static RMStateChangeEntity buildRMStateChangeEntity(String rmStateChangeLog) {
		Matcher match;
		RMStateChangeEntity rmStateChangeObj = new RMStateChangeEntity();
		match = RM_STATECHANGE_PATTERN.matcher(rmStateChangeLog);
		if (match.matches()) {
			rmStateChangeObj.setDateTime(match.group(1));
			rmStateChangeObj.setLogLevel(match.group(2));
			rmStateChangeObj.setApplicationId(match.group(3));
			rmStateChangeObj.setFromState(match.group(4));
			rmStateChangeObj.setToState(match.group(5));
			rmStateChangeObj.setEvent(match.group(6));
		}
		return rmStateChangeObj;
	}
	
	@SuppressWarnings("unchecked")
	public static String getJson(String logLine, String regex) {
		Matcher matcher=Pattern.compile(regex).matcher(logLine);
		int groupCount = matcher.groupCount();
		JSONObject jsonObject = new JSONObject();
		if(matcher.matches()) {
			for(int i=0;i<groupCount;i++) {
				jsonObject.put(String.valueOf(i+1),Utils.nullToEmpty(matcher.group(i+1)));
				
			}
		}
		
		return jsonObject.toJSONString();
	}
	
	public static String getJson(String logLine, String regex, String columnNames) throws ParseException {
		List<String> columnList = Arrays.asList(columnNames.split(","));
		String json = getJson(logLine, regex);
		return matchIndexToColumns(json, columnList);
	}
	
	@SuppressWarnings("unchecked")
	public static String getJson(String logLine, String regex, Integer[] groupNums) {
		List<Integer> groupList = Arrays.asList(groupNums);
		System.out.println(groupList);
		Collections.sort(groupList);
		Matcher matcher=Pattern.compile(regex).matcher(logLine);
		JSONObject jsonObject = new JSONObject();
		if(matcher.matches()) {
			for(Integer i : groupList) {
				jsonObject.put(String.valueOf(i), Utils.nullToEmpty(matcher.group(i.intValue())));
			}
		}
		return jsonObject.toJSONString();
	}
	
	// leaving this public
	@SuppressWarnings("unchecked")
	public static String matchIndexToColumns(String json, List<String> columns) throws ParseException {
		
		JSONParser parser = new JSONParser();
		JSONObject jsonParsed = (JSONObject) parser.parse(json);
		JSONObject returnObj = new JSONObject();
		int index=1;
		for(String s : columns) {
			returnObj.put(s.trim(), jsonParsed.get(String.valueOf(index++)));
		}
		
		return returnObj.toJSONString();
		
	}
	
}

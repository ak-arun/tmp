package com.ak.hadoop.loghandler.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ak.hadoop.loghandler.entities.HiveCompileCommandVO;
import com.ak.hadoop.loghandler.entities.HiveExecuteCommandVO;
import com.ak.hadoop.loghandler.entities.HiveQueryCompletionVO;
import com.ak.hadoop.loghandler.entities.HiveTezYarnVO;

public class EntityBuilder {

	private static final Pattern COMPILE_PATTERN = Pattern
			.compile("(.*?)\\s(.*?)\\[([a-z0-9\\-]{36})(.*?):.*?(Thread-.*?)\\].*?queryId=(.*?)\\):(.*)");
	private static final Pattern EXECUTE_PATTERN = Pattern
			.compile("(.*?)\\s(.*?)\\[(.*?):.*(Thread.*?)\\].*=(.*?)\\).*?:(.*)");
	private static final Pattern HIVE_TEZ_YARN_PATTERN = Pattern.compile(
			"(.*?)\\s(.*?)\\[(.*?):.*?(Thread.*?)\\].*?sessionName=(.*?),.*?=(.*?),.*?=(.*?)\\(S.*?context=(.*?),callerType=(.*?),.*?=(.*?)\\}");

	private static final Pattern HIVE_QUERYCOMPLETED_PATTERN=Pattern.compile("(.*?)\\s(.*?)\\s\\[(.*?):.*(Thread.*?)\\].*?queryId=(.*?)\\)");
	
	public static HiveCompileCommandVO buildHS2CompileEntity(String compileLog) {
		Matcher match;
		HiveCompileCommandVO compileCommand = new HiveCompileCommandVO();
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

	public static HiveExecuteCommandVO buildHS2ExecuteEntity(String executeLog) {
		Matcher match;
		HiveExecuteCommandVO executeCommand = new HiveExecuteCommandVO();
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

	public static HiveTezYarnVO buildHiveTezYarnEntity(String yarnTezLog) {
		Matcher match;
		HiveTezYarnVO yarnTezVO = new HiveTezYarnVO();
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

	public static HiveQueryCompletionVO buildHiveQueryCompletionEntity(String hiveQueryCompletionLog) {
		Matcher match;
		HiveQueryCompletionVO queryCompletionObj = new HiveQueryCompletionVO();
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
	
}

package com.senyint.util;

public class ConfigKeyUtils {

	private static final String REQUEST_COMMAND_BEFORE = "task.";
	private static final String REQUEST_COMMAND_AFTER = ".serv";
	private static final String REQUEST_COMMAND_DATA_TAG = ".dataTag";
	private static final String REQUEST_COMMAND_ENCODE = ".encode";
	private static final String REQUEST_COMMAND_HANDLERLIST = ".handlerList";
	private static final String HANDLER_LIST_CONFIG_BEFORE = "handlerListConfig.";
	private static final String HANDLER_CONFIG_BEFORE = "handlerConfig.";
	private static final String HANDLER_CONFIG_SCRIPT_FILE = ".scriptFile";
	private static final String HANDLER_CONFIG_SCRIPT_TYPE = ".scriptType";
	private static final String HANDLER_CONFIG_FUN_NAME = ".funName";
	private static final String HANDLER_CONFIG_DATASOURCE = ".dataSource";
	private static final String HANDLER_CONFIG_TEMPLATE_NAME = ".templateName";

	private static final String HANDLER_CONFIG_WEBSERVICE_URL = ".url";
	private static final String HANDLER_CONFIG_WEBSERVICE_NAME_SPACE = ".nameSpace";
	private static final String HANDLER_CONFIG_WEBSERVICE_METHOD_NAME = ".methodName";
	private static final String HANDLER_CONFIG_WEBSERVICE_PARAM_NAME = ".paramName";
	private static final String HANDLER_CONFIG_WEBSERVICE_GEN_WEBSERVICE_DATA_KEY = ".genWebServiceDataKey";
	private static final String HANDLER_CONFIG_WEBSERVICE_SAVE_WEBSERVICE_DATA_KEY = ".saveWebServiceDataKey";

	public static String getServiceName(String requestCommand) {
		return REQUEST_COMMAND_BEFORE + requestCommand + REQUEST_COMMAND_AFTER;
	}

	public static String getDataTag(String requestCommand) {
		return REQUEST_COMMAND_BEFORE + requestCommand + REQUEST_COMMAND_DATA_TAG;
	}

	public static String getEncode(String requestCommand) {
		return REQUEST_COMMAND_BEFORE + requestCommand + REQUEST_COMMAND_ENCODE;
	}

	public static String getServiceHandlerList(String requestCommand) {
		return REQUEST_COMMAND_BEFORE + requestCommand + REQUEST_COMMAND_HANDLERLIST;
	}

	public static String getBranchHandlerList(String handlerConfigName, String judegeFlag) {
		return HANDLER_CONFIG_BEFORE + handlerConfigName + "." + judegeFlag;
	}

	public static String getHandlerListConfigName(String handlerListConfigName) {
		return HANDLER_LIST_CONFIG_BEFORE + handlerListConfigName;
	}

	public static String getHandlerConfigScriptName(String configName) {
		return HANDLER_CONFIG_BEFORE + configName + HANDLER_CONFIG_SCRIPT_FILE;
	}

	public static String getHandlerConfigScriptType(String configName) {
		return HANDLER_CONFIG_BEFORE + configName + HANDLER_CONFIG_SCRIPT_TYPE;
	}

	public static String getHandlerConfigFunName(String configName) {
		return HANDLER_CONFIG_BEFORE + configName + HANDLER_CONFIG_FUN_NAME;
	}

	public static String getHandlerDataSource(String configName) {
		return HANDLER_CONFIG_BEFORE + configName + HANDLER_CONFIG_DATASOURCE;
	}

	public static String getHandlerTemplateName(String configName) {
		return HANDLER_CONFIG_BEFORE + configName + HANDLER_CONFIG_TEMPLATE_NAME;
	}

	public static String getHandlerConfigWebServiceUrl(String configName) {
		return HANDLER_CONFIG_BEFORE + configName + HANDLER_CONFIG_WEBSERVICE_URL;
	}

	public static String getHandlerConfigWebServiceNameSpace(String configName) {
		return HANDLER_CONFIG_BEFORE + configName + HANDLER_CONFIG_WEBSERVICE_NAME_SPACE;
	}

	public static String getHandlerConfigWebServiceMethodName(String configName) {
		return HANDLER_CONFIG_BEFORE + configName + HANDLER_CONFIG_WEBSERVICE_METHOD_NAME;
	}

	public static String getHandlerConfigWebServiceParamName(String configName) {
		return HANDLER_CONFIG_BEFORE + configName + HANDLER_CONFIG_WEBSERVICE_PARAM_NAME;
	}

	public static String getHandlerConfigWebServiceGenWebServiceDataKey(String configName) {
		return HANDLER_CONFIG_BEFORE + configName + HANDLER_CONFIG_WEBSERVICE_GEN_WEBSERVICE_DATA_KEY;
	}

	public static String getHandlerConfigWebServiceSaveWebServiceDataKey(String configName) {
		return HANDLER_CONFIG_BEFORE + configName + HANDLER_CONFIG_WEBSERVICE_SAVE_WEBSERVICE_DATA_KEY;
	}
}

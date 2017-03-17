package com.senyint.entity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemConstants {
	
	
	public static String XML_JSON_TEMPLATE_SRC;
	
	public final static String HANDLER_EXECUTE_NAV_CONFIG = "HANDLER_EXECUTE_NAV_CONFIG";

	public final static String HANDLER_HTTP_REQUEST = "HANDLER_HTTP_REQUEST";

	public final static String HANDLER_REQUEST_SERVICE_METHOD = "HANDLER_REQUEST_METHOD";
	
	/** 成功返回码 */
    public final static String SUCEESS_CODE = "00";

    /** 失败返回码 */
    public final static String FAILED_CODE = "01";

    /** 数据库连接失败 */
    public final static String DATABASE_CONNECTION_ERROR = "02";

    /** 接口xml解析失败 */
    public final static String XMLPARSE_ERROR = "03";

    /** 日期格式不合法 */
    public final static String DATEFORMAT_ERROR = "04";

    /** 传入整型字段值不合法 */
    public final static String TYPEMIMATCH_ERROR = "05";

    /** 字段值超长 */
    public final static String OVERFLOW_ERROR = "06";

    /** 不可识别的命令 */
    public final static String COMMAND_ERROR = "07";

    /** 命令参数错误 */
    public final static String PARAMETER_ERROR = "08";
    
    /** 命令参数错误 */
    public final static String CONFIG_ERROR = "09";

    /**帐户错误*/
    public final static  String UID_ERROR = "11";
    
    /**口令错*/
    public final static  String PASSWORD_ERROR = "12";
    
    /**角色错*/
    public final static  String ROLE_ERROR = "13";
    
    /**凭证错*/
    public final static String TOKEN_ERROR ="14";
    
    /**SQL错误*/
    public final static String SQL_ERROR = "15";
    
    /**查询，无记录*/
    public final static String LISTSIZE_ERROR = "16";
    
    /**存储过程错误*/
    public final static String SQL_PROCEDURE_ERROR = "17";
    
    
    private SystemConstants() {
	}
}

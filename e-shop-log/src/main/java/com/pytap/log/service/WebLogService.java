package com.pytap.log.service;


import com.pytap.log.dto.WebLogDTO;

import java.util.List;
import java.util.Set;

/**
 * @author Ecin520
 * @date 2020/4/7 2:20
 */
public interface WebLogService {

	/**
	 * 插入日志信息
	 * @param webLog 日志信息 */
	void insertWebLog(WebLogDTO webLog);


	/**
	 * 返回所有web日志
	 * @return List<Object>
	 * */
	List<Object> listWebLogs();

	/**
	 * 返回传入key的web日志
	 * @param date 传入日期
	 * @return List<Object>
	 * */
	List<Object> listWebLogsByDate(String date);

	/**
	 * 返回所有web日志
	 * @return List<Object>
	 * */
	Set<String> listWebLogKeys();

	/**
	 * 清空日志
	 * @return Integer
	 * */
	Integer deleteAllWebLogs();

}

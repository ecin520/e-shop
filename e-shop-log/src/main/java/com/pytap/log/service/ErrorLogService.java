package com.pytap.log.service;

import com.pytap.log.vo.ErrorLogVO;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/4/15 23:46
 */
public interface ErrorLogService {

	/**
	 * 插入异常日志信息
	 * @param errorLogVO 日志信息 */
	void insertErrorLog(ErrorLogVO errorLogVO);

	/**
	 * 获取异常日志信息
	 * @param errorLogVO 日志对象
	 * @return List<ErrorLogDTO>
	 * */
	List<ErrorLogVO> getErrorLogs(ErrorLogVO errorLogVO);

	/**
	 * 返回传入key的error日志
	 * @param date 传入日期
	 * @return List<Object>
	 * */
	List<Object> listErrorLogsByDate(String date);

	/**
	 * 返回所有异常日志信息
	 * @return List<ErrorLogDTO>
	 * */
	List<Object> listErrorLogs();

	/**
	 * 清空日志
	 * @return Integer
	 * */
	Integer deleteAllErrorLogs();

}

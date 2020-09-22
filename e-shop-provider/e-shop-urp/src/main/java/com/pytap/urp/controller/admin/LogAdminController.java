package com.pytap.urp.controller.admin;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.ResultEntity;
import com.pytap.log.service.ErrorLogService;
import com.pytap.log.service.WebLogService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 日志接口
 * @author Ecin520
 * @date 2020/4/14 0:06
 */
@RestController
@RequestMapping("/admin/log")
public class LogAdminController {

	@Resource
	private WebLogService webLogService;

	@Resource
	private ErrorLogService errorLogService;

	@RequestMapping(value = "/web/list", method = RequestMethod.GET)
	public ResultEntity<List<Object>> listAllWebLogs() {
		return ResultEntity.success(webLogService.listWebLogs());
	}

	@RequestMapping(value = "/web/list/{date}", method = RequestMethod.GET)
	public ResultEntity<List<Object>> listWebLogsByDate(@PathVariable String date) {
		return ResultEntity.success(webLogService.listWebLogsByDate(date));
	}

	@RequestMapping(value = "/web/keys", method = RequestMethod.GET)
	public ResultEntity<Object> listAllWebLogKeys() {
		return ResultEntity.success(webLogService.listWebLogKeys());
	}

	@Log(value = "删除所有WEB日志")
	@RequestMapping(value = "/web/delete", method = RequestMethod.GET)
	public ResultEntity<String> deleteAllWebLogs() {
		int result = webLogService.deleteAllWebLogs();
		if (result == 1) {
			return ResultEntity.success("删除成功");
		}
		return ResultEntity.success("删除失败");
	}

	@RequestMapping(value = "/error/list", method = RequestMethod.GET)
	public ResultEntity<List<Object>> listAllErrorLogs() {
		return ResultEntity.success(errorLogService.listErrorLogs());
	}

	@RequestMapping(value = "/error/list/{date}", method = RequestMethod.GET)
	public ResultEntity<List<Object>> listAllErrorLogs(@PathVariable String date) {
		return ResultEntity.success(errorLogService.listErrorLogsByDate(date));
	}

	@Log(value = "删除所有异常日志")
	@RequestMapping(value = "/error/delete", method = RequestMethod.GET)
	public ResultEntity<String> deleteAllErrorLogs() {
		int result = errorLogService.deleteAllErrorLogs();
		if (result == 1) {
			return ResultEntity.success("删除成功");
		}
		return ResultEntity.success("删除失败");
	}
}

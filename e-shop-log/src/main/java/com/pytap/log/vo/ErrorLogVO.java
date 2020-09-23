package com.pytap.log.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Ecin520
 * @date 2020/4/15 23:22
 */
@Data
public class ErrorLogVO implements Serializable {

	private static final long serialVersionUID = -4444968338285942063L;

	private Long id;
	private String message;
	private String username;
	private String param;
	private String url;
	private String method;
	private String name;
	private String ip;
	private String createTime;


}

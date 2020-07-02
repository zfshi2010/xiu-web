package com.avic.vo;

import com.avic.enums.ActionType;
import lombok.Data;

/**
 * 用户操作日志Vo
 * @author shizhaofeng
 */
@Data
public class UserLogVo {

	private Long id;

	private String username;

	private String actionTime;

	private String actionDesc;

	private String internetIp;

	private ActionType actionType;

	private String actionTypeName;
}

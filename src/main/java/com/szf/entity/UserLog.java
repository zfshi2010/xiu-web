package com.szf.entity;

import com.szf.enums.ActionType;
import com.szf.vo.UserLogVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_log")
@Data
public class UserLog implements java.io.Serializable {

	private static final long serialVersionUID = -5630497663220988464L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String actionTime;

	@Enumerated(EnumType.STRING)
	private ActionType actionType;

	@Transient
	private String actionTypeName;

	private String actionDesc;

	private String internetIp;
	
	public UserLog() {
		
	}
	
	public UserLog(UserLogVo userLogVo) {
		BeanUtils.copyProperties(userLogVo, this);
	}
	
	public UserLogVo toVo() {
		UserLogVo userLogVo = new UserLogVo();
		BeanUtils.copyProperties(this, userLogVo);
		userLogVo.setActionTypeName(this.actionType.getName());
		return userLogVo;
	}
	
	public static List<UserLogVo> toVoList(List<UserLog> userLogs) {
		List<UserLogVo> userLogVos = new ArrayList<UserLogVo>();
		for (UserLog userLog : userLogs) {
			userLogVos.add(userLog.toVo());
		}
		return userLogVos;
	}

}

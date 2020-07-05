package com.avic.vo;

import com.avic.enums.RoleType;
import lombok.Data;

import java.io.Serializable;

/**
 * 账号
 */
@Data
public class UserVo implements Serializable {

    private Long id;

    private String username;

    /**
     * 昵称
     */
    private String niceName;

    private String pwd;

    private String gender;

    private String phone;

    private RoleType roleType;

    private String roleTypeName;

    private String createTime;
}

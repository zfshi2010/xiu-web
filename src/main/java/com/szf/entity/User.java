package com.szf.entity;

import com.szf.vo.UserVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 账号
 */
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 32,unique = true)
    private String username;

    /**
     * 昵称
     */
    @Column(length = 32)
    private String niceName;

    @Column(length = 64)
    private String pwd;

    @Column(length = 4)
    private String gender;

    @Column(length = 11)
    private String phone;

    @Column(length = 100)
    private String address;

    public UserVo toVo() {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(this,userVo);
        return userVo;
    }

}

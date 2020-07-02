package com.avic.entity;

import com.avic.vo.SysConfigVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 参数设置
 */
@Data
@Entity
@Table(name = "sys_config")
public class SysConfig implements Serializable {

    public static String FIRST_PAGE = "first_page";

    public static String ABOUTUS = "about_us";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300)
    private String title;

    @Column(length = 32,unique = true)
    private String type;

    /**
     * 关键字
     */
    @Column(length = 500)
    private String keywords;

    @Column(length = 2000)
    private String description;

    @Column(length = 10000)
    private String content;

    @Column(length = 11)
    private String author;

    @Column(length = 20)
    private String createTime;

    public SysConfigVo toVo() {
        SysConfigVo sysConfigVo = new SysConfigVo();
        BeanUtils.copyProperties(this, sysConfigVo);
        return sysConfigVo;
    }

}

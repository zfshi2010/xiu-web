package com.avic.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 账号
 */
@Data
public class SysConfigVo implements Serializable {

    private Long id;

    private String title;

    private String type;

    /**
     * 关键字
     */
    private String keywords;

    private String description;

    private String content;

    private String author;

    private String createTime;

}

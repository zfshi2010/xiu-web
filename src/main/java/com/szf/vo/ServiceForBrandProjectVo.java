package com.szf.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 品牌服务项目
 */
@Data
public class ServiceForBrandProjectVo implements Serializable {

    private Long id;

    private Long serviceForBrandId;

    private String title;

    private String img;

    /**
     * 概述
     */
    private String overview;

    private String content;

    private String author;

    private String createTime;

}

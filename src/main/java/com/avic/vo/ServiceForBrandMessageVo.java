package com.avic.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 服务类型不是菜单，类型下，服务常识信息
 */
@Data
public class ServiceForBrandMessageVo implements Serializable {

    private Long id;

    private Long serviceForBrandId;

    private String title;

    /**
     * 概述
     */
    private String overview;

    private String content;

    private String author;

    private String createTime;

}

package com.szf.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 服务类型为菜单时，菜单的信息
 */
@Data
public class ServiceForTypeMessageVo implements Serializable {

    private Long id;

    private Long serviceForTypeId;

    private String title;

    /**
     * 概述
     */
    private String overview;

    private String content;

    private String author;

    private String createTime;

}

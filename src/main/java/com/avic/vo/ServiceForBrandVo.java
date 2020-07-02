package com.avic.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 服务的品牌
 */
@Data
public class ServiceForBrandVo implements Serializable {

    private Long id;

    /**
     * 所属类型id
     */
    private Long typeId;

    /**
     * 品牌名称
     */
    private String brandName;


    private String title;

    /**
     * 关键字
     */
    private String keywords;

    private String description;

    private String author;

    private String createTime;

}

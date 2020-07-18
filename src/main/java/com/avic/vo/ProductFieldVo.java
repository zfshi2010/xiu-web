package com.avic.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 产品领域
 */
@Data
public class ProductFieldVo implements Serializable {

    private Long id;

    private Long productBrandId;

    private String name;

    private String title;

    private String img;

    /**
     * 关键字
     */
    private String keywords;

    private String description;

    private String author;

    private String createTime;


}

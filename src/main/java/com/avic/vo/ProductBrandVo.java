package com.avic.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 产品品牌
 */
@Data
public class ProductBrandVo implements Serializable {

    private Long id;

    private Long productFieldId;

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

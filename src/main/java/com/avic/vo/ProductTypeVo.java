package com.avic.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 产品类型
 */
@Data
public class ProductTypeVo implements Serializable {

    private Long id;

    private Long productFieldId;

    private String name;

    private String title;

    /**
     * 关键字
     */
    private String keywords;

    private String description;

    private String author;

    private String createTime;

}

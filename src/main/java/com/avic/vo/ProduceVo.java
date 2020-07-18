package com.avic.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 产品信息
 */
@Data
public class ProduceVo implements Serializable {

    private Long id;

    private Long productFieldId;

    private Long productBrandId;

    private Long productTypeId;

    private String name;

    private String img;

    private String paramContent;

    private String tags;

    /**
     * 概述
     */
    private String overview;

    private String content;

    private String video;

    private String pdfFile;

    private String author;

    private String createTime;

}

package com.avic.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 账号
 */
@Data
public class MessageVo implements Serializable {

    private Long id;

    private String title;

    /**
     * 概述
     */
    private String overview;

    private String content;

    private String smallImg;

    private String img;

    private String author;

    private String createTime;

}

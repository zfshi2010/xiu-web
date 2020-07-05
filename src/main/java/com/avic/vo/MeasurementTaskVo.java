package com.avic.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 测量任务
 */
@Data
public class MeasurementTaskVo implements Serializable {

    private Long id;

    private String name;

    private String author;

    private String createTime;

}

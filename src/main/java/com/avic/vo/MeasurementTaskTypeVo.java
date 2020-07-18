package com.avic.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 测量任务产品类型
 */
@Data
public class MeasurementTaskTypeVo implements Serializable {

    private Long id;

    private Long measurementTaskId;

    private String name;

    private String author;

    private String createTime;

}

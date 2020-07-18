package com.avic.vo;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 测量任务产品类型
 */
@Data
public class MeasurementTaskTypeParameterVo implements Serializable {

    private Long id;

    private Long measurementTaskTypeId;

    private Long parameterId;

    private String createTime;

}

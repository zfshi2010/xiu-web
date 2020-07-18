package com.avic.entity;

import com.avic.vo.MeasurementTaskTypeParameterVo;
import com.avic.vo.MeasurementTaskTypeVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 测量任务产品类型绑定的属性
 */
@Data
@Entity
@Table(name = "measurement_task_type_parameter")
public class MeasurementTaskTypeParameter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long measurementTaskTypeId;

    private Long parameterId;

    @Column(length = 20)
    private String createTime;

    public MeasurementTaskTypeParameterVo toVo() {
        MeasurementTaskTypeParameterVo measurementTaskTypeParameterVo = new MeasurementTaskTypeParameterVo();
        BeanUtils.copyProperties(this, measurementTaskTypeParameterVo);
        return measurementTaskTypeParameterVo;
    }

}

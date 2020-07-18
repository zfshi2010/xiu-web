package com.avic.entity;

import com.avic.vo.MeasurementTaskTypeVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 测量任务产品类型
 */
@Data
@Entity
@Table(name = "measurement_task_type")
public class MeasurementTaskType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long measurementTaskId;

    @Column(length = 100)
    private String name;

    @Column(length = 11)
    private String author;

    @Column(length = 20)
    private String createTime;

    public MeasurementTaskTypeVo toVo() {
        MeasurementTaskTypeVo measurementTaskTypeVo = new MeasurementTaskTypeVo();
        BeanUtils.copyProperties(this, measurementTaskTypeVo);
        return measurementTaskTypeVo;
    }

}

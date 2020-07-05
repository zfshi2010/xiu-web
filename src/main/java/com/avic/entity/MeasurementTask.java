package com.avic.entity;

import com.avic.vo.MeasurementTaskVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 测量任务
 */
@Data
@Entity
@Table(name = "measurement_task")
public class MeasurementTask implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 11)
    private String author;

    @Column(length = 20)
    private String createTime;

    public MeasurementTaskVo toVo() {
        MeasurementTaskVo measurementTaskVo = new MeasurementTaskVo();
        BeanUtils.copyProperties(this, measurementTaskVo);
        return measurementTaskVo;
    }

}

package com.avic.entity;

import com.avic.vo.ParameterVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 参数
 */
@Data
@Entity
@Table(name = "parameter")
public class Parameter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 11)
    private String author;

    @Column(length = 20)
    private String createTime;

    public ParameterVo toVo() {
        ParameterVo parameterVo = new ParameterVo();
        BeanUtils.copyProperties(this, parameterVo);
        return parameterVo;
    }

}

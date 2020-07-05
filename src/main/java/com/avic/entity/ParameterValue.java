package com.avic.entity;

import com.avic.vo.ParameterValueVo;
import com.avic.vo.ParameterVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 参数可选值
 */
@Data
@Entity
@Table(name = "parameter_value")
public class ParameterValue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parameterId;

    @Column(length = 100)
    private String value;

    /**
     * 是否可以手动输入
     */
    private Boolean ifInput;

    @Column(length = 11)
    private String author;

    @Column(length = 20)
    private String createTime;

    public ParameterValueVo toVo() {
        ParameterValueVo parameterValueVo = new ParameterValueVo();
        BeanUtils.copyProperties(this, parameterValueVo);
        return parameterValueVo;
    }

    public static List<ParameterValueVo> toList(List<ParameterValue> parameterValues) {
        return parameterValues.stream().map(parameterValue -> {return parameterValue.toVo();}).collect(Collectors.toList());
    }

}

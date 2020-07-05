package com.avic.vo;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 参数可选值
 */
@Data
public class ParameterValueVo implements Serializable {

    private Long id;

    private Long parameterId;

    private String value;

    private Boolean ifInput;

    private String author;

    private String createTime;

}

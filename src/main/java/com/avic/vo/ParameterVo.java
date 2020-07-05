package com.avic.vo;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 参数
 */
@Data
public class ParameterVo implements Serializable {

    private Long id;

    private String name;

    private String author;

    private String createTime;

    private List<ParameterValueVo> parameterValues = new ArrayList<>();


}

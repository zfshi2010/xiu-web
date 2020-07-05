package com.avic.vo;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 材质管理
 */
@Data
public class TextureVo implements Serializable {

    private Long id;

    private Long measurementTaskId;

    private String name;

    private String author;

    private String createTime;

}

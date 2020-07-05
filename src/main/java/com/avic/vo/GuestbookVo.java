package com.avic.vo;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * 留言板
 */
@Data
public class GuestbookVo implements Serializable {

    private Long id;

    private Long measurementTaskId;

    private String measurementTaskName;

    private Long textureId;

    private String textureName;

    private String measurementRange;

    private String companyName;

    private String name;

    private String phone;

    private String content;

    private String createTime;

}

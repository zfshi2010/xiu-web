package com.avic.vo;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 留言板
 */
@Data
public class GuestbookVo implements Serializable {

    private Long id;

    private Long measurementTaskId;

    private Long measurementTaskTypeId;

    private String measurementTaskTypeName;

    private String measurementTaskName;

    private Long textureId;

    private String textureName;

    private String measurementRange;

    private String companyName;

    private String name;

    private String phone;

    private String content;

    private String createTime;

    private List<GuestbookParameterValueVo> guestbookParameterValues = new ArrayList<>();

}

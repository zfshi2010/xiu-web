package com.avic.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 留言板
 */
@Data
public class GuestbookParameterValueVo implements Serializable {

    private Long id;

    private Long guestbookId;

    private Long parameterId;

    private String parameterName;

    private Long parameterValueId;

    private Boolean ifInput;

    private String content;

    private String createTime;

}

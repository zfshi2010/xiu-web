package com.avic.vo;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 产品信息评论
 */
@Data
public class ProduceCommentVo implements Serializable {

    private Long id;

    private Long produceId;

    private String content;

    private String createTime;

}

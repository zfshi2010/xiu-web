package com.avic.entity;

import com.avic.vo.ProduceCommentVo;
import com.avic.vo.ProduceVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 产品信息评论
 */
@Data
@Entity
@Table(name = "produce_comment")
public class ProduceComment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long produceId;

    @Column(length = 100)
    private String content;

    @Column(length = 20)
    private String createTime;

    public ProduceCommentVo toVo() {
        ProduceCommentVo produceCommentVo = new ProduceCommentVo();
        BeanUtils.copyProperties(this, produceCommentVo);
        return produceCommentVo;
    }

}

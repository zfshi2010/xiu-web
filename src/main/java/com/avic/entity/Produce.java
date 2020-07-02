package com.avic.entity;

import com.avic.vo.ProduceVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 差评信息
 */
@Data
@Entity
@Table(name = "produce")
public class Produce implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productFieldId;

    private Long productTypeId;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String img;

    @Column(length = 100)
    private String paramImg;

    /**
     * 概述
     */
    @Column(length = 300)
    private String overview;

    @Column(length = 10000)
    private String content;

    @Column(length = 100)
    private String video;

    @Column(length = 11)
    private String author;

    @Column(length = 20)
    private String createTime;

    public ProduceVo toVo() {
        ProduceVo produceVo = new ProduceVo();
        BeanUtils.copyProperties(this, produceVo);
        return produceVo;
    }

}

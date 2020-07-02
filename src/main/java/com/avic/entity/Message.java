package com.avic.entity;

import com.avic.vo.MessageVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 信息
 */
@Data
@Entity
@Table(name = "message")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    /**
     * 概述
     */
    @Column(length = 300)
    private String overview;

    @Column(length = 10000)
    private String content;

    @Column(length = 100)
    private String smallImg;

    @Column(length = 100)
    private String img;

    @Column(length = 11)
    private String author;

    @Column(length = 20)
    private String createTime;

    public MessageVo toVo() {
        MessageVo messageVo = new MessageVo();
        BeanUtils.copyProperties(this, messageVo);
        return messageVo;
    }

}

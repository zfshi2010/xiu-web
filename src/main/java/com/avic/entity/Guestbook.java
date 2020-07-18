package com.avic.entity;

import com.avic.vo.GuestbookVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 在线选型
 */
@Data
@Entity
@Table(name = "guestbook")
public class Guestbook implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long measurementTaskId;

    private Long measurementTaskTypeId;

    private Long textureId;

    @Column(length = 200)
    private String measurementRange;

    private String companyName;

    @Column(length = 20)
    private String name;

    @Column(length = 15)
    private String phone;

    @Column(length = 500)
    private String content;

    @Column(length = 20)
    private String createTime;

    public Guestbook(){}

    public Guestbook(GuestbookVo guestbookVo) {
        BeanUtils.copyProperties(guestbookVo, this);
    }

    public GuestbookVo toVo() {
        GuestbookVo guestbookVo = new GuestbookVo();
        BeanUtils.copyProperties(this, guestbookVo);
        return guestbookVo;
    }

}

package com.szf.entity;

import com.szf.vo.GuestbookVo;
import com.szf.vo.MessageVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 留言板
 */
@Data
@Entity
@Table(name = "guestbook")
public class Guestbook implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(length = 15)
    private String phone;

    @Column(length = 100)
    private String address;

    @Column(length = 300)
    private String content;

    @Column(length = 2)
    private String trade;

    @Column(length = 20)
    private String createTime;

    public GuestbookVo toVo() {
        GuestbookVo guestbookVo = new GuestbookVo();
        BeanUtils.copyProperties(this, guestbookVo);
        if (trade.equals("1")) {
            guestbookVo.setTradeName("报修内");
        } else {
            guestbookVo.setTradeName("报修外");
        }
        return guestbookVo;
    }

}

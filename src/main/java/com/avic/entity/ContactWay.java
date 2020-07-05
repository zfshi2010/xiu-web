package com.avic.entity;

import com.avic.vo.ContactWayVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 联系方式
 */
@Data
@Entity
@Table(name = "contact_way")
public class ContactWay implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 15)
    private String phone;

    @Column(length = 11)
    private String author;

    @Column(length = 20)
    private String createTime;

    public ContactWayVo toVo() {
        ContactWayVo contactWayVo = new ContactWayVo();
        BeanUtils.copyProperties(this, contactWayVo);
        return contactWayVo;
    }

}

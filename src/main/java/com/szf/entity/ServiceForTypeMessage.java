package com.szf.entity;

import com.szf.vo.ServiceForTypeMessageVo;
import com.szf.vo.ServiceForTypeVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 服务类型为菜单时，菜单的信息
 */
@Data
@Entity
@Table(name = "service_for_type_message")
public class ServiceForTypeMessage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long serviceForTypeId;

    @Column(length = 100)
    private String title;

    /**
     * 概述
     */
    @Column(length = 300)
    private String overview;

    @Column(length = 10000)
    private String content;

    @Column(length = 11)
    private String author;

    @Column(length = 20)
    private String createTime;

    public ServiceForTypeMessageVo toVo() {
        ServiceForTypeMessageVo serviceForTypeMessageVo = new ServiceForTypeMessageVo();
        BeanUtils.copyProperties(this, serviceForTypeMessageVo);
        return serviceForTypeMessageVo;
    }

}

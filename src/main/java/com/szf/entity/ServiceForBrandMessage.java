package com.szf.entity;

import com.szf.vo.ServiceForBrandMessageVo;
import com.szf.vo.ServiceForTypeMessageVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 服务类型不是菜单，类型下，服务常识信息
 */
@Data
@Entity
@Table(name = "service_for_brand_message")
public class ServiceForBrandMessage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long serviceForBrandId;

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

    public ServiceForBrandMessageVo toVo() {
        ServiceForBrandMessageVo serviceForBrandMessageVo = new ServiceForBrandMessageVo();
        BeanUtils.copyProperties(this, serviceForBrandMessageVo);
        return serviceForBrandMessageVo;
    }

}

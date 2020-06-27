package com.szf.entity;

import com.szf.vo.ServiceForBrandMessageVo;
import com.szf.vo.ServiceForBrandProjectVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 品牌服务项目
 */
@Data
@Entity
@Table(name = "service_for_brand_project")
public class ServiceForBrandProject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long serviceForBrandId;

    @Column(length = 100)
    private String title;

    @Column(length = 100)
    private String img;

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

    public ServiceForBrandProjectVo toVo() {
        ServiceForBrandProjectVo serviceForBrandProjectVo = new ServiceForBrandProjectVo();
        BeanUtils.copyProperties(this, serviceForBrandProjectVo);
        return serviceForBrandProjectVo;
    }

}

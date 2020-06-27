package com.szf.entity;

import com.szf.vo.ServiceForBrandVo;
import com.szf.vo.ServiceForTypeVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 服务的品牌
 */
@Data
@Entity
@Table(name = "service_for_brand")
public class ServiceForBrand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 所属类型id
     */
    private Long typeId;

    /**
     * 品牌名称
     */
    @Column(length = 300)
    private String brandName;


    @Column(length = 300)
    private String title;

    /**
     * 关键字
     */
    @Column(length = 500)
    private String keywords;

    @Column(length = 2000)
    private String description;

    @Column(length = 11)
    private String author;

    @Column(length = 20)
    private String createTime;

    public ServiceForBrandVo toVo() {
        ServiceForBrandVo serviceForBrandVo = new ServiceForBrandVo();
        BeanUtils.copyProperties(this, serviceForBrandVo);
        return serviceForBrandVo;
    }

}

package com.avic.entity;

import com.avic.vo.ProductFieldVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 产品领域
 */
@Data
@Entity
@Table(name = "product_field")
public class ProductField implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 领域名称
     */
    @Column(length = 300)
    private String name;

    @Column(length = 300)
    private String title;

    @Column(length = 100)
    private String img;

    /**
     * 关键字
     */
    @Column(length = 500)
    private String keywords;

    @Column(length = 2000)
    private String description;

    @Column(length = 50)
    private String author;

    @Column(length = 20)
    private String createTime;

    public ProductFieldVo toVo() {
        ProductFieldVo serviceForTypeVo = new ProductFieldVo();
        BeanUtils.copyProperties(this, serviceForTypeVo);
        return serviceForTypeVo;
    }

}

package com.avic.entity;

import com.avic.vo.ProductBrandVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 产品品牌
 */
@Data
@Entity
@Table(name = "product_brand")
public class ProductBrand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productFieldId;

    @Column(length = 300)
    private String name;

    @Column(length = 100)
    private String img;

    @Column(length = 100)
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

    public ProductBrandVo toVo() {
        ProductBrandVo productBrandVo = new ProductBrandVo();
        BeanUtils.copyProperties(this, productBrandVo);
        return productBrandVo;
    }

}

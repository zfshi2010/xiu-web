package com.avic.entity;

import com.avic.vo.ProductTypeVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 产品类型
 */
@Data
@Entity
@Table(name = "product_type")
public class ProductType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productFieldId;

    @Column(length = 300)
    private String name;

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

    public ProductTypeVo toVo() {
        ProductTypeVo productTypeVo = new ProductTypeVo();
        BeanUtils.copyProperties(this, productTypeVo);
        return productTypeVo;
    }

}

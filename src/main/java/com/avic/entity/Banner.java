package com.avic.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 图片信息
 */
@Data
@Entity
@Table(name = "banner")
public class Banner implements Serializable {

    public static final String BANNER = "banner";

    public static final String LOGO = "logo";

    public static final String PHONE = "phone";

    public static final String ADVERT = "advert";

    public static final String BRAND_BANNER = "brand_banner";


    public static final String BRAND_LOGO = "brand_logo";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(length = 20)
    private String type;

    @Transient
    private String typeName;

    private Long serviceForBrandId;

    /**
     * 概述
     */
    @Column(length = 300)
    private String alt;

    @Column(length = 100)
    private String img;

    @Column(length = 11)
    private String author;

    @Column(length = 20)
    private String createTime;

}

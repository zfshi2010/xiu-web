package com.avic.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 友情链接
 */
@Data
@Entity
@Table(name = "blogroll")
public class Blogroll implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 300)
    private String link;

    @Column(length = 11)
    private String author;

    @Column(length = 20)
    private String createTime;

}

package com.avic.entity;

import com.avic.vo.TextureVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 材质管理
 */
@Data
@Entity
@Table(name = "texture")
public class Texture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long measurementTaskId;

    @Column(length = 100)
    private String name;


    @Column(length = 11)
    private String author;

    @Column(length = 20)
    private String createTime;

    public TextureVo toVo() {
        TextureVo textureVo = new TextureVo();
        BeanUtils.copyProperties(this, textureVo);
        return textureVo;
    }

}

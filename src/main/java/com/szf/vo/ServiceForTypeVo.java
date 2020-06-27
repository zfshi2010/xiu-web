package com.szf.vo;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * 服务类型
 */
@Data
public class ServiceForTypeVo implements Serializable {

    private Long id;



    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 作为菜单的名称
     */
    private String menuName;

    /**
     * 是否作为菜单
     */
    private Boolean isMenu;


    private String title;

    private String img;

    /**
     * 关键字
     */
    private String keywords;

    private String description;

    private String author;

    private String createTime;


}

package com.szf.entity;

import com.szf.vo.ServiceForTypeVo;
import com.szf.vo.SysConfigVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务类型
 */
@Data
@Entity
@Table(name = "service_for_type")
public class ServiceForType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 类型名称
     */
    @Column(length = 300)
    private String typeName;

    /**
     * 作为菜单的名称
     */
    @Column(length = 300)
    private String menuName;

    /**
     * 是否作为菜单
     */
    private Boolean isMenu;


    @Column(length = 300)
    private String title;

    @Transient
    private List<ServiceForTypeMessage> serviceForTypeMessages = new ArrayList<>();

    @Column(length = 100)
    private String img;

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

    public ServiceForTypeVo toVo() {
        ServiceForTypeVo serviceForTypeVo = new ServiceForTypeVo();
        BeanUtils.copyProperties(this, serviceForTypeVo);
        return serviceForTypeVo;
    }

}

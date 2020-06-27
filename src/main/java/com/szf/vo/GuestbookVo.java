package com.szf.vo;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 留言板
 */
@Data
public class GuestbookVo implements Serializable {

    private Long id;

    private String name;

    private String phone;

    private String address;

    private String content;

    private String trade;

    private String tradeName;

    private String createTime;

}

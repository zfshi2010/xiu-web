package com.avic.entity;

import com.avic.vo.GuestbookParameterValueVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 在线选型其他属性
 */
@Data
@Entity
@Table(name = "guestbook_parameter_value")
public class GuestbookParameterValue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long guestbookId;

    private Long parameterId;

    @Column(length = 50)
    private String parameterName;

    private Long parameterValueId;

    @Column(length = 50)
    private String parameterValueName;

    private Boolean ifInput;

    @Column(length = 500)
    private String content;

    @Column(length = 20)
    private String createTime;


    public GuestbookParameterValue() {}

    public GuestbookParameterValue(GuestbookParameterValueVo guestbookParameterValueVo) {
        BeanUtils.copyProperties(guestbookParameterValueVo, this);
    }

    public GuestbookParameterValueVo toVo() {
        GuestbookParameterValueVo guestbookParameterValueVo = new GuestbookParameterValueVo();
        BeanUtils.copyProperties(this, guestbookParameterValueVo);
        return guestbookParameterValueVo;
    }


    public static List<GuestbookParameterValueVo> toList(List<GuestbookParameterValue> guestbookParameterValues) {
        return guestbookParameterValues.stream().map(guestbookParameterValue ->
        {return guestbookParameterValue.toVo();}).collect(Collectors.toList());
    }

}

package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.Guestbook;
import com.avic.entity.GuestbookParameterValue;
import com.avic.entity.MeasurementTask;
import com.avic.entity.Texture;
import com.avic.repository.GuestbookParameterValueRepository;
import com.avic.repository.GuestbookRepository;
import com.avic.repository.MeasurementTaskRepository;
import com.avic.repository.TextureRepository;
import com.avic.vo.GuestbookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/guestbook")
public class AdminGuestbookController extends BaseController {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Autowired
    private MeasurementTaskRepository measurementTaskRepository;

    @Autowired
    private TextureRepository textureRepository;

    @Autowired
    private GuestbookParameterValueRepository guestbookParameterValueRepository;

    @RequestMapping("/index")
    public String index() throws Exception{
        return "admin/guestbook/list";
    }



    @RequestMapping("/detail")
    public String detail(ModelMap model, Long id) throws Exception{
        Guestbook guestbook = guestbookRepository.findOne(id);
        GuestbookVo guestbookVo = guestbook.toVo();

        if (guestbookVo.getMeasurementTaskId() != null) {
            MeasurementTask measurementTask = measurementTaskRepository.findOne(guestbookVo.getMeasurementTaskId());
            if (measurementTask != null) {
                guestbookVo.setMeasurementTaskName(measurementTask.getName());
            }
        }
        if (guestbookVo.getTextureId() != null) {
            Texture texture = textureRepository.findOne(guestbookVo.getTextureId());
            if (texture != null) {
                guestbookVo.setTextureName(texture.getName());
            }
        }

        List<GuestbookParameterValue> guestbookParameterValues = guestbookParameterValueRepository.findByGuestbookId(id);
        model.addAttribute("guestbookParameterValues", guestbookParameterValues);
        model.addAttribute("guestbook", guestbookVo);
        return "admin/guestbook/detail";
    }

}

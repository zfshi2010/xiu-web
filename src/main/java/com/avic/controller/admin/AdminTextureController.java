package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.MeasurementTask;
import com.avic.entity.ParameterValue;
import com.avic.entity.Texture;
import com.avic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/texture")
public class AdminTextureController extends BaseController {

    @Autowired
    private TextureRepository textureRepository;

    @Autowired
    private MeasurementTaskTypeRepository measurementTaskTypeRepository;

    @RequestMapping("/index")
    public String index(ModelMap model, Long measurementTaskTypeId) throws Exception{
        model.addAttribute("measurementTaskTypeId", measurementTaskTypeId);
        model.addAttribute("measurementTaskType", measurementTaskTypeRepository.findOne(measurementTaskTypeId));
        return "admin/texture/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model, Long measurementTaskTypeId) throws Exception{
        model.addAttribute("measurementTaskTypeId", measurementTaskTypeId);
        model.addAttribute("measurementTaskType", measurementTaskTypeRepository.findOne(measurementTaskTypeId));
        return "admin/texture/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        Texture texture = textureRepository.findOne(id);
        model.addAttribute("texture", texture);

        model.addAttribute("measurementTaskTypeId", texture.getMeasurementTaskTypeId());
        model.addAttribute("measurementTaskType", measurementTaskTypeRepository.findOne(texture.getMeasurementTaskTypeId()));

        return "admin/texture/update";
    }

}

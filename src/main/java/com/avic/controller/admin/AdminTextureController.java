package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.MeasurementTask;
import com.avic.entity.ParameterValue;
import com.avic.entity.Texture;
import com.avic.repository.MeasurementTaskRepository;
import com.avic.repository.ParameterRepository;
import com.avic.repository.ParameterValueRepository;
import com.avic.repository.TextureRepository;
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
    private MeasurementTaskRepository measurementTaskRepository;

    @RequestMapping("/index")
    public String index(ModelMap model, Long measurementTaskId) throws Exception{
        model.addAttribute("measurementTaskId", measurementTaskId);
        model.addAttribute("measurementTask", measurementTaskRepository.findOne(measurementTaskId));
        return "admin/texture/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model, Long measurementTaskId) throws Exception{
        model.addAttribute("measurementTaskId", measurementTaskId);
        model.addAttribute("measurementTask", measurementTaskRepository.findOne(measurementTaskId));
        return "admin/texture/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        Texture texture = textureRepository.findOne(id);
        model.addAttribute("texture", texture);

        model.addAttribute("measurementTaskId", texture.getMeasurementTaskId());
        model.addAttribute("measurementTask", measurementTaskRepository.findOne(texture.getMeasurementTaskId()));

        return "admin/texture/update";
    }

}

package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.MeasurementTaskType;
import com.avic.entity.MeasurementTaskTypeParameter;
import com.avic.repository.MeasurementTaskRepository;
import com.avic.repository.MeasurementTaskTypeParameterRepository;
import com.avic.repository.MeasurementTaskTypeRepository;
import com.avic.repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/measurementTaskTypeParameter")
public class AdminMeasurementTaskTypeParameterController extends BaseController {

    @Autowired
    private MeasurementTaskTypeParameterRepository measurementTaskTypeParameterRepository;

    @Autowired
    private MeasurementTaskTypeRepository measurementTaskTypeRepository;

    @Autowired
    private ParameterRepository parameterRepository;

    @RequestMapping("/index")
    public String index(ModelMap model, Long measurementTaskTypeId) throws Exception{
        model.addAttribute("measurementTaskTypeId", measurementTaskTypeId);
        model.addAttribute("measurementTaskType", measurementTaskTypeRepository.findOne(measurementTaskTypeId));
        model.addAttribute("parameters", parameterRepository.findAll());
        return "admin/measurementTaskTypeParameter/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model, Long measurementTaskTypeId) throws Exception{
        model.addAttribute("measurementTaskTypeId", measurementTaskTypeId);
        model.addAttribute("measurementTaskType", measurementTaskTypeRepository.findOne(measurementTaskTypeId));
        return "admin/measurementTaskTypeParameter/add";
    }

}

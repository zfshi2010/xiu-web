package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.MeasurementTaskType;
import com.avic.repository.MeasurementTaskRepository;
import com.avic.repository.MeasurementTaskTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/measurementTaskType")
public class AdminMeasurementTaskTypeController extends BaseController {

    @Autowired
    private MeasurementTaskTypeRepository measurementTaskTypeRepository;

    @Autowired
    private MeasurementTaskRepository measurementTaskRepository;

    @RequestMapping("/index")
    public String index(ModelMap model, Long measurementTaskId) throws Exception{
        model.addAttribute("measurementTaskId", measurementTaskId);
        model.addAttribute("measurementTask", measurementTaskRepository.findOne(measurementTaskId));
        return "admin/measurementTaskType/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model, Long measurementTaskId) throws Exception{
        model.addAttribute("measurementTaskId", measurementTaskId);
        model.addAttribute("measurementTask", measurementTaskRepository.findOne(measurementTaskId));
        return "admin/measurementTaskType/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        MeasurementTaskType measurementTaskType = measurementTaskTypeRepository.findOne(id);
        model.addAttribute("measurementTaskType", measurementTaskType);

        model.addAttribute("measurementTaskId", measurementTaskType.getMeasurementTaskId());
        model.addAttribute("measurementTask", measurementTaskRepository.findOne(measurementTaskType.getMeasurementTaskId()));

        return "admin/measurementTaskType/update";
    }

}

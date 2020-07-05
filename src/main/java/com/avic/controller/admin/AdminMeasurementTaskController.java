package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.ContactWay;
import com.avic.entity.MeasurementTask;
import com.avic.repository.ContactWayRepository;
import com.avic.repository.MeasurementTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/measurementTask")
public class AdminMeasurementTaskController extends BaseController {

    @Autowired
    private MeasurementTaskRepository measurementTaskRepository;

    @RequestMapping("/index")
    public String index() throws Exception{
        return "admin/measurementTask/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model) throws Exception{
        return "admin/measurementTask/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        MeasurementTask measurementTask = measurementTaskRepository.findOne(id);
        model.addAttribute("measurementTask", measurementTask);
        return "admin/measurementTask/update";
    }

}

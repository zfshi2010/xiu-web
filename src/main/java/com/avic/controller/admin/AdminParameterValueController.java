package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.Parameter;
import com.avic.entity.ParameterValue;
import com.avic.repository.ParameterRepository;
import com.avic.repository.ParameterValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/parameterValue")
public class AdminParameterValueController extends BaseController {

    @Autowired
    private ParameterValueRepository parameterValueRepository;

    @Autowired
    private ParameterRepository parameterRepository;

    @RequestMapping("/index")
    public String index(ModelMap model, Long parameterId) throws Exception{
        model.addAttribute("parameterId", parameterId);
        model.addAttribute("parameter", parameterRepository.findOne(parameterId));
        return "admin/parameterValue/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model, Long parameterId) throws Exception{
        model.addAttribute("parameterId", parameterId);
        model.addAttribute("parameter", parameterRepository.findOne(parameterId));
        return "admin/parameterValue/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        ParameterValue parameterValue = parameterValueRepository.findOne(id);
        model.addAttribute("parameterValue", parameterValue);

        model.addAttribute("parameterId", parameterValue.getParameterId());
        model.addAttribute("parameter", parameterRepository.findOne(parameterValue.getParameterId()));

        return "admin/parameterValue/update";
    }

}

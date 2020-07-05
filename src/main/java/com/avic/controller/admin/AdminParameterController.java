package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.Parameter;
import com.avic.repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/parameter")
public class AdminParameterController extends BaseController {

    @Autowired
    private ParameterRepository parameterRepository;

    @RequestMapping("/index")
    public String index() throws Exception{
        return "admin/parameter/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model) throws Exception{
        return "admin/parameter/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        Parameter parameter = parameterRepository.findOne(id);
        model.addAttribute("parameter", parameter);
        return "admin/parameter/update";
    }

}

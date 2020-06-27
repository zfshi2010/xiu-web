package com.szf.controller.admin;

import com.szf.controller.BaseController;
import com.szf.entity.ServiceForBrandProject;
import com.szf.repository.ServiceForBrandProjectRepository;
import com.szf.repository.ServiceForBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/serviceForBrandProject")
public class AdminServiceForBrandProjectController extends BaseController {

    @Autowired
    private ServiceForBrandRepository serviceForBrandRepository;

    @Autowired
    private ServiceForBrandProjectRepository serviceForBrandProjectRepository;

    @RequestMapping("/index")
    public String index(ModelMap model, Long serviceForBrandId) throws Exception{
        model.addAttribute("serviceForBrandId", serviceForBrandId);
        model.addAttribute("serviceForBrand", serviceForBrandRepository.findOne(serviceForBrandId));
        return "admin/serviceForBrandProject/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model, Long serviceForBrandId) throws Exception{
        model.addAttribute("serviceForBrandId", serviceForBrandId);
        model.addAttribute("serviceForBrand", serviceForBrandRepository.findOne(serviceForBrandId));
        return "admin/serviceForBrandProject/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        ServiceForBrandProject serviceForBrandProject = serviceForBrandProjectRepository.findOne(id);
        model.addAttribute("serviceForBrandProject", serviceForBrandProject);

        model.addAttribute("serviceForBrandId", serviceForBrandProject.getServiceForBrandId());
        model.addAttribute("serviceForBrand", serviceForBrandRepository.findOne(serviceForBrandProject.getServiceForBrandId()));
        return "admin/serviceForBrandProject/update";
    }
}

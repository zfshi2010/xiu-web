package com.szf.controller.admin;

import com.szf.controller.BaseController;
import com.szf.entity.ServiceForBrand;
import com.szf.repository.ServiceForBrandRepository;
import com.szf.repository.ServiceForTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/serviceForBrand")
public class AdminServiceForBrandController extends BaseController {

    @Autowired
    private ServiceForTypeRepository serviceForTypeRepository;

    @Autowired
    private ServiceForBrandRepository serviceForBrandRepository;

    @RequestMapping("/index")
    public String index() throws Exception{
        return "admin/serviceForBrand/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model) throws Exception{
        model.addAttribute("serviceForTypes", serviceForTypeRepository.findByIsMenu(false));
        return "admin/serviceForBrand/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        ServiceForBrand serviceForBrand = serviceForBrandRepository.findOne(id);
        model.addAttribute("serviceForTypes", serviceForTypeRepository.findByIsMenu(false));
        model.addAttribute("serviceForBrand", serviceForBrand);
        return "admin/serviceForBrand/update";
    }

}

package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.ServiceForBrand;
import com.avic.repository.ServiceForBrandRepository;
import com.avic.repository.ProductFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/serviceForBrand")
public class AdminServiceForBrandController extends BaseController {

    @Autowired
    private ProductFieldRepository serviceForTypeRepository;

    @Autowired
    private ServiceForBrandRepository serviceForBrandRepository;

    @RequestMapping("/index")
    public String index() throws Exception{
        return "admin/serviceForBrand/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model) throws Exception{
        return "admin/serviceForBrand/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        ServiceForBrand serviceForBrand = serviceForBrandRepository.findOne(id);
        model.addAttribute("serviceForBrand", serviceForBrand);
        return "admin/serviceForBrand/update";
    }

}

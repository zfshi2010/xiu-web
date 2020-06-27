package com.szf.controller.admin;

import com.szf.controller.BaseController;
import com.szf.entity.ServiceForBrand;
import com.szf.entity.ServiceForBrandMessage;
import com.szf.repository.ServiceForBrandMessageRepository;
import com.szf.repository.ServiceForBrandRepository;
import com.szf.repository.ServiceForTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/serviceForBrandMessage")
public class AdminServiceForBrandMessageController extends BaseController {

    @Autowired
    private ServiceForBrandRepository serviceForBrandRepository;

    @Autowired
    private ServiceForBrandMessageRepository serviceForBrandMessageRepository;

    @RequestMapping("/index")
    public String index(ModelMap model, Long serviceForBrandId) throws Exception{
        model.addAttribute("serviceForBrandId", serviceForBrandId);
        model.addAttribute("serviceForBrand", serviceForBrandRepository.findOne(serviceForBrandId));
        return "admin/serviceForBrandMessage/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model, Long serviceForBrandId) throws Exception{
        model.addAttribute("serviceForBrandId", serviceForBrandId);
        model.addAttribute("serviceForBrand", serviceForBrandRepository.findOne(serviceForBrandId));
        return "admin/serviceForBrandMessage/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        ServiceForBrandMessage serviceForBrandMessage = serviceForBrandMessageRepository.findOne(id);
        model.addAttribute("serviceForBrandMessage", serviceForBrandMessage);

        model.addAttribute("serviceForBrandId", serviceForBrandMessage.getServiceForBrandId());
        model.addAttribute("serviceForBrand", serviceForBrandRepository.findOne(serviceForBrandMessage.getServiceForBrandId()));
        return "admin/serviceForBrandMessage/update";
    }
}

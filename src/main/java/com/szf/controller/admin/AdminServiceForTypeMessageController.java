package com.szf.controller.admin;

import com.szf.controller.BaseController;
import com.szf.entity.ServiceForBrandMessage;
import com.szf.entity.ServiceForType;
import com.szf.entity.ServiceForTypeMessage;
import com.szf.repository.ServiceForTypeMessageRepository;
import com.szf.repository.ServiceForTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/serviceForTypeMessage")
public class AdminServiceForTypeMessageController extends BaseController {

    @Autowired
    private ServiceForTypeRepository serviceForTypeRepository;

    @Autowired
    private ServiceForTypeMessageRepository serviceForTypeMessageRepository;

    @RequestMapping("/index")
    public String index(ModelMap model, Long serviceForTypeId) throws Exception{
        model.addAttribute("serviceForTypeId", serviceForTypeId);
        model.addAttribute("serviceForType", serviceForTypeRepository.findOne(serviceForTypeId));
        return "admin/serviceForTypeMessage/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model, Long serviceForTypeId) throws Exception{
        model.addAttribute("serviceForTypeId", serviceForTypeId);
        model.addAttribute("serviceForType", serviceForTypeRepository.findOne(serviceForTypeId));
        return "admin/serviceForTypeMessage/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        ServiceForTypeMessage serviceForTypeMessage = serviceForTypeMessageRepository.findOne(id);
        model.addAttribute("serviceForTypeMessage", serviceForTypeMessage);

        model.addAttribute("serviceForTypeId", serviceForTypeMessage.getServiceForTypeId());
        model.addAttribute("serviceForType", serviceForTypeRepository.findOne(serviceForTypeMessage.getServiceForTypeId()));
        return "admin/serviceForTypeMessage/update";
    }

}

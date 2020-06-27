package com.szf.controller.admin;

import com.szf.controller.BaseController;
import com.szf.entity.Banner;
import com.szf.entity.ServiceForType;
import com.szf.repository.ServiceForTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/serviceForType")
public class AdminServiceForTypeController extends BaseController {

    @Autowired
    private ServiceForTypeRepository serviceForTypeRepository;

    @RequestMapping("/index")
    public String index() throws Exception{
        return "admin/serviceForType/list";
    }

    @RequestMapping("/add")
    public String add() throws Exception{
        return "admin/serviceForType/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        ServiceForType serviceForType = serviceForTypeRepository.findOne(id);
        model.addAttribute("serviceForType", serviceForType);
        return "admin/serviceForType/update";
    }
}

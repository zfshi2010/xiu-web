package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.Blogroll;
import com.avic.entity.ContactWay;
import com.avic.repository.BlogrollRepository;
import com.avic.repository.ContactWayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/contactWay")
public class AdminContactWayController extends BaseController {

    @Autowired
    private ContactWayRepository contactWayRepository;

    @RequestMapping("/index")
    public String index() throws Exception{
        return "admin/contactWay/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model) throws Exception{
        return "admin/contactWay/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        ContactWay contactWay = contactWayRepository.findOne(id);
        model.addAttribute("contactWay", contactWay);
        return "admin/contactWay/update";
    }

}

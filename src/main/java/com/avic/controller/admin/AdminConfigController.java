package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.SysConfig;
import com.avic.repository.SysConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/config")
public class AdminConfigController extends BaseController {

    @Autowired
    SysConfigRepository sysConfigRepository;

    @RequestMapping("/firstPage")
    public String firstPage(ModelMap model) throws Exception{
        SysConfig sysConfig = sysConfigRepository.findByType(SysConfig.FIRST_PAGE);
        if (sysConfig == null) {
            sysConfig = new SysConfig();
        }
        model.addAttribute("sysConfig", sysConfig);
        return "admin/config/firstPage";
    }

    @RequestMapping("/aboutUs")
    public String aboutUs(ModelMap model) throws Exception{
        SysConfig sysConfig = sysConfigRepository.findByType(SysConfig.ABOUTUS);
        if (sysConfig == null) {
            sysConfig = new SysConfig();
        }
        model.addAttribute("sysConfig", sysConfig);
        return "admin/config/aboutUs";
    }

}

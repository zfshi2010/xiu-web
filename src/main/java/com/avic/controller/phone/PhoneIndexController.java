package com.avic.controller.phone;

import com.avic.controller.BaseController;
import com.avic.entity.*;
import com.avic.repository.BannerRepository;
import com.avic.repository.BlogrollRepository;
import com.avic.repository.ContactWayRepository;
import com.avic.repository.SysConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PhoneIndexController extends BaseController {


    @Autowired
    private SysConfigRepository sysConfigRepository;

    @Autowired
    private BlogrollRepository blogrollRepository;

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private ContactWayRepository contactWayRepository;

    private void setContactWay(ModelMap model) {
        List<ContactWay> contactWays = contactWayRepository.findAll();
        model.addAttribute("contactWays", contactWays);
    }

    @RequestMapping("/")
    public String indexPage(ModelMap model) throws Exception{
        return getFirstPage(model);
    }

    @RequestMapping("/index")
    public String index(ModelMap model) throws Exception{
        return getFirstPage(model);
    }

    @RequestMapping("/phone")
    public String firstPage(ModelMap model) throws Exception{
        return getFirstPage(model);
    }

    @RequestMapping("/phone/aboutUs")
    public String aboutUs(ModelMap model) throws Exception{
        return getFirstPage(model);
    }

    private String getFirstPage(ModelMap model) {
        SysConfig firstPage = sysConfigRepository.findByType(SysConfig.FIRST_PAGE);
        model.addAttribute("firstPage", firstPage);
        SysConfig aboutUs = sysConfigRepository.findByType(SysConfig.ABOUTUS);
        model.addAttribute("aboutUs", aboutUs);
        setContactWay(model);
        return "phone/aboutUs";
    }

    @RequestMapping("/phone/index")
    public String phoneIndex(ModelMap model) throws Exception{
        return getFirstPage(model);
    }
}

package com.avic.controller.phone;

import com.avic.controller.BaseController;
import com.avic.entity.*;
import com.avic.repository.*;
import com.avic.service.MessageService;
import com.avic.service.ProductTypeService;
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
    private MessageService messageService;

    @Autowired
    private SysConfigRepository sysConfigRepository;

    @Autowired
    private ProductFieldRepository serviceForTypeRepository;

    @Autowired
    private ServiceForBrandRepository serviceForBrandRepository;

    @Autowired
    private ProductTypeService serviceForTypeMessageService;

    @Autowired
    private BlogrollRepository blogrollRepository;

    @Autowired
    private BannerRepository bannerRepository;

    @RequestMapping("/phone")
    public String firstPage(ModelMap model) throws Exception{
        return getFirstPage(model);
    }

    @RequestMapping("/phone/aboutUs")
    public String aboutUs(ModelMap model) throws Exception{
        SysConfig firstPage = sysConfigRepository.findByType(SysConfig.FIRST_PAGE);
        model.addAttribute("firstPage", firstPage);
        SysConfig aboutUs = sysConfigRepository.findByType(SysConfig.ABOUTUS);
        model.addAttribute("aboutUs", aboutUs);
        return "phone/aboutUs";
    }

    private String getFirstPage(ModelMap model) {
        model.addAttribute("isFirstPage", true);
        SysConfig firstPage = sysConfigRepository.findByType(SysConfig.FIRST_PAGE);
        model.addAttribute("firstPage", firstPage);
        addBlogroll(model);
        setImg(model);

        List<Banner> adverts = bannerRepository.findByType(Banner.ADVERT);
        model.addAttribute("advert", adverts.size() > 0? adverts.get(0) : new Banner());

        List<Blogroll> blogrolls = blogrollRepository.findAll();
        model.addAttribute("blogrolls", blogrolls);

        List<ProductField> serviceForTypes = null;
        List<ProductField> serviceForTypesIsMenu = null;
        model.addAttribute("serviceForTypes", serviceForTypes);
        model.addAttribute("serviceForTypesIsMenu", serviceForTypesIsMenu);

        List<ServiceForBrand> serviceForBrands = new ArrayList<>();
        if (serviceForTypes.size() > 0) {
            serviceForBrands = serviceForBrandRepository.findByTypeId(serviceForTypes.get(0).getId());
        }
        model.addAttribute("serviceForBrands", serviceForBrands);

        serviceForTypesIsMenu = serviceForTypesIsMenu.stream().map(serviceForType -> {
           // serviceForType.setServiceForTypeMessages(serviceForTypeMessageService.index(0,9, serviceForType.getId()).getContent());
            return serviceForType;
        }).collect(Collectors.toList());
        List<ProductField> serviceForTypesIsMenu1;
        List<ProductField> serviceForTypesIsMenu2;
        if (serviceForTypesIsMenu.size() > 6) {
            serviceForTypesIsMenu1 = serviceForTypesIsMenu.subList(0,6);
            serviceForTypesIsMenu2 = serviceForTypesIsMenu.subList(6,serviceForTypesIsMenu.size());
        } else {
            serviceForTypesIsMenu1 = serviceForTypesIsMenu;
            serviceForTypesIsMenu2 = new ArrayList<>();
        }
        model.addAttribute("serviceForTypesIsMenu1", serviceForTypesIsMenu1);
        model.addAttribute("serviceForTypesIsMenu2", serviceForTypesIsMenu2);
        return "phone/index";
    }

    @RequestMapping("/phone/index")
    public String index(ModelMap model) throws Exception{
        return getFirstPage(model);
    }

    private void addBlogroll(ModelMap model) {
        List<Blogroll> blogrolls = blogrollRepository.findAll();
        model.addAttribute("blogrolls", blogrolls);
    }

    private void setImg(ModelMap model) {
        List<Banner> banners = bannerRepository.findByType(Banner.BANNER);
        model.addAttribute("banners", banners);
        List<Banner> logos = bannerRepository.findByType(Banner.LOGO);
        model.addAttribute("logo", logos.size() > 0? logos.get(0) : new Banner());
        List<Banner> phones = bannerRepository.findByType(Banner.PHONE);
        model.addAttribute("phone", phones.size() > 0? phones.get(0) : new Banner());
    }

}

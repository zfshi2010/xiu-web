package com.szf.controller.web;

import com.szf.controller.BaseController;
import com.szf.entity.*;
import com.szf.repository.*;
import com.szf.service.MessageService;
import com.szf.service.ServiceForTypeMessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/guestbook")
public class WebGuestbookController extends BaseController {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Autowired
    private SysConfigRepository sysConfigRepository;

    @Autowired
    private ServiceForTypeRepository serviceForTypeRepository;

    @Autowired
    private ServiceForBrandRepository serviceForBrandRepository;

    @Autowired
    private ServiceForTypeMessageService serviceForTypeMessageService;

    @Autowired
    private BlogrollRepository blogrollRepository;

    @Autowired
    private BannerRepository bannerRepository;

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

        List<ServiceForType> serviceForTypes = serviceForTypeRepository.findByIsMenu(false);
        List<ServiceForType> serviceForTypesIsMenu = serviceForTypeRepository.findByIsMenu(true);
        model.addAttribute("serviceForTypes", serviceForTypes);
        model.addAttribute("serviceForTypesIsMenu", serviceForTypesIsMenu);

        List<ServiceForBrand> serviceForBrands = new ArrayList<>();
        if (serviceForTypes.size() > 0) {
            serviceForBrands = serviceForBrandRepository.findByTypeId(serviceForTypes.get(0).getId());
        }
        model.addAttribute("serviceForBrands", serviceForBrands);

        serviceForTypesIsMenu = serviceForTypesIsMenu.stream().map(serviceForType -> {
            serviceForType.setServiceForTypeMessages(serviceForTypeMessageService.index(0,9, serviceForType.getId()).getContent());
            return serviceForType;
        }).collect(Collectors.toList());
        List<ServiceForType> serviceForTypesIsMenu1;
        List<ServiceForType> serviceForTypesIsMenu2;
        if (serviceForTypesIsMenu.size() > 6) {
            serviceForTypesIsMenu1 = serviceForTypesIsMenu.subList(0,6);
            serviceForTypesIsMenu2 = serviceForTypesIsMenu.subList(6,serviceForTypesIsMenu.size());
        } else {
            serviceForTypesIsMenu1 = serviceForTypesIsMenu;
            serviceForTypesIsMenu2 = new ArrayList<>();
        }
        model.addAttribute("serviceForTypesIsMenu1", serviceForTypesIsMenu1);
        model.addAttribute("serviceForTypesIsMenu2", serviceForTypesIsMenu2);
        return "web/index";
    }

    @RequestMapping("/save")
    public String index(ModelMap model,Guestbook guestbook) throws Exception{
        if (StringUtils.isBlank(guestbook.getName())) {
            model.addAttribute("errorMessage", "您的姓名不能为空!");
            return getFirstPage(model);
        }
        if (StringUtils.isBlank(guestbook.getPhone())) {
            model.addAttribute("errorMessage", "您的电话不能为空!");
            return getFirstPage(model);
        }
        if (guestbook.getName().length() > 10) {
            model.addAttribute("errorMessage", "您的姓名过长!");
            return getFirstPage(model);
        }
        if (guestbook.getPhone().length() > 15) {
            model.addAttribute("errorMessage", "您的电话过长!");
            return getFirstPage(model);
        }

        if (StringUtils.isNotBlank(guestbook.getAddress()) && guestbook.getAddress().length() > 100) {
            model.addAttribute("errorMessage", "地址过长!");
            return getFirstPage(model);
        }
        if (StringUtils.isNotBlank(guestbook.getContent()) && guestbook.getContent().length() > 200) {
            model.addAttribute("errorMessage", "留言过长!");
            return getFirstPage(model);
        }

        SysConfig firstPage = sysConfigRepository.findByType(SysConfig.FIRST_PAGE);
        model.addAttribute("firstPage", firstPage);
        addBlogroll(model);
        setImg(model);
        List<ServiceForType> serviceForTypes = serviceForTypeRepository.findByIsMenu(false);
        List<ServiceForType> serviceForTypesIsMenu = serviceForTypeRepository.findByIsMenu(true);
        model.addAttribute("serviceForTypes", serviceForTypes);
        model.addAttribute("serviceForTypesIsMenu", serviceForTypesIsMenu);

        List<ServiceForBrand> serviceForBrands = new ArrayList<>();
        if (serviceForTypes.size() > 0) {
            serviceForBrands = serviceForBrandRepository.findByTypeId(serviceForTypes.get(0).getId());
        }
        model.addAttribute("serviceForBrands", serviceForBrands);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        guestbook.setCreateTime(nowText);
        guestbookRepository.save(guestbook);
        return "web/guestbook_success";
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

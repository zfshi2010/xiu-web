package com.szf.controller.web;

import com.szf.controller.BaseController;
import com.szf.entity.*;
import com.szf.repository.*;
import com.szf.service.MessageService;
import com.szf.service.ServiceForTypeMessageService;
import com.szf.vo.ServiceForTypeMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/serviceForTypeMessage")
public class WebServiceForTypeMessageController extends BaseController {

    @Autowired
    private ServiceForTypeMessageRepository serviceForTypeMessageRepository;

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

    private void setImg(ModelMap model) {
        List<Banner> banners = bannerRepository.findByType(Banner.BANNER);
        model.addAttribute("banners", banners);
        List<Banner> logos = bannerRepository.findByType(Banner.LOGO);
        model.addAttribute("logo", logos.size() > 0? logos.get(0) : new Banner());
        List<Banner> phones = bannerRepository.findByType(Banner.PHONE);
        model.addAttribute("phone", phones.size() > 0? phones.get(0) : new Banner());
    }

    private void addBlogroll(ModelMap model) {
        List<Blogroll> blogrolls = blogrollRepository.findAll();
        model.addAttribute("blogrolls", blogrolls);
    }

    /**
     * 菜单的，进去看信息列表
     * @param model
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/detail_{id}")
    public String detail(ModelMap model, @PathVariable Long id) throws Exception{
        addBlogroll(model);
        setImg(model);

        ServiceForTypeMessageVo serviceForTypeMessageVo = serviceForTypeMessageRepository.getOne(id).toVo();
        model.addAttribute("serviceForTypeId", serviceForTypeMessageVo.getServiceForTypeId());
        model.addAttribute("serviceForTypeMessage", serviceForTypeMessageVo);

        List<ServiceForType> serviceForTypes = serviceForTypeRepository.findByIsMenu(false);
        List<ServiceForType> serviceForTypesIsMenu = serviceForTypeRepository.findByIsMenu(true);
        model.addAttribute("serviceForTypes", serviceForTypes);
        model.addAttribute("serviceForTypesIsMenu", serviceForTypesIsMenu);

        List<ServiceForBrand> serviceForBrands = new ArrayList<>();
        if (serviceForTypes.size() > 0) {
            serviceForBrands = serviceForBrandRepository.findByTypeId(serviceForTypes.get(0).getId());
        }
        model.addAttribute("serviceForBrands", serviceForBrands);

        Page<ServiceForTypeMessage> serviceForTypeMessagePage = serviceForTypeMessageService.index(0,10, serviceForTypeMessageVo.getServiceForTypeId());
        model.addAttribute("serviceForTypeMessagePage", serviceForTypeMessagePage);
        model.addAttribute("totalPages", serviceForTypeMessagePage.getTotalPages() ==0 ? 1 : serviceForTypeMessagePage.getTotalPages());
        return "web/serviceForTypeMessage/detail";
    }



}

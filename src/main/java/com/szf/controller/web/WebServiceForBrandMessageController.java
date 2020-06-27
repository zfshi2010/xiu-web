package com.szf.controller.web;

import com.szf.controller.BaseController;
import com.szf.entity.*;
import com.szf.repository.*;
import com.szf.service.MessageService;
import com.szf.service.ServiceForBrandMessageService;
import com.szf.vo.ServiceForBrandMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/serviceForBrandMessage")
public class WebServiceForBrandMessageController extends BaseController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ServiceForBrandMessageRepository serviceForBrandMessageRepository;

    @Autowired
    private SysConfigRepository sysConfigRepository;

    @Autowired
    private ServiceForTypeRepository serviceForTypeRepository;

    @Autowired
    private ServiceForBrandRepository serviceForBrandRepository;

    @Autowired
    private BannerRepository bannerRepository;


    /**
     * @param model
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/detail_{id}")
    public String detail(ModelMap model, @PathVariable Long id) throws Exception{
        ServiceForBrandMessageVo serviceForBrandMessageVo = serviceForBrandMessageRepository.getOne(id).toVo();
        model.addAttribute("serviceForBrandId", serviceForBrandMessageVo.getServiceForBrandId());
        model.addAttribute("serviceForBrandMessage", serviceForBrandMessageVo);

        List<Banner> phones = bannerRepository.findByType(Banner.PHONE);
        model.addAttribute("phone", phones.size() > 0? phones.get(0) : new Banner());

        List<Banner> logos = bannerRepository.findByTypeAndServiceForBrandId(Banner.BRAND_LOGO, serviceForBrandMessageVo.getServiceForBrandId());
        model.addAttribute("logo", logos.size() > 0? logos.get(0) : new Banner());

        List<Banner> banners = bannerRepository.findByTypeAndServiceForBrandId(Banner.BRAND_BANNER, serviceForBrandMessageVo.getServiceForBrandId());
        model.addAttribute("banners", banners);

        ServiceForBrand serviceForBrand = serviceForBrandRepository.findOne(serviceForBrandMessageVo.getServiceForBrandId());
        model.addAttribute("serviceForBrand", serviceForBrand);

        List<ServiceForType> serviceForTypes = serviceForTypeRepository.findByIsMenu(false);
        List<ServiceForType> serviceForTypesIsMenu = serviceForTypeRepository.findByIsMenu(true);
        model.addAttribute("serviceForTypes", serviceForTypes);
        model.addAttribute("serviceForTypesIsMenu", serviceForTypesIsMenu);

        List<ServiceForBrand> serviceForBrands = new ArrayList<>();
        if (serviceForTypes.size() > 0) {
            serviceForBrands = serviceForBrandRepository.findByTypeId(serviceForTypes.get(0).getId());
        }
        model.addAttribute("serviceForBrands", serviceForBrands);

        List<ServiceForBrandMessage> serviceForBrandMessages = serviceForBrandMessageRepository.findByServiceForBrandId(serviceForBrandMessageVo.getServiceForBrandId());
        model.addAttribute("serviceForBrandMessages", serviceForBrandMessages);
        return "web/serviceForBrandMessage/detail";
    }



}

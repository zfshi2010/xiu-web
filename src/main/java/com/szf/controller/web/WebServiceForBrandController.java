package com.szf.controller.web;

import com.szf.controller.BaseController;
import com.szf.entity.*;
import com.szf.repository.*;
import com.szf.service.MessageService;
import com.szf.service.ServiceForBrandMessageService;
import com.szf.service.ServiceForTypeMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/serviceForBrand")
public class WebServiceForBrandController extends BaseController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SysConfigRepository sysConfigRepository;

    @Autowired
    private ServiceForTypeRepository serviceForTypeRepository;

    @Autowired
    private ServiceForBrandRepository serviceForBrandRepository;

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private ServiceForBrandMessageRepository serviceForBrandMessageRepository;

    @Autowired
    private ServiceForBrandProjectRepository serviceForBrandProjectRepository;

    @RequestMapping("/index-{id}")
    public String index(ModelMap model, @PathVariable Long id) throws Exception{

        List<Banner> phones = bannerRepository.findByType(Banner.PHONE);
        model.addAttribute("phone", phones.size() > 0? phones.get(0) : new Banner());

        List<Banner> logos = bannerRepository.findByTypeAndServiceForBrandId(Banner.BRAND_LOGO, id);
        model.addAttribute("logo", logos.size() > 0? logos.get(0) : new Banner());

        List<Banner> banners = bannerRepository.findByTypeAndServiceForBrandId(Banner.BRAND_BANNER, id);
        model.addAttribute("banners", banners);

        ServiceForBrand serviceForBrand = serviceForBrandRepository.findOne(id);
        model.addAttribute("serviceForBrand", serviceForBrand);

        model.addAttribute("serviceForTypeId", serviceForBrand.getTypeId());
        model.addAttribute("serviceForBrandId", id);

        List<ServiceForBrand> serviceForBrands = serviceForBrandRepository.findByTypeId(serviceForBrand.getTypeId());
        model.addAttribute("serviceForBrands", serviceForBrands);

        List<ServiceForBrandMessage> serviceForBrandMessages = serviceForBrandMessageRepository.findByServiceForBrandId(id);
        model.addAttribute("serviceForBrandMessages", serviceForBrandMessages);


        List<ServiceForBrandProject> serviceForBrandProjects = serviceForBrandProjectRepository.findByServiceForBrandId(id);
        model.addAttribute("serviceForBrandProjects", serviceForBrandProjects);

        return "web/serviceForBrand/index";
    }
}
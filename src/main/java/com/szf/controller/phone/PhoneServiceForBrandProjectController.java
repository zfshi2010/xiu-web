package com.szf.controller.phone;

import com.szf.controller.BaseController;
import com.szf.entity.Banner;
import com.szf.entity.ServiceForBrand;
import com.szf.entity.ServiceForBrandProject;
import com.szf.entity.ServiceForType;
import com.szf.repository.*;
import com.szf.service.MessageService;
import com.szf.vo.ServiceForBrandProjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/phone/serviceForBrandProject")
public class PhoneServiceForBrandProjectController extends BaseController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ServiceForBrandProjectRepository serviceForBrandProjectRepository;

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
        ServiceForBrandProjectVo serviceForBrandProjectVo = serviceForBrandProjectRepository.getOne(id).toVo();
        model.addAttribute("serviceForBrandId", serviceForBrandProjectVo.getServiceForBrandId());
        model.addAttribute("serviceForBrandProject", serviceForBrandProjectVo);

        List<Banner> phones = bannerRepository.findByType(Banner.PHONE);
        model.addAttribute("phone", phones.size() > 0? phones.get(0) : new Banner());

        List<Banner> logos = bannerRepository.findByTypeAndServiceForBrandId(Banner.BRAND_LOGO, serviceForBrandProjectVo.getServiceForBrandId());
        model.addAttribute("logo", logos.size() > 0? logos.get(0) : new Banner());

        List<Banner> banners = bannerRepository.findByTypeAndServiceForBrandId(Banner.BRAND_BANNER, serviceForBrandProjectVo.getServiceForBrandId());
        model.addAttribute("banners", banners);

        ServiceForBrand serviceForBrand = serviceForBrandRepository.findOne(serviceForBrandProjectVo.getServiceForBrandId());
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

        List<ServiceForBrandProject> serviceForBrandProjects = serviceForBrandProjectRepository.findByServiceForBrandId(serviceForBrandProjectVo.getServiceForBrandId());
        model.addAttribute("serviceForBrandProjects", serviceForBrandProjects);
        return "web/serviceForBrandProject/detail";
    }



}

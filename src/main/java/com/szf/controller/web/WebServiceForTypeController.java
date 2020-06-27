package com.szf.controller.web;

import com.szf.controller.BaseController;
import com.szf.entity.*;
import com.szf.repository.*;
import com.szf.service.MessageService;
import com.szf.service.ServiceForTypeMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/serviceForType")
public class WebServiceForTypeController extends BaseController {

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

    @RequestMapping("/index")
    public String index(ModelMap model) throws Exception{
        addBlogroll(model);
        setImg(model);

        List<ServiceForType> serviceForTypes = serviceForTypeRepository.findByIsMenu(false);
        List<ServiceForType> serviceForTypesIsMenu = serviceForTypeRepository.findByIsMenu(true);
        model.addAttribute("serviceForTypes", serviceForTypes);
        model.addAttribute("serviceForTypesIsMenu", serviceForTypesIsMenu);

        List<ServiceForBrand> serviceForBrands = new ArrayList<>();
        if (serviceForTypes.size() > 0) {
            model.addAttribute("serviceForTypeId", serviceForTypes.get(0).getId());
            ServiceForType serviceForType = serviceForTypeRepository.findOne(serviceForTypes.get(0).getId());
            model.addAttribute("serviceForType", serviceForType);
            serviceForBrands = serviceForBrandRepository.findByTypeId(serviceForTypes.get(0).getId());
        } else {
            model.addAttribute("serviceForType", new ServiceForType());
        }
        model.addAttribute("serviceForBrands", serviceForBrands);
        return "web/serviceForType/index";
    }

    private void addBlogroll(ModelMap model) {
        List<Blogroll> blogrolls = blogrollRepository.findAll();
        model.addAttribute("blogrolls", blogrolls);
    }

    /**
     * 服务的，进去看所有品牌
     * @param model
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/serviceList-{id}")
    public String serviceList(ModelMap model, @PathVariable Long id) throws Exception{

        model.addAttribute("serviceForTypeId", id);

        addBlogroll(model);

        setImg(model);

        ServiceForType serviceForType = serviceForTypeRepository.findOne(id);
        model.addAttribute("serviceForType", serviceForType);

        List<ServiceForType> serviceForTypes = serviceForTypeRepository.findByIsMenu(false);
        List<ServiceForType> serviceForTypesIsMenu = serviceForTypeRepository.findByIsMenu(true);
        model.addAttribute("serviceForTypes", serviceForTypes);
        model.addAttribute("serviceForTypesIsMenu", serviceForTypesIsMenu);

        List<ServiceForBrand> serviceForBrands = serviceForBrandRepository.findByTypeId(id);
        model.addAttribute("serviceForBrands", serviceForBrands);
        return "web/serviceForType/index";
    }

    /**
     * 菜单的，进去看信息列表
     * @param model
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/list-{id}")
    public String list(ModelMap model, @PathVariable Long id) throws Exception{
        model.addAttribute("serviceForTypeId", id);

        ServiceForType serviceForType = serviceForTypeRepository.findOne(id);
        model.addAttribute("serviceForType", serviceForType);

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

        Page<ServiceForTypeMessage> serviceForTypeMessagePage = serviceForTypeMessageService.index(0,10, id);

        model.addAttribute("serviceForTypeMessagePage", serviceForTypeMessagePage);
        model.addAttribute("totalPages", serviceForTypeMessagePage.getTotalPages() ==0 ? 1 : serviceForTypeMessagePage.getTotalPages());
        return "web/serviceForType/indexForMenu";
    }

    /**
     * 菜单的，进去看信息列表
     * @param model
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/list-{id}/page-{page}")
    public String listForPage(ModelMap model, @PathVariable Long id, @PathVariable Integer page) throws Exception{

        ServiceForType serviceForType = serviceForTypeRepository.findOne(id);
        model.addAttribute("serviceForType", serviceForType);
        addBlogroll(model);
        setImg(model);
        model.addAttribute("serviceForTypeId", id);
        List<ServiceForType> serviceForTypes = serviceForTypeRepository.findByIsMenu(false);
        List<ServiceForType> serviceForTypesIsMenu = serviceForTypeRepository.findByIsMenu(true);
        model.addAttribute("serviceForTypes", serviceForTypes);
        model.addAttribute("serviceForTypesIsMenu", serviceForTypesIsMenu);

        List<ServiceForBrand> serviceForBrands = new ArrayList<>();
        if (serviceForTypes.size() > 0) {
            serviceForBrands = serviceForBrandRepository.findByTypeId(serviceForTypes.get(0).getId());
        }
        model.addAttribute("serviceForBrands", serviceForBrands);

        Page<ServiceForTypeMessage> serviceForTypeMessagePage = serviceForTypeMessageService.index(page,10, id);
        model.addAttribute("serviceForTypeMessagePage",serviceForTypeMessagePage);
        model.addAttribute("totalPages", serviceForTypeMessagePage.getTotalPages() ==0 ? 1 : serviceForTypeMessagePage.getTotalPages());
        return "web/serviceForType/indexForMenu";
    }
}

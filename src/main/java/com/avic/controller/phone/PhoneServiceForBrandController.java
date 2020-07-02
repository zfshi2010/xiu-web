package com.avic.controller.phone;

import com.avic.controller.BaseController;
import com.avic.entity.Banner;
import com.avic.entity.ServiceForBrand;
import com.avic.entity.ServiceForBrandMessage;
import com.avic.entity.Produce;
import com.avic.repository.*;
import com.avic.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/phone/serviceForBrand")
public class PhoneServiceForBrandController extends BaseController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SysConfigRepository sysConfigRepository;

    @Autowired
    private ProductFieldRepository serviceForTypeRepository;

    @Autowired
    private ServiceForBrandRepository serviceForBrandRepository;

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private ServiceForBrandMessageRepository serviceForBrandMessageRepository;

    @Autowired
    private ProduceRepository serviceForBrandProjectRepository;

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


        List<Produce> serviceForBrandProjects = serviceForBrandProjectRepository.findAll();
        model.addAttribute("serviceForBrandProjects", serviceForBrandProjects);

        return "phone/serviceForBrand/index";
    }
}

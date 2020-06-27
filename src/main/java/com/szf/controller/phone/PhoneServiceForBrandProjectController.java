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
        model.addAttribute("serviceForBrandProject", serviceForBrandProjectVo);

        ServiceForBrand serviceForBrand = serviceForBrandRepository.findOne(serviceForBrandProjectVo.getServiceForBrandId() );
        model.addAttribute("serviceForBrand", serviceForBrand);

        List<ServiceForBrandProject> serviceForBrandProjects = serviceForBrandProjectRepository.findByServiceForBrandId(serviceForBrandProjectVo.getServiceForBrandId());
        model.addAttribute("serviceForBrandProjects", serviceForBrandProjects);
        return "phone/serviceForBrandProject/detail";
    }



}

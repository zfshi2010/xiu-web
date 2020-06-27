package com.szf.controller.phone;

import com.szf.controller.BaseController;
import com.szf.entity.Banner;
import com.szf.entity.ServiceForBrand;
import com.szf.entity.ServiceForBrandMessage;
import com.szf.entity.ServiceForType;
import com.szf.repository.*;
import com.szf.service.MessageService;
import com.szf.vo.ServiceForBrandMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/phone/serviceForBrandMessage")
public class PhoneServiceForBrandMessageController extends BaseController {

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
        model.addAttribute("serviceForBrandMessage", serviceForBrandMessageVo);

        ServiceForBrand serviceForBrand = serviceForBrandRepository.findOne(serviceForBrandMessageVo.getServiceForBrandId());
        model.addAttribute("serviceForBrand", serviceForBrand);

        List<ServiceForBrandMessage> serviceForBrandMessages = serviceForBrandMessageRepository.findByServiceForBrandId(serviceForBrandMessageVo.getServiceForBrandId());
        model.addAttribute("serviceForBrandMessages", serviceForBrandMessages);
        return "phone/serviceForBrandMessage/detail";
    }



}

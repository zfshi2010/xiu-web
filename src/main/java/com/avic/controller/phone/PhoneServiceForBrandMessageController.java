package com.avic.controller.phone;

import com.avic.controller.BaseController;
import com.avic.entity.ServiceForBrand;
import com.avic.entity.ServiceForBrandMessage;
import com.avic.repository.*;
import com.avic.service.MessageService;
import com.avic.vo.ServiceForBrandMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private ProductFieldRepository serviceForTypeRepository;

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

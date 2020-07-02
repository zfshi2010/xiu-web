package com.avic.controller.phone;

import com.avic.controller.BaseController;
import com.avic.entity.ProductField;
import com.avic.entity.ProductType;
import com.avic.entity.ServiceForBrand;
import com.avic.entity.Produce;
import com.avic.repository.*;
import com.avic.service.MessageService;
import com.avic.vo.ProduceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/phone/produce")
public class PhoneProduceController extends BaseController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ProduceRepository produceRepository;

    @Autowired
    private SysConfigRepository sysConfigRepository;

    @Autowired
    private ProductFieldRepository productFieldRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

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
        ProduceVo produceVo = produceRepository.getOne(id).toVo();
        model.addAttribute("produce", produceVo);

        ProductType productType = productTypeRepository.findOne(produceVo.getProductTypeId());
        model.addAttribute("productType", productType);

        ProductField productField = productFieldRepository.findOne(produceVo.getProductFieldId());
        model.addAttribute("productField", productField);
        return "phone/produce/detail";
    }



}

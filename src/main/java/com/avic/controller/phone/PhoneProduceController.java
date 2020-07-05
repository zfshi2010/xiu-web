package com.avic.controller.phone;

import com.avic.controller.BaseController;
import com.avic.entity.ContactWay;
import com.avic.entity.Produce;
import com.avic.entity.ProductField;
import com.avic.entity.ProductType;
import com.avic.repository.ContactWayRepository;
import com.avic.repository.ProduceRepository;
import com.avic.repository.ProductFieldRepository;
import com.avic.repository.ProductTypeRepository;
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
    private ProduceRepository produceRepository;

    @Autowired
    private ProductFieldRepository productFieldRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private ContactWayRepository contactWayRepository;

    private void setContactWay(ModelMap model) {
        List<ContactWay> contactWays = contactWayRepository.findAll();
        model.addAttribute("contactWays", contactWays);
    }


    @RequestMapping("/index-{productTypeId}")
    public String index(ModelMap model, @PathVariable Long productTypeId) throws Exception{
        ProductType productType = productTypeRepository.findOne(productTypeId);
        model.addAttribute("productType", productType);

        List<Produce> produces = produceRepository.findByProductTypeId(productTypeId);
        model.addAttribute("produces", produces);
        setContactWay(model);

        return "phone/produce/index";
    }

    /**
     * @param model
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/detail-{id}")
    public String detail(ModelMap model, @PathVariable Long id) throws Exception{
        ProduceVo produceVo = produceRepository.getOne(id).toVo();
        model.addAttribute("produce", produceVo);

        ProductType productType = productTypeRepository.findOne(produceVo.getProductTypeId());
        model.addAttribute("productType", productType);

        ProductField productField = productFieldRepository.findOne(produceVo.getProductFieldId());
        model.addAttribute("productField", productField);

        setContactWay(model);
        return "phone/produce/detail";
    }


    /**
     * @param model
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/pdf-{id}")
    public String pdf(ModelMap model, @PathVariable Long id) throws Exception{
        ProduceVo produceVo = produceRepository.getOne(id).toVo();
        model.addAttribute("produce", produceVo);

        ProductType productType = productTypeRepository.findOne(produceVo.getProductTypeId());
        model.addAttribute("productType", productType);

        setContactWay(model);

        return "phone/produce/pdf";
    }


}

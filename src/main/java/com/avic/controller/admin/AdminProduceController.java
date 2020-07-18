package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.Produce;
import com.avic.entity.ProductType;
import com.avic.repository.ProduceRepository;
import com.avic.repository.ProductBrandRepository;
import com.avic.repository.ProductFieldRepository;
import com.avic.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/produce")
public class AdminProduceController extends BaseController {

    @Autowired
    private ProductFieldRepository productFieldRepository;

    @Autowired
    private ProductBrandRepository productBrandRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private ProduceRepository produceRepository;

    @RequestMapping("/index")
    public String index(ModelMap model) throws Exception{
        model.addAttribute("productFields", productFieldRepository.findAll());
        return "admin/produce/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model, Long serviceForBrandId) throws Exception{
        model.addAttribute("productFields", productFieldRepository.findAll());
        return "admin/produce/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        Produce produce = produceRepository.findOne(id);
        model.addAttribute("produce", produce);

        model.addAttribute("productFields", productFieldRepository.findAll());
        model.addAttribute("productBrands", productBrandRepository.findByProductFieldId(produce.getProductFieldId()));
        model.addAttribute("productTypes", productTypeRepository.findByProductBrandId(produce.getProductBrandId()));
        return "admin/produce/update";
    }
}

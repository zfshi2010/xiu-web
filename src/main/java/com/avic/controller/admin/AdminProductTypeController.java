package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.ProductType;
import com.avic.repository.ProductTypeRepository;
import com.avic.repository.ProductFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/productType")
public class AdminProductTypeController extends BaseController {

    @Autowired
    private ProductFieldRepository productFieldRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @RequestMapping("/index")
    public String index(ModelMap model, Long productFieldId) throws Exception{
        model.addAttribute("productFieldId", productFieldId);
        model.addAttribute("productField", productFieldRepository.findOne(productFieldId));
        return "admin/productType/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model, Long productFieldId) throws Exception{
        model.addAttribute("productFieldId", productFieldId);
        model.addAttribute("productField", productFieldRepository.findOne(productFieldId));
        return "admin/productType/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        ProductType productType = productTypeRepository.findOne(id);
        model.addAttribute("productType", productType);

        model.addAttribute("productFieldId", productType.getProductFieldId());
        model.addAttribute("productField", productFieldRepository.findOne(productType.getProductFieldId()));
        return "admin/productType/update";
    }

}

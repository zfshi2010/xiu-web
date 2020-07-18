package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.ProductType;
import com.avic.repository.ProductBrandRepository;
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
    private ProductBrandRepository productBrandRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @RequestMapping("/index")
    public String index(ModelMap model, Long productBrandId) throws Exception{
        model.addAttribute("productBrandId", productBrandId);
        model.addAttribute("productBrand", productBrandRepository.findOne(productBrandId));
        return "admin/productType/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model, Long productBrandId) throws Exception{
        model.addAttribute("productBrandId", productBrandId);
        model.addAttribute("productBrand", productBrandRepository.findOne(productBrandId));
        return "admin/productType/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        ProductType productType = productTypeRepository.findOne(id);
        model.addAttribute("productType", productType);

        model.addAttribute("productBrandId", productType.getProductBrandId());
        model.addAttribute("productBrand", productBrandRepository.findOne(productType.getProductBrandId()));
        return "admin/productType/update";
    }

}

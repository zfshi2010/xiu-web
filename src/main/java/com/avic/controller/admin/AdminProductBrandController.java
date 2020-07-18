package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.ProductBrand;
import com.avic.entity.ProductType;
import com.avic.repository.ProductBrandRepository;
import com.avic.repository.ProductFieldRepository;
import com.avic.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/productBrand")
public class AdminProductBrandController extends BaseController {

    @Autowired
    private ProductFieldRepository productFieldRepository;

    @Autowired
    private ProductBrandRepository productBrandRepository;

    @RequestMapping("/index")
    public String index(ModelMap model, Long productFieldId) throws Exception{
        model.addAttribute("productFieldId", productFieldId);
        model.addAttribute("productField", productFieldRepository.findOne(productFieldId));
        return "admin/productBrand/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model, Long productFieldId) throws Exception{
        model.addAttribute("productFieldId", productFieldId);
        model.addAttribute("productField", productFieldRepository.findOne(productFieldId));
        return "admin/productBrand/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        ProductBrand productBrand = productBrandRepository.findOne(id);
        model.addAttribute("productBrand", productBrand);

        model.addAttribute("productFieldId", productBrand.getProductFieldId());
        model.addAttribute("productField", productFieldRepository.findOne(productBrand.getProductFieldId()));
        return "admin/productBrand/update";
    }

}

package com.avic.controller.phone;

import com.avic.controller.BaseController;
import com.avic.entity.ContactWay;
import com.avic.entity.ProductBrand;
import com.avic.entity.ProductField;
import com.avic.entity.ProductType;
import com.avic.repository.ContactWayRepository;
import com.avic.repository.ProductBrandRepository;
import com.avic.repository.ProductFieldRepository;
import com.avic.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/phone/productBrand")
public class PhoneProductBrandController extends BaseController {


    @Autowired
    private ProductFieldRepository productFieldRepository;

    @Autowired
    private ProductBrandRepository productBrandRepository;


    @Autowired
    private ContactWayRepository contactWayRepository;

    private void setContactWay(ModelMap model) {
        List<ContactWay> contactWays = contactWayRepository.findAll();
        model.addAttribute("contactWays", contactWays);
    }


    @RequestMapping("/index-{productFieldId}")
    public String index(ModelMap model, @PathVariable Long productFieldId) throws Exception{
        ProductField productField = productFieldRepository.findOne(productFieldId);
        model.addAttribute("productField", productField);

        List<ProductBrand> productBrands = productBrandRepository.findByProductFieldId(productFieldId);
        model.addAttribute("productBrands", productBrands);

        setContactWay(model);
        return "phone/productBrand/index";
    }
}

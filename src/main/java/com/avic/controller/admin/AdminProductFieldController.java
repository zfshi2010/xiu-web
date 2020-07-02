package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.ProductField;
import com.avic.repository.ProductFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/productField")
public class AdminProductFieldController extends BaseController {

    @Autowired
    private ProductFieldRepository productFieldRepository;

    @RequestMapping("/index")
    public String index() throws Exception{
        return "admin/productField/list";
    }

    @RequestMapping("/add")
    public String add() throws Exception{
        return "admin/productField/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        ProductField productField = productFieldRepository.findOne(id);
        model.addAttribute("productField", productField);
        return "admin/productField/update";
    }
}

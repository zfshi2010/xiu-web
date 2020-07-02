package com.avic.controller.phone;

import com.avic.controller.BaseController;
import com.avic.entity.ProductField;
import com.avic.entity.SysConfig;
import com.avic.repository.ProductFieldRepository;
import com.avic.repository.SysConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/phone/productField")
public class PhoneServiceForTypeController extends BaseController {

    @Autowired
    private ProductFieldRepository productFieldRepository;

    @Autowired
    private SysConfigRepository sysConfigRepository;


    /**
     * 产品领域列表（二级菜单）
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/index")
    public String index(ModelMap model) throws Exception{
        SysConfig firstPage = sysConfigRepository.findByType(SysConfig.FIRST_PAGE);
        model.addAttribute("firstPage", firstPage);
        List<ProductField> productFields = productFieldRepository.findAll();
        model.addAttribute("productFields", productFields);
        return "phone/productField/index";
    }

}

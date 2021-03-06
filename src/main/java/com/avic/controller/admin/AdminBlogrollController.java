package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.Blogroll;
import com.avic.repository.BlogrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/blogroll")
public class AdminBlogrollController extends BaseController {

    @Autowired
    private BlogrollRepository blogrollRepository;

    @RequestMapping("/index")
    public String index() throws Exception{
        return "admin/blogroll/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model) throws Exception{
        return "admin/blogroll/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        Blogroll blogroll = blogrollRepository.findOne(id);
        model.addAttribute("blogroll", blogroll);
        return "admin/blogroll/update";
    }

}

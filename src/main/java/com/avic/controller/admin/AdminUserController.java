package com.avic.controller.admin;

import com.avic.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController extends BaseController {

    @RequestMapping("/index")
    public String index() throws Exception{
        return "admin/user/list";
    }

    @RequestMapping("/add")
    public String add() throws Exception{
        return "admin/user/add";
    }
}

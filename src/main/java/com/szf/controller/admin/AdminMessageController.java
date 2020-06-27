package com.szf.controller.admin;

import com.szf.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/message")
public class AdminMessageController extends BaseController {

    @RequestMapping("/index")
    public String index() throws Exception{
        return "admin/message/list";
    }

    @RequestMapping("/add")
    public String add() throws Exception{
        return "admin/message/add";
    }
}

package com.szf.controller.web;

import com.szf.controller.BaseController;
import com.szf.repository.MessageRepository;
import com.szf.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class WebMessageController extends BaseController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/index")
    public String index(ModelMap model) throws Exception{
        model.addAttribute("messages", messageService.index(null,null));
        return "web/message/index";
    }

    @RequestMapping("/detail/{id}")
    public String detail(ModelMap model, @PathVariable Long id) throws Exception{
        model.addAttribute("message", messageService.detail(id));
        return "web/message/detail";
    }

    @RequestMapping("/index/page_{page}")
    public String index(ModelMap model, @PathVariable Integer page) throws Exception{
        model.addAttribute("messages", messageService.index(page,null));
        return "web/message/index";
    }
}

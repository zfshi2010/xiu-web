package com.szf.controller.admin;

import com.szf.controller.BaseController;
import com.szf.entity.Guestbook;
import com.szf.repository.GuestbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/guestbook")
public class AdminGuestbookController extends BaseController {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @RequestMapping("/index")
    public String index() throws Exception{
        return "admin/guestbook/list";
    }



    @RequestMapping("/detail")
    public String detail(ModelMap model, Long id) throws Exception{
        Guestbook guestbook = guestbookRepository.findOne(id);
        model.addAttribute("guestbook", guestbook.toVo());
        return "admin/guestbook/detail";
    }

}

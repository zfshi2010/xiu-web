package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.MeasurementTask;
import com.avic.entity.User;
import com.avic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController extends BaseController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/index")
    public String index() throws Exception{
        return "admin/user/list";
    }

    @RequestMapping("/add")
    public String add() throws Exception{
        return "admin/user/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model, Long id) throws Exception{
        User user = userRepository.findOne(id);
        model.addAttribute("user", user.toVo());
        return "admin/user/update";
    }
}

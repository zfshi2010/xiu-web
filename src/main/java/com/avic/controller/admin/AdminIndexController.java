package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.User;
import com.avic.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin")
public class AdminIndexController extends BaseController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/login")
    public String login() throws Exception{
        return "admin/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception{
        request.getSession().setAttribute("username", "");
        request.getSession().removeAttribute("username");
        return "admin/login";
    }


    @RequestMapping("/dWF2X2xvZ2lu")
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model, String username,
                        String password) throws Exception {
        if (StringUtils.isBlank(username)) {
            model.addAttribute("errorMessage", "用户名不能为空!");
            return "admin/login";
        }
        if (StringUtils.isBlank(password)) {
            model.addAttribute("errorMessage", "密码不能为空!");
            return "admin/login";
        }
        User user = userRepository.findByUsername(username);
        if (user == null) {
            model.addAttribute("errorMessage", "用户名不存在，请重试!");
            return "admin/login";
        } else {
            if (user.getPwd().equals(password)) {
                request.getSession().setAttribute("username", user.getUsername());
                request.getSession().setAttribute("roleType", user.getRoleType().toString());
                return "redirect:/admin/config/aboutUs";
            } else {
                model.addAttribute("errorMessage", "密码错误，请仔细核对！");
                return "admin/login";
            }

        }
    }
}

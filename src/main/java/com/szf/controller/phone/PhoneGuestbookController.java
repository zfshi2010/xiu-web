package com.szf.controller.phone;

import com.szf.controller.BaseController;
import com.szf.entity.*;
import com.szf.repository.*;
import com.szf.service.ServiceForTypeMessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/phone/guestbook")
public class PhoneGuestbookController extends BaseController {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Autowired
    private SysConfigRepository sysConfigRepository;

    @Autowired
    private ServiceForTypeRepository serviceForTypeRepository;

    @Autowired
    private ServiceForBrandRepository serviceForBrandRepository;

    @Autowired
    private ServiceForTypeMessageService serviceForTypeMessageService;

    @Autowired
    private BlogrollRepository blogrollRepository;

    @Autowired
    private BannerRepository bannerRepository;

    private String getFirstPage(ModelMap model) {
        SysConfig firstPage = sysConfigRepository.findByType(SysConfig.FIRST_PAGE);
        model.addAttribute("firstPage", firstPage);
        return "phone/guestbook/index";
    }

    @RequestMapping("/index")
    public String index(ModelMap model) throws Exception{
        SysConfig firstPage = sysConfigRepository.findByType(SysConfig.FIRST_PAGE);
        model.addAttribute("firstPage", firstPage);
        return "phone/guestbook/index";
    }

    @RequestMapping("/save")
    public String save(ModelMap model,Guestbook guestbook) throws Exception{
        if (StringUtils.isBlank(guestbook.getName())) {
            model.addAttribute("errorMessage", "您的姓名不能为空!");
            return getFirstPage(model);
        }
        if (StringUtils.isBlank(guestbook.getPhone())) {
            model.addAttribute("errorMessage", "您的电话不能为空!");
            return getFirstPage(model);
        }
        if (guestbook.getName().length() > 10) {
            model.addAttribute("errorMessage", "您的姓名过长!");
            return getFirstPage(model);
        }
        if (guestbook.getPhone().length() > 15) {
            model.addAttribute("errorMessage", "您的电话过长!");
            return getFirstPage(model);
        }

        if (StringUtils.isNotBlank(guestbook.getAddress()) && guestbook.getAddress().length() > 100) {
            model.addAttribute("errorMessage", "地址过长!");
            return getFirstPage(model);
        }
        if (StringUtils.isNotBlank(guestbook.getContent()) && guestbook.getContent().length() > 200) {
            model.addAttribute("errorMessage", "留言过长!");
            return getFirstPage(model);
        }

        SysConfig firstPage = sysConfigRepository.findByType(SysConfig.FIRST_PAGE);
        model.addAttribute("firstPage", firstPage);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        guestbook.setCreateTime(nowText);
        guestbookRepository.save(guestbook);
        return "phone/guestbook/success";
    }

}

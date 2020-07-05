package com.avic.controller.phone;

import com.avic.controller.BaseController;
import com.avic.entity.*;
import com.avic.repository.*;
import com.avic.service.ProductTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/phone/guestbook")
public class PhoneGuestbookController extends BaseController {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Autowired
    private SysConfigRepository sysConfigRepository;

    @Autowired
    private ProductTypeService serviceForTypeMessageService;

    @Autowired
    private MeasurementTaskRepository measurementTaskRepository;

    @Autowired
    private BlogrollRepository blogrollRepository;

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private ContactWayRepository contactWayRepository;

    private void setContactWay(ModelMap model) {
        List<ContactWay> contactWays = contactWayRepository.findAll();
        model.addAttribute("contactWays", contactWays);
    }

    private String getFirstPage(ModelMap model) {
        SysConfig firstPage = sysConfigRepository.findByType(SysConfig.FIRST_PAGE);
        model.addAttribute("firstPage", firstPage);
        return "phone/guestbook/index";
    }

    @RequestMapping("/index")
    public String index(ModelMap model) throws Exception{
        SysConfig firstPage = sysConfigRepository.findByType(SysConfig.FIRST_PAGE);
        model.addAttribute("firstPage", firstPage);
        setContactWay(model);

        List<MeasurementTask> measurementTasks = measurementTaskRepository.findAll();
        model.addAttribute("measurementTasks", measurementTasks);
        return "phone/guestbook/index";
    }

    @RequestMapping("/save")
    public String save(ModelMap model,Guestbook guestbook) throws Exception{

        if (guestbook.getMeasurementTaskId() == null || guestbook.getMeasurementTaskId() == 0L) {
            model.addAttribute("errorMessage", "请选择测量任务!");
            return getFirstPage(model);
        }
        if (guestbook.getTextureId() == null || guestbook.getTextureId() == 0L) {
            model.addAttribute("errorMessage", "请选择材质!");
            return getFirstPage(model);
        }
        if (StringUtils.isBlank(guestbook.getName())) {
            model.addAttribute("errorMessage", "姓名不能为空!");
            return getFirstPage(model);
        }
        if (StringUtils.isBlank(guestbook.getPhone())) {
            model.addAttribute("errorMessage", "联系电话不能为空!");
            return getFirstPage(model);
        }
        if (guestbook.getName().length() > 10) {
            model.addAttribute("errorMessage", "姓名过长!");
            return getFirstPage(model);
        }
        if (guestbook.getPhone().length() > 15) {
            model.addAttribute("errorMessage", "联系电话过长!");
            return getFirstPage(model);
        }

        if (StringUtils.isNotBlank(guestbook.getContent()) && guestbook.getContent().length() > 500) {
            model.addAttribute("errorMessage", "内容过长!");
            return getFirstPage(model);
        }

        SysConfig firstPage = sysConfigRepository.findByType(SysConfig.FIRST_PAGE);
        model.addAttribute("firstPage", firstPage);

        setContactWay(model);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        guestbook.setCreateTime(nowText);
        guestbookRepository.save(guestbook);
        return "phone/guestbook/success";
    }

}

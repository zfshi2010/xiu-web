package com.szf.controller.admin;

import com.szf.controller.BaseController;
import com.szf.entity.Banner;
import com.szf.repository.BannerRepository;
import com.szf.repository.BlogrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/banner")
public class AdminBannerController extends BaseController {

    @Autowired
    private BannerRepository bannerRepository;

    @RequestMapping("/index")
    public String index() throws Exception{
        return "admin/banner/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model) throws Exception{
        return "admin/banner/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap model,Long id) throws Exception{
        Banner banner = bannerRepository.findOne(id);
        model.addAttribute("banner",banner);
        return "admin/banner/update";
    }

    @RequestMapping("/index_for_brand")
    public String indexForBrand(ModelMap model,Long serviceForBrandId) throws Exception{
        model.addAttribute("serviceForBrandId",serviceForBrandId);
        return "admin/banner_for_brand/list";
    }

    @RequestMapping("/add_for_brand")
    public String addForBrand(ModelMap model,Long serviceForBrandId) throws Exception{
        model.addAttribute("serviceForBrandId",serviceForBrandId);
        return "admin/banner_for_brand/add";
    }

    @RequestMapping("/update_for_brand")
    public String updateForBrand(ModelMap model,Long id) throws Exception{
        Banner banner = bannerRepository.findOne(id);
        model.addAttribute("banner",banner);
        model.addAttribute("serviceForBrandId",banner.getServiceForBrandId());
        return "admin/banner_for_brand/update";
    }
}

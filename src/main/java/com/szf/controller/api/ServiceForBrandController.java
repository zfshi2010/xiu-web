package com.szf.controller.api;

import com.szf.common.JsonResult;
import com.szf.entity.ServiceForBrand;
import com.szf.entity.ServiceForType;
import com.szf.repository.ServiceForBrandRepository;
import com.szf.repository.ServiceForTypeRepository;
import com.szf.util.UserLoginToken;
import com.szf.vo.ServiceForBrandVo;
import com.szf.vo.ServiceForTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/serviceForBrand")
@Service
public class ServiceForBrandController {

    @Autowired
    ServiceForBrandRepository serviceForBrandRepository;

    @GetMapping("/detail")
    @UserLoginToken
    @Transactional(readOnly = true)
    public JsonResult detail(Long id) {
        ServiceForBrandVo serviceForBrandVo = this.serviceForBrandRepository.getOne(id).toVo();
        return JsonResult.getSuccessResult(serviceForBrandVo);
    }

    @PostMapping("/save")
    @Transactional
    public JsonResult save(ServiceForBrand serviceForBrand) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        serviceForBrand.setCreateTime(nowText);
        this.serviceForBrandRepository.save(serviceForBrand);
        return JsonResult.getSuccessResult();
    }

    @PostMapping("/update")
    @Transactional
    public JsonResult update(ServiceForBrand serviceForBrand) {
        this.serviceForBrandRepository.save(serviceForBrand);
        return JsonResult.getSuccessResult();
    }

    @DeleteMapping("/delete")
    @Transactional
    public JsonResult delete(Long id) {
        this.serviceForBrandRepository.delete(id);
        return JsonResult.getSuccessResult();
    }

    @GetMapping("/index")
    @UserLoginToken
    public JsonResult index(Integer page, Integer size) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<ServiceForBrand> serviceForBrands = serviceForBrandRepository.findAll(pageRequest);
        return JsonResult.getSuccessResult(serviceForBrands);
    }

}

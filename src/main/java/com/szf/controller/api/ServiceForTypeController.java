package com.szf.controller.api;

import com.szf.common.JsonResult;
import com.szf.entity.Message;
import com.szf.entity.ServiceForBrand;
import com.szf.entity.ServiceForType;
import com.szf.repository.MessageRepository;
import com.szf.repository.ServiceForTypeRepository;
import com.szf.util.UserLoginToken;
import com.szf.vo.MessageVo;
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
@RequestMapping("/api/serviceForType")
@Service
public class ServiceForTypeController {

    @Autowired
    ServiceForTypeRepository serviceForTypeRepository;

    @GetMapping("/detail")
    @UserLoginToken
    @Transactional(readOnly = true)
    public JsonResult detail(Long id) {
        ServiceForTypeVo serviceForTypeVo = this.serviceForTypeRepository.getOne(id).toVo();
        return JsonResult.getSuccessResult(serviceForTypeVo);
    }

    @PostMapping("/save")
    @UserLoginToken
    @Transactional
    public JsonResult save(ServiceForType serviceForType) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        serviceForType.setCreateTime(nowText);
        this.serviceForTypeRepository.save(serviceForType);
        return JsonResult.getSuccessResult();
    }

    @PostMapping("/update")
    @Transactional
    public JsonResult update(ServiceForType serviceForType) {
        this.serviceForTypeRepository.save(serviceForType);
        return JsonResult.getSuccessResult();
    }

    @DeleteMapping("/delete")
    @Transactional
    public JsonResult delete(Long id) {
        this.serviceForTypeRepository.delete(id);
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
        Page<ServiceForType> serviceForTypes = serviceForTypeRepository.findAll(pageRequest);
        return JsonResult.getSuccessResult(serviceForTypes);
    }

}

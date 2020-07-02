package com.avic.controller.api;

import com.avic.common.JsonResult;
import com.avic.entity.SysConfig;
import com.avic.repository.SysConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/config")
@Service
public class ConfigController {

    @Autowired
    SysConfigRepository sysConfigRepository;


    @PostMapping("/save")
    @Transactional
    public JsonResult save(SysConfig sysConfig) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        if (sysConfig.getId() == null || sysConfig.getId() == 0) {
            sysConfig.setCreateTime(nowText);
        }
        this.sysConfigRepository.save(sysConfig);
        return JsonResult.getSuccessResult();
    }

}

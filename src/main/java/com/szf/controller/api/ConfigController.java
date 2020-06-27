package com.szf.controller.api;

import com.szf.common.JsonResult;
import com.szf.entity.Message;
import com.szf.entity.SysConfig;
import com.szf.repository.MessageRepository;
import com.szf.repository.SysConfigRepository;
import com.szf.util.UserLoginToken;
import com.szf.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
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

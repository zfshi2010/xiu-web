package com.avic.controller.api;

import com.avic.common.JsonResult;
import com.avic.entity.ContactWay;
import com.avic.entity.Parameter;
import com.avic.repository.ContactWayRepository;
import com.avic.repository.ParameterRepository;
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
@RequestMapping("/api/parameter")
@Service
public class ParameterController {

    @Autowired
    ParameterRepository parameterRepository;

    @PostMapping("/save")
    @Transactional
    public JsonResult save(Parameter parameter) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        parameter.setCreateTime(nowText);
        this.parameterRepository.save(parameter);
        return JsonResult.getSuccessResult();
    }

    @PostMapping("/update")
    @Transactional
    public JsonResult update(Parameter parameter) {
        this.parameterRepository.save(parameter);
        return JsonResult.getSuccessResult();
    }


    @DeleteMapping("/delete")
    @Transactional
    public JsonResult delete(Long id) {
        this.parameterRepository.delete(id);
        return JsonResult.getSuccessResult();
    }

    @GetMapping("/index")
    public JsonResult index(Integer page, Integer size) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<Parameter> parameters = parameterRepository.findAll(pageRequest);
        return JsonResult.getSuccessResult(parameters);
    }

}

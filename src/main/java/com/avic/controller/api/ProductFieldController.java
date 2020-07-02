package com.avic.controller.api;

import com.avic.common.JsonResult;
import com.avic.entity.ProductField;
import com.avic.repository.ProductFieldRepository;
import com.avic.util.UserLoginToken;
import com.avic.vo.ProductFieldVo;
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
@RequestMapping("/api/productField")
@Service
public class ProductFieldController {

    @Autowired
    ProductFieldRepository productFieldRepository;

    @GetMapping("/detail")
    @UserLoginToken
    @Transactional(readOnly = true)
    public JsonResult detail(Long id) {
        ProductFieldVo serviceForTypeVo = this.productFieldRepository.getOne(id).toVo();
        return JsonResult.getSuccessResult(serviceForTypeVo);
    }

    @PostMapping("/save")
    @UserLoginToken
    @Transactional
    public JsonResult save(ProductField productField) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        productField.setCreateTime(nowText);
        this.productFieldRepository.save(productField);
        return JsonResult.getSuccessResult();
    }

    @PostMapping("/update")
    @Transactional
    public JsonResult update(ProductField productField) {
        this.productFieldRepository.save(productField);
        return JsonResult.getSuccessResult();
    }

    @DeleteMapping("/delete")
    @Transactional
    public JsonResult delete(Long id) {
        this.productFieldRepository.delete(id);
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
        Page<ProductField> productFields = productFieldRepository.findAll(pageRequest);
        return JsonResult.getSuccessResult(productFields);
    }

}

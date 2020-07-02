package com.avic.service;

import com.avic.entity.ProductField;
import com.avic.repository.ProductFieldRepository;
import com.avic.vo.ProductFieldVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductFieldService {

    @Autowired
    private ProductFieldRepository serviceForTypeRepository;

    @Transactional(readOnly = true)
    public Page<ProductField> index(Integer page, Integer size) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<ProductField> serviceForTypes = serviceForTypeRepository.findAll(pageRequest);
        return serviceForTypes;
    }

    @Transactional(readOnly = true)
    public ProductFieldVo detail(Long id) {
        return serviceForTypeRepository.getOne(id).toVo();
    }

}

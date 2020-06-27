package com.szf.service;

import com.szf.entity.Message;
import com.szf.entity.ServiceForBrand;
import com.szf.repository.MessageRepository;
import com.szf.repository.ServiceForBrandRepository;
import com.szf.vo.MessageVo;
import com.szf.vo.ServiceForBrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceForBrandService {

    @Autowired
    private ServiceForBrandRepository serviceForBrandRepository;

    @Transactional(readOnly = true)
    public Page<ServiceForBrand> index(Integer page, Integer size) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<ServiceForBrand> serviceForBrands = serviceForBrandRepository.findAll(pageRequest);
        return serviceForBrands;
    }

    @Transactional(readOnly = true)
    public ServiceForBrandVo detail(Long id) {
        return serviceForBrandRepository.getOne(id).toVo();
    }

}

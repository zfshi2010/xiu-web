package com.szf.service;

import com.szf.entity.Message;
import com.szf.entity.ServiceForType;
import com.szf.repository.MessageRepository;
import com.szf.repository.ServiceForTypeRepository;
import com.szf.vo.MessageVo;
import com.szf.vo.ServiceForTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceForTypeService {

    @Autowired
    private ServiceForTypeRepository serviceForTypeRepository;

    @Transactional(readOnly = true)
    public Page<ServiceForType> index(Integer page, Integer size) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<ServiceForType> serviceForTypes = serviceForTypeRepository.findAll(pageRequest);
        return serviceForTypes;
    }

    @Transactional(readOnly = true)
    public ServiceForTypeVo detail(Long id) {
        return serviceForTypeRepository.getOne(id).toVo();
    }

}

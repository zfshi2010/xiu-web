package com.szf.service;

import com.szf.common.JsonResult;
import com.szf.entity.ServiceForTypeMessage;
import com.szf.repository.ServiceForTypeMessageRepository;
import com.szf.util.UserLoginToken;
import com.szf.vo.ServiceForTypeMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

@Service
public class ServiceForTypeMessageService {

    @Autowired
    private ServiceForTypeMessageRepository serviceForTypeMessageRepository;

    @Transactional(readOnly = true)
    public Page<ServiceForTypeMessage> index(Integer page, Integer size) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<ServiceForTypeMessage> serviceForTypeMessages = serviceForTypeMessageRepository.findAll(pageRequest);
        return serviceForTypeMessages;
    }

    @Transactional(readOnly = true)
    public ServiceForTypeMessageVo detail(Long id) {
        return serviceForTypeMessageRepository.getOne(id).toVo();
    }


    @GetMapping("/index")
    public Page<ServiceForTypeMessage> index(Integer page, Integer size, Long serviceForTypeId) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        Specification<ServiceForTypeMessage> specification = getMenuSpecification(serviceForTypeId);

        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<ServiceForTypeMessage> serviceForTypeMessages = serviceForTypeMessageRepository.findAll(specification,pageRequest);
        return serviceForTypeMessages;
    }

    /**
     * 带条件的分页查询
     * @return Specification
     */
    private Specification<ServiceForTypeMessage> getMenuSpecification(Long serviceForTypeId) {
        return (Specification<ServiceForTypeMessage>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (serviceForTypeId != null && serviceForTypeId != 0L) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("serviceForTypeId"), serviceForTypeId));
            }
            return predicate;
        };
    }



}

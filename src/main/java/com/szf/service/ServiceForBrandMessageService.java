package com.szf.service;

import com.szf.entity.ServiceForBrandMessage;
import com.szf.repository.ServiceForBrandMessageRepository;
import com.szf.vo.ServiceForBrandMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.criteria.Predicate;

@Service
public class ServiceForBrandMessageService {

    @Autowired
    private ServiceForBrandMessageRepository serviceForBrandMessageRepository;

    @Transactional(readOnly = true)
    public Page<ServiceForBrandMessage> index(Integer page, Integer size) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<ServiceForBrandMessage> serviceForBrandMessages = serviceForBrandMessageRepository.findAll(pageRequest);
        return serviceForBrandMessages;
    }

    @Transactional(readOnly = true)
    public ServiceForBrandMessageVo detail(Long id) {
        return serviceForBrandMessageRepository.getOne(id).toVo();
    }


    @GetMapping("/index")
    public Page<ServiceForBrandMessage> index(Integer page, Integer size, Long serviceForBrandId) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        Specification<ServiceForBrandMessage> specification = getMenuSpecification(serviceForBrandId);

        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<ServiceForBrandMessage> serviceForBrandMessages = serviceForBrandMessageRepository.findAll(specification,pageRequest);
        return serviceForBrandMessages;
    }

    /**
     * 带条件的分页查询
     * @return Specification
     */
    private Specification<ServiceForBrandMessage> getMenuSpecification(Long serviceForBrandId) {
        return (Specification<ServiceForBrandMessage>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (serviceForBrandId != null && serviceForBrandId != 0L) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("serviceForBrandId"), serviceForBrandId));
            }
            return predicate;
        };
    }



}

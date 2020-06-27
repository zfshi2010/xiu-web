package com.szf.service;

import com.szf.entity.ServiceForBrandProject;
import com.szf.repository.ServiceForBrandProjectRepository;
import com.szf.vo.ServiceForBrandProjectVo;
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
public class ServiceForBrandProjectService {

    @Autowired
    private ServiceForBrandProjectRepository serviceForBrandProjectRepository;

    @Transactional(readOnly = true)
    public Page<ServiceForBrandProject> index(Integer page, Integer size) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<ServiceForBrandProject> serviceForBrandProjects = serviceForBrandProjectRepository.findAll(pageRequest);
        return serviceForBrandProjects;
    }

    @Transactional(readOnly = true)
    public ServiceForBrandProjectVo detail(Long id) {
        return serviceForBrandProjectRepository.getOne(id).toVo();
    }


    @GetMapping("/index")
    public Page<ServiceForBrandProject> index(Integer page, Integer size, Long serviceForBrandId) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        Specification<ServiceForBrandProject> specification = getMenuSpecification(serviceForBrandId);

        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<ServiceForBrandProject> serviceForBrandProjects = serviceForBrandProjectRepository.findAll(specification,pageRequest);
        return serviceForBrandProjects;
    }

    /**
     * 带条件的分页查询
     * @return Specification
     */
    private Specification<ServiceForBrandProject> getMenuSpecification(Long serviceForBrandId) {
        return (Specification<ServiceForBrandProject>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (serviceForBrandId != null && serviceForBrandId != 0L) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("serviceForBrandId"), serviceForBrandId));
            }
            return predicate;
        };
    }



}

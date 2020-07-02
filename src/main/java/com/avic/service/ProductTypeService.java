package com.avic.service;

import com.avic.entity.ProductType;
import com.avic.repository.ProductTypeRepository;
import com.avic.vo.ProductTypeVo;
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
public class ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Transactional(readOnly = true)
    public Page<ProductType> index(Integer page, Integer size) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<ProductType> serviceForTypeMessages = productTypeRepository.findAll(pageRequest);
        return serviceForTypeMessages;
    }

    @Transactional(readOnly = true)
    public ProductTypeVo detail(Long id) {
        return productTypeRepository.getOne(id).toVo();
    }


    @GetMapping("/index")
    public Page<ProductType> index(Integer page, Integer size, Long serviceForTypeId) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        Specification<ProductType> specification = getMenuSpecification(serviceForTypeId);

        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<ProductType> serviceForTypeMessages = productTypeRepository.findAll(specification,pageRequest);
        return serviceForTypeMessages;
    }

    /**
     * 带条件的分页查询
     * @return Specification
     */
    private Specification<ProductType> getMenuSpecification(Long serviceForTypeId) {
        return (Specification<ProductType>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (serviceForTypeId != null && serviceForTypeId != 0L) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("serviceForTypeId"), serviceForTypeId));
            }
            return predicate;
        };
    }



}

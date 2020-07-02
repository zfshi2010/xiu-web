package com.avic.service;

import com.avic.entity.Produce;
import com.avic.repository.ProduceRepository;
import com.avic.vo.ProduceVo;
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
public class ProduceService {

    @Autowired
    private ProduceRepository produceRepository;

    @Transactional(readOnly = true)
    public Page<Produce> index(Integer page, Integer size) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<Produce> produces = produceRepository.findAll(pageRequest);
        return produces;
    }

    @Transactional(readOnly = true)
    public ProduceVo detail(Long id) {
        return produceRepository.getOne(id).toVo();
    }


    @GetMapping("/index")
    public Page<Produce> index(Integer page, Integer size,Long productFieldId, Long productTypeId) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        Specification<Produce> specification = getMenuSpecification(productFieldId, productTypeId);

        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<Produce> serviceForBrandProjects = produceRepository.findAll(specification,pageRequest);
        return serviceForBrandProjects;
    }

    /**
     * 带条件的分页查询
     * @return Specification
     */
    private Specification<Produce> getMenuSpecification(Long productFieldId,Long productTypeId) {
        return (Specification<Produce>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (productTypeId != null && productTypeId != 0L) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("productTypeId"), productTypeId));
            }
            if (productFieldId != null && productFieldId != 0L) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("productFieldId"), productFieldId));
            }
            return predicate;
        };
    }



}

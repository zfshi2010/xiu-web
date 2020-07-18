package com.avic.controller.api;

import com.avic.common.JsonResult;
import com.avic.entity.ProductType;
import com.avic.repository.ProductTypeRepository;
import com.avic.util.UserLoginToken;
import com.avic.vo.ProductTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/productType")
@Service
public class ProductTypeController {

    @Autowired
    ProductTypeRepository productTypeRepository;

    @GetMapping("/detail")
    @UserLoginToken
    @Transactional(readOnly = true)
    public JsonResult detail(Long id) {
        ProductTypeVo productTypeVo = this.productTypeRepository.getOne(id).toVo();
        return JsonResult.getSuccessResult(productTypeVo);
    }

    @PostMapping("/save")
    @UserLoginToken
    @Transactional
    public JsonResult save(ProductType productType) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        productType.setCreateTime(nowText);
        this.productTypeRepository.save(productType);
        return JsonResult.getSuccessResult();
    }

    @PostMapping("/update")
    @Transactional
    public JsonResult update(ProductType productType) {
        this.productTypeRepository.save(productType);
        return JsonResult.getSuccessResult();
    }

    @DeleteMapping("/delete")
    @Transactional
    public JsonResult delete(Long id) {
        this.productTypeRepository.delete(id);
        return JsonResult.getSuccessResult();
    }

    @GetMapping("/listByProductBrandId")
    @UserLoginToken
    public JsonResult listByProductFieldId(Long productBrandId) {
        List<ProductType> productTypes = productTypeRepository.findByProductBrandId(productBrandId);
        return JsonResult.getSuccessResult(productTypes);
    }

    @GetMapping("/index")
    @UserLoginToken
    public JsonResult index(Integer page, Integer size, Long productBrandId) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        Specification<ProductType> specification = getSpecification(productBrandId);

        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");

        Page<ProductType> productTypes = productTypeRepository.findAll(specification,pageRequest);
        return JsonResult.getSuccessResult(productTypes);
    }

    /**
     * 带条件的分页查询
     * @return Specification
     */
    private Specification<ProductType> getSpecification(Long productBrandId) {
        return (Specification<ProductType>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (productBrandId != null && productBrandId != 0L) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("productBrandId"), productBrandId));
            }
            return predicate;
        };
    }

}

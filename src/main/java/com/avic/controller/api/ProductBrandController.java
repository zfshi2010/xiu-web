package com.avic.controller.api;

import com.avic.common.JsonResult;
import com.avic.entity.ProductBrand;
import com.avic.entity.ProductType;
import com.avic.repository.ProductBrandRepository;
import com.avic.repository.ProductTypeRepository;
import com.avic.util.UserLoginToken;
import com.avic.vo.ProductBrandVo;
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
@RequestMapping("/api/productBrand")
@Service
public class ProductBrandController {

    @Autowired
    ProductBrandRepository productBrandRepository;

    @GetMapping("/detail")
    @UserLoginToken
    @Transactional(readOnly = true)
    public JsonResult detail(Long id) {
        ProductBrandVo productTypeVo = this.productBrandRepository.getOne(id).toVo();
        return JsonResult.getSuccessResult(productTypeVo);
    }

    @PostMapping("/save")
    @UserLoginToken
    @Transactional
    public JsonResult save(ProductBrand productBrand) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        productBrand.setCreateTime(nowText);
        this.productBrandRepository.save(productBrand);
        return JsonResult.getSuccessResult();
    }

    @PostMapping("/update")
    @Transactional
    public JsonResult update(ProductBrand productBrand) {
        this.productBrandRepository.save(productBrand);
        return JsonResult.getSuccessResult();
    }

    @DeleteMapping("/delete")
    @Transactional
    public JsonResult delete(Long id) {
        this.productBrandRepository.delete(id);
        return JsonResult.getSuccessResult();
    }

    @GetMapping("/listByProductFieldId")
    @UserLoginToken
    public JsonResult listByProductFieldId(Long productFieldId) {
        List<ProductBrand> productBrands = productBrandRepository.findByProductFieldId(productFieldId);
        return JsonResult.getSuccessResult(productBrands);
    }

    @GetMapping("/index")
    @UserLoginToken
    public JsonResult index(Integer page, Integer size, Long productFieldId) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        Specification<ProductBrand> specification = getSpecification(productFieldId);

        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");

        Page<ProductType> productTypes = productBrandRepository.findAll(specification,pageRequest);
        return JsonResult.getSuccessResult(productTypes);
    }

    /**
     * 带条件的分页查询
     * @return Specification
     */
    private Specification<ProductBrand> getSpecification(Long productFieldId) {
        return (Specification<ProductBrand>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (productFieldId != null && productFieldId != 0L) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("productFieldId"), productFieldId));
            }
            return predicate;
        };
    }

}

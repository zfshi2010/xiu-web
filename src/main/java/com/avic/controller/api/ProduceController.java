package com.avic.controller.api;

import com.avic.common.JsonResult;
import com.avic.entity.Produce;
import com.avic.repository.ProduceRepository;
import com.avic.util.UserLoginToken;
import com.avic.vo.ProduceVo;
import org.apache.commons.lang3.StringUtils;
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

@RestController
@RequestMapping("/api/produce")
@Service
public class ProduceController {

    @Autowired
    ProduceRepository produceRepository;

    @GetMapping("/detail")
    @UserLoginToken
    @Transactional(readOnly = true)
    public JsonResult detail(Long id) {
        ProduceVo produceVo = this.produceRepository.getOne(id).toVo();
        return JsonResult.getSuccessResult(produceVo);
    }

    @PostMapping("/save")
    @UserLoginToken
    @Transactional
    public JsonResult save(Produce produce) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        produce.setCreateTime(nowText);
        this.produceRepository.save(produce);
        return JsonResult.getSuccessResult();
    }

    @PostMapping("/update")
    @Transactional
    public JsonResult update(Produce produce) {
        this.produceRepository.save(produce);
        return JsonResult.getSuccessResult();
    }

    @DeleteMapping("/delete")
    @Transactional
    public JsonResult delete(Long id) {
        this.produceRepository.delete(id);
        return JsonResult.getSuccessResult();
    }

    @GetMapping("/index")
    @UserLoginToken
    public JsonResult index(Integer page, Integer size, String name, Long productFieldId, Long productTypeId) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        Specification<Produce> specification = getSpecification(name,productFieldId,productTypeId);

        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");

        Page<Produce> produces = produceRepository.findAll(specification,pageRequest);
        return JsonResult.getSuccessResult(produces);
    }

    /**
     * 带条件的分页查询
     * @return Specification
     */
    private Specification<Produce> getSpecification(String name, Long productFieldId, Long productTypeId) {
        return (Specification<Produce>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (StringUtils.isNotBlank(name)) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("name"), name));
            }
            if (productFieldId != null && productFieldId != 0L) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("productFieldId"), productFieldId));
            }
            if (productTypeId != null && productTypeId != 0L) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("productTypeId"), productTypeId));
            }
            return predicate;
        };
    }

}

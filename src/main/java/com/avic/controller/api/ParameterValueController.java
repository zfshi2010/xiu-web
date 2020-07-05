package com.avic.controller.api;

import com.avic.common.JsonResult;
import com.avic.entity.ParameterValue;
import com.avic.repository.ParameterValueRepository;
import com.avic.util.UserLoginToken;
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
@RequestMapping("/api/parameterValue")
@Service
public class ParameterValueController {

    @Autowired
    ParameterValueRepository parameterValueRepository;

    @PostMapping("/save")
    @Transactional
    public JsonResult save(ParameterValue parameterValue) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        parameterValue.setCreateTime(nowText);
        this.parameterValueRepository.save(parameterValue);
        return JsonResult.getSuccessResult();
    }

    @PostMapping("/update")
    @Transactional
    public JsonResult update(ParameterValue parameterValue) {
        this.parameterValueRepository.save(parameterValue);
        return JsonResult.getSuccessResult();
    }


    @DeleteMapping("/delete")
    @Transactional
    public JsonResult delete(Long id) {
        this.parameterValueRepository.delete(id);
        return JsonResult.getSuccessResult();
    }

    @GetMapping("/listByParameterId")
    @UserLoginToken
    public JsonResult listByProductFieldId(Long parameterId) {
        List<ParameterValue> parameterValues = parameterValueRepository.findByParameterId(parameterId);
        return JsonResult.getSuccessResult(parameterValues);
    }

    @GetMapping("/index")
    @UserLoginToken
    public JsonResult index(Integer page, Integer size, Long parameterId) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        Specification<ParameterValue> specification = getSpecification(parameterId);

        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");

        Page<ParameterValue> parameterValues = parameterValueRepository.findAll(specification, pageRequest);
        return JsonResult.getSuccessResult(parameterValues);
    }

    /**
     * 带条件的分页查询
     * @return Specification
     */
    private Specification<ParameterValue> getSpecification(Long parameterId) {
        return (Specification<ParameterValue>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (parameterId != null && parameterId != 0L) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("parameterId"), parameterId));
            }
            return predicate;
        };
    }

}

package com.avic.controller.api;

import com.avic.common.JsonResult;
import com.avic.entity.MeasurementTaskType;
import com.avic.entity.Texture;
import com.avic.repository.MeasurementTaskTypeRepository;
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
@RequestMapping("/api/measurementTaskType")
@Service
public class MeasurementTaskTypeController {

    @Autowired
    MeasurementTaskTypeRepository measurementTaskTypeRepository;

    @PostMapping("/save")
    @Transactional
    public JsonResult save(MeasurementTaskType measurementTaskType) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        measurementTaskType.setCreateTime(nowText);
        this.measurementTaskTypeRepository.save(measurementTaskType);
        return JsonResult.getSuccessResult();
    }

    @PostMapping("/update")
    @Transactional
    public JsonResult update(MeasurementTaskType measurementTaskType) {
        this.measurementTaskTypeRepository.save(measurementTaskType);
        return JsonResult.getSuccessResult();
    }


    @DeleteMapping("/delete")
    @Transactional
    public JsonResult delete(Long id) {
        this.measurementTaskTypeRepository.delete(id);
        return JsonResult.getSuccessResult();
    }

    @GetMapping("/listByMeasurementTaskId")
    @UserLoginToken
    public JsonResult listByMeasurementTaskId(Long measurementTaskId) {
        List<MeasurementTaskType> measurementTaskTypes = measurementTaskTypeRepository.findByMeasurementTaskId(measurementTaskId);
        return JsonResult.getSuccessResult(measurementTaskTypes);
    }

    @GetMapping("/index")
    @UserLoginToken
    public JsonResult index(Integer page, Integer size, Long measurementTaskId) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        Specification<MeasurementTaskType> specification = getSpecification(measurementTaskId);

        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");

        Page<MeasurementTaskType> parameterValues = measurementTaskTypeRepository.findAll(specification, pageRequest);
        return JsonResult.getSuccessResult(parameterValues);
    }

    /**
     * 带条件的分页查询
     * @return Specification
     */
    private Specification<MeasurementTaskType> getSpecification(Long measurementTaskId) {
        return (Specification<MeasurementTaskType>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (measurementTaskId != null && measurementTaskId != 0L) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("measurementTaskId"), measurementTaskId));
            }
            return predicate;
        };
    }

}

package com.avic.controller.api;

import com.avic.common.JsonResult;
import com.avic.entity.MeasurementTaskType;
import com.avic.entity.MeasurementTaskTypeParameter;
import com.avic.entity.Parameter;
import com.avic.repository.MeasurementTaskTypeParameterRepository;
import com.avic.repository.MeasurementTaskTypeRepository;
import com.avic.repository.ParameterRepository;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/measurementTaskTypeParameter")
@Service
public class MeasurementTaskTypeParameterController {

    @Autowired
    MeasurementTaskTypeParameterRepository measurementTaskTypeParameterRepository;

    @Autowired
    ParameterRepository parameterRepository;

    @PostMapping("/save")
    @Transactional
    public JsonResult save(MeasurementTaskTypeParameter measurementTaskTypeParameter) {
        List<MeasurementTaskTypeParameter> measurementTaskTypeParameters =
                measurementTaskTypeParameterRepository.
                        findByMeasurementTaskTypeIdAndParameterId(measurementTaskTypeParameter.getMeasurementTaskTypeId(),
                                measurementTaskTypeParameter.getParameterId());

        if (measurementTaskTypeParameters.size() > 0) {
            return JsonResult.getErrorResult("该属性已绑定，请勿重复绑定");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        measurementTaskTypeParameter.setCreateTime(nowText);
        this.measurementTaskTypeParameterRepository.save(measurementTaskTypeParameter);
        return JsonResult.getSuccessResult();
    }

    @DeleteMapping("/delete")
    @Transactional
    public JsonResult delete(Long id) {
        this.measurementTaskTypeParameterRepository.delete(id);
        return JsonResult.getSuccessResult();
    }

    @GetMapping("/listByMeasurementTaskTypeId")
    @UserLoginToken
    public JsonResult listByMeasurementTaskTypeId(Long measurementTaskTypeId) {
        List<MeasurementTaskTypeParameter> measurementTaskTypeParameters = measurementTaskTypeParameterRepository.findByMeasurementTaskTypeId(measurementTaskTypeId);
        return JsonResult.getSuccessResult(measurementTaskTypeParameters);
    }

    @GetMapping("/index")
    @UserLoginToken
    public JsonResult index(Long measurementTaskTypeId) {
        List<MeasurementTaskTypeParameter> measurementTaskTypeParameters = measurementTaskTypeParameterRepository.findByMeasurementTaskTypeId(measurementTaskTypeId);
        List<Parameter> parameters = new ArrayList<>();
        measurementTaskTypeParameters.forEach(measurementTaskTypeParameter -> {
            Parameter parameter = parameterRepository.findOne(measurementTaskTypeParameter.getParameterId());
            parameters.add(parameter);
        });
        return JsonResult.getSuccessResult(parameters);
    }

}

package com.avic.controller.api;

import com.avic.common.JsonResult;
import com.avic.entity.*;
import com.avic.repository.*;
import com.avic.util.UserLoginToken;
import com.avic.vo.ParameterVo;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/open")
@Service
public class OpenController {

    @Autowired
    TextureRepository textureRepository;

    @Autowired
    MeasurementTaskTypeRepository measurementTaskTypeRepository;

    @Autowired
    MeasurementTaskTypeParameterRepository measurementTaskTypeParameterRepository;

    @Autowired
    ParameterRepository parameterRepository;

    @Autowired
    ParameterValueRepository parameterValueRepository;


    @GetMapping("/texture/listByMeasurementTaskTypeId")
    public JsonResult listByMeasurementTaskTypeId(Long measurementTaskTypeId) {
        List<Texture> textures = textureRepository.findByMeasurementTaskTypeId(measurementTaskTypeId);
        return JsonResult.getSuccessResult(textures);
    }

    @GetMapping("/measurementTaskType/listByMeasurementTaskId")
    public JsonResult listByMeasurementTaskId(Long measurementTaskId) {
        List<MeasurementTaskType> measurementTaskTypes = measurementTaskTypeRepository.findByMeasurementTaskId(measurementTaskId);
        return JsonResult.getSuccessResult(measurementTaskTypes);
    }

    @GetMapping("/parameter/listByMeasurementTaskTypeId")
    public JsonResult findParametersByMeasurementTaskTypeId(Long measurementTaskTypeId) {
        List<MeasurementTaskTypeParameter> measurementTaskTypeParameters = measurementTaskTypeParameterRepository.findByMeasurementTaskTypeId(measurementTaskTypeId);
        List<ParameterVo> parameterVos = measurementTaskTypeParameters.stream().map(measurementTaskTypeParameter -> {
            ParameterVo parameterVo = parameterRepository.findOne(measurementTaskTypeParameter.getParameterId()).toVo();
            parameterVo.setParameterValues(ParameterValue.toList(parameterValueRepository.findByParameterId(parameterVo.getId())));
            return parameterVo;
        }).collect(Collectors.toList());

        return JsonResult.getSuccessResult(parameterVos);
    }


}

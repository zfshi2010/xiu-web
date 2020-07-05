package com.avic.controller.api;

import com.avic.common.JsonResult;
import com.avic.entity.Texture;
import com.avic.repository.TextureRepository;
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
@RequestMapping("/open")
@Service
public class OpenController {

    @Autowired
    TextureRepository textureRepository;

    @GetMapping("/texture/listByMeasurementTaskId")
    public JsonResult listByMeasurementTaskId(Long measurementTaskId) {
        List<Texture> textures = textureRepository.findByMeasurementTaskId(measurementTaskId);
        return JsonResult.getSuccessResult(textures);
    }

}

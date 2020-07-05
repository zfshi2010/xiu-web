package com.avic.controller.api;

import com.avic.common.JsonResult;
import com.avic.entity.ParameterValue;
import com.avic.entity.Texture;
import com.avic.repository.ParameterValueRepository;
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
@RequestMapping("/api/texture")
@Service
public class TextureController {

    @Autowired
    TextureRepository textureRepository;

    @PostMapping("/save")
    @Transactional
    public JsonResult save(Texture texture) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        texture.setCreateTime(nowText);
        this.textureRepository.save(texture);
        return JsonResult.getSuccessResult();
    }

    @PostMapping("/update")
    @Transactional
    public JsonResult update(Texture texture) {
        this.textureRepository.save(texture);
        return JsonResult.getSuccessResult();
    }


    @DeleteMapping("/delete")
    @Transactional
    public JsonResult delete(Long id) {
        this.textureRepository.delete(id);
        return JsonResult.getSuccessResult();
    }

    @GetMapping("/listByMeasurementTaskId")
    @UserLoginToken
    public JsonResult listByMeasurementTaskId(Long measurementTaskId) {
        List<Texture> textures = textureRepository.findByMeasurementTaskId(measurementTaskId);
        return JsonResult.getSuccessResult(textures);
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
        Specification<Texture> specification = getSpecification(measurementTaskId);

        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");

        Page<Texture> parameterValues = textureRepository.findAll(specification, pageRequest);
        return JsonResult.getSuccessResult(parameterValues);
    }

    /**
     * 带条件的分页查询
     * @return Specification
     */
    private Specification<Texture> getSpecification(Long measurementTaskId) {
        return (Specification<Texture>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (measurementTaskId != null && measurementTaskId != 0L) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("measurementTaskId"), measurementTaskId));
            }
            return predicate;
        };
    }

}

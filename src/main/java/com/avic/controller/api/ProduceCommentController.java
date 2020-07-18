package com.avic.controller.api;

import com.avic.common.JsonResult;
import com.avic.entity.ProduceComment;
import com.avic.repository.ProduceCommentRepository;
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
@RequestMapping("/api/produceComment")
@Service
public class ProduceCommentController {

    @Autowired
    ProduceCommentRepository produceCommentRepository;

    @PostMapping("/save")
    @Transactional
    public JsonResult save(ProduceComment produceComment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        produceComment.setCreateTime(nowText);
        this.produceCommentRepository.save(produceComment);
        return JsonResult.getSuccessResult();
    }


    @DeleteMapping("/delete")
    @Transactional
    public JsonResult delete(Long id) {
        this.produceCommentRepository.delete(id);
        return JsonResult.getSuccessResult();
    }

    @GetMapping("/listByProduceId")
    @UserLoginToken
    public JsonResult listByProduceId(Long produceId) {
        List<ProduceComment> parameterValues = produceCommentRepository.findByProduceId(produceId);
        return JsonResult.getSuccessResult(parameterValues);
    }

    @GetMapping("/index")
    @UserLoginToken
    public JsonResult index(Integer page, Integer size, Long produceId) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        Specification<ProduceComment> specification = getSpecification(produceId);

        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");

        Page<ProduceComment> parameterValues = produceCommentRepository.findAll(specification, pageRequest);
        return JsonResult.getSuccessResult(parameterValues);
    }

    /**
     * 带条件的分页查询
     * @return Specification
     */
    private Specification<ProduceComment> getSpecification(Long produceId) {
        return (Specification<ProduceComment>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (produceId != null && produceId != 0L) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("produceId"), produceId));
            }
            return predicate;
        };
    }

}

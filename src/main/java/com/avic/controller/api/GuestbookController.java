package com.avic.controller.api;

import com.avic.common.JsonResult;
import com.avic.entity.Guestbook;
import com.avic.entity.ParameterValue;
import com.avic.repository.GuestbookRepository;
import com.avic.util.UserLoginToken;
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
@RequestMapping("/api/guestbook")
@Service
public class GuestbookController {

    @Autowired
    GuestbookRepository guestbookRepository;

    @PostMapping("/save")
    @Transactional
    public JsonResult save(Guestbook guestbook) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        guestbook.setCreateTime(nowText);
        this.guestbookRepository.save(guestbook);
        return JsonResult.getSuccessResult();
    }


    @DeleteMapping("/delete")
    @Transactional
    public JsonResult delete(Long id) {
        this.guestbookRepository.delete(id);
        return JsonResult.getSuccessResult();
    }


    @GetMapping("/index")
    @UserLoginToken
    public JsonResult index(Integer page, Integer size, String companyName, String name, String phone) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        Specification<Guestbook> specification = getSpecification(companyName, name, phone);

        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");

        Page<Guestbook> parameterValues = guestbookRepository.findAll(specification, pageRequest);
        return JsonResult.getSuccessResult(parameterValues);
    }

    /**
     * 带条件的分页查询
     * @return Specification
     */
    private Specification<Guestbook> getSpecification(String companyName, String name, String phone) {
        return (Specification<Guestbook>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (StringUtils.isNotBlank(companyName)) {
                predicate.getExpressions().add(criteriaBuilder.like(root.get("companyName"), "%" + companyName + "%"));
            }
            if (StringUtils.isNotBlank(name)) {
                predicate.getExpressions().add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            if (StringUtils.isNotBlank(phone)) {
                predicate.getExpressions().add(criteriaBuilder.like(root.get("phone"), "%" + phone + "%"));
            }
            return predicate;
        };
    }

}

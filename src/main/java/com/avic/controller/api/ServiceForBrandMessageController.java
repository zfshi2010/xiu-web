package com.avic.controller.api;

import com.avic.common.JsonResult;
import com.avic.entity.ServiceForBrandMessage;
import com.avic.repository.ServiceForBrandMessageRepository;
import com.avic.util.UserLoginToken;
import com.avic.vo.ServiceForBrandMessageVo;
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
@RequestMapping("/api/serviceForBrandMessage")
@Service
public class ServiceForBrandMessageController {

    @Autowired
    ServiceForBrandMessageRepository serviceForBrandMessageRepository;

    @GetMapping("/detail")
    @UserLoginToken
    @Transactional(readOnly = true)
    public JsonResult detail(Long id) {
        ServiceForBrandMessageVo serviceForBrandMessageVo = this.serviceForBrandMessageRepository.getOne(id).toVo();
        return JsonResult.getSuccessResult(serviceForBrandMessageVo);
    }

    @PostMapping("/save")
    @UserLoginToken
    @Transactional
    public JsonResult save(ServiceForBrandMessage serviceForBrandMessage) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        serviceForBrandMessage.setCreateTime(nowText);
        this.serviceForBrandMessageRepository.save(serviceForBrandMessage);
        return JsonResult.getSuccessResult();
    }

    @DeleteMapping("/delete")
    @Transactional
    public JsonResult delete(Long id) {
        this.serviceForBrandMessageRepository.delete(id);
        return JsonResult.getSuccessResult();
    }

    @PostMapping("/update")
    @Transactional
    public JsonResult update(ServiceForBrandMessage serviceForBrandMessage) {
        this.serviceForBrandMessageRepository.save(serviceForBrandMessage);
        return JsonResult.getSuccessResult();
    }

    @GetMapping("/index")
    @UserLoginToken
    public JsonResult index(Integer page, Integer size, Long serviceForBrandId) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        Specification<ServiceForBrandMessage> specification = getMenuSpecification(serviceForBrandId);

        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");

        Page<ServiceForBrandMessage> serviceForBrandMessages = serviceForBrandMessageRepository.findAll(specification,pageRequest);
        return JsonResult.getSuccessResult(serviceForBrandMessages);
    }

    /**
     * 带条件的分页查询
     * @return Specification
     */
    private Specification<ServiceForBrandMessage> getMenuSpecification(Long serviceForBrandId) {
        return (Specification<ServiceForBrandMessage>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (serviceForBrandId != null && serviceForBrandId != 0L) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("serviceForBrandId"), serviceForBrandId));
            }
            return predicate;
        };
    }

}

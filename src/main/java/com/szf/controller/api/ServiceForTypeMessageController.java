package com.szf.controller.api;

import com.szf.common.JsonResult;
import com.szf.entity.ServiceForBrandMessage;
import com.szf.entity.ServiceForType;
import com.szf.entity.ServiceForTypeMessage;
import com.szf.repository.ServiceForTypeMessageRepository;
import com.szf.repository.ServiceForTypeRepository;
import com.szf.util.UserLoginToken;
import com.szf.vo.ServiceForTypeMessageVo;
import com.szf.vo.ServiceForTypeVo;
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
@RequestMapping("/api/serviceForTypeMessage")
@Service
public class ServiceForTypeMessageController {

    @Autowired
    ServiceForTypeMessageRepository serviceForTypeMessageRepository;

    @GetMapping("/detail")
    @UserLoginToken
    @Transactional(readOnly = true)
    public JsonResult detail(Long id) {
        ServiceForTypeMessageVo serviceForTypeMessageVo = this.serviceForTypeMessageRepository.getOne(id).toVo();
        return JsonResult.getSuccessResult(serviceForTypeMessageVo);
    }

    @PostMapping("/save")
    @UserLoginToken
    @Transactional
    public JsonResult save(ServiceForTypeMessage serviceForTypeMessage) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        serviceForTypeMessage.setCreateTime(nowText);
        this.serviceForTypeMessageRepository.save(serviceForTypeMessage);
        return JsonResult.getSuccessResult();
    }

    @PostMapping("/update")
    @Transactional
    public JsonResult update(ServiceForTypeMessage serviceForTypeMessage) {
        this.serviceForTypeMessageRepository.save(serviceForTypeMessage);
        return JsonResult.getSuccessResult();
    }

    @DeleteMapping("/delete")
    @Transactional
    public JsonResult delete(Long id) {
        this.serviceForTypeMessageRepository.delete(id);
        return JsonResult.getSuccessResult();
    }

    @GetMapping("/index")
    @UserLoginToken
    public JsonResult index(Integer page, Integer size, Long serviceForTypeId) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        Specification<ServiceForTypeMessage> specification = getMenuSpecification(serviceForTypeId);

        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");

        Page<ServiceForTypeMessage> serviceForTypeMessages = serviceForTypeMessageRepository.findAll(specification,pageRequest);
        return JsonResult.getSuccessResult(serviceForTypeMessages);
    }

    /**
     * 带条件的分页查询
     * @return Specification
     */
    private Specification<ServiceForTypeMessage> getMenuSpecification(Long serviceForTypeId) {
        return (Specification<ServiceForTypeMessage>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (serviceForTypeId != null && serviceForTypeId != 0L) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("serviceForTypeId"), serviceForTypeId));
            }
            return predicate;
        };
    }

}

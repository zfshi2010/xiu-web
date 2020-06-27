package com.szf.controller.api;

import com.szf.common.JsonResult;
import com.szf.entity.ServiceForBrandMessage;
import com.szf.entity.ServiceForBrandProject;
import com.szf.repository.ServiceForBrandMessageRepository;
import com.szf.repository.ServiceForBrandProjectRepository;
import com.szf.util.UserLoginToken;
import com.szf.vo.ServiceForBrandMessageVo;
import com.szf.vo.ServiceForBrandProjectVo;
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
@RequestMapping("/api/serviceForBrandProject")
@Service
public class ServiceForBrandProjectController {

    @Autowired
    ServiceForBrandProjectRepository serviceForBrandProjectRepository;

    @GetMapping("/detail")
    @UserLoginToken
    @Transactional(readOnly = true)
    public JsonResult detail(Long id) {
        ServiceForBrandProjectVo serviceForBrandProjectVo = this.serviceForBrandProjectRepository.getOne(id).toVo();
        return JsonResult.getSuccessResult(serviceForBrandProjectVo);
    }

    @PostMapping("/save")
    @UserLoginToken
    @Transactional
    public JsonResult save(ServiceForBrandProject serviceForBrandProject) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        serviceForBrandProject.setCreateTime(nowText);
        this.serviceForBrandProjectRepository.save(serviceForBrandProject);
        return JsonResult.getSuccessResult();
    }

    @PostMapping("/update")
    @Transactional
    public JsonResult update(ServiceForBrandProject serviceForBrandProject) {
        this.serviceForBrandProjectRepository.save(serviceForBrandProject);
        return JsonResult.getSuccessResult();
    }

    @DeleteMapping("/delete")
    @Transactional
    public JsonResult delete(Long id) {
        this.serviceForBrandProjectRepository.delete(id);
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
        Specification<ServiceForBrandProject> specification = getMenuSpecification(serviceForBrandId);

        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");

        Page<ServiceForBrandProject> serviceForBrandProjects = serviceForBrandProjectRepository.findAll(specification,pageRequest);
        return JsonResult.getSuccessResult(serviceForBrandProjects);
    }

    /**
     * 带条件的分页查询
     * @return Specification
     */
    private Specification<ServiceForBrandProject> getMenuSpecification(Long serviceForBrandId) {
        return (Specification<ServiceForBrandProject>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (serviceForBrandId != null && serviceForBrandId != 0L) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("serviceForBrandId"), serviceForBrandId));
            }
            return predicate;
        };
    }

}

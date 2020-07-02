package com.avic.controller.api;

import com.avic.common.JsonResult;
import com.avic.entity.Banner;
import com.avic.repository.BannerRepository;
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

@RestController
@RequestMapping("/api/banner")
@Service
public class BannerController {

    @Autowired
    BannerRepository bannerRepository;

    @PostMapping("/save")
    @Transactional
    public JsonResult save(Banner banner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        banner.setCreateTime(nowText);
        this.bannerRepository.save(banner);
        return JsonResult.getSuccessResult();
    }

    @PostMapping("/update")
    @Transactional
    public JsonResult update(Banner banner) {
        this.bannerRepository.save(banner);
        return JsonResult.getSuccessResult();
    }

    /**
     * 带条件的分页查询
     * @return Specification
     */
    private Specification<Banner> getSpecificationNotBrand() {
        return (Specification<Banner>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            predicate.getExpressions().add(criteriaBuilder.notEqual(root.get("type"),Banner.BRAND_BANNER));
            predicate.getExpressions().add(criteriaBuilder.notEqual(root.get("type"),Banner.BRAND_LOGO));
            return predicate;
        };
    }

    /**
     * 带条件的分页查询
     * @return Specification
     */
    private Specification<Banner> getSpecification(Long serviceForBrandId) {
        return (Specification<Banner>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (serviceForBrandId != null && serviceForBrandId != 0l) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("serviceForBrandId"), serviceForBrandId));
            }
            return predicate;
        };
    }

    @GetMapping("/index")
    public JsonResult index(Integer page, Integer size) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        Specification<Banner> specification = getSpecificationNotBrand();
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<Banner> banners = bannerRepository.findAll(specification,pageRequest);
        banners.getContent().forEach(banner -> {
            switch (banner.getType()) {
                case Banner.ADVERT : banner.setTypeName("广告位"); break;
                case Banner.BANNER : banner.setTypeName("banner"); break;
                case Banner.LOGO : banner.setTypeName("logo"); break;
                case Banner.PHONE : banner.setTypeName("联系方式"); break;
                default:banner.setTypeName("banner"); break;
            }
        });
        return JsonResult.getSuccessResult(banners);
    }

    @DeleteMapping("/delete")
    @Transactional
    public JsonResult delete(Long id) {
        this.bannerRepository.delete(id);
        return JsonResult.getSuccessResult();
    }

    @GetMapping("/indexForBrand")
    @UserLoginToken
    public JsonResult indexForBrand(Integer page, Integer size, Long serviceForBrandId) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        Specification<Banner> specification = getSpecification(serviceForBrandId);
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<Banner> banners = bannerRepository.findAll(specification,pageRequest);
        banners.getContent().forEach(banner -> {
            switch (banner.getType()) {
                case Banner.BRAND_BANNER : banner.setTypeName("banner"); break;
                case Banner.BRAND_LOGO : banner.setTypeName("logo"); break;
                default:banner.setTypeName("banner"); break;
            }
        });
        return JsonResult.getSuccessResult(banners);
    }

}

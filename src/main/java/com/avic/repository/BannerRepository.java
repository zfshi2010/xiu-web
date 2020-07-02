package com.avic.repository;

import com.avic.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long>,JpaSpecificationExecutor {

    List<Banner> findByType(String type);

    List<Banner> findByTypeAndServiceForBrandId(String type, Long serviceForBrandId);

}
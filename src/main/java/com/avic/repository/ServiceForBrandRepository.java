package com.avic.repository;

import com.avic.entity.ServiceForBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceForBrandRepository extends JpaRepository<ServiceForBrand, Long> {

    List<ServiceForBrand> findByTypeId(long typeId);

}

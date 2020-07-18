package com.avic.repository;

import com.avic.entity.ProductBrand;
import com.avic.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductBrandRepository extends JpaRepository<ProductBrand, Long>,JpaSpecificationExecutor {

    List<ProductBrand> findByProductFieldId(Long productFieldId);
}

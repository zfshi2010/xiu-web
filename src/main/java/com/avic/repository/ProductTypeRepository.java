package com.avic.repository;

import com.avic.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long>,JpaSpecificationExecutor {

    List<ProductType> findByProductBrandId(Long productBrandId);
}

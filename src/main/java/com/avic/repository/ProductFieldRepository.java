package com.avic.repository;

import com.avic.entity.ProductField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductFieldRepository extends JpaRepository<ProductField, Long> {

}

package com.avic.repository;

import com.avic.entity.Produce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduceRepository extends JpaRepository<Produce, Long>,JpaSpecificationExecutor {


    List<Produce> findByProductTypeId(Long productTypeId);

}

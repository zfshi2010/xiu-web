package com.avic.repository;

import com.avic.entity.ParameterValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParameterValueRepository extends JpaRepository<ParameterValue, Long>,JpaSpecificationExecutor {

    List<ParameterValue> findByParameterId(Long parameterId);

}

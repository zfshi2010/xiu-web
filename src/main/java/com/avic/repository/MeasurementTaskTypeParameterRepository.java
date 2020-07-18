package com.avic.repository;

import com.avic.entity.MeasurementTaskTypeParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementTaskTypeParameterRepository extends JpaRepository<MeasurementTaskTypeParameter, Long>,JpaSpecificationExecutor {

    List<MeasurementTaskTypeParameter> findByMeasurementTaskTypeId(Long measurementTaskTypeId);

    List<MeasurementTaskTypeParameter> findByMeasurementTaskTypeIdAndParameterId(Long measurementTaskTypeId, Long parameterId);
}

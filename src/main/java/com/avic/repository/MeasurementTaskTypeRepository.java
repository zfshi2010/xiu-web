package com.avic.repository;

import com.avic.entity.MeasurementTaskType;
import com.avic.entity.Texture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementTaskTypeRepository extends JpaRepository<MeasurementTaskType, Long>,JpaSpecificationExecutor {

    List<MeasurementTaskType> findByMeasurementTaskId(Long measurementTaskId);
}

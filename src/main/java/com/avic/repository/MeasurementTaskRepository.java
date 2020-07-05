package com.avic.repository;

import com.avic.entity.MeasurementTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementTaskRepository extends JpaRepository<MeasurementTask, Long> {

}

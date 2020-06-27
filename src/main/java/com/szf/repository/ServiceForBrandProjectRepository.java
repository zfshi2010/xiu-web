package com.szf.repository;

import com.szf.entity.ServiceForBrandProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceForBrandProjectRepository extends JpaRepository<ServiceForBrandProject, Long>,JpaSpecificationExecutor {


    List<ServiceForBrandProject> findByServiceForBrandId(Long serviceForBrandId);

}

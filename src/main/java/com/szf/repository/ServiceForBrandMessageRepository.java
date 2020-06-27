package com.szf.repository;

import com.szf.entity.ServiceForBrand;
import com.szf.entity.ServiceForBrandMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceForBrandMessageRepository extends JpaRepository<ServiceForBrandMessage, Long>,JpaSpecificationExecutor {


    List<ServiceForBrandMessage> findByServiceForBrandId(Long serviceForBrandId);

}

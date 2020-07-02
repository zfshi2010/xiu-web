package com.avic.repository;

import com.avic.entity.ServiceForBrandMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceForBrandMessageRepository extends JpaRepository<ServiceForBrandMessage, Long>,JpaSpecificationExecutor {


    List<ServiceForBrandMessage> findByServiceForBrandId(Long serviceForBrandId);

}

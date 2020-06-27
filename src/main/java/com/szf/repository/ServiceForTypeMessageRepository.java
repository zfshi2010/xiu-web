package com.szf.repository;

import com.szf.entity.ServiceForTypeMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceForTypeMessageRepository extends JpaRepository<ServiceForTypeMessage, Long>,JpaSpecificationExecutor {

    public List<ServiceForTypeMessage> findByServiceForTypeId(Long serviceForTypeId);
}

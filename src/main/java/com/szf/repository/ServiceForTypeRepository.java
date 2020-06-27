package com.szf.repository;

import com.szf.entity.ServiceForType;
import com.szf.entity.SysConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceForTypeRepository extends JpaRepository<ServiceForType, Long> {

    List<ServiceForType> findByIsMenu(Boolean isMenu);

}

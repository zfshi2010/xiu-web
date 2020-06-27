package com.szf.repository;

import com.szf.entity.SysConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysConfigRepository extends JpaRepository<SysConfig, Long> {

    SysConfig findByType(String type);

}

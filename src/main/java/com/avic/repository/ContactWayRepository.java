package com.avic.repository;

import com.avic.entity.ContactWay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactWayRepository extends JpaRepository<ContactWay, Long> {

}

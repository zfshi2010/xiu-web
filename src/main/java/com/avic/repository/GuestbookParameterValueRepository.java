package com.avic.repository;

import com.avic.entity.GuestbookParameterValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestbookParameterValueRepository extends JpaRepository<GuestbookParameterValue, Long> ,JpaSpecificationExecutor {


    List<GuestbookParameterValue> findByGuestbookId(Long guestbookId);

}

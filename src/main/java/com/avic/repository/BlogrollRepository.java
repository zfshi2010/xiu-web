package com.avic.repository;

import com.avic.entity.Blogroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogrollRepository extends JpaRepository<Blogroll, Long> {

}

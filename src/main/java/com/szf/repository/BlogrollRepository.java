package com.szf.repository;

import com.szf.entity.Blogroll;
import com.szf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogrollRepository extends JpaRepository<Blogroll, Long> {

}

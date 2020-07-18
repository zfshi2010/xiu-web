package com.avic.repository;

import com.avic.entity.ProduceComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduceCommentRepository extends JpaRepository<ProduceComment, Long>,JpaSpecificationExecutor {


    List<ProduceComment> findByProduceId(Long produceId);

}

package com.system.exam.exam.repositories;

import com.system.exam.exam.entities.MCQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MCQuestionRepo extends JpaRepository<MCQuestion, Long> {
}

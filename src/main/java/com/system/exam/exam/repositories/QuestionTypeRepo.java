package com.system.exam.exam.repositories;

import com.system.exam.exam.entities.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionTypeRepo extends JpaRepository<QuestionType, Integer> {
}

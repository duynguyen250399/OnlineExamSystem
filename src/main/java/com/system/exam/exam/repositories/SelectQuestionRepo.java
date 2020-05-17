package com.system.exam.exam.repositories;

import com.system.exam.exam.entities.SelectQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectQuestionRepo extends JpaRepository<SelectQuestion, Long> {
}

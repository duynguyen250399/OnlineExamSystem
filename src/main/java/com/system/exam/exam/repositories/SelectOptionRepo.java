package com.system.exam.exam.repositories;

import com.system.exam.exam.entities.SelectOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectOptionRepo extends JpaRepository<SelectOption, Long> {
}

package com.system.exam.exam.repositories;

import com.system.exam.exam.entities.MCOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MCOptionRepo extends JpaRepository<MCOption, Long> {

}

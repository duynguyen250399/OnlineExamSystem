package com.system.exam.exam.services;

import com.system.exam.exam.entities.MCQuestion;
import com.system.exam.exam.repositories.MCQuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MCQuestionService {

    private MCQuestionRepo mcQuestionRepo;

    @Autowired
    public MCQuestionService(MCQuestionRepo mcQuestionRepo) {
        this.mcQuestionRepo = mcQuestionRepo;
    }

    public MCQuestion createQuestion(MCQuestion question){
        return this.mcQuestionRepo.save(question);
    }

    public MCQuestion updateQuestion(MCQuestion question){
        return this.mcQuestionRepo.save(question);
    }

}

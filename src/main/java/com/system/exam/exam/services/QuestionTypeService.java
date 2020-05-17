package com.system.exam.exam.services;

import com.system.exam.exam.entities.QuestionType;
import com.system.exam.exam.repositories.QuestionTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionTypeService {
    private QuestionTypeRepo questionTypeRepo;

    @Autowired
    public QuestionTypeService(QuestionTypeRepo questionTypeRepo) {
        this.questionTypeRepo = questionTypeRepo;
    }

    public List<QuestionType> getQuestionTypes(){
        return this.questionTypeRepo.findAll();
    }
}

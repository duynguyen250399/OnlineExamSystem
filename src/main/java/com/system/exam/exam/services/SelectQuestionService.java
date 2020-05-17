package com.system.exam.exam.services;

import com.system.exam.exam.entities.SelectQuestion;
import com.system.exam.exam.repositories.SelectQuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectQuestionService {
    private SelectQuestionRepo selectQuestionRepo;

    @Autowired
    public SelectQuestionService(SelectQuestionRepo selectQuestionRepo) {
        this.selectQuestionRepo = selectQuestionRepo;
    }

    public List<SelectQuestion> getSelectQuestions(){
        return this.selectQuestionRepo.findAll();
    }

    public SelectQuestion createSelectQuestion(SelectQuestion selectQuestion){
        return this.selectQuestionRepo.save(selectQuestion);
    }

    public SelectQuestion updateSelectQuestion(SelectQuestion selectQuestion){
        return this.selectQuestionRepo.save(selectQuestion);
    }
}

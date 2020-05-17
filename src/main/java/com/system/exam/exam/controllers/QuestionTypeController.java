package com.system.exam.exam.controllers;

import com.system.exam.exam.entities.MCQuestion;
import com.system.exam.exam.entities.QuestionType;
import com.system.exam.exam.entities.Quiz;
import com.system.exam.exam.services.QuestionTypeService;
import com.system.exam.exam.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class QuestionTypeController {
    private QuestionTypeService questionTypeService;
    private QuizService quizService;

    @Autowired
    public QuestionTypeController(QuestionTypeService questionTypeService, QuizService quizService) {
        this.questionTypeService = questionTypeService;
        this.quizService = quizService;
    }

    @GetMapping("/selectQuestionType")
    public String selectQuestionType(Model model, @RequestParam("qid") int quizId){
        List<QuestionType> questionTypes = this.questionTypeService.getQuestionTypes();
        model.addAttribute("questionTypes", questionTypes);
        Quiz quiz = this.quizService.getQuizById(quizId);
        if(quiz == null || quizId == 0){
            return "error";
        }

        model.addAttribute("quiz", quiz);

        return "selectQuestionType";
    }

    @PostMapping("/selectQuestionType")
    public String createQuestionPage(Model model, @RequestParam("questionType") int questionType, @RequestParam("quizId") int quizId){
        Quiz quiz = this.quizService.getQuizById(quizId);
        if(quiz == null || quizId == 0){
            return "error";
        }

        model.addAttribute("quiz", quiz);
        MCQuestion mcQuestion = new MCQuestion();
        mcQuestion.setQuiz(quiz);
        model.addAttribute("question", mcQuestion);
        model.addAttribute("questionType", questionType);

        if(questionType == 1){
            return "createMcQuestion";
        }
        else if(questionType == 2){
            return "createSelectQuestion";
        }
        else{
            return "error";
        }
    }
}

package com.system.exam.exam.controllers;

import com.system.exam.exam.entities.MCOption;
import com.system.exam.exam.entities.MCQuestion;
import com.system.exam.exam.entities.Quiz;
import com.system.exam.exam.services.MCOptionService;
import com.system.exam.exam.services.MCQuestionService;
import com.system.exam.exam.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController {
    private MCQuestionService mcQuestionService;
    private QuizService quizService;
    private MCOptionService mcOptionService;

    @Autowired
    public QuestionController(MCQuestionService mcQuestionService, QuizService quizService, MCOptionService mcOptionService) {
        this.mcQuestionService = mcQuestionService;
        this.quizService = quizService;
        this.mcOptionService = mcOptionService;
    }

    @PostMapping("/createMcQuestion")
    public String createQuestion(MCQuestion question, HttpServletRequest request){
        String[] options = request.getParameterValues("op");
        if(options.length >= 8){
            return "error";
        }

        int correctOption = Integer.parseInt(request.getParameter("answer"));
        String qid = request.getParameter("qid");
        if(qid.isEmpty() || qid == null){
            return "error";
        }
        String qtype = request.getParameter("qtype");
        if(qtype.isEmpty() || qtype == null){
            return "error";
        }

        int questionType = Integer.parseInt(qtype);

        int quizId = Integer.parseInt(qid);
        Quiz quiz = this.quizService.getQuizById(quizId);
        if(quiz == null){
            return "error";
        }

        question.setQuiz(quiz);
        question.setQuestionType(questionType);

        MCQuestion createdQuestion = this.mcQuestionService.createQuestion(question);
        if(createdQuestion != null){

            List<MCOption> mcOptions = new ArrayList<>();
            for(String op : options){
                MCOption mcOption = new MCOption();
                mcOption.setOption(op);
                mcOption.setQuestion(createdQuestion);
                MCOption createdOption = this.mcOptionService.createQuestionOption(mcOption);

                if(createdOption == null){
                    return "error";
                }

                mcOptions.add(createdOption);
            }

            String correctOpStr = options[correctOption - 1];
            for(MCOption op : mcOptions){
                if(op.getOption().equals(correctOpStr)){
                    createdQuestion.setCorrectAnswer(op.getId());
                    break;
                }
            }

            MCQuestion updatedQuestion = this.mcQuestionService.updateQuestion(createdQuestion);

            if(updatedQuestion == null){
                return "error";
            }
        }
        else{
            return "error";
        }

        return "success";
    }
}

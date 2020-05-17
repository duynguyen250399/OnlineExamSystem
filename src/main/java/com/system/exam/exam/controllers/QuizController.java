package com.system.exam.exam.controllers;

import com.system.exam.exam.entities.Quiz;
import com.system.exam.exam.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class QuizController {

    private QuizService quizService;
    private final String[] ALPHABET = {"A", "B", "C", "D", "E", "F", "G", "H"};

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/createQuiz")
    public String createQuizPage(Model model){
        model.addAttribute("quiz", new Quiz());
        return "createQuiz";
    }

    @PostMapping("/createQuiz")
    public String createQuiz(Quiz quiz){
        Quiz newQuiz = this.quizService.createQuiz(quiz);
        if(newQuiz != null){
            return "success";
        }

        return "error";
    }

    @GetMapping("/quiz")
    public String quizListPage(Model model){
        List<Quiz> quizList = quizService.getQuizList();
        model.addAttribute("quizList", quizList);
        return "quizList";
    }

    @GetMapping("/quiz/{id}")
    public String quizPage(Model model, @PathVariable(name = "id") int id){
        Quiz quiz = quizService.getQuizById(id);
        if(quiz == null){
            return "error";
        }

        model.addAttribute("quiz", quiz);
        model.addAttribute("alphabet", ALPHABET);
        return "quiz";
    }


}

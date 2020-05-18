package com.system.exam.exam.controllers;

import com.system.exam.exam.entities.*;
import com.system.exam.exam.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private MCOptionService mcOptionService;
    private SelectQuestionService selectQuestionService;
    private SelectOptionService selectOptionService;
    private QuizService quizService;
    private MCQuestionService mcQuestionService;
    private QuestionTypeService questionTypeService;

    private final String[] ALPHABET = {"A", "B", "C", "D", "E", "F", "G", "H"};

    @Autowired
    public AdminController(MCOptionService mcOptionService, SelectQuestionService selectQuestionService, SelectOptionService selectOptionService, QuizService quizService, MCQuestionService mcQuestionService, QuestionTypeService questionTypeService) {
        this.mcOptionService = mcOptionService;
        this.selectQuestionService = selectQuestionService;
        this.selectOptionService = selectOptionService;
        this.quizService = quizService;
        this.mcQuestionService = mcQuestionService;
        this.questionTypeService = questionTypeService;
    }

    @GetMapping("/dashboard")
    public String adminPage(){

        return "admin";
    }

    @GetMapping("/createQuizPage")
    public String createQuizPage(Model model) {
        model.addAttribute("quiz", new Quiz());
        return "createQuiz";
    }

    @PostMapping("/createQuiz")
    public String createQuiz(Quiz quiz) {
        Quiz newQuiz = this.quizService.createQuiz(quiz);
        if (newQuiz != null) {
            return "success";
        }

        return "error";
    }

    @GetMapping("/quiz")
    public String quizListPage(Model model) {
        List<Quiz> quizList = quizService.getQuizList();
        model.addAttribute("quizList", quizList);
        return "quizList";
    }

    @GetMapping("/quiz/{id}")
    public String quizPage(Model model, @PathVariable(name = "id") int id) {
        Quiz quiz = quizService.getQuizById(id);
        if (quiz == null) {
            return "error";
        }

        model.addAttribute("quiz", quiz);
        model.addAttribute("alphabet", ALPHABET);
        return "quiz";
    }

    @PostMapping("/createQuestion")
    public String createMcQuestion(MCQuestion mcQuestion, SelectQuestion selectQuestion, HttpServletRequest request) {
        // get options of question
        String[] options = request.getParameterValues("op");
        if (options.length >= 8) {
            return "error";
        }

        // get question type
        String qtype = request.getParameter("qtype");
        if (qtype.isEmpty() || qtype == null) {
            return "error";
        }
        int questionType = Integer.parseInt(qtype);

        // get correct option
        int correctOption = Integer.parseInt(request.getParameter("answer"));

        // get quiz
        String qid = request.getParameter("qid");
        if (qid.isEmpty() || qid == null) {
            return "error";
        }
        int quizId = Integer.parseInt(qid);
        Quiz quiz = this.quizService.getQuizById(quizId);
        if (quiz == null) {
            return "error";
        }

        if (questionType == 1) {
            return processAndCreateMCQuestion(mcQuestion, quiz, questionType, options, correctOption);
        } else if (questionType == 2) {
            String[] correctOptions = request.getParameterValues("answer");
            return processAndCreateSelectQuestion(selectQuestion, quiz, questionType, options, correctOptions);
        }

        return "error";
    }

    @GetMapping("/selectQuestionType")
    public String selectQuestionType(Model model, @RequestParam("qid") int quizId) {
        List<QuestionType> questionTypes = this.questionTypeService.getQuestionTypes();
        model.addAttribute("questionTypes", questionTypes);
        Quiz quiz = this.quizService.getQuizById(quizId);
        if (quiz == null || quizId == 0) {
            return "error";
        }

        model.addAttribute("quiz", quiz);

        return "selectQuestionType";
    }

    @PostMapping("/selectQuestionType")
    public String createQuestionPage(Model model, @RequestParam("questionType") int questionType, @RequestParam("quizId") int quizId) {
        Quiz quiz = this.quizService.getQuizById(quizId);
        if (quiz == null || quizId == 0) {
            return "error";
        }

        model.addAttribute("quiz", quiz);
        model.addAttribute("questionType", questionType);

        if (questionType == 1) {
            MCQuestion mcQuestion = new MCQuestion();
            mcQuestion.setQuiz(quiz);
            model.addAttribute("mcQuestion", mcQuestion);
            return "createMcQuestion";
        } else if (questionType == 2) {
            SelectQuestion selectQuestion = new SelectQuestion();
            selectQuestion.setQuiz(quiz);
            model.addAttribute("selectQuestion", selectQuestion);
            return "createSelectQuestion";
        } else {
            return "error";
        }
    }

    private String processAndCreateMCQuestion(MCQuestion mcQuestion, Quiz quiz, int questionType, String[] options, int correctOption) {
        // set up quiz and question type for creating question
        mcQuestion.setQuiz(quiz);
        mcQuestion.setQuestionType(questionType);

        MCQuestion createdQuestion = this.mcQuestionService.createQuestion(mcQuestion);
        if (createdQuestion != null) {

            List<MCOption> mcOptions = new ArrayList<>();
            for (String op : options) {
                MCOption mcOption = new MCOption();
                mcOption.setOption(op);
                mcOption.setQuestion(createdQuestion);
                MCOption createdOption = this.mcOptionService.createQuestionOption(mcOption);

                if (createdOption == null) {
                    return "error";
                }

                mcOptions.add(createdOption);
            }

            String correctOpStr = options[correctOption - 1];
            for (MCOption op : mcOptions) {
                if (op.getOption().equals(correctOpStr)) {
                    createdQuestion.setCorrectAnswer(op.getId());
                    break;
                }
            }

            MCQuestion updatedQuestion = this.mcQuestionService.updateQuestion(createdQuestion);

            if (updatedQuestion == null) {
                return "error";
            }
        } else {
            return "error";
        }

        return "success";
    }

    private String processAndCreateSelectQuestion(SelectQuestion selectQuestion, Quiz quiz, int questionType, String[] options, String[] correctOptions) {
        // set up quiz and question type for creating question
        selectQuestion.setQuiz(quiz);
        selectQuestion.setQuestionType(questionType);

        SelectQuestion createdQuestion = this.selectQuestionService.createSelectQuestion(selectQuestion);
        if (createdQuestion != null) {

            // create options
            List<SelectOption> selectOptions = new ArrayList<>();
            for (String op : options) {
                SelectOption selectOption = new SelectOption();
                selectOption.setOption(op);
                selectOption.setSelectQuestion(createdQuestion);
                SelectOption createdOption = this.selectOptionService.createSelectOption(selectOption);

                if (createdOption == null) {
                    return "error";
                }

                selectOptions.add(createdOption);
            }

            String answer = "";

            for (String op : correctOptions) {
                int index = Integer.parseInt(op);
                answer += selectOptions.get(index - 1).getId() + ";";
            }

            createdQuestion.setAnswer(answer);

            SelectQuestion updatedQuestion = this.selectQuestionService.updateSelectQuestion(createdQuestion);

            if (updatedQuestion == null) {
                return "error";
            }
        } else {
            return "error";
        }

        return "success";
    }

}

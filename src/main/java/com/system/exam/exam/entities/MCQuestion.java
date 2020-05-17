package com.system.exam.exam.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class MCQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String question;
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private List<MCOption> options;
    private long correctAnswer;
    /*
        "1": Multiple choices question
        "3": Open question
        "2": Select question
     */
    private int questionType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<MCOption> getOptions() {
        return options;
    }

    public void setOptions(List<MCOption> options) {
        this.options = options;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public long getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(long correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}

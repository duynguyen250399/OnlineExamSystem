package com.system.exam.exam.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private long time;
    private double totalScore;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quiz")
    private List<MCQuestion> mcQuestions;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quiz")
    private List<SelectQuestion> selectQuestions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public List<MCQuestion> getMcQuestions() {
        return mcQuestions;
    }

    public void setMcQuestions(List<MCQuestion> mcQuestions) {
        this.mcQuestions = mcQuestions;
    }

    public List<SelectQuestion> getSelectQuestions() {
        return selectQuestions;
    }

    public void setSelectQuestions(List<SelectQuestion> selectQuestions) {
        this.selectQuestions = selectQuestions;
    }
}

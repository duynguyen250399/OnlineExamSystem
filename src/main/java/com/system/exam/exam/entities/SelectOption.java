package com.system.exam.exam.entities;

import javax.persistence.*;

@Entity
public class SelectOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String option;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private SelectQuestion selectQuestion;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public SelectQuestion getSelectQuestion() {
        return selectQuestion;
    }

    public void setSelectQuestion(SelectQuestion selectQuestion) {
        this.selectQuestion = selectQuestion;
    }
}

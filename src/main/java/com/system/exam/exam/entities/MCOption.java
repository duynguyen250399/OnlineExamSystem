package com.system.exam.exam.entities;

import javax.persistence.*;

@Entity
public class MCOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "content")
    private String option;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private MCQuestion question;

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

    public MCQuestion getQuestion() {
        return question;
    }

    public void setQuestion(MCQuestion question) {
        this.question = question;
    }
}

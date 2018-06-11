package com.codecool.PTA.quest;

import javax.persistence.*;

@Entity
public class FillInAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String answer;

    @ManyToOne
    private FillInTheBlank question;

    public FillInAnswer() {}

    public FillInAnswer(String answer, FillInTheBlank question) {
        this.answer = answer;
        this.question = question;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public FillInTheBlank getQuestion() {
        return question;
    }

    public void setQuestion(FillInTheBlank question) {
        this.question = question;
    }
}

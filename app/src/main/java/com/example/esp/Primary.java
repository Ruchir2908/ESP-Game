package com.example.esp;

public class Primary {

    Integer[] answers = new Integer[2];
    boolean isCompleted;
    String question;
    Secondary options;

    public Primary(Integer[] answers, boolean isCompleted, String question, Secondary options) {
        this.answers = answers;
        this.isCompleted = isCompleted;
        this.question = question;
        this.options = options;
    }

}

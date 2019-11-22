package com.example.esp;

public class Primary {

    public Integer[] getAnswers() {
        return answers;
    }

    public void setAnswers(Integer[] answers) {
        this.answers = answers;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Secondary getOptions() {
        return options;
    }

    public void setOptions(Secondary options) {
        this.options = options;
    }

    Integer[] answers = new Integer[2];
    boolean isCompleted;
    String question;
    Secondary options;

    public Primary(boolean isCompleted, String question, Secondary options) {
        this.isCompleted = isCompleted;
        this.question = question;
        this.options = options;
    }

}

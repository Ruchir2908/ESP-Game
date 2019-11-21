package com.example.esp;

import java.util.ArrayList;

public class Task {

    boolean complete;
    ArrayList<Integer> ques;
    String firstPlayer;
    String secondPlayer;
    // check doc id???
    String docId;

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public ArrayList<Integer> getQues() {
        return ques;
    }

    public void setQues(ArrayList<Integer> ques) {
        this.ques = ques;
    }

    public String getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(String firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public String getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(String secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public Task(boolean complete, ArrayList<Integer> ques, String firstPlayer, String secondPlayer, String docId) {
        this.complete = complete;
        this.ques = ques;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.docId = docId;
    }
}

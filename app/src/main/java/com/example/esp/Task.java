package com.example.esp;

import java.util.ArrayList;
import java.util.List;

public class Task {

    public Task(boolean complete, List<Integer> ques, String firstPlayer, String secondPlayer, String docId, String firstPlayerResponse, String secondPlayerResponse) {
        this.complete = complete;
        this.ques = ques;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.docId = docId;
        this.firstPlayerResponse = firstPlayerResponse;
        this.secondPlayerResponse = secondPlayerResponse;
    }

    boolean complete;
    List<Integer> ques;
    String firstPlayer;
    String secondPlayer;
    // check doc id???
    String docId;
    String firstPlayerResponse;

    public String getFirstPlayerResponse() {
        return firstPlayerResponse;
    }

    public void setFirstPlayerResponse(String firstPlayerResponse) {
        this.firstPlayerResponse = firstPlayerResponse;
    }

    public String getSecondPlayerResponse() {
        return secondPlayerResponse;
    }

    public void setSecondPlayerResponse(String secondPlayerResponse) {
        this.secondPlayerResponse = secondPlayerResponse;
    }

    String secondPlayerResponse;

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public List<Integer> getQues() {
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


}

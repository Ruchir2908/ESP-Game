package com.example.esp;

import java.util.HashMap;

public class Player {

    String playerID;
    long score;
    HashMap<String,Player> opponents;

    public Player() {

    }

    public Player(String playerID, long score, HashMap<String, Player> opponents) {
        this.playerID = playerID;
        this.score = score;
        this.opponents = opponents;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public HashMap<String, Player> getOpponents() {
        return opponents;
    }

    public void setOpponents(HashMap<String, Player> opponents) {
        this.opponents = opponents;
    }

}

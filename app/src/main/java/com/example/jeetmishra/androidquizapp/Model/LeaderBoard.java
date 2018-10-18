package com.example.jeetmishra.androidquizapp.Model;

public class LeaderBoard {
    private String userName;
    private long score;

    public LeaderBoard() {
    }

    public LeaderBoard(String userName, long score) {
        this.userName = userName;
        this.score = score;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
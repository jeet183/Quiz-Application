package com.example.jeetmishra.androidquizapp.Model;

public class QuestionScore {
    private  String Question_Score;
    private String User;
    private String Score;
    private  String GameModesNo;
    private String GameModeName;

    public QuestionScore() {
    }

    public QuestionScore(String question_Score, String user, String score, String gameModesNo, String gameModeName) {
        Question_Score = question_Score;
        User = user;
        Score = score;
        GameModesNo = gameModesNo;
        GameModeName = gameModeName;
    }

    public String getQuestion_Score() {
        return Question_Score;
    }

    public void setQuestion_Score(String question_Score) {
        Question_Score = question_Score;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getGameModesNo() {
        return GameModesNo;
    }

    public void setGameModesNo(String gameModesNo) {
        GameModesNo = gameModesNo;
    }

    public String getGameModeName() {
        return GameModeName;
    }

    public void setGameModeName(String gameModeName) {
        GameModeName = gameModeName;
    }
}



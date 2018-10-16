package com.example.jeetmishra.androidquizapp.Model;

public class Question {

    String question;
    String  qption1;
    String option2;
    String option3;
    String option4;
    String answer;
    String questionId;
    String gameModesNo;

    public Question() {

    }


    public Question(String question, String qption1, String option2, String option3, String option4, String answer, String questionId, String gameModesNo) {
        this.question = question;
        this.qption1 = qption1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.questionId = questionId;
        this.gameModesNo = gameModesNo;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQption1() {
        return qption1;
    }

    public void setQption1(String qption1) {
        this.qption1 = qption1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getGameModesNo() {
        return gameModesNo;
    }

    public void setGameModesNo(String gameModesNo) {
        this.gameModesNo = gameModesNo;
    }
}


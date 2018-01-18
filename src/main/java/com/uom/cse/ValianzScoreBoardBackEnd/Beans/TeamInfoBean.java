package com.uom.cse.ValianzScoreBoardBackEnd.Beans;

import java.util.List;

public class TeamInfoBean {
    private int teamID;
    private String teamName;
    private float teamScore;
    private List<GameInfoBean> gameScores;

    public TeamInfoBean(int teamID,String teamName, float teamScore){
        this.setTeamID(teamID);
        this.setTeamName(teamName);
        this.setTeamScore(teamScore);
    }

    public TeamInfoBean(){ }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public float getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(float teamScore) {
        this.teamScore = teamScore;
    }

    public List<GameInfoBean> getGameScores() {
        return gameScores;
    }

    public void setGameScores(List<GameInfoBean> gameScores) {
        this.gameScores = gameScores;
    }
}

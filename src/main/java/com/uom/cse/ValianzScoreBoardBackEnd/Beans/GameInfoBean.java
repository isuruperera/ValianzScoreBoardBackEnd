package com.uom.cse.ValianzScoreBoardBackEnd.Beans;

public class GameInfoBean {
    private int gameID;
    private String gameName;
    private float gameWeight;
    private float score;

    public GameInfoBean(int gameID,String gameName, float gameWeight, float score){
        this.gameID = gameID;
        this.setGameName(gameName);
        this.gameWeight = gameWeight;
        this.score = score;
    }

    public GameInfoBean(){}

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public float getGameWeight() {
        return gameWeight;
    }

    public void setGameWeight(float gameWeight) {
        this.gameWeight = gameWeight;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}

package com.uom.cse.ValianzScoreBoardBackEnd.Beans;

import com.uom.cse.ValianzScoreBoardBackEnd.Util.JSONHandler;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ScoreBoardRequestBean {
    private int requestType;
    private int teamID;
    private int gameID;

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public static void main(String[] args) {
        ScoreBoardRequestBean scoreBoardRequestBean = new ScoreBoardRequestBean();
        scoreBoardRequestBean.setRequestType(1);
        try {
            System.out.println(JSONHandler.parseToJSON(scoreBoardRequestBean));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}

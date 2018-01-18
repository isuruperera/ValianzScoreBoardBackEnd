package com.uom.cse.ValianzScoreBoardBackEnd.Beans;

import java.util.List;

public class GameDataResponse extends BaseResponse{
    private List<GameInfoBean> gameInformation;

    public List<GameInfoBean> getGameInformation() {
        return gameInformation;
    }

    public void setGameInformation(List<GameInfoBean> gameInformation) {
        this.gameInformation = gameInformation;
    }
}

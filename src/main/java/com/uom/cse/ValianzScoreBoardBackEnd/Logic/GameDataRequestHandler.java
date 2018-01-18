package com.uom.cse.ValianzScoreBoardBackEnd.Logic;

import com.uom.cse.ValianzScoreBoardBackEnd.Beans.BaseResponse;
import com.uom.cse.ValianzScoreBoardBackEnd.Beans.GameDataResponse;
import com.uom.cse.ValianzScoreBoardBackEnd.Persistance.GameDataDAO;
import com.uom.cse.ValianzScoreBoardBackEnd.Persistance.TeamDataDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameDataRequestHandler {

    @Autowired
    private GameDataDAO gameDataDAO;
    public BaseResponse handle(){
        GameDataResponse response = new GameDataResponse();
        try{
            response.setGameInformation(gameDataDAO.getTeamInformation());
            response.setStatus("SUCCESS");
        }catch (Exception e){
            response.setStatus("FAILED: "+e.getMessage());
        }
        return response;
    }

}

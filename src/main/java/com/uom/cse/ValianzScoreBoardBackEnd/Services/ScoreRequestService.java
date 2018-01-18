package com.uom.cse.ValianzScoreBoardBackEnd.Services;

import com.uom.cse.ValianzScoreBoardBackEnd.Beans.BaseResponse;
import com.uom.cse.ValianzScoreBoardBackEnd.Beans.ScoreBoardRequestBean;
import com.uom.cse.ValianzScoreBoardBackEnd.Logic.GameDataRequestHandler;
import com.uom.cse.ValianzScoreBoardBackEnd.Logic.TeamDataRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreRequestService {

    @Autowired
    private TeamDataRequestHandler teamDataRequestHandler;

    @Autowired
    private GameDataRequestHandler gameDataRequestHandler;

    public BaseResponse requestData(ScoreBoardRequestBean requestBean){
        switch (requestBean.getRequestType()){
            case 1:
                return handleTeamDataRequest();
            case 2:
                return handleTeamDataRequest(requestBean.getTeamID());
            case 3:
                return handleGameDataRequest();

            default:
                BaseResponse defaultResponse = new BaseResponse();
                defaultResponse.setStatus("Invalid request!");
                return defaultResponse;
        }
    }

    private BaseResponse handleTeamDataRequest(){
        return teamDataRequestHandler.handle();
    }


    private BaseResponse handleTeamDataRequest(int teamID){
        return teamDataRequestHandler.handle(teamID);
    }

    private BaseResponse handleGameDataRequest(){
        return gameDataRequestHandler.handle();
    }

}

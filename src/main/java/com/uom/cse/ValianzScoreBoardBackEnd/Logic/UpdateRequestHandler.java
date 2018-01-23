package com.uom.cse.ValianzScoreBoardBackEnd.Logic;

import com.uom.cse.ValianzScoreBoardBackEnd.Beans.BaseResponse;
import com.uom.cse.ValianzScoreBoardBackEnd.Beans.ScoreUpdateRequestBean;
import com.uom.cse.ValianzScoreBoardBackEnd.Persistance.UpdaterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateRequestHandler {
    @Autowired
    private UpdaterDAO updaterDAO;

    @Autowired
    private GameWeight gameWeight;

    public BaseResponse handleNewRecord(ScoreUpdateRequestBean requestBean){
        BaseResponse response = new BaseResponse();
        try{
            updaterDAO.addNewRecord(requestBean.getTeamID(),requestBean.getGameID(),requestBean.getScore());
            gameWeight.calculate(requestBean.getTeamID(),requestBean.getGameID(),requestBean.getScore());
            response.setStatus("SUCCESS");
        }catch (Exception e){
            response.setStatus("FAILED: "+e.getMessage());
        }
        return response;
    }

    public BaseResponse handleUpdateRecord(ScoreUpdateRequestBean requestBean){
        BaseResponse response = new BaseResponse();
        try{
            updaterDAO.updateRecord(requestBean.getTeamID(),requestBean.getGameID(),requestBean.getScore());
            gameWeight.calculate(requestBean.getTeamID(),requestBean.getGameID(),requestBean.getScore());
            response.setStatus("SUCCESS");
        }catch (Exception e){
            response.setStatus("FAILED: "+e.getMessage());
        }
        return response;
    }
}

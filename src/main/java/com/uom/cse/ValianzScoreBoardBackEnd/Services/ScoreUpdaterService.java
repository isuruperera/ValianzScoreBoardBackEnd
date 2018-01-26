package com.uom.cse.ValianzScoreBoardBackEnd.Services;

import com.uom.cse.ValianzScoreBoardBackEnd.Beans.BaseResponse;
import com.uom.cse.ValianzScoreBoardBackEnd.Beans.ScoreUpdateRequestBean;
import com.uom.cse.ValianzScoreBoardBackEnd.Logic.UpdateRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScoreUpdaterService {

    @Autowired
    private UpdateRequestHandler updateRequestHandler;

    public BaseResponse updateData(ScoreUpdateRequestBean requestBean){
        switch (requestBean.getRequestType()){
            case 1:
                return handleNewEntryRequest(requestBean);
            case 2:
                return handleUpdateEntryRequest(requestBean);
            default:
                BaseResponse defaultResponse = new BaseResponse();
                defaultResponse.setStatus("Invalid request!");
                return defaultResponse;
        }
    }

    private BaseResponse handleNewEntryRequest(ScoreUpdateRequestBean requestBean){
        return updateRequestHandler.handleNewRecord(requestBean);
    }

    private BaseResponse handleUpdateEntryRequest(ScoreUpdateRequestBean requestBean){
        return updateRequestHandler.handleUpdateRecord(requestBean);
    }


}

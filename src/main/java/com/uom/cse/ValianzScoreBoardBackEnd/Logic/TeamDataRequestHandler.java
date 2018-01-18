package com.uom.cse.ValianzScoreBoardBackEnd.Logic;

import com.uom.cse.ValianzScoreBoardBackEnd.Beans.BaseResponse;
import com.uom.cse.ValianzScoreBoardBackEnd.Beans.TeamDataResponse;
import com.uom.cse.ValianzScoreBoardBackEnd.Beans.TeamInfoBean;
import com.uom.cse.ValianzScoreBoardBackEnd.Persistance.TeamDataDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TeamDataRequestHandler {

    @Autowired
    private TeamDataDAO teamDataDAO;

    public BaseResponse handle(){
        TeamDataResponse response = new TeamDataResponse();
        try{
            response.setTeamInformation(teamDataDAO.getTeamInformation());
            response.setStatus("SUCCESS");
        }catch (Exception e){
            response.setStatus("FAILED: "+e.getMessage());
        }
        return response;
    }

    public BaseResponse handle(int id){
        TeamDataResponse response = new TeamDataResponse();
        try{
            ArrayList<TeamInfoBean> teamInfoBeans = new ArrayList();
            teamInfoBeans.add(teamDataDAO.getTeamInformation(id));
            response.setTeamInformation(teamInfoBeans);
            response.setStatus("SUCCESS");
        }catch (Exception e){
            response.setStatus("FAILED: "+e.getMessage());
        }
        return response;
    }
}

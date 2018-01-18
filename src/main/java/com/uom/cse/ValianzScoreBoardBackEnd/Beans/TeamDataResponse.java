package com.uom.cse.ValianzScoreBoardBackEnd.Beans;

import java.util.List;

public class TeamDataResponse extends BaseResponse {
    private List<TeamInfoBean> teamInformation;

    public List<TeamInfoBean> getTeamInformation() {
        return teamInformation;
    }

    public void setTeamInformation(List<TeamInfoBean> teamInformation) {
        this.teamInformation = teamInformation;
    }
}

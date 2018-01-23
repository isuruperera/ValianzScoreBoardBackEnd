package com.uom.cse.ValianzScoreBoardBackEnd.Persistance;

import com.uom.cse.ValianzScoreBoardBackEnd.Beans.GameInfoBean;
import com.uom.cse.ValianzScoreBoardBackEnd.Beans.TeamInfoBean;
import com.uom.cse.ValianzScoreBoardBackEnd.Util.DataSourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class TeamDataDAO {
    private static String allTeamInfoQuery = "SELECT * FROM teams";
    private static String allGameInfoQuery = "select game_scores.team_id,games.game_id,games.game_name," +
            "game_scores.score,games.game_weight from games,game_scores where " +
            "games.game_id=game_scores.game_id and game_scores.team_id = ?";
    private static String selectedTeamInfoQuery = "SELECT * FROM teams where team_id=?";

    @Autowired
    private DataSourceManager dataSourceManager;

    public List<TeamInfoBean> getTeamInformation(){
        List<TeamInfoBean> teamInfoBeanList = new ArrayList<>();
        Collection queryData = dataSourceManager.getJdbcTemplate().query(
                allTeamInfoQuery,
                (rs, rowNum) -> new TeamInfoBean(rs.getInt("team_id"),
                        rs.getString("team_name"), rs.getFloat("team_score"),rs.getString("team_color"))
        );
        teamInfoBeanList.addAll(queryData);
        for(TeamInfoBean teamInfoBean:teamInfoBeanList){
            teamInfoBean.setGameScores(extractGameInfo(teamInfoBean.getTeamID()));
        }
        return teamInfoBeanList;
    }

    public TeamInfoBean getTeamInformation(int team_id){
        Collection queryData = dataSourceManager.getJdbcTemplate().query(
                selectedTeamInfoQuery,new Object[] {team_id} ,
                (rs, rowNum) -> new TeamInfoBean(rs.getInt("team_id"),
                        rs.getString("team_name"), rs.getFloat("team_score"),rs.getString("team_color"))
        );
        TeamInfoBean teamInfoBean = (TeamInfoBean) queryData.toArray()[0];
        teamInfoBean.setGameScores(extractGameInfo(team_id));
        return teamInfoBean;
    }

    private List<GameInfoBean> extractGameInfo(int teamID){
        List<GameInfoBean> gameInfoBeanList = new ArrayList<>();
        Collection gameData = dataSourceManager.getJdbcTemplate().query(
                allGameInfoQuery,new Object[] {teamID} ,
                (rs, rowNum) -> new GameInfoBean(rs.getInt("game_id"),
                        rs.getString("game_name"),
                        rs.getFloat("game_weight")
                        ,rs.getFloat("score"))
        );
        gameInfoBeanList.addAll(gameData);
        return gameInfoBeanList;
    }


}

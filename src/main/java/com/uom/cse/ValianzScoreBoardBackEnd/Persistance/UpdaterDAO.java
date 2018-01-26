package com.uom.cse.ValianzScoreBoardBackEnd.Persistance;

import com.uom.cse.ValianzScoreBoardBackEnd.Util.DataSourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;

@Component
public class UpdaterDAO {
    private static String addNewEntryQuery = "insert into game_scores (game_id, team_id, score) VALUES (?,?,?)";
    private static String updateEntryQuery = "update game_scores set score = ? WHERE game_id = ? and team_id = ?";
    private static String updateTotalScoreQuery = "update teams set team_score = ? WHERE team_id = ?";

    @Autowired
    private DataSourceManager dataSourceManager;

    @Transactional
    public void addNewRecord(int teamID,int gameID,float score){
        dataSourceManager.getJdbcTemplate().update(addNewEntryQuery,new Object[] {gameID,teamID,score}, new int[]{Types.INTEGER,Types.INTEGER,Types.FLOAT});
    }

    @Transactional
    public void updateRecord(int teamID,int gameID,float score){
        dataSourceManager.getJdbcTemplate().update(updateEntryQuery,new Object[] {score,gameID,teamID}, new int[]{Types.FLOAT,Types.INTEGER,Types.INTEGER});
    }

    @Transactional
    public void updateTotalTeamScore(int teamID, float score) {
        dataSourceManager.getJdbcTemplate().update(updateTotalScoreQuery, new Object[] {score, teamID}, new int[]{Types.FLOAT, Types.INTEGER});
    }
}

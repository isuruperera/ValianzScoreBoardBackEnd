package com.uom.cse.ValianzScoreBoardBackEnd.Logic;

import com.uom.cse.ValianzScoreBoardBackEnd.Beans.GameInfoBean;
import com.uom.cse.ValianzScoreBoardBackEnd.Beans.TeamInfoBean;
import com.uom.cse.ValianzScoreBoardBackEnd.Persistance.TeamDataDAO;
import com.uom.cse.ValianzScoreBoardBackEnd.Persistance.UpdaterDAO;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GameWeight {

    // Configuration
    private final float in_min = -4f;
    private final float in_max = 4f;
    private final float out_min = 0f;
    private final float out_max = 200f;

    public void calculate(int teamID, int gameID, float score) {
//        new UpdaterDAO().addNewRecord(teamID, gameID, score);
        updateAllTeamScores();
    }

    private void updateAllTeamScores() {
        List<TeamInfoBean> teamInfos = new TeamDataDAO().getTeamInformation();
        HashMap<Integer, HashMap<Integer, Float>> gameScores = new HashMap<>(); // game id -> team id -> game score;
        HashMap<Integer, Float> totalTeamScores = new HashMap<>(); // team id -> total score

        for (TeamInfoBean teamInfo : teamInfos) {
            for (GameInfoBean gameInfo : teamInfo.getGameScores()) {
                if (!gameScores.containsKey(gameInfo.getGameID())) {
                    gameScores.put(gameInfo.getGameID(), new HashMap<>());
                }
                gameScores.get(gameInfo.getGameID()).put(teamInfo.getTeamID(), gameInfo.getScore());
            }
        }

        for (Integer gameID : gameScores.keySet()) {
            ScoreStatistic scoreStatistic = calculateMeanAndStDev(gameScores.get(gameID).values());

            // Calculating new game scores by z scores
            for (Map.Entry<Integer, Float> scoreEntry : gameScores.get(gameID).entrySet()) {
                int teamID = scoreEntry.getKey();
                float gameScore = scoreEntry.getValue();
                float z = (gameScore - scoreStatistic.mean) / scoreStatistic.stDev;
                float mappedScore = mapRange(z);
                if (!totalTeamScores.containsKey(teamID)) {
                    totalTeamScores.put(teamID, 0f);
                }
                totalTeamScores.put(teamID, totalTeamScores.get(teamID) + mappedScore);
            }

            // Updating all team scores
            for (Map.Entry<Integer, Float> scoreEntry : totalTeamScores.entrySet()) {
                updateTotalTeamScore(scoreEntry.getKey(), scoreEntry.getValue());
            }
        }
    }

    private void updateTotalTeamScore(int teamID, float score) {
        new UpdaterDAO().updateTotalTeamScore(teamID, score);
    }

    private ScoreStatistic calculateMeanAndStDev(Collection<Float> scores) {
        float mean = 0f;
        for (Float score : scores) {
            mean += score / scores.size();
        }
        float variance = 0f;
        for (Float score : scores) {
            variance += (score - mean) * (score - mean) / scores.size();
        }
        return new ScoreStatistic(mean, (float) Math.sqrt(variance));
    }

    private float mapRange(float z) {
        return (z - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    private class ScoreStatistic {
        float mean;
        float stDev;

        ScoreStatistic(float mean, float stDev) {
            this.mean = mean;
            this.stDev = stDev;
        }
    }
}

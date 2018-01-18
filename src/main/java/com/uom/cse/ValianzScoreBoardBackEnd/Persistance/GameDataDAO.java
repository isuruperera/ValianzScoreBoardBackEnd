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
public class GameDataDAO {
    private static String allGameInfoQuery = "SELECT * FROM games";
    @Autowired
    private DataSourceManager dataSourceManager;

    public List<GameInfoBean> getTeamInformation(){
        List<GameInfoBean> gameInfoBeanList = new ArrayList<>();
        Collection queryData = dataSourceManager.getJdbcTemplate().query(
                allGameInfoQuery,
                (rs, rowNum) -> new GameInfoBean(rs.getInt("game_id"),
                        rs.getString("game_name")
                        , rs.getFloat("game_weight"),0.0f)
        );
        gameInfoBeanList.addAll(queryData);
        return gameInfoBeanList;
    }
}

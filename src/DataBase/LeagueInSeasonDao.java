package DataBase;

import Domain.League.LeagueInSeason;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LeagueInSeasonDao  implements DAOTEMP<LeagueInSeason> {
    private static final LeagueInSeasonDao instance = new LeagueInSeasonDao();

    //private constructor to avoid client applications to use constructor
    public static LeagueInSeasonDao getInstance(){
        return instance;
    }

    @Override
    public String getTableName() {
        return null;
    }

    private LeagueInSeasonDao() {

    }
    DBConnector dbc = DBConnector.getInstance();






    @Override
    public LeagueInSeason get(String id) {
        return null;
    }

    @Override
    public List<LeagueInSeason> getAll() {
        return null;
    }

    @Override
    public void save(LeagueInSeason leagueInSeason) throws SQLException {

    }

    @Override
    public void update(LeagueInSeason leagueInSeason, String[] params) {

    }

    @Override
    public void delete(LeagueInSeason leagueInSeason) {

    }

    @Override
    public boolean exist(LeagueInSeason leagueInSeason) {

        return false;
    }
}

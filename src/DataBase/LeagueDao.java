package DataBase;

import Domain.League.League;

import java.sql.SQLException;
import java.util.List;

public class LeagueDao  implements DAOTEMP<League> {
    private static final LeagueDao instance = new LeagueDao();

    //private constructor to avoid client applications to use constructor
    public static LeagueDao getInstance(){
        return instance;
    }

    @Override
    public String getTableName() {
        return null;
    }

    private LeagueDao() {

    }
    DBConnector dbc = DBConnector.getInstance();




    @Override
    public League get(String id) {
        return null;
    }

    @Override
    public List<League> getAll() {
        return null;
    }

    @Override
    public void save(League league) throws SQLException {

    }

    @Override
    public void update(League league, String[] params) {

    }

    @Override
    public void delete(League league) {

    }

    @Override
    public boolean exist(League league) {

        return false;
    }
}

package DataBase;

import Domain.Game.Team;
import Domain.League.Season;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SeasonDao  implements DAOTEMP<Season> {

    private static final SeasonDao instance = new SeasonDao();

    //private constructor to avoid client applications to use constructor
    public static SeasonDao getInstance(){
        return instance;
    }

    @Override
    public String getTableName() {
        return null;
    }

    private SeasonDao() {

    }
    DBConnector dbc = DBConnector.getInstance();





    @Override
    public Season get(String id) {
        return null;
    }

    @Override
    public List<Season> getAll() {
        return null;
    }

    @Override
    public void save(Season season) throws SQLException {

    }

    @Override
    public void update(Season season, String[] params) {

    }

    @Override
    public void delete(Season season) {

    }

    @Override
    public boolean exist(Season season) {
        return false;
    }
}

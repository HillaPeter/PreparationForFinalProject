package DataBase;

import Domain.Game.Game;

import java.sql.SQLException;
import java.util.List;

public class GameDao implements DAOTEMP<Game> {
    private static final GameDao instance = new GameDao();

    //private constructor to avoid client applications to use constructor
    public static GameDao getInstance(){
        return instance;
    }

    @Override
    public String getTableName() {
        return null;
    }

    private GameDao() {

    }
    DBConnector dbc = DBConnector.getInstance();




    @Override
    public Game get(String id) {
        return null;
    }

    @Override
    public List<Game> getAll() {
        return null;
    }

    @Override
    public void save(Game game) throws SQLException {

    }

    @Override
    public void update(Game game, String[] params) {

    }

    @Override
    public void delete(Game game) {

    }

    @Override
    public boolean exist(Game game) {

        return false;
    }
}

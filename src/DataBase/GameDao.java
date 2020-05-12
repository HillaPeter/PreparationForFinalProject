package DataBase;

import Domain.Game.Game;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GameDao implements DAOTEMP<Game> {
    private static final GameDao instance = new GameDao();
    DBConnector dbc = DBConnector.getInstance();

    //private constructor to avoid client applications to use constructor
    public static GameDao getInstance(){
        return instance;
    }

    @Override
    public String getTableName() {
     return "Game";
    }

    private GameDao() {

    }


    @Override
    public Game get(String id) {
        try {
            Connection connection = DBConnector.getConnection();
            Statement stmt = connection.createStatement();

            String sql = "SELECT FROM "+getTableName()+
                    "VALUES ('" + id+ "');";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }

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

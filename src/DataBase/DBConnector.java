package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public static final String URL = "jdbc:mysql://132.72.65.124:3306/football";
    public static final String USER = "root";
    public static final String PASS = "root";

    private static final DBConnector instance = new DBConnector();

    //private constructor to avoid client applications to use constructor
    public static DBConnector getInstance(){
        return instance;
    }

    private DBConnector() {

    }
    /**
     * Get a connection to database
     *
     * @return Connection object
     */
    public static Connection getConnection() {
        try {
          //  DriverManager.registerDriver(new Driver());
           // return  DriverManager.getConnection(URL, USER, PASS);
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://132.72.65.124:3306/football" +
                            "user=root&password=root&useLegacyDatetimeCode=false&serverTimezone=UTC");
            return conn;
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }


    /**
         * Test Connection
         */



}

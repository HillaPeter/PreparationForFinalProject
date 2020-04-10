package system;

import League.*;

import java.util.HashMap;

public class DBController {

    private DB db;

    public DBController() {
        this.db = new DB();
    }

    public void addLeague(League league){
        db.addLeague(league);
    }

    public HashMap<String, League> getLeagues(){
        return db.getLeagues();
    }

    public void removeLeague(String leagueName) {
        db.removeLeague(leagueName);
    }

    public void removeSeason(String year) {
        db.removeSeason(year);
    }

    public void addSeason(Season season) {
        db.addSeason(season);
    }
}

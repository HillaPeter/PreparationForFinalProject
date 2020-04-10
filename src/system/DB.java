package system;

import Game.Team;
import League.*;
import Users.Role;
import Users.SystemManager;

import java.util.HashMap;

public class DB {

    private HashMap<String, League> leagues; //key-name of league, value-league
    private HashMap<String, Season> seasons; //key-year, value-season
    private HashMap<String, SystemManager> systemManagers;
    private HashMap<String, Role> roles; // hash map <mail,role> - maybe users instead roles??
    private HashMap<String, Team> teams;
    //  private HashMap<Member,String> passwordValidation;


    public DB() {
        this.leagues = new HashMap<>();
        this.seasons = new HashMap<>();
        this.systemManagers = new HashMap<>();
        this.roles = new HashMap<>();
        this.teams = new HashMap<>();
    }


    public HashMap<String, League> getLeagues() {
        return leagues;
    }

    public void removeLeague(String leagueName) {
        leagues.remove(leagueName);
    }
    public void addLeague(League league) {
        leagues.put(league.getName(), league);
    }

    public void removeSeason(String year) {
        seasons.remove(year);
    }

    public void addSeason(Season season) {
        seasons.put(season.getYear(), season);
    }
}

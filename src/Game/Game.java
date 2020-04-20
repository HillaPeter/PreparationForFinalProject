package Game;

import Asset.Field;
import League.LeagueInSeason;
import Users.Referee;

import java.util.Date;
import java.util.HashSet;

public class Game {
    private String date; //year-month-day, example: 2000-01-01
    private Team hostTeam;
    private Team visitorTeam;
    private String time; //hours:minutes
    private Field field;
    private String result;
    private EventLog eventLog;
    private LeagueInSeason leagueInSeason;
    private HashSet<Referee> referees;

    public Game(String date,Team hostTeam, Team visitorTeam, String time, Field field, LeagueInSeason leagueInSeason) {
        this.date = date;
        this.hostTeam = hostTeam;
        this.visitorTeam = visitorTeam;
        this.time = time;
        this.field = field;
        this.result = "";
        this.eventLog = new EventLog(this);
        this.leagueInSeason=leagueInSeason;
    }

    public void removeReferee(Referee referee) {
        referees.remove(referee);
        //put new referee after the delete
    }

    public boolean isRefereeInTheGame(Referee referee){
        return referees.contains(referee);
    }

    public void addEvent (Event event){
        this.eventLog.addEvent(event);
    }
}

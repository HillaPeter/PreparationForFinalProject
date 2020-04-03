package Game;

import Asset.Field;
import League.LeagueInSeason;

import java.util.Date;

public class Game {
    private Date date;
    private Team hostTeam;
    private Team visitorTeam;
    private Date time;
    private Field field;
    private String result;
    private EventLog eventLog;
    private LeagueInSeason leagueInSeason;

    public Game(Date date,Team hostTeam, Team visitorTeam, Date time, Field field, String result,EventLog eventLog, LeagueInSeason leagueInSeason) {
        this.date = date;
        this.hostTeam = hostTeam;
        this.visitorTeam = visitorTeam;
        this.time = time;
        this.field = field;
        this.result = result;
        this.eventLog = eventLog;
        this.leagueInSeason=leagueInSeason;
    }
}

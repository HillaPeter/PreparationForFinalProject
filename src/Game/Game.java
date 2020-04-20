package Game;

import Asset.Field;
import League.LeagueInSeason;
import Users.Referee;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;

public class Game {
    private Calendar dateAndTime; //
    private Team hostTeam;
    private Team visitorTeam;
    private Field field;
    private String result;
    private EventLog eventLog;
    private LeagueInSeason leagueInSeason;
    private HashSet<Referee> referees;

    public Game(Calendar dateAndTime,Team hostTeam, Team visitorTeam, Field field, LeagueInSeason leagueInSeason) {
        this.dateAndTime = dateAndTime;
        this.hostTeam = hostTeam;
        this.visitorTeam = visitorTeam;
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

    public String getDateString() {
        String ans = "" + dateAndTime.get(Calendar.YEAR) + "-" +  dateAndTime.get(Calendar.MONTH) + "-" + dateAndTime.get(Calendar.DAY_OF_MONTH);
        return ans;
    }
    public Calendar getDateCalendar() {
        return dateAndTime;
    }

    public Team getHostTeam() {
        return hostTeam;
    }

    public Team getVisitorTeam() {
        return visitorTeam;
    }


    public String getDateAndTimeString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
        String ans = sdf.format(dateAndTime.getTime());
        return ans;
    }

    public Field getField() {
        return field;
    }
}

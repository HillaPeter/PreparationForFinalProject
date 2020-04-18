package League;
import Game.Game;
import Users.Referee;
import Game.Team;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class LeagueInSeason {
    private HashSet<Game> games;
    private League league;
    private Season season;
    private SchedulingPolicy schedulingPolicies;
    private ScorePolicy scorePolicies;
    private HashMap<String, Referee> referees;
    private LinkedList<Team> teams;

    public LeagueInSeason(League league,Season season) {
        this.league = league;
        this.season = season;
//        this.schedulingPolicies = schedulingPolicies;
//        this.scorePolicies = scorePolicies;
//        , SchedulingPolicy schedulingPolicies,ScorePolicy scorePolicies
        games=new HashSet<>();
        referees=new HashMap<>();
        teams=new LinkedList<Team>();
    }

    /*
    public void setReferee(Referee newReferee){
        if(newReferee != null){
            referee = newReferee;
        }
    }

     */

    public LinkedList<Team> getTeams()
    {
        return teams;
    }
    public Season getSeason(){
        return season;
    }
    public League getLeague(){
        return league;
    }

    public HashMap<String, Referee> getReferees() {
        return referees;
    }

    public void addReferee(String refereeName, Referee referee) {
        if(!referees.containsKey(refereeName)){
            referees.put(refereeName, referee);
        }
    }
}

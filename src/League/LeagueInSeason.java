package League;
import Game.Game;
import Users.Referee;

import java.util.HashSet;

public class LeagueInSeason {
    private HashSet<Game> games;
    private League league;
    private Season season;
    private SchedulingPolicy schedulingPolicies;
    private ScorePolicy scorePolicies;
    //private Referee referee; UC21 ?

    public LeagueInSeason(League league,Season season) {
        this.league = league;
        this.season = season;
//        this.schedulingPolicies = schedulingPolicies;
//        this.scorePolicies = scorePolicies;
//        , SchedulingPolicy schedulingPolicies,ScorePolicy scorePolicies
        games=new HashSet<>();
        //referee=null;
    }

    /*
    public void setReferee(Referee newReferee){
        if(newReferee != null){
            referee = newReferee;
        }
    }

     */

    public Season getSeason(){
        return season;
    }
    public League getLeague(){
        return league;
    }
}

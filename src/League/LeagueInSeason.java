package League;
import Asset.Coach;
import Asset.Manager;
import Asset.Player;
import Game.Game;
import Users.MainReferee;
import Users.Referee;
import Game.Team;
import Users.SecondaryReferee;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class LeagueInSeason {
    private HashSet<Game> games;
    private League league;
    private Season season;
    private ASchedulingPolicy schedulingPolicy;
    private IScorePolicy scorePolicy;
    private HashMap<String, Referee> referees;
    private LinkedList<Team> teams;

    public LeagueInSeason(League league,Season season) {
        this.league = league;
        this.season = season;
//        this.schedulingPolicies = schedulingPolicies;
//        this.scorePolicies = scorePolicies;
//        , ASchedulingPolicy schedulingPolicies,IScorePolicy scorePolicies
        games=new HashSet<>();
        referees=new HashMap<>();
        teams=new LinkedList<Team>();
    }

    public ASchedulingPolicy getPolicy()
    {
        return schedulingPolicy;
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

    public void setScorePolicy(IScorePolicy policy) {
        scorePolicy = policy;
    }

    public void setSchedulingPolicy(ASchedulingPolicy policy) {
        schedulingPolicy = policy;
    }

    public LinkedList<Team> getTeamsForSceduling() {
        LinkedList<Team> teamToReturn=new LinkedList<>();
        for (int i=0; i<teams.size(); i++)
        {
            if(isFullTeam(teams.get(i)))
            {
                teamToReturn.add(teams.get(i));
            }
        }
        return teamToReturn;
    }

    private boolean isFullTeam(Team team) {
        HashSet<Player> players=team.getPlayers();
        HashSet<Coach> coaches=team.getCoaches();
        HashSet<Manager> managers=team.getManagers();
        if(players.size()<11)
        {
            return false;
        }
        if(coaches.size()<1)
        {
            return false;
        }
        if(managers.size()<1)
        {
            return false;
        }

        return true;
    }

    public void addGames(Set<Game> games) {
        for (Game game:games
             ) {
            this.games.add(game);
        }
    }
    public HashMap<String, Referee> getMainReferee() {
        HashMap<String , Referee> toReturn=new HashMap<>();
        for (String role:referees.keySet()
        ) {
            if(referees.get(role) instanceof MainReferee)
                toReturn.put(((MainReferee) referees.get(role)).getUserMail() , (MainReferee)referees.get(role));
        }
        return toReturn;
    }

    public HashMap<String, Referee> getSecondaryReferee() {
        HashMap<String , Referee> toReturn=new HashMap<>();
        for (String role:referees.keySet()
        ) {
            if(referees.get(role) instanceof SecondaryReferee)
                toReturn.put(((SecondaryReferee) referees.get(role)).getUserMail() , (SecondaryReferee)referees.get(role));
        }
        return toReturn;
    }
}

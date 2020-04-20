package Users;

import Asset.Field;
import Game.Team;
import League.*;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class SchedulingPolicyTest {

    @Test
    public void setGamesOfTeamsTest (){
        ASchedulingPolicy policy = new SchedulingPolicyAllTeamsPlayTwice();
        List<Team> teams = generateTeams(6);
        Season season = new Season("2000");
        League league = new League("league  A");
        LeagueInSeason leagueInSeason = new LeagueInSeason(league, season);
        policy.setGamesOfTeams(teams, leagueInSeason);
    }



    public List<Team> generateTeams(int n){
        List<Team> teams = new LinkedList<>();
        for(int i = 1; i <=n ; i++){
            teams.add(generateTeam("team "+i,"field "+i));
        }
        return teams;
    }


    public Team generateTeam(String name, String fieldName) {
        Field field = new Field(fieldName);
        return new Team(name,null,field);
    }
}

package Users;

import Asset.Field;
import Game.Team;
import League.SchedulingPolicyAllTeamsPlayTwice;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class SchedulingPolicyTest {

    @Test
    public void setGamesOfTeamsTest (){
        SchedulingPolicyAllTeamsPlayTwice policy = new SchedulingPolicyAllTeamsPlayTwice();
        List<Team> teams = generateTeams(6);

        policy.setGamesOfTeams(teams,null);



    }

    public List<Team> generateTeams(int n){
        List<Team> teams = new LinkedList<>();
        for(int i = 1; i <=n ; i++){
            teams.add(genreateTeam("team "+i,"field "+i));
        }
        return teams;
    }

    public Team genreateTeam(String name, String fieldName) {
        Field field = new Field(fieldName);
        return new Team(name,null,field);
    }
}

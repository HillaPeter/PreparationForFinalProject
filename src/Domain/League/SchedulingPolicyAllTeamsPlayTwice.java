package Domain.League;

import Domain.Game.Game;
import Domain.Game.Team;

import java.util.*;

public class SchedulingPolicyAllTeamsPlayTwice extends ASchedulingPolicy {


    private String name;

    public SchedulingPolicyAllTeamsPlayTwice(){
        name = "All teams play each other twice";
    }

    public Set<Game> setGamesOfTeams(List<Team> teams, LeagueInSeason leagueInSeason) {
        return super.setGamesOfTeams(teams, leagueInSeason, true);
    }


    public String getNameOfPolicy(){
        return name;
    }
}

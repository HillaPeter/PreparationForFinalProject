package League;

import Game.Game;
import Game.Team;

import java.util.List;
import java.util.Set;

public interface ISchedulingPolicy {

    public Set<Game> setGamesOfTeams(List<Team> teams, LeagueInSeason leagueInSeason);
    public String getNameOfPolicy();
}

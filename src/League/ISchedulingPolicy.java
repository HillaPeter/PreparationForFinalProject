package League;

import Game.Game;
import Game.Team;

import java.util.HashMap;
import java.util.HashSet;

public interface ISchedulingPolicy {

    public HashSet<Game> setGamesOfTeams(HashMap<String, Team> teams, LeagueInSeason leagueInSeason);
    public String getNameOfPolicy();
}

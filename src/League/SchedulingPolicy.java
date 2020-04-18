package League;

import Game.Game;
import Game.Team;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class SchedulingPolicy implements ISchedulingPolicy {


    private String name;

    public SchedulingPolicy(){
        name = "All teams play each other twice";
    }
    @Override
    public HashSet<Game> setGamesOfTeams(HashMap<String, Team> teams, LeagueInSeason leagueInSeason) {
       HashSet<Game> games = new HashSet<>();
       for(String team1 : teams.keySet()){
           for(String team2 : teams.keySet()){
               if(!team1.equals(team2)) {
                   Date date1 = new Date();
                   Date date2 = new Date();
                   Date time1 = new Date();
                   Date time2 = new Date();
                   Game game1 = new Game(date1, teams.get(team1), teams.get(team2), time1, teams.get(team1).getHomeField(), leagueInSeason);
                   Game game2 = new Game(date2, teams.get(team2), teams.get(team1), time2, teams.get(team2).getHomeField(), leagueInSeason);
                   games.add(game1);
                   games.add(game2);
               }
           }
       }
       return games;
    }

    public String getNameOfPolicy(){
        return name;
    }
}

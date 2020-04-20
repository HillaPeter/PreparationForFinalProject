package League;

import Game.Game;
import Game.Team;

import java.util.List;
import java.util.Set;


public class SchedulingPolicyAllTeamsPlayOnce implements ISchedulingPolicy {


    private String name;

    public SchedulingPolicyAllTeamsPlayOnce(){
        name = "All teams play each other once";
    }

    @Override
    public Set<Game> setGamesOfTeams(List <Team> teams, LeagueInSeason leagueInSeason) {
//        Set <Game> games = new HashSet<Game>();
//        for(String team1 : teams.keySet()) {
//            for (String team2 : teams.keySet()) {
//                if (!team1.equals(team2)) {
//                    Date date = new Date();
//                    Date time = new Date();
//                    Random rd = new Random();
//                    Field fieldHost;
//                    if(rd.nextDouble() > 0.5){ //choose randomly field
//                        fieldHost =  teams.get(team1).getHomeField();
//                    }
//                    else{
//                        fieldHost = teams.get(team2).getHomeField();
//                    }
//                    Game game = new Game(date, teams.get(team1), teams.get(team2), time, fieldHost, leagueInSeason);
//                    games.add(game);
//                }
//            }
//        }

        return null;
    }

    @Override
    public String getNameOfPolicy() {
        return name;
    }
}

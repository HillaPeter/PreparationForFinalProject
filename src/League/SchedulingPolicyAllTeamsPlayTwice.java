package League;

import Game.Game;
import Game.Team;

import java.util.*;

public class SchedulingPolicyAllTeamsPlayTwice implements ISchedulingPolicy {


    private String name;

    public SchedulingPolicyAllTeamsPlayTwice(){
        name = "All teams play each other twice";
    }

    @Override
    public Set<Game> setGamesOfTeams(List<Team> teams, LeagueInSeason leagueInSeason) {

        Set<Game> games = new HashSet<>();
        FixtureGenerator<Team> fixtureGenerator = new FixtureGenerator();
        List<Team> teamsList = new LinkedList<Team>(teams);
        List<List<Fixture<Team>>> rounds = fixtureGenerator.getFixtures(teamsList, true);
        for(int i=0; i<rounds.size(); i++){
            System.out.println("Round " + (i+1));
            List<Fixture<Team>> round = rounds.get(i);
            for(Fixture<Team> fixture: round){
                System.out.println(fixture.getHomeTeam().getName() + " vs " + fixture.getAwayTeam().getName());
            }
            System.out.println("");
        }
        return null;
    }



    //@Override
//    public HashSet<Game> setGamesOfTeams(HashMap<String, Team> teams, LeagueInSeason leagueInSeason) {
//       HashSet<Game> games = new HashSet<>();
//       int numberOfFixtures = (teams.size()-1)*2;
//       String year = leagueInSeason.getSeason().getYear();
//       String date = ""+ year + "-01" + "-01";
//       for(int k=0; k<numberOfFixtures; k++){
//            date = getNewDate(date, k);
//
//            for(String team1 : teams.keySet()){
//               for(String team2 : teams.keySet()){
//                   if(!team1.equals(team2)) {
//                       Date date1 = new Date();
//                       Date date2 = new Date();
//                       Date time1 = new Date();
//                       Date time2 = new Date();
//
//                       Game game1 = new Game(date1, teams.get(team1), teams.get(team2), time1, teams.get(team1).getHomeField(), leagueInSeason);
//                       Game game2 = new Game(date2, teams.get(team2), teams.get(team1), time2, teams.get(team2).getHomeField(), leagueInSeason);
//                       games.add(game1);
//                       games.add(game2);
//                   }
//               }
//           }
//       }

//       return games;
//    }

//    private String getNewDate(String date, int k) {
//        int daysInAWeek = 7;
//        int[] months = {31, 28, 30};
//
//        int daysInMonth =
//        String ans = date.substring(0,4);
//        int month = Integer.parseInt(date.substring(5,7));
//        int day = (Integer.parseInt(date.substring(8)) + k*daysInAWeek) % 30 ;
//
//    }

    public String getNameOfPolicy(){
        return name;
    }
}

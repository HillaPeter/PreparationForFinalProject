//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Domain.League;

import Domain.Game.Game;
import Domain.Game.Team;
import Domain.Users.Referee;

import java.util.*;

public abstract class ASchedulingPolicy {
    public ASchedulingPolicy() {
    }

    protected Set<Game> setGamesOfTeams(List<Team> teams, LeagueInSeason leagueInSeason, boolean isTwice) {
        int year = Integer.parseInt(leagueInSeason.getSeason().getYear());
        Set<Game> games = new HashSet();
        FixtureGenerator fixtureGenerator = new FixtureGenerator();
        List<List<Fixture>> rounds = fixtureGenerator.getFixtures(teams, isTwice);
        Calendar dateAndTime = new GregorianCalendar(year, 0, 1, 20, 30, 0);
        int counterReferees = 0;
        for(int i = 0; i < rounds.size(); ++i) {
            List<Fixture> round = rounds.get(i);
            List<Referee> mainReferees = leagueInSeason.getMainReferee();
            List<Referee> secondaryReferees =leagueInSeason.getSecondaryReferee();
            for(Fixture fixture: round) {
                Game game = new Game(dateAndTime.toString()+""+fixture.getHomeTeam().toString() , dateAndTime, fixture.getHomeTeam(), fixture.getAwayTeam(), fixture.getHomeTeam().getHomeField(), mainReferees.get(counterReferees),secondaryReferees.get(counterReferees), leagueInSeason);
                mainReferees.remove(counterReferees);
                secondaryReferees.remove(counterReferees);
                Team team1=fixture.getHomeTeam();
                Team team2=fixture.getAwayTeam();
                team1.addGame(game);
                team2.addGame(game);
                //System.out.println("Date1- " + game.getDateAndTimeString());
                games.add(game);
            }
            dateAndTime = new GregorianCalendar(year, 0, 1, 20, 30, 0);
            dateAndTime.add(Calendar.DATE, (i+1)*7);
        }

//        for(Game gameTest : games) {
//            System.out.println("Team1: " + gameTest.getHostTeam().getName() + " Team2: " + gameTest.getVisitorTeam().getName() + " Date: " + gameTest.getDateAndTimeString() + " Field: " + gameTest.getField().getNameOfField());
//        }
        return games;
    }

    public abstract String getNameOfPolicy();

    public abstract Set<Game> setGamesOfTeams(List<Team> var1, LeagueInSeason var2);
}

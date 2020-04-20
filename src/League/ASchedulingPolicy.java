//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package League;

import Game.Game;
import Game.Team;

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
        Calendar time = new GregorianCalendar();
        time.set(10, 3);
        for(int i = 0; i < rounds.size(); ++i) {
            List<Fixture> round = (List)rounds.get(i);
            for(Fixture fixture: round) {
                Game game = new Game(dateAndTime, fixture.getHomeTeam(), fixture.getAwayTeam(), fixture.getHomeTeam().getHomeField(), leagueInSeason);
                games.add(game);
            }

            dateAndTime.add(Calendar.DAY_OF_MONTH, 7);
        }

        for(Game gameTest : games) {
            System.out.println("Team1: " + gameTest.getHostTeam().getName() + " Team2: " + gameTest.getVisitorTeam().getName() + " Date: " + gameTest.getDateAndTimeString() + " Field: " + gameTest.getField().getNameOfField());
        }
        return games;
    }

    public abstract String getNameOfPolicy();

    public abstract Set<Game> setGamesOfTeams(List<Team> var1, LeagueInSeason var2);
}

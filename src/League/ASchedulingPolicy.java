//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package League;

import Game.Game;
import Game.Team;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
            Iterator var12 = round.iterator();

            while(var12.hasNext()) {
                Fixture fixture = (Fixture)var12.next();
                Game game = new Game(dateAndTime, fixture.getHomeTeam(), fixture.getAwayTeam(), fixture.getHomeTeam().getHomeField(), leagueInSeason);
                games.add(game);
            }

            dateAndTime.set(5, 7);
        }

        Iterator var15 = games.iterator();

        while(var15.hasNext()) {
            Game gameTest = (Game)var15.next();
            System.out.println("Team1: " + gameTest.getHostTeam().getName() + " Team2: " + gameTest.getVisitorTeam().getName() + " Date: " + gameTest.getDateAndTimeString() + " Field: " + gameTest.getField().getNameOfField());
        }

        return games;
    }

    public abstract String getNameOfPolicy();

    public abstract Set<Game> setGamesOfTeams(List<Team> var1, LeagueInSeason var2);
}

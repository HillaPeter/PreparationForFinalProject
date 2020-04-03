package League;
import Game.Game;

import java.util.HashSet;

public class LeagueInSeason {
    private HashSet<Game> games;
    private League league;
    private Season season;
    private SchedulingPolicy schedulingPolicies;
    private ScorePolicy scorePolicies;

    public LeagueInSeason(League league,Season season, SchedulingPolicy schedulingPolicies,ScorePolicy scorePolicies) {
        this.league = league;
        this.season = season;
        this.schedulingPolicies = schedulingPolicies;
        this.scorePolicies = scorePolicies;
        games=new HashSet<>();
    }
}

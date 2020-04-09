package League;

import java.util.HashMap;

public class League {
    private String name;
    private HashMap<Season,LeagueInSeason> leagueInSeasons;

    public League(String name) {
        this.name = name;
        leagueInSeasons=new HashMap<>();
    }

    public LeagueInSeason getLeagueInSeason(Season season)
    {
        return leagueInSeasons.get(season);
    }

    public String getName() {
        return this.name;
    }
}

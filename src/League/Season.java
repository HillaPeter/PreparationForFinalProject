package League;

import java.util.HashMap;

public class Season {
    private String name;
    private HashMap<League,LeagueInSeason> leagueInSeasons;

    public Season(String name) {
        this.name = name;
        this.leagueInSeasons = new HashMap<>();
    }
}

package League;

import java.util.HashMap;
import Exception.*;

public class League {
    private String name;
    private HashMap<Season,LeagueInSeason> leagueInSeasons;

    public League(String name) {
        this.name = name;
        leagueInSeasons=new HashMap<>();
    }

    public LeagueInSeason getLeagueInSeason(Season season) throws ObjectNotExist {
        if(leagueInSeasons.containsKey(season))
             return leagueInSeasons.get(season);
        else
            throw new ObjectNotExist(season.getYear());
    }

    public String getName() {
        return this.name;
    }

    public void addLeagueInSeason(LeagueInSeason leagueInSeason){
        if(leagueInSeason != null && !leagueInSeasons.containsKey(leagueInSeason.getSeason())){
            leagueInSeasons.put(leagueInSeason.getSeason(), leagueInSeason);
        }
    }
}

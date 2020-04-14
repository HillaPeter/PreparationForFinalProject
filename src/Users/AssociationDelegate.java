package Users;

import League.*;
import system.DBController;
import Exception.*;


import java.util.HashMap;
import java.util.regex.Pattern;

public class AssociationDelegate extends Member {

    private DBController dbController;

    public AssociationDelegate(String name, String userMail, String password) {
        super(name, userMail, password);
        dbController = new DBController();
    }

    public void setLeague(String leagueName) throws AlreadyExistException, IncorrectInputException, DontHavePermissionException {
        HashMap<String, League> leagues = dbController.getLeagues(this);
        League league;
        if (Pattern.matches("[a-zA-Z]+", leagueName)) { //checks that the name contains only letters(true=just letters)
            if(!leagues.containsKey(leagueName)){
                league = new League(leagueName);
                dbController.addLeague(league);
            }
            else{
                throw new AlreadyExistException();
            }

        }
        else {
            throw new IncorrectInputException(leagueName);
        }
    //todo
}

    public void setLeagueByYear(String specificLeague, String year) throws ObjectNotExist, AlreadyExistException, DontHavePermissionException {
        HashMap<String, League> leagues = dbController.getLeagues(this);
        League league = leagues.get(specificLeague);
        if(league == null){
            throw new ObjectNotExist("There is no league called "+ specificLeague);
        }

        Season season = new Season(year);
        if(league.getLeagueInSeason(season) != null){ //check if the season is already exist in this league
            throw new AlreadyExistException();
        }
        LeagueInSeason leagueInSeason = new LeagueInSeason(league, season);
        //do the scheduling policy
        //do the score policy
        league.addLeagueInSeason(leagueInSeason);
        season.addLeagueInSeason(leagueInSeason);
        dbController.removeLeague(league.getName());
        dbController.addLeague(league);
        dbController.removeSeason(season.getYear());
        dbController.addSeason(season);
        //todo
    }


    public void signRefereeToSeason() {
        //todo
    }


    public void insertSchedulingPolicy() {
        //todo
    }


    public void changeScorePolicy() {
        //todo
    }

}

package Users;

import League.*;
import system.DBController;
import Exception.*;


import java.util.HashMap;
import java.util.HashSet;
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
            if (!leagues.containsKey(leagueName)) {
                league = new League(leagueName);
                dbController.addLeague(league);
            } else {
                throw new AlreadyExistException();
            }
        } else {
            throw new IncorrectInputException(leagueName);
        }
        //todo
    }

    public void setLeagueByYear(String specificLeague, String year) throws ObjectNotExist, AlreadyExistException, DontHavePermissionException {
        HashMap<String, League> leagues = dbController.getLeagues(this);
        League league = leagues.get(specificLeague);
        if (league == null) {
            throw new ObjectNotExist("There is no league called " + specificLeague);
        }

        Season season = new Season(year);
        if (league.getLeagueInSeason(season) != null) { //check if the season is already exist in this league
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


    public void insertSchedulingPolicy() {
        //todo
    }


    public void changeScorePolicy(String league, String season, String sWinning, String sDraw, String sLosing) throws IncorrectInputException {
        try{
            double winning = Double.parseDouble(sWinning);
            double draw = Double.parseDouble(sDraw);
            double losing = Double.parseDouble(sLosing);
            ScorePolicy policy = new ScorePolicy(winning, draw, losing);
            League leagueObj = dbController.getLeague(league);
            Season seasonObj = dbController.getSeason(season);
            LeagueInSeason leagueInSeason = leagueObj.getLeagueInSeason(seasonObj);
            leagueInSeason.setScorePolicy(policy);
        }
        catch(Exception e){
            throw new IncorrectInputException();
        }
    }

    /**
     * This function returns all the referees that each referee doesn't exist in the league in season that
     * we get in the parameters
     *
     * @param league - name of league
     * @param season - name of season
     * @return list of referees
     * @throws DontHavePermissionException
     */
    public HashMap<String, Referee> getRefereesDoesntExistInTheLeagueAndSeason(String league, String season) throws DontHavePermissionException {
        HashMap<String, Referee> referees = new HashMap<>();
        try {
            HashMap<String, Referee> allRefereesInTheSystem = dbController.getReferees(this);
            League leagueObj = dbController.getLeague(league);
            Season seasonObj = dbController.getSeason(season);
            LeagueInSeason leagueInSeason = leagueObj.getLeagueInSeason(seasonObj);
            HashMap<String, Referee> refereesInLeagueInSeason = leagueInSeason.getReferees();
            for (String nameOfReferee : allRefereesInTheSystem.keySet()) {

                if (!refereesInLeagueInSeason.containsKey(nameOfReferee)) {
                    referees.put(nameOfReferee, allRefereesInTheSystem.get(nameOfReferee));
                }
            }
        } catch (Exception e) {
            throw new DontHavePermissionException();
        }
        return referees;
    }

    /**
     * @param league      - name of league to add the referee
     * @param season      - name of season to add the referee
     * @param refereeName - name of the referee we would like to add
     */
    public void addRefereeToLeagueInSeason(String league, String season, String refereeName) throws ObjectNotExist, ObjectAlreadyExist {

        HashMap<String, Referee> referees = dbController.getReferees();
        Referee referee = referees.get(refereeName);
        League leagueObj = dbController.getLeague(league);
        Season seasonObj = dbController.getSeason(season);
        LeagueInSeason leagueInSeason = leagueObj.getLeagueInSeason(seasonObj);
        if (!leagueInSeason.getReferees().containsKey(refereeName))
            leagueInSeason.addReferee(refereeName, referee);
        else
            throw new ObjectAlreadyExist();
    }


}

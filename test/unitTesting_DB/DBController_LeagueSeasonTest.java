package unitTesting_DB;

import DataBase.DBController;
import Domain.League.League;
import Domain.League.LeagueInSeason;
import Domain.League.Season;
import Domain.Users.Role;
import Exception.*;
import Service.SystemController;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class DBController_LeagueSeasonTest extends TestCase {
    SystemController controller = new SystemController("test DB");
    DBController dbc = DBController.getInstance();
    @Before
    public void init(){

    }

    @Test
    public void addSeason() throws ObjectNotExist, DontHavePermissionException, MemberNotExist, AlreadyExistException, PasswordDontMatchException {

        addSeasonPreCondition("1234");

        Season result = dbc.getSeason("1234");
        assertNotNull(result);
        assertEquals(result.getYear(),"1234");

    }
    @Test
    public void addLeague() throws ObjectNotExist, DontHavePermissionException, MemberNotExist, AlreadyExistException, PasswordDontMatchException {

        addLeaguePreCondition("league");

        League result = dbc.getLeague("league");
        assertNotNull(result);
        assertEquals(result.getName(),"league");

    }

    @Test
    public void addSeason2() throws ObjectNotExist, DontHavePermissionException, MemberNotExist, AlreadyExistException, PasswordDontMatchException, MemberAlreadyExistException, IncorrectInputException {

        addSeasonAndLeaguePreCondition("league2","1111");
        //todo
        Season result = dbc.getSeason("1111");
        League league = dbc.getLeague("league2");

        assertNotNull(result);
        assertEquals(result.getYear(),"1111");
        assertTrue(result.getLeagues().size() == 1 );
        assertTrue(result.getLeagues().get(league).getLeague().getName().equals("league2"));

    }

    private void addSeasonAndLeaguePreCondition(String league,String year) throws DontHavePermissionException, MemberNotExist, AlreadyExistException, PasswordDontMatchException, ObjectNotExist, MemberAlreadyExistException, IncorrectInputException {
        addLeaguePreCondition(league);
        addSeasonPreCondition(year);
        Season result = dbc.getSeason(year);
        League league1 = dbc.getLeague(league);

        LeagueInSeason leagueInSeason = new LeagueInSeason(league1,result);

        controller.signIn("referee","referee@gmail.com","123", new Date(1993,10,12));
        controller.logIn("admin@gmail.com","123");
        controller.addReferee("referee@gmail.com",false);
        controller.logOut();
        controller.logIn("dani@gmail.com","123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");
        //leagueInSeason.addReferee("referee@gmail.com" , );
        result.addLeagueInSeason(leagueInSeason);
    }

    private void addLeaguePreCondition(String league) throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException, AlreadyExistException {
        League league1 = new League(league);
        dbc.addLeague(preCon_Permission(),league1);
        controller.logOut();
    }
    private void addSeasonPreCondition(String year) throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException, AlreadyExistException {
        Season season = new Season(year);
        dbc.addSeason(preCon_Permission(),season);
        controller.logOut();
    }
    private Role preCon_Permission() throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException {
        return controller.logIn("admin@gmail.com","123");
    }
    private Role preCon_PermissionAD() throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException {
        return controller.logIn("dani@gmail.com","123");
    }

    private void addAss() throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException, AlreadyExistException, IncorrectInputException {
        controller.signIn("dani","dani@gmail.com","123", new Date(1993,10,12));
        controller.logIn("admin@gmail.com","123");
        controller.addAssociationDelegate("dani@gmail.com");
        controller.logOut();
    }

}
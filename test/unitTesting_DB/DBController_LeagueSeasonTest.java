package unitTesting_DB;

import DataBase.DBController;
import Domain.League.League;
import Domain.League.LeagueInSeason;
import Domain.League.Season;
import Domain.Users.Role;
import Domain.Users.SystemManager;
import Exception.*;
import Service.SystemController;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

public class DBController_LeagueSeasonTest extends TestCase {
    SystemController controller = new SystemController("test DB");
    SystemManager systemManager;
    DBController dbc = DBController.getInstance();
    @Before
    public void init(){
        try {
            systemManager = (SystemManager)preCon_Permission();
        } catch (PasswordDontMatchException e) {
            e.printStackTrace();
        } catch (MemberNotExist memberNotExist) {
            memberNotExist.printStackTrace();
        } catch (DontHavePermissionException e) {
            e.printStackTrace();
        }
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
    public void updateLeagueAndSeason() throws ObjectNotExist, DontHavePermissionException, MemberNotExist, AlreadyExistException, PasswordDontMatchException, MemberAlreadyExistException, IncorrectInputException {

        Season season = dbc.getSeason("1234");
        League league = dbc.getLeague("league");
        LeagueInSeason leagueInSeason = new LeagueInSeason(league,season);
        league.addLeagueInSeason(leagueInSeason);
        season.addLeagueInSeason(leagueInSeason);

        dbc.updateLeague(systemManager,league);
        dbc.updateSeason(systemManager,season);


        Season resultS = dbc.getSeason("1234");
        League resultL = dbc.getLeague("league");

        assertNotNull(resultL);
        assertNotNull(resultS);

        assertEquals(resultS.getLeagues().get(league) , leagueInSeason);
        assertEquals(resultL.getLeagueInSeason(season) , leagueInSeason);
        assertTrue(resultL.getSeasons().size() == 1 );
        assertTrue(resultS.getLeagues().get(league).getLeague().getName().equals("league"));
        assertTrue(resultL.getLeagueInSeason(season).getSeason().getYear().equals("1234"));

    }
    @Test
    public void updateSeason() throws ObjectNotExist, DontHavePermissionException, MemberNotExist, AlreadyExistException, PasswordDontMatchException, MemberAlreadyExistException, IncorrectInputException {

        Season season = dbc.getSeason("1234");
        addLeaguePreCondition("league2");
        League league = dbc.getLeague("league2");

        LeagueInSeason leagueInSeason = new LeagueInSeason(league,season);
        league.addLeagueInSeason(leagueInSeason);
        season.addLeagueInSeason(leagueInSeason);

        dbc.updateLeague(systemManager,league);
        dbc.updateSeason(systemManager,season);


        Season resultS = dbc.getSeason("1234");
        League resultL = dbc.getLeague("league2");

        assertNotNull(resultL);
        assertNotNull(resultS);

        assertEquals(resultS.getLeagues().get(league) , leagueInSeason);
        assertEquals(resultL.getLeagueInSeason(season) , leagueInSeason);
        assertTrue(resultL.getSeasons().size() == 1 );
        assertTrue(resultS.getLeagues().size() == 2 );
        assertTrue(resultS.getLeagues().get(league).getLeague().getName().equals("league2"));
        assertTrue(resultL.getLeagueInSeason(season).getSeason().getYear().equals("1234"));

    }
    @Test
    public void addFan() throws ObjectNotExist, DontHavePermissionException, MemberNotExist, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        //todo
    }@Test
    public void addAsDele() throws ObjectNotExist, DontHavePermissionException, MemberNotExist, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
       //todo
    }@Test
    public void addSysM() throws ObjectNotExist, DontHavePermissionException, MemberNotExist, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
       //todo
    }@Test
    public void addReferee() throws ObjectNotExist, DontHavePermissionException, MemberNotExist, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
       //todo
    }@Test
    public void addOwner() throws ObjectNotExist, DontHavePermissionException, MemberNotExist, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
       //todo
    }
    @Test
    public void existSeason() throws ObjectNotExist, DontHavePermissionException, MemberNotExist, AlreadyExistException, PasswordDontMatchException {
        addSeasonPreCondition("1234");
        assertTrue(dbc.existSeason("1234"));
    }
    @Test
    public void existLeague() throws ObjectNotExist, DontHavePermissionException, MemberNotExist, AlreadyExistException, PasswordDontMatchException {
        addLeaguePreCondition("league");
        assertTrue(dbc.existSeason("league"));
    }
    @Test
    public void existMembers() throws ObjectNotExist, DontHavePermissionException, MemberNotExist, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, MemberAlreadyExistException {
        addMembers();
        assertTrue(dbc.existAssociationDelegate("dani@gmail.com"));
        assertTrue(dbc.existOwner("owner@gmail.com"));
        assertTrue(dbc.existFan("fan@gmail.com"));
        assertTrue(dbc.existReferee("referee@gmail.com"));
        assertTrue(dbc.existSystemManager("sysM@gmail.com"));
        assertTrue(dbc.existMember("sysM@gmail.com"));
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
        dbc.addLeague(systemManager,league1);
    }
    private void addSeasonPreCondition(String year) throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException, AlreadyExistException {
        Season season = new Season(year);
        dbc.addSeason(systemManager,season);
    }
    private Role preCon_Permission() throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException {
        return controller.logIn("admin@gmail.com","123");
    }
    private Role preCon_PermissionAD() throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException {
        return controller.logIn("dani@gmail.com","123");
    }
    private void addAss() throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException, AlreadyExistException, IncorrectInputException {
        controller.logOut();
        controller.signIn("dani","dani@gmail.com","123", new Date(1993,10,12));
        controller.logIn("admin@gmail.com","123");
        controller.addAssociationDelegate("dani@gmail.com");
        controller.logOut();
    }
    private void addMembers() throws DontHavePermissionException, IncorrectInputException, MemberNotExist, AlreadyExistException, PasswordDontMatchException, MemberAlreadyExistException {
        addAss();
        controller.signIn("fan","fan@gmail.com","123", new Date(1993,10,12));
        controller.signIn("owner","owner@gmail.com","123", new Date(1993,10,12));
        controller.signIn("referee","referee@gmail.com","123", new Date(1993,10,12));
        controller.signIn("sysM","sysM@gmail.com","123", new Date(1993,10,12));

        controller.logIn("admin@gmail.com","123");

        controller.addSystemManager("sysM@gmail.com");
        controller.addOwner("ownerM@gmail.com");
        controller.addReferee("referee@gmail.com",false);

        controller.logOut();
    }

}
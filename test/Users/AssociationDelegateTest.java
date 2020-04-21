package Users;

import Exception.*;
import League.Season;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import system.SystemController;

import java.util.Date;

import static org.junit.Assert.*;

public class AssociationDelegateTest {
    Date birthdate=new Date(1993,10,12);
    SystemController controller= new SystemController("test");
    AssociationDelegate a_s_Test = new AssociationDelegate("dani" , "dani@gmail.com","123", birthdate);

    @Before
    public void init() throws IncorrectInputException, AlreadyExistException, DontHavePermissionException, MemberNotExist, PasswordDontMatchException {
        controller.signIn(a_s_Test.getName(),a_s_Test.getUserMail(),a_s_Test.getPassword(), a_s_Test.getBirthDate());
        controller.logIn("admin@gmail.com","123");
        controller.addAssociationDelegate(this.a_s_Test.getUserMail());
    }
    @Rule
    public final ExpectedException thrown= ExpectedException.none();
    /*******************************************************************************/
    @Test
    public void setLeague() throws MemberNotExist, PasswordDontMatchException, AlreadyExistException, IncorrectInputException, DontHavePermissionException {
        controller.logOut();
        controller.logIn("dani@gmail.com","123");


        /* try to add league with valid details -result should be positive */
        controller.setLeague("league");
    }
    @Test
    public void setLeagueAlreadyExistPermission() throws AlreadyExistException, IncorrectInputException, MemberNotExist, PasswordDontMatchException {
        thrown.expect(DontHavePermissionException.class);

        /* try to add league who without logIn -result should be negative */

        controller.setLeague("league");
    }
    @Test
    public void setLeagueAlreadyExistException() throws AlreadyExistException, IncorrectInputException, MemberNotExist, PasswordDontMatchException, DontHavePermissionException {
        thrown.expect(AlreadyExistException.class);
        /* UC 19 (noa) */
        /*init*/
        controller.logOut();
        controller.logIn("dani@gmail.com","123");
        controller.setLeague("league");
        /* try to add league who already exist -result should be negative */
        controller.setLeague("league");
    }
    /*******************************************************************************/
    @Test
    public void setLeagueByYear() throws MemberNotExist, PasswordDontMatchException, AlreadyExistException, IncorrectInputException, DontHavePermissionException, ObjectNotExist {
        //UC20
        controller.logOut();
        controller.logIn("dani@gmail.com","123");
        controller.setLeague("league");


        /* try to add referee with valid details -result should be positive */
        controller.setLeagueByYear("league","2020");
//       check if season is connect to league
        boolean contains= false;
        for (Season s: controller.getLeagues().get("league").getSeasons().keySet()) {
            if(s.getYear().equals("2020")){
                contains = true;
            }
        }
        assertTrue(contains);
    }
    @Test
    public void setLeagueByYearLeagueException() throws MemberNotExist, PasswordDontMatchException, AlreadyExistException, IncorrectInputException, DontHavePermissionException, ObjectNotExist {
        thrown.expect(ObjectNotExist.class);
        controller.logOut();
        controller.logIn("dani@gmail.com","123");

        /* try to add referee with invalid league -result should be negative */
        controller.setLeagueByYear("league","2020");
    }
    @Test
    public void setLeagueByYearSeasonException() throws MemberNotExist, PasswordDontMatchException, AlreadyExistException, IncorrectInputException, DontHavePermissionException, ObjectNotExist {
        thrown.expect(AlreadyExistException.class);
        controller.logOut();
        controller.logIn("dani@gmail.com","123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");

        /* try to add referee with valid league , invalid season -result should be negative */
        controller.setLeagueByYear("league","2020");
    }
    /*******************************************************************************/
    @Test
    public void addRefereeToLeagueInSeason() throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberNotExist, PasswordDontMatchException, ObjectNotExist {
        /* init - add referee , add league, add season */
        controller.logOut();
        controller.signIn("referee","referee@gmail.com","123", a_s_Test.getBirthDate());
        controller.logIn("admin@gmail.com","123");
        controller.addReferee("referee@gmail.com",false);
        controller.logOut();
        controller.logIn("dani@gmail.com","123");
        assertTrue(controller.getRefereesDoesntExistInTheLeagueAndSeason("league","season").containsKey("referee@gmail.com"));
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");
        int sizeBefore = controller.getRefereesDoesntExistInTheLeagueAndSeason("league","2020").size();


        /* try to add referee with valid details -result should be positive */
        controller.addRefereeToLeagueInSeason("league","202","referee@gmail.com");
        assertEquals(sizeBefore-1,controller.getRefereesDoesntExistInTheLeagueAndSeason("league","2020").size());
        assertFalse(controller.getRefereesDoesntExistInTheLeagueAndSeason("league","season").containsKey("referee@gmail.com"));

    }
    @Test
    public void addRefereeToLeagueInSeasonPermission() {
        thrown.expect(DontHavePermissionException.class);
        controller.addRefereeToLeagueInSeason("league","season","referee");
    }
    @Test
    public void addRefereeToLeagueInSeasonLeagueException() throws AlreadyExistException, IncorrectInputException, MemberNotExist, PasswordDontMatchException, DontHavePermissionException, ObjectNotExist {
        thrown.expect(ObjectNotExist.class);
        /* init - add referee */
        controller.logOut();
        controller.signIn("referee","referee@gmail.com","123", a_s_Test.getBirthDate());
        controller.logIn("admin@gmail.com","123");
        controller.addReferee("referee@gmail.com",false);
        controller.logOut();
        controller.logIn("dani@gmail.com","123");
        int sizeBefore = controller.getRefereesDoesntExistInTheLeagueAndSeason("league","2020").size();

        /* try to add referee to un valid league -result should be negative */
        controller.addRefereeToLeagueInSeason("league0","2020","referee@gmail.com");
        assertEquals(sizeBefore,controller.getRefereesDoesntExistInTheLeagueAndSeason("league","2020").size());

    }
    @Test
    public void addRefereeToLeagueInSeasonSeasonException() throws MemberNotExist, PasswordDontMatchException, AlreadyExistException, IncorrectInputException, DontHavePermissionException, ObjectNotExist {
        thrown.expect(ObjectNotExist.class);
        /* init - add referee , add league */
        controller.logOut();
        controller.signIn("referee","referee@gmail.com","123", a_s_Test.getBirthDate());
        controller.logIn("admin@gmail.com","123");
        controller.addReferee("referee@gmail.com",false);
        controller.logOut();
        controller.logIn("dani@gmail.com","123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");
        int sizeBefore = controller.getRefereesDoesntExistInTheLeagueAndSeason("league","2020").size();


        /* try to add referee to un valid season -result should be negative */
        controller.addRefereeToLeagueInSeason("league","2021","referee@gmail.com");
        assertEquals(sizeBefore,controller.getRefereesDoesntExistInTheLeagueAndSeason("league","2020").size());
    }
    /*******************************************************************************/
    @Test
    public void insertSchedulingPolicy() {
    }
    /*******************************************************************************/
    @Test
    public void changeScorePolicy() throws IncorrectInputException, ObjectNotExist {

    }

}
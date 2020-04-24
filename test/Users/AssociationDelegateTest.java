package Users;

import Exception.*;
import League.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import system.SystemController;

import java.util.Date;
import java.util.HashMap;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class AssociationDelegateTest {
    Date birthdate=new Date(1993,10,12);
    SystemController controller= new SystemController("test");
    //AssociationDelegate a_s_Test = new AssociationDelegate("dani" , "dani@gmail.com","123", birthdate);

    public AssociationDelegateTest() throws DontHavePermissionException, AlreadyExistException, MemberNotExist, IncorrectInputException {
    }

    @Before
    public void init() throws IncorrectInputException, AlreadyExistException, DontHavePermissionException, MemberNotExist, PasswordDontMatchException {
        controller.signIn("dani","dani@gmail.com","123", birthdate);
        controller.logIn("admin@gmail.com","123");
        controller.addAssociationDelegate("dani@gmail.com");
        controller.logOut();
    }
    @Rule
    public final ExpectedException thrown= ExpectedException.none();
    /*******************************************************************************/
    @Test
    public void setLeague() throws MemberNotExist, PasswordDontMatchException, AlreadyExistException, IncorrectInputException, DontHavePermissionException {
        controller.logIn("dani@gmail.com","123");


        /* try to add league with valid details -result should be positive */
        controller.setLeague("league");

    }
    @Test
    public void setLeagueAlreadyExistPermission() throws AlreadyExistException, IncorrectInputException, MemberNotExist, PasswordDontMatchException, DontHavePermissionException {
        thrown.expect(DontHavePermissionException.class);

        /* try to add league who without logIn -result should be negative */

        controller.setLeague("league");
        assertTrue(controller.getLeagues().containsKey("league"));
    }
    @Test
    public void setLeagueAlreadyExistException() throws AlreadyExistException, IncorrectInputException, MemberNotExist, PasswordDontMatchException, DontHavePermissionException {
        thrown.expect(AlreadyExistException.class);

        /*init*/
        controller.logIn("dani@gmail.com","123");
        controller.setLeague("league");

        /* try to add league who already exist -result should be negative */
        controller.setLeague("league");
    }
    /************************************** setLeagueByYear *****************************************/
    @Test
    public void setLeagueByYear() throws MemberNotExist, PasswordDontMatchException, AlreadyExistException, IncorrectInputException, DontHavePermissionException, ObjectNotExist {
        /* init */
        controller.logIn("dani@gmail.com","123");
        controller.setLeague("league");

        /* try to setLeagueByYear with valid details -result should be positive */
        controller.setLeagueByYear("league","2020");
//       check if season is connect to league
        boolean contains= false;
        League league = controller.getLeague("league");
        HashMap<Season, LeagueInSeason> leagueInSeasons = league.getSeasons();
        for (Season s: leagueInSeasons.keySet()) {
            if(s.getYear().equals("2020")){
                contains = true;
            }
        }
        assertTrue(contains);

        controller.changeScorePolicy("league","2020","3","1","0");
    }
    @Test
    public void setLeagueByYearLeaguePermission() throws MemberNotExist, PasswordDontMatchException, AlreadyExistException, IncorrectInputException, DontHavePermissionException, ObjectNotExist {
        thrown.expect(DontHavePermissionException.class);

        /* try to setLeagueByYear with invalid league -result should be negative */
        controller.setLeagueByYear("league","2020");
    }
    @Test
    public void setLeagueByYearLeagueException() throws MemberNotExist, PasswordDontMatchException, AlreadyExistException, IncorrectInputException, DontHavePermissionException, ObjectNotExist {
        thrown.expect(ObjectNotExist.class);
        controller.logIn("dani@gmail.com","123");

        /* try to setLeagueByYear with invalid league -result should be negative */
        controller.setLeagueByYear("league","2020");
    }
    @Test
    public void setLeagueByYearSeasonException() throws MemberNotExist, PasswordDontMatchException, AlreadyExistException, IncorrectInputException, DontHavePermissionException, ObjectNotExist {
        thrown.expect(AlreadyExistException.class);
        /* init */
        controller.logIn("dani@gmail.com","123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");

        /* try to setLeagueByYear with valid league , invalid season -result should be negative */
        controller.setLeagueByYear("league","2020");

    }
    /************************************** addRefereeToLeagueInSeason *****************************************/
    @Test
    public void addRefereeToLeagueInSeason() throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, MemberAlreadyExistException {
        /* init - add referee , add league, add season */
        controller.signIn("referee","referee@gmail.com","123", birthdate);
        controller.logIn("admin@gmail.com","123");
        controller.addReferee("referee@gmail.com",false);
        controller.logOut();
        controller.logIn("dani@gmail.com","123");
        //assertTrue(controller.getRefereesDoesntExistInTheLeagueAndSeason("league","season").containsKey("referee@gmail.com"));
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");
        int sizeBefore = controller.getRefereesDoesntExistInTheLeagueAndSeason("league","2020").size();
        int sizeBeforeIn = controller.getRefereesInLeagueInSeason("league","2020").size();


        /* try to add referee with valid details -result should be positive */
        controller.addRefereeToLeagueInSeason("league","2020","referee@gmail.com");
        assertEquals(sizeBefore-1,controller.getRefereesDoesntExistInTheLeagueAndSeason("league","2020").size());
        assertFalse(controller.getRefereesDoesntExistInTheLeagueAndSeason("league","season").containsKey("referee@gmail.com"));
        assertTrue(controller.getRefereesInLeagueInSeason("league","2020").containsKey("referee@gmail.com"));
        assertEquals(sizeBeforeIn+1 , controller.getRefereesInLeagueInSeason("league","2020").size());

    }
    @Test
    public void addRefereeToLeagueInSeasonPermission() throws DontHavePermissionException, ObjectNotExist {
        thrown.expect(DontHavePermissionException.class);
        controller.addRefereeToLeagueInSeason("league","season","referee");
    }
    @Test
    public void addRefereeToLeagueInSeasonLeagueException() throws AlreadyExistException, IncorrectInputException, MemberNotExist, PasswordDontMatchException, DontHavePermissionException, ObjectNotExist, MemberAlreadyExistException {
        thrown.expect(ObjectNotExist.class);
        /* init - add referee */
        controller.logOut();
        controller.signIn("referee","referee@gmail.com","123", birthdate);
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
    public void addRefereeToLeagueInSeasonSeasonException() throws MemberNotExist, PasswordDontMatchException, AlreadyExistException, IncorrectInputException, DontHavePermissionException, ObjectNotExist, MemberAlreadyExistException {
        thrown.expect(ObjectNotExist.class);
        /* init - add referee , add league */
        controller.logOut();
        controller.signIn("referee","referee@gmail.com","123",birthdate);
        controller.logIn("admin@gmail.com","123");
        controller.addReferee("referee@gmail.com",false);
        controller.logOut();
        controller.logIn("dani@gmail.com","123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");
        int sizeBefore = controller.getRefereesDoesntExistInTheLeagueAndSeason("league","2020").size();


        /* try to add referee to invalid season -result should be negative */
        controller.addRefereeToLeagueInSeason("league","2021","referee@gmail.com");
        assertEquals(sizeBefore,controller.getRefereesDoesntExistInTheLeagueAndSeason("league","2020").size());
    }
    /************************************** changeScorePolicy *****************************************/
    @Test
    public void changeScorePolicy() throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException, AlreadyExistException, IncorrectInputException, ObjectNotExist {
        controller.logIn("dani@gmail.com", "123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");
        
        /* try to change score policy with league and season valid - result should be positive*/
        controller.changeScorePolicy("league","2020","3","1","0");

        ScorePolicy policy = (ScorePolicy)controller.getScorePolicy("league","2020");
        assertTrue(policy.getScoreToDrawGame() == Double.parseDouble("1"));
        assertTrue(policy.getScoreToLosingTeam() == Double.parseDouble("0"));
        assertTrue(policy.getScoreToWinningTeam() == Double.parseDouble("3"));
    }
    @Test
    public void changeScorePolicyNotPermission() throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException, AlreadyExistException, IncorrectInputException, ObjectNotExist {
        thrown.expect(DontHavePermissionException.class);
        /* init */
        controller.logIn("dani@gmail.com", "123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");
        controller.logOut();

        /*try to change policy without login - result should be negative*/
        controller.changeScorePolicy("league","2020","3","1","0");
    }
    @Test
    public void changeScorePolicyLeagueNotExist() throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException, AlreadyExistException, IncorrectInputException, ObjectNotExist {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.logIn("dani@gmail.com", "123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");

        /*try to change policy with invalid league - result should be negative*/
        controller.changeScorePolicy("league2","2020","3","1","0");
    }
    @Test
    public void changeScorePolicyPolicySeasonNotExist() throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException, AlreadyExistException, IncorrectInputException, ObjectNotExist {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.logIn("dani@gmail.com", "123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");

        /*try to change policy with invalid season - result should be negative*/
        controller.changeScorePolicy("league","2021","3","1","0");
    }
    /*******************************************************************************/
    @Test
    public void insertSchedulingPolicy1() throws IncorrectInputException, ObjectNotExist, AlreadyExistException, DontHavePermissionException, ObjectAlreadyExist, MemberNotExist, NoEnoughMoney, PasswordDontMatchException {
        controller.logIn("dani@gmail.com", "123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");

        /* try to change scheduling policy with league and season valid - result should be positive*/
        /* policy number 1*/
        controller.setSchedulingPolicyToLeagueInSeason("league","2020","All teams play each other twice");

        assertThat(controller.getSchedulingPolicyInLeagueInSeason("league","2020"),instanceOf(SchedulingPolicyAllTeamsPlayTwice.class));
    }
    @Test
    public void insertSchedulingPolicy2() throws IncorrectInputException, ObjectNotExist, AlreadyExistException, DontHavePermissionException, ObjectAlreadyExist, MemberNotExist, NoEnoughMoney, PasswordDontMatchException {
        controller.logIn("dani@gmail.com", "123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");

        /* try to change scheduling policy with league and season valid - result should be positive*/
        /* policy number 2*/
        controller.setSchedulingPolicyToLeagueInSeason("league","2020","All teams play each other once");

        assertThat(controller.getSchedulingPolicyInLeagueInSeason("league","2020"),instanceOf(SchedulingPolicyAllTeamsPlayOnce.class));

    }
    @Test
    public void insertSchedulingPolicyPermission() throws IncorrectInputException, ObjectNotExist, AlreadyExistException, DontHavePermissionException, ObjectAlreadyExist, MemberNotExist, NoEnoughMoney, PasswordDontMatchException {
        thrown.expect(DontHavePermissionException.class);
        controller.logIn("dani@gmail.com", "123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");

        controller.logOut();
        /* try to change scheduling policy without login - result should be negative*/
        controller.setSchedulingPolicyToLeagueInSeason("league","2020","All teams play each other once");
    }
    @Test
    public void insertSchedulingPolicyLeagueException() throws IncorrectInputException, ObjectNotExist, AlreadyExistException, DontHavePermissionException, ObjectAlreadyExist, MemberNotExist, NoEnoughMoney, PasswordDontMatchException {
       thrown.expect(ObjectNotExist.class);
        controller.logIn("dani@gmail.com", "123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");

        /* try to change scheduling policy with incorrect league - result should be negative*/
        controller.setSchedulingPolicyToLeagueInSeason("league11","2020","All teams play each other once");
    }
    @Test
    public void insertSchedulingPolicySeasonException() throws IncorrectInputException, ObjectNotExist, AlreadyExistException, DontHavePermissionException, ObjectAlreadyExist, MemberNotExist, NoEnoughMoney, PasswordDontMatchException {
        thrown.expect(ObjectNotExist.class);
        controller.logIn("dani@gmail.com", "123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");

        /* try to change scheduling policy with incorrect season - result should be negative*/
        controller.setSchedulingPolicyToLeagueInSeason("league","20","All teams play each other once");
    }
    @Test
    public void insertSchedulingPolicyPolicyException() throws IncorrectInputException, ObjectNotExist, AlreadyExistException, DontHavePermissionException, ObjectAlreadyExist, MemberNotExist, NoEnoughMoney, PasswordDontMatchException {
        thrown.expect(IncorrectInputException.class);
        controller.logIn("dani@gmail.com", "123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");

        /* try to change scheduling policy with incorrect policy - result should be negative*/
        controller.setSchedulingPolicyToLeagueInSeason("league","2020","Allther once");
    }
}
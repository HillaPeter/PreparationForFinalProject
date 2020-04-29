package acceptanceTesting;

import Domain.Game.Game;
import Domain.Users.Member;
import Service.SecurityMachine;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import Service.SystemController;
import Exception.*;

import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.*;

public class RefereeTest {
    Date birthdate=new Date(1993,10,12);
    private SystemController controller;// = new SystemController("");
    private SecurityMachine securityMachine = new SecurityMachine();
    public RefereeTest() throws DontHavePermissionException, AlreadyExistException, MemberNotExist, IncorrectInputException {
    }
//    private SystemManager systemManager=new SystemManager("for test" , "for Test" , "fortest" , new DBController());
    /***********************************************************************************************/
    @Before
    public void init() throws IncorrectInputException, AlreadyExistException, DontHavePermissionException, MemberNotExist, PasswordDontMatchException, MemberAlreadyExistException {
        controller = new SystemController("");
        controller.deleteDBcontroller();
        controller.signIn("referee","referee0@gmail.com","123" ,birthdate);
        controller.logIn("admin@gmail.com","123");
        controller.addReferee("referee0@gmail.com",false);
        controller.logOut();
    }
    @Rule
    public final ExpectedException thrown= ExpectedException.none();
    /************************************* printGameSchedule ***************************************/
    @Test
    public void printGameSchedule() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, AlreadyExistException, ObjectNotExist, ObjectAlreadyExist, NoEnoughMoney, MemberAlreadyExistException {
        /*********** init - scheduling games ************/

        /* init - enter league , season , schedulePolicy  */
        controller.signIn("associateDelegite", "dani@gmail.com", "123", birthdate);

        controller.logIn("admin@gmail.com", "123");
        controller.addAssociationDelegate("dani@gmail.com");
        controller.logOut();

        controller.logIn("dani@gmail.com", "123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");
        controller.setSchedulingPolicyToLeagueInSeason("league","2020","All teams play each other once");
        controller.logOut();

        /*init - add 20 Teams ,referees to league in season */
        addTeamsCorrectly(20);
        enterReferee(20);
        addTeamsToLeagueSeason(20);
        addRefereesToLeagueInSeason(20);

        controller.logIn("admin@gmail.com", "123");
        controller.schedulingGames("2020","league");
        controller.logOut();

        /*********** end of scheduling games ************/

        controller.logIn("referee0@gmail.com","123");

        /*try to printGameSchedule with login- result should be positive */
        HashSet<Game> games = controller.getGameSchedule();
        assertTrue(games.size() != 0);
    }
    @Test
    public void printGameSchedulePermission() throws DontHavePermissionException {
        thrown.expect(DontHavePermissionException.class);
        /* init */

        /*try to printGameSchedule without login- result should be negative */
        controller.getGameSchedule();
    }
    /*************************************** updateDetails ****************************************/
    @Test
    public void updateDetails() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, AlreadyExistException, ObjectNotExist {
        /* init */
        Member member= controller.logIn("referee0@gmail.com","123");

        /*try to update details after login with correct values- result should be positive */
        controller.updateDetails("newName" ,"newMail@gmail.com","training");

        assertNotNull(controller.getReferee("newMail@gmail.com"));
        assertEquals("newName",controller.getReferees().get("newMail@gmail.com").getName());
        assertEquals("newMail@gmail.com",controller.getReferees().get("newMail@gmail.com").getUserMail());
    }
    @Test
    public void updateDetailsIncorrectInput() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, AlreadyExistException {
       thrown.expect(IncorrectInputException.class);
        /* init */
        Member member = controller.logIn("referee0@gmail.com","123");

        /*try to update details after login with incorrect values- result should be negative */
        controller.updateDetails(null ,"referee@gmail.com", "training");

        assertNotNull(controller.getReferees().get("referee@gmail.com"));
        assertEquals(member.getName() , controller.getReferees().get("referee@gmail.com").getName());
        assertEquals(member.getPassword() , controller.getReferees().get("referee@gmail.com").getPassword());
    }
    @Test
    public void updateDetailsPermission() throws DontHavePermissionException, IncorrectInputException, AlreadyExistException, MemberNotExist {
        thrown.expect(DontHavePermissionException.class);
        /* init */

        /*try to update details without login- result should be negative */
        controller.updateDetails("newName" ,"referee@gmail.com", "training");
    }
    /*******************************************************************************/

    private void addTeamsCorrectly(int mumOfTeams) throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, ObjectNotExist, ObjectAlreadyExist, MemberNotExist, NoEnoughMoney, PasswordDontMatchException {
        for(int i=0 ; i< mumOfTeams ; i++){
            addUsers(i);
            addTeam(i);
            addPlayers(i);
            addFieldToTeam(i);
        }
    }
    private void addUsers(int i) throws IncorrectInputException, DontHavePermissionException, AlreadyExistException {
        controller.signIn("palyer0"+i,"p0"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer1"+i,"p1"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer2"+i,"p2"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer3"+i,"p3"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer4"+i,"p4"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer5"+i,"p5"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer6"+i,"p6"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer7"+i,"p7"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer8"+i,"p8"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer9"+i,"p9"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer10"+i,"p10"+i+"@gmail.com","1",birthdate);
        controller.signIn("coach"+i,"coach"+i+"@gmail.com","1",birthdate);
        controller.signIn("manager"+i,"manager"+i+"@gmail.com","1",birthdate);
    }
    private void addTeamsToLeagueSeason(int mumOfTeams) throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, ObjectNotExist, ObjectAlreadyExist, MemberNotExist, PasswordDontMatchException, NoEnoughMoney {
        for(int i=0 ; i< mumOfTeams ; i++){
            controller.logIn("dani@gmail.com", "123");
            controller.addTeamToLeagueInSeason("league","2020","team"+i);
            controller.logOut();
        }
    }
    private void addPlayers(int i) throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException, ObjectNotExist, IncorrectInputException, NoEnoughMoney, AlreadyExistException {
        controller.logIn("owner"+i+"@gmail.com","1");

        controller.setMoneyToAccount("team"+i,10000);
        controller.addPlayer("p0"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p1"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p2"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p3"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p4"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p5"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p6"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p7"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p8"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p9"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p10"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addCoach("team"+i ,"coach"+i+"@gmail.com" );
        controller.addManager("team"+i , "manager"+i+"@gmail.com");
        controller.logOut();
    }
    private void addFieldToTeam(int i) throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException, ObjectNotExist, IncorrectInputException, NoEnoughMoney, AlreadyExistException, ObjectAlreadyExist {
        controller.logIn("owner"+i+"@gmail.com","1");
        controller.addField("team"+i,"f"+i);
        controller.logOut();
    }
    private void addTeam(int i) throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberNotExist, PasswordDontMatchException, ObjectAlreadyExist, ObjectNotExist, NoEnoughMoney {
        controller.signIn("owner"+i,"owner"+i+"@gmail.com","1",birthdate);
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team"+i,"owner"+i+"@gmail.com");
        controller.logOut();
    }
    private void enterReferee(int numOfReferees) throws IncorrectInputException, DontHavePermissionException, AlreadyExistException {
        for(int i=1; i<numOfReferees; i++)
            controller.signIn("referee"+i, "referee"+i+"@gmail.com", "123", birthdate);
    }
    private void addRefereesToLeagueInSeason(int numOfReferees) throws DontHavePermissionException, IncorrectInputException, MemberAlreadyExistException, MemberNotExist, AlreadyExistException, PasswordDontMatchException, ObjectNotExist {

        controller.logIn("admin@gmail.com", "123");
        for(int i=1 ; i<numOfReferees/2;i++)
            controller.addReferee("referee"+i+"@gmail.com", false);
        for(int i=numOfReferees/2 ; i<numOfReferees;i++)
            controller.addReferee("referee"+i+"@gmail.com", true);
        controller.logOut();

        controller.logIn("dani@gmail.com", "123");
        for(int i=0 ; i<numOfReferees;i++)
            controller.addRefereeToLeagueInSeason("league","2020","referee"+i+"@gmail.com");
        controller.logOut();
    }
}
package Domain.Users.acceptanceTesting;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import Service.SystemController;
import Exception.*;

import java.util.Date;

public class RefereeTest {
    private SystemController controller = new SystemController("");

    public RefereeTest() throws DontHavePermissionException, AlreadyExistException, MemberNotExist, IncorrectInputException {
    }
//    private SystemManager systemManager=new SystemManager("for test" , "for Test" , "fortest" , new DBController());
    /*******************************************************************************/
    @Before
    public void init() throws IncorrectInputException, AlreadyExistException, DontHavePermissionException, MemberNotExist, PasswordDontMatchException, MemberAlreadyExistException {
        Date birthdate=new Date(1993,10,12);
        controller.signIn("referee","referee@gmail.com","123" ,birthdate);
        controller.logIn("admin@gmail.com","123");
        controller.addReferee("referee@gmail.com",false);
    }
    @Rule
    public final ExpectedException thrown= ExpectedException.none();
    /*******************************************************************************/
    @Test
    public void printGameSchedule() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException {
        /* init */
        controller.logIn("referee@gmail.com","123");

        /*try to printGameSchedule with login- result should be positive */
      //  HashMap<String,String> games = controller.getGameSchedule("referee@gmail.com");
        //todo - first needs to schedule games to league
        //todo - after add the referee to game - and after check if the game saved


    }
    @Test
    public void printGameSchedulePermission() throws DontHavePermissionException {
        thrown.expect(DontHavePermissionException.class);
        /* init */

        /*try to printGameSchedule without login- result should be negative */
       // controller.getGameSchedule("referee@gmail.com");
    }
    /*******************************************************************************/
    @Test
    public void updateDetails() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException {
        /* init */
        controller.logIn("referee@gmail.com","123");

        /*try to update details after login with correct values- result should be positive */
       // controller.updateDetails("referee@gmail.com","newName" , "newMail@gmail.com","1234","training");

//        assertNotNull(controller.getReferees(systemManager).get("newMail@gmail.com"));
//        assertNotNull(controller.getRoles().get("newMail@gmail.com"));
//        assertEquals("newName",controller.getReferees(systemManager).get("newMail@gmail.com").getName());
//        assertEquals("newMail@gmail.com",controller.getReferees(systemManager).get("newMail@gmail.com").getUserMail());
//        assertEquals("1234",controller.getReferees(systemManager).get("newMail@gmail.com").getPassword());
    }
    @Test
    public void updateDetailsIncorrectName() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException {
       thrown.expect(IncorrectInputException.class);
        /* init */
        controller.logIn("referee@gmail.com","123");

        /*try to update details after login with incorrect values- result should be negative */
       // controller.updateDetails("referee@gmail.com","12344" , "newMail@gmail.com","1234","training");

//        assertNotNull(controller.getReferees(systemManager).get("referee@gmail.com"));
//        assertEquals("referee",controller.getReferees(systemManager).get("referee@gmail.com").getName());
//        assertEquals("123",controller.getReferees(systemManager).get("referee@gmail.com").getPassword());
    }
    @Test
    public void updateDetailsPermission() throws DontHavePermissionException {
        thrown.expect(DontHavePermissionException.class);
        /* init */

        /*try to update details without login- result should be negative */
       // controller.updateDetails("referee@gmail.com","newName" , "newMail@gmail.com","1234","training");
    }
    /*******************************************************************************/
    @Test
    public void deleteTheGames() {
    }
}
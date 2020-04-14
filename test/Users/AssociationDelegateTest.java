package Users;

import League.League;
import Exception.*;
import org.junit.Before;
import org.junit.Test;
import system.SystemController;

import static org.junit.Assert.*;

public class AssociationDelegateTest {
    SystemController controller= new SystemController("test");
    AssociationDelegate a_s_Test = new AssociationDelegate("dani" , "dani@gmail.com","123");

    @Before
    public void init() throws IncorrectInputException, AlreadyExistException, DontHavePermissionException {
        controller.signIn(a_s_Test.getName(),a_s_Test.getUserMail(),a_s_Test.getPassword());
    }
    @Test
    public void getUserMail() {
    }

    @Test
    public void getPassword() {
    }

    @Test
    public void setUserMail() {
    }

    @Test
    public void setPassword() {
    }

    @Test
    public void getName() {
    }

    @Test
    public void addLeagueAlreadyExistException() throws AlreadyExistException, DontHavePermissionException {
        /* UC 19 (noa) */
        /*init*/
        League league1 = new League("champions");
        League league2 = new League("league2");
//        controller.addLeague(league1);
//        controller.addLeague(league2);

 //       controller.setLeague(a_s_Test , league1);

        //todo

    }

    @Test
    public void setLeagueByYear() {
        //UC20





    }

    @Test
    public void signRefereeToSeason() {
    }

    @Test
    public void insertSchedulingPolicy() {
    }

    @Test
    public void changeScorePolicy() {
    }
}
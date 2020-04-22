package Users;

import org.junit.Test;
import system.SystemController;
import Exception.*;
import static org.junit.Assert.*;

public class MainRefereeTest {
    SystemController controller = new SystemController("test controller");

    public MainRefereeTest() throws DontHavePermissionException, AlreadyExistException, MemberNotExist, IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberNotExist, IncorrectInputException {
    }

    @Test
    public void printGameSchedule() {
    }

    @Test
    public void updateDetails() {
        // todo - UC 3 test - noa
        //  check empty list , check 5 hours , check notApprove button ,
        //  check Approve button - with correct events , check incorrect events
    }

    @Test
    public void deleteTheGames() {
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
    public void updateGameEvent() {
    }
}
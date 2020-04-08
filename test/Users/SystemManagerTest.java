package Users;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import Exception.*;
import org.junit.rules.ExpectedException;
import system.SystemController;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class SystemManagerTest {
    SystemController controller = new SystemController("");
    SystemManager systemManager = new SystemManager("Shachar","shachar@gmail.com","123",controller);

    @Before
    public void init(){
        this.controller.addSystemManager(systemManager);
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
    public void viewSystemInformation() {
    }

    @Test
    public void schedulingGames() {
        //todo - UC4 noa

    }

    @Test
    public void addNewTeam() {
    }

    @Test
    public void removeReferee() {
    }

    @Test
    public void addReferee() throws MemberNotSystemManager {

       /*try to add referee who doesnt exist in the system - result should be positive*/
        assertFalse(controller.ifMemberExistTesting("referee@gmail.com"));
        int sizeBefore = controller.sizeOfMembersListTesting();
        controller.addRefree(systemManager.getUserMail(),"referee@gmail.com",false);
        assertTrue(controller.ifMemberExistTesting("referee@gmail,com"));
        assertEquals(sizeBefore+1,controller.sizeOfMembersListTesting());

        /*try to add referee who exist in the system - result should be negative*/
        Fan refereeToAdd = new Fan("refereeError","referee@gmail.com","123");
        controller.addMemberTesting(refereeToAdd);
        assertTrue(controller.ifMemberExistTesting(refereeToAdd.getUserMail()));
        sizeBefore = controller.sizeOfMembersListTesting();
        controller.addRefree(systemManager.getUserMail(),refereeToAdd.getUserMail(),false);
        assertEquals(sizeBefore,controller.sizeOfMembersListTesting());
        assertThat(controller.getRefree(refereeToAdd.getUserMail()), instanceOf(Fan.class));

        //todo -  מבחני קבלה בוורד לא ברורים לי, משתמש הרשום במערכת כאוהד לא יכול להיות שופט? יש למשתמש מנהל מערכת הרשאות לבצע מינוי של שופטים?
    }
    @Rule
    public final ExpectedException thrown= ExpectedException.none();
    @Test
    public void addRefereeWithException() throws IncorrectInputException, MemberNotSystemManager {
        thrown.expect(IncorrectInputException.class);
        int sizeBefore = this.controller.sizeOfMembersListTesting();

        /*try to add referee with incorrect mail*/
        controller.addRefree(systemManager.getUserMail(),"refere",false);
        assertEquals(sizeBefore,controller.sizeOfMembersListTesting());
        assertFalse(controller.ifMemberExistTesting("refere"));
    }

    @Test
    public void closeTeam() throws MemberNotSystemManager {
        /* init */
        controller.addTeam(systemManager.getUserMail(),null,null,null,null,"Beer Sheva");
        controller.closeTeam(systemManager.getUserMail(),"Beer Sheva");
        //todo

    }

    @Test
    public void removeMember() throws MemberNotSystemManager {
        /*init*/
        Referee refereeToRemove = new SecondaryReferee("referee","referee@gmail,com","123","");
        controller.addSystemManager(this.systemManager);
        controller.addMemberTesting(refereeToRemove);
        //controller.addRefree(systemManager.getUserMail(),refereeToRemove.getUserMail(),false);
        //todo - remove the "//" after fixing addReferee()
        assertTrue(controller.ifMemberExistTesting(refereeToRemove.getUserMail()));
        int size = controller.sizeOfMembersListTesting();

        /*try to remove referee who exist in the system*/
        controller.removeReferee(systemManager.getUserMail(),refereeToRemove.getUserMail());
        assertFalse(controller.ifMemberExistTesting(refereeToRemove.getUserMail()));
        assertEquals(size-1,controller.sizeOfMembersListTesting());

        /*try to remove referee who doesnt exist in the system*/
        controller.removeReferee(systemManager.getUserMail(),"ss");
        assertEquals(size-1,controller.sizeOfMembersListTesting());

    }
    @Rule
    public final ExpectedException thrown2= ExpectedException.none();
    @Test
    public void removeMemberWithManagerException() throws MemberNotSystemManager {
        thrown2.expect(MemberNotSystemManager.class);

        /*try to remove referee with member who not systemManager  */
        SystemManager systemManagertest = new SystemManager("shachar","shachar@gmail.com","123",controller);
        Owner owner = new Owner("owner","owner@gmail.com","123", controller);
        controller.removeReferee(owner.getUserMail(),"id");
    }

    @Test
    public void watchComplaint() {
    }

    @Test
    public void responseComplaint() {
    }

    @Test
    public void readLineByLine() {
    }
}
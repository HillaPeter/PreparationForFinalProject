package Users;

import Asset.Coach;
import Asset.Manager;
import Asset.Player;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import Exception.*;
import org.junit.rules.ExpectedException;
import system.SystemController;

import java.util.LinkedList;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class SystemManagerTest {
    SystemController controller = new SystemController("");
    @Before
    public void init() throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberNotExist, PasswordDontMatchException {
        controller.signIn("palyer0","p0@gmail.com","1");
        controller.signIn("palyer1","p1@gmail.com","1");
        controller.signIn("palyer2","p2@gmail.com","1");
        controller.signIn("palyer3","p3@gmail.com","1");
        controller.signIn("palyer4","p4@gmail.com","1");
        controller.signIn("palyer5","p5@gmail.com","1");
        controller.signIn("palyer6","p6@gmail.com","1");
        controller.signIn("palyer7","p7@gmail.com","1");
        controller.signIn("palyer8","p8@gmail.com","1");
        controller.signIn("palyer9","p9@gmail.com","1");
        controller.signIn("palyer10","p10@gmail.com","1");
        controller.signIn("coach","coach@gmail.com","1");
        controller.signIn("manager","manager@gmail.com","1");
        controller.signIn("owner","owner@gmail.com","1");

    }
    @Rule
    public final ExpectedException thrown= ExpectedException.none();
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
    public void addNewTeam() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException {
        controller.logIn("admin@gmail.com","123");
        LinkedList<String> idPlayers = new LinkedList<>();
        idPlayers.add("p0@gmail.com");
        idPlayers.add("p1@gmail.com");
        idPlayers.add("p2@gmail.com");
        idPlayers.add("p3@gmail.com");
        idPlayers.add("p4@gmail.com");
        idPlayers.add("p5@gmail.com");
        idPlayers.add("p6@gmail.com");
        idPlayers.add("p7@gmail.com");
        idPlayers.add("p8@gmail.com");
        idPlayers.add("p9@gmail.com");
        idPlayers.add("p10@gmail.com");
        LinkedList<String> idcoach = new LinkedList<>();
        idcoach.add("coach0@gmail.com");
        LinkedList<String> idmanager = new LinkedList<>();
        idmanager.add("manager@gmail.com");
        LinkedList<String> idowner = new LinkedList<>();
        idowner.add("owner@gmail.com");
        int sizeBefore = this.controller.getRoles().size();

        controller.addTeam(idPlayers,idcoach,idmanager,idowner,"newTeam");
        assertTrue(controller.getTeams().containsKey("newTeam"));
        assertThat(controller.getRoles().get("manager@gmail.com"), instanceOf(Manager.class));
        assertThat(controller.getRoles().get("owner@gmail.com"), instanceOf(Owner.class));
        assertThat(controller.getRoles().get("coach@gmail.com"), instanceOf(Coach.class));
        assertThat(controller.getRoles().get("p0@gmail.com"), instanceOf(Player.class));
        assertThat(controller.getRoles().get("p1@gmail.com"), instanceOf(Player.class));
        assertThat(controller.getRoles().get("p2@gmail.com"), instanceOf(Player.class));
        assertThat(controller.getRoles().get("p3@gmail.com"), instanceOf(Player.class));
        assertThat(controller.getRoles().get("p4@gmail.com"), instanceOf(Player.class));
        assertThat(controller.getRoles().get("p5@gmail.com"), instanceOf(Player.class));
        assertThat(controller.getRoles().get("p6@gmail.com"), instanceOf(Player.class));
        assertThat(controller.getRoles().get("p7@gmail.com"), instanceOf(Player.class));
        assertThat(controller.getRoles().get("p8@gmail.com"), instanceOf(Player.class));
        assertThat(controller.getRoles().get("p9@gmail.com"), instanceOf(Player.class));
        assertThat(controller.getRoles().get("p10@gmail.com"), instanceOf(Player.class));
        assertEquals(sizeBefore,controller.getRoles().size());
    }
    @Test
    public void addNewTeamNotValidPlayers() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException {
        thrown.expect(IncorrectInputException.class);
        controller.logIn("admin@gmail.com","123");
        LinkedList<String> idPlayers = new LinkedList<>();
        idPlayers.add("p0@gmail.com");
        idPlayers.add("p1@gmail.com");
        idPlayers.add("p2@gmail.com");
        idPlayers.add("p3@gmail.com");
        idPlayers.add("p4@gmail.com");
        idPlayers.add("p5@gmail.com");
        idPlayers.add("p6@gmail.com");
        idPlayers.add("p7@gmail.com");
        idPlayers.add("p8@gmail.com");
        idPlayers.add("p9@gmail.com");
        LinkedList<String> idcoach = new LinkedList<>();
        idcoach.add("coach0@gmail.com");
        LinkedList<String> idmanager = new LinkedList<>();
        idmanager.add("manager@gmail.com");
        LinkedList<String> idowner = new LinkedList<>();
        idowner.add("owner@gmail.com");
        int sizeBefore = this.controller.getRoles().size();

        /*try to add team without 11 player*/
        controller.addTeam(idPlayers,idcoach,idmanager,idowner,"newTeam");
        assertFalse(controller.getTeams().containsKey("newTeam"));
        assertThat(controller.getRoles().get("manager@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("owner@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("coach@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p0@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p1@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p2@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p3@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p4@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p5@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p6@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p7@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p8@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p9@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p10@gmail.com"), instanceOf(Fan.class));
        assertEquals(sizeBefore,controller.getRoles().size());
    }
    @Test
    public void addNewTeamNoPermission() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException {
        thrown.expect(DontHavePermissionException.class);
        controller.logIn("admin@gmail.com","123");
        LinkedList<String> idPlayers = new LinkedList<>();
        idPlayers.add("p0@gmail.com");
        idPlayers.add("p1@gmail.com");
        idPlayers.add("p2@gmail.com");
        idPlayers.add("p3@gmail.com");
        idPlayers.add("p4@gmail.com");
        idPlayers.add("p5@gmail.com");
        idPlayers.add("p6@gmail.com");
        idPlayers.add("p7@gmail.com");
        idPlayers.add("p8@gmail.com");
        idPlayers.add("p9@gmail.com");
        idPlayers.add("p10@gmail.com");
        LinkedList<String> idcoach = new LinkedList<>();
        idcoach.add("coach0@gmail.com");
        LinkedList<String> idmanager = new LinkedList<>();
        idmanager.add("manager@gmail.com");
        LinkedList<String> idowner = new LinkedList<>();
        idowner.add("owner@gmail.com");
        int sizeBefore = this.controller.getRoles().size();

        controller.addTeam(idPlayers,idcoach,idmanager,idowner,"newTeam");
        assertEquals(sizeBefore,controller.getRoles().size());
    }
    @Test
    public void addNewTeamExistingTeam() throws DontHavePermissionException {
        thrown.expect(ObjectAlreadyExist.class);
        LinkedList<String> idPlayers = new LinkedList<>();
        idPlayers.add("p0@gmail.com");
        idPlayers.add("p1@gmail.com");
        idPlayers.add("p2@gmail.com");
        idPlayers.add("p3@gmail.com");
        idPlayers.add("p4@gmail.com");
        idPlayers.add("p5@gmail.com");
        idPlayers.add("p6@gmail.com");
        idPlayers.add("p7@gmail.com");
        idPlayers.add("p8@gmail.com");
        idPlayers.add("p9@gmail.com");
        idPlayers.add("p10@gmail.com");
        LinkedList<String> idcoach = new LinkedList<>();
        idcoach.add("coach0@gmail.com");
        LinkedList<String> idmanager = new LinkedList<>();
        idmanager.add("manager@gmail.com");
        LinkedList<String> idowner = new LinkedList<>();
        idmanager.add("owner@gmail.com");
        int sizeBefore = this.controller.getRoles().size();

        controller.addTeam(idPlayers,idcoach,idmanager,idowner,"newTeam");

        /* try to add team with existing name - result should be negative */
        controller.addTeam(idPlayers,idcoach,idmanager,idowner,"newTeam");
    }
    @Test
    public void addNewTeamNameNotGood() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException {
        thrown.expect(IncorrectInputException.class);
        controller.logIn("admin@gmail.com","123");
        LinkedList<String> idPlayers = new LinkedList<>();
        idPlayers.add("p0@gmail.com");
        idPlayers.add("p1@gmail.com");
        idPlayers.add("p2@gmail.com");
        idPlayers.add("p3@gmail.com");
        idPlayers.add("p4@gmail.com");
        idPlayers.add("p5@gmail.com");
        idPlayers.add("p6@gmail.com");
        idPlayers.add("p7@gmail.com");
        idPlayers.add("p8@gmail.com");
        idPlayers.add("p9@gmail.com");
        LinkedList<String> idcoach = new LinkedList<>();
        idcoach.add("coach0@gmail.com");
        LinkedList<String> idmanager = new LinkedList<>();
        idmanager.add("manager@gmail.com");
        LinkedList<String> idowner = new LinkedList<>();
        idowner.add("owner@gmail.com");
        int sizeBefore = this.controller.getRoles().size();

        /*try to add team with teamName contains only numbers */
        controller.addTeam(idPlayers,idcoach,idmanager,idowner,"1234");
        assertTrue(controller.getTeams().containsKey("newTeam"));
        assertThat(controller.getRoles().get("manager@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("owner@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("coach@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p0@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p1@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p2@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p3@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p4@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p5@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p6@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p7@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p8@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p9@gmail.com"), instanceOf(Fan.class));
        assertThat(controller.getRoles().get("p10@gmail.com"), instanceOf(Fan.class));
        assertEquals(sizeBefore,controller.getRoles().size());
    }
    @Test
    public void removeReferee() {
    }

    @Test
    public void addReferee() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException {
        controller.logIn("admin@gmail.com","123");
        int sizeBefore = controller.getRoles().size();

        /*try to add referee who doesnt exist in the system - result should be positive*/
        assertTrue(controller.addReferee("p0@gmail.com",false));
        assertEquals(sizeBefore,controller.getRoles().size());
        assertTrue(controller.getRoles().containsKey("p0@gmail.com"));
        assertThat(controller.getRoles().get("p0@gmail.com"), instanceOf(SecondaryReferee.class));

    }
    @Test
    public void addRefereeNotexist() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException {
        thrown.expect(MemberNotExist.class);
        controller.logIn("admin@gmail.com","123");
        int sizeBefore = controller.getRoles().size();

        /*try to add referee who doesnt exist in the system - result should be Negative*/
        assertFalse(controller.addReferee("referee@gmail.com",false));
        assertEquals(sizeBefore,controller.getRoles().size());
    }
    @Test
    public void addRefereeNoPermission() throws DontHavePermissionException {
        thrown.expect(DontHavePermissionException.class);
       // int sizeBefore = this.controller.getRoles().size();

        /*try to add referee with incorrect mail*/
        assertFalse(controller.addReferee("refere@gmail.com",false));
       // assertEquals(sizeBefore,controller.getRoles().size());
    }

    @Test
    public void closeTeam()  {

    }

    @Test
    public void removeMember()  {
//        /*init*/
//        Referee refereeToRemove = new SecondaryReferee("referee","referee@gmail,com","123","");
//        controller.addSystemManager(this.systemManager);
//        controller.addMemberTesting(refereeToRemove);
//        //controller.addReferee(systemManager.getUserMail(),refereeToRemove.getUserMail(),false);
//        //todo - remove the "//" after fixing addReferee()
//        assertTrue(controller.ifMemberExistTesting(refereeToRemove.getUserMail()));
//        int size = controller.sizeOfMembersListTesting();
//
//        /*try to remove referee who exist in the system*/
//        controller.removeReferee(systemManager.getUserMail(),refereeToRemove.getUserMail());
//        assertFalse(controller.ifMemberExistTesting(refereeToRemove.getUserMail()));
//        assertEquals(size-1,controller.sizeOfMembersListTesting());
//
//        /*try to remove referee who doesnt exist in the system*/
//        controller.removeReferee(systemManager.getUserMail(),"ss");
//        assertEquals(size-1,controller.sizeOfMembersListTesting());

    }
    @Test
    public void removeMemberWithManagerException()  {
//        thrown2.expect(MemberNotSystemManager.class);
//
//        /*try to remove referee with member who not systemManager  */
//        SystemManager systemManagertest = new SystemManager("shachar","shachar@gmail.com","123",controller);
//        Owner owner = new Owner("owner","owner@gmail.com","123", controller);
//        controller.removeReferee(owner.getUserMail(),"id");
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
package Users;

import Asset.Coach;
import Asset.Manager;
import Asset.Player;
import Game.Game;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import Exception.*;
import org.junit.rules.ExpectedException;
import system.DBController;
import system.SystemController;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class SystemManagerTest {
    SystemController controller = new SystemController("");
    Date birthdate=new Date(1993,10,12);


   // private SystemManager systemManager=new SystemManager("for test" , "for Test" , "fortest" , new DBController());

    @Before
    public void init() throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberNotExist, PasswordDontMatchException {
        controller.signIn("palyer0","p0@gmail.com","1", birthdate);
        controller.signIn("palyer1","p1@gmail.com","1", birthdate);
        controller.signIn("palyer2","p2@gmail.com","1", birthdate);
        controller.signIn("palyer3","p3@gmail.com","1", birthdate);
        controller.signIn("palyer4","p4@gmail.com","1", birthdate);
        controller.signIn("palyer5","p5@gmail.com","1", birthdate);
        controller.signIn("palyer6","p6@gmail.com","1", birthdate);
        controller.signIn("palyer7","p7@gmail.com","1", birthdate);
        controller.signIn("palyer8","p8@gmail.com","1", birthdate);
        controller.signIn("palyer9","p9@gmail.com","1", birthdate);
        controller.signIn("palyer10","p10@gmail.com","1", birthdate);
        controller.signIn("coach","coach@gmail.com","1", birthdate);
        controller.signIn("manager","manager@gmail.com","1", birthdate);
        controller.signIn("owner","owner@gmail.com","1", birthdate);
    }
    @Rule
    public final ExpectedException thrown= ExpectedException.none();
    /*******************************************************************************/
    @Test
    public void schedulingGames() {
        //todo - UC4 noa
    }
    /*******************************************************************************/
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
        idcoach.add("coach@gmail.com");
        LinkedList<String> idmanager = new LinkedList<>();
        idmanager.add("manager@gmail.com");
        LinkedList<String> idowner = new LinkedList<>();
        idowner.add("owner@gmail.com");
        int sizeBefore = this.controller.getRoles().size();
        int sizeBeforeT= this.controller.getTeams().size();

        /* try to add team - result should be positive */
        //todo - after changing addTeam remove"//"
       // controller.addTeam(idPlayers,idcoach,idmanager,idowner,"newTeam");
        //assertTrue(controller.getTeams().containsKey("newTeam"));
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
        assertEquals(sizeBeforeT+1 ,controller.getTeams().size());

        assertTrue(controller.getTeams().containsKey("newTeam"));
        assertTrue(((Coach)this.controller.getRoles().get("coach0@gmail.com")).getTeam().containsKey("newTeam"));
        assertTrue(((Manager)this.controller.getRoles().get("manager@gmail.com")).getTeam().containsKey("newTeam"));
        assertTrue(((Owner)this.controller.getRoles().get("owner@gmail.com")).getTeams().containsKey("newTeam"));
        for (String idPlayer : idPlayers) {
            assertTrue(((Player)this.controller.getRoles().get(idPlayer)).getTeam().containsKey("newTeam"));
        }
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
        //todo - after changing addTeam remove"//"
        //controller.addTeam(idPlayers,idcoach,idmanager,idowner,"newTeam");
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

        //todo - after changing addTeam remove"//"
     //   controller.addTeam(idPlayers,idcoach,idmanager,idowner,"newTeam");
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
        //todo - after changing addTeam remove"//"
   //     controller.addTeam(idPlayers,idcoach,idmanager,idowner,"newTeam");

        /* try to add team with existing name - result should be negative */
        //todo - after changing addTeam remove"//"
//        controller.addTeam(idPlayers,idcoach,idmanager,idowner,"newTeam");
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
        //todo - after changing addTeam remove"//"
        //controller.addTeam(idPlayers,idcoach,idmanager,idowner,"1234");
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
    /*******************************************************************************/
    @Test
    public void removeReferee() throws MemberNotExist, PasswordDontMatchException, IncorrectInputException, DontHavePermissionException, AlreadyExistException {
        /* init */
        controller.signIn("referee","referee@gmail.com","123", birthdate);
        controller.logOut();
        controller.logIn("admin@gmail.com","123");
        controller.addReferee("referee@gmail.com",false);
        int sizeBefore = controller.getRoles().size();

        Referee referee = (Referee) controller.getRoles().get("referee@gmail.com");

        HashSet<Game> games = referee.getGameSchedule();
        boolean refereeNotInGame = true;


        /* try to remove referee who already exist in the system - result should be positive*/
        controller.removeReferee("referee@gmail.com");
        assertEquals(sizeBefore-1,controller.getRoles().size());
        for(Game game: games){
            if(game.isRefereeInTheGame(referee)){
                refereeNotInGame = false;
            }
        }
        assertTrue(refereeNotInGame);

    }
    @Test
    public void removeRefereeNotExist() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException {
        thrown.expect(MemberNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        int sizeBefore = controller.getRoles().size();


        /*try to remove referee who doesnt exist in the system - result should be Negative*/
        assertFalse(controller.removeReferee("referee@gmail.com"));
        assertEquals(sizeBefore,controller.getRoles().size());
    }
    @Test
    public void removeRefereeNoPermission() throws DontHavePermissionException {
        thrown.expect(DontHavePermissionException.class);
        // int sizeBefore = this.controller.getRoles().size();

        /* try to remove referee without login with systemManager */
        assertFalse(controller.addReferee("refere@gmail.com",false));
        // assertEquals(sizeBefore,controller.getRoles().size());
    }
    /*******************************************************************************/
    @Test
    public void addReferee() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException {
        controller.logIn("admin@gmail.com","123");
        int sizeBefore = controller.getRoles().size();

        /*try to add referee who doesnt exist in the system - result should be positive*/
        assertTrue(controller.addReferee("p0@gmail.com",false));
        assertTrue(sizeBefore+1 == controller.getRoles().size());
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
    /*******************************************************************************/
    @Test
    public void closeTeam() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException {
        /* init */
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
        //todo - after fixing addTeam - remove "//"
       // controller.addTeam(idPlayers,idcoach,idmanager,idowner,"newTeam");
        int sizeBefore = this.controller.getTeams().size();

        /* try to close team - result should be positive */
        controller.closeTeam("newTeam");

        assertEquals(sizeBefore-1,controller.getTeams().size());
        assertFalse(controller.getTeams().containsKey("newTeam"));
        assertFalse(((Coach)this.controller.getRoles().get("coach0@gmail.com")).getTeam().containsKey("newTeam"));
        assertFalse(((Manager)this.controller.getRoles().get("manager@gmail.com")).getTeam().containsKey("newTeam"));
        assertFalse(((Owner)this.controller.getRoles().get("owner@gmail.com")).getTeams().containsKey("newTeam"));
        for (String idPlayer : idPlayers) {
            assertFalse(((Player)this.controller.getRoles().get(idPlayer)).getTeam().containsKey("newTeam"));
        }
    }
    @Test
    public void closeTeamNoPermission() throws DontHavePermissionException {
        thrown.expect(DontHavePermissionException.class);
        /* try to close team without login  - result should be negative */
        controller.closeTeam("newTeam");
    }
    @Test
    public void closeTeamNotExistingTeam() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");

        /* try to close team with wrong name - result should be negative */
        controller.closeTeam("newTeam");
    }
    /*******************************************************************************/
    @Test
    public void removeMember() throws MemberNotExist, PasswordDontMatchException, IncorrectInputException, DontHavePermissionException, AlreadyExistException {
        /*init*/
        controller.signIn("member","member@gmail.com","123",  birthdate);
        controller.logIn("admin@gmail.com","123");

        assertTrue(controller.getRoles().containsKey("member@gmail.com"));
        int size = controller.getRoles().size();

        /* try to remove member who exist in the system - result should be positive*/
        controller.removeMember("member@gmail.com");
        assertFalse(controller.getRoles().containsKey("member@gmail.com"));
        assertEquals(size-1,controller.getRoles().size());
    }
    @Test
    public void removeMemberNoPermissionException() throws DontHavePermissionException {
        thrown.expect(DontHavePermissionException.class);

        /* try to remove member without login  - result should be negative */
        controller.removeMember("member@gmail.com");
    }
    @Test
    public void removeMemberNotExist() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException {
        thrown.expect(MemberNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        int sizeBefore = controller.getRoles().size();

        /*try to remove member who not exist in the system - result should be negative*/
        controller.removeMember("member@gmail.com");
        assertEquals(sizeBefore,controller.getRoles().size());
    }
    /*******************************************************************************/
    @Test
    public void watchComplaint() {
    }
    /*******************************************************************************/
    @Test
    public void responseComplaint() {
    }
    /*******************************************************************************/
    @Test
    public void readLineByLine() {
    }
}
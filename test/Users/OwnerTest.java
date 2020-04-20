package Users;

import Asset.Coach;
import Asset.Field;
import Asset.Manager;
import Asset.Player;
import Game.Account;
import Game.Team;
import Game.Transaction;
import Exception.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import system.DBController;
import system.SystemController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class OwnerTest {
    SystemController controller = new SystemController("");
    private SystemManager systemManager=new SystemManager("for test" , "for Test" , "fortest" , new DBController());

    LinkedList<String> idPlayers = new LinkedList<>();
    LinkedList<String> idcoach = new LinkedList<>();
    LinkedList<String> idmanager = new LinkedList<>();
    LinkedList<String> idowner = new LinkedList<>();

    @Before
    public void init() throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberNotExist, PasswordDontMatchException {

        /*add Team*/
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

        idcoach.add("coach0@gmail.com");
        idmanager.add("manager@gmail.com");
        idowner.add("owner@gmail.com");
    }

    @Rule
    public final ExpectedException thrown= ExpectedException.none();

    /************************************************addAsset-coach**************************************************/
    @Test
    public void addCoach() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, IncorrectInputException, PasswordDontMatchException {
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.signIn("c","c@gmail.com","123");
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",1000);


        /* try to add coach - with login result should be positive */
        controller.addCoach("team","c@gmail.com");
        assertThat(controller.getRoles().get("c@gmail.com") , instanceOf(Coach.class));
        assertTrue(((Coach)controller.getRoles().get("c@gmail.com")).getTeam().containsKey("team"));
        assertTrue(controller.getTeams().get("team").getCoaches().contains("c@gmail.com"));
        assertEquals(950,controller.getAccountBalance("team"));

    }
    @Test
    public void addCoachPremission() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException {
        thrown.expect(DontHavePermissionException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();

        /* try to add coach - without login result should be negative */
        controller.addCoach("team","c@gmail.com");
    }
    @Test
    public void addCoachNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException {
        thrown.expect(MemberNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        int sizeBefore = this.controller.getTeams().get("team").getCoaches().size();

        /* try to add coach - who not exist in the system result should be negative */
        controller.addCoach("team","coach@gmail.com");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getCoaches().size());
    }
    @Test
    public void addCoachTeamAlreadyExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException {
        thrown.expect(AlreadyExistException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        int sizeBefore = this.controller.getTeams().get("team").getCoaches().size();


        /* try to add coach who already exist in the team - result should be negative */
        controller.addCoach("team","c@gmail.com");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getCoaches().size());

    }
    @Test
    public void addCoachTeamNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        /* try to add coach - invalid team name result should be negative */
        controller.addCoach("teammm","coach@gmail.com");
    }
    @Test
    public void addCoachNotMoney() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(NoEnoughMoney.class);
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.signIn("c","c@gmail.com","123");
        controller.logIn("owner@gmail.com","1");

        //TODO - add amount to account

        /* try to add coach - with login result should be positive */
        controller.addCoach("team","c@gmail.com");
    }
    /************************************************addAsset-player**************************************************/
    @Test
    public void addPlayer() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, AlreadyExistException, ObjectNotExist {
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.signIn("p","newPlayer@gmail.com","123");
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",1000);


        /* try to add player - with login result should be positive */
        controller.addPlayer("newPlayer@gmail.com","team",2000,10,12,"");
        assertThat(controller.getRoles().get("newPlayer@gmail.com") , instanceOf(Player.class));
        assertTrue(((Player)controller.getRoles().get("newPlayer@gmail.com")).getTeam().containsKey("team"));
        assertTrue(controller.getTeams().get("team").getPlayers().contains("newPlayer@gmail.com"));
        assertEquals(950,controller.getAccountBalance("team"));
    }
    @Test
    public void addPlayerPremission() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(DontHavePermissionException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();

        /* try to add player - without login result should be negative */
        controller.addPlayer("newPlayer@gmail.com","team",2000,10,12,"");
    }
    @Test
    public void addPlayerNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(MemberNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        int sizeBefore = this.controller.getTeams().get("team").getPlayers().size();

        /* try to add player who not exist in the system - result should be negative */
        controller.addPlayer("newPlayer@gmail.com","team",2000,10,12,"");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getPlayers().size());
    }
    @Test
    public void addPlayerTeamAlreadyExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(AlreadyExistException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        int sizeBefore = this.controller.getTeams().get("team").getPlayers().size();


        /* try to add player who already exist in the team - result should be negative */
        controller.addPlayer("p0@gmail.com","team",2000,10,12,"");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getPlayers().size());

    }
    @Test
    public void addPlayerTeamNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        /* try to add player - invalid team name result should be negative */
        controller.addPlayer("newPlayer@gmail.com","team",2000,10,12,"");
    }
    @Test
    public void addPlayerNotMoney() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(NoEnoughMoney.class);
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.signIn("p","newPlayer@gmail.com","123");
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",40);
        int sizeBefore = this.controller.getTeams().get("team").getPlayers().size();


        /* try to add player - with login result should be positive */
        controller.addPlayer("newPlayer@gmail.com","team",2000,10,12,"");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getPlayers().size());

    }
    /************************************************addAsset-field**************************************************/
    @Test
    public void addField() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, AlreadyExistException, ObjectNotExist {
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.signIn("p","newPlayer@gmail.com","123");
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",1000);
        int sizeBefore = controller.getTeams().get("team").getTrainingFields().size();

        /* try to add field - with login result should be positive */
        controller.addField("team","f");
        boolean fieldExist = false;

        for( Field f : controller.getTeams().get("team").getTrainingFields()){
            if (f.getName().equals("f")){
                fieldExist = true;
            }
        }
        assertTrue(fieldExist);
        assertEquals(950,controller.getAccountBalance("team"));
        assertEquals(sizeBefore+1,controller.getTeams().get("team").getTrainingFields().size());

    }
    @Test
    public void addFieldPermission() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(DontHavePermissionException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();

        /* try to add field - without login result should be negative */
        controller.addField("team","f");
    }
    @Test
    public void addFieldTeamAlreadyExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(ObjectAlreadyExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.addField("team","f");

        int sizeBefore = this.controller.getTeams().get("team").getTrainingFields().size();

        /* try to add field who already exist in the team - result should be negative */
        controller.addField("team","f");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getTrainingFields().size());
    }
    @Test
    public void addFieldTeamNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        /* try to add field - invalid team name result should be negative */
        controller.addField("teammmm","f");
    }
    @Test
    public void addFieldNotMoney() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(NoEnoughMoney.class);
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.signIn("p","newPlayer@gmail.com","123");
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",40);
        int sizeBefore = this.controller.getTeams().get("team").getTrainingFields().size();


        /* try to add field with no enough money - result should be negative */
        controller.addField("team","f");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getTrainingFields().size());
    }

    /*********************************************removeAsset-coach*********************************************/
    @Test
    public void removeCoach() {
    }
    /*********************************************removeAsset-player*********************************************/
    @Test
    public void removePlayer() {
    }
    /*********************************************removeAsset-field*********************************************/
    @Test
    public void removeField() {
    }
    /*********************************************addNewManager***********************************************/
    @Test
    public void addNewManager() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, AlreadyExistException, ObjectNotExist, NoEnoughMoney {
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.signIn("M","newManager@gmail.com","123");
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",1000);

        /* try to add new manager - with login result should be positive */
        controller.addManager("team" , "newManager@gmail.com");
        assertThat(controller.getRoles().get("newManager@gmail.com") , instanceOf(Manager.class));
        Manager m = (Manager)controller.getRoles().get("newManager@gmail.com");
        assertTrue(m.getTeam().containsKey("team"));
        assertTrue(controller.getTeams().get("team").getManagers().contains(m));
        assertEquals(950 ,controller.getAccountBalance("team"));
    }
    @Test
    public void addNewManagerPermission() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(DontHavePermissionException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();

        /* try to add new manager - without login result should be negative */
        controller.addManager("team" , "newManager@gmail.com");
    }
    @Test
    public void addNewManagerNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(MemberNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        int sizeBefore = this.controller.getTeams().get("team").getManagers().size();

        /* try to add new manager who not exist in the system - result should be negative */
        controller.addManager("team" , "newManager@gmail.com");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getManagers().size());
    }
    @Test
    public void addNewManagerTeamAlreadyExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(AlreadyExistException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        int sizeBefore = this.controller.getTeams().get("team").getManagers().size();


        /* try to add new manager who already exist in the team - result should be negative */
        controller.addManager("team","manager@gmail.com");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getManagers().size());

    }
    @Test
    public void addNewManagerTeamNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        /* try to add new manager - invalid team name result should be negative */
        controller.addManager("teammmm","manager@gmail.com");
    }
    @Test
    public void addNewManagerNoEnoughMoney() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(NoEnoughMoney.class);
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.signIn("M","newManager@gmail.com","123");
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",40);

        int sizeBefore = this.controller.getTeams().get("team").getManagers().size();

        /* try to add new manager - with not enough money-  result should be negative */
        controller.addManager("team" , "newManager@gmail.com");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getManagers().size());

    }

    /******************************************addNewOwner******************************************/
    @Test
    public void addNewOwner() {
    }
    /******************************************removeManager******************************************/
    @Test
    public void removeManager() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, AlreadyExistException, ObjectNotExist, NoEnoughMoney {
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.signIn("M","newManager@gmail.com","123");
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",1000);
        controller.addManager("team" , "newManager@gmail.com");
        Manager m = (Manager)controller.getRoles().get("newManager@gmail.com");

        /* try to remove manager - with login result should be positive */
        controller.removeManager("team" , "newManager@gmail.com");
        assertTrue(controller.getRoles().containsKey("newManager@gmail.com"));
        assertFalse(controller.getTeams().get("team").getManagers().contains(m));
        assertEquals(1000 ,controller.getAccountBalance("team"));
        assertFalse(m.getTeam().containsKey("team"));
    }
    @Test
    public void removeManagerPermission() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(DontHavePermissionException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();

        /* try to remove manager - without login result should be negative */
        controller.removeManager("team" , "newManager@gmail.com");
    }
    @Test
    public void removeManagerNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(MemberNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        int sizeBefore = this.controller.getTeams().get("team").getManagers().size();

        /* try to remove manager who not exist in the system/team - result should be negative */
        controller.removeManager("team" , "newManager@gmail.com");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getManagers().size());
    }
    @Test
    public void removeManagerTeamNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        /* try to remove manager - invalid team name result should be negative */
        controller.removeManager("teammmm","NewManager@gmail.com");
    }

    /******************************************temporaryTeamClosing******************************************/
    @Test
    public void temporaryTeamClosing() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist {
        /* init */
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        int sizeBefore = controller.getTeams().size();

        /* try to temporary close team - result positive*/
        controller.temporaryTeamClosing("team");
        assertTrue(controller.getTeams().containsKey("team"));
        assertFalse(controller.getTeams().get("team").getStatus());
        assertEquals(sizeBefore ,controller.getTeams().size());
    }
    @Test
    public void temporaryTeamClosinNoPremission() throws DontHavePermissionException, ObjectNotExist {
        /* init */
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();

        /* try to temporary close team without login - result negative*/
        controller.temporaryTeamClosing("team");
        assertTrue(controller.getTeams().get("team").getStatus());
    }
    @Test
    public void temporaryTeamClosingTeamNotExist1() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist {
        thrown.expect(ObjectNotExist.class);

        /* init */
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        /* try to close team who not exist - result negative*/
        controller.temporaryTeamClosing("teammmm");
        assertTrue(controller.getTeams().get("team").getStatus());

    }
    @Test
    public void temporaryTeamClosingUnavalableOption()  {
        /* init */

        /* try to close team who close already - result negative*/

    }

    /******************************************reopenClosedTeam******************************************/
    @Test
    public void reopenClosedTeam() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist {
        /* init */
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        int sizeBefore = controller.getTeams().size();

        /* try to reopen team - result positive*/
        controller.reopenClosedTeam("team");
        assertTrue(controller.getTeams().containsKey("team"));
        assertTrue(controller.getTeams().get("team").getStatus());
        assertEquals(sizeBefore ,controller.getTeams().size());
    }
    @Test
    public void reopenClosedTeamNoPremission() throws DontHavePermissionException, ObjectNotExist {
        /* init */
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();

        /* try to reopen team who open already - result negative*/
        controller.reopenClosedTeam("team");
        assertFalse(controller.getTeams().get("team").getStatus());
    }
    @Test
    public void reopenClosedTeamTeamNotExist1() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist {
        thrown.expect(ObjectNotExist.class);

        /* init */
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        /* try to reopen team who not exist - result negative*/
        controller.reopenClosedTeam("teammmm");
        assertFalse(controller.getTeams().get("team").getStatus());
    }
    @Test
    public void reopenClosedTeamUnavalableOption()  {
        /* init */

        /* try to reopen team who open already - result negative*/

    }
    /******************************************addIncome******************************************/
    @Test
    public void addIncome() {
    }

    @Test
    public void addOutCome() {
    }

    @Test
    public void setTeams() {
    }

    @Test
    public void getTeams() {
    }
}
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
import system.SystemController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class OwnerTest {
    SystemController controller = new SystemController("");

    LinkedList<String> idPlayers = new LinkedList<>();
    LinkedList<String> idcoach = new LinkedList<>();
    LinkedList<String> idmanager = new LinkedList<>();
    LinkedList<String> idowner = new LinkedList<>();
    Transaction transaction = new Transaction("Transaction", 445453);
    Account account0;
    ArrayList<Transaction> listTransactions = new ArrayList<>();
    Field field0 = new Field("");

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
        listTransactions.add(transaction);
    }

    @Rule
    public final ExpectedException thrown= ExpectedException.none();

    @Test
    public void addTeam() {
    }

    @Test
    public void removeTheTeamFromMyList() {
    }
    /****************************************************************************************************************/

    @Test
    public void addCoach() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, IncorrectInputException, PasswordDontMatchException {
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.signIn("c","c@gmail.com","123");
        controller.logIn("owner@gmail.com","1");


        /* try to add coach - with login result should be positive */
        controller.addCoach("team","c@gmail.com");
        assertThat(controller.getRoles().get("c@gmail.com") , instanceOf(Coach.class));
        assertTrue(((Coach)controller.getRoles().get("c@gmail.com")).getTeam().containsKey("team"));
        assertTrue(controller.getTeams().get("team").getCoaches().contains("c@gmail.com"));
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
    /****************************************************************************************************************/

    @Test
    public void addPlayer() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, AlreadyExistException, ObjectNotExist {
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.signIn("p","newPlayer@gmail.com","123");
        controller.logIn("owner@gmail.com","1");


        /* try to add player - with login result should be positive */
        controller.addPlayer("newPlayer@gmail.com","team",2000,10,12,"");
        assertThat(controller.getRoles().get("newPlayer@gmail.com") , instanceOf(Player.class));
        assertTrue(((Player)controller.getRoles().get("newPlayer@gmail.com")).getTeam().containsKey("team"));
        assertTrue(controller.getTeams().get("team").getPlayers().contains("newPlayer@gmail.com"));
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

    /****************************************************************************************************************/

    @Test
    public void addField() {
    }
    /****************************************************************************************************************/

    @Test
    public void updateAsset() {
    }

    @Test
    public void removeAsset() {
    }
    /******************************************addNewManager******************************************/
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

//    @Test
//    public void removeManagerTeamNotExist() throws OwnerNotExist, TeamNotExist, ManagerNotExist {
//        /* init */
//        thrown.expect(TeamNotExist.class);
//        account0 = new Account("Hapoel", listTransactions, 0);
//        Team team0 = new Team("Hapoel", account0, field0);
//        team0.setPlayers(players);
//        controller.addTeam(team0);
//
//        /* try to remove manager but owner is without team - result should be negative */
//        controller.removeManager(owner,team0,"");
//    }
//    @Test
//    public void removeManagerNotExistInTeam() throws OwnerNotExist, TeamNotExist , ManagerNotExist{
//        /* init */
//        thrown.expect(ManagerNotExist.class);
//        account0 = new Account("Hapoel", listTransactions, 0);
//        Team team0 = new Team("Hapoel", account0, field0);
//        team0.setPlayers(players);
//        Manager exist = new Manager("someone","some2@gmail.com","12");
//        Manager notExist = new Manager("not","not@gmail.com","12");
//
//        controller.addManager(exist);
//        controller.addManager(notExist);
//        controller.addTeam(team0);
//
//        owner.addTeam(team0);
//        owner.addNewManager(exist,team0);
//
//        /* try to remove manager who not exist in the team - result should be negative */
//        controller.removeManager(owner,team0,notExist.getUserMail());
//    }
//    @Test
//    public void removeManagerNotExistSInSystem() throws OwnerNotExist, TeamNotExist , ManagerNotExist{
//        /* init */
//        thrown.expect(ManagerNotExist.class);
//        account0 = new Account("Hapoel", listTransactions, 0);
//        Team team0 = new Team("Hapoel", account0, field0);
//        team0.setPlayers(players);
//        Manager exist = new Manager("someone","some2@gmail.com","12");
//        Manager notExist = new Manager("not","not@gmail.com","12");
//        controller.addTeam(team0);
//        owner.addTeam(team0);
//        team0.addManager(exist);
//
//        /* try to remove manager who not exist in the team - result should be negative */
//        controller.removeManager(owner,team0,notExist.getUserMail());
//    }
//    @Test
//    public void removeManager() throws OwnerNotExist, TeamNotExist , ManagerNotExist{
//        /* init */
//        account0 = new Account("Hapoel", listTransactions, 0);
//        Team team0 = new Team("Hapoel", account0, field0);
//        team0.setPlayers(players);
//        Manager manager = new Manager("someone","some2@gmail.com","12");
//        controller.addTeam(team0);
//        controller.addManager(manager);
//        owner.addTeam(team0);
//        owner.addNewManager(manager,team0);
//        int sizeBeforeTeams = manager.getTeam().size();
//        int sizeBeforeManagers = team0.getManagers().size();
//
//        /* try to remove manager - result should be positive */
//        controller.removeManager(owner,team0,manager.getUserMail());
//        assertFalse(manager.getTeam().containsKey(team0.getName()));
//        assertFalse(team0.isManager(manager));
//        assertEquals(sizeBeforeTeams-1 , manager.getTeam().size());
//        assertEquals(sizeBeforeManagers-1 , team0.getManagers().size());
//    }
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
    public void temporaryTeamClosinNoPremission() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist {
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
    public void reopenClosedTeamNoPremission() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist {
//        /* init */
//        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
//        controller.logOut();
//
//        /* try to reopen team who open already - result negative*/
//        controller.temporaryTeamClosing("team");
//        assertTrue(controller.getTeams().get("team").getStatus());
    }
    @Test
    public void reopenClosedTeamTeamNotExist1() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist {
//        thrown.expect(ObjectNotExist.class);
//
//        /* init */
//        controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
//        controller.logOut();
//        controller.logIn("owner@gmail.com","1");
//
//        /* try to close team who not exist - result negative*/
//        controller.temporaryTeamClosing("teammmm");
//        assertTrue(controller.getTeams().get("team").getStatus());

    }
    @Test
    public void reopenClosedTeamUnavalableOption()  {
        /* init */

        /* try to close team who close already - result negative*/

    }

//    @Test
//    public void reopenClosedTeamTeamNotExist1() throws UnavailableOption, TeamNotExist, OwnerNotExist {
//        /* init */
//        thrown.expect(TeamNotExist.class);
//        account0 = new Account("Hapoel", listTransactions, 0);
//        Team team0 = new Team("Hapoel", account0, field0);
//        Team team1 = new Team("Hapoel1", account0, field0);
//
//        team0.setPlayers(players);
//        controller.addTeam(team0);
//        owner.addTeam(team0);
//
//        /* try to reopen team who not exist - result negative*/
//        controller.reopenTeam(owner.getUserMail(),team1.getName());
//
//    }
//    @Test
//    public void reopenClosedTeamTeamNotExist2() throws UnavailableOption, TeamNotExist, OwnerNotExist {
//        /* init */
//        thrown.expect(TeamNotExist.class);
//        account0 = new Account("Hapoel", listTransactions, 0);
//        Team team0 = new Team("Hapoel", account0, field0);
//        Team team1 = new Team("Hapoel1", account0, field0);
//        team0.setPlayers(players);
//
//        controller.addTeam(team0);
//        controller.addTeam(team1);
//        owner.addTeam(team0);
//
//        /* try to reopen team who not exist in owner's teams- result negative*/
//        controller.reopenTeam(owner.getUserMail(),team1.getName());
//    }
//    @Test
//    public void reopenClosedUnavalableOption() throws UnavailableOption, TeamNotExist, OwnerNotExist {
//        /* init */
//        thrown.expect(UnavailableOption.class);
//        account0 = new Account("Hapoel", listTransactions, 0);
//        Team team0 = new Team("Hapoel", account0, field0);
//        team0.setPlayers(players);
//        team0.setStatus(true);
//        controller.addTeam(team0);
//        owner.addTeam(team0);
//
//        /* try to reopen team who open already - result negative*/
//        controller.reopenTeam(owner.getUserMail(),team0.getName());
//    }
//    @Test
//    public void reopenClosed() throws UnavailableOption, TeamNotExist, OwnerNotExist {
//        /* init */
//        account0 = new Account("Hapoel", listTransactions, 0);
//        Team team0 = new Team("Hapoel", account0, field0);
//        team0.setPlayers(players);
//        team0.setStatus(false);
//        controller.addTeam(team0);
//        owner.addTeam(team0);
//        int sizeBefore = this.owner.getTeams().size();
//
//        /* try to reopen team who open already - result negative*/
//        controller.reopenTeam(owner.getUserMail(),team0.getName());
//        assertTrue(team0.getStatus());
//        assertEquals(sizeBefore , owner.getTeams().size());
//        assertTrue(controller.existTeamName(team0.getName()));
//        assertTrue(owner.getTeams().containsKey(team0.getName()));
//        assertTrue(team0.isOwner(owner));
//    }
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
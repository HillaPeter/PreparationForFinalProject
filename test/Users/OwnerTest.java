package Users;

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

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class OwnerTest {
    SystemController controller = new SystemController("");
    Owner owner = new Owner("hila","hila@gmail.com","123",controller);
    HashSet<Player> players = new HashSet<>();
    Transaction transaction = new Transaction("Transaction", 445453);
    Account account0;
    ArrayList<Transaction> listTransactions = new ArrayList<>();
    Field field0 = new Field();

    @Before
    public void init(){
        controller.addOwner(owner);
        /*add Team*/
        Player player1 = new Player("yaara", "yaara@gmail.com", "yaara", new Date(1995, 1, 1), "player");
        Player player2 = new Player("daniel", "daniel@gmail.com", "daniel", new Date(1995, 1, 1), "player");
        Player player3 = new Player("may", "may@gmail.com", "may", new Date(1995, 1, 1), "player");
        Player player4 = new Player("noa", "noa@gmail.com", "noa", new Date(1995, 1, 1), "player");
        Player player5 = new Player("inbar", "inbar@gmail.com", "inbar", new Date(1995, 1, 1), "player");
        Player player6 = new Player("neta", "neta@gmail.com", "neta", new Date(1995, 1, 1), "player");
        Player player7 = new Player("ziv", "ziv@gmail.com", "ziv", new Date(1995, 1, 1), "player");
        Player player8 = new Player("neta", "neta@gmail.com", "neta", new Date(1995, 1, 1), "player");
        Player player9 = new Player("or", "or@gmail.com", "or", new Date(1995, 1, 1), "player");
        Player player10 = new Player("shoval", "shoval@gmail.com", "shoval", new Date(1995, 1, 1), "player");
        Player player11 = new Player("gal", "gal@gmail.com", "gal", new Date(1995, 1, 1), "player");

        controller.addPlayer(player1);
        controller.addPlayer(player2);
        controller.addPlayer(player3);
        controller.addPlayer(player4);
        controller.addPlayer(player5);
        controller.addPlayer(player6);
        controller.addPlayer(player7);
        controller.addPlayer(player8);
        controller.addPlayer(player9);
        controller.addPlayer(player10);
        controller.addPlayer(player11);

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        players.add(player6);
        players.add(player7);
        players.add(player8);
        players.add(player9);
        players.add(player10);
        players.add(player11);

        listTransactions.add(transaction);
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
    public void addTeam() {
    }

    @Test
    public void removeTheTeamFromMyList() {
    }

    @Test
    public void addAsset() {
    }

    @Test
    public void addCoach() {
    }

    @Test
    public void addPlayer() {
    }

    @Test
    public void addField() {
    }

    @Test
    public void updateAsset() {
    }

    @Test
    public void removeAsset() {
    }
    /******************************************addNewManager******************************************/

    @Test
    public void addNewManagerNoEnoughMoney() throws NoEnoughMoney, TeamNotExist, OwnerNotExist {
        thrown.expect(NoEnoughMoney.class);
        account0 = new Account("Hapoel", listTransactions, 0);
        Team team0 = new Team("Hapoel", account0, field0);
        team0.setPlayers(players);
        controller.addTeam(team0);
        Fan someone = new Fan("someone","some@gmail.com","12");
        controller.addFan(someone);

        /*try to add manager with no money in the account  - result should be negative*/
        controller.addManager(owner,team0,someone,"some@gmail.com");
    }
    @Test
    public void addNewManagerTeamNotExist() throws NoEnoughMoney, TeamNotExist, OwnerNotExist {
        thrown.expect(TeamNotExist.class);
        account0 = new Account("Hapoel", listTransactions, 0);
        Team team0 = new Team("Hapoel", account0, field0);
        team0.setPlayers(players);
        Fan someone = new Fan("someone","some@gmail.com","12");
        controller.addFan(someone);
        Team team1 = new Team("HapoelNN", account0, field0);
        team1.setPlayers(players);
        controller.addTeam(team1);

        /*try to add manager but team doesnt exist  - result should be negative*/
        controller.addManager(owner,team0,someone,"some@gmail.com");
    }
    @Test
    public void addNewManagerOwnerNotExist() throws NoEnoughMoney, TeamNotExist, OwnerNotExist {
        thrown.expect(OwnerNotExist.class);
        account0 = new Account("Hapoel", listTransactions, 0);
        Team team0 = new Team("Hapoel", account0, field0);
        team0.setPlayers(players);
        Fan someone = new Fan("someone","some@gmail.com","12");
        controller.addFan(someone);
        controller.addTeam(team0);
        Owner notExist = new Owner("not","notExist@gmail.com","not",controller);

        /*try to add manager , owner not exist  - result should be negative*/
        controller.addManager(notExist,team0,someone,"some@gmail.com");
    }
    @Test
    public void addNewManager1() throws NoEnoughMoney, TeamNotExist, OwnerNotExist {
        account0 = new Account("Hapoel", listTransactions, 0);
        Team team0 = new Team("Hapoel", account0, field0);
        team0.setPlayers(players);
        Fan someone = new Fan("someone","some@gmail.com","12");
        controller.addFan(someone);
        controller.addTeam(team0);
        int sizeBefore = controller.sizeOfMembersListTesting();


        /*try to add manager - result should be positive*/
        controller.addManager(owner,team0,someone,"some@gmail.com");
        assertTrue(controller.ifMemberExistTesting("some@gmail.com"));
        assertEquals(sizeBefore ,controller.sizeOfMembersListTesting());
        assertThat(controller.getMember("some@gmail.com"),instanceOf(Manager.class));
        assertNotNull(controller.getManager("some@gmail.com"));
    }
    @Test
    public void addNewManager2() throws NoEnoughMoney, TeamNotExist, OwnerNotExist {
        /* init */
        account0 = new Account("Hapoel", listTransactions, 0);
        Team team0 = new Team("Hapoel", account0, field0);
        team0.setPlayers(players);
        Manager exist = new Manager("someone","some2@gmail.com","12");
        controller.addManager(exist);
        controller.addTeam(team0);
        int sizeBefore = controller.sizeOfMembersListTesting();

        /* try to add manager who already is a manager - result should be negative */
        controller.addManager(owner,team0,exist,exist.getUserMail());
        assertTrue(controller.ifMemberExistTesting(exist.getUserMail()));
        assertEquals(sizeBefore ,controller.sizeOfMembersListTesting());
        assertThat(controller.getMember(exist.getUserMail()),instanceOf(Manager.class));
        assertFalse(team0.isManager(exist));
    }
    /******************************************addNewOwner******************************************/

    @Test
    public void addNewOwner() {
    }
    /******************************************removeManager******************************************/

    @Test
    public void removeManagerTeamNotExist() throws OwnerNotExist, TeamNotExist, ManagerNotExist {
        /* init */
        thrown.expect(TeamNotExist.class);
        account0 = new Account("Hapoel", listTransactions, 0);
        Team team0 = new Team("Hapoel", account0, field0);
        team0.setPlayers(players);
        controller.addTeam(team0);

        /* try to remove manager but owner is without team - result should be negative */
        controller.removeManager(owner,team0,"");
    }
    @Test
    public void removeManagerNotExistInTeam() throws OwnerNotExist, TeamNotExist , ManagerNotExist{
        /* init */
        thrown.expect(ManagerNotExist.class);
        account0 = new Account("Hapoel", listTransactions, 0);
        Team team0 = new Team("Hapoel", account0, field0);
        team0.setPlayers(players);
        Manager exist = new Manager("someone","some2@gmail.com","12");
        Manager notExist = new Manager("not","not@gmail.com","12");

        controller.addManager(exist);
        controller.addManager(notExist);
        controller.addTeam(team0);

        owner.addTeam(team0);
        owner.addNewManager(exist,team0);

        /* try to remove manager who not exist in the team - result should be negative */
        controller.removeManager(owner,team0,notExist.getUserMail());
    }
    @Test
    public void removeManagerNotExistSInSystem() throws OwnerNotExist, TeamNotExist , ManagerNotExist{
        /* init */
        thrown.expect(ManagerNotExist.class);
        account0 = new Account("Hapoel", listTransactions, 0);
        Team team0 = new Team("Hapoel", account0, field0);
        team0.setPlayers(players);
        Manager exist = new Manager("someone","some2@gmail.com","12");
        Manager notExist = new Manager("not","not@gmail.com","12");
        controller.addTeam(team0);
        owner.addTeam(team0);
        team0.addManager(exist);

        /* try to remove manager who not exist in the team - result should be negative */
        controller.removeManager(owner,team0,notExist.getUserMail());
    }
    @Test
    public void removeManager() throws OwnerNotExist, TeamNotExist , ManagerNotExist{
        /* init */
        account0 = new Account("Hapoel", listTransactions, 0);
        Team team0 = new Team("Hapoel", account0, field0);
        team0.setPlayers(players);
        Manager manager = new Manager("someone","some2@gmail.com","12");
        controller.addTeam(team0);
        controller.addManager(manager);
        owner.addTeam(team0);
        owner.addNewManager(manager,team0);
        int sizeBeforeTeams = manager.getTeam().size();
        int sizeBeforeManagers = team0.getManagers().size();

        /* try to remove manager - result should be positive */
        controller.removeManager(owner,team0,manager.getUserMail());
        assertFalse(manager.getTeam().containsKey(team0.getName()));
        assertFalse(team0.isManager(manager));
        assertEquals(sizeBeforeTeams-1 , manager.getTeam().size());
        assertEquals(sizeBeforeManagers-1 , team0.getManagers().size());
    }
    /******************************************temporaryTeamClosing******************************************/
    @Test
    public void temporaryTeamClosingTeamNotExist1() throws UnavailableOption, TeamNotExist, OwnerNotExist {
        /* init */
        thrown.expect(TeamNotExist.class);
        account0 = new Account("Hapoel", listTransactions, 0);
        Team team0 = new Team("Hapoel", account0, field0);
        Team team1 = new Team("Hapoel1", account0, field0);
        team0.setPlayers(players);
        team0.setStatus(true);
        team1.setStatus(true);
        controller.addTeam(team0);
        owner.addTeam(team0);


        /* try to close team who not exist - result negative*/
        controller.temporaryTeamClosing(owner.getUserMail(),team1.getName());

    }
    @Test
    public void temporaryTeamClosingTeamNotExist2() throws UnavailableOption, TeamNotExist, OwnerNotExist {
        /* init */
        thrown.expect(TeamNotExist.class);
        account0 = new Account("Hapoel", listTransactions, 0);
        Team team0 = new Team("Hapoel", account0, field0);
        Team team1 = new Team("Hapoel1", account0, field0);
        team0.setPlayers(players);

        controller.addTeam(team0);
        controller.addTeam(team1);
        team0.setStatus(true);
        team1.setStatus(true);
        owner.addTeam(team0);

        /* try to close team who not exist in owner's teams- result negative*/
        controller.temporaryTeamClosing(owner.getUserMail(),team1.getName());
    }
    @Test
    public void temporaryTeamClosingUnavalableOption() throws UnavailableOption, TeamNotExist, OwnerNotExist {
        /* init */
        thrown.expect(UnavailableOption.class);
        account0 = new Account("Hapoel", listTransactions, 0);
        Team team0 = new Team("Hapoel", account0, field0);
        team0.setPlayers(players);
        team0.setStatus(false);
        controller.addTeam(team0);
        owner.addTeam(team0);

        /* try to close team who close already - result negative*/
        controller.temporaryTeamClosing(owner.getUserMail(),team0.getName());
        assertFalse(team0.getStatus());
        assertTrue(controller.existTeamName(team0.getName()));
        assertTrue(owner.getTeams().containsKey(team0.getName()));
        assertTrue(team0.isOwner(owner));
    }
    @Test
    public void temporaryTeamClosing() throws UnavailableOption, TeamNotExist, OwnerNotExist {
        /* init */
        account0 = new Account("Hapoel", listTransactions, 0);
        Team team0 = new Team("Hapoel", account0, field0);
        team0.setPlayers(players);
        team0.setStatus(false);
        controller.addTeam(team0);
        owner.addTeam(team0);
        int sizeBefore = this.owner.getTeams().size();

        /* try to reopen team who open already - result negative*/
        controller.temporaryTeamClosing(owner.getUserMail(),team0.getName());
        assertFalse(team0.getStatus());
        assertEquals(sizeBefore , owner.getTeams().size());
        assertTrue(controller.existTeamName(team0.getName()));
    }
    /******************************************reopenClosedTeam******************************************/

    @Test
    public void reopenClosedTeamTeamNotExist1() throws UnavailableOption, TeamNotExist, OwnerNotExist {
        /* init */
        thrown.expect(TeamNotExist.class);
        account0 = new Account("Hapoel", listTransactions, 0);
        Team team0 = new Team("Hapoel", account0, field0);
        Team team1 = new Team("Hapoel1", account0, field0);

        team0.setPlayers(players);
        controller.addTeam(team0);
        owner.addTeam(team0);

        /* try to reopen team who not exist - result negative*/
        controller.reopenTeam(owner.getUserMail(),team1.getName());

    }
    @Test
    public void reopenClosedTeamTeamNotExist2() throws UnavailableOption, TeamNotExist, OwnerNotExist {
        /* init */
        thrown.expect(TeamNotExist.class);
        account0 = new Account("Hapoel", listTransactions, 0);
        Team team0 = new Team("Hapoel", account0, field0);
        Team team1 = new Team("Hapoel1", account0, field0);
        team0.setPlayers(players);

        controller.addTeam(team0);
        controller.addTeam(team1);
        owner.addTeam(team0);

        /* try to reopen team who not exist in owner's teams- result negative*/
        controller.reopenTeam(owner.getUserMail(),team1.getName());
    }
    @Test
    public void reopenClosedUnavalableOption() throws UnavailableOption, TeamNotExist, OwnerNotExist {
        /* init */
        thrown.expect(UnavailableOption.class);
        account0 = new Account("Hapoel", listTransactions, 0);
        Team team0 = new Team("Hapoel", account0, field0);
        team0.setPlayers(players);
        team0.setStatus(true);
        controller.addTeam(team0);
        owner.addTeam(team0);

        /* try to reopen team who open already - result negative*/
        controller.reopenTeam(owner.getUserMail(),team0.getName());
    }
    @Test
    public void reopenClosed() throws UnavailableOption, TeamNotExist, OwnerNotExist {
        /* init */
        account0 = new Account("Hapoel", listTransactions, 0);
        Team team0 = new Team("Hapoel", account0, field0);
        team0.setPlayers(players);
        team0.setStatus(false);
        controller.addTeam(team0);
        owner.addTeam(team0);
        int sizeBefore = this.owner.getTeams().size();

        /* try to reopen team who open already - result negative*/
        controller.reopenTeam(owner.getUserMail(),team0.getName());
        assertTrue(team0.getStatus());
        assertEquals(sizeBefore , owner.getTeams().size());
        assertTrue(controller.existTeamName(team0.getName()));
        assertTrue(owner.getTeams().containsKey(team0.getName()));
        assertTrue(team0.isOwner(owner));
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
    public void checkIfManagerExistsInTeam() {
    }

    @Test
    public void checkIfCoachExistsInTeam() {
    }

    @Test
    public void checkIfPlayerExistsInTeam() {
    }

    @Test
    public void getTeams() {
    }
}
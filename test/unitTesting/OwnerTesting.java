package unitTesting;

import Exception.*;
import Domain.Users.Fan;
import Service.SystemController;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Date;
import java.util.LinkedList;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class OwnerTesting {
    SystemController controller = new SystemController("");

    Date birthdate=new Date(1993,8,11);

    LinkedList<String> idPlayers = new LinkedList<>();
    LinkedList<String> idcoach = new LinkedList<>();
    LinkedList<String> idmanager = new LinkedList<>();
    LinkedList<String> idowner = new LinkedList<>();

    public OwnerTesting() {
    }


    @Before
    public void init() throws IncorrectInputException, DontHavePermissionException, AlreadyExistException {

        controller.signIn("player0","p0@gmail.com","1",birthdate);
        controller.signIn("player1","p1@gmail.com","1",birthdate);
        controller.signIn("player2","p2@gmail.com","1",birthdate);
        controller.signIn("player3","p3@gmail.com","1",birthdate);
        controller.signIn("player4","p4@gmail.com","1",birthdate);
        controller.signIn("player5","p5@gmail.com","1",birthdate);
        controller.signIn("player6","p6@gmail.com","1",birthdate);
        controller.signIn("player7","p7@gmail.com","1",birthdate);
        controller.signIn("player8","p8@gmail.com","1",birthdate);
        controller.signIn("player9","p9@gmail.com","1",birthdate);
        controller.signIn("player10","p10@gmail.com","1",birthdate);
        controller.signIn("coach","coach@gmail.com","1",birthdate);
        controller.signIn("manager","manager@gmail.com","1",birthdate);
        controller.signIn("owner","owner@gmail.com","1",birthdate);

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

        idcoach.add("coach@gmail.com");
        idmanager.add("manager@gmail.com");
        idowner.add("owner@gmail.com");
    }

    @Rule
    public final ExpectedException thrown= ExpectedException.none();

    @Test
    public void addManagerNotExistInSystem() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, AlreadyExistException, ObjectNotExist, NoEnoughMoney, ObjectAlreadyExist {
        thrown.expect(MemberNotExist.class);

        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        //try to add manager not exist
        controller.addManager("team" , "newManager@gmail.com");

        controller.logOut();
        controller.signIn("M","newManager@gmail.com","123", birthdate);
        controller.logIn("owner@gmail.com","1");

        controller.addManager("teamNotExist","newManager@gmail.com");
    }

    @Test
    public void addManagerToTeamNotExistInSystem() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, AlreadyExistException, ObjectNotExist, NoEnoughMoney, ObjectAlreadyExist {
        thrown.expect(ObjectNotExist.class);

        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.signIn("M","newManager@gmail.com","123", birthdate);
        controller.logIn("owner@gmail.com","1");

        controller.addManager("teamNotExist","newManager@gmail.com");
    }

    @Test
    public void addPlayerAlreadyExistInSystem() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, AlreadyExistException, ObjectNotExist, NoEnoughMoney, ObjectAlreadyExist {
        thrown.expect(ObjectNotExist.class);

        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();

        controller.logIn("owner@gmail.com","1");

        //try to add player already exist
        controller.addPlayer("player0" , "p0@gmail.com",1993,8,11,"hilla");

    }

    @Test
    public void addFieldToTeamNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(ObjectNotExist.class);

        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        controller.addField("teamNotExist","f");
    }

    @Test
    public void removePlayerMakeFan() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, AlreadyExistException, NoEnoughMoney, IncorrectInputException, PasswordDontMatchException, ObjectAlreadyExist {

        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.addPlayer(this.idPlayers.get(0),"team",1993,8,11,"hilla");

        controller.removePlayer("team", this.idPlayers.get(0));
        assertThat(controller.getRoles().get(this.idPlayers.get(0)), instanceOf(Fan.class));
    }

    @Test
    public void addOwnerDoNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(MemberNotExist.class);

        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        controller.addNewOwner("team" , "ownerNotExist@gmail.com");
    }

    @Test
    public void temporaryTeamClosingTest() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException, NoEnoughMoney {
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        //status should be false
        controller.temporaryTeamClosing("team");
        assertTrue(controller.getTeams().containsKey("team"));
        assertFalse(controller.getTeams().get("team").getStatus());
    }

    @Test
    public void reopenClosedTeamTest() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException, NoEnoughMoney {
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        //status should be true
        controller.reopenClosedTeam("team");
        assertTrue(controller.getTeams().containsKey("team"));
        assertTrue(controller.getTeams().get("team").getStatus());
    }

    @Test
    public void addIncomeToTeam() throws DontHavePermissionException, ObjectAlreadyExist, MemberNotExist, AlreadyExistException, ObjectNotExist, PasswordDontMatchException, IncorrectInputException, NoEnoughMoney, AccountNotExist {
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team" , 500);

        controller.addInCome("team","TestShouldWork",100);
        int left=(int)controller.getAccountBalance("team");
        assertEquals(600,left);
    }

    @Test
    public void addOutComeToTeam() throws DontHavePermissionException, ObjectAlreadyExist, MemberNotExist, AlreadyExistException, ObjectNotExist, PasswordDontMatchException, IncorrectInputException, NoEnoughMoney, AccountNotExist {

        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team" , 100);


        controller.addOutCome("team","TestShouldWork",50);
        int left=(int)controller.getAccountBalance("team");
        assertEquals(50,left);
    }
}
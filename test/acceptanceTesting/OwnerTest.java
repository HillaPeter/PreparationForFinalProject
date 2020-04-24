package acceptanceTesting;

import Domain.Asset.Coach;
import Domain.Asset.Field;
import Domain.Asset.Manager;
import Domain.Asset.Player;
import Exception.*;
import Domain.Users.Fan;
import Domain.Users.Owner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import Service.SystemController;

import java.util.Date;
import java.util.LinkedList;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class OwnerTest {
    SystemController controller = new SystemController("");

    Date birthdate=new Date(1993,10,12);
    LinkedList<String> idPlayers = new LinkedList<>();
    LinkedList<String> idcoach = new LinkedList<>();
    LinkedList<String> idmanager = new LinkedList<>();
    LinkedList<String> idowner = new LinkedList<>();

    @Before
    public void init() throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberNotExist, PasswordDontMatchException {

        /*add Team*/
        controller.signIn("palyer0","p0@gmail.com","1",birthdate);
        controller.signIn("palyer1","p1@gmail.com","1",birthdate);
        controller.signIn("palyer2","p2@gmail.com","1",birthdate);
        controller.signIn("palyer3","p3@gmail.com","1",birthdate);
        controller.signIn("palyer4","p4@gmail.com","1",birthdate);
        controller.signIn("palyer5","p5@gmail.com","1",birthdate);
        controller.signIn("palyer6","p6@gmail.com","1",birthdate);
        controller.signIn("palyer7","p7@gmail.com","1",birthdate);
        controller.signIn("palyer8","p8@gmail.com","1",birthdate);
        controller.signIn("palyer9","p9@gmail.com","1",birthdate);
        controller.signIn("palyer10","p10@gmail.com","1",birthdate);
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

    /************************************************addAsset-coach**************************************************/
    @Test
    public void addCoach() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, IncorrectInputException, PasswordDontMatchException, ObjectAlreadyExist {
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",1000);

        /* try to add coach - with login result should be positive */
        controller.addCoach("team",this.idcoach.get(0));
        assertThat(controller.getRoles().get(this.idcoach.get(0)) , instanceOf(Coach.class));
        assertTrue(((Coach)controller.getRoles().get(this.idcoach.get(0))).getTeam().containsKey("team"));
        assertNotNull(controller.getTeams().get("team").getCoach(this.idcoach.get(0)));
        assertTrue(950 == controller.getAccountBalance("team"));

    }
    @Test
    public void addCoachPremission() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, ObjectAlreadyExist, IncorrectInputException {
        thrown.expect(DontHavePermissionException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();

        /* try to add coach - without login result should be negative */
        controller.addCoach("team",this.idcoach.get(0));
    }
    @Test
    public void addCoachNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, ObjectAlreadyExist, IncorrectInputException {
        thrown.expect(MemberNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        //controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        int sizeBefore = this.controller.getTeams().get("team").getCoaches().size();

        /* try to add coach - who not exist in the system result should be negative */
        controller.addCoach("team","c@gmail.com");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getCoaches().size());
    }
    @Test
    public void addCoachTeamAlreadyExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, ObjectAlreadyExist, IncorrectInputException {
        thrown.expect(AlreadyExistException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");

        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        controller.setMoneyToAccount("team",1000);
        controller.addCoach("team",this.idcoach.get(0));

        int sizeBefore = this.controller.getTeams().get("team").getCoaches().size();

        /* try to add coach who already exist in the team - result should be negative */
        controller.addCoach("team",this.idcoach.get(0));
        System.out.println(this.controller.getTeams().get("team").getCoaches().size());
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getCoaches().size());

    }
    @Test
    public void addCoachTeamAlreadyExistInAnother() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, ObjectAlreadyExist, IncorrectInputException {
        thrown.expect(AlreadyExistException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.addTeam("team2","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team2",1000);
        controller.addCoach("team2",this.idcoach.get(0));

        int sizeBefore = this.controller.getTeams().get("team").getCoaches().size();

        /* try to add coach who already exist in Another team - result should be negative */
        controller.addCoach("team",this.idcoach.get(0));
        System.out.println(this.controller.getTeams().get("team").getCoaches().size());
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getCoaches().size());

    }
    @Test
    public void addCoachTeamNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, ObjectAlreadyExist, IncorrectInputException {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        System.out.println(this.idcoach.get(0));
        /* try to add coach - invalid team name result should be negative */
        controller.addCoach("teammm",this.idcoach.get(0));
    }
    @Test
    public void addCoachNotMoney() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(NoEnoughMoney.class);
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        //  controller.addTeam(this.idPlayers,this.idcoach,this.idmanager,this.idowner,"team");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",40);
        int sizeBefore = this.controller.getTeams().get("team").getPlayers().size();

        /* try to add coach - with login result should be positive */
        controller.addCoach("team",this.idcoach.get(0));
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getPlayers().size());

    }
    /************************************************addAsset-player**************************************************/
    @Test
    public void addPlayer() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, AlreadyExistException, ObjectNotExist, ObjectAlreadyExist, NoEnoughMoney {
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",1000);


        /* try to add player - with login result should be positive */
        controller.addPlayer(this.idPlayers.get(0),"team",1993,10,12,"df");
        assertThat(controller.getRoles().get(this.idPlayers.get(0)) , instanceOf(Player.class));
        assertTrue(((Player)controller.getRoles().get(this.idPlayers.get(0))).getTeam().containsKey("team"));
        assertNotNull(controller.getTeams().get("team").getPlayer(this.idPlayers.get(0)));

        int moneyLeft=(int)controller.getAccountBalance("team");
        assertEquals(950,moneyLeft);
    }
    @Test
    public void addPlayerPermission() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(DontHavePermissionException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();

        /* try to add player - without login result should be negative */
        controller.addPlayer(this.idPlayers.get(0),"team",1993,10,12,"yi");
    }
    @Test
    public void addPlayerNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(MemberNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        int sizeBefore = this.controller.getTeams().get("team").getPlayers().size();

        /* try to add player who not exist in the system - result should be negative */
        controller.addPlayer("newPlayer@gmail.com","team",2000,10,12,"yi");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getPlayers().size());
    }
    @Test
    public void addPlayerTeamAlreadyExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(AlreadyExistException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");

        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        controller.setMoneyToAccount("team",1000);
        controller.addPlayer(this.idPlayers.get(0),"team",2000,10,12,"fd");

        int sizeBefore = this.controller.getTeams().get("team").getPlayers().size();


        /* try to add player who already exist in the team - result should be negative */
        controller.addPlayer(this.idPlayers.get(0),"team",2000,10,12,"uu");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getPlayers().size());

    }
    @Test
    public void addPlayerTeamAlreadyExistInAnother() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(AlreadyExistException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.addTeam("team2","owner@gmail.com");

        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        controller.setMoneyToAccount("team2",1000);
        controller.addPlayer(this.idPlayers.get(0),"team2",1993,10,12,"esfd");

        int sizeBefore = this.controller.getTeams().get("team").getPlayers().size();


        /* try to add player who already exist in the team - result should be negative */
        controller.addPlayer(this.idPlayers.get(0),"team",1993,10,12,"wer");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getPlayers().size());
    }
    @Test
    public void addPlayerTeamNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        /* try to add player - invalid team name result should be negative */
        controller.addPlayer(this.idPlayers.get(0),"teammm",1993,10,12,"df");
    }
    @Test
    public void addPlayerNotMoney() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(NoEnoughMoney.class);
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",40);
        int sizeBefore = this.controller.getTeams().get("team").getPlayers().size();


        /* try to add player - with login result should be positive */
        controller.addPlayer(this.idPlayers.get(0),"team",1993,10,12,"fd");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getPlayers().size());

    }
    /************************************************addAsset-field**************************************************/
    @Test
    public void addField() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, AlreadyExistException, ObjectNotExist, ObjectAlreadyExist, NoEnoughMoney {
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
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

        int leftAmount=(int)controller.getAccountBalance("team");
        assertEquals(950,leftAmount);
        assertEquals(sizeBefore+1,controller.getTeams().get("team").getTrainingFields().size());

    }
    @Test
    public void addFieldPermission() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(DontHavePermissionException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();

        /* try to add field - without login result should be negative */
        controller.addField("team","f");
    }
    @Test
    public void addFieldTeamAlreadyExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(ObjectAlreadyExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",1000);
        controller.addField("team","f");

        int sizeBefore = this.controller.getTeams().get("team").getTrainingFields().size();

        /* try to add field who already exist in the team - result should be negative */
        controller.addField("team","f");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getTrainingFields().size());
    }
    @Test
    public void addFieldTeamNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        /* try to add field - invalid team name result should be negative */
        controller.addField("teammmm","f");
    }
    @Test
    public void addFieldNotMoney() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(NoEnoughMoney.class);
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",40);
        int sizeBefore = this.controller.getTeams().get("team").getTrainingFields().size();


        /* try to add field with no enough money - result should be negative */
        controller.addField("team","f");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getTrainingFields().size());
    }
    /*********************************************removeAsset-coach*********************************************/
    @Test
    public void removeCoach() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, IncorrectInputException, PasswordDontMatchException, ObjectAlreadyExist {
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",1000);
        controller.addCoach("team",this.idcoach.get(0));

        /* try to remove coach - with login result should be positive */
        controller.removeCoach("team",this.idcoach.get(0));
        assertTrue(controller.getRoles().containsKey(this.idcoach.get(0)));
        assertThat(controller.getRoles().get(this.idcoach.get(0)), instanceOf(Fan.class));
        assertNotNull(controller.getTeams().get("team").getCoach(this.idcoach.get(0)));
        assertTrue(950 == controller.getAccountBalance("team"));
    }
    @Test
    public void removeCoachPermission() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, AlreadyExistException, PasswordDontMatchException, ObjectAlreadyExist, IncorrectInputException {
        thrown.expect(DontHavePermissionException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");

        /* try to remove coach - without login result should be negative */
        controller.removeCoach("team",this.idcoach.get(0));
    }
    @Test
    public void removeCoachNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, ObjectAlreadyExist, IncorrectInputException {
        thrown.expect(MemberNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.addCoach("team",this.idcoach.get(0));

        int sizeBefore = this.controller.getTeams().get("team").getCoaches().size();

        /* try to remove coach - who not exist in the team result should be negative */
        controller.removeCoach("team","c@gmail.com");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getCoaches().size());
    }
    @Test
    public void removeCoachTeamNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, AlreadyExistException, PasswordDontMatchException, ObjectAlreadyExist, IncorrectInputException {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        /* try to remove coach - invalid team name result should be negative */
        controller.removeCoach("teammm",this.idcoach.get(0));
    }
    /*********************************************removeAsset-player*********************************************/
    @Test
    public void removePlayer() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, AlreadyExistException, NoEnoughMoney, IncorrectInputException, PasswordDontMatchException, ObjectAlreadyExist {
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",1000);
        controller.addPlayer(this.idPlayers.get(0),"team",1993,10,12,"dfs");
        int sizeBefore = this.controller.getTeams().get("team").getPlayers().size();


        /* try to remove player - with login result should be positive */
        controller.removePlayer("team", this.idPlayers.get(0));
        assertTrue(controller.getRoles().containsKey(this.idPlayers.get(0)));
        assertThat(controller.getRoles().get(this.idPlayers.get(0)), instanceOf(Fan.class));
        assertNull(controller.getTeams().get("team").getPlayer(this.idPlayers.get(0)));
        assertTrue(950 == controller.getAccountBalance("team"));
        assertEquals(sizeBefore-1 , this.controller.getTeams().get("team").getPlayers().size());
    }
    @Test
    public void removePlayerPermission() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, AlreadyExistException, PasswordDontMatchException, ObjectAlreadyExist, IncorrectInputException {
        thrown.expect(DontHavePermissionException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");

        /* try to remove player - without login result should be negative */
        controller.removePlayer("team", this.idPlayers.get(0));
    }
    @Test
    public void removePlayerNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, ObjectAlreadyExist, IncorrectInputException {
        thrown.expect(MemberNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.addPlayer(this.idPlayers.get(0),"team",1993,10,12,"dfg");

        int sizeBefore = this.controller.getTeams().get("team").getPlayers().size();

        /* try to remove player - who not exist in the team result should be negative */
        controller.removePlayer("team", "p@gmail.com");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getPlayers().size());
    }
    @Test
    public void removePlayerTeamNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, ObjectAlreadyExist, IncorrectInputException {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        /* try to remove player - invalid team name result should be negative */
        controller.removePlayer("teammmm", "p@gmail.com");
    }
    /*********************************************removeAsset-field*********************************************/
    @Test
    public void removeField() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, AlreadyExistException, NoEnoughMoney, IncorrectInputException, PasswordDontMatchException, ObjectAlreadyExist {
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",1000);
        controller.addField("team","f");
        int sizeBefore = this.controller.getTeams().get("team").getTrainingFields().size();


        /* try to remove field - with login result should be positive */
        controller.removeField("team", "f");
        assertNull(controller.getTeams().get("team").getField("f"));
        assertTrue(950 == controller.getAccountBalance("team"));
        assertEquals(sizeBefore-1 , this.controller.getTeams().get("team").getTrainingFields().size());
    }
    @Test
    public void removeFieldPermission() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, AlreadyExistException, PasswordDontMatchException, ObjectAlreadyExist, IncorrectInputException {
        thrown.expect(DontHavePermissionException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");

        /* try to remove field - without login result should be negative */
        controller.removeField("team", "f");
    }
    @Test
    public void removeFieldNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, ObjectAlreadyExist, IncorrectInputException {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.addField("team","f");

        int sizeBefore = this.controller.getTeams().get("team").getTrainingFields().size();

        /* try to remove field - who not exist in the team result should be negative */
        controller.removeField("team", "ff");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getTrainingFields().size());
    }
    @Test
    public void removeFieldTeamNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, ObjectAlreadyExist, IncorrectInputException {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        /* try to remove field - invalid team name result should be negative */
        controller.removeField("teammm", "f");
    }
    /*********************************************addNewManager***********************************************/
    @Test
    public void addNewManager() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, AlreadyExistException, ObjectNotExist, NoEnoughMoney, ObjectAlreadyExist {
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.signIn("M","newManager@gmail.com","123", birthdate);
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",1000);

        /* try to add new manager - with login result should be positive */
        controller.addManager("team" , "newManager@gmail.com");
        assertThat(controller.getRoles().get("newManager@gmail.com") , instanceOf(Manager.class));
        assertNotNull(controller.getTeams().get("team").getManager("newManager@gmail.com"));
        Manager m = (Manager)controller.getRoles().get("newManager@gmail.com");
        assertTrue(m.getTeam().containsKey("team"));
        assertTrue(950 == controller.getAccountBalance("team"));
    }
    @Test
    public void addNewManagerPermission() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(DontHavePermissionException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();

        /* try to add new manager - without login result should be negative */
        controller.addManager("team" , "newManager@gmail.com");
    }
    @Test
    public void addNewManagerNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(MemberNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");


        /* try to add new manager who not exist in the system - result should be negative */
        controller.addManager("team" , "newManager@gmail.com");
    }
    @Test
    public void addNewManagerTeamAlreadyExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(AlreadyExistException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");

        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.addManager("team","manager@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        /* try to add new manager who already exist in the team - result should be negative */
        controller.addManager("team","manager@gmail.com");
    }
    @Test
    public void addNewManagerTeamNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        /* try to add new manager - invalid team name result should be negative */
        controller.addManager("teammmm","manager@gmail.com");
    }
    @Test
    public void addNewManagerNoEnoughMoney() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(NoEnoughMoney.class);
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.signIn("M","newManager@gmail.com","123", birthdate);
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",40);

        int sizeBefore = this.controller.getTeams().get("team").getManagers().size();

        /* try to add new manager - with not enough money-  result should be negative */
        controller.addManager("team" , "newManager@gmail.com");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getManagers().size());

    }
    /******************************************addNewOwner******************************************/
    @Test
    public void addNewOwner() throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException, NoEnoughMoney {
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.signIn("owner2","owner2@gmail.com","1",birthdate);
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",1000);

        /* try to add owner - with login result should be positive */
        controller.addNewOwner("team","owner2@gmail.com");
        assertThat(controller.getRoles().get("owner2@gmail.com") , instanceOf(Owner.class));
        assertNotNull(((Owner)controller.getRoles().get("owner2@gmail.com")).getTeams().get("team"));
        assertNotNull(controller.getTeams().get("team").getOwner("owner2@gmail.com"));

        int leftMoney=(int) controller.getAccountBalance("team");
        assertEquals(950,leftMoney);
    }
    @Test
    public void addNewOwnerPermission() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(DontHavePermissionException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();

        /* try to add new owner - without login result should be negative */
        controller.addNewOwner("team" , "owner2@gmail.com");
    }
    @Test
    public void addNewOwnerNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(MemberNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        /* try to add new owner who not exist in the system - result should be negative */
        controller.addNewOwner("team" , "owner2@gmail.com");
    }
    @Test
    public void addNewOwnerTeamAlreadyExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(AlreadyExistException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.signIn("owner2","owner2@gmail.com","1",birthdate);
        controller.logIn("owner@gmail.com","1");

        controller.addNewOwner("team","owner2@gmail.com");


        /* try to add new owner who already exist in the team - result should be negative */
        controller.addNewOwner("team","owner2@gmail.com");

    }
    @Test
    public void addNewOwnerTeamNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.signIn("owner2","owner2@gmail.com","1",birthdate);
        controller.logIn("owner@gmail.com","1");

        /* try to add new owner - invalid team name result should be negative */
        controller.addNewOwner("teammmm","owner2@gmail.com");
    }
    @Test
    public void addNewOwnerNoEnoughMoney() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(NoEnoughMoney.class);
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.signIn("owner","owner2@gmail.com","123", birthdate);
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",40);

        /* try to add new owner - with not enough money-  result should be negative */
        controller.addManager("team" , "owner2@gmail.com");
    }
    /******************************************removeManager******************************************/
    @Test
    public void removeManager() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, AlreadyExistException, ObjectNotExist, NoEnoughMoney, ObjectAlreadyExist {
        /* init - create team  */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.signIn("M","newManager@gmail.com","123", birthdate);
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team",1000);
        controller.addManager("team" , "newManager@gmail.com");

        int sizeBefore = this.controller.getTeams().get("team").getManagers().size();


        /* try to remove manager - with login result should be positive */
        controller.removeManager("team" , "newManager@gmail.com");
        assertTrue(controller.getRoles().containsKey("newManager@gmail.com"));
        assertThat(controller.getRoles().get("newManager@gmail.com"),instanceOf(Fan.class));
        assertNull(controller.getTeams().get("team").getManager("newManager@gmail.com"));
        assertTrue(950 == controller.getAccountBalance("team"));
        assertEquals(sizeBefore-1 ,this.controller.getTeams().get("team").getManagers().size());

    }
    @Test
    public void removeManagerPermission() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(DontHavePermissionException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();

        /* try to remove manager - without login result should be negative */
        controller.removeManager("team" , "newManager@gmail.com");
    }
    @Test
    public void removeManagerNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(MemberNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        int sizeBefore = this.controller.getTeams().get("team").getManagers().size();

        /* try to remove manager who not exist in the system/team - result should be negative */
        controller.removeManager("team" , "newManager@gmail.com");
        assertEquals(sizeBefore, this.controller.getTeams().get("team").getManagers().size());
    }
    @Test
    public void removeManagerTeamNotExist() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, PasswordDontMatchException, IncorrectInputException, ObjectAlreadyExist {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        /* try to remove manager - invalid team name result should be negative */
        controller.removeManager("teammmm","NewManager@gmail.com");
    }
    /******************************************temporaryTeamClosing******************************************/
    @Test
    public void temporaryTeamClosing() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException, NoEnoughMoney {
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team" , 1000);
        controller.addPlayer(this.idPlayers.get(0),"team",1993,10,12,"fsd");

        int sizeBefore = controller.getTeams().size();

        /* try to temporary close team - result positive*/
        controller.temporaryTeamClosing("team");
        assertTrue(controller.getTeams().containsKey("team"));
        assertFalse(controller.getTeams().get("team").getStatus());
        assertEquals(sizeBefore ,controller.getTeams().size());
        assertThat(controller.getRoles().get(this.idPlayers.get(0)) , instanceOf(Player.class));
    }
    @Test
    public void temporaryTeamClosinNoPremission() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, ObjectAlreadyExist, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(DontHavePermissionException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();

        /* try to temporary close team without login - result negative*/
        controller.temporaryTeamClosing("team");
        assertTrue(controller.getTeams().get("team").getStatus());
    }
    @Test
    public void temporaryTeamClosingTeamNotExist1() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        thrown.expect(ObjectNotExist.class);

        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
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
    public void reopenClosedTeam() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException, NoEnoughMoney {
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team" , 1000);
        controller.addPlayer(this.idPlayers.get(0),"team",1993,10,12,"guard");
        controller.temporaryTeamClosing("team");
        int sizeBefore = controller.getTeams().size();

        /* try to reopen team - result positive*/
        controller.reopenClosedTeam("team");
        assertTrue(controller.getTeams().containsKey("team"));
        assertTrue(controller.getTeams().get("team").getStatus());
        assertEquals(sizeBefore ,controller.getTeams().size());
        assertThat(controller.getRoles().get(this.idPlayers.get(0)) , instanceOf(Player.class));
        assertTrue(((Player)controller.getRoles().get(this.idPlayers.get(0))).getTeam().containsKey("team"));
    }
    @Test
    public void reopenClosedTeamNoPremission() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException, PasswordDontMatchException {
        thrown.expect(DontHavePermissionException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();

        /* try to reopen team who open already - result negative*/
        controller.reopenClosedTeam("team");
        assertFalse(controller.getTeams().get("team").getStatus());
    }
    @Test
    public void reopenClosedTeamTeamNotExist1() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        thrown.expect(ObjectNotExist.class);

        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
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
    public void addIncome() throws DontHavePermissionException, ObjectAlreadyExist, MemberNotExist, AlreadyExistException, ObjectNotExist, PasswordDontMatchException, IncorrectInputException, NoEnoughMoney, AccountNotExist {
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team" , 10000);

        /* try to add income to Team - result should be positive */
        controller.addInCome("team","present",1000);
        assertTrue(11000 == controller.getAccountBalance("team"));
    }
    @Test
    public void addIncomeNoPermission() throws DontHavePermissionException, ObjectAlreadyExist, MemberNotExist, AlreadyExistException, ObjectNotExist, PasswordDontMatchException, IncorrectInputException, NoEnoughMoney, AccountNotExist {
        thrown.expect(DontHavePermissionException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();

        /* try to add income to Team without login - result should be negative */
        controller.addInCome("team","present",1000);
    }
    @Test
    public void addIncomeTeamNotExist() throws DontHavePermissionException, ObjectAlreadyExist, MemberNotExist, AlreadyExistException, ObjectNotExist, PasswordDontMatchException, IncorrectInputException, NoEnoughMoney, AccountNotExist {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team" , 10000);

        /* try to add income to Team team not exist - result should be negative */
        controller.addInCome("teammm","present",1000);
        assertTrue(10000 == controller.getAccountBalance("team"));
    }
    @Test
    public void addIncomeWrongInput() throws DontHavePermissionException, ObjectAlreadyExist, MemberNotExist, AlreadyExistException, ObjectNotExist, PasswordDontMatchException, IncorrectInputException, NoEnoughMoney, AccountNotExist {
        thrown.expect(IncorrectInputException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team" , 10000);

        /* try to add income to Team without fill the description field - result should be negative */
        controller.addInCome("team","",1000);
    }
    /******************************************addOutcome******************************************/
    @Test
    public void addOutCome() throws DontHavePermissionException, ObjectAlreadyExist, MemberNotExist, AlreadyExistException, ObjectNotExist, PasswordDontMatchException, IncorrectInputException, NoEnoughMoney, AccountNotExist {
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team" , 10000);

        /* try to add outcome to Team - result should be positive */
        controller.addOutCome("team","outcome",1000);
        assertTrue((10000-1000) == controller.getAccountBalance("team"));
    }
    @Test
    public void addOutComeNoPermission() throws DontHavePermissionException, ObjectAlreadyExist, MemberNotExist, AlreadyExistException, ObjectNotExist, PasswordDontMatchException, IncorrectInputException, NoEnoughMoney, AccountNotExist {
        thrown.expect(DontHavePermissionException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();

        /* try to add outcome to Team without login - result should be negative */
        controller.addOutCome("team","outcome",1000);
    }
    @Test
    public void addOutComeTeamNotExist() throws DontHavePermissionException, ObjectAlreadyExist, MemberNotExist, AlreadyExistException, ObjectNotExist, PasswordDontMatchException, IncorrectInputException, NoEnoughMoney, AccountNotExist {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team" , 10000);

        /* try to add outcome to Team team not exist - result should be negative */
        controller.addOutCome("teammm","present",1000);
        assertTrue(10000 == controller.getAccountBalance("team"));
    }
    @Test
    public void addOutComeWrongInput() throws DontHavePermissionException, ObjectAlreadyExist, MemberNotExist, AlreadyExistException, ObjectNotExist, PasswordDontMatchException, IncorrectInputException, NoEnoughMoney, AccountNotExist {
        thrown.expect(IncorrectInputException.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");
        controller.setMoneyToAccount("team" , 10000);

        /* try to add outcome to Team without fill the description field - result should be negative */
        controller.addOutCome("team","",1000);
    }
    @Test
    public void addOutComeNotEnoughMoney() throws DontHavePermissionException, ObjectAlreadyExist, MemberNotExist, AlreadyExistException, ObjectNotExist, PasswordDontMatchException, IncorrectInputException, NoEnoughMoney, AccountNotExist {
        thrown.expect(NoEnoughMoney.class);
        /* init */
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team","owner@gmail.com");
        controller.logOut();
        controller.logIn("owner@gmail.com","1");

        /* try to add outcome to Team without set money no account - result should be negative */
        controller.addOutCome("team","tt",1001);
    }

}
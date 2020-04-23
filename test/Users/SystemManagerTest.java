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
    Date birthdate = new Date(1993, 10, 12);

    public SystemManagerTest() throws DontHavePermissionException, AlreadyExistException, MemberNotExist, IncorrectInputException {
    }


    @Before
    public void init() throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberNotExist, PasswordDontMatchException {
        controller.signIn("owner", "owner@gmail.com", "1", birthdate);
    }

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    /*******************************************************************************/
    @Test
    public void schedulingGames1() throws PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, ObjectNotExist, ObjectAlreadyExist, NoEnoughMoney, AlreadyExistException, MemberNotExist, MemberAlreadyExistException {
        /* init - enter league , season , schedulePolicy  */
        controller.signIn("associateDelegite", "dani@gmail.com", "123", birthdate);

        controller.logIn("admin@gmail.com", "123");
        controller.addAssociationDelegate("dani@gmail.com");
        controller.logOut();

        controller.logIn("dani@gmail.com", "123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");
        controller.setSchedulingPolicyToLeagueInSeason("league","2020","All teams play each other once");
        controller.logOut();

        /*init - add 20 Teams ,referees to league in season */
        addTeamsCorrectly(20);
        enterReferee(20);
        addTeamsToLeagueSeason(20);
        addRefereesToLeagueInSeason(20);

        controller.logIn("admin@gmail.com", "123");

        /* try to scheduling game with correct input - teams with 11 players - result should be positive */
        controller.schedulingGames("2020","league");

        HashSet<Game> games = controller.getGames("league","2020");
        int amountOfGames = 190; // 20 Choose 2 (???)
        assertTrue(games.size() == amountOfGames );
        //check if all teams status==true
        // && check if each team play in 38 games - 19*2
        // && check if team.games.contains(game)
        for (Game game: games) {
            assertTrue(game.getHostTeam().getStatus());
            assertTrue(game.getHostTeam().getGamesSize() == 38);
            assertTrue(game.getHostTeam().getGames().contains(game));

            assertTrue(game.getVisitorTeam().getStatus());
            assertTrue(game.getVisitorTeam().getGamesSize() == 38);
            assertTrue(game.getVisitorTeam().getGames().contains(game));

            assertTrue(game.getReferees().size() == 2);
            assertNotNull(game.getField());
        }
    }
    @Test
    public void schedulingGames2() throws PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, ObjectNotExist, ObjectAlreadyExist, NoEnoughMoney, AlreadyExistException, MemberNotExist, MemberAlreadyExistException {
        /* init - enter league , season , schedulePolicy  */
        controller.signIn("associateDelegite", "dani@gmail.com", "123", birthdate);

        controller.logIn("admin@gmail.com", "123");
        controller.addAssociationDelegate("dani@gmail.com");
        controller.logOut();

        controller.logIn("dani@gmail.com", "123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");
        controller.setSchedulingPolicyToLeagueInSeason("league","2020","All teams play each other twice");
        controller.logOut();

        /*init - add 20 Teams ,referees to league in season */
        addTeamsCorrectly(20);
        enterReferee(20);
        addTeamsToLeagueSeason(20);
        addRefereesToLeagueInSeason(20);

        controller.logOut();

        controller.logIn("admin@gmail.com", "123");

        /*try to scheduling game with correct input - teams with 11 players - result should be positive*/
        controller.schedulingGames("2020","league");
        HashSet<Game> games = controller.getGames("league","2020");
        int amountOfGames = 190; // 20 Choose 2 (???)
        assertTrue(games.size() == amountOfGames );
        //check if all teams status==true
        // && check if each team play in 38 games - 19*2
        // && check if team.games.contains(game)
        for (Game game: games) {
            assertTrue(game.getHostTeam().getStatus());
            assertTrue(game.getHostTeam().getGamesSize() == 38);
            assertTrue(game.getHostTeam().getGames().contains(game));

            assertTrue(game.getVisitorTeam().getStatus());
            assertTrue(game.getVisitorTeam().getGamesSize() == 38);
            assertTrue(game.getVisitorTeam().getGames().contains(game));

            assertTrue(game.getReferees().size() == 2);
            assertNotNull(game.getField());
        }
    }
    @Test
    public void schedulingGamesWrongTeams() throws PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, ObjectNotExist, ObjectAlreadyExist, NoEnoughMoney, AlreadyExistException, MemberNotExist, MemberAlreadyExistException {
        thrown.expect(IncorrectInputException.class);

        /* init - enter league , season , scedualePolicy  */
        controller.signIn("associateDelegite", "dani@gmail.com", "123", birthdate);

        controller.logIn("admin@gmail.com", "123");
        controller.addAssociationDelegate("dani@gmail.com");
        controller.logOut();


        controller.logIn("dani@gmail.com", "123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");
        controller.setSchedulingPolicyToLeagueInSeason("league","2020","All teams play each other once");
        controller.logOut();

        /*init - add 11 Teams ,referees to league in season */
        addTeamsCorrectly(11);
        enterReferee(11);
        addTeamsToLeagueSeason(11);
        addRefereesToLeagueInSeason(11);

        controller.logOut();


        controller.logIn("admin@gmail.com", "123");

        /*try to scheduling game with incorrect input - teams odd number of teams- result should be negative*/
        controller.schedulingGames("2020","league");
    }
    @Test
    public void schedulingGamesNoPlayers() throws PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, ObjectNotExist, ObjectAlreadyExist, NoEnoughMoney, AlreadyExistException, MemberNotExist, MemberAlreadyExistException {
        thrown.expect(IncorrectInputException.class);

        /* init - enter league , season , scedualePolicy  */
        controller.signIn("associateDelegite", "dani@gmail.com", "123", birthdate);

        controller.logIn("admin@gmail.com", "123");
        controller.addAssociationDelegate("dani@gmail.com");
        controller.logOut();


        controller.logIn("dani@gmail.com", "123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");
        controller.setSchedulingPolicyToLeagueInSeason("league","2020","All teams play each other once");
        controller.logOut();

        /*init - add 11 Teams ,referees to league in season */
        addTeamsWithoutPlayers(10);
        enterReferee(10);
        addTeamsToLeagueSeason(10);
        addRefereesToLeagueInSeason(10);

        controller.logOut();


        controller.logIn("admin@gmail.com", "123");

        /*try to scheduling game with incorrect input - teams without 11 players- result should be negative*/
        controller.schedulingGames("2020","league");
    }
    @Test
    public void schedulingGamesNoReferees() throws PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, ObjectNotExist, ObjectAlreadyExist, NoEnoughMoney, AlreadyExistException, MemberNotExist, MemberAlreadyExistException {
        thrown.expect(IncorrectInputException.class);

        /* init - enter league , season , scedualePolicy  */
        controller.signIn("associateDelegite", "dani@gmail.com", "123", birthdate);

        controller.logIn("admin@gmail.com", "123");
        controller.addAssociationDelegate("dani@gmail.com");
        controller.logOut();


        controller.logIn("dani@gmail.com", "123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");
        controller.setSchedulingPolicyToLeagueInSeason("league","2020","All teams play each other once");
        controller.logOut();

        /*init - add 11 Teams ,referees to league in season */
        addTeamsCorrectly(20);
        enterReferee(20);
        addTeamsToLeagueSeason(20);
        addRefereesToLeagueInSeason(11);

        controller.logOut();


        controller.logIn("admin@gmail.com", "123");

        /*try to scheduling game with incorrect input - not enough referees- result should be negative*/
        controller.schedulingGames("2020","league");
    }
    @Test
    public void schedulingGamesLeagueNotExist() throws PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, ObjectNotExist, ObjectAlreadyExist, NoEnoughMoney, AlreadyExistException, MemberNotExist {
        thrown.expect(ObjectNotExist.class);

        /* init - enter league , season , scedualePolicy  */
        controller.signIn("associateDelegite", "dani@gmail.com", "123", birthdate);

        controller.logIn("admin@gmail.com", "123");
        controller.addAssociationDelegate("dani@gmail.com");
        controller.logOut();


        controller.logIn("dani@gmail.com", "123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");
        controller.setSchedulingPolicyToLeagueInSeason("league","2020","All teams play each other once");
        controller.logOut();

        controller.logIn("admin@gmail.com", "123");

        /*try to scheduling game with incorrect input - league not exist - result should be negative*/
        controller.schedulingGames("2020","league2");
    }
    @Test
    public void schedulingGamesSeasonNotExist() throws PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, ObjectNotExist, ObjectAlreadyExist, NoEnoughMoney, AlreadyExistException, MemberNotExist {
        thrown.expect(ObjectNotExist.class);

        /* init - enter league , season , scedualePolicy  */
        controller.signIn("associateDelegite", "dani@gmail.com", "123", birthdate);

        controller.logIn("admin@gmail.com", "123");
        controller.addAssociationDelegate("dani@gmail.com");
        controller.logOut();


        controller.logIn("dani@gmail.com", "123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");
        controller.setSchedulingPolicyToLeagueInSeason("league","2020","All teams play each other once");
        controller.logOut();

        controller.logIn("admin@gmail.com", "123");

        /*try to scheduling game with incorrect input - season not exist- result should be negative*/
        controller.schedulingGames("202","league");
    }
    @Test
    public void schedulingGamesNoPermission() throws PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, ObjectNotExist, ObjectAlreadyExist, NoEnoughMoney, AlreadyExistException, MemberNotExist {
        thrown.expect(DontHavePermissionException.class);

        /* init - enter league , season , scedulePolicy and teams */
        controller.signIn("associateDelegite", "dani@gmail.com", "123", birthdate);

        controller.logIn("admin@gmail.com", "123");
        controller.addAssociationDelegate("dani@gmail.com");
        controller.logOut();

        controller.logIn("dani@gmail.com", "123");
        controller.setLeague("league");
        controller.setLeagueByYear("league","2020");

        controller.setSchedulingPolicyToLeagueInSeason("league","2020","All teams play each other once");
        controller.logOut();

        /*try to scheduling game without login - season not exist- result should be negative*/
        controller.schedulingGames("202","league");
    }

    /*******************************************************************************/
    @Test
    public void addNewTeam() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        controller.logIn("admin@gmail.com", "123");
        int sizeBefore = this.controller.getRoles().size();
        int sizeBeforeT = this.controller.getTeams().size();

        /* try to add team - result should be positive */

        controller.addTeam("newTeam", "owner@gmail.com");
        assertTrue(controller.getTeams().containsKey("newTeam"));

        assertEquals(sizeBefore, controller.getRoles().size());
        assertEquals(sizeBeforeT + 1, controller.getTeams().size());

        assertTrue(controller.getTeams().containsKey("newTeam"));
        assertTrue(((Owner) this.controller.getRoles().get("owner@gmail.com")).getTeams().containsKey("newTeam"));
    }

    @Test
    public void addNewTeamNoPermission() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        thrown.expect(DontHavePermissionException.class);
        // controller.logIn("admin@gmail.com", "123");

        //controller.logIn("admin@gmail.com","123");
        //try to make new team without log in
        int sizeBefore = this.controller.getRoles().size();

        controller.addTeam("newTeam", "owner@gmail.com");
        assertEquals(sizeBefore, controller.getRoles().size());
    }

    @Test
    public void addNewTeamExistingTeam() throws DontHavePermissionException, ObjectNotExist, ObjectAlreadyExist, MemberNotExist, AlreadyExistException, PasswordDontMatchException, IncorrectInputException {
        thrown.expect(ObjectAlreadyExist.class);
        controller.logIn("admin@gmail.com", "123");

        int sizeBefore = this.controller.getRoles().size();
        controller.addTeam("newTeam", "owner@gmail.com");

        /* try to add team with existing name - result should be negative */
        controller.addTeam("newTeam", "owner@gmail.com");

    }

    @Test
    public void addNewTeamNameNotGood() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        thrown.expect(IncorrectInputException.class);
        controller.logIn("admin@gmail.com", "123");
        int sizeBefore = this.controller.getRoles().size();

        /*try to add team with teamName contains only numbers */

        controller.addTeam("1234" , "owner@gmail.com");
        assertFalse(controller.getTeams().containsKey("1234"));
        assertEquals(sizeBefore, controller.getRoles().size());
    }


    /*******************************************************************************/

    @Test
    public void removeReferee() throws MemberNotExist, PasswordDontMatchException, IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberAlreadyExistException {
        //done
        /* init */
        controller.signIn("referee", "referee@gmail.com", "123", birthdate);
        //  controller.logOut();
        controller.logIn("admin@gmail.com", "123");

        controller.addReferee("referee@gmail.com", false);
        int sizeBefore = controller.getRoles().size();

        Referee referee = (Referee) controller.getRoles().get("referee@gmail.com");

        HashSet<Game> games = referee.getGameSchedule();
        boolean refereeNotInGame = true;


        /* try to remove referee who already exist in the system - result should be positive*/
        controller.removeReferee("referee@gmail.com");
        assertEquals(sizeBefore, controller.getRoles().size());
        assertThat(controller.getRoles().get("referee@gmail.com"), instanceOf(Fan.class));

        for (Game game : games) {
            if (game.isRefereeInTheGame(referee)) {
                refereeNotInGame = false;
            }
        }
        assertTrue(refereeNotInGame);

    }

    @Test
    public void removeRefereeNotExist() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, IncorrectInputException, AlreadyExistException {
        //done

        thrown.expect(MemberNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com", "123");
        int sizeBefore = controller.getRoles().size();


        /*try to remove referee who doesnt exist in the system - result should be Negative*/
        assertFalse(controller.removeReferee("referee@gmail.com"));
        assertEquals(sizeBefore, controller.getRoles().size());
    }

    @Test
    public void removeRefereeNoPermission() throws DontHavePermissionException, MemberAlreadyExistException, AlreadyExistException, MemberNotExist, IncorrectInputException {
        //done

        thrown.expect(DontHavePermissionException.class);
        // int sizeBefore = this.controller.getRoles().size();

        /* try to remove referee without login with systemManager */
        assertFalse(controller.addReferee("refere@gmail.com", false));
        // assertEquals(sizeBefore,controller.getRoles().size());
    }

    /*******************************************************************************/
    @Test
    public void addReferee() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, IncorrectInputException, AlreadyExistException, MemberAlreadyExistException {
//done
        controller.signIn("referee", "referee@gmail.com", "1", birthdate);

        controller.logIn("admin@gmail.com", "123");
        int sizeBefore = controller.getRoles().size();


        /*try to add referee who  exist in the system as fan - result should be positive*/
        assertTrue(controller.addReferee("referee@gmail.com", false));
        assertTrue(sizeBefore == controller.getRoles().size());
        assertTrue(controller.getRoles().containsKey("referee@gmail.com"));
        assertThat(controller.getRoles().get("referee@gmail.com"), instanceOf(SecondaryReferee.class));
    }

    @Test
    public void addRefereeNotexist() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, IncorrectInputException, MemberAlreadyExistException, AlreadyExistException {
        //done
        thrown.expect(MemberNotExist.class);
        controller.logIn("admin@gmail.com", "123");
        int sizeBefore = controller.getRoles().size();


        /*try to add referee who doesnt exist in the system - result should be Negative*/
        assertFalse(controller.addReferee("referee@gmail.com", false));
        assertEquals(sizeBefore, controller.getRoles().size());
    }

    @Test
    public void addRefereeNoPermission() throws DontHavePermissionException, MemberAlreadyExistException, AlreadyExistException, MemberNotExist, IncorrectInputException {
        //done
        thrown.expect(DontHavePermissionException.class);
        // int sizeBefore = this.controller.getRoles().size();

        /*try to add referee with incorrect mail*/
        assertFalse(controller.addReferee("refere@gmail.com", false));
        // assertEquals(sizeBefore,controller.getRoles().size());
    }

    /*******************************************************************************/
    @Test
    public void closeTeam() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        /* init */
        controller.logIn("admin@gmail.com", "123");
        controller.addTeam("newTeam", "owner@gmail.com");
        int sizeBefore = this.controller.getTeams().size();

        /* try to close team - result should be positive */
        controller.closeTeam("newTeam");

        //if close one team and the owner had only one he becoma a fan
        assertEquals(sizeBefore - 1, controller.getTeams().size());
        assertFalse(controller.getTeams().containsKey("newTeam"));
        assertThat(controller.getRoles().get("owner@gmail.com"), instanceOf(Fan.class));


        //if close one team and the owner had 2 he is still owner
        controller.addTeam("newTeam1", "owner@gmail.com");
        controller.addTeam("newTeam2", "owner@gmail.com");
        sizeBefore = this.controller.getTeams().size();
        controller.closeTeam("newTeam1");
        assertEquals(sizeBefore - 1, controller.getTeams().size());
        assertFalse(controller.getTeams().containsKey("newTeam1"));
        assertFalse(((Owner) this.controller.getRoles().get("owner@gmail.com")).getTeams().containsKey("newTeam1"));
        assertThat(controller.getRoles().get("owner@gmail.com"), instanceOf(Owner.class));

        //    assertFalse(((Manager)this.controller.getRoles().get("manager@gmail.com")).getTeam().containsKey("newTeam"));
        //    assertFalse(((Owner)this.controller.getRoles().get("owner@gmail.com")).getTeams().containsKey("newTeam"));
        //    for (String idPlayer : idPlayers) {
        //         assertFalse(((Player)this.controller.getRoles().get(idPlayer)).getTeam().containsKey("newTeam"));
        //    }
    }

    @Test
    public void closeTeamNoPermission() throws DontHavePermissionException, ObjectNotExist, MemberNotExist, AlreadyExistException, IncorrectInputException {
        thrown.expect(DontHavePermissionException.class);
        /* try to close team without login  - result should be negative */
        controller.closeTeam("newTeam");
    }

    @Test
    public void closeTeamNotExistingTeam() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, AlreadyExistException, ObjectNotExist, IncorrectInputException {
        thrown.expect(ObjectNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com", "123");

        /* try to close team with wrong name - result should be negative */
        controller.closeTeam("newTeam");
    }

    /*******************************************************************************/
    @Test
    public void removeMember() throws MemberNotExist, PasswordDontMatchException, IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberAlreadyExistException {
        /*init*/
        controller.signIn("member", "member@gmail.com", "123", birthdate);
        controller.logIn("admin@gmail.com", "123");

        //  controller.signIn("member", "member@gmail.com", "123", birthdate);
        controller.addReferee("member@gmail.com" , false);
        assertTrue(controller.getRoles().containsKey("member@gmail.com"));
        int size = controller.getRoles().size();

        /* try to remove member who exist in the system - result should be positive*/
        controller.removeMember("member@gmail.com");
        assertFalse(controller.getRoles().containsKey("member@gmail.com"));
        assertEquals(size - 1, controller.getRoles().size());
    }

    @Test
    public void removeMemberNoPermissionException() throws DontHavePermissionException, MemberNotExist, IncorrectInputException, AlreadyExistException {
        thrown.expect(DontHavePermissionException.class);

        /* try to remove member without login  - result should be negative */
        controller.removeMember("member@gmail.com");
    }

    @Test
    public void removeMemberNotExist() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, IncorrectInputException, AlreadyExistException {
        thrown.expect(MemberNotExist.class);
        /* init */
        controller.logIn("admin@gmail.com", "123");
        int sizeBefore = controller.getRoles().size();

        /*try to remove member who not exist in the system - result should be negative*/
        controller.removeMember("member@gmail.com");
        assertEquals(sizeBefore, controller.getRoles().size());
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


    private void addTeamsCorrectly(int mumOfTeams) throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, ObjectNotExist, ObjectAlreadyExist, MemberNotExist, NoEnoughMoney, PasswordDontMatchException {
        for(int i=0 ; i< mumOfTeams ; i++){
            addUsers(i);
            addTeam(i);
            addPlayers(i);
        }
    }
    private void addUsers(int i) throws IncorrectInputException, DontHavePermissionException, AlreadyExistException {
        controller.signIn("palyer0"+i,"p0"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer1"+i,"p1"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer2"+i,"p2"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer3"+i,"p3"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer4"+i,"p4"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer5"+i,"p5"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer6"+i,"p6"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer7"+i,"p7"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer8"+i,"p8"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer9"+i,"p9"+i+"@gmail.com","1",birthdate);
        controller.signIn("palyer10"+i,"p10"+i+"@gmail.com","1",birthdate);
        controller.signIn("coach"+i,"coach"+i+"@gmail.com","1",birthdate);
        controller.signIn("manager"+i,"manager"+i+"@gmail.com","1",birthdate);
    }
    private void addTeamsWithoutPlayers(int mumOfTeams) throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, ObjectNotExist, ObjectAlreadyExist, MemberNotExist, PasswordDontMatchException, NoEnoughMoney {
        for(int i=0 ; i< mumOfTeams ; i++){
            addTeam(i);
        }
        for(int i=0; i<mumOfTeams; i++){
            controller.addTeamToLeagueInSeason("league","2020","team"+i);
        }
    }
    private void addTeamsToLeagueSeason(int mumOfTeams) throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, ObjectNotExist, ObjectAlreadyExist, MemberNotExist, PasswordDontMatchException, NoEnoughMoney {
        for(int i=0 ; i< mumOfTeams ; i++){
            addTeam(i);
        }
    }
    private void addPlayers(int i) throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException, ObjectNotExist, IncorrectInputException, NoEnoughMoney, AlreadyExistException {
        controller.logIn("owner"+i+"@gmail.com","1");

        controller.setMoneyToAccount("team"+i,10000);
        controller.addPlayer("p0"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p1"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p2"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p3"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p4"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p5"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p6"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p7"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p8"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p9"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.addPlayer("p10"+i+"@gmail.com","team"+i,1993,10,12,"df");
        controller.logOut();
    }
    private void addTeam(int i) throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberNotExist, PasswordDontMatchException, ObjectAlreadyExist, ObjectNotExist, NoEnoughMoney {
        controller.signIn("owner"+i,"owner"+i+"@gmail.com","1",birthdate);
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team"+i,"owner"+i+"@gmail.com");
        controller.logOut();
    }
    private void enterReferee(int numOfReferees) throws IncorrectInputException, DontHavePermissionException, AlreadyExistException {
        for(int i=0; i<numOfReferees; i++)
        controller.signIn("referee"+i, "referee"+i+"@gmail.com", "123", birthdate);
    }
    private void addRefereesToLeagueInSeason(int numOfReferees) throws DontHavePermissionException, IncorrectInputException, MemberAlreadyExistException, MemberNotExist, AlreadyExistException, PasswordDontMatchException {

        controller.logIn("admin@gmail.com", "123");
        for(int i=0 ; i<numOfReferees/2;i++)
            controller.addReferee("referee"+i+"@gmail.com", false);
        for(int i=numOfReferees/2 ; i<numOfReferees;i++)
            controller.addReferee("referee"+i+"@gmail.com", true);
        controller.logOut();

        controller.logIn("dani@gmail.com", "123");
        for(int i=0 ; i<numOfReferees;i++)
            controller.addRefereeToLeagueInSeason("league","2020","referee"+i+"@gmail.com");
        controller.logOut();
    }

}
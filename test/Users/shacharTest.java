package Users;

import Exception.*;
import Game.Game;
import Game.Team;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import system.SystemController;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class shacharTest {
    SystemController controller = new SystemController("");
    Date birthdate = new Date(1993, 10, 12);

    public shacharTest() throws DontHavePermissionException, AlreadyExistException, MemberNotExist, IncorrectInputException {
    }


    @Before
    public void init() throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberNotExist, PasswordDontMatchException {
        controller.signIn("owner", "owner@gmail.com", "1", birthdate);
        controller.signIn("systemManager","systemManager@gmail.com" ,"1" , birthdate );
        controller.signIn("referee", "referee@gmail.com", "123", birthdate);
    }

    @Rule
    public final ExpectedException thrown = ExpectedException.none();




    /*******************************************************************************/
    @Test
    public void removeOwner() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        controller.logIn("admin@gmail.com", "123");
        int sizeBefore = this.controller.getRoles().size();
        controller.addTeam("test" ,"owner@gmail.com");
        controller.closeTeam("test");

        /* try to add system manager - result should be positive */
        controller.removeOwner("owner@gmail.com");
        assertFalse(controller.getRoles().containsKey("owner@gmail.com"));
        assertEquals(sizeBefore, controller.getRoles().size());
        assertTrue(controller.getRoles().get("owner@gmail.com") instanceof Fan);

    }

    @Test
    public void removeOwnerWIthTeam() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        thrown.expect(NotReadyToDelete.class);

        controller.logIn("admin@gmail.com", "123");
        int sizeBefore = this.controller.getRoles().size();
        controller.addTeam("test" ,"owner@gmail.com");

        /* try to add system manager - result should be positive */
        controller.removeOwner("owner@gmail.com");
        assertTrue(controller.getRoles().containsKey("owner@gmail.com"));
        assertEquals(sizeBefore+1, controller.getRoles().size());

    }


    /*******************************************************************************/
    @Test
    public void addSystemManager() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        controller.signIn("systemManager","systemManager@gmail.com" ,"1" , birthdate );
        controller.logIn("admin@gmail.com", "123");
        int sizeBefore = this.controller.getRoles().size();

        /* try to add system manager - result should be positive */

        controller.addSystemManager("systemManager@gmail.com");
       // assertTrue(controller.getSystemManager().containsKey("systemManager@gmail.com"));
        assertEquals(sizeBefore+1, controller.getRoles().size());
    }

    /*******************************************************************************/
    @Test
    public void removeSystemManager() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        controller.signIn("systemManager","systemManager@gmail.com" ,"1" , birthdate );
        controller.logIn("admin@gmail.com", "123");
        int sizeBefore = this.controller.getRoles().size();
        controller.addSystemManager("systemManager@gmail.com");

        /* try to remove system manager - result should be positive */

        controller.removeSystemManager("systemManager@gmail.com");
       // assertFalse(controller.getSystemManager().containsKey("systemManager@gmail.com"));
        assertEquals(sizeBefore, controller.getRoles().size());
        assertTrue(controller.getRoles().get("systemManager@gmail.com") instanceof Fan);

    }

    @Test
    public void removeSystemManagerTheLastOne() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        thrown.expect(NotReadyToDelete.class);

        controller.logIn("admin@gmail.com", "123");
        int sizeBefore = this.controller.getRoles().size();

        /* try to remove system manager - result should be positive */

        controller.removeSystemManager("admin@gmail.com");
      //  assertTrue(controller.getSystemManager().containsKey("admin@gmail.com"));
        assertEquals(sizeBefore, controller.getRoles().size());
    }

    /*******************************************************************************/
    @Test
    public void removeAssociationDeligate() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        controller.signIn("assocaiation","association@gmail.com" ,"1" , birthdate );
        controller.logIn("admin@gmail.com", "123");
        int sizeBefore = this.controller.getRoles().size();
        controller.addAssociationDelegate("association@gmail.com");

        /* try to remove association deligate - result should be positive */

        controller.removeAssociationDelegate("association@gmail.com");
        assertFalse(controller.getRoles().containsKey("association@gmail.com"));
        assertEquals(sizeBefore, controller.getRoles().size());
        assertTrue(controller.getRoles().get("association@gmail.com") instanceof Fan);
    }
    /*******************************************************************************/
    @Test
    public void addAssocaitionDeligate() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        controller.signIn("assocaiation","association@gmail.com" ,"1" , birthdate );
        controller.logIn("admin@gmail.com", "123");
        int sizeBefore = this.controller.getRoles().size();

        /* try to add association deligate - result should be positive */

        controller.addAssociationDelegate("association@gmail.com");
        assertTrue(controller.getRoles().containsKey("association@gmail.com"));
        assertEquals(sizeBefore+1, controller.getRoles().size());
    }

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


    /*******************************************************************************/
    @Test
    public void addNewTeam() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        controller.logIn("admin@gmail.com", "123");
        int sizeBeforeT = this.controller.getTeams().size();

        /* try to add team - result should be positive */

        controller.addTeam("test", "owner@gmail.com");
        assertTrue(controller.getTeams().containsKey("test"));
        assertEquals(sizeBeforeT + 1, controller.getTeams().size());
        assertTrue(( this.controller.getRoles().get("owner@gmail.com")) instanceof Owner);
        assertTrue(((Owner) this.controller.getRoles().get("owner@gmail.com")).getTeams().containsKey("newTeam"));
    }

    @Test
    public void addNewTeamTwice() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        thrown.expect(ObjectAlreadyExist.class);

        controller.logIn("admin@gmail.com", "123");
        int sizeBeforeT = this.controller.getTeams().size();

        /* try to add team - result should be positive */

        controller.addTeam("test", "owner@gmail.com");
        assertTrue(controller.getTeams().containsKey("test"));
        assertEquals(sizeBeforeT + 1, controller.getTeams().size());
        assertTrue(( this.controller.getRoles().get("owner@gmail.com")) instanceof Owner);
        assertTrue(((Owner) this.controller.getRoles().get("owner@gmail.com")).getTeams().containsKey("newTeam"));

        controller.addTeam("test", "owner@gmail.com");
        assertTrue(controller.getTeams().containsKey("test"));
        assertEquals(sizeBeforeT + 1, controller.getTeams().size());
        assertTrue(( this.controller.getRoles().get("owner@gmail.com")) instanceof Owner);
        assertTrue(((Owner) this.controller.getRoles().get("owner@gmail.com")).getTeams().containsKey("newTeam"));

    }

    @Test
    public void addNewTeamNoOwner() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        thrown.expect(ObjectAlreadyExist.class);

        controller.logIn("admin@gmail.com", "123");
        int sizeBeforeT = this.controller.getTeams().size();

        /* try to add team - result should be positive */

        controller.addTeam("test", "owner1@gmail.com");
        assertFalse(controller.getTeams().containsKey("test"));
        assertEquals(sizeBeforeT , controller.getTeams().size());
        assertFalse(( this.controller.getRoles().get("owner@gmail.com")) instanceof Owner);
        assertFalse(((Owner) this.controller.getRoles().get("owner@gmail.com")).getTeams().containsKey("newTeam"));
    }

    @Test
    public void addteamNameWrong() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        thrown.expect(IncorrectInputException.class);

        controller.logIn("admin@gmail.com", "123");
        int sizeBeforeT = this.controller.getTeams().size();

        /* try to add team - result should be positive */

        controller.addTeam("123", "owner@gmail.com");
        assertFalse(controller.getTeams().containsKey("123"));
        assertEquals(sizeBeforeT , controller.getTeams().size());
        assertFalse(( this.controller.getRoles().get("owner@gmail.com")) instanceof Owner);
        assertFalse(((Owner) this.controller.getRoles().get("owner@gmail.com")).getTeams().containsKey("123"));


    }

    /*******************************************************************************/
    @Test
    public void removeReferee() throws MemberNotExist, PasswordDontMatchException, IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberAlreadyExistException {

        controller.logIn("admin@gmail.com", "123");
        int sizeBefore = controller.getRoles().size();
        controller.addReferee("referee@gmail.com", false);

        /* try to remove referee who already exist in the system - result should be positive*/
        controller.removeReferee("referee@gmail.com");
        assertEquals(sizeBefore, controller.getRoles().size());
        assertTrue(controller.getRoles().get("referee@gmail.com") instanceof Fan);
    }

    @Test
    public void removeRefereeWithFutureGame() throws MemberNotExist, PasswordDontMatchException, IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberAlreadyExistException {
        thrown.expect(IncorrectInputException.class);

        controller.logIn("admin@gmail.com", "123");
        int sizeBefore = controller.getRoles().size();

        controller.addReferee("referee@gmail.com", false);
      //  Referee referee=controller.getReferee("referee@gmail.com");
      //  referee.addGame(new Game(null , null , null , null , null , null, null, null));
        /* try to remove referee who already exist in the system - result should be positive*/
        controller.removeReferee("referee@gmail.com");
        assertEquals(sizeBefore+1, controller.getRoles().size());
        assertFalse(controller.getRoles().get("referee@gmail.com") instanceof Fan);
    }

    /*******************************************************************************/
    @Test
    public void addRefereeSecondary() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, IncorrectInputException, AlreadyExistException, MemberAlreadyExistException {
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
    public void addRefereeMain() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException, IncorrectInputException, AlreadyExistException, MemberAlreadyExistException {
        controller.signIn("referee", "referee@gmail.com", "1", birthdate);

        controller.logIn("admin@gmail.com", "123");
        int sizeBefore = controller.getRoles().size();


        /*try to add referee who  exist in the system as fan - result should be positive*/
        assertTrue(controller.addReferee("referee@gmail.com", true));
        assertTrue(sizeBefore == controller.getRoles().size());
        assertTrue(controller.getRoles().containsKey("referee@gmail.com"));
        assertThat(controller.getRoles().get("referee@gmail.com"), instanceOf(MainReferee.class));
    }

    /*******************************************************************************/
    @Test
    public void closeTeam() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        controller.logIn("admin@gmail.com", "123");
        controller.addTeam("newTeam", "owner@gmail.com");
        int sizeBefore = this.controller.getTeams().size();

        /* try to close team - result should be positive */
        controller.closeTeam("newTeam");

        //if close one team and the owner had only one he becoma a fan
        assertEquals(sizeBefore - 1, controller.getTeams().size());
        assertFalse(controller.getTeams().containsKey("newTeam"));
        assertThat(controller.getRoles().get("owner@gmail.com"), instanceOf(Fan.class));
    }
    @Test
    public void close1TeamExist2() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {

                controller.logIn("admin@gmail.com", "123");
                controller.addTeam("newTeam1", "owner@gmail.com");
                controller.addTeam("newTeam2", "owner@gmail.com");
                int sizeBefore = this.controller.getTeams().size();

            //if close one team and the owner had 2 he is still owner

        controller.closeTeam("newTeam1");
        assertEquals(sizeBefore - 1, controller.getTeams().size());
        assertFalse(controller.getTeams().containsKey("newTeam1"));
        assertFalse(((Owner) this.controller.getRoles().get("owner@gmail.com")).getTeams().containsKey("newTeam1"));
        assertThat(controller.getRoles().get("owner@gmail.com"), instanceOf(Owner.class));

    }
    @Test
    public void closeTeamNotExist() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        thrown.expect(ObjectNotExist.class);

        controller.logIn("admin@gmail.com", "123");
        controller.addTeam("newTeam", "owner@gmail.com");
        int sizeBefore = this.controller.getTeams().size();

        /* try to close team - result should be positive */
        controller.closeTeam("newTeam1");

        //if close one team and the owner had only one he becoma a fan
        assertEquals(sizeBefore , controller.getTeams().size());
        assertTrue(controller.getTeams().containsKey("newTeam"));
        assertThat(controller.getRoles().get("owner@gmail.com"), instanceOf(Owner.class));
    }
    @Test
    public void closeTeamWithGames() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, IncorrectInputException {
        thrown.expect(IncorrectInputException.class);


        controller.logIn("admin@gmail.com", "123");
        controller.addTeam("newTeam", "owner@gmail.com");
        int sizeBefore = this.controller.getTeams().size();

        Team team=controller.getTeamByName("newTeam");
        team.addGame(new Game(null , null , null , null , null , null, null, null));
        /* try to close team - result should be positive */
        controller.closeTeam("newTeam");

        //if close one team and the owner had only one he becoma a fan
        assertEquals(sizeBefore , controller.getTeams().size());
        assertTrue(controller.getTeams().containsKey("newTeam"));
        assertThat(controller.getRoles().get("owner@gmail.com"), instanceOf(Owner.class));
    }


    /*******************************************************************************/
    @Test
    public void removeMemberReferee() throws MemberNotExist, PasswordDontMatchException, IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberAlreadyExistException {
        /*init*/
        controller.logIn("admin@gmail.com", "123");

        controller.addReferee("referee@gmail.com", false);
        assertTrue(controller.getRoles().containsKey("referee@gmail.com"));
        int size = controller.getRoles().size();

        /* try to remove member who exist in the system - result should be positive*/
        controller.removeMember("referee@gmail.com");
        assertFalse(controller.getRoles().containsKey("referee@gmail.com"));
        assertEquals(size - 1, controller.getRoles().size());
    }
    @Test
    public void removeMemberSystemManager() throws MemberNotExist, PasswordDontMatchException, IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberAlreadyExistException {
        /*init*/
        controller.logIn("admin@gmail.com", "123");

        controller.addReferee("systemManager@gmail.com", false);
       // assertTrue(controller.getSystemManager().containsKey("systemManager@gmail.com"));
        int size = controller.getRoles().size();

        /* try to remove member who exist in the system - result should be positive*/
        controller.removeMember("systemManager@gmail.com");
        assertFalse(controller.getRoles().containsKey("systemManager@gmail.com"));
        assertEquals(size - 1, controller.getRoles().size());
    }
    @Test
    public void removeMemberFan() throws MemberNotExist, PasswordDontMatchException, IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberAlreadyExistException {
        /*init*/
        controller.signIn("fan" , "fan@gmail.com" , "123" ,birthdate);
        controller.logIn("admin@gmail.com", "123");
        int size = controller.getRoles().size();

        /* try to remove member who exist in the system - result should be positive*/
        controller.removeMember("fan@gmail.com");
        assertFalse(controller.getRoles().containsKey("fan@gmail.com"));
        assertEquals(size - 1, controller.getRoles().size());
    }


    /*******************************************************************************/
    @Test
    public void watchComplaint() throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException {
        controller.logIn("admin@gmail.com", "123");
        controller.watchComplaint("\\complaint.txt");
    }

    /*******************************************************************************/
    @Test
    public void responseComplaint() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException {
        controller.logIn("admin@gmail.com", "123");
        LinkedList<Pair<String,String>> fortry=new LinkedList<>();
        fortry.add(new Pair<>("1" , "try1"));
        fortry.add(new Pair<>("2" ,"try2"));
        fortry.add(new Pair<>("3" ,"try3"));
        controller.responseComplaint("\\complaint.txt" , fortry);
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
    private void addRefereesToLeagueInSeason(int numOfReferees) throws DontHavePermissionException, IncorrectInputException, MemberAlreadyExistException, MemberNotExist, AlreadyExistException, PasswordDontMatchException, ObjectNotExist {

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
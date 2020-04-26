package acceptanceTesting;

import DataBase.DBController;
import Domain.Asset.Field;
import Domain.Asset.Player;
import Domain.Game.*;
import Domain.Users.Fan;
import Domain.Users.Member;
import Service.SystemController;
import Exception.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;


public class FanTest {

    private SystemController controller = new SystemController("");
    Date birthdate=new Date(1993,10,12);

    @Before
    public void init() throws IncorrectInputException, AlreadyExistException, DontHavePermissionException, MemberNotExist, PasswordDontMatchException, MemberAlreadyExistException, NoEnoughMoney, ObjectNotExist {
        addFans(5);
        controller.signIn("referee","referee0@gmail.com","123" ,birthdate);
        controller.logIn("admin@gmail.com","123");
        controller.addReferee("referee0@gmail.com",false);
        controller.logOut();
    }

    @Rule
    public final ExpectedException thrown= ExpectedException.none();


    @Test
    public void updateDetails() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, AlreadyExistException, ObjectNotExist {
        /* init */
        Member member= controller.logIn("f0@gmail.com","123");

        /*try to update details after login with correct values- result should be positive */
        controller.updatePersonalDetails("funfan","f0@gmail.com");

        HashMap<String, Fan> fans = controller.getFans();

        assertNotNull(fans.containsKey("f0@gmail.com"));
        assertEquals(fans.get("f0@gmail.com").getName(), "funfan");

        controller.updatePersonalDetails("funfan","fan0@gmail.com");
        fans = controller.getFans();
        assertEquals(fans.get("fan0@gmail.com").getName(), "funfan");
    }


    @Test
    public void updateDetailsNullDetail() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, AlreadyExistException {
        thrown.expect(IncorrectInputException.class);
        /* init */
        Member member= controller.logIn("f0@gmail.com","123");

        /*try to update details after login with incorrect values- should throw Exception */
        controller.updatePersonalDetails(null,"fan0@gmail.com");

    }

    @Test
    public void updateDetailsEmptyDetail() throws MemberNotExist, PasswordDontMatchException, DontHavePermissionException, IncorrectInputException, AlreadyExistException {
        /* init */
        Member member= controller.logIn("f0@gmail.com","123");

        /*try to update details after login with empty detail- the detail doesnt change */
        controller.updatePersonalDetails("","f0@gmail.com");

        HashMap<String, Fan> fans = controller.getFans();

        assertNotNull(fans.containsKey("f0@gmail.com"));
        assertEquals(fans.get("f0@gmail.com").getName(), "fan05");
    }

    @Test
    public void testGameFollowing() throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException {
        Member member= controller.logIn("f0@gmail.com","123");
        DBController dbController = new DBController();
        ArrayList<Transaction> transM = new ArrayList<>();
        ArrayList<Transaction> transH = new ArrayList<>();
        Field field = new Field("Blumfield");
        Team maccabi = new Team("Maccabi tel aviv", new Account(transM, 100), field);
        Team hapoel = new Team("Hapoel tel aviv", new Account(transH, 100), field);
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, 1999);
        date.set(Calendar.MONTH, 7);
        date.set(Calendar.DAY_OF_MONTH, 26);
        Game derbi = new Game("derbi" , date ,maccabi, hapoel, field, null, null, null);
        controller.addFollowerToGame(derbi);
        derbi.addEvent(new Event(new Date(), "blabla", EventInGame.FOUL,60, null));
        assertEquals(derbi.getFollowersNumber(),1);
    }


    @Test
    public void testGameNotifications() throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException {
        Member member= controller.logIn("f0@gmail.com","123");
        DBController dbController = new DBController();
        ArrayList<Transaction> transM = new ArrayList<>();
        ArrayList<Transaction> transH = new ArrayList<>();
        Field field = new Field("Blumfield");
        Team maccabi = new Team("Maccabi tel aviv", new Account(transM, 100), field);
        Team hapoel = new Team("Hapoel tel aviv", new Account(transH, 100), field);
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, 1999);
        date.set(Calendar.MONTH, 7);
        date.set(Calendar.DAY_OF_MONTH, 26);
        Game derbi = new Game("derbi" , date ,maccabi, hapoel, field, null, null, null);
        controller.addFollowerToGame(derbi);
        derbi.addEvent(new Event(new Date(), "blabla", EventInGame.FOUL,60, null));
        assertEquals(((Fan)controller.getConnectedUser()).getUpdates().size(),1);
    }

    @Test
    public void testTeamFollowing() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException {
        Member member = controller.logIn("f0@gmail.com", "123");
        ArrayList<Transaction> trans = new ArrayList<>();
        Field field = new Field("Blumfield");
        Team maccabi = new Team("Maccabi tel aviv", new Account(trans, 100), field);
        controller.addFollowerToTeam(maccabi);
        Field field2 = new Field("Teddy");
        maccabi.addField(field2);
        Player messi = new Player("Leo Messi", "messi@gmail.com", "1234", new Date(), "blabla");
        maccabi.addPlayer(messi);
        assertEquals(maccabi.getFollowersNumber(), 1);
    }


    @Test
    public void testTeamNotifications() throws DontHavePermissionException, MemberNotExist, PasswordDontMatchException {
        Member member= controller.logIn("f0@gmail.com","123");
        ArrayList<Transaction> trans = new ArrayList<>();
        Field field = new Field("Blumfield");
        Team maccabi = new Team("Maccabi tel aviv", new Account(trans, 100), field);
        controller.addFollowerToTeam(maccabi);
        Field field2 = new Field("Teddy");
        maccabi.addField(field2);
        Player messi = new Player("Leo Messi", "messi@gmail.com", "1234", new Date(), "blabla");
        maccabi.addPlayer(messi);
        assertEquals(((Fan)controller.getConnectedUser()).getUpdates().size(),2);
    }



/**********************************************************************************************************/

    private void addTeamsCorrectly(int mumOfTeams) throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, ObjectNotExist, ObjectAlreadyExist, MemberNotExist, NoEnoughMoney, PasswordDontMatchException {
        for(int i=0 ; i< mumOfTeams ; i++){
            addUsers(i);
            addTeam(i);
            addPlayers(i);
            addFieldToTeam(i);
            addFans(i);
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
    private void addTeamsToLeagueSeason(int mumOfTeams) throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, ObjectNotExist, ObjectAlreadyExist, MemberNotExist, PasswordDontMatchException, NoEnoughMoney {
        for(int i=0 ; i< mumOfTeams ; i++){
            controller.logIn("dani@gmail.com", "123");
            controller.addTeamToLeagueInSeason("league","2020","team"+i);
            controller.logOut();
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
        controller.addCoach("team"+i ,"coach"+i+"@gmail.com" );
        controller.addManager("team"+i , "manager"+i+"@gmail.com");
        controller.logOut();
    }
    private void addFieldToTeam(int i) throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException, ObjectNotExist, IncorrectInputException, NoEnoughMoney, AlreadyExistException, ObjectAlreadyExist {
        controller.logIn("owner"+i+"@gmail.com","1");
        controller.addField("team"+i,"f"+i);
        controller.logOut();
    }
    private void addTeam(int i) throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberNotExist, PasswordDontMatchException, ObjectAlreadyExist, ObjectNotExist, NoEnoughMoney {
        controller.signIn("owner"+i,"owner"+i+"@gmail.com","1",birthdate);
        controller.logIn("admin@gmail.com","123");
        controller.addTeam("team"+i,"owner"+i+"@gmail.com");
        controller.logOut();
    }
    private void enterReferee(int numOfReferees) throws IncorrectInputException, DontHavePermissionException, AlreadyExistException {
        for(int i=1; i<numOfReferees; i++)
            controller.signIn("referee"+i, "referee"+i+"@gmail.com", "123", birthdate);
    }
    private void addRefereesToLeagueInSeason(int numOfReferees) throws DontHavePermissionException, IncorrectInputException, MemberAlreadyExistException, MemberNotExist, AlreadyExistException, PasswordDontMatchException, ObjectNotExist {

        controller.logIn("admin@gmail.com", "123");
        for(int i=1 ; i<numOfReferees/2;i++)
            controller.addReferee("referee"+i+"@gmail.com", false);
        for(int i=numOfReferees/2 ; i<numOfReferees;i++)
            controller.addReferee("referee"+i+"@gmail.com", true);
        controller.logOut();

        controller.logIn("dani@gmail.com", "123");
        for(int i=0 ; i<numOfReferees;i++)
            controller.addRefereeToLeagueInSeason("league","2020","referee"+i+"@gmail.com");
        controller.logOut();
    }


    private void addFans(int i) throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException, ObjectNotExist, IncorrectInputException, NoEnoughMoney, AlreadyExistException {
        controller.signIn("fan0"+i,"f0@gmail.com","123",birthdate);
        controller.signIn("fan1"+i,"f1@gmail.com","1",birthdate);
        controller.signIn("fan2"+i,"f2@gmail.com","1",birthdate);
        controller.signIn("fan3"+i,"f3@gmail.com","1",birthdate);
        controller.signIn("fan4"+i,"f4@gmail.com","1",birthdate);
        controller.signIn("fan5"+i,"f5@gmail.com","1",birthdate);
        controller.signIn("fan6"+i,"f6@gmail.com","1",birthdate);
        controller.signIn("fan7"+i,"f7@gmail.com","1",birthdate);
    }

}

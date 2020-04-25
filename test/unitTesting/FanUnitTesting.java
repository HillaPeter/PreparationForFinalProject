package unitTesting;
import Domain.Asset.Field;
import Domain.Asset.Player;
import Domain.Game.*;
import Domain.League.LeagueInSeason;
import Domain.League.SchedulingPolicyAllTeamsPlayTwice;
import Domain.League.ScorePolicy;
import Domain.Users.Fan;
import Domain.Users.Referee;
import Exception.*;
import Service.SystemController;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class FanUnitTesting {

    @Test

    public void testTeamNotifications() {
        ArrayList<Transaction> trans = new ArrayList<>();
        Field field = new Field("Blumfield");
        Team maccabi = new Team("Maccabi tel aviv", new Account(trans, 100), field);
        Fan fan = new Fan("Hilla", "hilla@gmail.com", "1234", new Date());
        fan.followTeam(maccabi);
        Field field2 = new Field("Teddy");
        maccabi.addField(field2);
        Player messi = new Player("Leo Messi", "messi@gmail.com", "1234", new Date(), "blabla");
        maccabi.addPlayer(messi);
        assertEquals(fan.getUpdates().size(),2);
    }

    @Test
    public void testGameNotifications(){
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
        Fan fan = new Fan("Hilla", "hilla@gmail.com", "1234", new Date());
        fan.followGame(derbi);
        derbi.addEvent(new Event(new Date(), "blabla", EventInGame.FOUL,60, null));
        assertEquals(fan.getUpdates().size(),1);
    }


}

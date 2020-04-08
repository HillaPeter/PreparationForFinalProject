package Users;

import Asset.Player;
import org.junit.Rule;
import org.junit.Test;
import Exception.*;
import org.junit.rules.ExpectedException;
import system.SystemController;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class GuestTest {
    SystemController controller = new SystemController("test controller");
    @Test
    public void getName() {
        Guest guest = new Guest();
        assertEquals("guest",guest.getName());
        assertNotEquals("n",guest.getName());
    }
    @Rule
    public final ExpectedException thrown= ExpectedException.none();
    @Test
    public void signInWithException() throws MemberAlreadyExistException {
        thrown.expect(MemberAlreadyExistException.class);

        /*try to sign in but he is a member now*/
        Player player = new Player("noa","noa@gmail.com","123",null,"");
        controller.addPlayer(player);
        controller.signIn("noa", "noa@gmail.com", "11123");
    }
    @Test
    public void signIn() {
        boolean notThrown = true;
        Player player = new Player("noa","noa@gmail.com","123",null,"");
        controller.addPlayer(player);
        /*try to sign in with correct inputs  - result should be correct*/
        try {
            Member newMember = controller.signIn("noa", "noa2@gmail.com", "123");
            assertTrue(notThrown);
            assertNotNull(newMember);
            assertThat(newMember, instanceOf(Fan.class));
            assertTrue(controller.existFan(newMember.getUserMail()));
        } catch (MemberAlreadyExistException e) {
            notThrown = false;
        }
        assertTrue(notThrown);
    }
    @Test
    public void logInWithException() throws MemberNotExist, PasswordDontMatchException {
        thrown.expect(MemberNotExist.class);
        controller.addPlayer(new Player("n","noa@gmail.com",null,""));

        /*try to log in with not exist member - result should be negative*/
        Member member = controller.logIn("notExist@gmail.com","1223");
        assertNull(member);
    }
    @Test
    public void logInIncorrectPassword() throws MemberNotExist, PasswordDontMatchException {
        thrown.expect(PasswordDontMatchException.class);
        Player player = new Player("noa","noa@gmail.com","123",null,"");
        controller.addPlayer(player);

        /*try to log in with different password - result should be negative*/
        controller.logIn("noa@gmail.com","1223");
    }
    @Test
    public void logIn() throws MemberNotExist, PasswordDontMatchException {
        Player player = new Player("noa","noa@gmail.com","123",null,"");
        controller.addPlayer(player);
        assertTrue(controller.ifMemberExistTesting("noa@gmail.com"));

        /*try to log in with correct details - result should be positive*/
        Member member = controller.logIn("noa@gmail.com","123");
        assertNotNull(member);
        assertThat(player, instanceOf(Player.class));
    }
}
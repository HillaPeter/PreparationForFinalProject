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
        Player player = new Player("noa","noa@gmail.com","123",null,"");
        controller.addPlayer(player);
        controller.signIn("noa", "noa@gmail.com", "1--23");
    }
    @Test
    public void signIn() {
        boolean notThrown = true;
        Player player = new Player("noa","noa@gmail.com","123",null,"");
        controller.addPlayer(player);
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
    public void logInWithException() throws MemberDontExist {
        thrown.expect(MemberDontExist.class);
        controller.addPlayer(new Player("n","noa@gmail.com",null,""));
        Member member = controller.logIn("notExist@gmail.com","1223");
        assertNull(member);
    }
    @Test
    public void logInIncorrectPassword() throws MemberDontExist, MemberAlreadyExistException {
        controller.signIn("noa","noa@gmail.com","123");
        Member member = controller.logIn("noa@gmail.com","1223");
        assertNotNull(member);
        //todo - incorrect password exception
    }
    @Test
    public void logIn() throws MemberDontExist, MemberAlreadyExistException {
        controller.signIn("noa","noa@gmail.com","123Nn");
        Member member = controller.logIn("noa@gmail.com","123Nn");
        assertNotNull(member);
    }
}
package Users;

import org.junit.Rule;
import org.junit.Test;
import Exception.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class GuestTest {

    @Test
    public void getName() {
        Guest guest = new Guest();
        assertEquals("guest",guest.getName());
        assertNotEquals("n",guest.getName());
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void whenExceptionThrown_thenRuleIsApplied() {
        exceptionRule.expect(NumberFormatException.class);
        exceptionRule.expectMessage("For input string");
        Integer.parseInt("1a");
    }
    @Test (expected = IncorrectInputException.class)
    public void signIn() {
        Guest guest = new Guest();
        //guest.signIn();
    }

    @Test
    public void logIn() {
    }
}
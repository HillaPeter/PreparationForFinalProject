package Users;
import java.util.Scanner;
import Exception.*;
public class Guest extends Role{

    public Guest() {
        super("guest");
    }

    /**
     * This function fill the signIn-form with user mail, user name and user password
     * @return String array - details[mail,name,password]
     */
    public String[] signIn() throws IncorrectPasswordInputException, IncorrectInputException{
        return null;
        //delete this function
    }


    /**
     * this function fill the logIn-form : user name , user password
     * @return String array  - details[name,password]
     */
    public String[] logIn(){
       return null;
       //delete this function
    }
}

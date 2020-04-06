package Users;
import java.util.Scanner;

public class Guest extends Role{

    public Guest() {
        super("guest");
    }

    /**
     * This function fill the signIn-form with user mail, user name and user password
     * @return String array - details[mail,name,password]
     */
    public String[] signIn(){
        String[] details = {};
        Scanner scanInput = new Scanner(System.in);

        System.out.println("please enter your mail");
        String mailInput = scanInput.nextLine();
        //todo - check input af mail
        System.out.println("please enter full name");
        String nameInput = scanInput.nextLine();
        System.out.println("please enter password - contains only numbers and letters");
        String password = scanInput.nextLine();
        System.out.println("please verify your password");
        String password2 = scanInput.nextLine();
        //todo - check input of password - only numbers and letters

        while (password.compareTo(password2) == 0){
            System.out.println("you entered two different password");
            System.out.println("please enter password");
            password = scanInput.nextLine();
            System.out.println("please verify your password");
            password2 = scanInput.nextLine();
        }

        System.out.println("your details entered successfully!\nplease wait for confirmation");
        details[0]=mailInput;
        details[1]=nameInput;
        details[2]=password;
        return details;
    }

    /**
     * this function fill the logIn-form : user name , user password
     * @return String array  - details[name,password]
     */
    public String[] logIn(){
        String[] details = {,};
        Scanner scanInput = new Scanner(System.in);
        System.out.println("please enter full name");
        String nameInput = scanInput.nextLine();
        System.out.println("please enter password");
        String password = scanInput.nextLine();
        details[0]=nameInput;
        details[1]=password;
        return details;
    }
}


import Asset.*;
import Exception.*;
import Game.Account;
import Game.Team;
import Game.Transaction;
import Users.*;
import system.SecurityMachine;
import system.SystemController;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
/****************************************************menu******************************************************/
        Role member;
        String input ="";
        Scanner scanInput = new Scanner(System.in);
        SystemController controller = new SystemController("System Controller");
        /*
        Guest menu
         */
        while (!input.equals("Exit")) {
            System.out.println("choose one of the following options:\n");
            System.out.println("write \"1\" for signIn.");
            System.out.println("write \"2\" for logIn.");
            System.out.println("\nwrite \"Exit\" if you want to finish. \n");
            input = "";
            while (input.equals("")){
                input = scanInput.nextLine();
            }
            switch (input){

                case "1":{
                    try {
                        String[] details = fillFormSignIn();
                        try {
                            member = controller.signIn(details[1],details[0],details[2]);
                            System.out.println("succseed to signIn!!");
                            showMenu(member);
                        } catch (MemberAlreadyExistException e) {
                            System.out.println("this mail is already exist in the system.\ntry again with a different mai.");
                        }

                    } catch (IncorrectPasswordInputException e) {
                        System.out.println("you entered wrong password - please enter password that contains only numbers and letters.");
                    } catch (IncorrectInputException e) {
                        System.out.println("you entered invalid mail.");
                    }
                }break;

                case "2":{
                    String[] details = fillFormLogIn();
                    try {
                        member = controller.logIn(details[1],details[0]);
                        showMenu(member);
                    } catch (MemberDontExist e) {
                        System.out.println("This member mail is doesnt exist in the system.\nlog in with different mail");
                    }
                }break;

                case "Exit":{

                }
            }
        }


/***************************************************tests********************************************************/
  /*      SecurityMachine securityMachine = new SecurityMachine();
        String afterEncrtypt = securityMachine.encrypt("stamLibdok", "key");
        String afterDycrypt = securityMachine.decrypt(afterEncrtypt, "key");
        System.out.println(afterEncrtypt);
        System.out.println(afterDycrypt);


        SystemController SystemController = new SystemController("shachar");

        LinkedList<Player> players1 = new LinkedList<>();
        LinkedList<Player> players2 = new LinkedList<>();
        LinkedList<Integer> onlyTheIdPlayers1 = new LinkedList<>();
        LinkedList<Integer> onlyTheIdPlayers2 = new LinkedList<>();

        for (int i = 0; i < 11; i++) {
            players1.add(new Player("jordi" + i, "1", "" + i + i + i, new Date(i, i, i + i), "Player"));
            players2.add(new Player("static" + i, "2", "" + i + i + i, new Date(i, i, i + i), "Player"));
            onlyTheIdPlayers1.add(i);
            onlyTheIdPlayers2.add(i);
        }
        for (int i = 0; i < 11; i++) {
            SystemController.addPlayer(players1.get(i));
            SystemController.addPlayer(players2.get(i));
        }
        LinkedList<Integer> onlyTheIdCoach1 = new LinkedList<>();
        LinkedList<Integer> onlyTheIdManager21 = new LinkedList<>();
        LinkedList<Integer> onlyTheIdOwner1 = new LinkedList<>();


        Coach coach1 = new Coach("romi", "romi@gmaill.com", "123", "Coach");
        Coach coach2 = new Coach("yosi", "yosi@gmaill.com", "124", "Coach");
        SystemController.addCoach(coach1);
        onlyTheIdCoach1.add(123);
        SystemController.addCoach(coach1);
        Manager manager1 = new Manager("shlomi", "shlomi@gmaill.com", "125");
        Manager manager2 = new Manager("lior", "lior@gmaill.com", "126");
        SystemController.addManager(manager1);
        onlyTheIdManager21.add(125);
        SystemController.addManager(manager2);
        Owner owner1 = new Owner("daniel", "daniel@gmail.com", "127");
        Owner owner2 = new Owner("ben-el", "ben-el@gmail.com", "128");
        SystemController.addOwner(owner1);
        onlyTheIdOwner1.add(127);
        SystemController.addOwner(owner2);
        SystemManager systemManager = new SystemManager("shachar", "shachar@gmail.com", "208240275", SystemController);
        SystemController.addSystemManager(systemManager);
        systemManager.addNewTeam(onlyTheIdPlayers1, onlyTheIdCoach1, onlyTheIdManager21, onlyTheIdOwner1, "macabi");
        System.out.println("done");
        int x = 0;
        x++;*/

        // system.addCoach();
        //system.addManager();
        //system.addOwner();
        //system.addRefree();
        //system
        //system.addTeam();


        /**
         * checking owner- please don't delete it! (Hilla P)
         */
/*
        Owner ownerHilla=new Owner("hilla" , "hilla@gmail.com" , "3wet127");
        Owner ownerLiat=new Owner("liat" , "liat@gmail.com" , "123237");

        Transaction transaction=new Transaction("Transaction",12);
        Transaction transaction1=new Transaction("Transaction1",43);
        Transaction transaction2=new Transaction("Transaction2",445453);
        ArrayList<Transaction> listTransactions= new ArrayList<>();
        listTransactions.add(transaction);
        listTransactions.add(transaction1);
        listTransactions.add(transaction2);

        Account account0=new Account("Hapoel", listTransactions,123123);
        Field field0= new Field();
        Team team0= new Team("Hapoel Haifa",account0,field0);
        field0.setTeam(team0);
        HashSet<Owner> ownersTeam0= team0.getOwners();
        ownersTeam0.add(ownerHilla);

        Account account1=new Account("Maccabi", listTransactions,12335435);
      //  Field field1= new Field("Sami offer");
        Team team1= new Team("Maccabi Haifa",account1,null);
       // field1.setTeam(team1);
        HashSet<Owner> ownersTeam1= team1.getOwners();
        ownersTeam1.add(ownerHilla);

        HashMap<String,Team> hashMapTeams=new HashMap<>();
        hashMapTeams.put("Maccabi",team0);
        hashMapTeams.put("Hapoel",team1);

        SystemController.addTeam(team0);
        SystemController.addTeam(team1);
        owner1.setTeams(hashMapTeams);

        System.out.println("--");
        owner1.addAsset();
        System.out.println("--done.");

*/


    }

    private static void guestMenu() {

    }

    /***********************************************private function**************************************************/

    private static void showMenu(Role member) {
        if(member instanceof Fan){

        }
        else if (member instanceof MainReferee){

        }
        else if (member instanceof SecondaryReferee){

        }
        else if (member instanceof SystemManager){

        }
        else if (member instanceof Owner){

        }
    }
    /*******************************private function for guest menu**********************************/

    /**
     * This function fill the signIn-form with user mail, user name and user password
     * @return String array - details[mail,name,password]
     */
    private static String[] fillFormSignIn() throws IncorrectPasswordInputException, IncorrectInputException {
        String[] details = new String[3];
        Scanner scanInput = new Scanner(System.in);
        System.out.println("please enter your mail");
        String mailInput = scanInput.nextLine();
        if(!checkMailInput(mailInput)){
            throw new IncorrectInputException("incorrect mail input");
        }
        System.out.println("please enter full name");
        String nameInput = scanInput.nextLine();
        System.out.println("please enter password - contains only numbers and letters");
        String password = scanInput.nextLine();
        if(!checkPasswordValue(password)){
            throw (new IncorrectPasswordInputException());
        }
        System.out.println("please verify your password");
        String password2 = scanInput.nextLine();
        while (password.compareTo(password2)!=0){
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
     * @return String array  - details[mail,password]
     */
    private static String[] fillFormLogIn() {
        String[] details = {,};
        Scanner scanInput = new Scanner(System.in);
        System.out.println("please enter your mail");
        String mailInput = scanInput.nextLine();
        System.out.println("please enter password");
        String password = scanInput.nextLine();
        details[0]=mailInput;
        details[1]=password;
        return details;
    }
    /**
     * this function check if an email address is valid using Regex.
     * @param mailInput
     * @return
     */
    private static  boolean checkMailInput(String mailInput) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (mailInput == null)
            return false;
        return pat.matcher(mailInput).matches();
    }
    /**
     * this function Use a regular expression (regex) to check for only letters and numbers
     * The regex will check for upper and lower case letters and digits
     * @param password
     * @return
     */
    private static boolean checkPasswordValue(String password) {
        // todo - check input of password - only numbers and letters
        if( !password.matches("[a-zA-Z0-9]+") ){
            /* A non-alphanumeric character was found, return false */
            return false;
        }
        return true;
    }
}

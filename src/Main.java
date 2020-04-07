
import Asset.*;
import Exception.*;
import Users.*;
import javafx.util.Pair;
import system.SystemController;

import java.util.*;
import java.util.regex.Pattern;

public class Main {
    static Scanner scanInput = new Scanner(System.in);
    static SystemController controller = new SystemController("System Controller");
    static Member member;
    static String path;

    public static void main(String[] args) throws MemberNotSystemManager {
/****************************************************menu******************************************************/
        path = "";
        //SystemController controller =

        /**********for my test - shacahr********/
        Main main = new Main();
        main.shacharFunctionForTesting();
        /***************************************/

        startMenu();

    }


    /***********************************************private function**************************************************/
    private static void startMenu() {
        String input = "";
        while (!input.equals("Exit")) {
            System.out.println("choose one of the following options:\n");
            System.out.println("write \"1\" for signIn.");
            System.out.println("write \"2\" for logIn.");
            System.out.println("\nwrite \"Exit\" if you want to finish. \n");
            input = "";
            while (input.equals("")) {
                input = scanInput.nextLine();
            }
            switch (input) {

                case "1": {
                    try {
                        String[] details = fillFormSignIn();
                        try {
                            member = controller.signIn(details[1], details[0], details[2]);
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
                }
                break;

                case "2": {
                    String[] details = fillFormLogIn();
                    try {
                        member = controller.logIn(details[0], details[1]);
                        showMenu(member);
                    } catch (MemberDontExist e) {
                        System.out.println("This member mail is doesnt exist in the system.\nlog in with different mail.");
                    } catch (PasswordDontMatchException e) {
                        System.out.println("You entered incorrect password.\nlog in with the correct password.");
                    }
                }
                break;
                case "Exit": {
                }
            }
        }
    }

    private static void showMenu(Role member) {
        //just for testing
        member = new SystemManager("shachar", "shachar@gmail.com", "shachar", controller);

        if (member instanceof SystemManager) {
            SystemManagerMenu((Member) member);
        } else if (member instanceof MainReferee) {

        } else if (member instanceof SecondaryReferee) {

        } else if (member instanceof Fan) {

        } else if (member instanceof Owner) {

        }
    }

    private static void SystemManagerMenu(Member member) {
        String input = "";
        while (!input.equals("Exit")) {
            System.out.println("choose one of the following options:\n");
            System.out.println("write \"1\" for handle with refree");
            System.out.println("write \"2\" for handle with team");
            System.out.println("write \"3\" for remove member");
            System.out.println("write \"4\" for handle with complaints");
            System.out.println("write \"5\" for handle with games");
            System.out.println("write \"6\" for view System Information");
            System.out.println("\nwrite \"Exit\" if you want to finish. \n");
            input = "";
            while (input.equals("")) {
                input = scanInput.nextLine();
            }
            switch (input) {

                case "1": {

                    while (!input.equals("Exit")) {
                        System.out.println("choose one of the following options:\n");
                        System.out.println("write \"1\" to remove refree");
                        System.out.println("write \"2\" to add refree");
                        System.out.println("write \"3\" to update refree details");
                        System.out.println("\nwrite \"Exit\" if you want to finish. \n");
                        input = "";
                        while (input.equals("")) {
                            input = scanInput.nextLine();
                        }
                        switch (input) {
                            case "1": {
                                System.out.println("please enter the Id of the refree");
                                String id = scanInput.nextLine();
                                try {
                                    boolean success = controller.removeReferee(member.getUserMail(), id);
                                } catch (MemberNotSystemManager e) {
                                    System.out.println("you don't have the premission to remove refree");
                                }
                            }

                            case "2": {
                                System.out.println("please enter the Id of the refree");
                                String id = scanInput.nextLine();
                                System.out.println("would you like this refree will be main refree ? yes/no");
                                String bool = scanInput.nextLine();
                                boolean mainRefree;
                                if (bool.equals("yes"))
                                    mainRefree = true;
                                else {
                                    mainRefree = false;
                                }
                                try {
                                    boolean success = controller.addRefree(member.getUserMail(), id, mainRefree);
                                } catch (MemberNotSystemManager e) {
                                    System.out.println("you don't have the premission to remove refree");
                                }
                            }
                            case "3": {
                                // controller.updateRefree();
                            }
                        }
                    }
                }
                case "2": {
                    while (!input.equals("Exit")) {
                        System.out.println("choose one of the following options:\n");
                        System.out.println("write \"1\" to add new team");
                        System.out.println("write \"2\" to remove exist team");
                        System.out.println("\nwrite \"Exit\" if you want to finish. \n");
                        input = "";
                        while (input.equals("")) {
                            input = scanInput.nextLine();
                        }
                        switch (input) {
                            case "1": {
                                LinkedList<String> players = new LinkedList<>();
                                LinkedList<String> coachs = new LinkedList<>();
                                LinkedList<String> managers = new LinkedList<>();
                                LinkedList<String> owners = new LinkedList<>();
                                String teamName = "";
                                System.out.println("please enter the name of the new team");
                                teamName = scanInput.nextLine();
                                String id = "";
                                while (!id.equals("0")) {
                                    System.out.println("please enter the id of the players in the team \n when you finish press 0");
                                    id = scanInput.nextLine();
                                    if (!id.equals(0))
                                        players.add(id);
                                }
                                while (!id.equals("0")) {
                                    System.out.println("please enter the id of the coachs in the team \n when you finish press 0");
                                    id = scanInput.nextLine();
                                    if (!id.equals(0))
                                        coachs.add(id);
                                }
                                while (!id.equals("0")) {
                                    System.out.println("please enter the id of the managers in the team \n when you finish press 0");
                                    id = scanInput.nextLine();
                                    if (!id.equals(0))
                                        managers.add(id);
                                }
                                while (!id.equals("0")) {
                                    System.out.println("please enter the id of the owners in the team \n when you finish press 0");
                                    id = scanInput.nextLine();
                                    if (!id.equals(0))
                                        owners.add(id);
                                }
                                try {
                                    boolean success = controller.addTeam(member.getUserMail(), players, coachs, managers, owners, teamName);
                                } catch (MemberNotSystemManager e) {
                                    System.out.println("you don't have the premission to remove refree");
                                }
                            }
                            case "2": {
                                System.out.println("please enter the team nama you want to close");
                                String TeamName = scanInput.nextLine();
                                try {
                                    boolean success = controller.closeTeam(member.getUserMail(), TeamName);
                                } catch (MemberNotSystemManager e) {
                                    System.out.println("you don't have the premission to remove refree");
                                }
                            }
                        }
                    }
                }
                case "3": {
                    System.out.println("please enter the Id of the member you want to remove");
                    String id = scanInput.nextLine();
                    try {
                        boolean success = controller.removeMember(member.getUserMail(), id);
                    } catch (MemberNotSystemManager e) {
                        System.out.println("you don't have the premission to remove refree");
                    }
                }
                case "4": {

                    while (!input.equals("Exit")) {
                        System.out.println("choose one of the following options:\n");
                        System.out.println("write \"1\" to watch the complaints");
                        System.out.println("write \"2\" to response on the complaints");
                        System.out.println("\nwrite \"Exit\" if you want to finish. \n");
                        input = "";
                        while (input.equals("")) {
                            input = scanInput.nextLine();
                        }
                        switch (input) {
                            case "1": {
                                try {
                                    controller.watchComplaint(member.getUserMail(), path);
                                } catch (MemberNotSystemManager e) {
                                    System.out.println("you don't have the premission to remove refree");
                                }
                            }
                            case "2": {
                                try {
                                    LinkedList<Pair<String , String>> responseForComplaint = new LinkedList<>();
                                    boolean success = controller.responseComplaint(member.getUserMail(), path, responseForComplaint);
                                } catch (MemberNotSystemManager e) {
                                    System.out.println("you don't have the premission to remove refree");
                                }
                            }
                        }
                    }
                }
                case "5": {
                    try {
                        controller.schedulingGames(member.getUserMail());
                    } catch (MemberNotSystemManager e) {
                        System.out.println("you don't have the premission to remove refree");
                    }
                }
                case "6": {
                    try {
                        controller.viewSystemInformation(member.getUserMail());
                    } catch (MemberNotSystemManager e) {
                        System.out.println("you don't have the premission to remove refree");
                    }
                }
            }


        }
    }


/***************************************************tests********************************************************/

private static void shacharFunctionForTesting() throws MemberNotSystemManager {

    Fan fan1 = new Fan("adi", "adi@gmail.com", "adi");
    Fan fan2 = new Fan("alisa", "alisa@gmail.com", "alisa");
    Player player1 = new Player("yaara", "yaara@gmail.com", "yaara", new Date(1995, 1, 1), "player");
    Player player2 = new Player("daniel", "daniel@gmail.com", "daniel", new Date(1995, 1, 1), "player");
    Player player3 = new Player("hilla", "hilla@gmail.com", "hilla", new Date(1995, 1, 1), "player");
    Player player4 = new Player("noa", "noa@gmail.com", "noa", new Date(1995, 1, 1), "player");
    Player player5 = new Player("liat", "liat@gmail.com", "liat", new Date(1995, 1, 1), "player");
    Player player6 = new Player("neta", "neta@gmail.com", "neta", new Date(1995, 1, 1), "player");
    Player player7 = new Player("ziv", "ziv@gmail.com", "ziv", new Date(1995, 1, 1), "player");
    Player player8 = new Player("neta", "neta@gmail.com", "neta", new Date(1995, 1, 1), "player");
    Player player9 = new Player("or", "or@gmail.com", "or", new Date(1995, 1, 1), "player");
    Player player10 = new Player("shoval", "shoval@gmail.com", "shoval", new Date(1995, 1, 1), "player");
    Player player11 = new Player("gal", "gal@gmail.com", "gal", new Date(1995, 1, 1), "player");
    Player player12 = new Player("michelle", "michelle@gmail.com", "michelle", new Date(1995, 1, 1), "player");
    Player player13 = new Player("gabi", "gabi@gmail.com", "gabi", new Date(1995, 1, 1), "player");
    Player player14 = new Player("almog", "almog@gmail.com", "almog", new Date(1995, 1, 1), "player");
    Player player15 = new Player("shani", "shani@gmail.com", "shani", new Date(1995, 1, 1), "player");
    Player player16 = new Player("ifat", "ifat@gmail.com", "ifat", new Date(1995, 1, 1), "player");
    Player player17 = new Player("inbal", "inbal@gmail.com", "dor", new Date(1995, 1, 1), "player");
    Player player18 = new Player("oscar", "oscar@gmail.com", "oscar", new Date(1995, 1, 1), "player");
    Player player19 = new Player("roman", "roman@gmail.com", "roman", new Date(1995, 1, 1), "player");
    Player player20 = new Player("omer", "omer@gmail.com", "omer", new Date(1995, 1, 1), "player");
    Player player21 = new Player("asi", "asi@gmail.com", "asi", new Date(1995, 1, 1), "player");
    Player player22 = new Player("peleg", "peleg@gmail.com", "peleg", new Date(1995, 1, 1), "player");

    controller.addPlayer(player1);
    controller.addPlayer(player2);
    controller.addPlayer(player3);
    controller.addPlayer(player4);
    controller.addPlayer(player5);
    controller.addPlayer(player6);
    controller.addPlayer(player7);
    controller.addPlayer(player8);
    controller.addPlayer(player9);
    controller.addPlayer(player10);
    controller.addPlayer(player11);
    controller.addPlayer(player12);
    controller.addPlayer(player13);
    controller.addPlayer(player14);
    controller.addPlayer(player15);
    controller.addPlayer(player16);
    controller.addPlayer(player17);
    controller.addPlayer(player18);
    controller.addPlayer(player19);
    controller.addPlayer(player20);
    controller.addPlayer(player21);
    controller.addPlayer(player22);


    Coach coach1 = new Coach("yosi oren", "yosi@gmaill.com", "123", "Coach");
    Coach coach2 = new Coach("arnold strum", "arnold@gmaill.com", "124", "Coach");
    controller.addCoach(coach1);
    controller.addCoach(coach2);
    Manager manager1 = new Manager("oren tzur", "oren@gmaill.com", "125");
    Manager manager2 = new Manager("guy shani", "guy@gmaill.com", "126");
    controller.addManager(manager1);
    controller.addManager(manager2);
    Owner owner1 = new Owner("ariel pelner", "ariel@gmail.com", "127");
    Owner owner2 = new Owner("dor atzmon", "dor@gmail.com", "128");
    controller.addOwner(owner1);
    controller.addOwner(owner2);
    SystemManager systemManager = new SystemManager("shachar meretz ", "shachar@gmail.com", "shachar", controller);
    controller.addSystemManager(systemManager);
    controller.addFan(fan1);
    //  controller.addRefree(systemManager.getUserMail(), fan1.getUserMail(), true);
    //   controller.removeReferee(systemManager.getUserMail(), fan1.getUserMail());
    // controller.removeMember(systemManager.getUserMail(), coach1.getUserMail());
    //  controller.removeMember(systemManager.getUserMail(), manager1.getUserMail());
    //   controller.removeMember(systemManager.getUserMail(), owner1.getUserMail());
    //   controller.removeMember(systemManager.getUserMail(), player1.getUserMail());
    //   controller.removeMember(systemManager.getUserMail(), fan2.getUserMail());

    String name = "macabi";
    LinkedList<String> players1 = new LinkedList<>();
    players1.add(player1.getUserMail());
    players1.add(player2.getUserMail());
    players1.add(player3.getUserMail());
    players1.add(player4.getUserMail());
    players1.add(player5.getUserMail());
    players1.add(player6.getUserMail());
    players1.add(player7.getUserMail());
    players1.add(player8.getUserMail());
    players1.add(player9.getUserMail());
    players1.add(player10.getUserMail());
    players1.add(player11.getUserMail());

    LinkedList<String> coaches1 = new LinkedList<>();
    coaches1.add(coach1.getUserMail());

    LinkedList<String> managers1 = new LinkedList<>();
    managers1.add(manager1.getUserMail());

    LinkedList<String> owners1 = new LinkedList<>();
    owners1.add(owner1.getUserMail());

    controller.addTeam(systemManager.getUserMail(), players1, coaches1, managers1, owners1, name);
    systemManager.watchComplaint("C:\\Users\\shachar meretz\\Desktop\\semesterB\\arnold\\projectGit\\PreparationForFinalProject\\complaint.txt");
    int x = 0;


}

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
    /******************************* public function for guest menu (noa) **********************************/

    /**
     * This function fill the signIn-form with user mail, user name and user password
     *
     * @return String array - details[mail,name,password]
     */

    public static String[] fillFormSignIn() throws IncorrectPasswordInputException, IncorrectInputException {
        String[] details = new String[3];
        Scanner scanInput = new Scanner(System.in);
        System.out.println("please enter your mail");
        String mailInput = scanInput.nextLine();
        if (!checkMailInput(mailInput)) {
            throw new IncorrectInputException("incorrect mail input");
        }
        System.out.println("please enter full name");
        String nameInput = scanInput.nextLine();
        System.out.println("please enter password - contains only numbers and letters");
        String password = scanInput.nextLine();
        if (!checkPasswordValue(password)) {
            throw (new IncorrectPasswordInputException());
        }
        System.out.println("please verify your password");
        String password2 = scanInput.nextLine();
        while (password.compareTo(password2) != 0) {
            System.out.println("you entered two different password");
            System.out.println("please enter password");
            password = scanInput.nextLine();
            System.out.println("please verify your password");
            password2 = scanInput.nextLine();
        }

        System.out.println("your details entered successfully!\nplease wait for confirmation");
        details[0] = mailInput;
        details[1] = nameInput;
        details[2] = password;

        return details;
    }

    /**
     * this function fill the logIn-form : user name , user password
     *
     * @return String array  - details[mail,password]
     */
    public static String[] fillFormLogIn() {
        String[] details = {"", ""};
        Scanner scanInput = new Scanner(System.in);
        System.out.println("please enter your mail");
        String mailInput = scanInput.nextLine();
        System.out.println("please enter password");
        String password = scanInput.nextLine();
        details[0] = mailInput;
        details[1] = password;
        return details;
    }

    /**
     * this function check if an email address is valid using Regex.
     *
     * @param mailInput
     * @return
     */
    public static boolean checkMailInput(String mailInput) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
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
     *
     * @param password
     * @return
     */
    public static boolean checkPasswordValue(String password) {
        // todo - check input of password - only numbers and letters
        if (!password.matches("[a-zA-Z0-9]+")) {
            /* A non-alphanumeric character was found, return false */
            return false;
        }
        return true;
    }

}
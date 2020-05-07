
import Domain.Asset.Coach;
import Domain.Asset.Field;
import Domain.Asset.Manager;
import Domain.Asset.Player;
import Domain.Game.*;
import Domain.League.ASchedulingPolicy;
import Domain.League.League;
import Domain.League.LeagueInSeason;
import Domain.League.Season;
import Domain.Users.*;
import Exception.*;
import javafx.util.Pair;
import Service.SystemController;

import java.util.*;
import java.util.regex.Pattern;

public class Main {
    static Scanner scanInput = new Scanner(System.in);
    static SystemController controller;//= new SystemController("System Controller");
    static Role member;
    static String path;

    public static void main(String[] args) throws IncorrectInputException, DontHavePermissionException, AlreadyExistException {
/****************************************************menu******************************************************/
        //  controller.initSystem();
        controller = new SystemController("System Controller");
        controller.signIn("owner" , "owner@gmail.com" , "123" , new Date(1995,6,7));
        controller.signIn("owner" , "player@gmail.com" , "123" , new Date(1995,6,7));
        controller.signIn("owner" , "manager@gmail.com" , "123" , new Date(1995,6,7));

        startMenu();
        /****shachar tests******/
        //must write the path in the main
        //dbController = new DBController();
        //shacharFunctionForTesting();
        //SystemManagerMenu();

        /**** Hilla Peter Tests!!****/
        //  Main main = new Main();
        // main.hillaPeterFunctionForTesting();
        //startMenu();

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
                            Date birthdate = new Date(Integer.parseInt(details[3]), Integer.parseInt(details[4]), Integer.parseInt(details[5]));
                            member = controller.signIn(details[1], details[0], details[2], birthdate);
                            System.out.println("succeed to signIn!!");
                            break;
                        } catch (AlreadyExistException e) {
                            System.out.println("this mail is already exist in the system.\ntry again with a different mai.");
                        } catch (DontHavePermissionException e) {
                            System.out.println("not have permission to signIn");
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
                        Member member = controller.logIn(details[0], details[1]);
                       // showMenu(member);
                        break;
                    } catch (MemberNotExist e) {
                        System.out.println("This member mail is doesnt exist in the system.\nlog in with different mail.");
                    } catch (PasswordDontMatchException e) {
                        System.out.println("You entered incorrect password.\nlog in with the correct password.");
                    }  catch (DontHavePermissionException e) {
                        break;
                    }
                    break;
                }
                case "Exit": {
                    System.exit(1);
                }
            }
        }
    }
/*
    private static void showMenu(String member) throws IncorrectInputException, DontHavePermissionException {
        if (member.equals()Guest) {
            startMenu();
        } else if (member instanceof SystemManager) {
            SystemManagerMenu();
        } else if (member instanceof MainReferee) {
            mainRefereeMenu();

        } else if (member instanceof SecondaryReferee) {
            secondaryRefereeMenu();
        } else if (member instanceof Fan) {
            fanMenu();

        } else if (member instanceof Owner) {
            OwnerMenu();
        } else if (member instanceof AssociationDelegate) {
            AssociationDelegateMenu((Member) member);
        }

        startMenu();
    }
*/
    /*********************************************System Manager**********************************************/
    private static void SystemManagerMenu() throws DontHavePermissionException {
        String input = "";
        while (!input.equals("ExitAll")) {
            System.out.println("choose one of the following options:\n");
            System.out.println("write \"1\" for handle with refree");
            System.out.println("write \"2\" for handle with team");
            System.out.println("write \"3\" for remove member");
            System.out.println("write \"4\" for handle with complaints");
            System.out.println("write \"5\" for handle with games");
            System.out.println("write \"6\" for view System Information");
            System.out.println("write \"7\" for remove owner");
            System.out.println("write \"8\" for handle with Association Delegate");
            System.out.println("write \"9\" for handle with System Manager");
            System.out.println("\nwrite \"logOut\" if you want to finish. \n");
            input = "";
            while (input.equals("")) {
                input = scanInput.nextLine();
            }
            switch (input) {

                case "1": {

                    while (!input.equals("Exit")) {
                        System.out.println("choose one of the following options:\n");
                        System.out.println("write \"1\" to remove referee");
                        System.out.println("write \"2\" to add referee");
                        System.out.println("\nwrite \"Exit\" if you want to finish. \n");
                        input = "";
                        while (input.equals("")) {
                            input = scanInput.nextLine();
                        }
                        switch (input) {
                            case "1": {
                                System.out.println("please enter the Id of the referee");
                                String id = removeRefree();
                                try {
                                    controller.removeReferee(id);
                                } catch (DontHavePermissionException e) {
                                    System.out.println("you don't have the permission to remove referee");
                                } catch (IncorrectInputException e) {
                                    e.printStackTrace();
                                } catch (AlreadyExistException e) {
                                    e.printStackTrace();
                                } catch (MemberNotExist memberNotExist) {
                                    memberNotExist.printStackTrace();
                                }
                                break;
                            }

                            case "2": {
                                String id = addRefree();
                                System.out.println("would you like this refree will be main refree ? yes/no");
                                String bool = scanInput.nextLine();
                                boolean mainReferee;
                                if (bool.equals("yes"))
                                    mainReferee = true;
                                else {
                                    mainReferee = false;
                                }
                                try {
                                    controller.addReferee(id, mainReferee);
                                } catch (DontHavePermissionException e) {
                                    System.out.println("you don't have the permission to remove referee");
                                } catch (IncorrectInputException e) {
                                    e.printStackTrace();
                                } catch (AlreadyExistException e) {
                                    e.printStackTrace();
                                } catch (MemberAlreadyExistException e) {
                                    e.printStackTrace();
                                } catch (MemberNotExist memberNotExist) {
                                    memberNotExist.printStackTrace();
                                }
                                break;
                            }
                        }
                    }
                    break;
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
                                String teamName = "";
                                System.out.println("please enter the name of the new team");
                                teamName = scanInput.nextLine();
                                String ownerId = getOwnerId();
                                try {
                                    controller.addTeam(teamName, ownerId);
                                } catch (DontHavePermissionException e) {
                                    System.out.println("you don't have the permission to remove referee");
                                } catch (ObjectNotExist objectNotExist) {
                                    objectNotExist.printStackTrace();
                                } catch (ObjectAlreadyExist objectAlreadyExist) {
                                    objectAlreadyExist.printStackTrace();
                                } catch (MemberNotExist memberNotExist) {
                                    memberNotExist.printStackTrace();
                                } catch (AlreadyExistException e) {
                                    e.printStackTrace();
                                } catch (IncorrectInputException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                            case "2": {
                                String TeamName = removeTeam();
                                try {
                                    controller.closeTeam(TeamName);
                                } catch (DontHavePermissionException e) {
                                    System.out.println("you don't have the permission to remove referee");
                                } catch (ObjectNotExist objectNotExist) {
                                    objectNotExist.printStackTrace();
                                } catch (AlreadyExistException e) {
                                    e.printStackTrace();
                                } catch (MemberNotExist memberNotExist) {
                                    memberNotExist.printStackTrace();
                                } catch (IncorrectInputException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                case "3": {
                    String id = removeMember();
                    try {
                       controller.removeMember(id);
                    } catch (IncorrectInputException e) {
                        e.printStackTrace();
                    } catch (DontHavePermissionException e) {
                        System.out.println("you don't have the permission to remove member");
                    } catch (MemberNotExist e) {
                        e.printStackTrace();
                    } catch (AlreadyExistException e) {
                        e.printStackTrace();
                    } catch (NotReadyToDelete notReadyToDelete) {
                        notReadyToDelete.printStackTrace();
                    }
                    break;
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
                                    controller.watchComplaint(path);
                                } catch (DontHavePermissionException e) {
                                    System.out.println("you don't have the permission to watch complaints");
                                }
                                break;
                            }
                            case "2": {
                                try {
                                    LinkedList<Pair<String, String>> responseForComplaint = new LinkedList<>();
                                    controller.responseComplaint(path, responseForComplaint);
                                } catch (DontHavePermissionException e) {
                                    System.out.println("you don't have the permission to response on the complaint");
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                case "5": {
                    try {
                        String seasonId = chooseSeason();
                        String leagueId = chooseLeague();
                        controller.schedulingGames(seasonId, leagueId);
                    } catch (DontHavePermissionException e) {
                        System.out.println("you don't have the permission to remove referee");
                    } catch (ObjectNotExist e) {
                        System.out.println("the season or the league you choose doesnt exist");
                    } catch (IncorrectInputException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "6": {
                    try {
                        controller.viewSystemInformation(path + "\\info.txt");
                    } catch (DontHavePermissionException e) {
                        System.out.println("you don't have the permission to remove referee");
                    }
                    break;
                }
                case "7": {

                    System.out.println("please enter the Id of the owner");
                    String id = removeOwner();
                    try {
                        controller.removeOwner(id);
                    } catch (DontHavePermissionException e) {
                        System.out.println("you don't have the permission to remove owner");
                    } catch (IncorrectInputException e) {
                        e.printStackTrace();
                    } catch (NotReadyToDelete notReadyToDelete) {
                        notReadyToDelete.printStackTrace();
                    } catch (MemberNotExist memberNotExist) {
                        memberNotExist.printStackTrace();
                    }
                    break;
                }
                case "8": {

                    while (!input.equals("Exit")) {
                        System.out.println("choose one of the following options:\n");
                        System.out.println("write \"1\" to remove Association Delegate");
                        System.out.println("write \"2\" to add Association Delegate");
                        System.out.println("\nwrite \"Exit\" if you want to finish. \n");
                        input = "";
                        while (input.equals("")) {
                            input = scanInput.nextLine();
                        }
                        switch (input) {
                            case "1": {
                                System.out.println("please enter the Id of the association deligate");
                                String id = removeAssociationDelegate();
                                try {
                                    controller.removeAssociationDelegate(id);
                                } catch (DontHavePermissionException e) {
                                    System.out.println("you don't have the permission to remove association delegate");
                                    //    input="Exit";
                                } catch (IncorrectInputException e) {
                                    e.printStackTrace();
                                } catch (AlreadyExistException e) {
                                    e.printStackTrace();
                                } catch (MemberNotExist memberNotExist) {
                                    memberNotExist.printStackTrace();
                                }
                                break;
                            }

                            case "2": {
                                String id = addAssociationDelegate();
                                try {
                                    controller.addAssociationDelegate(id);
                                } catch (DontHavePermissionException e) {
                                    System.out.println("you don't have the permission to add association delegate");
                                } catch (MemberNotExist memberNotExist) {
                                    memberNotExist.printStackTrace();
                                } catch (AlreadyExistException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                case "9": {

                    while (!input.equals("Exit")) {
                        System.out.println("choose one of the following options:\n");
                        System.out.println("write \"1\" to remove System Manager");
                        System.out.println("write \"2\" to add System Manager");
                        System.out.println("\nwrite \"Exit\" if you want to finish. \n");
                        input = "";
                        while (input.equals("")) {
                            input = scanInput.nextLine();
                        }
                        switch (input) {
                            case "1": {
                                System.out.println("please enter the Id of the system manager");
                                String id = removeSystemManager();
                                try {
                                    controller.removeSystemManager(id);
                                } catch (DontHavePermissionException e) {
                                    System.out.println("you don't have the permission to remove System Manager");
                                } catch (IncorrectInputException e) {
                                    e.printStackTrace();
                                } catch (NotReadyToDelete notReadyToDelete) {
                                    notReadyToDelete.printStackTrace();
                                } catch (MemberNotExist memberNotExist) {
                                    memberNotExist.printStackTrace();
                                } catch (AlreadyExistException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }

                            case "2": {
                                String id = addSystemManager();
                                try {
                                    controller.addSystemManager(id);
                                } catch (DontHavePermissionException e) {
                                    System.out.println("you don't have the permission to add System Manager");
                                } catch (MemberNotExist memberNotExist) {
                                    memberNotExist.printStackTrace();
                                } catch (AlreadyExistException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                case "logOut": {
                    controller.logOut();
                    member = null;//(Role) controller.logOut();
                    input = "ExitAll";
                    break;
                }
            }


        }
    }

    /**
     * this function return the owner id the member want to remove
     * @return
     * @throws DontHavePermissionException - if the member is not system manager
     */
    private static String getOwnerId() throws DontHavePermissionException {
        HashMap<String, Role> owners = controller.getOwnersAndFans();
        System.out.println("Choose id to add owner");
        for (String owner : owners.keySet()) {
            System.out.println(owner);
        }

        String ownerToAdd = scanInput.nextLine();
        return ownerToAdd;
    }

    /**
     * this function return the owner id the member want to remove
     * @return
     * @throws DontHavePermissionException - if the member is not system manager
     */
    private static String removeOwner() throws DontHavePermissionException {
        HashMap<String, Owner> owners = controller.getOwners(member);
        System.out.println("Choose id to remove owner");
        for (String owner : owners.keySet()) {
            System.out.println(owner);
        }

        String ownerToRemove = scanInput.nextLine();
        return ownerToRemove;
    }

    /**
     * this function return the association deligate id the member want to remove
     * @return
     * @throws DontHavePermissionException - if the member is not system manager
     */
    private static String removeAssociationDelegate() throws DontHavePermissionException {
        HashMap<String, AssociationDelegate> associationDelegates = controller.getAssociationDelegates();
        System.out.println("Choose id to remove associationDelegate");
        for (String associationDelegate : associationDelegates.keySet()) {
            System.out.println(associationDelegate);
        }

        String associationDelegateToRemove = scanInput.nextLine();
        return associationDelegateToRemove;
    }

    /**
     * this function return the association deligate id the member want to add
     * @return
     * @throws DontHavePermissionException - if the member is not system manager
     */
    private static String addAssociationDelegate() throws DontHavePermissionException {
        HashMap<String, Fan> associationDelegate = controller.getFans(member);
        System.out.println("Choose id to add associationDelegate");
        for (String owner : associationDelegate.keySet()) {
            System.out.println(owner);
        }

        String associationDelegateToAdd = scanInput.nextLine();
        return associationDelegateToAdd;
    }

    /**
     * this function return the system manager id the member want to remove
     * @return
     * @throws DontHavePermissionException - if the member is not system manager
     */
    private static String removeSystemManager() throws DontHavePermissionException {
        HashMap<String, SystemManager> systemmanagers = controller.getSystemManager();
        System.out.println("Choose id to remove system manager");
        for (String systemmanager : systemmanagers.keySet()) {
            System.out.println(systemmanager);
        }

        String systemmanagerToRemove = scanInput.nextLine();
        return systemmanagerToRemove;
    }

    /**
     * this function return the system manager id the member want to add
     * @return
     * @throws DontHavePermissionException - if the member is not system manager
     */
    private static String addSystemManager() throws DontHavePermissionException {
        HashMap<String, Fan> systemmanagers = controller.getFans(member);
        System.out.println("Choose id to add system manager");
        for (String systemmanager : systemmanagers.keySet()) {
            System.out.println(systemmanager);
        }

        String systemmanagerToAdd = scanInput.nextLine();
        return systemmanagerToAdd;
    }

    /**
     * this function return the season id the member want to scheduling game in
     * @return
     */
    private static String chooseSeason() {
        HashMap<String, Season> season = controller.getSeasons();
        System.out.println("Choose season id");
        for (String seasonId : season.keySet()) {
            System.out.println(seasonId);
        }
        String seasonToreturn = scanInput.nextLine();
        return seasonToreturn;
    }

    /**
     * this function return the league id the member want to scheduling game in
     * @return
     * @throws DontHavePermissionException
     */
    private static String chooseLeague() throws DontHavePermissionException {
        HashMap<String, League> leagues = controller.getLeagues();
        System.out.println("Choose league id");
        for (String leagueId : leagues.keySet()) {
            System.out.println(leagueId);
        }
        String leagueToReturn = scanInput.nextLine();
        return leagueToReturn;
    }

    /**
     * this function return the referee id the member want to remove
     * @return
     * @throws DontHavePermissionException - if the member is not system manager
     */
    private static String removeRefree() throws DontHavePermissionException {
        HashMap<String, Referee> refrees = controller.getReferees();
        System.out.println("Choose id to remove referee");
        for (String refere : refrees.keySet()) {
            System.out.println(refere);
        }

        String refereeToRemove = scanInput.nextLine();
        return refereeToRemove;
    }

    /**
     * this function return the referee id the member want to add
     * @return
     * @throws DontHavePermissionException - if the member is not system manager
     */
    private static String addRefree() throws DontHavePermissionException {
        HashMap<String, Fan> fans = controller.getFans(member);
        System.out.println("Choose id to add referee");
        for (String Fan : fans.keySet()) {
            System.out.println(Fan);
        }
        String refreeToAdd = scanInput.nextLine();
        return refreeToAdd;
    }

    /**
     * this function return the team name that the member want to close
     * @return
     */
    private static String removeTeam() {
        HashMap<String, Team> team = controller.getTeams();
        System.out.println("Choose team name to remove team");
        for (String teamName : team.keySet()) {
            System.out.println(teamName);
        }
        scanInput = new Scanner(System.in);
        String teamToRemove = scanInput.nextLine();
        return teamToRemove;
    }

    /**
     * this function return the id the member want to remove
     * @return
     * @throws DontHavePermissionException - if the member is not system manager
     */
    private static String removeMember() throws DontHavePermissionException {
        HashMap<String, Member> members = controller.getMembers();
        System.out.println("Choose id to remove member");
        for (String member : members.keySet()) {
            System.out.println(member);
        }

        String memberToRemove = scanInput.nextLine();
        return memberToRemove;
    }

    /*********************************************Owner**********************************************/
    private static void OwnerMenu() {
        try {
            Team team;
            String input = "";
            while (!input.equals("ExitAll")) {
                System.out.println("choose one of the following options:\n");
                System.out.println("write \"1\" Add Asset");
                System.out.println("write \"2\" Remove Asset");
                System.out.println("write \"3\" Add New Manager");
                System.out.println("write \"4\" Add New Owner");
                System.out.println("write \"5\" Remove Manager");
                System.out.println("write \"6\" Temporary Team Closing");
                System.out.println("write \"7\" Reopen Closed Team");
                System.out.println("write \"8\" Add Outcome");
                System.out.println("write \"9\" Add Income");
                System.out.println("write \"10\" Update Asset");
                System.out.println("\nwrite \"logOut\" if you want to finish. \n");
                input = "";
                while (input.equals("")) {
                    input = scanInput.nextLine();
                }
                switch (input) {
                    case "1": {
                        while (!input.equals("Exit"))
                        {
                        System.out.println("What asset do you want to add? choose by index");
                        System.out.println("Press \"1.\" for Team manager");
                        System.out.println("Press \"2.\" for Coach");
                        System.out.println("Press \"3.\" for Player");
                        System.out.println("Press \"4.\" for Field");
                        System.out.println("\nwrite \"Exit\" if you want to finish. \n");
                        input = "";
                            while (input.equals("")) {
                                input = scanInput.nextLine();
                            }
                            switch (input) {
                                case "1": {
                                    addTeamManager();
                                    break;
                                }
                                case "2": {
                                    addTeamCoach();
                                    break;
                                }
                                case "3": {
                                    addTeamPlayer();
                                    break;
                                }
                                case "4": {
                                    addTeamField();
                                    break;
                                }
                                case "Exit": {
                                    break;
                                }
                            }
                        }
                        break;
                    }//case 1- add asset
                    case "2": {
                        while (!input.equals("Exit")) {
                            System.out.println("What asset do you want to remove? choose by index");
                        System.out.println("Press \"1.\" for Team manager");
                        System.out.println("Press \"2.\" for Coach");
                        System.out.println("Press \"3.\" for Player");
                        System.out.println("Press \"4.\" for Field");
                        System.out.println("\nwrite \"Exit\" if you want to finish. \n");
                        input = "";
                            while (input.equals("")) {
                                input = scanInput.nextLine();
                            }                            switch (input) {
                                case "1": {
                                    removeTeamManager();
                                    break;
                                }
                                case "2": {
                                    removeTeamCoach();
                                    break;
                                }
                                case "3": {
                                    removeTeamPlayer();
                                    break;
                                }
                                case "4": {
                                    removeTeamField();
                                    break;
                                }
                                case "Exit": {
                                    break;
                                }
                            }
                        }
                        break;
                    }//removeAsset
                    case "3": {
                        addTeamManager();
                        break;
                    }
                    case "4": {
                        System.out.println("Choose role by mail to make him owner");
                        HashMap<String, Role> allRoles = controller.getRoles();
                        //moving on all the roles in system
                        for (String mailId : allRoles.keySet()) {
                            Role role = allRoles.get(mailId);
                            if (!(role instanceof Owner)) {
                                System.out.println(mailId);
                            }
                        }
                        String mailId = scanInput.nextLine();
                        Role role = allRoles.get(mailId);
                        HashMap<String, Team> teams = controller.getTeams();
                        System.out.println("Choose team name to add new owner");
                        for (String teamName : teams.keySet()) {
                            System.out.println(teamName);
                        }

                        String teamName = scanInput.nextLine();
                        try {
                            controller.addNewOwner(teamName, mailId);
                        } catch (ObjectNotExist exception) {

                        } catch (MemberNotExist ownerNotExist) {
                            ownerNotExist.printStackTrace();
                        }

                        break;

                    }
                    case "5": {
                        removeTeamManager();
                        break;
                    }
                    case "6": {
                        HashMap<String, Team> teams = controller.getTeams();
                        System.out.println("Choose a team you want to close temporary");
                        for (String teamName : teams.keySet()) {
                            team = teams.get(teamName);
                            //open
                            if (team.getStatus()) {
                                System.out.println(teamName);
                            }
                        }
                        String teamName = scanInput.next();
                        controller.temporaryTeamClosing(teamName);
                        break;
                    }
                    case "7": {
                        HashMap<String, Team> teams = controller.getTeams();
                        System.out.println("Choose a team you want to reopen temporary");
                        for (String teamName : teams.keySet()) {
                            team = teams.get(teamName);
                            //closed
                            if (!team.getStatus()) {
                                System.out.println(teamName);
                            }
                        }
                        String teamName = scanInput.next();
                        controller.reopenClosedTeam(teamName);
                        break;
                    }
                    case "8": {
                        HashMap<String, Team> teams = controller.getTeams();
                        System.out.println("Choose team name to update outcome");
                        for (String teamName : teams.keySet()) {
                            System.out.println(teamName);
                        }
                        String teamName = scanInput.nextLine();
                        System.out.println("What outcome do you want to add?");
                        String description = scanInput.nextLine();
                        System.out.println("What is the amount?");
                        double amount = scanInput.nextDouble();
                        controller.addOutCome(teamName, description, amount);
                        break;
                    }
                    case "9": {
                        HashMap<String, Team> teams = controller.getTeams();
                        System.out.println("Choose team name to update income");
                        for (String teamName : teams.keySet()) {
                            System.out.println(teamName);
                        }
                        String teamName = scanInput.nextLine();
                        System.out.println("What income do you want to add?");
                        String description = scanInput.nextLine();
                        System.out.println("What is the amount?");
                        double amount = scanInput.nextDouble();
                        controller.addInCome(teamName, description, amount);
                        break;
                    }
                    case "10": {
                        System.out.println("What asset do you want to update? choose by index");
                        System.out.println("Press \"1.\" for Player");
                        System.out.println("Press \"2.\" for Field");
                        System.out.println("\nwrite \"Exit\" if you want to finish. \n");
                        input = scanInput.nextLine();
                        while (!input.equals("Exit")) {
                            switch (input) {
                                case "1": {
                                    updatePlayerDetails();
                                }
                                case "2": {
                                    updateField();
                                }
                                case "Exit": {
                                }
                            }
                        }
                    }
                    case "logOut": {
                        controller.logOut();
                        member = null;//(Role) controller.logOut();
                        input = "ExitAll";
                        break;
                    }
                }
            }

        } catch (Exception ownerNotExist) {
        }

    }


    /**
     * Private functions for Owner main-don't delete!
     **/
    private static void addTeamManager() throws DontHavePermissionException {
        System.out.println("Choose role by mail to make him manager");
        HashMap<String, Role> allRoles = controller.getRoles();
        //moving on all the roles in system
        for (String mailId : allRoles.keySet()) {
            Role role = allRoles.get(mailId);
            if (!(role instanceof Manager) && !(role instanceof Owner)) {
                System.out.println(mailId);
            }
        }
        String mailId = scanInput.nextLine();
        Role role = allRoles.get(mailId);
        HashMap<String, Team> teams = controller.getTeams();
        System.out.println("Choose team name to add new manager");
        for (String teamName : teams.keySet()) {
            System.out.println(teamName);
        }

        String teamName = scanInput.nextLine();
        try {
            controller.addManager(teamName, mailId);
        } catch (Exception ownerNotExist) {
        }

    }

    private static void addTeamCoach() throws DontHavePermissionException {
        System.out.println("Choose role by mail to make him coach");
        HashMap<String, Role> allRoles = controller.getRoles();
        //moving on all the roles in system
        for (String mailId : allRoles.keySet()) {
            Role role = allRoles.get(mailId);
            if (!(role instanceof Manager) && !(role instanceof Owner)) {
                System.out.println(mailId);
            }
        }
        String mailId = scanInput.nextLine();
        Role role = allRoles.get(mailId);
        HashMap<String, Team> teams = controller.getTeams();
        System.out.println("Choose team name to add new coach");
        for (String teamName : teams.keySet()) {
            System.out.println(teamName);
        }

        String teamName = scanInput.nextLine();
        try {
            controller.addCoach(teamName, mailId);
        } catch (Exception ownerNotExist) {
        }

    }

    private static void addTeamPlayer() throws IncorrectInputException, ObjectNotExist, MemberNotExist, AlreadyExistException, DontHavePermissionException, NoEnoughMoney {
        System.out.println("Choose role by mail to make him player");
        HashMap<String, Role> allRoles = controller.getRoles();
        //moving on all the roles in system
        for (String mailId : allRoles.keySet()) {
            Role role = allRoles.get(mailId);
            if (!(role instanceof Manager) && !(role instanceof Owner)) {
                System.out.println(mailId);
            }
        }
        String mailId = scanInput.nextLine();
        Role role = allRoles.get(mailId);

        HashMap<String, Team> teams = controller.getTeams();
        System.out.println("Choose team name to add new player");
        for (String teamName : teams.keySet()) {
            System.out.println(teamName);
        }
        String teamName = scanInput.nextLine();
        System.out.println("Enter year of birth of player");
        int year = scanInput.nextInt();
        System.out.println("Enter month of birth of player");
        int month = scanInput.nextInt();
        System.out.println("Enter day of birth of player");
        int day = scanInput.nextInt();
        System.out.println("Choose role for the player");
        String rolePlayer = scanInput.nextLine();

        controller.addPlayer(mailId, teamName, year, month, day, rolePlayer);

    }

    private static void addTeamField() throws IncorrectInputException, ObjectNotExist, MemberNotExist, DontHavePermissionException, AlreadyExistException, ObjectAlreadyExist, NoEnoughMoney {
        HashMap<String, Team> teams = controller.getTeams();
        System.out.println("Choose team name to add new field");
        for (String teamName : teams.keySet()) {
            System.out.println(teamName);
        }
        String teamName = scanInput.nextLine();
        System.out.println("Enter Field name");
        String fieldName = scanInput.nextLine();

        controller.addField(teamName, fieldName);

    }

    private static void removeTeamManager() throws DontHavePermissionException {
        HashMap<String, Team> teams = controller.getTeams();
        System.out.println("Choose team name to remove manager");
        for (String teamName : teams.keySet()) {
            System.out.println(teamName);
        }

        String teamName = scanInput.nextLine();
        Team team = teams.get(teamName);

        System.out.println("Choose role to delete him from being manager");
        HashSet<Manager> managers = team.getManagers();
        for (Manager m : managers) {
            System.out.println(m.getUserMail());
        }
        String mailToRemove = scanInput.nextLine();

        try {
            controller.removeManager(teamName, mailToRemove);
        } catch (Exception ownerNotExist) {
        }
    }

    private static void removeTeamCoach() throws DontHavePermissionException {
        HashMap<String, Team> teams = controller.getTeams();
        System.out.println("Choose team name to remove coach");
        for (String teamName : teams.keySet()) {
            System.out.println(teamName);
        }

        String teamName = scanInput.nextLine();
        Team team = teams.get(teamName);

        System.out.println("Choose role to delete him from being coach");
        HashSet<Coach> coaches = team.getCoaches();
        for (Coach c : coaches) {
            System.out.println(c.getUserMail());
        }
        String mailToRemove = scanInput.nextLine();

        try {
            controller.removeCoach(teamName, mailToRemove);
        } catch (Exception ownerNotExist) {
        }
    }

    private static void removeTeamPlayer() throws DontHavePermissionException {
        HashMap<String, Team> teams = controller.getTeams();
        System.out.println("Choose team name to remove player");
        for (String teamName : teams.keySet()) {
            System.out.println(teamName);
        }

        String teamName = scanInput.nextLine();
        Team team = teams.get(teamName);

        System.out.println("Choose role to delete him from being player");
        HashSet<Player> players = team.getPlayers();
        for (Player p : players) {
            System.out.println(p.getUserMail());
        }
        String mailToRemove = scanInput.nextLine();

        try {
            controller.removePlayer(teamName, mailToRemove);
        } catch (Exception ownerNotExist) {
        }
    }



    private static void removeTeamField() throws IncorrectInputException, ObjectNotExist, MemberNotExist, DontHavePermissionException {
        HashMap<String, Team> teams = controller.getTeams();
        System.out.println("Choose team name to add remove field");
        for (String teamName : teams.keySet()) {
            System.out.println(teamName);
        }
        String teamName = scanInput.nextLine();
        System.out.println("Enter Field name");
        String fieldName = scanInput.nextLine();

        controller.removeField(teamName, fieldName);
    }

    private static void updatePlayerDetails() throws DontHavePermissionException, IncorrectInputException, ObjectNotExist, AccountNotExist, NoEnoughMoney, AlreadyExistException, MemberNotExist {
        HashMap<String, Team> teams = controller.getTeams();
        System.out.println("Choose team name to update player");
        for (String teamName : teams.keySet()) {
            System.out.println(teamName);
        }

        String teamName = scanInput.nextLine();
        Team team = teams.get(teamName);

        System.out.println("Choose player to update his role");
        HashSet<Player> players = team.getPlayers();
        for (Player p : players) {
            System.out.println(p.getUserMail());
        }
        String mailToRemove = scanInput.nextLine();
        System.out.println("What is the new role for this player? ");
        String role = scanInput.nextLine();
        controller.updatePlayerRole(teamName, mailToRemove,role);
    }

    private static void updateField() throws DontHavePermissionException, IncorrectInputException, ObjectNotExist, AccountNotExist, NoEnoughMoney, AlreadyExistException, MemberNotExist {
        HashMap<String, Team> teams = controller.getTeams();
        System.out.println("Choose team name to update field");
        for (String teamName : teams.keySet()) {
            System.out.println(teamName);
        }

        String teamName = scanInput.nextLine();
        Team team = teams.get(teamName);

        System.out.println("Choose field to make home field");
        HashSet<Field> fields = team.getTrainingFields();
        for (Field f : fields) {
            System.out.println(f.getNameOfField());
        }
        String makeHomeField = scanInput.nextLine();
        controller.updateHomeField(teamName, makeHomeField);
    }
  //  updateField
    /***************************************************tests********************************************************/

    private static void shacharFunctionForTesting() throws AlreadyExistException, DontHavePermissionException, MemberNotExist, PasswordDontMatchException {
/*
        SystemManager systemManager = new SystemManager("shachar meretz ", "shachar@gmail.com", "shachar", controller.getDbController());
      //  controller.addSystemManager(systemManager);
        controller.logIn("shachar@gmail.com","shachar");


        Fan fan1 = new Fan("adi", "adi@gmail.com", "adi");
        Fan fan2 = new Fan("alisa", "alisa@gmail.com", "alisa");
        Player player1 = new Player("yaara", "yaara@gmail.com", "yaara", new Date(1995, 1, 1), "player");
        Player player2 = new Player("daniel", "daniel@gmail.com", "daniel", new Date(1995, 1, 1), "player");
        Player player3 = new Player("hilla", "hilla@gmail.com", "hilla", new Date(1995, 1, 1), "player");
        Player player4 = new Player("noa", "noa@gmail.com", "noa", new Date(1995, 1, 1), "player");
        Player player5 = new Player("liat", "liat@gmail.com", "liat", new Date(1995, 1, 1), "player");
        Player player6 = new Player("flanktin", "flanktin@gmail.com", "neta", new Date(1995, 1, 1), "player");
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

      /*  controller.addPlayer(player1);
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
        Owner owner1 = new Owner("ariel pelner", "ariel@gmail.com", "127", controller.getDbController());
        Owner owner2 = new Owner("dor atzmon", "dor@gmail.com", "128", controller.getDbController());
        controller.addOwner(owner1);
        controller.addOwner(owner2);

        controller.addFan(fan1);


        //  controller.addReferee(systemManager.getUserMail(), fan1.getUserMail(), true);
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

      //  controller.addTeam(players1, coaches1, managers1, owners1, name);
*/

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
    //system.addReferee();
    //system
    //system.addTeam();


    /***************************************************testsHillaPeter********************************************************/

    /**
     * checking owner- please don't delete it! (Hilla P)
     */


    /******************************* public function for guest menu (noa) **********************************/

    /**
     * This function fill the signIn-form with user mail, user name and user password
     *
     * @return String array - details[mail,name,password]
     */

    public static String[] fillFormSignIn() throws IncorrectPasswordInputException, IncorrectInputException {
        String[] details = new String[6];
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
            throw new IncorrectPasswordInputException();
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

        //todo fix the check if legal
        System.out.println("please enter your birth date");
        System.out.println("year:");
        String year = scanInput.nextLine();
        System.out.println("month:");
        String month = scanInput.nextLine();
        System.out.println("day:");
        String day = scanInput.nextLine();
        if (!checkDateBirth(year, month, day)) {
            throw new IncorrectInputException("incorrect date input");
        }

        System.out.println("your details entered successfully!\nplease wait for confirmation");
        details[0] = mailInput;
        details[1] = nameInput;
        details[2] = password;
        details[3] = year;
        details[4] = month;
        details[5] = day;
        return details;
    }

    private static boolean checkDateBirth(String year, String month, String day) {
        if (year.length() > 4) {
            return false;
        }
        if (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12) {
            return false;
        }
        if (Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31) {
            return false;
        }
        return true;
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


    /***********************************************AssociationDelegate**************************************************/

    private static void AssociationDelegateMenu(Member member) throws IncorrectInputException, DontHavePermissionException {
        String input = "";
        while (!input.equals("Exit")) {
            System.out.println("choose one of the following options:\n");
            System.out.println("write \"1\" to add new league");
            System.out.println("write \"2\" to define season in a specific league");
            System.out.println("write \"3\" to set referee to a league in a specific season");
            System.out.println("write \"4\" to change the score policy in a specific league in season");
            System.out.println("write \"5\" to set scheduling policy in a specific league in season");
            System.out.println("\nwrite \"Exit\" if you want to finish. \n");
            input = "";
            while (input.equals("")) {
                input = scanInput.nextLine();
            }
            switch (input) {
                case "1":
                    System.out.println("Please write a name for new league");
                    String leagueName = scanInput.nextLine();
                    try {
                        controller.setLeague(leagueName);
                    } catch (IncorrectInputException incorrectInput) {
                        System.out.println("The name of league must contains only letters");
                        break;
                    } catch (AlreadyExistException alreadyExist) {
                        System.out.println("This name is already exists");
                        break;
                    } catch (Exception e) {
                        System.out.println("You can't define a league");
                        break;
                    }
                    break;

                case "2":

                    try {
                        HashMap<String, League> leagues = controller.getLeagues();
                        System.out.println("These are the leagues: ");
                        for (String league : leagues.keySet()) {
                            System.out.println("League: " + league);
                        }
                        System.out.println("Please choose a specific league (only letters)");
                        String specificLeague = scanInput.nextLine();
                        System.out.println("Please write the year of this league");
                        String year = scanInput.nextLine();
                        try {
                            controller.setLeagueByYear(specificLeague, year);
                            //score policy
                            System.out.println("Please write the points for a winning team");
                            String sWinning = scanInput.nextLine();
                            System.out.println("Please write the points for a draw game");
                            String sDraw = scanInput.nextLine();
                            System.out.println("Please write the points for a losing team");
                            String sLosing = scanInput.nextLine();
                            controller.changeScorePolicy(specificLeague, year, sWinning, sDraw, sLosing);
                            System.out.println("The score policy has been changed successfully");
                            //scheduling policy
                            HashMap<String, ASchedulingPolicy> schedulingPolicies = controller.getSchedulingPolicies();
                            System.out.println("These the scheduling policies in the system: ");
                            for (String policyName : schedulingPolicies.keySet()) {
                                System.out.println("policy- " + policyName);
                            }
                            System.out.println("Please write the name of policy");
                            String policy = scanInput.nextLine();
                            controller.setSchedulingPolicyToLeagueInSeason(specificLeague, year, policy);
                        } catch (DontHavePermissionException e) {
                            System.out.println("You don't have permission to this action");
                        } catch (IncorrectInputException e) {
                            System.out.println("There isn't this policy");
                        }
                    } catch (ObjectNotExist incorrectInput) {
                        System.out.println(incorrectInput.getMessage());
                        break;
                    } catch (AlreadyExistException alreadyExist) {
                        System.out.println("This season is already exist in this league");
                        break;
                    } catch (Exception e) {
                        System.out.println("You can't do this functionality");
                        break;
                    }
                    break;

                case "3":
                    try {
                        System.out.println("Please write a league ");
                        String league = scanInput.nextLine();
                        HashMap<String, League> leagues = controller.getLeagues();
                        if (!leagues.containsKey(league)) {
                            break;
                        }
                        System.out.println("Please write a specific season of this league ");
                        String season = scanInput.nextLine();
                        HashMap<String, Season> seasons = controller.getSeasons();
                        if (!seasons.containsKey(season)) {
                            break;
                        }
                        HashMap<String, Referee> referees = controller.getRefereesDoesntExistInTheLeagueAndSeason(league, season);
                        System.out.println("These all referees you can add to league- " + league + " in season- " + season);
                        for (String nameOfReferee : referees.keySet()) {  //display all referees who doesn't exist in this league and season
                            System.out.println("Referee- " + nameOfReferee);
                        }
                        System.out.println("Please write the referee's name you would like to add ");
                        boolean validName = false;
                        int counterTries = 0;
                        String refereeNameToAdd = "";
                        while ((!validName) || counterTries == 3) { //give 3 tries to write valid name of referee
                            refereeNameToAdd = scanInput.nextLine();
                            if (referees.containsKey(refereeNameToAdd)) {
                                validName = true;
                            } else {
                                System.out.println("" + refereeNameToAdd + " is not exist, write valid name of referee");
                                counterTries++;
                            }
                        }
                        if (validName) {
                            controller.addRefereeToLeagueInSeason(league, season, refereeNameToAdd);
                        } else {
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("You don't have permission to this action");
                        break;
                    }
                    break;

                case "4":
                    HashMap<String, Season> seasons = controller.getSeasons();
                    System.out.println("These are the seasons: ");
                    for (String key : seasons.keySet()) {
                        System.out.println("Season- " + key);
                    }
                    System.out.println("Please write season");
                    String seasonName = scanInput.nextLine();
                    if (!seasons.containsKey(seasonName)) {
                        break;
                    }
                    HashMap<League, LeagueInSeason> leaguesInSpecificSeason = seasons.get(seasonName).getLeagues();
                    System.out.println("These are the leagues in season " + seasonName);
                    for (League leagueObj : leaguesInSpecificSeason.keySet()) {
                        System.out.println("League- " + leagueObj.getName());
                    }
                    System.out.println("Please write a league");
                    String sLeague = scanInput.nextLine();
                    if (!leaguesInSpecificSeason.containsKey(sLeague)) {
                        break;
                    }
                    System.out.println("Please write the points for a winning team");
                    String sWinning = scanInput.nextLine();
                    System.out.println("Please write the points for a draw game");
                    String sDraw = scanInput.nextLine();
                    System.out.println("Please write the points for a losing team");
                    String sLosing = scanInput.nextLine();
                    try {
                        controller.changeScorePolicy(sLeague, seasonName, sWinning, sDraw, sLosing);
                        System.out.println("The score policy has been changed successfully");
                    } catch (IncorrectInputException incorrectInputException) {
                        System.out.println("Incorrect input");
                        break;
                    } catch (Exception e) {
                        break;
                    }
                    break;

                case "5":
                    try {
                        HashMap<String, Season> seasons1 = controller.getSeasons();
                        System.out.println("These are the seasons: ");
                        for (String key : seasons1.keySet()) {
                            System.out.println("Season- " + key);
                        }
                        System.out.println("Please write season");
                        String seasonName1 = scanInput.nextLine();
                        if (!seasons1.containsKey(seasonName1)) {
                            break;
                        }
                        HashMap<League, LeagueInSeason> leaguesInSpecificSeason1 = seasons1.get(seasonName1).getLeagues();
                        System.out.println("These are the leagues in season " + seasonName1);
                        for (League leagueObj : leaguesInSpecificSeason1.keySet()) {
                            System.out.println("League- " + leagueObj.getName());
                        }
                        System.out.println("Please write a league");
                        String sLeague1 = scanInput.nextLine();
                        if (!leaguesInSpecificSeason1.containsKey(sLeague1)) {
                            break;
                        }
                        HashMap<String, ASchedulingPolicy> schedulingPolicies = controller.getSchedulingPolicies();
                        System.out.println("These are the scheduling policies in the system: ");
                        for (String policyName : schedulingPolicies.keySet()) {
                            System.out.println("policy- " + policyName);
                        }
                        System.out.println("Please write the name of policy");
                        String policy = scanInput.nextLine();
                        controller.setSchedulingPolicyToLeagueInSeason(sLeague1, seasonName1, policy);
                    } catch (DontHavePermissionException e) {
                        System.out.println("You don't have permission to this action");
                    } catch (IncorrectInputException e) {
                        System.out.println("There isn't this policy");
                    } catch (ObjectNotExist objectNotExist) {
                        System.out.println("The Object is not exist");
                    }
                case "Exit":
                  //  showMenu(controller.logOut());
                default:

            }
        }
    }

    /***********************************************MainReferee**************************************************/
    private static void mainRefereeMenu() throws DontHavePermissionException {
        String input = "";
        while (!input.equals("ExitAll")) {
            System.out.println("choose one of the following options:\n");
            System.out.println("write \"1\" for update personal details");
            System.out.println("write \"2\" for update game event");
            System.out.println("write \"3\" for get game schedule");
            System.out.println("\nwrite \"LogOut\" if you want to finish. \n");
            //todo case for log out and not exit all
            input = "";
            while (input.equals("")) {
                input = scanInput.nextLine();
            }
            switch (input) {

                case "1": {
                    try {
                        System.out.println("Please enter your new details: \n");
                        System.out.println("Insert your new name: \n");
                        String newName = scanInput.nextLine();
                        System.out.println("Insert your new mail: \n");
                        String newMail = scanInput.nextLine();
                        System.out.println("Insert your new training: \n");
                        String newTraining = scanInput.nextLine();
                        controller.updateDetails(newName, newMail,newTraining);
                    } catch (Exception e) {
                        System.out.println("One or more of the details are illegal.");
                    }
                    break;
                }
                case "2": {
                    try {
                        LinkedList<Game> editableGames = controller.getEditableGames();
                        if (editableGames.size() == 0) {
                            System.out.println("There are not games you can edit.");
                            break;
                        }
                        System.out.println("The games you can still edit are:");
                        for (int i = 0; i < editableGames.size(); i++) {
                            System.out.println((i + 1) + "." + editableGames.get(i).getHostTeam().getName() + " Vs. " + editableGames.get(i).getVisitorTeam().getName() + " at " + editableGames.get(i).getField().getName());
                        }
                        System.out.println("Please insert the number of the game you want to update");
                        String gameNumber = scanInput.nextLine();
                        int gameIndex = Integer.parseInt(gameNumber) - 1;
                        if (gameIndex >= editableGames.size() || gameIndex < 0) {
                            throw new IncorrectInputException();
                        }
                        Game game = editableGames.get(gameIndex);
                        System.out.println("enter the time in the game the event happened: ");
                        int timeInGame = scanInput.nextInt();
                        System.out.println("Please choose the type of the event:");
                        System.out.println("1.Goal 2.Foul 3.Red card 4.Yellow card 5.Wound 6.Replacement");
                        String eventType = scanInput.nextLine();
                        EventInGame event = null;
                        switch (eventType) {
                            case "1": {
                                event = EventInGame.GOAL;
                                break;
                            }
                            case "2": {
                                event = EventInGame.FOUL;
                                break;
                            }
                            case "3": {
                                event = EventInGame.RED_CARD;
                                break;
                            }
                            case "4": {
                                event = EventInGame.YELLOW_CARD;
                                break;
                            }
                            case "5": {
                                event = EventInGame.WOUND;
                                break;
                            }
                            case "6": {
                                event = EventInGame.REPLACEMENT;
                                break;
                            }
                            default: {
                                throw new IncorrectInputException();
                            }
                        }
                        System.out.println("please insert a description of the event");
                        String description = scanInput.nextLine();
                        System.out.println("There are players involved in thr event?");
                        System.out.println("Insert 1 for Yes or 2 for No");
                        int morePlayers = scanInput.nextInt();
                        ArrayList<Player> players = new ArrayList<>();
                        while (morePlayers == 1) {
                            System.out.println("Which team the player belongs to?");
                            System.out.println("Insert 1 for the team " + game.getHostTeam().getName() + " or 2 for the team " + game.getVisitorTeam().getName());
                            int team = scanInput.nextInt();
                            ArrayList<Player> teamPlayers = new ArrayList<>();
                            if (team == 1) {
                                teamPlayers.addAll(game.getHostTeam().getPlayers());
                            } else if (team == 2) {
                                teamPlayers.addAll(game.getVisitorTeam().getPlayers());
                            } else {
                                throw new IncorrectInputException();
                            }
                            System.out.println("please choose the number of the player:");
                            int i = 0;
                            for (Player player : teamPlayers) {
                                System.out.println(i + 1 + ". " + player.getName());
                            }
                            int playerIndex = scanInput.nextInt();
                            if (playerIndex - 1 < 0 || playerIndex > teamPlayers.size()) {
                                throw new IncorrectInputException();
                            }
                            players.add(teamPlayers.get(playerIndex));
                            System.out.println("There are more players involved in thr event?");
                            System.out.println("Insert 1 for Yes or other character for No");
                            morePlayers = scanInput.nextInt();
                        }
                        controller.updateGameEvent(game, timeInGame, event, new Date(), description, players);
                        break;
                    } catch (IncorrectInputException e) {
                        System.out.println(e.getStackTrace());
                    }
                }
                case "3": {
                    try {
                        HashSet<Game> games = controller.getGameSchedule();
                        System.out.println("The games scheduled for you:");
                        if (games.size() == 0) {
                            System.out.println("you dont have any games in your schedule");
                            break;
                        }
                        for (Game game : games) {
                            System.out.println("" + game.getHostTeam().getName() + " Vs. " + game.getVisitorTeam() + "at" + game.getField().getName() + " in " + game.getDateAndTimeString());
                        }
                        break;
                    } catch (DontHavePermissionException e) {
                        System.out.println("you are not allowed to get a referee's schedule");
                    }
                }
                case "LogOut": {
                    controller.logOut();
                    member = null;//(Role) controller.logOut();
                    input = "ExitAll";
                    break;
                }

            }
        }
    }

    /***********************************************SecondaryReferee**************************************************/
    private static void secondaryRefereeMenu() throws DontHavePermissionException {
        String input = "";
        while (!input.equals("ExitAll")) {
            System.out.println("choose one of the following options:\n");
            System.out.println("write \"1\" to update personal details");
            System.out.println("write \"2\" to get game schedule");
            System.out.println("\nwrite \"LogOut\" if you want to finish. \n");
            input = "";
            while (input.equals("")) {
                input = scanInput.nextLine();
            }
            switch (input) {

                case "1": {
                    try {
                        System.out.println("Please enter your new details: \n");
                        System.out.println("Insert your new name: \n");
                        String newName = scanInput.nextLine();
                        System.out.println("Insert your new mail: \n");
                        String newMail = scanInput.nextLine();
                        System.out.println("Insert your new training: \n");
                        String newTraining = scanInput.nextLine();
                        controller.updateDetails(newName, newMail,newTraining);
                    } catch (IncorrectInputException e) {
                        System.out.println("One or more of the details are illegal.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case "2": {
                    try {
                        HashSet<Game> games = controller.getGameSchedule();
                        System.out.println("The games scheduled for you:");
                        if (games.size() == 0) {
                            System.out.println("you dont have any games in your schedule");
                            break;
                        }
                        for (Game game : games) {
                            System.out.println("" + game.getHostTeam().getName() + " Vs. " + game.getVisitorTeam() + "at" + game.getField().getName() + " in " + game.getDateAndTimeString());
                        }
                        break;
                    } catch (DontHavePermissionException e) {
                        System.out.println("you are not allowed to get a referee's schedule");
                    }
                }
                case "LogOut": {
                    controller.logOut();
                    member = null;//(Role) controller.logOut();
                    input = "ExitAll";
                    break;
                }
            }
        }
    }

    /***********************************************Fan**************************************************/
    private static void fanMenu(){
        String input = "";
        while (!input.equals("ExitAll")) {
            System.out.println("choose one of the following options:\n");
            System.out.println("write \"1\" to update personal details");
            System.out.println("write \"2\" to send a complaint");
            System.out.println("write \"3\" to follow a team");
            System.out.println("write \"4\" to follow a game");
            System.out.println("\nwrite \"LogOut\" if you want to finish. \n");
            input = "";
            while (input.equals("")) {
                input = scanInput.nextLine();
            }
            switch (input) {

                case "1": {
                    try {
                        System.out.println("Please enter your new details: \n");
                        System.out.println("Insert your new name: \n");
                        String newName = scanInput.nextLine();
                        System.out.println("Insert your new mail: \n");
                        String newMail = scanInput.nextLine();
                        controller.updatePersonalDetails(newName,newMail);
                    } catch (IncorrectInputException e) {
                        System.out.println("One or more of the details are illegal.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "2": {
                    try {
                        System.out.println("Please enter your complaint");
                        String complaint = scanInput.nextLine();
                        controller.sendComplaint(path, complaint);
                        break;
                    } catch (DontHavePermissionException e) {
                        System.out.println("you dont have a permission to write a complaint");
                    }
                }
                case "3": {
                    try {
                        System.out.println("Please enter the team name:");
                        String teamName = scanInput.nextLine();
                        Team team = controller.getTeamByName(teamName);
                        controller.addFollowerToTeam(team);
                        break;
                    } catch (ObjectNotExist e) {
                        System.out.println("The team is not exist.");
                    } catch (DontHavePermissionException e) {
                        System.out.println("you dont have a permission to follow a team");
                    }
                }
                case "4": {
                    try {
                        System.out.println("Please enter the league name:");
                        String leagueName = scanInput.nextLine();
                        System.out.println("Please enter the season year");
                        String seasonYear = scanInput.nextLine();
                        HashSet<Game> games = controller.getGames(leagueName, seasonYear);
                        if (games.size() == 0) {
                            System.out.println("There are no games in this League and season");
                            break;
                        }
                        ArrayList<Game> gamesList = new ArrayList<>();
                        gamesList.addAll(games);
                        System.out.println("The games are:");
                        for (int i = 0; i < gamesList.size(); i++) {
                            System.out.println("" + gamesList.get(i).getHostTeam().getName() + " Vs. " + gamesList.get(i).getVisitorTeam() + "at" + gamesList.get(i).getField().getName() + " in " + gamesList.get(i).getDateAndTimeString());
                        }
                        int gameIndex = scanInput.nextInt();
                        if (gameIndex - 1 < 0 || gameIndex > gamesList.size()) {
                            throw new IncorrectInputException();
                        }
                        controller.addFollowerToGame(gamesList.get(gameIndex));
                        System.out.println("Insert the number of the game you would like to follow");
                        System.out.println();
                        break;
                    } catch (ObjectNotExist e) {
                        System.out.println("The team is not exist.");
                    } catch (DontHavePermissionException e) {
                        System.out.println("you dont have a permission to follow a team");
                    } catch (IncorrectInputException e) {
                        System.out.println("Incorrect input");
                    } catch (Exception e) {
                        System.out.println("The league or the season doesn't exist");
                    }
                }
                case "LogOut": {
                    controller.logOut();
                    member = null;//(Role) controller.logOut();
                    input = "ExitAll";
                    break;
                }
            }
        }
    }



}

package system;

import Game.Team;
import League.*;
import Users.*;
import Exception.*;
import javafx.util.Pair;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.HashMap;
import java.util.LinkedList;

public class SystemController {
    private String name;
    private Role connectedUser;
    private DBController dbController;
    //  private HashMap<Member,String> passwordValidation;


    /**
     * constructor
     *
     * @param name
     */
    public SystemController(String name) {
        this.name = name;
        this.initSystem("",""); // change it
        connectedUser = new Guest(dbController);
        //todo
//        password verifications
//        passwordValidation=new HashMap<>();
//        for(Role r:roles){
//            if(r instanceof Member){
//                
//            }
//        }
        //member = user; (argument in the constructor-Member user- Fan, Owner, AD, Referee...)
    }

    public void initSystem(String userName, String password) {
        //check if the user name and the password are connect
        dbController = new DBController();
    }

    /*************************************** function for guest******************************************/
    /**
     * this function makes a Guest into a member
     * if the member's mail doesnt exist -
     * we will remove the Guest from the roles map and add create a Fan member by default and return true
     * if the member's mail exist in the system - prints a error message and return false.
     *
     * @return true = success or false = failed to sign
     */
    public Member signIn(String userName, String userMail, String password) throws IncorrectInputException, AlreadyExistException {
        return ((Guest)connectedUser).signIn(userMail,userName,password);

    }
    /**
     * this function makes a guest into an existing member.
     * if the member doesnt exist - return null
     * if the member exist - return the member
     *
     * @return
     */
    public Member logIn(String userMail, String userPassword) throws MemberNotExist, PasswordDontMatchException {
        return ((Guest)connectedUser).logIn(userMail,userPassword);
    }
    /*************************************** function for system manager******************************************/

    /***
     * this function get id of the refree to remove and the id of the system manager that take care of this function
     * if the refree didnt exist - return false
     * if the refree exist - delete it and return true
     */
    public boolean removeReferee(String systemManagerId, String refreeId) throws MemberNotSystemManager {
//        SystemManager systemManager = systemManagers.get(systemManagerId);
//        if (null != systemManager) {
//            return systemManager.removeReferee(refreeId);
//        } else {
//            throw new MemberNotSystemManager();
//        }
        return false;
    }

    /***
     * this function get id of the member to make refree and the id of the system manager that take care of this function
     * if the refree already exist - return false
     * if the refree not exist and success of adding it - add it and return true
     */
    public boolean addReferee(String systemManagerId, String refreeId, boolean ifMainRefree) throws MemberNotSystemManager {
//        SystemManager systemManager = systemManagers.get(systemManagerId);
//        if (null != systemManager) {
//            return systemManager.addReferee(refreeId, ifMainRefree);
//        } else {
//            throw new MemberNotSystemManager();
//        }
        return false;
    }

    /**
     * this function get the team name and the id of the system manager that take care of this function
     * if the team name already exist - close the team and return true
     * if the team dont exist return false
     *
     * @param systemManagerId
     * @param teamName
     * @return
     * @throws MemberNotSystemManager
     */
    public boolean closeTeam(String systemManagerId, String teamName) throws MemberNotSystemManager {
//        SystemManager systemManager = systemManagers.get(systemManagerId);
//        if (null != systemManager) {
//            return systemManager.closeTeam(teamName);
//        } else {
//            throw new MemberNotSystemManager();
//        }
        return false;
    }

    /**
     * this function get the id of the member we want to delete and the id of the system manager that take care of this function
     *
     * @param systemManagerId
     * @param id
     * @return
     * @throws MemberNotSystemManager
     */
    public boolean removeMember(String systemManagerId, String id) throws MemberNotSystemManager {
//        SystemManager systemManager = systemManagers.get(systemManagerId);
//        if (null != systemManager) {
//            return systemManager.removeMember(id);
//        } else {
//            throw new MemberNotSystemManager();
//        }
        return false;
    }

    /**
     * this function get the path to the complaint file and the id of the system manager that take care of this function
     *
     * @param systemManagerId
     * @param path
     * @throws MemberNotSystemManager
     */
    public void watchComplaint(String systemManagerId, String path) throws MemberNotSystemManager {
//        SystemManager systemManager = systemManagers.get(systemManagerId);
//        if (null != systemManager) {
//            LinkedList<String> complaint = systemManager.watchComplaint(path);
//        } else {
//            throw new MemberNotSystemManager();
//        }
    }

    /**
     * this function get the path to the complaint file , the response for the complaint and the id of the system manager that take care of this function
     *
     * @param systemManagerId
     * @param path
     * @param responseForComplaint
     * @return
     * @throws MemberNotSystemManager
     */
    public boolean responseComplaint(String systemManagerId, String path, LinkedList<Pair<String, String>> responseForComplaint) throws MemberNotSystemManager {
//        SystemManager systemManager = systemManagers.get(systemManagerId);
//        if (null != systemManager) {
//            return systemManager.ResponseComplaint(path, responseForComplaint);
//        } else {
//            throw new MemberNotSystemManager();
//        }
        return false;
    }

    public void schedulingGames(String systemManagerId) throws MemberNotSystemManager {
//        SystemManager systemManager = systemManagers.get(systemManagerId);
//        if (null != systemManager) {
//            //   systemManager.schedulingGames();
//        } else {
//            throw new MemberNotSystemManager();
//        }
    }

    public void viewSystemInformation(String systemManagerId) throws MemberNotSystemManager {
//        SystemManager systemManager = systemManagers.get(systemManagerId);
//        if (null != systemManager) {
//            systemManager.viewSystemInformation();
//        } else {
//            throw new MemberNotSystemManager();
//        }
    }

    /**
     * this function get the team name and all the team member and the id of the system manager that take care of this function
     * if the team name not exist - open the team and return true
     * if the team exist return false
     *
     * @param systemManagerId
     * @param players
     * @param coachs
     * @param managers
     * @param owners
     * @param teamName
     * @return
     * @throws MemberNotSystemManager
     */
    public boolean addTeam(String systemManagerId, LinkedList<String> players, LinkedList<String> coachs, LinkedList<String> managers, LinkedList<String> owners, String teamName) throws MemberNotSystemManager {
//        SystemManager systemManager = systemManagers.get(systemManagerId);
//        if (null != systemManager) {
//            return systemManager.addNewTeam(players, coachs, managers, owners, teamName);
//        } else {
//            throw new MemberNotSystemManager();
//        }
        return false;
    }

    /***************************************help function for system manager******************************************/

    public boolean notAllTheIdAreMembers(LinkedList<String> idPlayers, LinkedList<String> idCoach, LinkedList<String> idManager, LinkedList<String> idOwner) {
//        for (int i = 0; i < idPlayers.size(); i++) {
//            if (roles.containsKey(idPlayers.get(i)) == false)
//                return true;
//        }
//        for (int i = 0; i < idCoach.size(); i++) {
//            if (roles.containsKey(idCoach.get(i)) == false)
//                return true;
//        }
//        for (int i = 0; i < idManager.size(); i++) {
//            if (roles.containsKey(idManager.get(i)) == false)
//                return true;
//        }
//        for (int i = 0; i < idOwner.size(); i++) {
//            if (roles.containsKey(idOwner.get(i)) == false)
//                return true;
//        }
        return false;
    }

    public boolean alreadyIncludeThisTeamName(String teamName) {
//        if (teams == null) {
//            return false;
//        }
//        for (String name : teams.keySet()
//        ) {
//            if (name.equals(teamName))
//                return true;
//        }
        return false;
    }

    public LinkedList<Member> returnFromSystemTheExactUsers(LinkedList<String> id) {
//        LinkedList<Member> toReturn = new LinkedList<>();
//        for (int i = 0; i < id.size(); i++) {
//            toReturn.add((Member) roles.get(id.get(i)));
//        }
//        return toReturn;
        return null;
    }

    public void makeTheRoleAReferee(String id, boolean mainReferee) {
        //if the referee is main referee the boolean filed wil be true
        //change the role from fan to referee
//        Fan fan = (Fan) roles.get(id);
//        Referee referee;
//        if (mainReferee)
//            referee = new MainReferee(fan.getName(), fan.getUserMail(), fan.getPassword(), "");
//        else
//            referee = new SecondaryReferee(fan.getName(), fan.getUserMail(), fan.getPassword(), "");
//        roles.put(fan.getUserMail(), referee);
    }


    /******************************* function for Testing!!!!! (noa) *********************************/

//    /**
//     * this function is used in test - return if the member exist in the system
//     *
//     * @param memberMail
//     * @return
//     */
//    public boolean ifMemberExistTesting(String memberMail) {
//        if (memberMail != null) {
//            return roles.containsKey(memberMail);
//        }
//        return false;
//    }
//
//    public int sizeOfMembersListTesting() {
//        return this.roles.size();
//    }
//
//    public Role getMember(String id) {
//        if (this.roles.get(id) == null)
//            return this.systemManagers.get(id);
//        return this.roles.get(id);
//    }
//
//    public void addMemberTesting(Member member) {
//        this.roles.put(member.getUserMail(), member);
//    }

    /*************************************** function for owner******************************************/

    /**
     * owner:
     * add a coach
     *
     * @param teamName
     * @param mailId
     * @throws ManagerNotExist
     * @throws TeamNotExist
     * @throws NoEnoughMoney
     */
    public void addCoach(String teamName, String mailId) throws ManagerNotExist, TeamNotExist, NoEnoughMoney, MemberNotExist, AlreadyExistException {
        if (!dbController.getRoles().containsKey(mailId))
            throw new ManagerNotExist();
        if (!dbController.getTeams().containsKey(teamName))
            throw new TeamNotExist();
        if (dbController.getTeams().get(teamName).getAccount().getAmountOfTeam() < 0)
            throw new NoEnoughMoney();

        ((Owner) connectedUser).addCoach(teamName, mailId);
    }

    /**
     * owner:
     * add a player
     *
     * @param mailId
     * @param teamName
     * @param year
     * @param month
     * @param day
     * @param rolePlayer
     */
    public void addPlayer(String mailId, String teamName, int year, int month, int day, String rolePlayer) throws ManagerNotExist, TeamNotExist, IncorrectInputException, MemberNotExist, AlreadyExistException {
        if (!dbController.getRoles().containsKey(mailId))
            throw new ManagerNotExist();
        if (!dbController.getTeams().containsKey(teamName))
            throw new TeamNotExist();
        if (year < 0 || year > 2020 || month > 12 || month < 1 || day < 1 || day > 32 || rolePlayer == null)
            throw new IncorrectInputException();

        ((Owner) connectedUser).addPlayer(teamName, mailId, year, month, day, rolePlayer);
    }

    /**
     * owner:
     * add a field to list of training field of his team
     *
     * @param teamName
     * @param fieldName
     */
    public void addField(String teamName, String fieldName) throws ManagerNotExist, TeamNotExist, IncorrectInputException {
        if (!dbController.getTeams().containsKey(teamName))
            throw new TeamNotExist();
        if (!dbController.getTeams().get(teamName).getTrainingFields().contains(fieldName))
            throw new IncorrectInputException();
        if (!dbController.getTeams().get(teamName).getHomeField().equals(fieldName))
            throw new IncorrectInputException();
        ((Owner) connectedUser).addField(teamName, fieldName);
    }

    /**
     * owner:
     * add new manager to one of his groups
     *
     * @param teamName
     * @param mailId
     * @throws NoEnoughMoney
     * @throws TeamNotExist
     * @throws ManagerNotExist
     */
    public void addManager(String teamName, String mailId) throws NoEnoughMoney, TeamNotExist, ManagerNotExist, MemberNotExist, AlreadyExistException {
        if (!dbController.getRoles().containsKey(mailId))
            throw new ManagerNotExist();
        if (!dbController.getTeams().containsKey(teamName))
            throw new TeamNotExist();
        if (dbController.getTeams().get(teamName).getAccount().getAmountOfTeam() < 0)
            throw new NoEnoughMoney();

        ((Owner) connectedUser).addManager(teamName, mailId);
    }

    /**
     * owner:
     * removes a manager
     *
     * @param teamName
     * @param mailToRemove
     */
    public void removeManager(String teamName, String mailToRemove) throws TeamNotExist, OwnerNotExist, ManagerNotExist, MemberNotExist, AlreadyExistException {

        if (!dbController.getTeams().containsKey(teamName))
            throw new TeamNotExist();
        if (!(dbController.getRoles().containsKey(connectedUser.getName())))
            throw new OwnerNotExist();
        if (!dbController.getRoles().containsKey(mailToRemove))
            throw new ManagerNotExist();

        ((Owner) connectedUser).removeManager(teamName, mailToRemove);

    }

    /**
     * owner:
     * remove coach
     *
     * @param teamName
     * @param mailToRemove
     * @throws TeamNotExist
     * @throws OwnerNotExist
     * @throws MemberNotExist
     */
    public void removeCoach(String teamName, String mailToRemove) throws TeamNotExist, OwnerNotExist, MemberNotExist, AlreadyExistException {
        if (!dbController.getTeams().containsKey(teamName))
            throw new TeamNotExist();
        if (!(dbController.getRoles().containsKey(connectedUser.getName())))
            throw new OwnerNotExist();
        if (!dbController.getRoles().containsKey(mailToRemove))
            throw new MemberNotExist();

        ((Owner) connectedUser).removeCoach(teamName, mailToRemove);
    }

    /**
     * owner:
     * remove player from team
     *
     * @param teamName
     * @param mailToRemove
     */
    public void removePlayer(String teamName, String mailToRemove) throws TeamNotExist, OwnerNotExist, MemberNotExist, AlreadyExistException {
        if (!dbController.getTeams().containsKey(teamName))
            throw new TeamNotExist();
        if (!(dbController.getRoles().containsKey(connectedUser.getName())))
            throw new OwnerNotExist();
        if (!dbController.getRoles().containsKey(mailToRemove))
            throw new MemberNotExist();

        ((Owner) connectedUser).removePlayer(teamName, mailToRemove);
    }

    /**
     * owner:
     * removes a field
     *
     * @param teamName
     * @param fieldName
     */
    public void removeField(String teamName, String fieldName) throws TeamNotExist, IncorrectInputException, OwnerNotExist {
        if (!dbController.getTeams().containsKey(teamName))
            throw new TeamNotExist();
        if (!(dbController.getRoles().containsKey(connectedUser.getName())))
            throw new OwnerNotExist();
        if (!dbController.getTeams().containsKey(teamName))
            throw new TeamNotExist();
        if (!dbController.getTeams().get(teamName).getTrainingFields().contains(fieldName))
            throw new IncorrectInputException();
        if (!dbController.getTeams().get(teamName).getHomeField().equals(fieldName))
            throw new IncorrectInputException();
        ((Owner) connectedUser).removeField(teamName, fieldName);
    }

    /**
     * owner:
     * add new owner to one of his groups
     *
     * @param teamName
     * @param mailId
     * @throws NoEnoughMoney
     * @throws TeamNotExist
     * @throws ManagerNotExist
     */
    public void addNewOwner(String teamName, String mailId) throws NoEnoughMoney, TeamNotExist, OwnerNotExist, MemberNotExist, AlreadyExistException {
        if (!dbController.getRoles().containsKey(mailId))
            throw new OwnerNotExist();
        if (!dbController.getTeams().containsKey(teamName))
            throw new TeamNotExist();
        if (dbController.getTeams().get(teamName).getAccount().getAmountOfTeam() < 0)
            throw new NoEnoughMoney();

        ((Owner) connectedUser).addNewOwner(teamName, mailId);
    }

    /**
     * owner:
     * close team temporary
     *
     * @param teamName
     * @throws TeamNotExist
     */
    public void temporaryTeamClosing(String teamName) throws TeamNotExist, UnavailableOption {

        if (!dbController.getTeams().containsKey(teamName))
            throw new TeamNotExist();
        if (!((Owner) connectedUser).getTeams().containsKey(teamName))
            throw new TeamNotExist();
        if (!((Owner) connectedUser).getTeams().get(teamName).getStatus())
            throw new UnavailableOption();

        ((Owner) connectedUser).temporaryTeamClosing(teamName);
    }

    /**
     * owner:
     * reopen team
     *
     * @param teamName
     * @throws TeamNotExist
     */
    public void reopenClosedTeam(String teamName) throws TeamNotExist, UnavailableOption {
        if (!dbController.getTeams().containsKey(teamName))
            throw new TeamNotExist();
        if (!((Owner) connectedUser).getTeams().containsKey(teamName))
            throw new TeamNotExist();
        if (!((Owner) connectedUser).getTeams().get(teamName).getStatus())
            throw new UnavailableOption();

        ((Owner) connectedUser).reopenClosedTeam(teamName);
    }

    /**
     * owner:
     * add outcome of team
     *
     * @param teamName
     * @throws NoEnoughMoney
     * @throws TeamNotExist
     * @throws ManagerNotExist
     */
    public void addOutCome(String teamName, String description, double amount) throws NoEnoughMoney, TeamNotExist, AccountNotExist, IncorrectInputException {
        if (description == null || amount < 0)
            throw new IncorrectInputException();
        if (!dbController.getTeams().containsKey(teamName))
            throw new TeamNotExist();
        if (dbController.getTeams().get(teamName).getAccount().getAmountOfTeam() < 0)
            throw new NoEnoughMoney();
        if (dbController.getTeams().get(teamName).getAccount().getAmountOfTeam() - amount < 0)
            throw new NoEnoughMoney();
        if (dbController.getTeams().get(teamName).getAccount() == null)
            throw new AccountNotExist();

        ((Owner) connectedUser).addOutCome(teamName, description, amount);
    }

    /**
     * owner:
     * add outcome of team
     *
     * @param teamName
     * @throws NoEnoughMoney
     * @throws TeamNotExist
     * @throws ManagerNotExist
     */
    public void addInCome(String teamName, String description, double amount) throws NoEnoughMoney, TeamNotExist, AccountNotExist, IncorrectInputException {
        if (description == null || amount < 0)
            throw new IncorrectInputException();
        if (!dbController.getTeams().containsKey(teamName))
            throw new TeamNotExist();
        if (dbController.getTeams().get(teamName).getAccount().getAmountOfTeam() < 0)
            throw new NoEnoughMoney();
        if (dbController.getTeams().get(teamName).getAccount() == null)
            throw new AccountNotExist();

        ((Owner) connectedUser).addInCome(teamName, description, amount);
    }


    /********Getters for Owner********/
    public HashMap<String, Role> getRoles(){
        return ((Owner) connectedUser).getRoles();
    }

    public HashMap<String, Team> getTeams() {
        return ((Owner) connectedUser).getTeams();
    }


    /*************************************** function for associationDelegate******************************************/

    /**
     * this
     *
     * @param
     * @param leagueName
     * @throws AlreadyExistException
     */
    public void setLeague(String leagueName) throws AlreadyExistException, IncorrectInputException {
        try {
            ((AssociationDelegate) connectedUser).setLeague(leagueName);
        } catch (IncorrectInputException incorrectInput) {
            throw new IncorrectInputException(leagueName);
        } catch (AlreadyExistException alreadyExist) {
            throw new AlreadyExistException();
        } catch (Exception e) {

        }
    }

    public void setLeagueByYear(String specificLeague, String year) throws ObjectNotExist, AlreadyExistException {
        try {
            ((AssociationDelegate) connectedUser).setLeagueByYear(specificLeague, year);
        } catch (ObjectNotExist incorrectInput) {
            throw new ObjectNotExist(incorrectInput.getMessage());
        } catch (AlreadyExistException alreadyExist) {
            throw new AlreadyExistException();
        }
    }

    public HashMap<String, League> getLeagues() {
        return dbController.getLeagues();
    }

}

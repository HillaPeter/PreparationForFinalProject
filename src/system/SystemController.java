package system;

import Asset.Coach;
import Asset.Manager;
import Asset.Player;
import Game.Team;
import League.*;
import Users.*;
import Exception.*;
import javafx.util.Pair;

import java.util.ArrayList;
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
        this.initSystem("", "", ""); // change it
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

    public void initSystem(String name, String userName, String password) {
        //check if the user name and the password are connect
        dbController = new DBController();
        System.out.println("Init System:");
        System.out.println("Connect to Security System");
        //todo
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
    public Member signIn(String userName, String userMail, String password) throws IncorrectInputException, AlreadyExistException, DontHavePermissionException {
        return ((Guest) connectedUser).signIn(userMail, userName, password);

    }
    public Role logOut(){
        this.connectedUser = new Guest(this.dbController);
        return this.connectedUser;
    }
    /**
     * this function makes a guest into an existing member.
     * if the member doesnt exist - return null
     * if the member exist - return the member
     *
     * @return
     */
    public Member logIn(String userMail, String userPassword) throws MemberNotExist, PasswordDontMatchException {
        this.connectedUser =((Guest)this.connectedUser).logIn(userMail, userPassword);
        return (Member) this.connectedUser;
    }
    /*************************************** function for system manager******************************************/
    /***
     * this function get id of the refree to remove and the id of the system manager that take care of this function
     * if the referee didnt exist - return false
     * if the referee exist - delete it and return true
     */
    public boolean removeReferee(String refereeId) throws DontHavePermissionException {
            if (connectedUser instanceof SystemManager) {
                SystemManager systemManager = (SystemManager) connectedUser;
                return systemManager.removeReferee(refereeId);
            } else {
                throw new DontHavePermissionException();
            }
        }
    /***
     * this function get id of the member to make referee and the id of the system manager that take care of this function
     * if the referee already exist - return false
     * if the referee not exist and success of adding it - add it and return true
     */
    public boolean addReferee(String refereeId, boolean ifMainReferee) throws DontHavePermissionException {
        if (connectedUser instanceof SystemManager) {
            SystemManager systemManager = (SystemManager) connectedUser;
            return systemManager.addReferee(refereeId, ifMainReferee);
        } else {
            throw new DontHavePermissionException();
        }
    }
    /**
     * this function get the team name and the id of the system manager that take care of this function
     * if the team name already exist - close the team and return true
     * if the team dont exist return false
     *
     * @param teamName
     * @return
     * @throws DontHavePermissionException
     */
    public boolean closeTeam(String teamName) throws DontHavePermissionException {
        if (connectedUser instanceof SystemManager) {
            SystemManager systemManager = (SystemManager) connectedUser;
            return systemManager.closeTeam(teamName);
        } else {
            throw new DontHavePermissionException();
        }
    }

    /**
     * this function get the id of the member we want to delete and the id of the system manager that take care of this function
     *
     * @param id
     * @return
     * @throws DontHavePermissionException
     */
    public boolean removeMember(String id) throws DontHavePermissionException {
        if (connectedUser instanceof SystemManager) {
            SystemManager systemManager = (SystemManager) connectedUser;
            return systemManager.removeMember(id);
        } else {
            throw new DontHavePermissionException();
        }
    }

    /**
     * this function get the path to the complaint file and the id of the system manager that take care of this function
     *
     * @param path
     * @throws DontHavePermissionException
     */
    public void watchComplaint(String path) throws DontHavePermissionException {
        if (connectedUser instanceof SystemManager) {
            SystemManager systemManager = (SystemManager) connectedUser;
            LinkedList<String> complaint = systemManager.watchComplaint(path);
        } else {
            throw new DontHavePermissionException();
        }
    }

    /**
     * this function get the path to the complaint file , the response for the complaint and the id of the system manager that take care of this function
     *
     * @param path
     * @param responseForComplaint
     * @return
     * @throws DontHavePermissionException
     */
    public boolean responseComplaint(String path, LinkedList<Pair<String, String>> responseForComplaint) throws DontHavePermissionException {
        if (connectedUser instanceof SystemManager) {
            SystemManager systemManager = (SystemManager) connectedUser;
            return systemManager.ResponseComplaint(path, responseForComplaint);
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void schedulingGames(String seasonId, String leagueId) throws DontHavePermissionException, ObjectNotExist {

           if (connectedUser instanceof SystemManager) {
               SystemManager systemManager = (SystemManager) connectedUser;
               systemManager.schedulingGames(seasonId, leagueId);
           } else {
               throw new DontHavePermissionException();
           }


    }

    public void viewSystemInformation(String path) throws DontHavePermissionException {
        if (connectedUser instanceof SystemManager) {
            SystemManager systemManager = (SystemManager) connectedUser;
            systemManager.viewSystemInformation(path);
        } else {
            throw new DontHavePermissionException();
        }
    }

    /**
     * this function get the team name and all the team member and the id of the system manager that take care of this function
     * if the team name not exist - open the team and return true
     * if the team exist return false
     *
     * @param players
     * @param coachs
     * @param managers
     * @param owners
     * @param teamName
     * @return
     * @throws DontHavePermissionException
     */
    public boolean addTeam(LinkedList<String> players, LinkedList<String> coachs, LinkedList<String> managers, LinkedList<String> owners, String teamName) throws DontHavePermissionException {
        if (connectedUser instanceof SystemManager) {
            SystemManager systemManager = (SystemManager) connectedUser;
            return systemManager.addNewTeam(players, coachs, managers, owners, teamName);
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void addAssociationDelegate(String id) throws DontHavePermissionException, AlreadyExistException, MemberNotExist {
        if(connectedUser instanceof SystemManager){
            ((SystemManager)connectedUser).addAssociationDelegate(id);
            return;
        }
        throw new DontHavePermissionException();
    }

    public void addOwner(String id) throws DontHavePermissionException, AlreadyExistException, MemberNotExist {
        if(connectedUser instanceof SystemManager){
            ((SystemManager)connectedUser).addOwner(id);
            return;
        }
        throw new DontHavePermissionException();
    }

    /*************************************** function for owner******************************************/

    /**
     * owner:
     * add a coach
     *
     * @param teamName
     * @param mailId
     * @throws NoEnoughMoney
     */
    public void addCoach(String teamName, String mailId) throws ObjectNotExist, NoEnoughMoney, MemberNotExist, AlreadyExistException, DontHavePermissionException {
        if (!dbController.getRoles(this.connectedUser).containsKey(mailId))
            throw new MemberNotExist();
        if (!dbController.getTeams(this.connectedUser).containsKey(teamName))
            throw new ObjectNotExist("team not exist");
        if (dbController.getTeams(this.connectedUser).get(teamName).getAccount().getAmountOfTeam() < 0)
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
    public void addPlayer(String mailId, String teamName, int year, int month, int day, String rolePlayer) throws ObjectNotExist, IncorrectInputException, MemberNotExist, AlreadyExistException, DontHavePermissionException {
        if (!dbController.getRoles(this.connectedUser).containsKey(mailId))
            throw new MemberNotExist();
        if (!dbController.getTeams(this.connectedUser).containsKey(teamName))
            throw new ObjectNotExist("team not exist");
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
    public void addField(String teamName, String fieldName) throws DontHavePermissionException, ObjectNotExist, IncorrectInputException {
        if (!dbController.getTeams(this.connectedUser).containsKey(teamName))
            throw new ObjectNotExist("team not exist");
        if (!dbController.getTeams(this.connectedUser).get(teamName).getTrainingFields().contains(fieldName))
            throw new IncorrectInputException();
        if (!dbController.getTeams(this.connectedUser).get(teamName).getHomeField().equals(fieldName))
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
     * @throws ObjectNotExist
     */
    public void addManager(String teamName, String mailId) throws NoEnoughMoney, ObjectNotExist, MemberNotExist, AlreadyExistException, DontHavePermissionException {
        if (!dbController.getRoles(this.connectedUser).containsKey(mailId))
            throw new ObjectNotExist("");
        if (!dbController.getTeams(this.connectedUser).containsKey(teamName))
            throw new ObjectNotExist("team not exist");
        if (dbController.getTeams(this.connectedUser).get(teamName).getAccount().getAmountOfTeam() < 0)
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
    public void removeManager(String teamName, String mailToRemove) throws ObjectNotExist, MemberNotExist, AlreadyExistException, DontHavePermissionException {

        if (!dbController.getTeams(this.connectedUser).containsKey(teamName))
            throw new ObjectNotExist("team not exist ");
        if (!(dbController.getRoles(this.connectedUser).containsKey(connectedUser.getName())))
            throw new MemberNotExist();
        if (!dbController.getRoles(this.connectedUser).containsKey(mailToRemove))
            throw new ObjectNotExist("member not exist");

        ((Owner) connectedUser).removeManager(teamName, mailToRemove);

    }

    /**
     * owner:
     * remove coach
     *
     * @param teamName
     * @param mailToRemove
     * @throws MemberNotExist
     */
    public void removeCoach(String teamName, String mailToRemove) throws ObjectNotExist, MemberNotExist, AlreadyExistException, DontHavePermissionException {
        if (!dbController.getTeams(this.connectedUser).containsKey(teamName))
            throw new ObjectNotExist("team not exist");
        if (!(dbController.getRoles(this.connectedUser).containsKey(connectedUser.getName())))
            throw new MemberNotExist();
        if (!dbController.getRoles(this.connectedUser).containsKey(mailToRemove))
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
    public void removePlayer(String teamName, String mailToRemove) throws DontHavePermissionException, ObjectNotExist, MemberNotExist, AlreadyExistException {
        if (!dbController.getTeams(this.connectedUser).containsKey(teamName))
            throw new ObjectNotExist("team not exist");
        if (!(dbController.getRoles(this.connectedUser).containsKey(connectedUser.getName())))
            throw new MemberNotExist();
        if (!dbController.getRoles(this.connectedUser).containsKey(mailToRemove))
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
    public void removeField(String teamName, String fieldName) throws ObjectNotExist, DontHavePermissionException, MemberNotExist, IncorrectInputException {
        if (!dbController.getTeams(this.connectedUser).containsKey(teamName))
            throw new ObjectNotExist("team not exist");
        if (!(dbController.getRoles(this.connectedUser).containsKey(connectedUser.getName())))
            throw new MemberNotExist();
        if (!dbController.getTeams(this.connectedUser).containsKey(teamName))
            throw new ObjectNotExist("");
        if (!dbController.getTeams(this.connectedUser).get(teamName).getTrainingFields().contains(fieldName))
            throw new IncorrectInputException();
        if (!dbController.getTeams(this.connectedUser).get(teamName).getHomeField().equals(fieldName))
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
     * @throws ObjectNotExist
     * @throws MemberNotExist
     */
    public void addNewOwner(String teamName, String mailId) throws NoEnoughMoney, ObjectNotExist, MemberNotExist, AlreadyExistException, DontHavePermissionException {
        if (!dbController.getRoles(this.connectedUser).containsKey(mailId))
            throw new MemberNotExist();
        if (!dbController.getTeams(this.connectedUser).containsKey(teamName))
            throw new ObjectNotExist("");
        if (dbController.getTeams(this.connectedUser).get(teamName).getAccount().getAmountOfTeam() < 0)
            throw new NoEnoughMoney();

        ((Owner) connectedUser).addNewOwner(teamName, mailId);
    }

    /**
     * owner:
     * close team temporary
     *
     * @param teamName
     * @throws ObjectNotExist
     */
    public void temporaryTeamClosing(String teamName) throws ObjectNotExist, DontHavePermissionException {

        if (!dbController.getTeams(this.connectedUser).containsKey(teamName))
            throw new ObjectNotExist("team not exist");
        if (!((Owner) connectedUser).getTeams().containsKey(teamName))
            throw new ObjectNotExist("team not exist");
        if (!((Owner) connectedUser).getTeams().get(teamName).getStatus())
            throw new DontHavePermissionException();

        ((Owner) connectedUser).temporaryTeamClosing(teamName);
    }

    /**
     * owner:
     * reopen team
     *
     * @param teamName
     * @throws ObjectNotExist
     */
    public void reopenClosedTeam(String teamName) throws ObjectNotExist, DontHavePermissionException {
        if (!dbController.getTeams(this.connectedUser).containsKey(teamName))
            throw new ObjectNotExist("");
        if (!((Owner) connectedUser).getTeams().containsKey(teamName))
            throw new ObjectNotExist("");
        if (!((Owner) connectedUser).getTeams().get(teamName).getStatus())
            throw new DontHavePermissionException();

        ((Owner) connectedUser).reopenClosedTeam(teamName);
    }

    /**
     * owner:
     * add outcome of team
     *
     * @param teamName
     * @throws NoEnoughMoney
     * @throws ObjectNotExist
     */
    public void addOutCome(String teamName, String description, double amount) throws NoEnoughMoney, ObjectNotExist, AccountNotExist, IncorrectInputException, DontHavePermissionException {
        if (description == null || amount < 0)
            throw new IncorrectInputException();
        if (!dbController.getTeams(this.connectedUser).containsKey(teamName))
            throw new ObjectNotExist("");
        if (dbController.getTeams(this.connectedUser).get(teamName).getAccount().getAmountOfTeam() < 0)
            throw new NoEnoughMoney();
        if (dbController.getTeams(this.connectedUser).get(teamName).getAccount().getAmountOfTeam() - amount < 0)
            throw new NoEnoughMoney();
        if (dbController.getTeams(this.connectedUser).get(teamName).getAccount() == null)
            throw new AccountNotExist();

        ((Owner) connectedUser).addOutCome(teamName, description, amount);
    }

    /**
     * owner:
     * add outcome of team
     *
     * @param teamName
     * @throws NoEnoughMoney
     * @throws ObjectNotExist
     */
    public void addInCome(String teamName, String description, double amount) throws NoEnoughMoney, ObjectNotExist, AccountNotExist, IncorrectInputException, DontHavePermissionException {
        if (description == null || amount < 0)
            throw new IncorrectInputException();
        if (!dbController.getTeams(this.connectedUser).containsKey(teamName))
            throw new ObjectNotExist("");
        if (dbController.getTeams(this.connectedUser).get(teamName).getAccount().getAmountOfTeam() < 0)
            throw new NoEnoughMoney();
        if (dbController.getTeams(this.connectedUser).get(teamName).getAccount() == null)
            throw new AccountNotExist();

        ((Owner) connectedUser).addInCome(teamName, description, amount);
    }


    /********Getters for Owner & System manager********/
    public HashMap<String, Role> getRoles() throws DontHavePermissionException {
        if (connectedUser instanceof Owner) {
            return ((Owner) connectedUser).getRoles();
        }
        if (connectedUser instanceof SystemManager) {
            return ((SystemManager) connectedUser).getRoles();
        }
        throw new DontHavePermissionException();
    }

    public HashMap<String, Team> getTeams() {
        try {
            if (connectedUser instanceof Owner) {
                return ((Owner) connectedUser).getTeams();
            }
            if (connectedUser instanceof SystemManager) {
                return ((SystemManager) connectedUser).getTeams();
            }
            throw new DontHavePermissionException();
        }
        catch (Exception e)
        {

        }
        return new HashMap<>();
    }

    public void setMoneyToAccount(String teamName , double amount) throws ObjectNotExist, DontHavePermissionException {
        if(! (connectedUser instanceof Owner) )
            throw new DontHavePermissionException();
        ((Owner)connectedUser).setMoneyToAccount(teamName,amount);
    }

    public double getAccountBalance(String teamName) throws DontHavePermissionException, ObjectNotExist {
        if(! (connectedUser instanceof Owner) )
            throw new DontHavePermissionException();
        return ((Owner)connectedUser).getAccountBalance(teamName);
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

    public void setLeagueByYear(String specificLeague, String year) throws ObjectNotExist, AlreadyExistException, DontHavePermissionException {
        try {
            ((AssociationDelegate) connectedUser).setLeagueByYear(specificLeague, year);
        } catch (ObjectNotExist incorrectInput) {
            throw new ObjectNotExist(incorrectInput.getMessage());
        } catch (AlreadyExistException alreadyExist) {
            throw new AlreadyExistException();
        }
    }

    public HashMap<String, League> getLeagues() {
        HashMap<String, League> leagues=new HashMap<String, League>();
        try{
            leagues = dbController.getLeagues(this.connectedUser);
            throw new DontHavePermissionException();
        }
        catch(Exception e){
        }
        return leagues;
    }

    public HashMap<String, Referee> getRefereesDoesntExistInTheLeagueAndSeason(String league, String season) throws DontHavePermissionException {
        HashMap<String, Referee> referees = new HashMap<>();
        try {
            referees = ((AssociationDelegate) connectedUser).getRefereesDoesntExistInTheLeagueAndSeason(league, season);

        } catch (Exception e) {
            throw new DontHavePermissionException();
        }
        return referees;
    }

    public void addRefereeToLeagueInSeason(String league, String season, String refereeToAdd) {
        try{
            ((AssociationDelegate)connectedUser).addRefereeToLeagueInSeason(league, season, refereeToAdd);
        }
        catch(Exception e){

        }

    }

    public HashMap<String, Season> getSeasons() {
        HashMap<String, Season> seasons=new HashMap<String, Season>();
        try{
            seasons = dbController.getSeasons(this.connectedUser);
            throw new DontHavePermissionException();
        }catch(Exception e){

        }
        return seasons;
    }

    public void changeScorePolicy(String league, String season, String sWinning, String sDraw, String sLosing) throws ObjectNotExist, IncorrectInputException {
        ((AssociationDelegate)connectedUser).changeScorePolicy(league, season, sWinning, sDraw, sLosing);
    }

    public HashMap<String, ISchedulingPolicy> getSchedulingPolicies() throws DontHavePermissionException{
       return ((AssociationDelegate)connectedUser).getSchedulingPolicies();
    }

    public void addSchedulingPolicy(String policyName) throws IncorrectInputException, DontHavePermissionException {
        ((AssociationDelegate)connectedUser).addSchedulingPolicy(policyName);
    }
    /*************************************** function for Referee ******************************************/
    public void updateDetails(String id ,String newName, String newMail,String newPassword, String newTraining){
        //todo
    }
    public void updateGameEvent(){
        //todo
    }
    public HashMap<String,String> getGameSchedule(String refereeId){
        //todo - return a list of date, gameDetails
        return null;
    }
    public void getGameReport(){
        //todo
    }
    /*************************************************************************************************************/

    public HashMap<String, Fan> getFans() {
        return dbController.getFans();
    }

    public HashMap<String, Referee> getReferees() {
        return dbController.getReferees();
    }

    public HashMap<String, Player> getPlayers() {
        return dbController.getPlayers();
    }

    public HashMap<String, Owner> getOwners() {
        return dbController.getOwners();
    }

    public HashMap<String, Manager> getManagers() {
        return dbController.getManagers();
    }

    public HashMap<String, Coach> getCoach() {
        return dbController.getCoaches();
    }

    public HashMap<String,Member> getMembers() {
        return dbController.getMembers();
    }


    /**********shachar test*************/
    public void addPlayer(Player player1) throws AlreadyExistException {
        dbController.addPlayer(player1);
    }

    public void addCoach(Coach coach1) throws AlreadyExistException {
        dbController.addCoach(coach1);
    }

    public void addManager(Manager manager1) throws AlreadyExistException {
        dbController.addManager(manager1);
    }

    public void addOwner(Owner owner1) throws AlreadyExistException, DontHavePermissionException {
        dbController.addOwner(connectedUser,owner1);
    }

    public void addSystemManager(SystemManager systemManager) throws AlreadyExistException {
        dbController.addSystemManager(systemManager);
    }

    public void addFan(Fan fan1) {
        dbController.addFan(fan1);
    }

    public void setSchedulingPolicyToLeagueInSeason(String specificLeague, String year, String policy) throws IncorrectInputException, ObjectNotExist {
        ((AssociationDelegate)connectedUser).setSchedulingPolicyToLeagueInSeason(specificLeague, year, policy);
    }
}

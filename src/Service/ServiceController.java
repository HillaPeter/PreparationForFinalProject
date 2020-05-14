package Service;

import DataBase.DBController;
import Domain.Asset.Coach;
import Domain.Asset.Field;
import Domain.Asset.Manager;
import Domain.Asset.Player;
import Domain.Game.Team;
import Domain.Users.*;
import Exception.*;
import Presentation.*;

import javax.swing.*;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * singleton
 */
public class ServiceController {
    private static final ServiceController instance = new ServiceController();
    SystemController systemController ;
    public static ServiceController getInstance() {
        return instance;
    }
    private ServiceController(){
        this.systemController = new SystemController("DomainController");
    }

    public String login(String id, String pass) throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException {
        String type = systemController.login(id,pass);
        return type;
    }
    public void signIn(String userName, String userMail, String password , Date birthDate) throws IncorrectInputException, DontHavePermissionException, AlreadyExistException {
        systemController.signIn(userName, userMail,  password , birthDate);
    }
    public void logOut(){
        systemController.logOut();
    }
    public LinkedList<String> removeSystemManagerComboBox() throws DontHavePermissionException {
        HashMap<String, SystemManager> systemmanagers = systemController.getSystemManager();
        LinkedList<String> linkedList = new LinkedList<>(systemmanagers.keySet());
        return linkedList;
    }

    public LinkedList<String> addSystemManagerComboBox() throws DontHavePermissionException {
        HashMap<String, Fan> fans = systemController.getFans();
        LinkedList<String> linkedList = new LinkedList<>(fans.keySet());
        return linkedList;
    }

    public LinkedList<String> addAssociationDeligateComboBox() throws DontHavePermissionException {
        HashMap<String, Fan> fans = systemController.getFans();
        LinkedList<String> linkedList = new LinkedList<>(fans.keySet());
        return linkedList;
    }

    public LinkedList<String> removeAssociationDeligateComboBox() throws DontHavePermissionException {
        HashMap<String, AssociationDelegate> associationDelegate = systemController.getAssociationDelegates();
        LinkedList<String> linkedList = new LinkedList<>(associationDelegate.keySet());
        return linkedList;
    }

    public LinkedList<String> addRefereeComboBox() throws DontHavePermissionException {
        HashMap<String, Fan> fans = systemController.getFans();
        LinkedList<String> linkedList = new LinkedList<>(fans.keySet());
        return linkedList;
    }

    public LinkedList<String> removeRefereeComboBox() {
        HashMap<String, Referee> referees = systemController.getReferees();
        LinkedList<String> linkedList = new LinkedList<>(referees.keySet());
        return linkedList;
    }

    public LinkedList<String> removeMemberComboBox() throws DontHavePermissionException {
        HashMap<String, Member> members = systemController.getMembers();
        LinkedList<String> linkedList = new LinkedList<>(members.keySet());
        return linkedList;
    }


    public LinkedList<String> removeTeamComboBox() {
        HashMap<String, Team> team = systemController.getTeams();
        LinkedList<String> linkedList = new LinkedList<>(team.keySet());
        return linkedList;
    }

    public LinkedList<String> addTeamComboBox() throws DontHavePermissionException {
        HashMap<String, Role> owners = systemController.getOwnersAndFans();
        LinkedList<String> linkedList = new LinkedList<>(owners.keySet());
        return linkedList;
    }

    public boolean matchPass(char[] pass1, char[] pass2) {
        if(pass1.length == pass2.length){
            for(int i=0; i< pass1.length ; i++){
                if(pass1[i] != pass2[i])
                    return false;
            }
            return true;
        }
        return false;
    }

    public String getUserName() {
        return this.systemController.getConnectedUser().getName();
    }



    /*******************************Owner************************************/

    public LinkedList<String> getTeams() {
        HashMap<String, Team> teams=systemController.getTeams();
        LinkedList<String> linkedList = new LinkedList<>(teams.keySet());
        return linkedList;
    }

    public LinkedList<String> addFanIntoComboBox() throws DontHavePermissionException {
        HashMap<String, Fan> fans = systemController.getFans();
        LinkedList<String> linkedList = new LinkedList<>(fans.keySet());
        return linkedList;
    }

    public void addTeamManager(String teamName,String id) throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException {
        systemController.addManager(teamName,id);
    }

    public void addCoach(String teamName,String id) throws DontHavePermissionException, ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException {
        systemController.addCoach(teamName,id);
    }

    public void addPlayer(String teamName,String id,int year, int month, int day, String role) throws DontHavePermissionException, ObjectNotExist, AlreadyExistException, MemberNotExist, NoEnoughMoney, IncorrectInputException {
       systemController.addPlayer(id,teamName,year,month,day,role);
    }

    public void addField(String teamName, String field) throws DontHavePermissionException, IncorrectInputException, ObjectNotExist, ObjectAlreadyExist, NoEnoughMoney, AlreadyExistException {
        systemController.addField(teamName,field);
    }


    public LinkedList<String> getManagers(String teamName) throws DontHavePermissionException, ObjectNotExist {
        Team team=this.systemController.getTeamByName(teamName);
        LinkedList<String> linkedList = new LinkedList<>();
        for(Manager m: team.getManagers()){
            linkedList.add(m.getName());
        }
        return linkedList;
    }

    public LinkedList<String> getCoaches(String teamName) throws DontHavePermissionException, ObjectNotExist {
        Team team=this.systemController.getTeamByName(teamName);
        LinkedList<String> linkedList = new LinkedList<>();
        for(Coach c: team.getCoaches()){
            linkedList.add(c.getName());
        }
        return linkedList;
    }

    public LinkedList<String> getPlayers(String teamName) throws DontHavePermissionException, ObjectNotExist {
        Team team=this.systemController.getTeamByName(teamName);
        LinkedList<String> linkedList = new LinkedList<>();
        for(Player p: team.getPlayers()){
            linkedList.add(p.getName());
        }
        return linkedList;
    }

    public LinkedList<String> getFields(String teamName) throws DontHavePermissionException, ObjectNotExist {
        Team team=this.systemController.getTeamByName(teamName);
        LinkedList<String> linkedList = new LinkedList<>();
        for(Field f: team.getTrainingFields()){
            linkedList.add(f.getNameOfField());
        }
        return linkedList;
    }

    public void removeTeamManager(String teamName,String id) throws ObjectNotExist, MemberNotExist, AlreadyExistException, DontHavePermissionException {
        systemController.removeManager(teamName,id);
    }

    public void removeCoach(String teamName,String id) throws ObjectNotExist, MemberNotExist, AlreadyExistException, DontHavePermissionException {
        systemController.removeCoach(teamName,id);
    }

    public void removePlayer(String teamName,String id) throws ObjectNotExist, MemberNotExist, AlreadyExistException, DontHavePermissionException {
        systemController.removePlayer(teamName,id);
    }

    public void removeField(String teamName,String fieldName) throws ObjectNotExist, MemberNotExist, AlreadyExistException, DontHavePermissionException, IncorrectInputException {
        systemController.removeField(teamName,fieldName);
    }

    public void updatePlayerRole(String teamName,String mailId, String role) throws DontHavePermissionException, IncorrectInputException, ObjectNotExist, AccountNotExist, NoEnoughMoney, AlreadyExistException, MemberNotExist {
        systemController.updatePlayerRole(teamName,mailId,role);
    }

    public void updateHomeField(String teamName, String field) throws ObjectNotExist, DontHavePermissionException, AlreadyExistException {
        systemController.updateHomeField(teamName,field);
    }

    public void addIncome(String teamName,String desc,double amount) throws ObjectNotExist, AccountNotExist, DontHavePermissionException, NoEnoughMoney, IncorrectInputException {
        systemController.addInCome(teamName,desc,amount);
    }

    public void addOutcome(String teamName,String desc,double amount) throws ObjectNotExist, AccountNotExist, DontHavePermissionException, NoEnoughMoney, IncorrectInputException {
        systemController.addOutCome(teamName,desc,amount);
    }

    public void addNewOwner(String teamName,String id) throws ObjectNotExist, MemberNotExist, NoEnoughMoney, AlreadyExistException, DontHavePermissionException {
        systemController.addNewOwner(teamName,id);
    }

    public void temporaryClosingTeam(String teamName) throws DontHavePermissionException, ObjectNotExist {
        systemController.temporaryTeamClosing(teamName);
    }

    public void reopenClosedTeam(String teamName) throws DontHavePermissionException, ObjectNotExist {
        systemController.reopenClosedTeam(teamName);
    }

}

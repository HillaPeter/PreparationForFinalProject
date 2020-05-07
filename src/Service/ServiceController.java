package Service;

import Domain.Game.Team;
import Domain.Users.*;
import Exception.*;
import Presentation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class ServiceController {

    SystemController systemController = new SystemController("DomainController");


    public Menu login(String id, String pass) throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException {
        ///  Member member = systemController.logIn(id,pass);

        // String type = systemController.logIn(id,pass);

        /*if(type.equals ( "Owner" ) )
               return newOwnerPre
        */
        return null;
    }

    // public RoleMenu login(){
    //   switch member to RoleMenu
    //}


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
}

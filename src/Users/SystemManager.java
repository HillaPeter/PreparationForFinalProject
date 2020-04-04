package Users;

import Asset.Coach;
import Asset.Manager;
import Asset.Player;
import Game.Account;
import Game.Team;
import System.System;

import java.util.LinkedList;

public class SystemManager extends Member {

    private System system;

    public SystemManager(String name, int userId, String password, System system) {
        super(name, userId, password);
        this.system = system;
    }

    //this function return true if the team added and false if there were problem with the data
    public boolean addNewTeam(LinkedList<Integer> idPlayers, LinkedList<Integer> idCoach, LinkedList<Integer> idManager, LinkedList<Integer> idOwner, String teamName) {

        if (idPlayers.size() < 11 || alreadyIncludeThisTeamName(teamName) == true || notAllTheIdAreMembers(idPlayers, idCoach, idManager, idOwner) == true) {
            return false;
        } else {
            LinkedList<Coach> coaches = makeCoachList(system.returnFromSystemTheExactUsers(idCoach));
            LinkedList<Player> players = makePlayerList(system.returnFromSystemTheExactUsers(idPlayers));
            LinkedList<Manager> managers = makeManagerList(system.returnFromSystemTheExactUsers(idManager));
            LinkedList<Owner> owners = makeOwnerList(system.returnFromSystemTheExactUsers(idOwner));
            Account account = new Account();
            Team newTeam = new Team(account, players, coaches, managers, owners, teamName);
            system.addTeam(newTeam);
            return true;
        }
    }

    //from here help function for add new team
    private LinkedList<Coach> makeCoachList(LinkedList<Member> returnFromSystemTheExactUsers) {
        LinkedList<Coach> newList = new LinkedList<>();
        for (int i = 0; i < returnFromSystemTheExactUsers.size(); i++) {
            newList.add((Coach) returnFromSystemTheExactUsers.get(i));
        }
        return newList;
    }

    private LinkedList<Player> makePlayerList(LinkedList<Member> returnFromSystemTheExactUsers) {
        LinkedList<Player> newList = new LinkedList<>();
        for (int i = 0; i < returnFromSystemTheExactUsers.size(); i++) {
            newList.add((Player) returnFromSystemTheExactUsers.get(i));
        }
        return newList;
    }

    private LinkedList<Owner> makeOwnerList(LinkedList<Member> returnFromSystemTheExactUsers) {
        LinkedList<Owner> newList = new LinkedList<>();
        for (int i = 0; i < returnFromSystemTheExactUsers.size(); i++) {
            newList.add((Owner) returnFromSystemTheExactUsers.get(i));
        }
        return newList;
    }

    private LinkedList<Manager> makeManagerList(LinkedList<Member> returnFromSystemTheExactUsers) {
        LinkedList<Manager> newList = new LinkedList<>();
        for (int i = 0; i < returnFromSystemTheExactUsers.size(); i++) {
            newList.add((Manager) returnFromSystemTheExactUsers.get(i));
        }
        return newList;
    }

    private boolean notAllTheIdAreMembers(LinkedList<Integer> idPlayers, LinkedList<Integer> idCoach, LinkedList<Integer> idManager, LinkedList<Integer> idOwner) {
        return system.notAllTheIdAreMembers(idPlayers, idCoach, idManager, idOwner);

    }

    private boolean alreadyIncludeThisTeamName(String teamName) {

        return system.alreadyIncludeThisTeamName(teamName);
    }
    //until here

    public void schedulingGames() {
        //todo
    }

    public void initSystem(String userName, String password) {

    }

    public void removeReferee() {
        //todo
    }

    public void addReferee() {
        //todo
    }

    public boolean closeTeam(String teamName) {
        //the team not exist
        if (system.existTeamName(teamName) == false)
            return false;
        else {//i can delete it
            //המערכת מסירה את הקבוצה מכל שיבוצי המשחק שיש לה
            Team team=system.getTeam(teamName);
            team.deleteTheData();
            system.deleteTeam(teamName);
            return true;
        }
    }

    public void removeMember() {
        //todo
    }

    public void watchAndResponseComplaint() {
        //todo
    }

    public void viewSystemInformation() {
        //todo
    }
}

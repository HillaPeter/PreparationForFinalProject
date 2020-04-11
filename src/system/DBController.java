package system;

import Asset.Coach;
import Asset.Manager;
import Asset.Player;
import Game.Team;
import League.*;
import Users.*;
import Exception.*;

import java.util.HashMap;

public class DBController {

    private DB db;

    public DBController() {
        this.db = new DB();
    }


    /***************************************Guest function******************************************/
    /**
     * this function check if the guest is the one who call the function. and then
     * @param guest
     * @param fan
     * @throws AlreadyExistException
     */
    public void addFan(Role guest , Fan fan) throws AlreadyExistException {db.addFan(fan);}

    public boolean existMember(String id) {
        return db.existMember(id);
    }
    public Role getMember(String id) throws MemberNotExist {
        return db.getMember(id) ;
    }


    /***************************************Getters******************************************/
    public HashMap<String, Role> getRoles() {
        return db.getRoles();
    }

    public HashMap<String, Team> getTeams() {
        return db.getTeams();
    }

    public HashMap<String, League> getLeagues() {
        return db.getLeagues();
    }

    /***************************************delete function function******************************************/

    public void deleteRole(String id) {
        db.removeRole(id);
    }

    /***************************************add function******************************************/
    public void addSeason(Season season) {
        db.addSeason(season);
    }

    public void addLeague(League league) {
        db.addLeague(league);
    }

    public void addManager(Manager manager) {
        db.addManager(manager);
    }

    public void addPlayer(Player player) {
        db.addPlayer(player);
    }

    public void addCoach(Coach coach) {
        db.addCoach(coach);
    }

    public void addOwner(Owner owner) {
        db.addOwner(owner);
    }

    public void addSystemManager(SystemManager systemManager) {
        db.addSystemManager(systemManager);
    }

    public void addTeam(Team team) {
        db.addTeam(team);
    }

    /***************************************remove function******************************************/
    public void removeLeague(String leagueName) {
        db.removeLeague(leagueName);
    }

    public void removeSeason(String year) {
        db.removeSeason(year);
    }

    public void removeTeam(String name) {
        db.removeTeam(name);
    }

    /***************************************exist function******************************************/


}

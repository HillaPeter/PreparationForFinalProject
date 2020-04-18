package system;

import Asset.Coach;
import Asset.Manager;
import Asset.Player;
import Game.Team;
import League.*;
import Users.*;
import Exception.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class DBController {

    private DB db;

    public DBController() {
        this.db = new DB();
        SystemManager systemManager=new SystemManager("admin" , "admin@gmail.com","123",this);
        db.addSystemManager(systemManager);
    }


    /***************************************Guest function******************************************/


    /***************************************Getters******************************************/
    public HashMap<String, Role> getRoles(Role role) throws DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner)
            return db.getRoles();
        throw new DontHavePermissionException();
    }

    public HashMap<String, Team> getTeams(Role role) throws DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner)
            return db.getTeams();
        throw new DontHavePermissionException();
    }

    public HashMap<String, League> getLeagues(Role role) throws DontHavePermissionException {
        if (role instanceof AssociationDelegate || role instanceof SystemManager)
            return db.getLeagues();
        throw new DontHavePermissionException();
    }

    public Role getMember(String id) throws MemberNotExist{
        if (!db.existMember(id))
            throw new MemberNotExist();
        return db.getMember(id);
    }
    public Team getTeam(String teamName) {
        return db.getTeam(teamName);
    }
    public League getLeague(String leagueId) throws ObjectNotExist {

        return db.getLeague(leagueId);
    }
    public Season getSeason(String seasonId) throws ObjectNotExist {
        return db.getSeason(seasonId);
    }
    public LinkedList<Member> getMembers(LinkedList<String> idMember) {
        LinkedList<Member> list=new LinkedList<>();
        for(int i=0; i<idMember.size(); i++)
        {
            Member member=db.getMember(idMember.get(i));
            list.add(member);
        }
        return list;
    }


    public HashMap<String, Referee> getReferees(Role role) throws DontHavePermissionException {
            if (role instanceof AssociationDelegate || role instanceof SystemManager)
                return db.getReferees();
            throw new DontHavePermissionException();
    }

    public HashMap<String, Fan> getFans() {
        return db.getFans();
    }

    public HashMap<String, Referee> getReferees() {
        return db.getReferees();
    }

    public HashMap<String, Player> getPlayers() {
        return db.getPlayers();
    }

    public HashMap<String, Owner> getOwners() {
        return db.getOwners();
    }

    public HashMap<String, Manager> getManagers() {
        return db.getManagers();
    }

    public HashMap<String, Coach> getCoaches() {
        return db.getCoaches();
    }

    public HashMap<String,Member> getMembers() {
        return db.getMembers();
    }

    public HashMap<String, ISchedulingPolicy> getSchedulingPolicies(Role role) throws DontHavePermissionException {
        if(role instanceof AssociationDelegate || role instanceof Owner || role instanceof SystemManager ){
            return db.getSchedulingPolicies();
        }
        else{
            throw new DontHavePermissionException();
        }
    }

    /***************************************delete function function******************************************/

    public void deleteRole(Role role, String id) throws MemberNotExist, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner || role instanceof AssociationDelegate) {
            if (!db.existMember(id))
                throw new MemberNotExist();
            db.removeRole(id);
        }
        throw new DontHavePermissionException();
    }

    public void deleteReferee(String id) {
        db.removeRole(id);
    }

    public void deleteFan(String id) {
        db.removeRole(id);
    }
    public void deleteMember(String id) {
        db.removeRole(id);
    }
    /***************************************add function******************************************/
    public void addFan(Role role, Fan fan) throws AlreadyExistException, DontHavePermissionException {
        if (!(role instanceof Guest)) {
            throw new DontHavePermissionException();
        }
        if (db.existMember(fan.getUserMail()))
            throw new AlreadyExistException();
        db.addFan(fan);
    }

    public void addSeason(Season season) throws AlreadyExistException {
        if (db.existSeason(season.getYear()))
            throw new AlreadyExistException();
        db.addSeason(season);
    }

    public void addLeague(League league) throws AlreadyExistException {
        if (db.existLeague(league.getName()))
            throw new AlreadyExistException();
        db.addLeague(league);
    }

    public void addManager(Manager manager) throws AlreadyExistException {
        if (db.existMember(manager.getUserMail()))
            throw new AlreadyExistException();
        db.addManager(manager);
    }

    public void addPlayer(Player player) throws AlreadyExistException {
        if (db.existMember(player.getUserMail()))
            throw new AlreadyExistException();
        db.addPlayer(player);
    }

    public void addCoach(Coach coach) throws AlreadyExistException {
        if (db.existMember(coach.getUserMail()))
            throw new AlreadyExistException();
        db.addCoach(coach);
    }

    public void addOwner(Owner owner) throws AlreadyExistException {
        if (db.existMember(owner.getUserMail()))
            throw new AlreadyExistException();
        db.addOwner(owner);
    }

    public void addSystemManager(SystemManager systemManager) throws AlreadyExistException {
        if (db.existMember(systemManager.getUserMail()))
            throw new AlreadyExistException();
        db.addSystemManager(systemManager);
    }

    public void addTeam(Team team) throws AlreadyExistException {
        if (db.existTeam(team.getName()))
            throw new AlreadyExistException();
        db.addTeam(team);
    }

    public void addReferee(Referee referee) {
        db.addReferee(referee);
    }

    public void addFan(Fan fan)
    {
        db.addFan(fan);
    }

    public void addSchedulingPolicies(Role role, ISchedulingPolicy policy) throws DontHavePermissionException {
        if(role instanceof AssociationDelegate || role instanceof Owner || role instanceof SystemManager ){
             db.addSchedulingPolicies(policy);
        }
        else{
            throw new DontHavePermissionException();
        }
    }
    /***************************************remove function******************************************/
    public void removeLeague(String leagueName) throws ObjectNotExist {
        if (!db.existLeague(leagueName))
            throw new ObjectNotExist("");
        db.removeLeague(leagueName);
    }

    public void removeSeason(String year) throws ObjectNotExist {
        if (!db.existSeason(year))
            throw new ObjectNotExist("");
        db.removeSeason(year);
    }

    public void removeTeam(String name) throws ObjectNotExist {
        if (!db.existTeam(name))
            throw new ObjectNotExist("");
        db.removeTeam(name);
    }

    /********************************************exist function***********************************/
    public boolean existReferee(String refereeId) {
        return db.existRefree(refereeId);
    }


    public boolean existFan(String fanId) {
        return db.existFan(fanId);
    }


    public boolean existTeam(String teamName) {
        return db.existTeam(teamName);
    }


    public boolean existMember(String id) {
        return db.existMember(id);
    }


    public HashMap<String,Season> getSeasons(Role role) {
        return db.getSeasons();
    }
}

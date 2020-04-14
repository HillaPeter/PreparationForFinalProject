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


    /***************************************Getters******************************************/
    public HashMap<String, Role> getRoles(Role role) throws DontHavePermissionException{
        if(role instanceof SystemManager)
            return db.getRoles();
        throw new DontHavePermissionException();
    }

    public HashMap<String, Team> getTeams(Role role)throws DontHavePermissionException {
        if(role instanceof SystemManager)
            return db.getTeams();
        throw new DontHavePermissionException();
    }

    public HashMap<String, League> getLeagues(Role role)throws DontHavePermissionException {
        return db.getLeagues();
    }

    public Role getMember(String id) throws MemberNotExist {
        if(! db.existMember(id))
            throw new MemberNotExist();
        return db.getMember(id) ;
    }
    /***************************************delete function function******************************************/

    public void deleteRole(Role role, String id) throws MemberNotExist , DontHavePermissionException{
        if(role instanceof SystemManager || role instanceof Owner || role instanceof AssociationDelegate){
            if(! db.existMember(id))
                throw new MemberNotExist();
            db.removeRole(id);
        }
        throw new DontHavePermissionException();
    }

    /***************************************add function******************************************/
    public void addFan(Role role, Fan fan) throws AlreadyExistException, DontHavePermissionException {
        if(! (role instanceof Guest) ){
            throw new DontHavePermissionException();
        }
        if(db.existMember(fan.getUserMail()))
            throw new AlreadyExistException();
        db.addFan(fan);
    }

    public void addSeason(Season season) throws AlreadyExistException{
        if(db.existSeason(season.getYear()))
            throw new AlreadyExistException();
        db.addSeason(season);
    }

    public void addLeague(League league) throws AlreadyExistException{
        if(db.existLeague(league.getName()))
            throw new AlreadyExistException();
        db.addLeague(league);
    }

    public void addManager(Manager manager) throws AlreadyExistException {
        if(db.existMember(manager.getUserMail()))
            throw new AlreadyExistException();
        db.addManager(manager);
    }

    public void addPlayer(Player player) throws AlreadyExistException {
        if(db.existMember(player.getUserMail()))
            throw new AlreadyExistException();
        db.addPlayer(player);
    }

    public void addCoach(Coach coach) throws AlreadyExistException {
        if(db.existMember(coach.getUserMail()))
            throw new AlreadyExistException();
        db.addCoach(coach);
    }

    public void addOwner(Owner owner) throws AlreadyExistException {
        if(db.existMember(owner.getUserMail()))
            throw new AlreadyExistException();
        db.addOwner(owner);
    }

    public void addSystemManager(SystemManager systemManager) throws AlreadyExistException {
        if(db.existMember(systemManager.getUserMail()))
            throw new AlreadyExistException();
        db.addSystemManager(systemManager);
    }

    public void addTeam(Team team) throws AlreadyExistException{
        if(db.existTeam(team.getName()))
            throw new AlreadyExistException();
        db.addTeam(team);
    }

    /***************************************remove function******************************************/
    public void removeLeague(String leagueName) throws ObjectNotExist{
        if(! db.existLeague(leagueName))
            throw new ObjectNotExist("");
        db.removeLeague(leagueName);
    }

    public void removeSeason(String year)  throws ObjectNotExist{
        if(! db.existSeason(year))
            throw new ObjectNotExist("");
        db.removeSeason(year);
    }

    public void removeTeam(String name) throws ObjectNotExist{
        if(! db.existTeam(name))
            throw new ObjectNotExist("");
        db.removeTeam(name);
    }
}

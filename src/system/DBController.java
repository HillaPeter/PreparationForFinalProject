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
    public HashMap<String, Role> getRoles() {
        return db.getRoles();
    }

    public HashMap<String, Team> getTeams() {
        return db.getTeams();
    }

    public HashMap<String, League> getLeagues() {
        return db.getLeagues();
    }

    public Role getMember(String id) throws MemberNotExist {
        if(! db.existMember(id))
            throw new MemberNotExist();
        return db.getMember(id) ;
    }
    /***************************************delete function function******************************************/

    public void deleteRole(String id) throws MemberNotExist{
        if(! db.existMember(id))
            throw new MemberNotExist();
        db.removeRole(id);
    }

    /***************************************add function******************************************/
    public void addFan(Fan fan) throws AlreadyExistException {
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
    public void removeLeague(String leagueName) throws LeagueNotExist{
        if(! db.existLeague(leagueName))
            throw new LeagueNotExist();
        db.removeLeague(leagueName);
    }

    public void removeSeason(String year)  throws SeasonNotExist{
        if(! db.existSeason(year))
            throw new SeasonNotExist();
        db.removeSeason(year);
    }

    public void removeTeam(String name) throws TeamNotExist{
        if(! db.existTeam(name))
            throw new TeamNotExist();
        db.removeTeam(name);
    }
}

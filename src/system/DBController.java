package system;

import Asset.Coach;
import Asset.Manager;
import Asset.Player;
import Game.Team;
import League.*;
import Users.*;
import Exception.*;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class DBController {

    private DB db;

    public DBController() {
        this.db = new DB();
        if (!this.db.existSystemManager("admin@gmail.com")) {
            SystemManager systemManager = new SystemManager("admin", "admin@gmail.com", "123", this , new Date(1995,2,15));
            db.addSystemManager(systemManager);
            db.addSystemManager(systemManager);
        }
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

    public HashMap<String, Referee> getReferees(Role role) throws DontHavePermissionException {
        if (role instanceof AssociationDelegate || role instanceof SystemManager)
            return db.getReferees();
        throw new DontHavePermissionException();
    }

    public HashMap<String, Fan> getFans(Role role) throws DontHavePermissionException {
        if (role instanceof AssociationDelegate || role instanceof SystemManager || role instanceof Owner)
            return db.getFans();
        throw new DontHavePermissionException();
    }

    public HashMap<String, Player> getPlayers(Role role) throws DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner)
            return db.getPlayers();
        throw new DontHavePermissionException();
    }

    public HashMap<String, Owner> getOwners(Role role) throws DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner)
            return db.getOwners();
        throw new DontHavePermissionException();
    }

    public HashMap<String, Manager> getManagers(Role role) throws DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner)
            return db.getManagers();
        throw new DontHavePermissionException();
    }

    public HashMap<String, Coach> getCoaches(Role role) throws DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner)
            return db.getCoaches();
        throw new DontHavePermissionException();
    }

    public HashMap<String, Member> getMembers(Role role) throws DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner)
            return db.getMembers();
        throw new DontHavePermissionException();
    }

    public HashMap<String, ASchedulingPolicy> getSchedulingPolicies(Role role) throws DontHavePermissionException {
        if (role instanceof AssociationDelegate || role instanceof Owner || role instanceof SystemManager) {
            return db.getSchedulingPolicies();
        } else {
            throw new DontHavePermissionException();
        }
    }

    public HashMap<String, Season> getSeasons(Role role) throws DontHavePermissionException {
        if (role instanceof AssociationDelegate || role instanceof SystemManager)
            return db.getSeasons();
        throw new DontHavePermissionException();
    }

    public HashMap<String, SystemManager> getSystemManagers(Role role) throws DontHavePermissionException {
        if (role instanceof SystemManager)
            return db.getSystemManagers();
        throw new DontHavePermissionException();
    }

    public HashMap<String, AssociationDelegate> getAssociationDelegate(Role role) throws DontHavePermissionException {
        if (role instanceof AssociationDelegate || role instanceof SystemManager)
            return db.getAssociationDelegate();
        throw new DontHavePermissionException();
    }

    public Role getMember(Role role, String id) throws MemberNotExist, DontHavePermissionException {
        if (role instanceof AssociationDelegate || role instanceof SystemManager || role instanceof Owner || role instanceof Guest) {
            if (db.existMember(id)) {
                return db.getMember(id);
            } else if (db.existSystemManager(id)) {
                return db.getSystemManager(id);
            } else {
                throw new MemberNotExist();
            }
        }
        throw new DontHavePermissionException();
    }

    public Team getTeam(Role role, String teamName) throws DontHavePermissionException, ObjectNotExist {
        if (role instanceof AssociationDelegate || role instanceof SystemManager || role instanceof Owner) {
            if (db.existTeam(teamName)) {
                return db.getTeam(teamName);
            } else {
                throw new ObjectNotExist("the team id is not exist");
            }
        }
        throw new DontHavePermissionException();
    }

    public League getLeague(Role role, String leagueId) throws ObjectNotExist, DontHavePermissionException {
        if (role instanceof AssociationDelegate || role instanceof SystemManager) {
            if (db.existLeague(leagueId)) {
                return db.getLeague(leagueId);
            } else {
                throw new ObjectNotExist("the league id is not exist");
            }

        }
        throw new DontHavePermissionException();
    }

    public Season getSeason(Role role, String seasonId) throws ObjectNotExist, DontHavePermissionException {
        if (role instanceof AssociationDelegate || role instanceof SystemManager) {
            if (db.existSeason(seasonId)) {
                return db.getSeason(seasonId);
            } else {
                throw new ObjectNotExist("the league id is not exist");
            }

        }
        throw new DontHavePermissionException();
    }

    public LinkedList<Member> getMembers(Role role, LinkedList<String> idMember) throws MemberNotExist, DontHavePermissionException {
        if (role instanceof AssociationDelegate || role instanceof SystemManager || role instanceof Owner) {
            LinkedList<Member> list = new LinkedList<>();
            for (int i = 0; i < idMember.size(); i++) {
                if (!db.existMember(idMember.get(i))) {
                    throw new MemberNotExist();
                }
                Member member = db.getMember(idMember.get(i));
                list.add(member);
            }
            return list;
        }
        throw new DontHavePermissionException();
    }

    public HashMap<String, Role> getOwnersAndFans(Role role) throws DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner) {
            return db.getOwnersAndFans();
        }
        throw new DontHavePermissionException();
    }

    public Owner getOwner(Role role, String idOwner) throws ObjectNotExist, DontHavePermissionException, MemberNotExist {
        if (role instanceof SystemManager || role instanceof Owner) {
            if (db.existOwner(idOwner)) {
                Owner owner = db.getOwnerOrFan(idOwner);
                owner.setDb(this);
                return owner;
            } else {
                throw new MemberNotExist();
            }
        }
        throw new DontHavePermissionException();
    }

    /***************************************delete function function******************************************/

    public void deleteRole(Role role, String id) throws MemberNotExist, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner || role instanceof AssociationDelegate) {
            if (!db.existMember(id))
                throw new MemberNotExist();
            db.removeRole(id);
            return;
        }
        throw new DontHavePermissionException();
    }

    public void deleteReferee(Role role, String id) throws DontHavePermissionException, MemberNotExist {
        if (role instanceof SystemManager) {
            if (db.existRefree(id)) {
                db.removeRole(id);
            } else {
                throw new MemberNotExist();

            }
        }
        throw new DontHavePermissionException();
    }

    public void deleteFan(Role role, String id) throws MemberNotExist, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner) {
            if (db.existFan(id)) {
                db.removeRole(id);
            } else {
                throw new MemberNotExist();
            }
        }
        throw new DontHavePermissionException();
    }

    public void deleteMember(Role role, String id) throws MemberNotExist, DontHavePermissionException {

        if (role instanceof SystemManager || role instanceof Owner) {
            if (db.existMember(id)) {
                db.removeRole(id);
            } else {
                throw new MemberNotExist();
            }
        }
        throw new DontHavePermissionException();
    }

    public void removeLeague(Role role, String leagueName) throws ObjectNotExist, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof AssociationDelegate) {
            if (!db.existLeague(leagueName))
                throw new ObjectNotExist("");
            db.removeLeague(leagueName);
        }
        throw new DontHavePermissionException();
    }

    public void removeSeason(Role role, String year) throws ObjectNotExist, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof AssociationDelegate) {
            if (!db.existSeason(year))
                throw new ObjectNotExist("");
            db.removeSeason(year);
        }
        throw new DontHavePermissionException();
    }

    public void removeTeam(Role role, String name) throws ObjectNotExist, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner) {

            if (!db.existTeam(name))
                throw new ObjectNotExist("");
            db.removeTeam(name);
        }
        throw new DontHavePermissionException();
    }

    public void deleteOwner(Role role, String ownerId) throws DontHavePermissionException, MemberNotExist {
        if (role instanceof SystemManager || role instanceof Owner) {
            if (db.existOwner(ownerId)) {
                db.removeRole(ownerId);
            } else {
                throw new MemberNotExist();
            }
        }
        throw new DontHavePermissionException();
    }

    public void deleteAssociationDelegate(Role role, String id) throws MemberNotExist, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof AssociationDelegate) {
            if (db.existAssociationDelegate(id)) {
                db.removeAssociationDelegate(id);
            } else {
                throw new MemberNotExist();
            }
        }
        throw new DontHavePermissionException();
    }

    public void deleteSystemManager(Role role, String id) throws MemberNotExist, DontHavePermissionException {
        if (role instanceof SystemManager) {
            if (db.existSystemManager(id)) {
                db.removeSystemManager(id);
            } else {

                throw new MemberNotExist();
            }
        }
        throw new DontHavePermissionException();
    }

    /***************************************add function******************************************/
    public void addAssociationDelegate(Role role, AssociationDelegate associationDelegate) throws
            DontHavePermissionException, AlreadyExistException {
        if (role instanceof SystemManager) {
            if (db.existMember(associationDelegate.getUserMail()))
                throw new AlreadyExistException();
            db.addAssociationDelegate(associationDelegate);
            return;
        }
        throw new DontHavePermissionException();
    }

    public void addFan(Role role, Fan fan) throws AlreadyExistException, DontHavePermissionException {
        if (!(role instanceof Guest)) {
            throw new DontHavePermissionException();
        }
        if (db.existMember(fan.getUserMail()))
            throw new AlreadyExistException();
        db.addFan(fan);
    }

    public void addSeason(Role role,Season season) throws AlreadyExistException, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof AssociationDelegate) {
            if (db.existSeason(season.getYear()))
                throw new AlreadyExistException();
            db.addSeason(season);
        }
        throw new DontHavePermissionException();
    }

    public void addLeague(Role role,League league) throws AlreadyExistException, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof AssociationDelegate) {
            if (db.existLeague(league.getName()))
            throw new AlreadyExistException();
        db.addLeague(league);
        }
        throw new DontHavePermissionException();
    }

    public void addManager(Role role,Manager manager) throws AlreadyExistException, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner) {

            if (db.existMember(manager.getUserMail()))
            throw new AlreadyExistException();
        db.addManager(manager);
        }
        throw new DontHavePermissionException();
    }

    public void addPlayer(Role role,Player player) throws AlreadyExistException, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner) {

            if (db.existMember(player.getUserMail()))
            throw new AlreadyExistException();
        db.addPlayer(player);
        }
        throw new DontHavePermissionException();
    }

    public void addCoach(Role role,Coach coach) throws AlreadyExistException, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner) {

            if (db.existMember(coach.getUserMail()))
            throw new AlreadyExistException();
        db.addCoach(coach);
        }
        throw new DontHavePermissionException();
    }

    public void addOwner(Role role, Owner owner) throws AlreadyExistException, DontHavePermissionException {

        if (!(role instanceof SystemManager || role instanceof Owner)) {
            throw new DontHavePermissionException();
        }
        if (db.existMember(owner.getUserMail()))
            throw new AlreadyExistException();
        db.addOwner(owner);
    }

    public void addSystemManager(Role role,SystemManager systemManager) throws AlreadyExistException, DontHavePermissionException {
        if (role instanceof SystemManager ) {

            if (db.existMember(systemManager.getUserMail()))
            throw new AlreadyExistException();
        db.addSystemManager(systemManager);
        }
        throw new DontHavePermissionException();
    }

    public void addTeam(Role role,Team team) throws AlreadyExistException, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner) {

            if (db.existTeam(team.getName()))
            throw new AlreadyExistException();
        db.addTeam(team);
        }
        throw new DontHavePermissionException();
    }

    public void addReferee(Role role,Referee referee) throws DontHavePermissionException, AlreadyExistException {
        if (role instanceof SystemManager ) {
            if(!db.existRefree(referee.getUserMail())) {
                db.addReferee(referee);
            }
            else
            {
                throw new AlreadyExistException();
            }
        }
        throw new DontHavePermissionException();
    }


    public void addSchedulingPolicies(Role role, ASchedulingPolicy policy) throws DontHavePermissionException {
        if (role instanceof AssociationDelegate || role instanceof Owner || role instanceof SystemManager) {
            db.addSchedulingPolicies(policy);
        } else {
            throw new DontHavePermissionException();
        }
    }
    /***************************************remove function******************************************/

    /********************************************exist function***********************************/
    public boolean existReferee(Role role,String refereeId) throws DontHavePermissionException {
        if (role instanceof SystemManager ) {
            return db.existRefree(refereeId);
        }
        throw new DontHavePermissionException();
    }

    public boolean existFan(Role role,String fanId) throws DontHavePermissionException {
        if (role instanceof SystemManager  || role instanceof Owner ||role instanceof AssociationDelegate) {

            return db.existFan(fanId);
        }
        throw new DontHavePermissionException();
    }

    public boolean existTeam(Role role,String teamName) throws DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner ) {

            return db.existTeam(teamName);
    }
        throw new DontHavePermissionException();
    }

    public boolean existMember(Role role,String id) throws DontHavePermissionException {
        if (role instanceof SystemManager  || role instanceof Owner || role instanceof AssociationDelegate) {

            return db.existMember(id);
    }
        throw new DontHavePermissionException();
    }

    public boolean existAssociationDelegate(Role role,String id) throws DontHavePermissionException {
        if (role instanceof SystemManager  || role instanceof AssociationDelegate) {

            return db.existAssociationDelegate(id);
        }
        throw new DontHavePermissionException();
    }

    public boolean existSystemManager(Role role,String id) throws DontHavePermissionException {
        if (role instanceof SystemManager ) {

            return db.existSystemManager(id);
        }
        throw new DontHavePermissionException();
    }

    public boolean existOwner(Role role,String ownerId) throws DontHavePermissionException {
        if (role instanceof SystemManager  || role instanceof Owner) {

            return db.existOwner(ownerId);
        }
        throw new DontHavePermissionException();
    }

}

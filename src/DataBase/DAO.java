package DataBase;

import Domain.Asset.Coach;
import Domain.Asset.Manager;
import Domain.Asset.Player;
import Domain.Game.Game;
import Domain.Game.Team;
import Domain.League.ASchedulingPolicy;
import Domain.League.League;
import Domain.League.Season;
import Domain.Users.*;
import Exception.AlreadyExistException;
import Exception.DontHavePermissionException;
import Exception.MemberNotExist;
import Exception.ObjectNotExist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public interface DAO {

    /***************************************Getters******************************************/

    public HashMap<String, Role> getRoles(Role role) throws DontHavePermissionException;

    public HashMap<String, Team> getTeams(Role role) throws DontHavePermissionException;

    public HashMap<String, League> getLeagues(Role role) throws DontHavePermissionException;

    public HashMap<String, Referee> getReferees();

    public HashMap<String, Fan> getFans(Role role) throws DontHavePermissionException;

    public HashMap<String, Player> getPlayers(Role role) throws DontHavePermissionException;

    public HashMap<String, Owner> getOwners(Role role) throws DontHavePermissionException;

    public HashMap<String, Manager> getManagers(Role role) throws DontHavePermissionException;

    public HashMap<String, Coach> getCoaches(Role role) throws DontHavePermissionException;

    public HashMap<String, Member> getMembers(Role role) throws DontHavePermissionException;

    public HashMap<String, ASchedulingPolicy> getSchedulingPolicies(Role role) throws DontHavePermissionException;

    public HashMap<String, Season> getSeasons(Role role) throws DontHavePermissionException;

    public HashMap<String, SystemManager> getSystemManagers(Role role) throws DontHavePermissionException;

    public SystemManager getSystemManagers(Role role, String id) throws DontHavePermissionException;

    public HashMap<String, AssociationDelegate> getAssociationDelegate(Role role) throws DontHavePermissionException;

    public AssociationDelegate getAssociationDelegate(Role role, String id) throws DontHavePermissionException;

    public Role getMember(Role role, String id) throws MemberNotExist, DontHavePermissionException;

    public Team getTeam(Role role, String teamName) throws DontHavePermissionException, ObjectNotExist;

    public League getLeague(Role role, String leagueId) throws ObjectNotExist, DontHavePermissionException;

    public Season getSeason(Role role, String seasonId) throws ObjectNotExist, DontHavePermissionException;

    public HashSet<Game> getGames(String league, String season) throws ObjectNotExist;

    public Referee getReferee(Role role, String s) throws ObjectNotExist, DontHavePermissionException;

    public LinkedList<Member> getMembers(Role role, LinkedList<String> idMember) throws MemberNotExist, DontHavePermissionException;

    public HashMap<String, Role> getOwnersAndFans(Role role) throws DontHavePermissionException;

    public Owner getOwner(Role role, String idOwner) throws ObjectNotExist, DontHavePermissionException, MemberNotExist;

    /***************************************delete function************************************************/

    public void deleteRole(Role role, String id) throws MemberNotExist, DontHavePermissionException;

    public void deleteReferee(Role role, String id) throws DontHavePermissionException, MemberNotExist;

    public void deleteFan(Role role, String id) throws MemberNotExist, DontHavePermissionException;

    public void deleteMember(Role role, String id) throws MemberNotExist, DontHavePermissionException;

    public void removeLeague(Role role, String leagueName) throws ObjectNotExist, DontHavePermissionException;

    public void removeSeason(Role role, String year) throws ObjectNotExist, DontHavePermissionException;

    public void removeTeam(Role role, String name) throws ObjectNotExist, DontHavePermissionException;

    public void deleteOwner(Role role, String ownerId) throws DontHavePermissionException, MemberNotExist;

    public void deleteAssociationDelegate(Role role, String id) throws MemberNotExist, DontHavePermissionException;

    public void deleteSystemManager(Role role, String id) throws MemberNotExist, DontHavePermissionException;

    /***************************************add function******************************************/
    public void addAssociationDelegate(Role role, AssociationDelegate associationDelegate) throws DontHavePermissionException, AlreadyExistException;

    public void addFan(Role role, Fan fan) throws AlreadyExistException, DontHavePermissionException;

    public void addSeason(Role role, Season season) throws AlreadyExistException, DontHavePermissionException;

    public void addLeague(Role role, League league) throws AlreadyExistException, DontHavePermissionException;

    public void addManager(Role role, Manager manager) throws AlreadyExistException, DontHavePermissionException;

    public void addPlayer(Role role, Player player) throws AlreadyExistException, DontHavePermissionException;

    public void addCoach(Role role, Coach coach) throws AlreadyExistException, DontHavePermissionException;

    public void addOwner(Role role, Owner owner) throws AlreadyExistException, DontHavePermissionException;

    public void addSystemManager(Role role, SystemManager systemManager) throws AlreadyExistException, DontHavePermissionException;

    public void addTeam(Role role, Team team) throws AlreadyExistException, DontHavePermissionException;

    public void addReferee(Role role, Referee referee) throws DontHavePermissionException, AlreadyExistException;

    public void addSchedulingPolicies(Role role, ASchedulingPolicy policy) throws DontHavePermissionException;

    /***************************************isExist function******************************************/

    public boolean existReferee(Role role, String refereeId) throws DontHavePermissionException;

    public boolean existFan(Role role, String fanId) throws DontHavePermissionException;

    public boolean existTeam(Role role, String teamName) throws DontHavePermissionException;

    public boolean existMember(Role role, String id) throws DontHavePermissionException;

    public boolean existAssociationDelegate(Role role, String id) throws DontHavePermissionException;

    public boolean existSystemManager(Role role, String id) throws DontHavePermissionException;

    public boolean existOwner(Role role, String ownerId) throws DontHavePermissionException;




}

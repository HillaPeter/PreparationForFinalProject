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

    HashMap<String, Role> getRoles();

    HashMap<String, Member> getMembers();

    HashMap<String, Fan> getFans();

    HashMap<String, Referee> getReferees();

    HashMap<String, Owner> getOwners();

    HashMap<String, Player> getPlayers();

    HashMap<String, Manager> getManagers();

    HashMap<String, Coach> getCoaches();

    HashMap<String, SystemManager> getSystemManagers();

    HashMap<String, AssociationDelegate> getAssociationDelegate();

    HashMap<String, Team> getTeams();

    HashMap<String, League> getLeagues();

    HashMap<String, Season> getSeasons();

    HashMap<String, ASchedulingPolicy> getSchedulingPolicies();

    HashSet<Game> getGames(String league, String season) throws ObjectNotExist;

    SystemManager getSystemManagers(String id)throws MemberNotExist;

    AssociationDelegate getAssociationDelegate(String id)throws MemberNotExist;

    Role getMember(String id) throws MemberNotExist;

    Team getTeam(String id) throws ObjectNotExist;

    League getLeague(String id) throws ObjectNotExist;

    Season getSeason(String id) throws ObjectNotExist;

    Fan getFan(String id)throws MemberNotExist;

    Referee getReferee(String id) throws MemberNotExist;

    /***************************************delete function************************************************/

    void deleteRole(Role role, String id) throws MemberNotExist, DontHavePermissionException;

    void deleteReferee(Role role, String id) throws DontHavePermissionException, MemberNotExist;

    void deleteFan(Role role, String id) throws MemberNotExist, DontHavePermissionException;

    void deleteMember(Role role, String id) throws MemberNotExist, DontHavePermissionException;

    void removeTeam(Role role, String id) throws ObjectNotExist, DontHavePermissionException;

    void deleteOwner(Role role, String id) throws DontHavePermissionException, MemberNotExist;

    void deleteAssociationDelegate(Role role, String id) throws MemberNotExist, DontHavePermissionException;

    void deleteSystemManager(Role role, String id) throws MemberNotExist, DontHavePermissionException;

    /***************************************add function******************************************/
    void addAssociationDelegate(Role role, AssociationDelegate associationDelegate) throws DontHavePermissionException, AlreadyExistException;

    void addSystemManager(Role role, SystemManager systemManager) throws AlreadyExistException, DontHavePermissionException;

    void addOwner(Role role, Owner owner) throws AlreadyExistException, DontHavePermissionException;

    void addFan(Role role, Fan fan) throws AlreadyExistException, DontHavePermissionException;

    void addReferee(Role role, Referee referee) throws DontHavePermissionException, AlreadyExistException;

    void addManager(Role role, Manager manager) throws AlreadyExistException, DontHavePermissionException;

    void addPlayer(Role role, Player player) throws AlreadyExistException, DontHavePermissionException;

    void addCoach(Role role, Coach coach) throws AlreadyExistException, DontHavePermissionException;

    void addTeam(Role role, Team team) throws AlreadyExistException, DontHavePermissionException;

    void addSeason(Role role, Season season) throws AlreadyExistException, DontHavePermissionException;

    void addLeague(Role role, League league) throws AlreadyExistException, DontHavePermissionException;

    /***************************************isExist function******************************************/

    boolean existReferee(String refereeId);

    boolean existFan( String fanId) ;

    boolean existTeam(String teamName);

    boolean existMember(String id);

    boolean existAssociationDelegate(String id);

    boolean existSystemManager(String id);

    boolean existOwner(String ownerId);

    boolean existSeason(String id);






}

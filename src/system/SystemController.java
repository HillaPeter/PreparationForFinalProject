package system;

import Asset.Coach;
import Asset.Manager;
import Asset.Player;
import Game.Account;
import Game.Team;
import League.*;
import Users.*;
import Exception.*;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class SystemController {
    private String name;
    private HashMap<String, League> leagues;
    private HashMap<String, Season> seasons;
    private HashMap<String, SystemManager> systemManagers;
    private HashMap<String, Role> roles; // hash map <mail,role>
    private HashMap<String, Team> teams;
    //  private HashMap<Member,String> passwordValidation;

    /**
     * constructor
     *
     * @param name
     */
    public SystemController(String name) {
        this.name = name;
        leagues = new HashMap<>();
        seasons = new HashMap<>();
        systemManagers = new HashMap<>();
        roles = new HashMap<>();
        teams = new HashMap<>();
        //todo
//        password verifications
//        passwordValidation=new HashMap<>();
//        for(Role r:roles){
//            if(r instanceof Member){
//                
//            }
//        }
    }

    public void initSystem(String userName, String password) {
        //check if the user name and the password are connect
    }

    /*************************************** function for guest******************************************/

    /**
     * this function makes a Guest into a member
     * if the member's mail doesnt exist -
     * we will remove the Guest from the roles map and add create a Fan member by default and return true
     * if the member's mail exist in the system - prints a error message and return false.
     *
     * @return true = success or false = failed to sign
     */
    public Member signIn(String userName, String userMail, String password) throws MemberAlreadyExistException {
        if (roles.containsKey(userMail)) {
            throw new MemberAlreadyExistException();
        } else {
            Fan newMember = new Fan(userName, userMail, password);
            roles.put(newMember.getUserMail(), newMember);
            return newMember;
        }
    }

    /**
     * this function makes a guest into an existing member.
     * if the member doesnt exist - return null
     * if the member exist - return the member
     *
     * @return
     */
    public Member logIn(String userMail, String userPassword) throws MemberNotExist, PasswordDontMatchException {
        if (roles.containsKey(userMail) || systemManagers.containsKey(userMail)) {
            Member existingMember = (Member) roles.get(userMail);
            checkValidationPassword(existingMember, userPassword);
            return existingMember;
        } else {
            throw new MemberNotExist();
        }
    }

    private void checkValidationPassword(Member member, String userPassword) throws PasswordDontMatchException {
        if (!member.getPassword().equals(userPassword))
            throw new PasswordDontMatchException();
        return;
    }


    /*************************************** function for system manager******************************************/

    /***
     * this function get id of the refree to remove and the id of the system manager that take care of this function
     * if the refree didnt exist - return false
     * if the refree exist - delete it and return true
     */
    public boolean removeReferee(String systemManagerId, String refreeId) throws MemberNotSystemManager {
        SystemManager systemManager = systemManagers.get(systemManagerId);
        if (null != systemManager) {
            return systemManager.removeReferee(refreeId);
        } else {
            throw new MemberNotSystemManager();
        }
    }

    /***
     * this function get id of the member to make refree and the id of the system manager that take care of this function
     * if the refree already exist - return false
     * if the refree not exist and success of adding it - add it and return true
     */
    public boolean addRefree(String systemManagerId, String refreeId, boolean ifMainRefree) throws MemberNotSystemManager {
        SystemManager systemManager = systemManagers.get(systemManagerId);
        if (null != systemManager) {
            return systemManager.addReferee(refreeId, ifMainRefree);
        } else {
            throw new MemberNotSystemManager();
        }
    }

    /**
     * this function get the team name and the id of the system manager that take care of this function
     * if the team name already exist - close the team and return true
     * if the team dont exist return false
     *
     * @param systemManagerId
     * @param teamName
     * @return
     * @throws MemberNotSystemManager
     */
    public boolean closeTeam(String systemManagerId, String teamName) throws MemberNotSystemManager {
        SystemManager systemManager = systemManagers.get(systemManagerId);
        if (null != systemManager) {
            return systemManager.closeTeam(teamName);
        } else {
            throw new MemberNotSystemManager();
        }
    }

    /**
     * this function get the id of the member we want to delete and the id of the system manager that take care of this function
     *
     * @param systemManagerId
     * @param id
     * @return
     * @throws MemberNotSystemManager
     */
    public boolean removeMember(String systemManagerId, String id) throws MemberNotSystemManager {
        SystemManager systemManager = systemManagers.get(systemManagerId);
        if (null != systemManager) {
            return systemManager.removeMember(id);
        } else {
            throw new MemberNotSystemManager();
        }
    }

    /**
     * this function get the path to the complaint file and the id of the system manager that take care of this function
     *
     * @param systemManagerId
     * @param path
     * @throws MemberNotSystemManager
     */
    public void watchComplaint(String systemManagerId, String path) throws MemberNotSystemManager {
        SystemManager systemManager = systemManagers.get(systemManagerId);
        if (null != systemManager) {
            LinkedList<String> complaint = systemManager.watchComplaint(path);
        } else {
            throw new MemberNotSystemManager();
        }
    }

    /**
     * this function get the path to the complaint file , the response for the complaint and the id of the system manager that take care of this function
     *
     * @param systemManagerId
     * @param path
     * @param responseForComplaint
     * @return
     * @throws MemberNotSystemManager
     */
    public boolean responseComplaint(String systemManagerId, String path, LinkedList<Pair<String, String>> responseForComplaint) throws MemberNotSystemManager {
        SystemManager systemManager = systemManagers.get(systemManagerId);
        if (null != systemManager) {
            return systemManager.ResponseComplaint(path, responseForComplaint);
        } else {
            throw new MemberNotSystemManager();
        }
    }

    public void schedulingGames(String systemManagerId) throws MemberNotSystemManager {
        SystemManager systemManager = systemManagers.get(systemManagerId);
        if (null != systemManager) {
            //   systemManager.schedulingGames();
        } else {
            throw new MemberNotSystemManager();
        }
    }

    public void viewSystemInformation(String systemManagerId) throws MemberNotSystemManager {
        SystemManager systemManager = systemManagers.get(systemManagerId);
        if (null != systemManager) {
            systemManager.viewSystemInformation();
        } else {
            throw new MemberNotSystemManager();
        }
    }

    /**
     * this function get the team name and all the team member and the id of the system manager that take care of this function
     * if the team name not exist - open the team and return true
     * if the team exist return false
     *
     * @param systemManagerId
     * @param players
     * @param coachs
     * @param managers
     * @param owners
     * @param teamName
     * @return
     * @throws MemberNotSystemManager
     */
    public boolean addTeam(String systemManagerId, LinkedList<String> players, LinkedList<String> coachs, LinkedList<String> managers, LinkedList<String> owners, String teamName) throws MemberNotSystemManager {
        SystemManager systemManager = systemManagers.get(systemManagerId);
        if (null != systemManager) {
            return systemManager.addNewTeam(players, coachs, managers, owners, teamName);
        } else {
            throw new MemberNotSystemManager();
        }
    }

    /***************************************help function for system manager******************************************/

    public boolean notAllTheIdAreMembers(LinkedList<String> idPlayers, LinkedList<String> idCoach, LinkedList<String> idManager, LinkedList<String> idOwner) {
        for (int i = 0; i < idPlayers.size(); i++) {
            if (roles.containsKey(idPlayers.get(i)) == false)
                return true;
        }
        for (int i = 0; i < idCoach.size(); i++) {
            if (roles.containsKey(idCoach.get(i)) == false)
                return true;
        }
        for (int i = 0; i < idManager.size(); i++) {
            if (roles.containsKey(idManager.get(i)) == false)
                return true;
        }
        for (int i = 0; i < idOwner.size(); i++) {
            if (roles.containsKey(idOwner.get(i)) == false)
                return true;
        }
        return false;
    }

    public boolean alreadyIncludeThisTeamName(String teamName) {
        if (teams == null) {
            return false;
        }
        for (String name : teams.keySet()
        ) {
            if (name.equals(teamName))
                return true;
        }
        return false;
    }

    public LinkedList<Member> returnFromSystemTheExactUsers(LinkedList<String> id) {
        LinkedList<Member> toReturn = new LinkedList<>();
        for (int i = 0; i < id.size(); i++) {
            toReturn.add((Member) roles.get(id.get(i)));
        }
        return toReturn;
    }

    public void makeTheRoleARefree(String id, boolean mainRefree) {
        //if the refree is main refree the boolean filed wil be true
        //change the role from fan to refree
        Fan fan = (Fan) roles.get(id);
        Referee referee;
        if (mainRefree)
            referee = new MainReferee(fan.getName(), fan.getUserMail(), fan.getPassword(), "");
        else
            referee = new SecondaryReferee(fan.getName(), fan.getUserMail(), fan.getPassword(), "");
        roles.put(fan.getUserMail(), referee);
    }

    /***************************************delete function function******************************************/

    public void deleteTeam(String teamName) {
        teams.remove(teamName);
    }

    public void deleteRefree(String id) {
        roles.remove(id);
    }

    public void deleteRole(String id) {
        roles.remove(id);
    }

    /***************************************exist function******************************************/

    public boolean existFan(String id) {
        return roles.get(id) instanceof Fan;
    }

    public boolean existTeamName(String teamName) {
        if (teams.containsKey(teamName))
            return true;
        else
            return false;
    }

    public boolean existRefree(String id) {
        if (roles.get(id) instanceof Referee)
            return true;
        else
            return false;
    }

    public boolean existRole(String id) {
        return roles.containsKey(id);
    }


    /***************************************get function******************************************/

    public League getLeague(String leagueId) {
        return leagues.get(leagueId);
    }

    public Season getSeason(String seasonId) {
        return seasons.get(seasonId);
    }

    public Team getTeam(String teamName) {
        return teams.get(teamName);
    }

    public Referee getRefree(String id) {
        return (Referee) roles.get(id);
    }

    public Owner getOwner(String id) {
        return (Owner) roles.get(id);
    }

    public Player getPlayer(String id) { return (Player) roles.get(id); }

    public Manager getManager(String id) { return (Manager) roles.get(id); }

    public Coach getCoach(String id) { return (Coach) roles.get(id); }

    public Fan getFan(String id){ return (Fan)roles.get(id); }

    public HashMap<String, Role> getRoles() {
        return roles;
    }

    public HashMap<String, Team> getTeams() {
        return teams;
    }

    public SystemManager getSystemManager(String id){ return systemManagers.get(id); }


    /***************************************add function******************************************/

    /***
     * this function add player to the roles list
     * @param player
     */
    public void addPlayer(Player player) {
        roles.put(player.getUserMail(), player);
    }

    /***
     * this function add coach to the roles list
     * @param coach
     */
    public void addCoach(Coach coach) {
        roles.put(coach.getUserMail(), coach);
    }

    /**
     * this function add manager to the roles list
     *
     * @param manager
     */
    public void addManager(Manager manager) {
        roles.put(manager.getUserMail(), manager);
    }

    /**
     * this function add owner to the roles list
     *
     * @param owner
     */
    public void addOwner(Owner owner) {
        roles.put(owner.getUserMail(), owner);
    }

    /**
     * this function add team to the teams list
     *
     * @param team
     */
    public void addTeam(Team team) {
        teams.put(team.getName(), team);
    }

    /**
     * this function add system manager to the system manager list
     *
     * @param systemManager
     */
    public void addSystemManager(SystemManager systemManager) {
        systemManagers.put(systemManager.getUserMail(), systemManager);
    }

    /**
     * this function add fan to the roles list
     *
     * @param fan1
     */
    public void addFan(Fan fan1) {
        roles.put(fan1.getUserMail(), fan1);
    }


    /******************************* function for Testing!!!!! (noa) *********************************/

    /**
     * this function is used in test - return if the member exist in the system
     *
     * @param memberMail
     * @return
     */
    public boolean ifMemberExistTesting(String memberMail) {
        if (memberMail != null) {
            return roles.containsKey(memberMail);
        }
        return false;
    }

    public int sizeOfMembersListTesting() {
        return this.roles.size();
    }

    public Role getMember(String id){
        if(this.roles.get(id) == null )
            return this.systemManagers.get(id);
        return this.roles.get(id);
    }
    public void addMemberTesting(Member member) {
        this.roles.put(member.getUserMail(), member);
    }

    /*************************************** function for owner******************************************/

    /**
     * owner:
     * add a new manager to team
     * delete his prev role
     * @param owner
     * @param team
     * @param role
     * @param mail
     * @throws NoEnoughMoney
     */
    public void addManager(Owner owner, Team team, Role role, String mail) throws NoEnoughMoney, OwnerNotExist, TeamNotExist {
        /*for tests*/
        if(!this.roles.containsKey(owner.getUserMail()))
            throw new OwnerNotExist();
        if(!this.teams.containsKey(team.getName()))
            throw new TeamNotExist();

        Account account = team.getAccount();
        //how much it cost?
        if (account.getAmountOfTeam() >= 0) {
            account.setAmountOfTeam(account.getAmountOfTeam() - 0);
        } else {
            throw new NoEnoughMoney();
        }

        if (role instanceof SystemManager) {
            SystemManager systemManager = (SystemManager) role;
            Manager manager = new Manager(name, systemManager.getUserMail(), systemManager.getPassword());
            systemManagers.remove(systemManager);
            owner.addNewManager(manager, getTeam(team.getName()));
            addManager(manager);
        } else if (role instanceof Player) {
            Player p=getPlayer(mail);
            Manager manager = new Manager(name, p.getUserMail(), p.getPassword());
            roles.remove(p);
            owner.addNewManager(manager, getTeam(team.getName()));
            addManager(manager);
        } else if (role instanceof Coach) {
            Coach c=getCoach(mail);
            Manager manager = new Manager(name, c.getUserMail(), c.getPassword());
            roles.remove(c);
            owner.addNewManager(manager, getTeam(team.getName()));
            addManager(manager);
        } else if (role instanceof Fan) {
            Fan fan = (Fan) role;
            Manager manager = new Manager(name, fan.getUserMail(), fan.getPassword());
            roles.remove(fan);
            owner.addNewManager(manager, getTeam(team.getName()));
            addManager(manager);
        } else if (role instanceof AssociationDelegate) {
            AssociationDelegate associationDelegate = (AssociationDelegate) role;
            Manager manager = new Manager(name, associationDelegate.getUserMail(), associationDelegate.getPassword());
            roles.remove(associationDelegate);
            owner.addNewManager(manager, getTeam(team.getName()));
            addManager(manager);
        } else if (role instanceof Referee) {
            Referee referee = (Referee) role;
            Manager manager = new Manager(name, referee.getUserMail(), referee.getPassword());
            roles.remove(referee);
            owner.addNewManager(manager, getTeam(team.getName()));
            addManager(manager);
        }
    }


    /**
     * owner:
     * removes a manager
     * @param owner
     * @param team
     * @param mailInput
     */
    public void removeManager(Owner owner, Team team, String mailInput) throws TeamNotExist, OwnerNotExist, ManagerNotExist{
        Manager manager=getManager(mailInput);

        /*for tests*/
        if(!this.roles.containsKey(owner.getUserMail()))
            throw new OwnerNotExist();
        if(!this.teams.containsKey(team.getName()))
            throw new TeamNotExist();
        if(! (owner.getTeams() !=null && owner.getTeams().containsKey(team.getName())) )
            throw new TeamNotExist();
        if(! team.isManager(manager))
            throw new ManagerNotExist();

        owner.removeManager(team, manager);
        Fan fan=new Fan(manager.getName(),manager.getUserMail(),manager.getPassword());
        roles.remove(manager);
        roles.put(fan.getUserMail(),fan);

    }

    /**
     * Owner:
     * reopen team
     * @param ownerMail
     * @param teamId
     */
    public void reopenTeam(String ownerMail, String teamId) throws OwnerNotExist, TeamNotExist, UnavalableOption {
        if(!roles.containsKey(ownerMail))
            throw new OwnerNotExist();
        if (!teams.containsKey(teamId))
            throw new TeamNotExist();

        Owner owner = getOwner(ownerMail);
        Team team = getTeam(teamId);

        if(! owner.checkIfTeamExist(teamId))
            throw new TeamNotExist();
        if(team.getStatus())
            throw new UnavalableOption();

        //todo
    }

    /**
     * Owner :
     * temporary Team Closeing
     * @param ownerMail
     * @param teamId
     */
    public void temporaryTeamClosing(String ownerMail, String teamId) throws OwnerNotExist, TeamNotExist, UnavalableOption{
        if(!roles.containsKey(ownerMail))
            throw new OwnerNotExist();
        if (!teams.containsKey(teamId))
            throw new TeamNotExist();

        Owner owner = getOwner(ownerMail);
        Team team = getTeam(teamId);

        if(! owner.checkIfTeamExist(teamId))
            throw new TeamNotExist();
        if(!team.getStatus())
            throw new UnavalableOption();


        //todo

    }
}

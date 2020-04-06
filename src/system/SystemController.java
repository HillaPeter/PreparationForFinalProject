package system;

import Asset.Coach;
import Asset.Manager;
import Asset.Player;
import Asset.TeamMember;
import Game.Team;
import League.*;
import Users.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class SystemController {
    private String name;
    private HashSet<League> leagues;
    private HashSet<Season> seasons;
    private HashMap<String, SystemManager> systemManagers;
    private HashMap<String, Role> roles; // hash map <mail,role>
    private HashMap<String, Team> teams;
    //  private HashMap<Member,String> passwordValidation;

    public SystemController(String name) {
        this.name = name;
        leagues = new HashSet<>();
        seasons = new HashSet<>();
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

    public void addPlayer(Player player)
    {
        roles.put(player.getUserMail() , player);
    }
    public void addCoach(Coach coach)
    {
        roles.put(coach.getUserMail() , coach);
    }
    public void addManager(Manager manager)
    {
        roles.put(manager.getUserMail() , manager);
    }
    public void addOwner(Owner owner)
    {
        roles.put(owner.getUserMail() , owner);
    }
    public void addTeam(Team team) {
        teams.put(team.getName(), team);
    }
    public void addSystemManager(SystemManager systemManager) {
        systemManagers.put(systemManager.getUserMail() , systemManager);
    }

    public boolean notAllTheIdAreMembers(LinkedList<Integer> idPlayers, LinkedList<Integer> idCoach, LinkedList<Integer> idManager, LinkedList<Integer> idOwner) {
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
        if(teams==null)
        {
            return false;
        }
        for (String name : teams.keySet()
        ) {
            if (name.equals(teamName))
                return true;
        }
        return true;
    }

    public LinkedList<Member> returnFromSystemTheExactUsers(LinkedList<Integer> id) {
        LinkedList<Member> toReturn = new LinkedList<>();
        for (int i = 0; i < id.size(); i++) {
            toReturn.add((Member) roles.get(id.get(i)));
        }
        return toReturn;
    }

    public boolean existTeamName(String teamName) {
        if (teams.containsKey(teamName))
            return true;
        else
            return false;
    }

    public Team getTeam(String teamName) {
        return teams.get(teamName);
    }

    public void deleteTeam(String teamName) {
        teams.remove(teamName);
    }

    public Referee getRefree(String id) {
        return (Referee) roles.get(id);
    }

    public boolean existRefree(String id) {
        if (roles.get(id) instanceof Referee)
            return true;
        else
            return false;
    }

    public void deleteRefree(String id) {
        roles.remove(id);
    }

    public boolean existRole(String id) {
        return roles.containsKey(id);
    }

    public boolean notHadAJob(String id) {
        if (roles.get(id) instanceof TeamMember)
            return false;
        else
            return true;
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
        roles.put(fan.getUserMail() , referee);
    }

    public boolean existMember(String id) {
        return roles.get(id) instanceof Member;
    }

    public void deleteRole(String id) {
        roles.remove(id);
    }

    public boolean existFan(String id) {
        return roles.get(id) instanceof Fan;
    }

    /**
     * this function makes a Guest into a member
     * if the member's mail doesnt exist -
     * we will remove the Guest from the roles map and add create a Fan member by default and return true
     * if the member's mail exist in the system - prints a error message and return false.
     * @return true = success or false = failed to sign
     */
    public boolean signIn(){
        if(roles.containsKey("0")){
            Guest guest = (Guest)roles.get("0");
            String[] details = guest.signIn();
            if(roles.containsKey(details[0])){
                System.out.println("This member mail is already exist in the system.\nsign in with different mail");
                return false;
            }
            else{
                roles.remove("0");
                Fan newMember = new Fan(details[1],details[0],details[3]);
                roles.put(newMember.getUserMail(),newMember);
                return true;
            }
        }
        else{
            System.out.println("you cant sign in");
            return false;
        }
    }

    /**
     * this function makes a guest into an existing member.
     * if the member doesnt exist - return null
     * if the member exist - return the member
     * @return
     */
    public Role logIn(){
        if(roles.containsKey("0")){
            Guest guest = (Guest)roles.get("0");
            String[] details = guest.logIn();
            if(roles.containsKey(details[0])){
                Role existingMember = roles.get(details[0]);
                roles.remove("0");
                return existingMember;
            }
            else{
                System.out.println("This member mail is doesnt exist in the system.\nlog in with different mail");
                return null;
            }
        }
        else{
            System.out.println("you cant log in");
            return null;
        }
        //todo - exception
    }
}

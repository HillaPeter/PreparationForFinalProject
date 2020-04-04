package System;

import Asset.Coach;
import Game.Team;
import League.*;
import Users.Member;
import Users.Role;
import Users.SystemManager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class System {
    private String name;
    private HashSet<League> leagues;
    private HashSet<Season> seasons;
    private HashSet<SystemManager> systemManagers;
    private HashMap<Integer, Role> roles;
    private HashSet<Team> teams;
    //  private HashMap<Member,String> passwordValidation;

    public System(String name) {
        this.name = name;
        leagues = new HashSet<>();
        seasons = new HashSet<>();
        systemManagers = new HashSet<>();
        roles = new HashMap<>();

        //todo
//        password verifications
//        passwordValidation=new HashMap<>();
//        for(Role r:roles){
//            if(r instanceof Member){
//                
//            }
//        }
    }

    public void addTeam(Team team) {
        teams.add(team);
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
        for (Team team : teams
        ) {
            if (team.getName().equals(teamName))
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
}

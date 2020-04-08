package Game;

import Asset.*;
import Users.Member;
import Users.Owner;
import Users.Role;
import Users.SystemManager;

import java.util.HashSet;
import java.util.LinkedList;

public class Team {
    private String name;
    private Account account;
    private HashSet<Coach> coaches;
    private HashSet<Player> players;
    private HashSet<Manager> managers;
    private HashSet<Owner> owners;
    private Field field;

    public Team(String name, Account account, Field field) {
        this.name = name;
        this.account = account;
        this.field = field;
        coaches = new HashSet<>();
        players = new HashSet<>();
        managers = new HashSet<>();
        owners = new HashSet<>();
    }

    public Team(Account account, LinkedList<Player> players, LinkedList<Coach> coaches, LinkedList<Manager> managers, LinkedList<Owner> owners, String teamName) {
        this.name = teamName;
        this.account = account;
        this.coaches = new HashSet<>();
        this.players = new HashSet<>();
        this.managers = new HashSet<>();
        this.owners = new HashSet<>();
        this.coaches.addAll(coaches);
        this.players.addAll(players);
        this.owners.addAll(owners);
        this.managers.addAll(managers);
        updateTheTeamListCoach(coaches);
        updateTheTeamListPlayer(players);
        updateTheTeamListManager(managers);
        updateTheTeamListForOwner(owners);

    }

    private void updateTheTeamListCoach(LinkedList<Coach> list) {
        for(int i=0; i<list.size(); i++)
        {
            TeamMember member=list.get(i);
            member.addTeam(this);
        }
    }
    private void updateTheTeamListManager(LinkedList<Manager> list) {
        for(int i=0; i<list.size(); i++)
        {
            TeamMember member=list.get(i);
            member.addTeam(this);
        }
    }
    private void updateTheTeamListPlayer(LinkedList<Player> list) {
        for(int i=0; i<list.size(); i++)
        {
            TeamMember member=list.get(i);
            member.addTeam(this);
        }
    }
    private void updateTheTeamListForOwner(LinkedList<Owner> list) {
        for(int i=0; i<list.size(); i++)
        {
            Owner owner=list.get(i);
            owner.addTeam(this);
        }
    }

    public String getName() {
        return name;
    }

    public void deleteTheData() {
        for (Player player : players
        ) {
            player.removeTheTeamFromMyList(this.name);
        }
        for (Owner owner : owners
        ) {
            owner.removeTheTeamFromMyList(this.name);
        }
        for (Coach coach : coaches
        ) {
            coach.removeTheTeamFromMyList(this.name);
        }
        for (Manager manager : managers
        ) {
            manager.removeTheTeamFromMyList(this.name);
        }

    }

    public HashSet<Coach> getCoaches() {
        return coaches;
    }

    public void setManagers(HashSet<Manager> managers) {
        this.managers = managers;
    }

    public HashSet<Manager> getManagers() {
        return managers;
    }

    public HashSet<Owner> getOwners() {
        return owners;
    }

    public HashSet<Player> getPlayers() {
        return players;
    }

    public Account getAccount() {
        return account;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void setPlayers(HashSet<Player> players) {
        this.players = players;
    }

    public void setOwners(HashSet<Owner> owners) {
        this.owners = owners;
    }

    public boolean isManager(Manager someone) {
        return this.managers.contains(someone);
    }

    public void addManager(Manager someone) {
        if(someone!=null && !this.managers.contains(someone))
            this.managers.add(someone);
    }
}


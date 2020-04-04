package Game;

import Asset.*;
import Users.Owner;

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
}


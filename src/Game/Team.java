package Game;

import Asset.*;
import Users.Owner;

import java.util.HashSet;

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
        coaches=new HashSet<>();
        players=new HashSet<>();
        managers=new HashSet<>();
        owners=new HashSet<>();
    }
}

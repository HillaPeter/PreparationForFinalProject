package Asset;

import Game.Event;
import Game.Team;

import java.util.HashSet;
import java.util.Date;

public class Player extends TeamMember{
    private HashSet<Event> events;
    private Date birthDate;
    private String role;


    public Player(String name, int userId, String password, Team team, HashSet<Event> events, Date birthDate, String role) {
        super(name, userId, password, team);
        this.events = events;
        this.birthDate = birthDate;
        this.role = role;
    }
}

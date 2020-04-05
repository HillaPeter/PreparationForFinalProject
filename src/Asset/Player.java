package Asset;

import Game.Event;
import Game.Team;

import java.util.HashSet;
import java.util.Date;

public class Player extends TeamMember{
    private HashSet<Event> events;
    private Date birthDate;
    private String role;
    private Team team;//maybe just one team


    public Player(String name, int userId, String password, HashSet<Event> events, Date birthDate, String role) {
        super(name, userId, password);
        this.events = events;
        this.birthDate = birthDate;
        this.role = role;
    }
}

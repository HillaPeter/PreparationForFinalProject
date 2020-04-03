package Game;
import Asset.*;

import java.util.ArrayList;
import java.util.Date;

public class Event {
    private int id;
    private Date date;
    private Date time;
    private String description;
    private EventInGame eventInGame;
    private Date gameMinute;
    private ArrayList<Player> players;

    public Event(int id, Date date, Date time, String description, EventInGame eventInGame, Date gameMinute, ArrayList<Player> players) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.description = description;
        this.eventInGame = eventInGame;
        this.gameMinute = gameMinute;
        this.players = players;
    }
}

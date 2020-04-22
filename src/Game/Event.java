package Game;

import java.util.Date;

public class Event {
    private Date time;
    private String description;
    private EventInGame eventInGame;
    private int gameMinute;


    public Event(Date time, String description, EventInGame eventInGame, int gameMinute) {
        this.time = time;
        this.description = description;
        this.eventInGame = eventInGame;
        this.gameMinute = gameMinute;
    }
}

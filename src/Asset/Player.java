package Asset;

import Game.Event;
import Game.Team;
import Users.Fan;

import java.util.HashSet;
import java.util.Date;

public class Player extends TeamMember{
    private HashSet<Event> events;
    private Date birthDate;
    private String role;
  //  private Team team;//maybe just one team


    public Player(String name, String userMail, String password, Date birthDate, String role) {
        super(name, userMail, password,birthDate);
        this.birthDate = birthDate;
        this.role = role;
        events=new HashSet<>();
    }

    public Player(String name, String userMail, Date birthDate, String role) {
        super(name, userMail , birthDate);
        this.birthDate = birthDate;
        this.role = role;
        setPassword(null);
    }
    public void deleteAllTheData()
    {
        for (String strTeam:team.keySet()
        ) {
            team.get(strTeam).removePlayer(this);
        }
    }
}

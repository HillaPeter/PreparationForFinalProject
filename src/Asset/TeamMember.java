package Asset;

import Game.Team;
import Users.Member;

import java.util.HashMap;
import java.util.HashSet;

public class TeamMember extends Member {
    private HashMap<String , Team> team;

    public TeamMember(String name, int userId, String password) {
        super(name, userId, password);
        this.team = new HashMap<>();
    }

    public void removeTheTeamFromMyList(String name)
    {
        team.remove(name);
    }
}

package Asset;

import Game.Team;
import Users.Member;

import java.util.HashMap;
import java.util.HashSet;

public class TeamMember extends Member {
    private HashMap<String , Team> team;

    public TeamMember(String name, int userId, String password, Team team) {
        super(name, userId, password);
        this.team = new HashMap<>();
        this.team.put(team.getName() , team);
    }

    public void removeTheTeamFromMyList(String name)
    {
        team.remove(name);
    }
}

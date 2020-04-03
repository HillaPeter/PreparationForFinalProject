package Asset;

import Game.Team;
import Users.Member;

public class TeamMember extends Member {
    private Team team;

    public TeamMember(String name, int userId, String password, Team team) {
        super(name, userId, password);
        this.team = team;
    }
}

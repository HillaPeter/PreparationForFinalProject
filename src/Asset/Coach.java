package Asset;

import Game.Team;

public class Coach extends TeamMember{
    private String roleInTeam;
    private String training;

    public Coach(String name, int userId, String password, Team team, String roleInTeam, String training) {
        super(name, userId, password, team);
        this.roleInTeam = roleInTeam;
        this.training = training;
    }
}

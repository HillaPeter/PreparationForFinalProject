package Asset;

import Game.Team;

import java.util.HashSet;

public class Coach extends TeamMember{
    private String roleInTeam;
    private String training;

    public Coach(String name, int userId, String password, String roleInTeam, String training) {
        super(name, userId, password);
        this.roleInTeam = roleInTeam;
        this.training = training;
    }
}

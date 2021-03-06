package Domain.Asset;

import Domain.Game.Team;
import Domain.Users.Member;

import java.util.Date;
import java.util.HashMap;


public class TeamMember extends Member {
    protected HashMap<String, Team> team;

    public TeamMember(String name, String userMail, String password, Date birthDate) {
        super(name, userMail, password, birthDate);
    }

    public TeamMember(String name, String userMail, Date birthDate) {
        super(name, userMail,  birthDate);
    }

    @Override
    public String getType() {
        return "TeamMember";
    }

    public void addTeam(Team teamToEnter) {
        if (team == null) {
            team = new HashMap<>();
        }
        if (!team.containsKey(teamToEnter.getName())) {
            team.put(teamToEnter.getName(), teamToEnter);
        }
    }

    public void removeTheTeamFromMyList(String name) {
        team.remove(name);
    }

    public HashMap<String, Team> getTeam() {
        return team;
    }

    public void setTeam(HashMap<String, Team> team) {
        this.team = team;
    }

    public boolean existInTeam(String mail) {
        if (team.containsKey(mail)) {
            return true;
        }
        return false;
    }



}

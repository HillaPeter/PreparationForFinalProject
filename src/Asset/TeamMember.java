package Asset;

import Game.Team;
import Users.Member;
import Users.Role;

import java.util.HashMap;


public class TeamMember extends Member {
    private HashMap<String , Team> team;

    public TeamMember(String name, String userMail, String password) {
        super(name, userMail, password);
    }

    public TeamMember(String name, String userMail) {
        super(name, userMail);
    }

    public void addTeam(Team teamToEnter)
    {
        if(team==null)
        {
            team=new HashMap<>();
        }
        team.put(teamToEnter.getName(), teamToEnter);
    }
    public void removeTheTeamFromMyList(String name)
    {
        team.remove(name);
    }

    public HashMap<String, Team> getTeam() {
        return team;
    }

    public void setTeam(HashMap<String, Team> team) {
        this.team = team;
    }


   public boolean existInTeam(String mail){
        if(team.containsKey(mail)){
            return true;
        }
        return false;
   }
}

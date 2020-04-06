package Users;

import Asset.TeamMember;
import Game.Team;

import java.util.HashMap;

public class Owner extends Member {
    private TeamMember teamMember;
    private HashMap<String,Team> team;

    public Owner(String name, int userId, String password) {
        super(name, userId, password);
        this.teamMember = null;
        //todo addTeam func?
    }

    public void removeTheTeamFromMyList(String name)
    {
        team.remove(name);
    }
    public void addAsset(){
        //todo
    }

    public void updateAsset(){
        //todo
    }

    public void removeAsset(){
        //todo
    }

    public void addNewManager(){
        //todo
    }

    public void addNewOwner(){
        //todo
    }

    public void removeManager(){
        //todo
    }

    public void temporaryTeamClosing(){
        //todo
    }

    public void reopenClosedTeam(){
        //todo
    }

    public void addIncome(){
        //todo
    }

    public void addOutCome(){
        //todo
    }

}

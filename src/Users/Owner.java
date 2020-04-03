package Users;

import Asset.TeamMember;
import Game.Team;

public class Owner extends Member {
    private TeamMember teamMember;
    private Team team;

    public Owner(String name, int userId, String password, TeamMember teamMember) {
        super(name, userId, password);
        this.teamMember = teamMember;
        //todo addTeam func?
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

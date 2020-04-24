package Domain.Asset;

import Domain.Game.Team;

public class Field{
    private String nameOfField;
    private Team team;

    public Field(String nameOfField) {
        this.nameOfField = nameOfField;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getNameOfField() {
        return nameOfField;
    }

    public Object getName() {
        return this.nameOfField;
    }

}

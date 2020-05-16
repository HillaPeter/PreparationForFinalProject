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

    @Override
    public String toString() {
        String str="";
        str="\'"+this.nameOfField+"\',\'"+this.team.toString()+"\'";
        return str;
    }
}

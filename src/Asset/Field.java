package Asset;

import Game.Team;

public class Field{
    private String nameOfField;
    private Team team;

    public Field(String nameOfField) {
        this.nameOfField = nameOfField;
    }

    public Field() {
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

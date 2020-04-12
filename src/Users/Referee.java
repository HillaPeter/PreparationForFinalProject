package Users;

import Game.Game;

import java.util.HashMap;
import java.util.HashSet;

public abstract class Referee extends Member{
    private String training;
    private HashSet<Game> games;

    public Referee(String name, String userMail, String password, String training) {
        super(name, userMail, password);
        this.training = training;
    }

    public void addGame(Game game){
        games.add(game);
    }

    public HashSet printGameSchedule(){
        return games;
    }

    public void updateDetails(){
        //todo
    }


    public void deleteTheGames()
    {
        /*
        for (Game game:games
             ) {
            game.removeReferee(this);
        }
        */
    }


}

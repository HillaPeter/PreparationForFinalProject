package Users;

import Game.Game;
import Exception.IncorrectInputException;

import java.util.HashSet;

public abstract class Referee extends Member{
    private String training;
    private HashSet<Game> games;

    public Referee(String name, String userMail, String password, String training) {
        super(name, userMail, password);
        this.training = training;
        games=new HashSet<Game>();
    }


    public void addGame(Game game){
        games.add(game);
    }

    public HashSet getGameSchedule(){
        return games;
    }

    // the details which didn't update will be recieved as ""
    public void updateDetails(String newName, String newMail,String newPassword, String newTraining) throws Exception{
        if (newName == null || newMail ==null || newPassword == null || newTraining == null){
            throw new IncorrectInputException("");
        }
        if (newName != ""){
            super.setName(newName);
        }
        if (newMail != ""){
            super.setUserMail(newMail);
        }
        if (newPassword != ""){
            super.setPassword(newPassword);
        }
        if (newTraining != ""){
            this.training = newTraining;
        }
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

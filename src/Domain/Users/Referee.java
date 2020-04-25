package Domain.Users;

import Domain.Game.Game;
import DataBase.DBController;

import java.util.Date;
import java.util.HashSet;


public abstract class Referee extends Member{
    protected String training;
    protected HashSet<Game> games;

    protected DBController dbController;

    public Referee(String name, String userMail, String password, String training , Date birthDate) {
        super(name, userMail, password , birthDate);
        this.training = training;
        games=new HashSet<Game>();
    }

    public HashSet<Game> getGameSchedule(){
        return games;
    }


    public void addGame(Game game){
        games.add(game);
    }


    public boolean hadGames()
    {
        return games.size()>0;
    }
}

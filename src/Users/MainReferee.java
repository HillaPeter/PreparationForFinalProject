package Users;

import Asset.Player;
import Game.EventInGame;
import Game.Game;
import Game.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class MainReferee extends Referee {

    public MainReferee(String name, String userMail, String password, String training) {
        super(name, userMail, password, training);
    }
    public MainReferee(Fan fan)
    {
        super(fan.getName(),fan.getUserMail(), fan.getPassword() , "");
    }
   // public HashMap<Game,String> getGameReport()
    //todo

   public void updateGameEvent(Game game, int id, Date date, Date time, String description,
                               EventInGame eventInGame, Date gameMinute, ArrayList<Player> players){
        game.addEvent(new Event(id, date, time, description,
               eventInGame, gameMinute, players));
   }



}

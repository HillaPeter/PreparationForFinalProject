package Domain.Asset;

import Observer.ObservableTeam;
import Observer.ObserverTeamManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

public class Manager extends TeamMember implements ObserverTeamManager {

    ArrayList<String> updates;

    public Manager(String name, String userMail, String password , Date birthDate) {
        super(name, userMail, password , birthDate);
        updates=new ArrayList<>() ;
    }

    public Manager(String name, String userMail , Date birthDate) {
        super(name, userMail , birthDate);
        this.setPassword(null);
    }

    public void deleteAllTheData()
    {
        for (String strTeam:team.keySet()
        ) {
            team.get(strTeam).removeManager(this);
        }
    }

    @Override
    public void update(Observable o, Object message) {
        if(o instanceof ObservableTeam){
            updates.add("new update:" + message.toString());
        }
    }

}

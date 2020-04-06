
import Asset.Coach;
import Asset.Manager;
import Asset.Player;
import Game.Event;
import Users.Owner;
import Users.SystemManager;
import system.SecurityMachine;
import system.system;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;


public class Main {
    public static void main(String[] args) {
        SecurityMachine securityMachine = new SecurityMachine();
        String afterEncrtypt = securityMachine.encrypt("stamLibdok", "key");
        String afterDycrypt = securityMachine.decrypt(afterEncrtypt, "key");
        System.out.println(afterEncrtypt);
        System.out.println(afterDycrypt);


        system system = new system("shachar");

        LinkedList<Player> players1 = new LinkedList<>();
        LinkedList<Player> players2 = new LinkedList<>();
        LinkedList<Integer>onlyTheIdPlayers1=new LinkedList<>();
        LinkedList<Integer>onlyTheIdPlayers2=new LinkedList<>();

        for (int i = 0; i < 11; i++) {
            players1.add(new Player("jordi"+i, i, "" + i + i + i, new HashSet<>(), new Date(i, i, i + i), "Player"));
            players2.add(new Player("static"+i, i, "" + i + i + i, new HashSet<>(), new Date(i, i, i + i), "Player"));
            onlyTheIdPlayers1.add(i);
            onlyTheIdPlayers2.add(i);
        }
        for (int i = 0; i < 11; i++) {
            system.addPlayer(players1.get(i));
            system.addPlayer(players2.get(i));
        }
        LinkedList<Integer>onlyTheIdCoach1=new LinkedList<>();
        LinkedList<Integer>onlyTheIdManager21=new LinkedList<>();
        LinkedList<Integer>onlyTheIdOwner1=new LinkedList<>();



        Coach coach1 = new Coach("romi", 123, "123","Coach" , "");
        Coach coach2 = new Coach("yosi", 124, "124","Coach" , "");
        system.addCoach(coach1);
        onlyTheIdCoach1.add(123);
        system.addCoach(coach1);
        Manager manager1=new Manager("shlomi" , 125 , "125");
        Manager manager2=new Manager("lior" , 126 , "126");
        system.addManager(manager1);
        onlyTheIdManager21.add(125);
        system.addManager(manager2);
        Owner owner1=new Owner("daniel" , 127 , "127");
        Owner owner2=new Owner("ben-el" , 128 , "128");
        system.addOwner(owner1);
        onlyTheIdOwner1.add(127);
        system.addOwner(owner2);
        SystemManager systemManager=new SystemManager("shachar" , 208240275 , "208240275" , system);
        system.addSystemManager(systemManager);
        systemManager.addNewTeam(onlyTheIdPlayers1 , onlyTheIdCoach1 , onlyTheIdManager21 , onlyTheIdOwner1 , "macabi");
        System.out.println("done");
        int x=0;
        x++;

        // system.addCoach();
        //system.addManager();
        //system.addOwner();
        //system.addRefree();
        //system
        //system.addTeam();


    }
}

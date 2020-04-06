
import Asset.*;
import Game.Account;
import Game.Team;
import Game.Transaction;
import Users.Owner;
import Users.SystemManager;
import system.SecurityMachine;
import system.SystemController;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        SecurityMachine securityMachine = new SecurityMachine();
        String afterEncrtypt = securityMachine.encrypt("stamLibdok", "key");
        String afterDycrypt = securityMachine.decrypt(afterEncrtypt, "key");
        System.out.println(afterEncrtypt);
        System.out.println(afterDycrypt);


        SystemController SystemController = new SystemController("shachar");

        LinkedList<Player> players1 = new LinkedList<>();
        LinkedList<Player> players2 = new LinkedList<>();
        LinkedList<Integer> onlyTheIdPlayers1 = new LinkedList<>();
        LinkedList<Integer> onlyTheIdPlayers2 = new LinkedList<>();

        for (int i = 0; i < 11; i++) {
            players1.add(new Player("jordi" + i, "1", "" + i + i + i, new Date(i, i, i + i), "Player"));
            players2.add(new Player("static" + i, "2", "" + i + i + i, new Date(i, i, i + i), "Player"));
            onlyTheIdPlayers1.add(i);
            onlyTheIdPlayers2.add(i);
        }
        for (int i = 0; i < 11; i++) {
            SystemController.addPlayer(players1.get(i));
            SystemController.addPlayer(players2.get(i));
        }
        LinkedList<Integer> onlyTheIdCoach1 = new LinkedList<>();
        LinkedList<Integer> onlyTheIdManager21 = new LinkedList<>();
        LinkedList<Integer> onlyTheIdOwner1 = new LinkedList<>();


        Coach coach1 = new Coach("romi", "romi@gmaill.com", "123", "Coach");
        Coach coach2 = new Coach("yosi", "yosi@gmaill.com", "124", "Coach");
        SystemController.addCoach(coach1);
        onlyTheIdCoach1.add(123);
        SystemController.addCoach(coach1);
        Manager manager1 = new Manager("shlomi", "shlomi@gmaill.com", "125");
        Manager manager2 = new Manager("lior", "lior@gmaill.com", "126");
        SystemController.addManager(manager1);
        onlyTheIdManager21.add(125);
        SystemController.addManager(manager2);
        Owner owner1 = new Owner("daniel", "daniel@gmail.com", "127");
        Owner owner2 = new Owner("ben-el", "ben-el@gmail.com", "128");
        SystemController.addOwner(owner1);
        onlyTheIdOwner1.add(127);
        SystemController.addOwner(owner2);
        SystemManager systemManager = new SystemManager("shachar", "shachar@gmail.com", "208240275", SystemController);
        SystemController.addSystemManager(systemManager);
        systemManager.addNewTeam(onlyTheIdPlayers1, onlyTheIdCoach1, onlyTheIdManager21, onlyTheIdOwner1, "macabi");
        System.out.println("done");
        int x = 0;
        x++;

        // system.addCoach();
        //system.addManager();
        //system.addOwner();
        //system.addRefree();
        //system
        //system.addTeam();


        /**
         * checking owner- please don't delete it! (Hilla P)
         */
/*
        Owner ownerHilla=new Owner("hilla" , "hilla@gmail.com" , "3wet127");
        Owner ownerLiat=new Owner("liat" , "liat@gmail.com" , "123237");

        Transaction transaction=new Transaction("Transaction",12);
        Transaction transaction1=new Transaction("Transaction1",43);
        Transaction transaction2=new Transaction("Transaction2",445453);
        ArrayList<Transaction> listTransactions= new ArrayList<>();
        listTransactions.add(transaction);
        listTransactions.add(transaction1);
        listTransactions.add(transaction2);

        Account account0=new Account("Hapoel", listTransactions,123123);
        Field field0= new Field();
        Team team0= new Team("Hapoel Haifa",account0,field0);
        field0.setTeam(team0);
        HashSet<Owner> ownersTeam0= team0.getOwners();
        ownersTeam0.add(ownerHilla);

        Account account1=new Account("Maccabi", listTransactions,12335435);
      //  Field field1= new Field("Sami offer");
        Team team1= new Team("Maccabi Haifa",account1,null);
       // field1.setTeam(team1);
        HashSet<Owner> ownersTeam1= team1.getOwners();
        ownersTeam1.add(ownerHilla);

        HashMap<String,Team> hashMapTeams=new HashMap<>();
        hashMapTeams.put("Maccabi",team0);
        hashMapTeams.put("Hapoel",team1);

        SystemController.addTeam(team0);
        SystemController.addTeam(team1);
        owner1.setTeams(hashMapTeams);

        System.out.println("--");
        owner1.addAsset();
        System.out.println("--done.");

*/


    }
}

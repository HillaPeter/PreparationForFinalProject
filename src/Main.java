
import Asset.Player;
import system.SecurityMachine;
import system.system;


public class Main {
    public static void main(String[] args) {
        SecurityMachine securityMachine=new SecurityMachine();
        String afterEncrtypt = securityMachine.encrypt("stamLibdok" , "key");
        String afterDycrypt = securityMachine.decrypt(afterEncrtypt , "key");
        System.out.println(afterEncrtypt);
        System.out.println(afterDycrypt);

        system system =new system("shachar");
        Player[] players=new Player[22];
        for(int i=0; i<22; i++) {
      //      players[i]=new Player()
        }
       // system.addCoach();
        //system.addManager();
        //system.addOwner();
        //system.addRefree();
        //system
        //system.addTeam();



    }
}

package System;

import League.*;
import Users.Member;
import Users.Role;
import Users.SystemManager;

import java.util.HashMap;
import java.util.HashSet;

public class System {
    private String name;
    private HashSet<League> leagues;
    private HashSet<Season> seasons;
    private HashSet<SystemManager> systemManagers;
    private HashSet<Role> roles;

  //  private HashMap<Member,String> passwordValidation;

    public System(String name) {
        this.name = name;
        leagues=new HashSet<>();
        seasons=new HashSet<>();
        systemManagers=new HashSet<>();
        roles=new HashSet<>();

        //todo
//        password verifications
//        passwordValidation=new HashMap<>();
//        for(Role r:roles){
//            if(r instanceof Member){
//                
//            }
//        }
    }
}

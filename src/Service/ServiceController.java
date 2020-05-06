package Service;

import Domain.Users.*;
import Exception.*;
import Presentation.*;

public class ServiceController {

    SystemController systemController = new SystemController("DomainController");


    public Menu login(String id, String pass) throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException {
      ///  Member member = systemController.logIn(id,pass);

        // String type = systemController.logIn(id,pass);

        /*if(type.equals ( "Owner" ) )
               return newOwnerPre
        */
        return null;
    }
   // public RoleMenu login(){
     //   switch member to RoleMenu
    //}

}

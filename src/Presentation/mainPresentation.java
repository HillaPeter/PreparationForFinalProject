package Presentation;

import Presentation.Guest.GuestMenu;
import Presentation.SystemManager.SystemManagerMenu;
import Exception.*;

import javax.swing.*;

import static com.sun.glass.ui.Cursor.setVisible;

public class mainPresentation {
    public static void main(String[] args){

//        SystemManagerMenu systemManagerMenu=new SystemManagerMenu();
//        systemManagerMenu.showMenu();

        GuestMenu guestMenu = new GuestMenu();
        guestMenu.showMenu();

      // systemManagerMenu.addSystemManager();
      //  systemManagerMenu.addAssociationDeligate();
       // systemManagerMenu.removeAssociationDeligate();

    }
}

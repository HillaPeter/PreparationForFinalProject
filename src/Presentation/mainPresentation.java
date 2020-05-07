package Presentation;

import Presentation.Guest.GuestMenu;
import Presentation.SystemManager.SystemManagerMenu;
import Exception.*;

import javax.swing.*;

import static com.sun.glass.ui.Cursor.setVisible;

public class mainPresentation {
    public static void main(String[] args) throws DontHavePermissionException, IncorrectInputException, AlreadyExistException {

        SystemManagerMenu systemManagerMenu=new SystemManagerMenu();
        systemManagerMenu.showMenu();
      // systemManagerMenu.addSystemManager();
      //  systemManagerMenu.addAssociationDeligate();
       // systemManagerMenu.removeAssociationDeligate();

        /*

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GuestMenu frame = new GuestMenu();
                frame.setVisible(true);
            }
        });
    */
    }
}

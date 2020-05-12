package Presentation;

import Presentation.Guest.GuestMenu;
import Presentation.Owner.OwnerMenu;
import Presentation.SystemManager.SystemManagerMenu;
import Exception.*;

import javax.swing.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static DataBase.DBConnector.getConnection;
import static com.sun.glass.ui.Cursor.setVisible;

public class mainPresentation {
    public static void main(String[] args){

//        SystemManagerMenu systemManagerMenu=new SystemManagerMenu();
//        systemManagerMenu.showMenu();

//        GuestMenu guestMenu = new GuestMenu();
//        guestMenu.showMenu();



        /*
        try{

            Connection myConn = getConnection();
            Statement myStatement = myConn.createStatement();
            ResultSet rs = myStatement.executeQuery("select * from league");

            while (rs.next()){
                System.out.println(rs.getString("seasonsId"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
          */

        // systemManagerMenu.addSystemManager();
      //  systemManagerMenu.addAssociationDeligate();
       // systemManagerMenu.removeAssociationDeligate();


        OwnerMenu ownerMenu = new OwnerMenu();
        ownerMenu.showMenu();
    }
}

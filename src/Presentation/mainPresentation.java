package Presentation;

import DataBase.DBController;
import Domain.League.League;
import Domain.League.Season;
import Domain.Users.Role;
import Domain.Users.SystemManager;
import Presentation.Guest.GuestMenu;
import Presentation.SystemManager.SystemManagerMenu;
import Exception.*;
import Service.SystemController;

import javax.swing.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import static DataBase.DBConnector.getConnection;
import static com.sun.glass.ui.Cursor.setVisible;


import static DataBase.DBConnector.getConnection;

public class mainPresentation {


    public static void main(String[] args){
        SystemController controller = new SystemController("test DB");
        SystemManager systemManager;
        DBController dbc = DBController.getInstance();
        try {
            systemManager = (SystemManager)controller.logIn("admin@gmail.com","123");
            League league1 = new League("league");
            dbc.addLeague(systemManager,league1);

            League result = dbc.getLeague("league");
        } catch (PasswordDontMatchException e) {
            e.printStackTrace();
        } catch (MemberNotExist memberNotExist) {
            memberNotExist.printStackTrace();
        } catch (DontHavePermissionException e) {
            e.printStackTrace();
        } catch (ObjectNotExist objectNotExist) {
            objectNotExist.printStackTrace();
        } catch (AlreadyExistException e) {
            e.printStackTrace();
        }


//        try{
//            Connection myConn = getConnection();
//            Statement myStatement = myConn.createStatement();
//            ResultSet rs = myStatement.executeQuery("select * from League");
//
//            while (rs.next()){
//                System.out.println(rs.getString("seasonsId"));
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }


//        SystemManagerMenu systemManagerMenu=new SystemManagerMenu();
//        systemManagerMenu.showMenu();

       // GuestMenu guestMenu = new GuestMenu();
    //    guestMenu.showMenu();



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

    }
}

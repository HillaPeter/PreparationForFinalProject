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


        //************************shacharTest***************************//
        //        LeagueInSeason leagueInSeason=new LeagueInSeason(new League("league") , new Season("1669"));
//        LeagueInSeasonDao leagueInSeasonDao=LeagueInSeasonDao.getInstance();
//        leagueInSeasonDao.save(leagueInSeason);
//        System.out.println(leagueInSeasonDao.get(leagueInSeason.getLeague().getName()+":"+leagueInSeason.getSeason().getYear()));
//        System.out.println(leagueInSeasonDao.getAll());
//        System.out.println(leagueInSeasonDao.exist(leagueInSeason.getLeague().getName()+":"+leagueInSeason.getSeason().getYear()));
//        leagueInSeasonDao.delete(leagueInSeason.getLeague().getName()+":"+leagueInSeason.getSeason().getYear());
//        System.out.println(leagueInSeasonDao.exist(leagueInSeason.getLeague().getName()+":"+leagueInSeason.getSeason().getYear()));


//        Season season=new Season("1879");
//        SeasonDao seasonDao=SeasonDao.getInstance();
//        seasonDao.save(season);
//        System.out.println(seasonDao.get(season.getYear()));
//        System.out.println(seasonDao.getAll());
//        System.out.println(seasonDao.exist(season.getYear()));
//        seasonDao.delete(season.getYear());
//        System.out.println(seasonDao.exist(season.getYear()));

//        Owner owner=new Owner("shacahr", "meretz1@gmail.com", "123567" , new Date(1,1,1995));
//        OwnerDao ownerDao=OwnerDao.getInstance();
//        ownerDao.save(owner);
//        System.out.println(ownerDao.get(owner.getUserMail()));
//        System.out.println(ownerDao.getAll());
//        System.out.println(ownerDao.exist(owner.getUserMail()));
//        ownerDao.delete(owner.getUserMail());
//        System.out.println(ownerDao.exist(owner.getUserMail()));
//

//        Field field=new Field("shacahr");
//        FieldDao fieldDao=FieldDao.getInstance();
//        fieldDao.save(field);
//        System.out.println(fieldDao.get(field.getNameOfField()));
//        System.out.println(fieldDao.getAll());
//        System.out.println(fieldDao.exist(field.getNameOfField()));
//        fieldDao.delete(field.getNameOfField());
//        System.out.println(fieldDao.exist(field.getNameOfField()));



//         Coach coach=new Coach("shacahr", "meretz1@gmail.com", "123567" , new Date(1,1,1995));
//          CoachDao coachDao=CoachDao.getInstance();
//        coachDao.save(coach);
//           System.out.println(coachDao.get(coach.getUserMail()));
//        System.out.println(coachDao.getAll());
//        System.out.println(coachDao.exist(coach.getUserMail()));
//        coachDao.delete(coach.getUserMail());
//           System.out.println(coachDao.exist(coach.getUserMail()));

//        Manager manager=new Manager("shacahr", "meretz1@gmail.com", "123567" , new Date(1,1,1995));
//        ManagerDao managerDao=ManagerDao.getInstance();
//        managerDao.save(manager);
//        System.out.println(managerDao.get(manager.getUserMail()));
//        System.out.println(managerDao.getAll());
//        System.out.println(managerDao.exist(manager.getUserMail()));
//        managerDao.delete(manager.getUserMail());
//        System.out.println(managerDao.exist(manager.getUserMail()));


//        Player player=new Player("shacahr", "meretz1@gmail.com", "123567" , new Date(1,1,1995) , "player");
//        PlayerDao playerDao=PlayerDao.getInstance();
//        playerDao.save(player);
//        System.out.println(playerDao.get(player.getUserMail()));
//        System.out.println(playerDao.exist(player.getUserMail()));
//        playerDao.delete(player.getUserMail());
//        System.out.println(playerDao.exist(player.getUserMail()));
//



        // AssociationDelegate associationDelegate=new AssociationDelegate("shacahr", "meretz1@gmail.com", "123567" , new Date(1,1,1995),null);
        //  AssociationDeligateDao associationDeligateDao=AssociationDeligateDao.getInstance();
        //   associationDeligateDao.save(associationDelegate);
        //   System.out.println(associationDeligateDao.get(associationDelegate.getUserMail()));
        //  System.out.println(associationDeligateDao.exist(associationDelegate.getUserMail()));
        //     associationDeligateDao.delete(associationDelegate.getUserMail());
        //   System.out.println(associationDeligateDao.exist(associationDelegate.getUserMail()));

        //SystemManager systemManager=new SystemManager("shacahr", "123567" , "meretz@gmail.com"  , null , new Date(1,1,1995));
        // SystemManagerDao systemManagerDao=SystemManagerDao.getInstance();
        // systemManagerDao.save(systemManager);
        // List<String> str=systemManagerDao.getAll();
        // System.out.println(str.toString());
        //   systemManagerDao.update(systemManager.getUserMail() , systemManager);
        // System.out.println(systemManagerDao.exist(systemManager.getUserMail()));


        //  Fan fan=new Fan("shacahr", "meretz1@gmail.com", "123567" , new Date(1,1,1995),null);
        //   FanDao fanDao=FanDao.getInstance();
        //   fanDao.save(fan);
        //   System.out.println(fanDao.get(fan.getUserMail()));
        //  System.out.println(fanDao.exist(fan.getUserMail()));
        //    fanDao.delete(fan.getUserMail());
        //  System.out.println(fanDao.exist(fan.getUserMail()));



        //   MainReferee mainReferee=new MainReferee("shacahr", "meretz1@gmail.com", "123567" ,"temp"  , new Date(1,1,1995),null);
        //  MainRefereeDao mainRefereeDao=MainRefereeDao.getInstance();
        //   mainRefereeDao.save(mainReferee);
        //    System.out.println(mainRefereeDao.get(mainReferee.getUserMail()));
        //    System.out.println(mainRefereeDao.exist(mainReferee.getUserMail()));
        //   mainRefereeDao.delete(mainReferee.getUserMail());
        //   System.out.println(mainRefereeDao.exist(mainReferee.getUserMail()));


        //    SecondaryReferee league=new SecondaryReferee("shacahr", "meretz1@gmail.com", "123567" ,"temp"  , new Date(1,1,1995),null );
        //   SecondaryRefereeDao leagueDao=SecondaryRefereeDao.getInstance();
        //    leagueDao.save(league);
        //    System.out.println(leagueDao.get(league.getUserMail()));
        //     System.out.println(leagueDao.getAll().toString());
        //    System.out.println(leagueDao.exist("meretz1@gmail.com"));
        //      leagueDao.delete("meretz1@gmail.com");
        //    System.out.println(leagueDao.exist("meretz1@gmail.com"));



        // League league2=new League("league12");
        // leagueDao.save(league2);
        //  System.out.println(leagueDao.exist("league9"));
        //  leagueDao.delete("league11");
        //systemManagerDao.delete(systemManager.getUserMail());
    }
}

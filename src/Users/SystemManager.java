package Users;

import Asset.Coach;
import Asset.Manager;
import Asset.Player;
import Game.Account;
import Game.Team;
import League.Season;
import League.League;
import League.LeagueInSeason;
import javafx.util.Pair;
import system.SystemController;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class SystemManager extends Member {

    private SystemController SystemController;

    public SystemManager(String name, String userMail, String password, SystemController SystemController) {
        super(name, userMail, password);
        this.SystemController = SystemController;
    }

    public void viewSystemInformation() {
        //todo
    }
//
//    public void schedulingGames(String seasonId , String leagueId) {
//        League league=SystemController.getLeague(leagueId);
//        Season season=SystemController.getSeason(seasonId);
//        LeagueInSeason leagueInSeason=league.getLeagueInSeason(season);
//    }
//
//    public boolean removeReferee(String id) {
//        //shachar
//        //if the system manager want to delete the refree he delete it from the system
//        if (SystemController.existReferee(id) == false)
//            return false;
//        else {
//            Referee referee = SystemController.getReferee(id);
//            referee.deleteTheGames();
//            //6.המערכת משבצת מחדש את השופטים למשחקים ששופטיהם נמחקו
//            SystemController.deleteReferee(id);
//            return true;
//        }
//    }
//
//    public boolean addReferee(String id , boolean ifMainRefree) {
//        if(SystemController.existFan(id)==false)
//            return false;
//        else
//        {
//            SystemController.makeTheRoleAReferee(id ,ifMainRefree );
//            return true;
//        }
//    }
//
//    public boolean closeTeam(String teamName) {
//        //the team not exist
//        if (SystemController.existTeamName(teamName) == false)
//            return false;
//        else {//i can delete it
//            //המערכת מסירה את הקבוצה מכל שיבוצי המשחק שיש לה
//            Team team = SystemController.getTeam(teamName);
//            team.deleteTheData();
//            SystemController.deleteTeam(teamName);
//            return true;
//        }
//    }
//
//    public boolean removeMember(String id) {
//        if(SystemController.existRole(id)==false)
//        {
//            return false;
//        }
//        else
//        {
//            SystemController.deleteRole(id);
//            return true;
//        }
//    }
//
//    /**
//     *     this function return true if the team added and false if there were problem with the data
//     */
//    public boolean addNewTeam(LinkedList<String> idPlayers, LinkedList<String> idCoach, LinkedList<String> idManager, LinkedList<String> idOwner, String teamName) {
//
//        if (idPlayers.size() < 11 || alreadyIncludeThisTeamName(teamName) == true || notAllTheIdAreMembers(idPlayers, idCoach, idManager, idOwner) == true) {
//            return false;
//        } else {
//            LinkedList<Coach> coaches = makeCoachList(SystemController.returnFromSystemTheExactUsers(idCoach));
//            LinkedList<Player> players = makePlayerList(SystemController.returnFromSystemTheExactUsers(idPlayers));
//            LinkedList<Manager> managers = makeManagerList(SystemController.returnFromSystemTheExactUsers(idManager));
//            LinkedList<Owner> owners = makeOwnerList(SystemController.returnFromSystemTheExactUsers(idOwner));
//            Account account = new Account();
//            Team newTeam = new Team(account, players, coaches, managers, owners, teamName);
//            SystemController.addTeam(newTeam);
//            return true;
//        }
//    }
//
//    /*************************************** help function for addNewTeam******************************************/
//
//    private LinkedList<Coach> makeCoachList(LinkedList<Member> returnFromSystemTheExactUsers) {
//        LinkedList<Coach> newList = new LinkedList<>();
//        for (int i = 0; i < returnFromSystemTheExactUsers.size(); i++) {
//            newList.add((Coach) returnFromSystemTheExactUsers.get(i));
//        }
//        return newList;
//    }
//
//    private LinkedList<Player> makePlayerList(LinkedList<Member> returnFromSystemTheExactUsers) {
//        LinkedList<Player> newList = new LinkedList<>();
//        for (int i = 0; i < returnFromSystemTheExactUsers.size(); i++) {
//            newList.add((Player) returnFromSystemTheExactUsers.get(i));
//        }
//        return newList;
//    }
//
//    private LinkedList<Owner> makeOwnerList(LinkedList<Member> returnFromSystemTheExactUsers) {
//        LinkedList<Owner> newList = new LinkedList<>();
//        for (int i = 0; i < returnFromSystemTheExactUsers.size(); i++) {
//            newList.add((Owner) returnFromSystemTheExactUsers.get(i));
//        }
//        return newList;
//    }
//
//    private LinkedList<Manager> makeManagerList(LinkedList<Member> returnFromSystemTheExactUsers) {
//        LinkedList<Manager> newList = new LinkedList<>();
//        for (int i = 0; i < returnFromSystemTheExactUsers.size(); i++) {
//            newList.add((Manager) returnFromSystemTheExactUsers.get(i));
//        }
//        return newList;
//    }
//
//    private boolean notAllTheIdAreMembers(LinkedList<String> idPlayers, LinkedList<String> idCoach, LinkedList<String> idManager, LinkedList<String> idOwner) {
//        return SystemController.notAllTheIdAreMembers(idPlayers, idCoach, idManager, idOwner);
//
//    }
//
//    private boolean alreadyIncludeThisTeamName(String teamName) {
//
//        return SystemController.alreadyIncludeThisTeamName(teamName);
//    }
//
//    /*****************************************all the complaint function**************************************************/
//
//    public LinkedList<String> watchComplaint(String path) {
//        LinkedList<String> complaintList=readLineByLine(path);
//        return complaintList;
//    }
//
//    /***
//     *
//     * @param path
//     * @param response this hashMap represent - the number of the complaint and the response for the complain
//     * @return
//     */
//    public boolean ResponseComplaint(String path , LinkedList<Pair<String , String> >response) {
//        //this function get the linkes list after the manager added his response for the complaint
//        writeToFile(path,response);
//        return true;
//    }
//
//    private void writeToFile(String path, LinkedList<Pair<String , String> >response) {
//        try {
//            FileWriter fw = new FileWriter(path, true);
//            BufferedWriter bw = new BufferedWriter(fw);
//            for(int i=0; i<response.size(); i++) {
//                if(!response.get(i).getValue().equals("")) {
//                    bw.write(i + "." + response.get(i).getKey() + ". answer: " + response.get(i).getValue());
//                }
//                else
//                {
//                    bw.write(i + "." + response.get(i).getKey() + ".");
//                }
//            }
//            bw.close();
//        } catch (Exception e) {
//
//        }
//    }
//
//    public LinkedList<String> readLineByLine(String newPath) {
//        LinkedList<String> list = new LinkedList<>();
//        try {
//            File newText = new File(newPath);
//            String allText = new String();
//            Scanner scanner = new Scanner(newText);
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                list.add(line);
//
//            }
//            return list;
//        }
//        catch (Exception e)
//        {
//
//        }
//        return list;
//    }

    }

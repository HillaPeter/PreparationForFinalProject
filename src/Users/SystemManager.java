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
import system.DBController;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import Exception.*;


public class SystemManager extends Member {

    private DBController dbController;

    public SystemManager(String name, String userMail, String password , DBController dbController) {
        super(name, userMail, password);
        this.dbController=dbController;
    }

    private boolean inputAreLegal(String refreeId) {
        if (refreeId.contains("@") && refreeId.contains(".")) {
            return true;
        }
        return false;
    }

    public boolean removeAssociationDelegate(String id)
    {
    try {
        if (dbController.existAssociationDelegate(id)) {
            if (inputAreLegal(id)) {

                    dbController.deleteAssociationDelegate(id);

            } else {
                throw new IncorrectInputException();
            }
        } else {
            throw new MemberNotExist();
        }
    } catch (Exception e) {

    }
        return false;
}

    public boolean removeOwner(String ownerId) {
        try {
            if (dbController.existOwner(ownerId)) {
                if (inputAreLegal(ownerId)) {
                    Owner owner = (Owner) dbController.getMember(ownerId);
                    if(owner.haveTeams()) {
                        dbController.deleteOwner(ownerId);
                        return true;
                    }
                    else
                    {
                        throw new NotReadyToDelete("this owner ows teams. you must close the team before");
                    }
                } else {
                    throw new IncorrectInputException();
                }
            } else {
                throw new MemberNotExist();
            }
        } catch (Exception e) {

        }
        return false;
    }

    public void viewSystemInformation(String path) {
        print(readLineByLine(path));
    }

    private void print(LinkedList<String> readLineByLine) {
        for(int i=0; i<readLineByLine.size(); i++)
        {
            System.out.println(readLineByLine.get(i));
        }
    }

    public void schedulingGames(String seasonId, String leagueId) throws ObjectNotExist {
        League league = dbController.getLeague(leagueId);
        Season season = dbController.getSeason(seasonId);
        LeagueInSeason leagueInSeason = league.getLeagueInSeason(season);
        LinkedList<Team> teams=leagueInSeason.getTeams();
        //sceduling game for teams
    }

    public boolean removeSystemManager(String id) {
        try {
            if (dbController.existSystemManager(id)) {
                if (inputAreLegal(id)) {
                    if(dbController.getSystemManagers().size()>1) {
                        dbController.deleteSystemManager(id);
                        return true;
                    }
                    else
                    {
                        throw new NotReadyToDelete("this is the only system manager in the system. you can't delete him");
                    }
                } else {
                    throw new IncorrectInputException();
                }
            } else {
                throw new MemberNotExist();
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean removeReferee(String id) {
        try {
            if (dbController.existReferee(id)) {
                if (inputAreLegal(id)) {
                    Referee referee = (Referee) dbController.getMember(id);
                    referee.deleteTheGames();
                    //6.המערכת משבצת מחדש את השופטים למשחקים ששופטיהם נמחקו
                    dbController.deleteReferee(id);
                    return true;
                } else {
                    throw new IncorrectInputException();
                }
            } else {
                throw new MemberNotExist();
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean addReferee(String id, boolean ifMainRefree) {
        try {
            if (inputAreLegal(id)) {
                if (!dbController.existReferee(id)) {
                    if (dbController.existFan(id)) {
                        Fan fan = (Fan) dbController.getMember(id);
                        Referee referee = null;
                        if (ifMainRefree) {
                            referee = new MainReferee(fan);
                        } else {
                            referee = new SecondaryReferee(fan);
                        }
                        dbController.deleteFan(id);
                        dbController.addReferee(referee);
                        return true;
                    } else {
                        throw new MemberNotExist();
                    }
                } else {
                    throw new MemberAlreadyExistException();
                }
            } else {
                throw new IncorrectInputException();
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean closeTeam(String teamName) {
        try {
            if (dbController.existTeam(teamName)) {
                //המערכת מסירה את הקבוצה מכל שיבוצי המשחק שיש לה
                Team team = dbController.getTeam(teamName);
                team.deleteTheData();
                dbController.removeTeam(teamName);
                return true;
            } else {
                throw new ObjectNotExist("this team name is not exist");
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean removeMember(String id) {
        try {
            if (inputAreLegal(id)) {
                if (dbController.existMember(id)) {
                    dbController.deleteMember(id);
                    return true;
                } else {
                    throw new MemberNotExist();
                }
            } else {
                throw new IncorrectInputException();
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean addNewTeam(String teamName , String idOwner) {
        {

        }

        /**
         * this function return true if the team added and false if there were problem with the data
         */
    public boolean addNewTeam(LinkedList<String> idPlayers, LinkedList<String> idCoach, LinkedList<String> idManager, LinkedList<String> idOwner, String teamName) {
        try {
            if (idPlayers.size() < 11) {
                throw new IncorrectInputException();
            } else if (alreadyIncludeThisTeamName(teamName) == true) {
                throw new ObjectAlreadyExist();
            } else if (notAllTheIdAreMembers(idPlayers, idCoach, idManager, idOwner) == false) {
                throw new MemberNotExist();
            } else {
                LinkedList<Coach> coaches = makeCoachList(dbController.getMembers(idCoach));
                LinkedList<Player> players = makePlayerList(dbController.getMembers(idPlayers));
                LinkedList<Manager> managers = makeManagerList(dbController.getMembers(idManager));
                LinkedList<Owner> owners = makeOwnerList(dbController.getMembers(idOwner));
                Account account = new Account();
                Team newTeam = new Team(account, players, coaches, managers, owners, teamName);
          //      dbController.addTeam(newTeam);
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    /************* help function for addNewTeam****************/

    private boolean notAllTheIdAreMembers(LinkedList<String> idPlayers, LinkedList<String> idCoach, LinkedList<String> idManager, LinkedList<String> idOwner) {
        boolean check=allTheListAreMember(idPlayers);
        if(check==false)
        {
            return false;
        }
        check=allTheListAreMember(idCoach);
        if(check==false)
        {
            return false;
        }check=allTheListAreMember(idManager);
        if(check==false)
        {
            return false;
        }
        check=allTheListAreMember(idOwner);
        if(check==false)
        {
            return false;
        }
        return true;
    }

    private boolean allTheListAreMember(LinkedList<String> list)
    {
        for(int i=0; i<list.size(); i++)
        {
            if(dbController.existMember(list.get(i))==false)
            {
                return false;
            }
        }
        return true;
    }

    private boolean alreadyIncludeThisTeamName(String teamName) {

        return dbController.existTeam(teamName);
    }

    private LinkedList<Coach> makeCoachList(LinkedList<Member> returnFromSystemTheExactUsers) {
        LinkedList<Coach> newList = new LinkedList<>();
        for (int i = 0; i < returnFromSystemTheExactUsers.size(); i++) {
                newList.add((Coach) returnFromSystemTheExactUsers.get(i));
        }
        return newList;
    }

    private LinkedList<Player> makePlayerList(LinkedList<Member> returnFromSystemTheExactUsers) {
        LinkedList<Player> newList = new LinkedList<>();
        for (int i = 0; i < returnFromSystemTheExactUsers.size(); i++) {
                newList.add((Player) returnFromSystemTheExactUsers.get(i));
        }
        return newList;
    }

    private LinkedList<Owner> makeOwnerList(LinkedList<Member> returnFromSystemTheExactUsers) throws DontHavePermissionException {
        LinkedList<Owner> newList = new LinkedList<>();
        for (int i = 0; i < returnFromSystemTheExactUsers.size(); i++) {
                newList.add((Owner) returnFromSystemTheExactUsers.get(i));
        }
        return newList;
    }

    private LinkedList<Manager> makeManagerList(LinkedList<Member> returnFromSystemTheExactUsers) {
        LinkedList<Manager> newList = new LinkedList<>();
        for (int i = 0; i < returnFromSystemTheExactUsers.size(); i++) {
            newList.add((Manager) returnFromSystemTheExactUsers.get(i));
        }
        return newList;
    }

    /**************all the complaint function*****************/

    public LinkedList<String> watchComplaint(String path) {
        LinkedList<String> complaintList = readLineByLine(path);
        return complaintList;
    }

    /*
     *
     * @param path
     * @param response this hashMap represent - the number of the complaint and the response for the complain
     * @return
     */
    public boolean ResponseComplaint(String path, LinkedList<Pair<String, String>> response) {
        //this function get the linkes list after the manager added his response for the complaint
        writeToFile(path, response);
        return true;
    }

    private void writeToFile(String path, LinkedList<Pair<String, String>> response) {
        try {
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < response.size(); i++) {
                if (!response.get(i).getValue().equals("")) {
                    bw.write(i + "." + response.get(i).getKey() + ". answer: " + response.get(i).getValue());
                } else {
                    bw.write(i + "." + response.get(i).getKey() + ".");
                }
            }
            bw.close();
        } catch (Exception e) {

        }
    }

    public LinkedList<String> readLineByLine(String newPath) {
        LinkedList<String> list = new LinkedList<>();
        try {
            File newText = new File(newPath);
            String allText = new String();
            Scanner scanner = new Scanner(newText);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                list.add(line);

            }
            return list;
        } catch (Exception e) {
            System.out.print("the path is not legal");
        }
        return list;
    }

    /************* get function (noa) *************/
    public HashMap<String, Role> getRoles() throws DontHavePermissionException {
        return this.dbController.getRoles(this);
    }
    public HashMap<String, Team> getTeams() throws DontHavePermissionException {
        return this.dbController.getTeams(this);
    }
    /************* add function (noa) *************/
    public void addAssociationDelegate(String id) throws DontHavePermissionException, MemberNotExist, AlreadyExistException {
       if( this.dbController.getFans().containsKey(id)){
           Member member = (Member)this.dbController.getMember(id);
           AssociationDelegate newA_D = new AssociationDelegate(member.getName(),member.getUserMail(),member.getPassword());
           this.dbController.deleteRole(this,id);
           this.dbController.addAssociationDelegate(this,newA_D);
       }
    }
    public void addOwner(String id) throws DontHavePermissionException, MemberNotExist, AlreadyExistException {
        if( this.dbController.getFans().containsKey(id)){
            Member member = (Member)this.dbController.getMember(id);
            Owner newOwner = new Owner(member.getName(),member.getUserMail(),member.getPassword(),this.dbController);
            this.dbController.deleteRole(this,id);
            this.dbController.addOwner(this,newOwner);
        }
    }

    public void addSystemManager(String id) throws MemberNotExist, DontHavePermissionException, AlreadyExistException {
        if( this.dbController.getFans().containsKey(id)){
            Member member = (Member)this.dbController.getMember(id);
            SystemManager newSystemManager = new SystemManager(member.getName(),member.getUserMail(),member.getPassword(),this.dbController);
            this.dbController.deleteRole(this,id);
            this.dbController.addSystemManager(newSystemManager);
        }
    }



}
package Domain.Users;

import Domain.Asset.Coach;
import Domain.Asset.Manager;
import Domain.Asset.Player;
import Exception.*;
import Domain.Game.Account;
import Domain.Game.Game;
import Domain.Game.Team;
import Domain.League.ASchedulingPolicy;
import Domain.League.League;
import Domain.League.LeagueInSeason;
import Domain.League.Season;
import javafx.util.Pair;
import DataBase.DBController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;


public class SystemManager extends Member {

    private DBController dbController;

    public SystemManager(String name, String userMail, String password, DBController dbController, Date birthDate) {
        super(name, userMail, password, birthDate);
        this.dbController = dbController;
    }

    private boolean inputAreLegal(String refereeId) {
        if (refereeId.contains("@") && refereeId.contains(".")) {
            return true;
        }
        return false;
    }

    public boolean removeAssociationDelegate(String id) {
        try {
            if (dbController.existAssociationDelegate(this, id)) {
                if (inputAreLegal(id)) {
                    AssociationDelegate temp=dbController.getAssociationDelegate(this , id);
                    Fan newFan=new Fan(temp.getName(),temp.getUserMail(), temp.getPassword(), temp.getBirthDate());
                    dbController.deleteAssociationDelegate(this, id);
                    dbController.addFan(temp,newFan);
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

    public boolean removeOwner(String ownerId) throws IncorrectInputException, NotReadyToDelete, MemberNotExist, DontHavePermissionException {
            if (dbController.existOwner(this, ownerId)) {
                if (inputAreLegal(ownerId)) {
                    Owner owner = (Owner) dbController.getMember(this, ownerId);
                    if (owner.notHaveTeams() == true) {
                        dbController.deleteOwner(this, ownerId);
                        return true;
                    } else {
                        throw new NotReadyToDelete("this owner ows teams. you must close the team before");
                    }
                } else {
                    throw new IncorrectInputException();
                }
            } else {
                throw new MemberNotExist();
            }
        }
    public void viewSystemInformation(String path) {
        print(readLineByLine(path));
    }

    private void print(LinkedList<String> readLineByLine) {
        for (int i = 0; i < readLineByLine.size(); i++) {
            System.out.println(readLineByLine.get(i));
        }
    }

    public void schedulingGames(String seasonId, String leagueId) throws ObjectNotExist, DontHavePermissionException, IncorrectInputException {
        League league = dbController.getLeague(this, leagueId);
        Season season = dbController.getSeason(this, seasonId);
        LeagueInSeason leagueInSeason = league.getLeagueInSeason(season);
        LinkedList<Team> teams = leagueInSeason.getTeamsForScheduling();
        if(teams.size()%2!=0)
        {
            throw new IncorrectInputException("need to be even teams to scheduling");
        }
        else if(notEnoughReferee(leagueInSeason , teams.size())==false)
        {
            throw new IncorrectInputException("not enough refree for scheduling games");
        }
        else {
            ASchedulingPolicy schedulingPolicy = leagueInSeason.getPolicy();
            Set <Game> games=schedulingPolicy.setGamesOfTeams(teams, leagueInSeason);
            leagueInSeason.addGames(games);
            //dbController.addGames(this,games);
        }
    }

    private boolean notEnoughReferee( LeagueInSeason leagueInSeason , int numOfTeams) throws DontHavePermissionException {
        HashMap<String,Referee> refereeHashMap=leagueInSeason.getReferees();
       // if(refereeHashMap.size()<) {
       //     return false;
      //  }
        int counterMain=0;
        int counterSecondary=0;

        for (String referee:refereeHashMap.keySet()
             ) {
            if(refereeHashMap.get(referee) instanceof MainReferee)
            {
                counterMain++;
            }
            else if(refereeHashMap.get(referee) instanceof SecondaryReferee)
            {
                counterSecondary++;
            }
        }
        if (counterSecondary<numOfTeams/2)
        {
            return false;
        }
        if (counterMain<numOfTeams/2)
        {
            return false;
        }
        return true;
    }

    public boolean removeSystemManager(String id) throws MemberNotExist, IncorrectInputException, NotReadyToDelete, DontHavePermissionException {
        if (dbController.existSystemManager(this, id)) {
                if (inputAreLegal(id)) {
                    if (dbController.getSystemManagers(this).size() > 1 && !(this.getUserMail().equals(id))) {
                        dbController.deleteSystemManager(this, id);
                        return true;
                    } else {
                        throw new NotReadyToDelete("this is the only system manager in the system. you can't delete him");
                    }
                } else {
                    throw new IncorrectInputException();
                }
            } else {
                throw new MemberNotExist();
            }
        }


    public boolean removeReferee(String id) throws DontHavePermissionException, MemberNotExist, AlreadyExistException, IncorrectInputException {
        if (dbController.existReferee(this, id)) {
            if (inputAreLegal(id)) {
                Referee referee = (Referee) dbController.getMember(this, id);
                if(referee.hadGames())
                {
                    throw new IncorrectInputException("this referee has games to work in , you cant delete it");
                }
                else {
                    dbController.deleteReferee(this, id);
                    Fan newFan = new Fan(referee.getName(), referee.getUserMail(), referee.getPassword(), referee.getBirthDate());
                    dbController.addFan(this, newFan);
                    return true;
                }
            } else {
                throw new IncorrectInputException();
            }
        } else {
            throw new MemberNotExist();
        }
    }

    public boolean addReferee(String id, boolean ifMainRefree) throws IncorrectInputException, MemberAlreadyExistException, MemberNotExist, DontHavePermissionException, AlreadyExistException {
        if (inputAreLegal(id)) {
            if (!dbController.existReferee(this, id)) {
                if (dbController.existFan(this, id)) {
                    Fan fan = (Fan) dbController.getMember(this, id);
                    Referee referee = null;
                    if (ifMainRefree) {
                        referee = new MainReferee(fan);
                    } else {
                        referee = new SecondaryReferee(fan);
                    }
                    dbController.deleteFan(this, id);
                    dbController.addReferee(this, referee);
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
    }

    public boolean closeTeam(String teamName) throws DontHavePermissionException, ObjectNotExist, MemberNotExist, AlreadyExistException, IncorrectInputException {
        if (dbController.existTeam(this, teamName)) {
            Team team = dbController.getTeam(this, teamName);
            if(team.getGamesSize()==0) {
                HashSet<Owner> allTheOwnerOfTheGroup = team.deleteTheData();
                changeTheOwnerToFan(allTheOwnerOfTheGroup);
                dbController.removeTeam(this, teamName);
                return true;
            }
            else
            {
                throw new IncorrectInputException("this team have games , you cant close it");
            }
        } else {
            throw new ObjectNotExist("this team name is not exist");
        }
    }

    private void changeTheOwnerToFan(HashSet<Owner> allTheOwnerOfTheGroup) throws MemberNotExist, DontHavePermissionException, AlreadyExistException {
        for (Owner owner : allTheOwnerOfTheGroup
        ) {
            if (owner.getTeams().size() == 0) {
                Fan newFan = new Fan(owner.getName(), owner.getUserMail(), owner.getPassword(), owner.getBirthDate());
                dbController.deleteOwner(this, owner.getUserMail());
                dbController.addFan(this, newFan);
            }
        }
    }

    public boolean removeMember(String id) throws IncorrectInputException, DontHavePermissionException, MemberNotExist, AlreadyExistException, NotReadyToDelete {
            if (inputAreLegal(id)) {
                if (dbController.existMember(this, id)) {
                    Role role=dbController.getMember(this,id);
                    if(role instanceof Player)
                    {
                        ((Player)role).deleteAllTheData();
                    }
                    else if(role instanceof Coach)
                    {
                        ((Coach)role).deleteAllTheData();
                    }
                    else if(role instanceof Manager)
                    {
                        ((Manager)role).deleteAllTheData();
                    }
                    else if(role instanceof Owner)
                    {
                        removeOwner(((Owner) role).getUserMail());
                   //     removeFan(((Owner) role).getUserMail());
                    }
                    else if(role instanceof Referee)
                    {
                        removeReferee(((Referee) role).getUserMail());
                      //  removeFan(((Owner) role).getUserMail());
                    }
                    else if(role instanceof AssociationDelegate)
                    {
                        removeAssociationDelegate(((AssociationDelegate) role).getUserMail());
                       // removeFan(((AssociationDelegate) role).getUserMail());
                    }
                    else if(role instanceof SystemManager)
                    {
                        removeSystemManager(((Owner) role).getUserMail());
                       // removeFan(((SystemManager) role).getUserMail());
                    }
                    else if(role instanceof Fan)
                    {
                       // removeFan(((Fan) role).getUserMail());
                    }
                    dbController.deleteMember(this, id);
                    return true;
                } else {
                    throw new MemberNotExist();
                }
            } else {
                throw new IncorrectInputException("input are illegal");
            }
        }

    private void removeFan(String id) throws MemberNotExist, DontHavePermissionException {
        dbController.deleteFan(this,id);
    }


    /**
     * this function return true if the team added and false if there were problem with the data
     */
    public boolean addNewTeam(String teamName, String idOwner) throws ObjectAlreadyExist, ObjectNotExist, MemberNotExist, DontHavePermissionException, AlreadyExistException, IncorrectInputException {
        if (alreadyIncludeThisTeamName(teamName) == true) {
            throw new ObjectAlreadyExist();
        }
        else if(legalInputTeamName(teamName)==false)
        {
            throw new IncorrectInputException();
        }
        else if (dbController.existOwner(this, idOwner) == false && dbController.existFan(this, idOwner) == false) {
            throw new ObjectNotExist("the is you enter is not exist as owner of a team");
        } else {
            Owner owner = null;
            Role role = dbController.getMember(this, idOwner);
            if (role instanceof Fan) {//if its the first team for this owner
                owner = new Owner(role.getName(), ((Fan) role).getUserMail(), ((Fan) role).getPassword(), role.getBirthDate(), dbController);
                dbController.deleteFan(this, ((Fan) role).getUserMail());
                dbController.addOwner(this, owner);
            } else if (role instanceof Owner) {
                owner = (Owner) role;
            }
            if (owner != null) {
                Account account = new Account();
                Team newTeam = new Team(teamName, account, owner);
                owner.addTeam(newTeam);
                dbController.addTeam(this, newTeam);
            }
        }
        return false;
    }

    private boolean legalInputTeamName(String teamName) {
        int counter=0;
        for(int i=0; i<teamName.length(); i++)
        {
            if(teamName.charAt(i)>='0' && teamName.charAt(i)<='9')
                counter++;
        }
        if(counter==teamName.length())
            return false;

        return true;
    }


    /************* help function for addNewTeam****************/

    private boolean notAllTheIdAreMembers(LinkedList<String> idPlayers, LinkedList<String> idCoach, LinkedList<String> idManager, LinkedList<String> idOwner) throws DontHavePermissionException {
        boolean check = allTheListAreMember(idPlayers);
        if (check == false) {
            return false;
        }
        check = allTheListAreMember(idCoach);
        if (check == false) {
            return false;
        }
        check = allTheListAreMember(idManager);
        if (check == false) {
            return false;
        }
        check = allTheListAreMember(idOwner);
        if (check == false) {
            return false;
        }
        return true;
    }

    private boolean allTheListAreMember(LinkedList<String> list) throws DontHavePermissionException {
        for (int i = 0; i < list.size(); i++) {
            if (dbController.existMember(this, list.get(i)) == false) {
                return false;
            }
        }
        return true;
    }

    private boolean alreadyIncludeThisTeamName(String teamName) throws DontHavePermissionException {

        return dbController.existTeam(this, teamName);
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
        if (this.dbController.getFans(this).containsKey(id)) {
            Member member = (Member) this.dbController.getMember(this, id);
            AssociationDelegate newA_D = new AssociationDelegate(member.getName(), member.getUserMail(), member.getPassword(), member.getBirthDate(), dbController);
            this.dbController.deleteRole(this, id);
            this.dbController.addAssociationDelegate(this, newA_D);
        }
    }

    public void addOwner(String id) throws DontHavePermissionException, MemberNotExist, AlreadyExistException {
        if (this.dbController.getFans(this).containsKey(id)) {
            Member member = (Member) this.dbController.getMember(this, id);
            Owner newOwner = new Owner(member.getName(), member.getUserMail(), member.getPassword(), member.getBirthDate(), this.dbController);
            this.dbController.deleteRole(this, id);
            this.dbController.addOwner(this, newOwner);
        }
    }

    public void addSystemManager(String id) throws MemberNotExist, DontHavePermissionException, AlreadyExistException {
        if (this.dbController.getFans(this).containsKey(id)) {
            Member member = (Member) this.dbController.getMember(this, id);
            SystemManager newSystemManager = new SystemManager(member.getName(), member.getUserMail(), member.getPassword(), this.dbController, member.getBirthDate());
            this.dbController.deleteRole(this, id);
            this.dbController.addSystemManager(this, newSystemManager);
        }
    }


    public HashSet<Game> getGames(String league, String season) throws ObjectNotExist {
        return this.dbController.getGames(league,season);
    }
}
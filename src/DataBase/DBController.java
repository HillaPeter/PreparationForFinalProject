package DataBase;

import Domain.Asset.Coach;
import Domain.Asset.Field;
import Domain.Asset.Manager;
import Domain.Asset.Player;
import Domain.Game.Event;
import Domain.League.*;
import Domain.Users.*;
import Exception.AlreadyExistException;
import Exception.DontHavePermissionException;
import Exception.MemberNotExist;
import Exception.ObjectNotExist;
import Domain.Game.Game;
import Domain.Game.Team;

import java.util.*;

public class DBController implements DAO{

    private DB db;
    private DAOTEMP dao;

    private static final DBController instance = new DBController();

    public static DBController getInstance(){
        return instance;
    }

    private DBController() {
        this.db = new DB();
    }
    /***************************************Presentation.Guest function******************************************/


    /***************************************Getters******************************************/


    public HashMap<String, Team> getTeams()  {
        //dao=TeamDao.getInstance();
        List<Team> list=dao.getAll();
        HashMap<String , Team> ans=new HashMap<>();
        for (int i=0; i<list.size(); i++)
        {
            ans.put(list.get(i).getName() , list.get(i));
        }
           return ans;
    }

    public HashMap<String, League> getLeagues() {
        this.dao = LeagueDao.getInstance();
        List<String> allLeagues = dao.getAll();
        HashMap<String,League> leagues = new HashMap<>();
        for(String leagueString : allLeagues){
            String[] leagueDetails =leagueString.split(":");
            League league = parseLeague(leagueDetails);
            leagues.put(league.getName(),league);
        }
        return leagues;
    }

    public HashMap<String, Referee> getReferees() {
       // dao=UserDao.getInstance();
        List<Member> list=dao.getAll();
        HashMap<String , Referee> ans=new HashMap<>();
        for (int i=0; i<list.size(); i++)
        {
            if(list.get(i) instanceof  Referee)
            ans.put(list.get(i).getName() , (Referee)list.get(i));
        }
        return ans;
    }

    public HashMap<String, Role> getRoles()  {
        return db.getRoles(); }

    public HashMap<String, ASchedulingPolicy> getSchedulingPolicies()   {
        return db.getSchedulingPolicies();
    }

    public HashMap<String, Fan> getFans()   {
            return db.getFans();
    }

    public HashMap<String, Player> getPlayers()   {
            return db.getPlayers();
    }

    public HashMap<String, Owner> getOwners()   {
            return db.getOwners();
    }

    public HashMap<String, Manager> getManagers()   {
            return db.getManagers();
    }

    public HashMap<String, Coach> getCoaches()  {
            return db.getCoaches();
    }

    public HashMap<String, Member> getMembers()  {
            return db.getMembers();
    }

    public HashMap<String, Season> getSeasons()   {
        this.dao = SeasonDao.getInstance();
        List<String> allSeasons = dao.getAll();
        HashMap<String,Season> seasons = new HashMap<>();
        for(String seasonString : allSeasons){
            String[] seasonDetails =seasonString.split(":");
            Season season = parseSeason(seasonDetails);
            seasons.put(season.getYear(),season);
        }
        return seasons;
    }

    public HashMap<String, SystemManager> getSystemManagers()   {
            return db.getSystemManagers();
    }

    public HashMap<String, AssociationDelegate> getAssociationDelegate()   {
        return db.getAssociationDelegate();
    }

    /****************************get with id*****************************************/

    public SystemManager getSystemManagers(String id)   {

            return db.getSystemManagers(id);
    }

    public AssociationDelegate getAssociationDelegate( String id)   {
            return db.getAssociationDelegate(id);
    }

    public Role getMember( String id) throws MemberNotExist {
            if (db.existMember(id)) {
                return db.getMember(id);
            } else if (db.existSystemManager(id)) {
                return db.getSystemManager(id);
            } else {
                throw new MemberNotExist();
            }
        }

    public Team getTeam(String teamName) throws ObjectNotExist {
            if (dao.exist(teamName)) {
                //dao=TeamDao.getInstance();
               return null;// (Team) dao.get(teamName);
            } else {
                throw new ObjectNotExist("the team id is not exist");
            }
        }

    public League getLeague(String leagueId) throws ObjectNotExist {
        dao=LeagueDao.getInstance();
        if(dao.exist(leagueId)){
            String leagueString = dao.get(leagueId);
            String[] splited = leagueString.split(":");
            League league = parseLeague(splited);
            return league;
        } else {
            throw new ObjectNotExist("the league id is not exist");
        }
    }

    public Season getSeason(String seasonId) throws ObjectNotExist {
        dao=SeasonDao.getInstance();
        if(dao.exist(seasonId)){
            String SeasonString = dao.get(seasonId);
            String[] splited = SeasonString.split(":");
            Season season = parseSeason(splited);
            return season;
        } else {
            throw new ObjectNotExist("the season id is not exist");
        }
    }

    public Fan getFan(String id) throws MemberNotExist {
        return db.getFan(id);
    }

    public Referee getReferee(String s) throws MemberNotExist {
            if (db.existRefree(s)) {
                return db.getReferee(s);
            }
            else {
                throw new MemberNotExist();
            }
    }

    public LinkedList<Member> getMembers(LinkedList<String> idMember) throws MemberNotExist {
            LinkedList<Member> list = new LinkedList<>();
            for (int i = 0; i < idMember.size(); i++) {
                if (!db.existMember(idMember.get(i))) {
                    throw new MemberNotExist();
                }
                Member member = db.getMember(idMember.get(i));
                list.add(member);
            }
            return list;
        }

    public HashMap<String, Role> getOwnersAndFans() {
            return db.getOwnersAndFans();
        }

    public Owner getOwner(String idOwner) throws MemberNotExist {
            if (db.existOwner(idOwner)) {
                Owner owner = db.getOwnerOrFan(idOwner);
                owner.setDb(this);
                return owner;
            } else {
                throw new MemberNotExist();
            }
        }

    public HashSet<Game> getGames(String league, String season) throws ObjectNotExist {
        dao = GameDao.getInstance();
        List<String> gamesStrings = dao.getAll();
        HashSet<Game> games = new HashSet<>();
        for(String game : gamesStrings){
            String[] splited = game.split(":");
            if(splited[8].equals(league) && splited[9].equals(season)) {
                League league1 = getLeague(splited[8]);
                LeagueInSeason leagueInSeason = league1.getLeagueInSeason(getSeason(splited[9]));
                Game newGame = parseGame(splited , leagueInSeason);
                games.add(newGame);
            }
        }
        return games;
    }

    /***************************************delete function function******************************************/

    public void deleteRole(Role role, String id) throws MemberNotExist, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner || role instanceof AssociationDelegate) {
            if (!db.existMember(id))
                throw new MemberNotExist();
            db.removeRole(id);
            return;
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void deleteReferee(Role role, String id) throws DontHavePermissionException, MemberNotExist {
        if (role instanceof SystemManager || role instanceof MainReferee || role instanceof SecondaryReferee) {
            if (db.existRefree(id)) {
                db.removeRole(id);
            } else {
                throw new MemberNotExist();

            }
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void deleteFan(Role role, String id) throws MemberNotExist, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner || role instanceof Fan) {
            if (db.existFan(id)) {
                db.removeRole(id);
            } else {
                throw new MemberNotExist();
            }
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void deleteMember(Role role, String id) throws MemberNotExist, DontHavePermissionException {

        if (role instanceof SystemManager || role instanceof Owner) {
            if (db.existMember(id)) {
                db.removeRole(id);
            } else {
                throw new MemberNotExist();
            }
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void removeLeague(Role role, String leagueName) throws ObjectNotExist, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof AssociationDelegate) {
            if (!db.existLeague(leagueName))
                throw new ObjectNotExist("");
            db.removeLeague(leagueName);
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void removeSeason(Role role, String year) throws ObjectNotExist, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof AssociationDelegate) {
            if (!db.existSeason(year))
                throw new ObjectNotExist("");
            db.removeSeason(year);
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void removeTeam(Role role, String name) throws ObjectNotExist, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner) {

            if (!db.existTeam(name))
                throw new ObjectNotExist("");
            db.removeTeam(name);
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void deleteOwner(Role role, String ownerId) throws DontHavePermissionException, MemberNotExist {
        if (role instanceof SystemManager || role instanceof Owner) {
            if (db.existOwner(ownerId)) {
                db.removeRole(ownerId);
            } else {
                throw new MemberNotExist();
            }
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void deleteAssociationDelegate(Role role, String id) throws MemberNotExist, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof AssociationDelegate) {
            if (db.existAssociationDelegate(id)) {
                db.removeAssociationDelegate(id);
            } else {
                throw new MemberNotExist();
            }
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void deleteSystemManager(Role role, String id) throws MemberNotExist, DontHavePermissionException {
        if (role instanceof SystemManager) {
            if (db.existSystemManager(id)) {
                db.removeSystemManager(id);
            } else {

                throw new MemberNotExist();
            }
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void deleteAll() {
        db.deleteAll();
    }

    /***************************************add function******************************************/
    public void addAssociationDelegate(Role role, AssociationDelegate associationDelegate) throws
            DontHavePermissionException, AlreadyExistException {
        if (role instanceof SystemManager) {
            if (db.existMember(associationDelegate.getUserMail()))
                throw new AlreadyExistException();
            db.addAssociationDelegate(associationDelegate);
            return;
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void addFan(Role role, Fan fan) throws AlreadyExistException, DontHavePermissionException {
        // if (!(role instanceof Presentation.Guest || role instanceof Fan) {
        //      throw new DontHavePermissionException();
        //  }
        if (db.existMember(fan.getUserMail()))
            throw new AlreadyExistException();
        db.addFan(fan);
    }

    public void addSeason(Role role, Season season) throws AlreadyExistException, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof AssociationDelegate) {
            // if (id in AssTable || id in SystemManager)
            if (db.existSeason(season.getYear()))
                throw new AlreadyExistException();
            db.addSeason(season);

        } else {
            throw new DontHavePermissionException();
        }
    }

    public void addLeague(Role role, League league) throws AlreadyExistException, DontHavePermissionException {

        if (role instanceof SystemManager || role instanceof AssociationDelegate) {
            if (db.existLeague(league.getName()))
                throw new AlreadyExistException();
            db.addLeague(league);
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void addManager(Role role, Manager manager) throws AlreadyExistException, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner) {

            if (db.existMember(manager.getUserMail()))
                throw new AlreadyExistException();
            db.addManager(manager);
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void addPlayer(Role role, Player player) throws AlreadyExistException, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner) {

            if (db.existMember(player.getUserMail()))
                throw new AlreadyExistException();
            db.addPlayer(player);
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void addCoach(Role role, Coach coach) throws AlreadyExistException, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner) {

            if (db.existMember(coach.getUserMail()))
                throw new AlreadyExistException();
            db.addCoach(coach);
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void addOwner(Role role, Owner owner) throws AlreadyExistException, DontHavePermissionException {

        if (!(role instanceof SystemManager || role instanceof Owner)) {
            throw new DontHavePermissionException();
        }
        if (db.existMember(owner.getUserMail()))
            throw new AlreadyExistException();
        db.addOwner(owner);
    }

    public void addSystemManager(Role role, SystemManager systemManager) throws AlreadyExistException, DontHavePermissionException {
        if (role instanceof SystemManager) {

            if (db.existMember(systemManager.getUserMail()))
                throw new AlreadyExistException();
            db.addSystemManager(systemManager);
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void addTeam(Role role, Team team) throws AlreadyExistException, DontHavePermissionException {
        if (role instanceof SystemManager || role instanceof Owner) {

            if (db.existTeam(team.getName()))
                throw new AlreadyExistException();
            db.addTeam(team);
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void addReferee(Role role, Referee referee) throws DontHavePermissionException, AlreadyExistException {
        if (role instanceof SystemManager || role instanceof MainReferee || role instanceof SecondaryReferee) {
            if (!db.existRefree(referee.getUserMail())) {
                db.addReferee(referee);
            } else {
                throw new AlreadyExistException();
            }
        } else {
            throw new DontHavePermissionException();
        }
    }

    public void addSchedulingPolicies(Role role, ASchedulingPolicy policy) throws DontHavePermissionException {
        if (role instanceof AssociationDelegate || role instanceof Owner || role instanceof SystemManager) {
            db.addSchedulingPolicies(policy);
        } else {
            throw new DontHavePermissionException();
        }
    }

    /********************************************exist function***********************************/
    public boolean existReferee( String refereeId) {
            return db.existRefree(refereeId);
        }

    public boolean existFan(String fanId) {
            return db.existFan(fanId);
        }

    public boolean existTeam( String teamName) {
            return db.existTeam(teamName);
    }

    public boolean existMember(String id)  {
            return db.existMember(id);
    }

    public boolean existAssociationDelegate(String id)  {

            return db.existAssociationDelegate(id);
        }

    public boolean existSystemManager( String id)  {
        return db.existSystemManager(id);
    }

    public boolean existOwner( String ownerId)  {

            return db.existOwner(ownerId);
        }

    public boolean existSeason(String id) {
        return db.existSeason(id);
    }


    private LeagueInSeason parseLeagueInSeason(String leagueInSeasonString, League league ,Season season) {
        String[] splited = leagueInSeasonString.split(":");
        LeagueInSeason leagueInSeason = new LeagueInSeason(league,season);

        /*add teams*/
        String[] teamsString = splited[2].split("---");
        for(String teamS : teamsString){
            try {
                leagueInSeason.addTeam(getTeam(teamS));
            } catch (ObjectNotExist objectNotExist) {
                objectNotExist.printStackTrace();
            } catch (AlreadyExistException e) {
                e.printStackTrace();
            }
        }

        /*add referees*/
        String[] refereeString = splited[3].split("---");
        for(String refereeS : refereeString){
            try {
                leagueInSeason.addReferee(refereeS , getReferee(refereeS));
            } catch (MemberNotExist memberNotExist) {
                memberNotExist.printStackTrace();
            }
        }

        /*add games*/
        String[] gamesString = splited[4].split("---");
        HashSet<Game> games = new HashSet<>();
        for(String gameS : gamesString){
            Game game = parseGame(gameS.split("-"),leagueInSeason);
            games.add(game);
        }
        leagueInSeason.addGames(games);

        /*add ScorePolicy*/
        String[] scoreP = splited[5].split("---");
        double winning = Double.parseDouble(scoreP[0]);
        double draw = Double.parseDouble(scoreP[1]);
        double losing = Double.parseDouble(scoreP[2]);
        ScorePolicy scorePolicy = new ScorePolicy(winning, draw, losing);
        leagueInSeason.setScorePolicy(scorePolicy);

        /*add SchedulingPolicy*/
        ASchedulingPolicy schedulingPolicy = null;
        if (splited[6].equals("All teams play each other twice")) {
            schedulingPolicy = new SchedulingPolicyAllTeamsPlayTwice();
        } else if (splited[6].equals("All teams play each other once")) {
            schedulingPolicy = new SchedulingPolicyAllTeamsPlayOnce();
        }
        leagueInSeason.setSchedulingPolicy(schedulingPolicy);

        return leagueInSeason;
    }
    private Season parseSeason(String[] splitedSeasonString) {
        Season season = new Season(splitedSeasonString[0]);
        for(int i=1 ; i< splitedSeasonString.length ; i++){
            League league = new League(splitedSeasonString[i]);
            String leagueInSeasonString = LeagueInSeasonDao.getInstance().get(splitedSeasonString[i]+":"+splitedSeasonString[0]);
            LeagueInSeason leagueInSeason = parseLeagueInSeason(leagueInSeasonString,league,season);
            league.addLeagueInSeason(leagueInSeason);
            season.addLeagueInSeason(leagueInSeason);
        }
        return season;
    }
    private League parseLeague(String[] splitedLeagueString) {
        League league = new League(splitedLeagueString[0]);
        for(int i=1 ; i< splitedLeagueString.length ; i++){
            Season season = new Season(splitedLeagueString[i]);
            String leagueInSeasonString = LeagueInSeasonDao.getInstance().get(splitedLeagueString[0]+":"+splitedLeagueString[i]);
            LeagueInSeason leagueInSeason = parseLeagueInSeason(leagueInSeasonString,league,season);
            league.addLeagueInSeason(leagueInSeason);
            season.addLeagueInSeason(leagueInSeason);
        }
        return league;

    }
    private Game parseGame(String[] splited, LeagueInSeason leagueInSeason){
        Calendar dateAndTime = getCalander(splited[1]);
        try {
            Team hostTeam = getTeam(splited[2]);
            Team visitorTeam = getTeam(splited[3]);
            Field field = new Field(splited[4]);
            MainReferee mainReferee;
            SecondaryReferee secondReferee;
            mainReferee= (MainReferee) getReferee(splited[5]);
            secondReferee = (SecondaryReferee)getReferee(splited[6]);
            Game newGame = new Game(splited[0],dateAndTime,hostTeam,visitorTeam,field,mainReferee,secondReferee,leagueInSeason);
            newGame.addEvents(splited[6]);
            newGame.setResult(splited[5]);
            return newGame;
        } catch (ObjectNotExist objectNotExist) {
        }catch (MemberNotExist memberNotExist) { }
        return null;
    }
    private Calendar getCalander(String calander){
        String[] dateTime = calander.split(",");
        int year = Integer.parseInt(dateTime[0]);
        int mounth = Integer.parseInt(dateTime[1]);
        int dayOfMonth = Integer.parseInt(dateTime[2]);
        int hourOfDay = Integer.parseInt(dateTime[3]);
        int minute = Integer.parseInt(dateTime[4]);
        int second = Integer.parseInt(dateTime[5]);
        return new GregorianCalendar(year, mounth, dayOfMonth, hourOfDay, minute, second);
    }
}

package Users;

import Exception.*;
import Asset.*;
import Game.Account;
import Game.Team;
import system.DBController;


import java.util.*;

public class Owner extends Member {
    private HashMap<String, Team> teams;
    private DBController dbController;

    public Owner(String name, String userMail, String password,DBController dbController) throws DontHavePermissionException {
        super(name, userMail, password);
        this.dbController=dbController;
        teams=new HashMap<>();
        for(String teamName:dbController.getTeams(this).keySet()){
            Team team=dbController.getTeams(this).get(teamName);
            //todo:check if works!
            if(team.getOwners().contains(this)){
                teams.put(teamName,team);
            }
        }
    }

    public void addTeam(Team team) throws AlreadyExistException {
        dbController.addTeam(team);
    }

    public void removeTheTeamFromMyList(String name) throws ObjectNotExist {
        dbController.removeTeam(name);
    }

    /******************************Add Asset- Manage, Coach, Player, Field************************************/

    /**
     * add a new Manager team to team belong to owner
     *
     * @param teamName,mailId
     */
    public void addManager(String teamName, String mailId) throws MemberNotExist, AlreadyExistException, DontHavePermissionException {
        Team team = teams.get(teamName);

        Account account = team.getAccount();
        //set amount
        account.setAmountOfTeam(account.getAmountOfTeam() - 0);

        HashMap<String, Role> roles = dbController.getRoles(this);
        Role role = roles.get(mailId);
        Manager manager;
        if (role instanceof SystemManager) {
            SystemManager systemManager = (SystemManager) role;
            manager = new Manager(systemManager.getName(), systemManager.getUserMail(), systemManager.getPassword());
            dbController.deleteRole(this,mailId);
            dbController.addManager(manager);
            team.addManager(manager);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof Player) {
            Player player = (Player) role;
            manager = new Manager(player.getName(), player.getUserMail(), player.getPassword());
            dbController.deleteRole(this,mailId);
            dbController.addManager(manager);
            team.addManager(manager);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof Coach) {
            Coach coach = (Coach) role;
            manager = new Manager(coach.getName(), coach.getUserMail(), coach.getPassword());
            dbController.deleteRole(this, mailId);
            dbController.addManager(manager);
            team.addManager(manager);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof Fan) {
            Fan fan = (Fan) role;
            manager = new Manager(fan.getName(), fan.getUserMail(), fan.getPassword());
            dbController.deleteRole(this,mailId);
            dbController.addManager(manager);
            team.addManager(manager);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof AssociationDelegate) {
            AssociationDelegate associationDelegate = (AssociationDelegate) role;
            manager = new Manager(associationDelegate.getName(), associationDelegate.getUserMail(), associationDelegate.getPassword());
            dbController.deleteRole(this,mailId);
            dbController.addManager(manager);
            team.addManager(manager);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof Referee) {
            Referee referee = (Referee) role;
            manager = new Manager(referee.getName(), referee.getUserMail(), referee.getPassword());
            dbController.deleteRole(this,mailId);
            dbController.addManager(manager);
            team.addManager(manager);
            dbController.getTeams(this).replace(teamName,team);
        }


    }

    /**
     * add a new coach team to team belong to owner
     *
     * @param teamName,mailId
     */
    public void addCoach(String teamName, String mailId) throws MemberNotExist, AlreadyExistException, DontHavePermissionException {
        Team team =  teams.get(teamName);

        Account account = team.getAccount();
        //set amount
        account.setAmountOfTeam(account.getAmountOfTeam() - 0);

        HashMap<String, Role> roles = dbController.getRoles(this);
        Role role = roles.get(mailId);
        Coach coach;
        if (role instanceof SystemManager) {
            SystemManager systemManager = (SystemManager) role;
            coach = new Coach(systemManager.getName(), systemManager.getUserMail(), systemManager.getPassword());
            dbController.deleteRole(this,mailId);
            dbController.addCoach(coach);
            team.addCoach(coach);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof Manager) {
            Manager manager = (Manager) role;
            coach = new Coach(manager.getName(), manager.getUserMail(), manager.getPassword());
            dbController.deleteRole(this,mailId);
            dbController.addCoach(coach);
            team.addCoach(coach);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof Player) {
            Player player = (Player) role;
            coach = new Coach(player.getName(), player.getUserMail(), player.getPassword());
            dbController.deleteRole(this,mailId);
            dbController.addCoach(coach);
            team.addCoach(coach);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof Fan) {
            Fan fan = (Fan) role;
            coach = new Coach(fan.getName(), fan.getUserMail(), fan.getPassword());
            dbController.deleteRole(this,mailId);
            dbController.addCoach(coach);
            team.addCoach(coach);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof AssociationDelegate) {
            AssociationDelegate associationDelegate = (AssociationDelegate) role;
            coach = new Coach(associationDelegate.getName(), associationDelegate.getUserMail(), associationDelegate.getPassword());
            dbController.deleteRole(this, mailId);
            dbController.addCoach(coach);
            team.addCoach(coach);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof Referee) {
            Referee referee = (Referee) role;
            coach = new Coach(referee.getName(), referee.getUserMail(), referee.getPassword());
            dbController.deleteRole(this,mailId);
            dbController.addCoach(coach);
            team.addCoach(coach);
            dbController.getTeams(this).replace(teamName,team);
        }
        //todo: owner??

    }

    /**
     * add a new player team to team belong to owner
     *
     * @param teamName,mailId
     */
    public void addPlayer(String teamName, String mailId,int year, int month, int day, String roleInPlayers) throws MemberNotExist, AlreadyExistException, DontHavePermissionException {
        Team team =  teams.get(teamName);

        Account account = team.getAccount();
        //set amount
        account.setAmountOfTeam(account.getAmountOfTeam() - 0);

        HashMap<String, Role> roles = dbController.getRoles(this);
        Role role = roles.get(mailId);
        Player player;
        Date date = new Date(year, month, day);

        if (role instanceof SystemManager) {
            SystemManager systemManager = (SystemManager) role;
            player = new Player(systemManager.getName(), systemManager.getUserMail(), systemManager.getPassword(), date, roleInPlayers);
            dbController.deleteRole(this,mailId);
            dbController.addPlayer(player);
            team.addPlayer(player);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof Manager) {
            Manager manager = (Manager) role;
            player = new Player(manager.getName(), manager.getUserMail(), manager.getPassword(), date, roleInPlayers);
            dbController.deleteRole(this,mailId);
            dbController.addPlayer(player);
            team.addPlayer(player);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof Coach) {
            Coach coach = (Coach) role;
            player = new Player(coach.getName(), coach.getUserMail(), coach.getPassword(), date, roleInPlayers);
            dbController.deleteRole(this,mailId);
            dbController.addPlayer(player);
            team.addPlayer(player);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof Fan) {
            Fan fan = (Fan) role;
            player = new Player(fan.getName(), fan.getUserMail(), fan.getPassword(), date, roleInPlayers);
            dbController.deleteRole(this,mailId);
            dbController.addPlayer(player);
            team.addPlayer(player);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof AssociationDelegate) {
            AssociationDelegate associationDelegate = (AssociationDelegate) role;
            player = new Player(associationDelegate.getName(), associationDelegate.getUserMail(), associationDelegate.getPassword(), date, roleInPlayers);
            dbController.deleteRole(this,mailId);
            dbController.addPlayer(player);
            team.addPlayer(player);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof Referee) {
            Referee referee = (Referee) role;
            player = new Player(referee.getName(), referee.getUserMail(), referee.getPassword(), date, roleInPlayers);
            dbController.deleteRole(this,mailId);
            dbController.addPlayer(player);
            team.addPlayer(player);
            dbController.getTeams(this).replace(teamName,team);
        }
        //todo: owner??

    }

    /**
     * add a field to team asset
     *
     * @param teamName
     */
    public void addField(String teamName,  String fieldName) throws DontHavePermissionException {
        Team team = teams.get(teamName);
        Field field = new Field(fieldName);
        team.addField(field);
        HashSet<Field> trainingFields = dbController.getTeams(this).get(teamName).getTrainingFields();
        trainingFields.add(field);
        dbController.getTeams(this).get(teamName).setTrainingFields(trainingFields);
    }

    /******************************Remove Asset- Manage, Coach, Player, Field************************************/

    /**
     * remove manager from team and make him fan
     *
     * @param teamName
     * @param mailInput
     */
    public void removeManager(String teamName, String mailInput) throws MemberNotExist, AlreadyExistException, DontHavePermissionException {
        //gets the team
        Team team = teams.get(teamName);
        //gets the list of managers
        HashSet<Manager> managers = team.getManagers();
        for (Manager manager : managers) {
            //found the manager to remove
            if (manager.getUserMail().equals(mailInput)) {
                Fan fan = new Fan(manager.getName(), manager.getUserMail(), manager.getPassword());
                team.removeManager(manager);
                dbController.deleteRole(this,mailInput);
                dbController.addFan(this,fan);
                dbController.getTeams(this).replace(teamName,team);
                break;
            }
        }
    }

    /**
     * remove coach from team and make him fan
     *
     * @param teamName
     * @param mailInput
     */
    public void removeCoach(String teamName, String mailInput) throws MemberNotExist, AlreadyExistException, DontHavePermissionException {
        //gets the team
        Team team = dbController.getTeams(this).get(teamName);
        //gets the list of managers
        HashSet<Coach> coaches = team.getCoaches();
        for (Coach coach : coaches) {
            //found the manager to remove
            if (coach.getUserMail().equals(mailInput)) {
                Fan fan = new Fan(coach.getName(), coach.getUserMail(), coach.getPassword());
                team.removeCoach(coach);
                dbController.deleteRole(this,mailInput);
                dbController.addFan(this,fan);
                dbController.getTeams(this).replace(teamName,team);
                break;
            }
        }
    }

    /**
     * remove player from team and make him fan
     *
     * @param teamName
     * @param mailInput
     */
    public void removePlayer(String teamName, String mailInput) throws MemberNotExist, AlreadyExistException, DontHavePermissionException {
        //gets the team
        Team team = dbController.getTeams(this).get(teamName);
        //gets the list of managers
        HashSet<Player> players = team.getPlayers();
        for (Player player : players) {
            //found the manager to remove
            if (player.getUserMail().equals(mailInput)) {
                Fan fan = new Fan(player.getName(), player.getUserMail(), player.getPassword());
                team.removePlayer(player);
                dbController.deleteRole(this,mailInput);
                dbController.addFan(this,fan);
                dbController.getTeams(this).replace(teamName,team);
                break;
            }
        }
    }

    /**
     * remove field from team
     *
     * @param teamName
     * @param fieldName
     */
    public void removeField(String teamName, String fieldName) throws DontHavePermissionException {
        Team team = dbController.getTeams(this).get(teamName);
        HashSet<Field> field = team.getTrainingFields();
        field.remove(fieldName);
        team.setTrainingFields(field);
        dbController.getTeams(this).replace(teamName,team);
    }


    /******************************All Use-Cases functions************************************/
    /**
     * add a new owner to team belong to owner
     *
     * @param teamName
     * @param mailId
     */
    public void addNewOwner(String teamName, String mailId) throws MemberNotExist, AlreadyExistException, DontHavePermissionException {
        Team team = teams.get(teamName);

        Account account = team.getAccount();
        //set amount
        account.setAmountOfTeam(account.getAmountOfTeam() - 0);

        HashMap<String, Role> roles = dbController.getRoles(this);
        Role role = roles.get(mailId);
        Owner owner;
        if (role instanceof SystemManager) {
            SystemManager systemManager = (SystemManager) role;
            owner = new Owner(systemManager.getName(), systemManager.getUserMail(), systemManager.getPassword(),dbController);
            dbController.deleteRole(this,mailId);
            dbController.addOwner(this , owner);
            dbController.getTeams(this).replace(teamName,team);
            team.addOwner(owner);
        } else if (role instanceof Manager) {
            Manager manager = (Manager) role;
            owner = new Owner(manager.getName(), manager.getUserMail(), manager.getPassword(),dbController);
            dbController.deleteRole(this,mailId);
            dbController.addOwner(this , owner);
            team.addOwner(owner);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof Player) {
            Player player = (Player) role;
            owner = new Owner(player.getName(), player.getUserMail(), player.getPassword(),dbController);
            dbController.deleteRole(this,mailId);
            dbController.addOwner(this,owner);
            team.addOwner(owner);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof Coach) {
            Coach coach = (Coach) role;
            owner = new Owner(coach.getName(), coach.getUserMail(), coach.getPassword(),dbController);
            dbController.deleteRole(this,mailId);
            dbController.addOwner(this,owner);
            team.addOwner(owner);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof Fan) {
            Fan fan = (Fan) role;
            owner = new Owner(fan.getName(), fan.getUserMail(), fan.getPassword(),dbController);
            dbController.deleteRole(this,mailId);
            dbController.addOwner(this, owner);
            team.addOwner(owner);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof AssociationDelegate) {
            AssociationDelegate associationDelegate = (AssociationDelegate) role;
            owner = new Owner(associationDelegate.getName(), associationDelegate.getUserMail(), associationDelegate.getPassword(),dbController);
            dbController.deleteRole(this, mailId);
            dbController.addOwner(this,owner);
            team.addOwner(owner);
            dbController.getTeams(this).replace(teamName,team);
        } else if (role instanceof Referee) {
            Referee referee = (Referee) role;
            owner = new Owner(referee.getName(), referee.getUserMail(), referee.getPassword(),dbController);
            dbController.deleteRole(this,mailId);
            dbController.addOwner(this,owner);
            team.addOwner(owner);
            dbController.getTeams(this).replace(teamName,team);
        }
    }

    /**
     * Owner temporary close team
     *
     * @param teamName
     */
    public void temporaryTeamClosing(String teamName) throws DontHavePermissionException {
        Team team = dbController.getTeams(this).get(teamName);
        team.setStatus(false);
        dbController.getTeams(this).replace(teamName,team);
    }

    /**
     * Owner reopen team
     *
     * @param teamName
     */
    public void reopenClosedTeam(String teamName) throws DontHavePermissionException { ;
        Team team = dbController.getTeams(this).get(teamName);
        team.setStatus(true);
        dbController.getTeams(this).replace(teamName,team);
    }

    /**
     * owner update the outcome of group
     *
     * @param teamName
     * @param description
     * @param amount
     */
    public void addOutCome(String teamName, String description, double amount) throws NoEnoughMoney, DontHavePermissionException {
        Team team = dbController.getTeams(this).get(teamName);
        team.addTransaction(description, amount);
        team.getAccount().setAmountOfTeam(team.getAccount().getAmountOfTeam() - amount);
        dbController.getTeams(this).replace(teamName,team);
    }

    /**
     * owner update the income of group
     *
     * @param teamName
     * @param description
     * @param amount
     */
    public void addInCome(String teamName, String description, double amount) throws DontHavePermissionException {
        Team team = dbController.getTeams(this).get(teamName);
        team.addTransaction(description, amount);
        team.getAccount().setAmountOfTeam(team.getAccount().getAmountOfTeam() + amount);
        dbController.getTeams(this).replace(teamName,team);
    }

    public void updateAsset() {
        //todo
    }

    /***************************Getters************************************************************/
    public HashMap<String, Team> getTeams() {
        return teams;
    }

    public HashMap<String, Role> getRoles() throws DontHavePermissionException {
        return dbController.getRoles(this);
    }


}

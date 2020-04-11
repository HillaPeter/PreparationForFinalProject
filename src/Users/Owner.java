package Users;

import Exception.NoEnoughMoney;
import Asset.*;
import Game.Account;
import Game.Team;
import system.DBController;


import java.util.*;

public class Owner extends Member {
    private DBController dbController;

    public Owner(String name, String userMail, String password) {
        super(name, userMail, password);
    }

    public void addTeam(Team team) {
        dbController.addTeam(team);
    }

    public void removeTheTeamFromMyList(String name) {
        dbController.removeTeam(name);
    }

    /******************************Add Asset- Manage, Coach, Player, Field************************************/

    /**
     * add a new Manager team to team belong to owner
     *
     * @param teamName,mailId
     */
    public void addManager(String teamName, String mailId, DBController dbC) {
        this.dbController = dbC;
        Team team = dbController.getTeams().get(teamName);

        Account account = team.getAccount();
        //set amount
        account.setAmountOfTeam(account.getAmountOfTeam() - 0);

        HashMap<String, Role> roles = dbController.getRoles();
        Role role = roles.get(mailId);
        Manager manager;
        if (role instanceof SystemManager) {
            SystemManager systemManager = (SystemManager) role;
            manager = new Manager(systemManager.getName(), systemManager.getUserMail(), systemManager.getPassword());
            dbController.deleteRole(mailId);
            dbController.addManager(manager);
            team.addManager(manager);
        } else if (role instanceof Player) {
            Player player = (Player) role;
            manager = new Manager(player.getName(), player.getUserMail(), player.getPassword());
            dbController.deleteRole(mailId);
            dbController.addManager(manager);
            team.addManager(manager);
        } else if (role instanceof Coach) {
            Coach coach = (Coach) role;
            manager = new Manager(coach.getName(), coach.getUserMail(), coach.getPassword());
            dbController.deleteRole(mailId);
            dbController.addManager(manager);
            team.addManager(manager);
        } else if (role instanceof Fan) {
            Fan fan = (Fan) role;
            manager = new Manager(fan.getName(), fan.getUserMail(), fan.getPassword());
            dbController.deleteRole(mailId);
            dbController.addManager(manager);
            team.addManager(manager);
        } else if (role instanceof AssociationDelegate) {
            AssociationDelegate associationDelegate = (AssociationDelegate) role;
            manager = new Manager(associationDelegate.getName(), associationDelegate.getUserMail(), associationDelegate.getPassword());
            dbController.deleteRole(mailId);
            dbController.addManager(manager);
            team.addManager(manager);
        } else if (role instanceof Referee) {
            Referee referee = (Referee) role;
            manager = new Manager(referee.getName(), referee.getUserMail(), referee.getPassword());
            dbController.deleteRole(mailId);
            dbController.addManager(manager);
            team.addManager(manager);
        }


    }

    /**
     * add a new coach team to team belong to owner
     *
     * @param teamName,mailId
     */
    public void addCoach(String teamName, String mailId, DBController dbC) {
        this.dbController = dbC;
        Team team = dbController.getTeams().get(teamName);

        Account account = team.getAccount();
        //set amount
        account.setAmountOfTeam(account.getAmountOfTeam() - 0);

        HashMap<String, Role> roles = dbController.getRoles();
        Role role = roles.get(mailId);
        Coach coach;
        if (role instanceof SystemManager) {
            SystemManager systemManager = (SystemManager) role;
            coach = new Coach(systemManager.getName(), systemManager.getUserMail(), systemManager.getPassword());
            dbController.deleteRole(mailId);
            dbController.addCoach(coach);
            team.addCoach(coach);
        } else if (role instanceof Manager) {
            Manager manager = (Manager) role;
            coach = new Coach(manager.getName(), manager.getUserMail(), manager.getPassword());
            dbController.deleteRole(mailId);
            dbController.addCoach(coach);
            team.addCoach(coach);
        } else if (role instanceof Player) {
            Player player = (Player) role;
            coach = new Coach(player.getName(), player.getUserMail(), player.getPassword());
            dbController.deleteRole(mailId);
            dbController.addCoach(coach);
            team.addCoach(coach);
        } else if (role instanceof Fan) {
            Fan fan = (Fan) role;
            coach = new Coach(fan.getName(), fan.getUserMail(), fan.getPassword());
            dbController.deleteRole(mailId);
            dbController.addCoach(coach);
            team.addCoach(coach);
        } else if (role instanceof AssociationDelegate) {
            AssociationDelegate associationDelegate = (AssociationDelegate) role;
            coach = new Coach(associationDelegate.getName(), associationDelegate.getUserMail(), associationDelegate.getPassword());
            dbController.deleteRole(mailId);
            dbController.addCoach(coach);
            team.addCoach(coach);
        } else if (role instanceof Referee) {
            Referee referee = (Referee) role;
            coach = new Coach(referee.getName(), referee.getUserMail(), referee.getPassword());
            dbController.deleteRole(mailId);
            dbController.addCoach(coach);
            team.addCoach(coach);
        }
        //todo: owner??

    }

    /**
     * add a new player team to team belong to owner
     *
     * @param teamName,mailId
     */
    public void addPlayer(String teamName, String mailId, DBController dbC, int year, int month, int day, String roleInPlayers) {
        this.dbController = dbC;
        Team team = dbController.getTeams().get(teamName);

        Account account = team.getAccount();
        //set amount
        account.setAmountOfTeam(account.getAmountOfTeam() - 0);

        HashMap<String, Role> roles = dbController.getRoles();
        Role role = roles.get(mailId);
        Player player;
        Date date = new Date(year, month, day);

        if (role instanceof SystemManager) {
            SystemManager systemManager = (SystemManager) role;
            player = new Player(systemManager.getName(), systemManager.getUserMail(), systemManager.getPassword(), date, roleInPlayers);
            dbController.deleteRole(mailId);
            dbController.addPlayer(player);
            team.addPlayer(player);
        } else if (role instanceof Manager) {
            Manager manager = (Manager) role;
            player = new Player(manager.getName(), manager.getUserMail(), manager.getPassword(), date, roleInPlayers);
            dbController.deleteRole(mailId);
            dbController.addPlayer(player);
            team.addPlayer(player);
        } else if (role instanceof Coach) {
            Coach coach = (Coach) role;
            player = new Player(coach.getName(), coach.getUserMail(), coach.getPassword(), date, roleInPlayers);
            dbController.deleteRole(mailId);
            dbController.addPlayer(player);
            team.addPlayer(player);
        } else if (role instanceof Fan) {
            Fan fan = (Fan) role;
            player = new Player(fan.getName(), fan.getUserMail(), fan.getPassword(), date, roleInPlayers);
            dbController.deleteRole(mailId);
            dbController.addPlayer(player);
            team.addPlayer(player);
        } else if (role instanceof AssociationDelegate) {
            AssociationDelegate associationDelegate = (AssociationDelegate) role;
            player = new Player(associationDelegate.getName(), associationDelegate.getUserMail(), associationDelegate.getPassword(), date, roleInPlayers);
            dbController.deleteRole(mailId);
            dbController.addPlayer(player);
            team.addPlayer(player);
        } else if (role instanceof Referee) {
            Referee referee = (Referee) role;
            player = new Player(referee.getName(), referee.getUserMail(), referee.getPassword(), date, roleInPlayers);
            dbController.deleteRole(mailId);
            dbController.addPlayer(player);
            team.addPlayer(player);
        }
        //todo: owner??

    }

    /**
     * add a field to team asset
     *
     * @param teamName
     */
    public void addField(String teamName, DBController dbC, String fieldName) {
        this.dbController = dbC;
        Team team = dbController.getTeams().get(teamName);
        Field field = new Field(fieldName);
        team.addField(field);
        HashSet<Field> trainingFields = dbController.getTeams().get(teamName).getTrainingFields();
        trainingFields.add(field);
    }

    /******************************Remove Asset- Manage, Coach, Player, Field************************************/

    /**
     * remove manager from team and make him fan
     *
     * @param teamName
     * @param mailInput
     */
    public void removeManager(String teamName, String mailInput, DBController dbC) {
        this.dbController = dbC;
        //gets the team
        Team team = dbController.getTeams().get(teamName);
        //gets the list of managers
        HashSet<Manager> managers = team.getManagers();
        for (Manager manager : managers) {
            //found the manager to remove
            if (manager.getUserMail().equals(mailInput)) {
                Fan fan = new Fan(manager.getName(), manager.getUserMail(), manager.getPassword());
                team.removeManager(manager);
                dbController.deleteRole(mailInput);
                dbController.addFan(fan);
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
    public void removeCoach(String teamName, String mailInput, DBController dbC) {
        this.dbController = dbC;
        //gets the team
        Team team = dbController.getTeams().get(teamName);
        //gets the list of managers
        HashSet<Coach> coaches = team.getCoaches();
        for (Coach coach : coaches) {
            //found the manager to remove
            if (coach.getUserMail().equals(mailInput)) {
                Fan fan = new Fan(coach.getName(), coach.getUserMail(), coach.getPassword());
                team.removeCoach(coach);
                dbController.deleteRole(mailInput);
                dbController.addFan(fan);
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
    public void removePlayer(String teamName, String mailInput, DBController dbC) {
        this.dbController = dbC;
        //gets the team
        Team team = dbController.getTeams().get(teamName);
        //gets the list of managers
        HashSet<Player> players = team.getPlayers();
        for (Player player : players) {
            //found the manager to remove
            if (player.getUserMail().equals(mailInput)) {
                Fan fan = new Fan(player.getName(), player.getUserMail(), player.getPassword());
                team.removePlayer(player);
                dbController.deleteRole(mailInput);
                dbController.addFan(fan);
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
    public void removeField(String teamName, String fieldName, DBController dbC) {
        this.dbController = dbC;
        Team team = dbController.getTeams().get(teamName);
        HashSet<Field> field = team.getTrainingFields();
        field.remove(fieldName);
        team.setTrainingFields(field);
    }


    /******************************All Use-Cases functions************************************/
    /**
     * add a new owner to team belong to owner
     *
     * @param teamName
     * @param mailId
     * @param dbC
     */
    public void addNewOwner(String teamName, String mailId, DBController dbC) {
        this.dbController = dbC;
        Team team = dbController.getTeams().get(teamName);

        Account account = team.getAccount();
        //set amount
        account.setAmountOfTeam(account.getAmountOfTeam() - 0);

        HashMap<String, Role> roles = dbController.getRoles();
        Role role = roles.get(mailId);
        Owner owner;
        if (role instanceof SystemManager) {
            SystemManager systemManager = (SystemManager) role;
            owner = new Owner(systemManager.getName(), systemManager.getUserMail(), systemManager.getPassword());
            dbController.deleteRole(mailId);
            dbController.addOwner(owner);
            team.addOwner(owner);
        } else if (role instanceof Manager) {
            Manager manager = (Manager) role;
            owner = new Owner(manager.getName(), manager.getUserMail(), manager.getPassword());
            dbController.deleteRole(mailId);
            dbController.addOwner(owner);
            team.addOwner(owner);
        } else if (role instanceof Player) {
            Player player = (Player) role;
            owner = new Owner(player.getName(), player.getUserMail(), player.getPassword());
            dbController.deleteRole(mailId);
            dbController.addOwner(owner);
            team.addOwner(owner);
        } else if (role instanceof Coach) {
            Coach coach = (Coach) role;
            owner = new Owner(coach.getName(), coach.getUserMail(), coach.getPassword());
            dbController.deleteRole(mailId);
            dbController.addOwner(owner);
            team.addOwner(owner);
        } else if (role instanceof Fan) {
            Fan fan = (Fan) role;
            owner = new Owner(fan.getName(), fan.getUserMail(), fan.getPassword());
            dbController.deleteRole(mailId);
            dbController.addOwner(owner);
            team.addOwner(owner);
        } else if (role instanceof AssociationDelegate) {
            AssociationDelegate associationDelegate = (AssociationDelegate) role;
            owner = new Owner(associationDelegate.getName(), associationDelegate.getUserMail(), associationDelegate.getPassword());
            dbController.deleteRole(mailId);
            dbController.addOwner(owner);
            team.addOwner(owner);
        } else if (role instanceof Referee) {
            Referee referee = (Referee) role;
            owner = new Owner(referee.getName(), referee.getUserMail(), referee.getPassword());
            dbController.deleteRole(mailId);
            dbController.addOwner(owner);
            team.addOwner(owner);
        }
    }

    /**
     * Owner temporary close team
     *
     * @param teamName
     * @param dbC
     */
    public void temporaryTeamClosing(String teamName, DBController dbC) {
        this.dbController = dbC;
        Team team = dbController.getTeams().get(teamName);
        team.setStatus(false);
    }

    /**
     * Owner reopen team
     *
     * @param teamName
     * @param dbC
     */
    public void reopenClosedTeam(String teamName, DBController dbC) {
        this.dbController = dbC;
        Team team = dbController.getTeams().get(teamName);
        team.setStatus(true);
    }

    /**
     * owner update the outcome of group
     *
     * @param teamName
     * @param description
     * @param amount
     * @param dbC
     */
    public void addOutCome(String teamName, String description, double amount, DBController dbC) throws NoEnoughMoney {
        this.dbController = dbC;
        Team team = dbController.getTeams().get(teamName);
        team.addTransaction(description, amount);
        team.getAccount().setAmountOfTeam(team.getAccount().getAmountOfTeam() - amount);
    }

    /**
     * owner update the income of group
     *
     * @param teamName
     * @param description
     * @param amount
     * @param dbC
     */
    public void addInCome(String teamName, String description, double amount, DBController dbC) {
        this.dbController = dbC;
        Team team = dbController.getTeams().get(teamName);
        team.addTransaction(description, amount);
        team.getAccount().setAmountOfTeam(team.getAccount().getAmountOfTeam() + amount);
    }

    public void updateAsset() {
        //todo
    }

    /***************************Getters************************************************************/
    public HashMap<String, Team> getTeams() {
        return dbController.getTeams();
    }


}

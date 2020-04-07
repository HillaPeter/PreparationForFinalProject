package Users;

import Asset.*;
import Game.Account;
import Game.Team;
import Game.Transaction;

import java.util.*;

public class Owner extends Member {
    //private TeamMember teamMember;
    private HashMap<String, Team> teams;

    public Owner(String name, String userMail, String password) {
        super(name, userMail, password);
        //  this.teamMember = null;
        //todo addTeam func?
    }

    public void addTeam(Team team)
    {
        if(teams==null)
        {
            teams=new HashMap<>();
        }
        teams.put(team.getName() , team);
    }
    public void removeTheTeamFromMyList(String name) {
        teams.remove(name);
    }

    /**
     * adding new asset to team belongs to owner
     */
    public void addAsset() {
        Scanner input = new Scanner(System.in);
        int i;
        if (teams == null) {
            System.out.println("There is no teams in system");
        } else {
            boolean hasChoosen = false;
            int teamNameChoose;
            String teamName = "";
            while (!hasChoosen) {
                System.out.println("Choose team name by index");
                i = 0;
                for (String teamNameIndex : teams.keySet()) {
                    System.out.println(i + ". " + teamNameIndex);
                    i++;
                }
                teamNameChoose = input.nextInt();
                i = 0;
                //get the team name
                for (String teamNameIndex : teams.keySet()) {
                    if (i == teamNameChoose) {
                        teamName = teamNameIndex;
                    }
                    i++;
                }
                if (teams.get(teamName) != null) {
                    hasChoosen = true;
                } else {
                    System.out.println("You entered invalid team name by index, please try again");
                }
            }
            System.out.println("What asset do you want to add? choose by index");
            System.out.println("1. Team manager");
            System.out.println("2. Coach");
            System.out.println("3. Player");
            System.out.println("4. Field");
            int choose = input.nextInt();
            switch (choose) {
                case 1: {
                    addNewManager(teamName);
                    System.out.println("Manager added successfully");
                    break;
                }
                case 2: {
                    addCoach(teamName);
                    System.out.println("Coach added successfully");
                    break;
                }
                case 3: {
                    addPlayer(teamName);
                    System.out.println("Player added successfully");
                    break;
                }
                case 4: {
                    addField(teamName);
                    System.out.println("Field added successfully");
                    break;
                }
                default: {
                    System.out.println("Invalid choose, please try again");
                    break;
                }
            }

        }
    }


    /**
     * add a coach to team asset
     *
     * @param teamName
     */
    private void addCoach(String teamName) {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert mail");
        String mailCoach = input.next();
        Team team = teams.get(teamName);

        HashSet<Coach> coachesInTeam = team.getCoaches();
        boolean exists = checkIfCoachExistsInTeam(coachesInTeam, mailCoach);
        //not exists
        if (!exists) {
            System.out.println("Insert name");
            String nameCoach = input.next();
            System.out.println("Insert training");
            String trainingCoach = input.next();
            Coach coach = new Coach(nameCoach, mailCoach, trainingCoach);

            HashSet<Coach> managersOfTeam = team.getCoaches();
            managersOfTeam.add(coach);

            //add the adding to transactions of team
            Account account = team.getAccount();
            ArrayList<Transaction> transaction = account.getTransactions();
            //todo: how i take money??
            Transaction currentTransaction = new Transaction("Add Coach", 0);
            transaction.add(currentTransaction);
        } else { //if exists
            System.out.println("Coach already exists in system");
        }
    }

    /**
     * add a player to team asset
     *
     * @param teamName
     */
    private void addPlayer(String teamName) {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert mail");
        String mailPlayer = input.next();
        Team team = teams.get(teamName);

        HashSet<Player> playersInTeam = team.getPlayers();
        boolean exists = checkIfPlayerExistsInTeam(playersInTeam, mailPlayer);
        //not exists
        if (!exists) {
            System.out.println("Insert name");
            String namePlayer = input.next();
            System.out.println("Insert birthDate:");
            System.out.println("Insert day of birthday");
            int day= input.nextInt();
            System.out.println("Insert month of birthday");
            int month= input.nextInt();
            System.out.println("Insert year of birthday");
            int year= input.nextInt();
            System.out.println("Insert role");
            String rolePLayer = input.next();

            Date date=new Date(year,month,day);
            Player player = new Player(namePlayer, mailPlayer,date, rolePLayer);

            HashSet<Player> playerInTeam = team.getPlayers();
            playerInTeam.add(player);

            //add the adding to transactions of team
            Account account = team.getAccount();
            ArrayList<Transaction> transaction = account.getTransactions();
            //todo: how i take money??
            Transaction currentTransaction = new Transaction("Add Player", 0);
            transaction.add(currentTransaction);
        } else { //if exists
            System.out.println("Player already exists in system");
        }
    }

    /**
     * add a field to team asset
     *
     * @param teamName
     */
    private void addField(String teamName) {
        Scanner input = new Scanner(System.in);
        Team team = teams.get(teamName);
        Field field=team.getField();
       if(field==null){
           System.out.println("Insert field name");
           String fieldName = input.next();

           field=new Field(fieldName);
           team.setField(field);

           Account account = team.getAccount();
           ArrayList<Transaction> transaction = account.getTransactions();
           //todo: how i take money??
           Transaction currentTransaction = new Transaction("Add Field", 0);
           transaction.add(currentTransaction);
       } else { //if exists
            System.out.println("Field already exists in system");
        }
    }


    public void updateAsset() {
        //todo
    }

    public void removeAsset() {
        //todo
    }

    /**
     * add a new Manager team to team belong to owner
     *
     * @param teamName
     */
    public void addNewManager(String teamName) {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert mail");
        String mailManager = input.next();
        Team team = teams.get(teamName);

        HashSet<Manager> managersInTeam = team.getManagers();
        boolean exists = checkIfManagerExistsInTeam(managersInTeam, mailManager);
        //not exists
        if (!exists) {
            System.out.println("Insert name");
            String nameManager = input.next();
            Manager manager = new Manager(nameManager, mailManager);

            HashSet<Manager> managersOfTeam = team.getManagers();
            managersOfTeam.add(manager);

            //add the adding to transactions of team
            Account account = team.getAccount();
            ArrayList<Transaction> transaction = account.getTransactions();
            //todo: how i take money??
            Transaction currentTransaction = new Transaction("Add Manager", 0);
            transaction.add(currentTransaction);
        } else { //if exists
            System.out.println("manager already exists in system");
        }
    }

    public void addNewOwner() {
        //todo
    }

    public void removeManager() {
        //todo
    }

    public void temporaryTeamClosing() {
        //todo
    }

    public void reopenClosedTeam() {
        //todo
    }

    public void addIncome() {
        //todo
    }

    public void addOutCome() {
        //todo
    }

    public void setTeams(HashMap<String, Team> teams) {
        this.teams = teams;
    }


    /**
     * check if manager exists in team
     *
     * @param managersInTeam
     * @param mailManager
     * @return true if he exists and false if he is not
     */
    private boolean checkIfManagerExistsInTeam(HashSet<Manager> managersInTeam, String mailManager) {
        boolean found = false;
        for (Manager manager : managersInTeam) {
            if (!found && manager.getUserMail().equals(mailManager)) {
                found = true;
            }
        }
        return found;
    }

    /**
     * check if manager exists in team
     *
     * @param coachesInTeam
     * @param mailManager
     * @return true if he exists and false if he is not
     */
    private boolean checkIfCoachExistsInTeam(HashSet<Coach> coachesInTeam, String mailManager) {
        boolean found = false;
        for (Coach coach : coachesInTeam) {
            if (!found && coach.getUserMail().equals(mailManager)) {
                found = true;
            }
        }
        return found;
    }

    /**
     * check if player exists in team
     *
     * @param playersInTeam
     * @param mailPlayer
     * @return true if he exists and false if he is not
     */
    private boolean checkIfPlayerExistsInTeam(HashSet<Player> playersInTeam, String mailPlayer) {
        boolean found = false;
        for (Player player : playersInTeam) {
            if (!found && player.getUserMail().equals(mailPlayer)) {
                found = true;
            }
        }
        return found;
    }

}

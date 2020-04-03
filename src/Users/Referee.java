package Users;

public abstract class Referee extends Member{
    private String training;

    public Referee(String name, int userId, String password, String training) {
        super(name, userId, password);
        this.training = training;
    }

    public void printGameSchedule(){
        //todo
        System.out.println("Hilla the queen");
    }

    public void updateDetails(){
        //todo
    }


}

package Asset;

public class Coach extends TeamMember{
    private String training;

    public Coach(String name, String userMail, String password, String training) {
        super(name, userMail, password);
        this.training = training;
    }

    public Coach(String name, String userMail, String training) {
        super(name, userMail);
        this.training = training;
    }

}

package Asset;

public class Manager extends TeamMember {

    public Manager(String name, String userMail, String password) {
        super(name, userMail, password);
    }

    public Manager(String name, String userMail) {
        super(name, userMail);
        this.setPassword(null);
    }



}

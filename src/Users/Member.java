package Users;

public abstract class Member extends Role {
    private String userMail;
    private String password;

    public Member(String name, String userMail, String password) {
        super(name);
        this.userMail = userMail;
        this.password = password;
    }

    public String getUserMail()
    {
        return userMail;
    }
    public String getPassword()
    {
        return password;
    }

}

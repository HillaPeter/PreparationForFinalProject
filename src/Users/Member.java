package Users;

public abstract class Member extends Role {
    private int userId;
    private String password;

    public Member(String name, int userId, String password) {
        super(name);
        this.userId = userId;
        this.password = password;
    }

}

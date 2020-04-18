package Users;

public class SecondaryReferee extends Referee {

    public SecondaryReferee(String name, String userMail, String password, String training) {
        super(name, userMail, password, training);
    }
    public SecondaryReferee(Fan fan)
    {
        super(fan.getName(),fan.getUserMail(), fan.getPassword() , "");
    }
}

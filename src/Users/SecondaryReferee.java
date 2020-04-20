package Users;

import java.util.Date;

public class SecondaryReferee extends Referee {

    public SecondaryReferee(String name, String userMail, String password, String training , Date birthdate) {
        super(name, userMail, password, training , birthdate);
    }
    public SecondaryReferee(Fan fan)
    {
        super(fan.getName(),fan.getUserMail(), fan.getPassword() , "" , fan.getBirthDate());
    }
}

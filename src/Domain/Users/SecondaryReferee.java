package Domain.Users;

import java.util.Date;
import Exception.IncorrectInputException;
import Exception.DontHavePermissionException;
import Exception.MemberNotExist;
import Exception.AlreadyExistException;

public class SecondaryReferee extends Referee {

    public SecondaryReferee(String name, String userMail, String password, String training , Date birthdate) {
        super(name, userMail, password, training , birthdate);
    }
    public SecondaryReferee(Fan fan)
    {
        super(fan.getName(),fan.getUserMail(), fan.getPassword() , "" , fan.getBirthDate());
    }

    public void updateDetails(String newName, String newMail,String newTraining) throws IncorrectInputException, MemberNotExist, DontHavePermissionException, AlreadyExistException {
        if (newName == null || newMail ==null ||newTraining == null){
            throw new IncorrectInputException("");
        }
        super.dbController.deleteReferee(this, super.getUserMail());
        if (newName != ""){
            super.setName(newName);
        }
        if (newMail != ""){
            super.setUserMail(newMail);
        }
        if (newTraining != ""){
            super.training = newTraining;
        }
        super.dbController.addReferee(this, this);
    }

}

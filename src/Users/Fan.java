package Users;

import Exception.IncorrectInputException;

import java.util.Date;

public class Fan extends Member {



    public Fan(String name, String userId, String password, Date birthDate) {
        super(name, userId, password, birthDate);
    }


    public void logOut(){
        //todo
    }

    public void followPersonalPage(){
        //todo
    }

    public void followGames(){
        //todo
    }

    public void watchSearchHistory(){
        //todo
    }

    public void updatePersonalDetails(String newName, String newPassword) throws Exception {
        if (newName == null || newPassword == null){
            throw new IncorrectInputException();
        }
        if(newName != ""){
            super.setName(newName);
        }
        if(newPassword != ""){
            super.setPassword(newPassword);
        }
    }

    public void sendComplaint(){
        //todo
    }

}

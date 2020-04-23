package Users;

import Exception.IncorrectInputException;
import Exception.MemberNotExist;
import Exception.DontHavePermissionException;
import Exception.AlreadyExistException;
import system.DBController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Fan extends Member implements Observer {

    private DBController dbController;
    private ArrayList<String> updates;

    public Fan(String name, String mail, String password, Date birthDate) {
        super(name, mail, password, birthDate);
        dbController = new DBController();
    }


    public void followPersonalPage(){
        //todo
    }


    public void updatePersonalDetails(String newName, String newPassword, String newMail) throws IncorrectInputException, MemberNotExist, DontHavePermissionException, AlreadyExistException {
        if (newName == null || newPassword == null){
            throw new IncorrectInputException();
        }

        dbController.deleteFan(this, super.getUserMail());

        if(newName != ""){
            super.setName(newName);
        }
        if(newPassword != ""){
            super.setPassword(newPassword);
        }
        if(newPassword != ""){
            super.setMail(newMail);
        }
        dbController.addFan(this, this);
    }

    public void sendComplaint(String path, String complaint){
        LinkedList<String> complaintsList = new LinkedList<>();
        try {
            File newText = new File(path);
            Scanner scanner = new Scanner(newText);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                complaintsList.add(line);
            }
            complaintsList.add(complaint);
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i=0; i<complaintsList.size(); i++){
                bw.write(i + "." + complaintsList.get(i));
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.print("the path is not legal");
        }
    }

    @Override
    // the arg is the message recieved from the observable
    public void update(Observable o, Object message) {
        updates.add("new update:" + message.toString());
    }
}

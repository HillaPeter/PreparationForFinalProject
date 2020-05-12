package Presentation.Guest;


import Presentation.Fan.FanMenu;
import Presentation.Menu;
import Service.ServiceController;
import Exception.*;
//import com.toedter.calendar.JCalendar;
//import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class SignIn implements Menu {
    JFrame frame = new JFrame("SignIn");
    private ServiceController serviceController=ServiceController.getInstance();

    /*********** SIGNIN ************/
    private JPanel signPanel;
    private JButton signButton;
    private JButton backButton;
    private JTextField mailField;
    private JTextField nameField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JPanel down;
    private JPanel up;
    private JPanel signForm;
    private JLabel errorMail;
    private JLabel errorPass;
    private JLabel errorMatch;

    public SignIn(){


        signButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorMatch.setText("");
                errorMail.setText("");
                errorPass.setText("");
                System.out.println("pressSign");
                sign();
                System.out.println("afterSign");
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitMenu();
            }
        });
    }

    private void sign() {
        char[] pass1 = passwordField1.getPassword();
        char[] pass2 = passwordField2.getPassword();
        if(! serviceController.matchPass(pass1,pass2) ){
            errorMatch.setText("Password don't match");
        }
        else {
            try {
                serviceController.signIn(nameField.getText(),mailField.getText(),new String(pass1),new Date());
                exitMenu();
            } catch (IncorrectInputException e) {
                errorPass.setText("Incorrect input - password or mail not valid");
            } catch (DontHavePermissionException e) {
            } catch (AlreadyExistException e) {
                errorMail.setText("The user mail already exist in the system");
            }
        }
    }

    public void showMenu() {
        frame.setContentPane(this.signPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void exitMenu() {
        GuestMenu guestMenu = new GuestMenu();
        guestMenu.showMenu();
        this.frame.dispose();
    }
}

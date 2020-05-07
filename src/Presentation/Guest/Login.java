package Presentation.Guest;

import Presentation.AssociationDelegate.AssociationDelegateMenu;
import Presentation.Fan.FanMenu;
import Presentation.Menu;
import Presentation.Owner.OwnerMenu;
import Presentation.Referee.RefereeMenu;
import Presentation.SystemManager.SystemManagerMenu;
import Service.ServiceController;
import Exception.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements Menu {
    JFrame frame = new JFrame("Login");
    private ServiceController serviceController=new ServiceController();
    /*********** LOGIN ************/
    private JPanel loginPanel;
    private JLabel loginMenu;
    private JTextField userMailTextField;
    private JButton loginButton;
    private JLabel errorLabel;
    private JButton backButton;
    private JPasswordField passwordField;

    public Login() {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorLabel.setText("");
                System.out.println("pressLogin");
                login();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitMenu();
            }
        });
    }

    public void showMenu() {
        frame.setContentPane(this.loginPanel);
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

    public void login() {
        try {
            String memberType = serviceController.login(userMailTextField.getText(), new String(passwordField.getPassword()) );
            showMenu(memberType);
            this.frame.dispose();
        } catch (PasswordDontMatchException e) {
            errorLabel.setText("incorrect password");
        } catch (MemberNotExist memberNotExist) {
            errorLabel.setText("incorrect user mail");
        } catch (DontHavePermissionException e) {

        }
    }
    public void showMenu(String memberType){
        if(memberType.equals("SystemManager")){
            SystemManagerMenu systemManagerMenu = new SystemManagerMenu();
            systemManagerMenu.showMenu();
        }
        else if(memberType.equals("Owner")){
            OwnerMenu ownerMenu = new OwnerMenu();
            ownerMenu.showMenu();
        }
        else if(memberType.equals("AssociationDelegate")){
            AssociationDelegateMenu associationDelegateMenu = new AssociationDelegateMenu();
            associationDelegateMenu.showMenu();
        }
        else if(memberType.equals("Fan")){
            FanMenu fanMenu = new FanMenu();
            fanMenu.showMenu();
        }
        else if(memberType.equals("Referee")){
            RefereeMenu refereeMenu = new RefereeMenu();
            refereeMenu.showMenu();
        }
    }
}

package Presentation.Guest;

import Presentation.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class GuestMenu implements Menu {
    JFrame frame = new JFrame("GuestMenu");
    /********** GUEST MENU **********/
    private JButton signinButton;
    private JPanel menuPanel;
    private JButton loginButton;
    private JLabel GuestMenu;
    private JSeparator JSeperator;
    private JSeparator Jseparator;

    public GuestMenu() {

        signinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSignInMenu();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginMenu();
            }
        });

    }

    @Override
    public void showMenu() {
        frame.setContentPane(this.menuPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public void showLoginMenu() {
        Login login = new Login();
        login.showMenu();
        this.frame.dispose();
    }
    public void showSignInMenu() {
        SignIn signIn = new SignIn();
        signIn.showMenu();
        this.frame.dispose();
    }
    @Override
    public void exitMenu() {

    }

}

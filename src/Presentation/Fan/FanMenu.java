package Presentation.Fan;

import DataBase.DBController;
import Domain.Users.Guest;
import Presentation.Guest.GuestMenu;
import Presentation.Menu;
import Service.ServiceController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FanMenu extends JFrame implements Menu {
    JFrame frame = new JFrame("Fan");
    private ServiceController serviceController =  ServiceController.getInstance();

    private JPanel menuPanel;
    private JPanel notification;
    private JButton searchButton;
    private JButton followersButton;
    private JButton logOutButton;
    private JLabel name;

    public FanMenu() {
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitMenu();
            }
        });
    }

    @Override
    public void showMenu() {
        name.setText(serviceController.getUserName());
        frame.setContentPane(this.menuPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        System.out.println("in fan menu");
    }

    @Override
    public void exitMenu() {
        serviceController.logOut();
        GuestMenu guestMenu = new GuestMenu();
        guestMenu.showMenu();
        this.frame.dispose();

    }
}
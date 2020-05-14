package Presentation.Owner;

import Presentation.Menu;
import Service.ServiceController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import Exception.*;

public class AddManager extends JFrame implements Menu {
    private JPanel panelAddManager;
    private JLabel label;
    private JComboBox comboBox;
    private JLabel labelTeam;
    private JComboBox comboBoxTeams;
    private JButton okButton;
    private JButton backButton;
    private JLabel errorLabel;
    private ServiceController serviceController = ServiceController.getInstance();
    JFrame frame = new JFrame("Add Manager");

    @Override
    public void showMenu() {
        frame.setContentPane(panelAddManager);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setTitle("Add Manager");
        errorLabel.setText("");
        try {
            LinkedList<String> allManagers = serviceController.addFanIntoComboBox();
            for (int i = 0; i < allManagers.size(); i++) {
                comboBox.addItem(allManagers.get(i));
            }
        } catch (NullPointerException | DontHavePermissionException e) {
            errorLabel.setText("You doon't have permission to do this");
        }

        LinkedList<String> allteams = serviceController.getTeams();
        for (int i = 0; i < allteams.size(); i++) {
            comboBoxTeams.addItem(allteams.get(i));
        }


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    serviceController.addTeamManager(labelTeam.getText(), comboBoxTeams.getActionCommand());
                    exitMenu();
                } catch (Exception e1) {
                    errorLabel.setText("You don't fill everything correctly");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitMenu();
            }
        });
    }

    @Override
    public void exitMenu() {
        OwnerMenu ownerMenu = new OwnerMenu();
        ownerMenu.showMenu();
        this.frame.dispose();
    }


}

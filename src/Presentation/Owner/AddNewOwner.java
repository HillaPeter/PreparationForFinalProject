package Presentation.Owner;

import Presentation.Menu;
import Service.ServiceController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import Exception.*;

public class AddNewOwner extends JFrame implements Menu {
    JFrame frame = new JFrame("Add New Owner");
    private ServiceController serviceController=ServiceController.getInstance();
    private JPanel panelAddNewOwner;
    private JComboBox comboBoxRoles;
    private JLabel labelTeam;
    private JComboBox comboBoxTeam;
    private JButton okButton;
    private JButton backButton;
    private JLabel errorLabel;

    @Override
    public void showMenu() {
        frame.setContentPane(panelAddNewOwner);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setTitle("Add New Owner");
        errorLabel.setText("");

        try {
            LinkedList<String> allManagers = serviceController.addFanIntoComboBox();
            for (int i = 0; i < allManagers.size(); i++) {
                comboBoxRoles.addItem(allManagers.get(i));
            }
        } catch (DontHavePermissionException e) {
            errorLabel.setText("You doon't have permission to do this");
        }

        LinkedList<String> allteams = serviceController.getTeams();
        for (int i = 0; i < allteams.size(); i++) {
            comboBoxTeam.addItem(allteams.get(i));
        }


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    serviceController.addNewOwner(labelTeam.getText(), comboBoxTeam.getActionCommand());
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

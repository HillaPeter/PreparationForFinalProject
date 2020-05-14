package Presentation.Owner;

import Presentation.Menu;
import Service.ServiceController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import Exception.*;

public class AddCoach extends JFrame implements Menu {
    private JPanel panelAddCoach;
    private JLabel label;
    private JComboBox comboBox;
    private JLabel labelTeam;
    private JComboBox comboBoxTeam;
    private JButton okButton;
    private JButton backButton;
    private JLabel errorLabel;
    private ServiceController serviceController = ServiceController.getInstance();
    JFrame frame = new JFrame("Add Coach");

    @Override
    public void showMenu() {
        frame.setContentPane(panelAddCoach);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setTitle("Add Coach");
        errorLabel.setText("");
        try {
            LinkedList<String> allCoaches = serviceController.addFanIntoComboBox();
            for (int i = 0; i < allCoaches.size(); i++) {
                comboBox.addItem(allCoaches.get(i));
            }
        } catch (DontHavePermissionException s) {
            errorLabel.setText("You don't have permission");
        }

        LinkedList<String> allteams = serviceController.getTeams();
        for (int i = 0; i < allteams.size(); i++) {
            comboBoxTeam.addItem(allteams.get(i));
        }

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    serviceController.addCoach(labelTeam.getText(), comboBoxTeam.getActionCommand());
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
        AddAsset addAsset = new AddAsset();
        addAsset.showMenu();
        this.frame.dispose();
    }
}

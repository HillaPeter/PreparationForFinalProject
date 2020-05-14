package Presentation.Owner;

import Presentation.Menu;
import Service.ServiceController;

import javax.swing.*;
import java.util.LinkedList;

import Exception.*;

public class AddPlayer extends JFrame implements Menu {
    private JPanel panelAddPlayer;
    private JLabel label;
    private JComboBox comboBox;
    private JLabel labelTeam;
    private JComboBox comboBoxTeam;
    private JTextField fieldY;
    private JTextField fieldM;
    private JTextField fieldD;
    private JButton backButton;
    private JButton okButton;
    private JTextField fieldRole;
    private JLabel errorLabel;
    private ServiceController serviceController = ServiceController.getInstance();
    JFrame frame = new JFrame("Add Player");


    @Override
    public void showMenu() {
        frame.setContentPane(panelAddPlayer);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setTitle("Add Manager");
        errorLabel.setText("");

        try {
            LinkedList<String> allPlayers = serviceController.addFanIntoComboBox();
            for (int i = 0; i < allPlayers.size(); i++) {
                comboBox.addItem(allPlayers.get(i));
            }
        } catch (DontHavePermissionException e) {
            errorLabel.setText("You don't fill everything correctly");
        }

        LinkedList<String> allteams = serviceController.getTeams();
        for (int i = 0; i < allteams.size(); i++) {
            comboBoxTeam.addItem(allteams.get(i));
        }


        try {
            int year = Integer.parseInt(fieldY.getText());
            if (year < 1000 || year > 2020) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException n) {
            errorLabel.setText("incorrect year");
        }

        try {
            int month = Integer.parseInt(fieldM.getText());
            if (month < 1 || month > 12) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException n) {
            errorLabel.setText("incorrect month");
        }

        try {
            int day = Integer.parseInt(fieldD.getText());
            if (day < 1 || day > 31) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException n) {
            errorLabel.setText("incorrect day");
        }

        okButton.addActionListener(e -> {
            try {
                serviceController.addPlayer(labelTeam.getText(), comboBoxTeam.getActionCommand(), Integer.parseInt(fieldY.getText()), Integer.parseInt(fieldM.getText()), Integer.parseInt(fieldD.getText()), fieldRole.getText());
                exitMenu();
            } catch (Exception e1) {
                errorLabel.setText("You don't fill everything correctly");
            }
        });

        backButton.addActionListener(e -> exitMenu());

    }

    @Override
    public void exitMenu() {
        AddAsset addAsset = new AddAsset();
        addAsset.showMenu();
        this.frame.dispose();
    }
}

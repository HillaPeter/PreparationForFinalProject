package Presentation.Owner;

import Presentation.Menu;
import Service.ServiceController;

import javax.swing.*;

import Exception.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class RemovePlayer extends JFrame implements Menu {
    private JPanel panelRemovePlayer;
    private JComboBox comboBoxTeam;
    private JComboBox comboBoxPlayers;
    private JButton okButton;
    private JButton backButton;
    private JLabel errorLabel;
    private ServiceController serviceController = ServiceController.getInstance();
    JFrame frame = new JFrame("Remove Player");

    @Override
    public void showMenu() {
        frame.setContentPane(panelRemovePlayer);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setTitle("Remove Player");
        errorLabel.setText("");


        LinkedList<String> allteams = serviceController.getTeams();
        for (int i = 0; i < allteams.size(); i++) {
            comboBoxTeam.addItem(allteams.get(i));
        }

        try {
            LinkedList<String> allManagers = serviceController.getPlayers(comboBoxTeam.getActionCommand());
            for (int i = 0; i < allManagers.size(); i++) {
                comboBoxPlayers.addItem(allManagers.get(i));
            }
        } catch (ObjectNotExist objectNotExist) {
            errorLabel.setText("Object not exist in system");
        } catch (DontHavePermissionException e) {
            errorLabel.setText("you don't have permission to do this");
        }

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    serviceController.removePlayer(comboBoxTeam.getActionCommand(), comboBoxPlayers.getActionCommand());
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
        RemoveAsset removeAsset = new RemoveAsset();
        removeAsset.showMenu();
        frame.dispose();
    }
}

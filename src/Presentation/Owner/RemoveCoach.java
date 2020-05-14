package Presentation.Owner;

import Presentation.Menu;
import Service.ServiceController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import Exception.*;

public class RemoveCoach  extends JFrame implements Menu {
    private JPanel panelRemoveCoach;
    private JLabel label;
    private JComboBox comboBoxTeam;
    private JLabel labelCoach;
    private JComboBox comboBoxCoaches;
    private JButton okButton;
    private JButton backButton;
    private JLabel errorLabel;
    private ServiceController serviceController = ServiceController.getInstance();
    JFrame frame = new JFrame("Remove Coach");


    @Override
    public void showMenu() {
        frame.setContentPane(panelRemoveCoach);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setTitle("Remove Coach");
        errorLabel.setText("");


        LinkedList<String> allteams = serviceController.getTeams();
        for (int i = 0; i < allteams.size(); i++) {
            comboBoxTeam.addItem(allteams.get(i));
        }

        try {
            LinkedList<String> allManagers = serviceController.getCoaches(comboBoxTeam.getActionCommand());
            for (int i = 0; i < allManagers.size(); i++) {
                comboBoxCoaches.addItem(allManagers.get(i));
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
                    serviceController.removeCoach(comboBoxTeam.getActionCommand(), comboBoxCoaches.getActionCommand());
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
        RemoveAsset removeAsset=new RemoveAsset();
        removeAsset.showMenu();
        frame.dispose();
    }
}

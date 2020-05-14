package Presentation.Owner;

import Presentation.Menu;
import Service.ServiceController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class TemporaryClosingTeam extends JFrame implements Menu {
    private ServiceController serviceController=ServiceController.getInstance();
    JFrame frame = new JFrame("Temporary closing team");
    private JPanel panelCloseTeam;
    private JComboBox comboBoxTeam;
    private JButton okButton;
    private JButton backButton;
    private JLabel errorLabel;

    @Override
    public void showMenu() {
        frame.setContentPane(panelCloseTeam);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setTitle("Temporary closing team");

        LinkedList<String> allteams = serviceController.getTeams();
        for (int i = 0; i < allteams.size(); i++) {
            comboBoxTeam.addItem(allteams.get(i));
        }

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    serviceController.temporaryClosingTeam(comboBoxTeam.getActionCommand());
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

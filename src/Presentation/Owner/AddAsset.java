package Presentation.Owner;

import Presentation.Menu;
import Service.ServiceController;
import Exception.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class AddAsset extends JFrame implements Menu {

    private ServiceController serviceController = ServiceController.getInstance();
    private JLabel errorLabel;
    JFrame frame = new JFrame("Add Asset");
    private JButton backToAddAssetMenu;

    private JPanel panelAddAsset;
    private JRadioButton r1;
    private JRadioButton r2;
    private JRadioButton r3;
    private JRadioButton r4;
    private JButton button;
    private JLabel labelAdd;


    @Override
    public void showMenu() {
        errorLabel.setText("");
        frame.setContentPane(panelAddAsset);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setTitle("Add Asset");

        errorLabel.setText("");

        r1.setActionCommand("Team Manager");
        r2.setActionCommand("Coach");
        r3.setActionCommand("Player");
        r4.setActionCommand("Field");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panelAddAsset);
                if (r1.isSelected()) {
                    addTeamManager();
                }
                if (r2.isSelected()) {
                    addCoach();
                }
                if (r3.isSelected()) {
                    addPlayer();
                }
                if (r4.isSelected()) {
                    addField();
                } else {
                    errorLabel.setText("You need to choose asset!");
                }
            }
        });

        backToAddAssetMenu.addActionListener(new ActionListener() {
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


    private void addTeamManager() {
        AddManager addManager = new AddManager();
        addManager.showMenu();
        this.frame.dispose();
    }

    private void addCoach() {
        AddCoach addCoach = new AddCoach();
        addCoach.showMenu();
        this.frame.dispose();
    }

    private void addPlayer() {
        AddPlayer addPlayer = new AddPlayer();
        addPlayer.showMenu();
        frame.dispose();
    }

    private void addField() {
        AddField addField = new AddField();
        addField.showMenu();
        frame.dispose();
    }

}

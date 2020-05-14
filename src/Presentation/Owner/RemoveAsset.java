package Presentation.Owner;

import Presentation.Menu;
import Service.ServiceController;
import Exception.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class RemoveAsset extends JFrame implements Menu {

    private ServiceController serviceController=ServiceController.getInstance();
    private JLabel errorLabel;
    JFrame frame = new JFrame("Remove Asset");
    private JButton backButton;
    private JButton backToRemoveMenu;
    private JPanel panelRemoveAsset;
    private JRadioButton r1;
    private JRadioButton r2;
    private JRadioButton r3;
    private JRadioButton r4;
    private JButton okButton;

    @Override
    public void showMenu() {
        errorLabel.setText("");
        frame.setContentPane(panelRemoveAsset);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setTitle("Remove Asset");



        r1.setActionCommand("Team Manager");
        r2.setActionCommand("Coach");
        r3.setActionCommand("Player");
        r4.setActionCommand("Field");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panelRemoveAsset);
                if (r1.isSelected()) {
                    removeManager();
                }
                if (r2.isSelected()) {
                    removeCoach();
                }
                if (r3.isSelected()) {
                    removePlayer();
                }
                if (r4.isSelected()) {
                    removeField();
                } else {
                    errorLabel.setText("You need to choose asset!");
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

    public void backToRemoveAssetMenu(){
        RemoveAsset removeAssetMenu = new RemoveAsset();
        removeAssetMenu.showMenu();
        this.frame.dispose();
    }

    private void removeManager(){
        RemoveManager removeManager=new RemoveManager();
        removeManager.showMenu();
        frame.dispose();
    }
    private void removeCoach(){
        RemoveCoach removeCoach=new RemoveCoach();
        removeCoach.showMenu();
        frame.dispose();
    }

    private void removePlayer(){
        RemovePlayer removePlayer=new RemovePlayer();
        removePlayer.showMenu();
        frame.dispose();
    }
    private void removeField(){
        RemoveField removeField=new RemoveField();
        removeField.showMenu();
        frame.dispose();
    }

}

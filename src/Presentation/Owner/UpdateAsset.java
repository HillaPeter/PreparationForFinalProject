package Presentation.Owner;

import Presentation.Menu;
import Service.ServiceController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateAsset extends JFrame implements Menu {
    private ServiceController serviceController=ServiceController.getInstance();
    JFrame frame = new JFrame("Update Asset");
    private JPanel panelUpdateAsset;
    private JRadioButton r1;
    private JRadioButton r2;
    private JButton okButton;
    private JButton back;
    private JLabel errorLabel;

    @Override
    public void showMenu() {
        frame.setContentPane(panelUpdateAsset);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setTitle("Update Asset");

        r1.setActionCommand("Player");
        r2.setActionCommand("Field");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panelUpdateAsset);
                if(r1.isSelected()){
                    UpdatePlayer updatePlayer = new UpdatePlayer();
                    updatePlayer.showMenu();
                    frame.dispose();
                }
                if(r2.isSelected()){
                    UpdateField updateField = new UpdateField();
                    updateField.showMenu();
                    frame.dispose();
                }
               else{
                    errorLabel.setText("You don't fill everything correctly");
                }
            }
        });

        back.addActionListener(new ActionListener() {
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

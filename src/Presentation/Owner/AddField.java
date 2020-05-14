package Presentation.Owner;

import Presentation.Menu;
import Service.ServiceController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class AddField extends JFrame implements Menu {
    private JPanel panelAddField;
    private JLabel label;
    private JComboBox comboBoxTeam;
    private JTextField textFieldField;
    private JButton okButton;
    private JButton backButton;
    private JLabel errorLabel;
    private ServiceController serviceController = ServiceController.getInstance();
    JFrame frame = new JFrame("Add Field");

    @Override
    public void showMenu() {
        frame.setContentPane(panelAddField);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setTitle("Add Manager");
        errorLabel.setText("");


        LinkedList<String> allteams = serviceController.getTeams();
        for (int i = 0; i < allteams.size(); i++) {
            comboBoxTeam.addItem(allteams.get(i));
        }

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    serviceController.addField(comboBoxTeam.getActionCommand(), textFieldField.getText());
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

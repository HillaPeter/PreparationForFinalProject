package Presentation.Owner;

import Presentation.Menu;
import Service.ServiceController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class AddOutcome extends JFrame implements Menu {
    JFrame frame = new JFrame("Add Outcome");
    private ServiceController serviceController=ServiceController.getInstance();
    private JPanel panelOutcome;
    private JComboBox comboBoxTeam;
    private JLabel descLabel;
    private JTextField fieldDesc;
    private JLabel amountLabel;
    private JLabel desLabel;
    private JTextField fieldDes;
    private JTextField fieldAmount;
    private JButton okButton;
    private JButton backButton;
    private JLabel errorLabel;

    @Override
    public void showMenu() {
        frame.setContentPane(panelOutcome);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setTitle("Add Outcome");
        errorLabel.setText("");

        LinkedList<String> allteams = serviceController.getTeams();
        for (int i = 0; i < allteams.size(); i++) {
            comboBoxTeam.addItem(allteams.get(i));
        }

        try{
            Double.parseDouble(fieldAmount.getText());
        }catch (NumberFormatException e){
            errorLabel.setText("You need to enter amount in numbers");
        }
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    serviceController.addOutcome(comboBoxTeam.getActionCommand(),fieldDes.getText(),Double.parseDouble(fieldAmount.getText()));
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

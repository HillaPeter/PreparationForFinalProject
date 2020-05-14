package Presentation.Owner;

import Presentation.Menu;
import Service.ServiceController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class AddIncome extends JFrame implements Menu {
    JFrame frame = new JFrame("Add Income");
    private ServiceController serviceController=ServiceController.getInstance();
    private JPanel panelAddIncome;
    private JComboBox comboBoxTeam;
    private JLabel amountLabel;
    private JLabel desLabel;
    private JTextField fieldDes;
    private JTextField fieldAmount;
    private JButton okButton;
    private JButton backButton;
    private JLabel errorLabel;

    @Override
    public void showMenu() {
        frame.setContentPane(panelAddIncome);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setTitle("Add Income");
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
                    serviceController.addIncome(comboBoxTeam.getActionCommand(),fieldDes.getText(),Double.parseDouble(fieldAmount.getText()));
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

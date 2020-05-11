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
    JFrame frame = new JFrame("OwnerMenu");
    private ServiceController serviceController=ServiceController.getInstance();
    private JButton btnAddTeamManager;
    private JPanel panelOwner;
    private JButton addTeamManagerButton;

    @Override
    public void showMenu() {
        frame.setContentPane(this.panelOwner);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400, 400);
        frame.setVisible(true);


        btnAddTeamManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    remove(panelOwner);
                    addTeamManager();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    @Override
    public void exitMenu() {

    }

    public void addTeamManager() throws DontHavePermissionException {
        JPanel panelAddSystem = new JPanel();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("add Team manager");

        JLabel label=new JLabel("which user you want to make a team manager:");
        panelAddSystem.add(label , BorderLayout.NORTH);
        add(panelAddSystem , BorderLayout.NORTH);

        JComboBox comboBox=new JComboBox();
        LinkedList<String> allThePosibolleTeamManagers=serviceController.addTeamManagerComboBox();
        for (int i = 0; i < allThePosibolleTeamManagers.size(); i++) {
            comboBox.addItem(allThePosibolleTeamManagers.get(i));
        }

        panelAddSystem.add(comboBox,  BorderLayout.CENTER); // comboBox added to transparent frame
        this.add(panelAddSystem, BorderLayout.CENTER);

        JButton button =new JButton("ok");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panelAddSystem);
                showMenu();
            }
        });
        panelAddSystem.add(button);
        add(panelAddSystem,BorderLayout.SOUTH);

        this.pack();
        setSize(400, 200);
    }
}

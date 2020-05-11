package Presentation.Owner;
import Exception.*;
import Presentation.Menu;
import Service.ServiceController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class OwnerMenu extends JFrame implements Menu {
    JFrame frame = new JFrame("OwnerMenu");
    private ServiceController serviceController=ServiceController.getInstance();
    private JButton btnAddAsset;
    private JPanel panelOwner;
    private JButton removeAssetButton;
    private JButton updateAssetButton;
    private JButton backButton;
    private JButton addNewManagerButton1;
    private JButton removeManagerButton;
    private JButton addIncomeButton;
    private JButton temporaryCloseTeamButton;
    private JButton addOutcomeButton;
    private JButton addNewOwnerButton;
    private JButton reopenClosedTeamButton;

    @Override
    public void showMenu() {
        frame.setContentPane(this.panelOwner);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400, 400);
        frame.setVisible(true);


        btnAddAsset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    remove(panelOwner);
                    addAsset();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

    }


    public void addAsset() throws DontHavePermissionException {
//        JPanel panelAddSystem = new JPanel();
//        setVisible(true);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setTitle("add Team manager");
//
//        JLabel label=new JLabel("which user you want to make a team manager:");
//        panelAddSystem.add(label , BorderLayout.NORTH);
//        add(panelAddSystem , BorderLayout.NORTH);
//
//        JComboBox comboBox=new JComboBox();
//        LinkedList<String> allThePosibolleTeamManagers=serviceController.addTeamManagerComboBox();
//        for (int i = 0; i < allThePosibolleTeamManagers.size(); i++) {
//            comboBox.addItem(allThePosibolleTeamManagers.get(i));
//        }
//
//        panelAddSystem.add(comboBox,  BorderLayout.CENTER); // comboBox added to transparent frame
//        this.add(panelAddSystem, BorderLayout.CENTER);
//
//        JButton button =new JButton("ok");
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                remove(panelAddSystem);
//                showMenu();
//            }
//        });
//        panelAddSystem.add(button);
//        add(panelAddSystem,BorderLayout.SOUTH);
//
//        this.pack();
//        setSize(400, 200);
    }


    @Override
    public void exitMenu() {

    }
}

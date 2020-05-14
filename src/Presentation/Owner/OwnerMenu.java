package Presentation.Owner;
import Domain.Users.Guest;
import Exception.*;
import Presentation.Guest.GuestMenu;
import Presentation.Menu;
import Service.ServiceController;
import com.sun.javafx.scene.control.behavior.TwoLevelFocusBehavior;

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

        removeAssetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    remove(panelOwner);
                    removeAsset();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        updateAssetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    remove(panelOwner);
                    updateAsset();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        addNewManagerButton1.addActionListener(new ActionListener() {
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

        removeManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    remove(panelOwner);
                    removeTeamManager();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        addIncomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    remove(panelOwner);
                    addIncome();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        addOutcomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    remove(panelOwner);
                    addOutcome();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        temporaryCloseTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    remove(panelOwner);
                    temporaryCloseTeam();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        reopenClosedTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    remove(panelOwner);
                    reopenClosedTeam();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        addNewOwnerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    remove(panelOwner);
                    addNewOwner();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    remove(panelOwner);
                    exitMenu();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }


    private void addAsset(){
        AddAsset addAsset = new AddAsset();
        addAsset.showMenu();
        this.frame.dispose();
    }

    private void addTeamManager() {
      AddManager addManager=new AddManager();
      addManager.showMenu();
      frame.dispose();
    }

    private void removeTeamManager() {
        RemoveManager removeManager=new RemoveManager();
        removeManager.showMenu();
        frame.dispose();
    }

    private void removeAsset() {
        RemoveAsset removeAsset = new RemoveAsset();
        removeAsset.showMenu();
        this.frame.dispose();
    }

    private void updateAsset(){
        UpdateAsset updateAsset=new UpdateAsset();
        updateAsset.showMenu();
        frame.dispose();
    }

    private void addIncome() {
        AddIncome addIncome=new AddIncome();
        addIncome.showMenu();
        frame.dispose();
    }
    private void addOutcome() {
        AddOutcome addOutcome=new AddOutcome();
        addOutcome.showMenu();
        frame.dispose();
    }

    private void temporaryCloseTeam() {
        TemporaryClosingTeam temporaryClosingTeam=new TemporaryClosingTeam();
        temporaryClosingTeam.showMenu();
        frame.dispose();
    }

    private void reopenClosedTeam() {
        ReopenClosedTeam reopenClosedTeam=new ReopenClosedTeam();
        reopenClosedTeam.showMenu();
        frame.dispose();
    }

    private void addNewOwner() {
        AddNewOwner addNewOwner=new AddNewOwner();
        addNewOwner.showMenu();
        frame.dispose();
    }


    @Override
    public void exitMenu() {
        GuestMenu guestMenu = new GuestMenu();
        guestMenu.showMenu();
        this.frame.dispose();
    }
}

package Presentation.SystemManager;

import Presentation.Menu;
import Service.ServiceController;
import Exception.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class SystemManagerMenu extends JFrame implements Menu {
    //private JPanel panel;
    private ServiceController serviceController=ServiceController.getInstance();
    private boolean check=false;


    @Override
    public void showMenu()   {
      //  if(check==true) {
        //    this.dispose();
       // }
        JPanel panel = new JPanel();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("System manager menu");
        setSize(400, 400);

        JButton btnAddSystem = new JButton("btnAddSystem");
        panel.add(btnAddSystem);
        btnAddSystem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    remove(panel);
                    addSystemManager();
                } catch (DontHavePermissionException e1) {
                    e1.printStackTrace();
                } catch (IncorrectInputException e1) {
                    e1.printStackTrace();
                } catch (AlreadyExistException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton btnRemoveSytem = new JButton("btnRemoveSytem");
        panel.add(btnRemoveSytem);
        btnRemoveSytem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    removeSystemManager();
                } catch (DontHavePermissionException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton btnAddAssocation = new JButton("btnAddAssocation");
        panel.add(btnAddAssocation);
        btnAddAssocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addAssociationDeligate();
                } catch (DontHavePermissionException e1) {
                    e1.printStackTrace();
            }
            }
        });

        JButton btnRemoveAssocaition = new JButton("btnRemoveAssocaition");
        panel.add(btnRemoveAssocaition);
        btnRemoveAssocaition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        JButton btnAddReferee = new JButton("btnAddReferee");
        panel.add(btnAddReferee);
        btnAddReferee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addReferee();
                } catch (DontHavePermissionException e1) {
                    e1.printStackTrace();
            }
            }
        });

        JButton btnRemoveReferee = new JButton("btnRemoveReferee");
        panel.add(btnRemoveReferee);
        btnRemoveReferee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    removeReferee();
            }
        });

        JButton btnRemoveMemeber = new JButton("btnRemoveMemeber");
        panel.add(btnRemoveMemeber);
        btnRemoveMemeber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    removeMemebr();
                } catch (DontHavePermissionException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton btnAddNewTeam = new JButton("btnAddNewTeam");
        panel.add(btnAddNewTeam);
        btnAddNewTeam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createNewTeam();
                } catch (DontHavePermissionException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton btnCloseTeam = new JButton("btnCloseTeam");
        panel.add(btnCloseTeam);
        btnCloseTeam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeTeam();
            }
        });

        JButton btnViewInfo = new JButton("btnViewInfo");
        panel.add(btnViewInfo);
        btnViewInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton btnComplimant = new JButton("btnComplimant");
        panel.add(btnComplimant);
        btnComplimant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });


        add(panel,BorderLayout.SOUTH);

    }

    @Override
    public void exitMenu() {
        check=false;

    }

    public void addSystemManager() throws DontHavePermissionException, IncorrectInputException, AlreadyExistException {
     //   check=true;
      //  this.dispose();
        JPanel panelAddSystem = new JPanel();
       setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("add System Manager");

        JLabel label=new JLabel("which user you want to make a system manager:");
        panelAddSystem.add(label , BorderLayout.NORTH);
        add(panelAddSystem , BorderLayout.NORTH);

        JComboBox comboBox=new JComboBox();
        LinkedList<String> allThePosibolleSystemManager=serviceController.addSystemManagerComboBox();
        for (int i = 0; i < allThePosibolleSystemManager.size(); i++) {
            comboBox.addItem(allThePosibolleSystemManager.get(i));
        }

        panelAddSystem.add(comboBox,  BorderLayout.CENTER); // comboBox added to transparent frame
        this.add(panelAddSystem, BorderLayout.CENTER);
        //this.pack();

        JButton button =new JButton("ok" );
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

    public void removeSystemManager() throws DontHavePermissionException {

        JPanel panelRemoveSystem = new JPanel();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("remove System Manager");

        JLabel label=new JLabel("which system manager would you like to remove:");
        panelRemoveSystem.add(label , BorderLayout.NORTH);
        add(panelRemoveSystem , BorderLayout.NORTH);

        JComboBox comboBox=new JComboBox();
        LinkedList<String> allThePosibolleSystemManager=serviceController.removeSystemManagerComboBox();
        for (int i = 0; i < allThePosibolleSystemManager.size(); i++) {
            comboBox.addItem(allThePosibolleSystemManager.get(i));
        }

        panelRemoveSystem.add(comboBox,  BorderLayout.CENTER); // comboBox added to transparent frame
        this.add(panelRemoveSystem, BorderLayout.CENTER);
        //this.pack();

        JButton button =new JButton("ok" );
        panelRemoveSystem.add(button);
        add(panelRemoveSystem,BorderLayout.SOUTH);

        this.pack();
        setSize(400, 200);
    }

    public void addAssociationDeligate() throws DontHavePermissionException {
        JPanel panelAddAssocaition = new JPanel();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Add Association Deligate");

        JLabel label=new JLabel("which user would you like to become an association deligate:");
        panelAddAssocaition.add(label , BorderLayout.NORTH);
        add(panelAddAssocaition , BorderLayout.NORTH);

        JComboBox comboBox=new JComboBox();
        LinkedList<String> allThePosibolleSystemManager=serviceController.addAssociationDeligateComboBox();
        for (int i = 0; i < allThePosibolleSystemManager.size(); i++) {
            comboBox.addItem(allThePosibolleSystemManager.get(i));
        }

        panelAddAssocaition.add(comboBox,  BorderLayout.CENTER); // comboBox added to transparent frame
        this.add(panelAddAssocaition, BorderLayout.CENTER);
        //this.pack();

        JButton button =new JButton("ok" );
        panelAddAssocaition.add(button);
        add(panelAddAssocaition,BorderLayout.SOUTH);

        this.pack();
        setSize(400, 200);
    }

    public void removeAssociationDeligate() throws DontHavePermissionException {
        JPanel panelremoveAssociation = new JPanel();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("remove Association Deligate");

        JLabel label=new JLabel("which assocaition deligate would you like to remove:");
        panelremoveAssociation.add(label , BorderLayout.NORTH);
        add(panelremoveAssociation , BorderLayout.NORTH);

        JComboBox comboBox=new JComboBox();
        LinkedList<String> allThePosibolleSystemManager=serviceController.removeAssociationDeligateComboBox();
        for (int i = 0; i < allThePosibolleSystemManager.size(); i++) {
            comboBox.addItem(allThePosibolleSystemManager.get(i));
        }

        panelremoveAssociation.add(comboBox,  BorderLayout.CENTER); // comboBox added to transparent frame
        this.add(panelremoveAssociation, BorderLayout.CENTER);
        //this.pack();

        JButton button =new JButton("ok" );
        panelremoveAssociation.add(button);
        add(panelremoveAssociation,BorderLayout.SOUTH);

        this.pack();
        setSize(400, 200);
    }

    public void removeReferee() {
        JPanel panelremoveReferee = new JPanel();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("remove Referee");

        JLabel label=new JLabel("which referee would you like to remove:");
        panelremoveReferee.add(label , BorderLayout.NORTH);
        add(panelremoveReferee , BorderLayout.NORTH);

        JComboBox comboBox=new JComboBox();
        LinkedList<String> allThePosibolleSystemManager=serviceController.removeRefereeComboBox();
        for (int i = 0; i < allThePosibolleSystemManager.size(); i++) {
            comboBox.addItem(allThePosibolleSystemManager.get(i));
        }

        panelremoveReferee.add(comboBox,  BorderLayout.CENTER); // comboBox added to transparent frame
        this.add(panelremoveReferee, BorderLayout.CENTER);
        //this.pack();

        JButton button =new JButton("ok" );
        panelremoveReferee.add(button);
        add(panelremoveReferee,BorderLayout.SOUTH);

        this.pack();
        setSize(400, 200);
    }

    public void addReferee() throws DontHavePermissionException {
        JPanel panelAddReferee = new JPanel();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Add Referee");

        JLabel label=new JLabel("which user would you like to become areferee:");
        panelAddReferee.add(label , BorderLayout.NORTH);
        add(panelAddReferee , BorderLayout.NORTH);

        JComboBox comboBox=new JComboBox();
        LinkedList<String> allThePosibolleSystemManager=serviceController.addRefereeComboBox();
        for (int i = 0; i < allThePosibolleSystemManager.size(); i++) {
            comboBox.addItem(allThePosibolleSystemManager.get(i));
        }

        panelAddReferee.add(comboBox,  BorderLayout.CENTER); // comboBox added to transparent frame
        this.add(panelAddReferee, BorderLayout.CENTER);
        //this.pack();

        JButton button =new JButton("ok" );
        panelAddReferee.add(button);
        add(panelAddReferee,BorderLayout.SOUTH);

        this.pack();
        setSize(400, 200);
    }

    public void removeMemebr() throws DontHavePermissionException {
        JPanel removeMember = new JPanel();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("remove member");

        JLabel label=new JLabel("which member would you like to remove:");
        removeMember.add(label , BorderLayout.NORTH);
        add(removeMember , BorderLayout.NORTH);

        JComboBox comboBox=new JComboBox();
        LinkedList<String> allThePosibolleSystemManager=serviceController.removeMemberComboBox();
        for (int i = 0; i < allThePosibolleSystemManager.size(); i++) {
            comboBox.addItem(allThePosibolleSystemManager.get(i));
        }

        removeMember.add(comboBox,  BorderLayout.CENTER); // comboBox added to transparent frame
        this.add(removeMember, BorderLayout.CENTER);
        //this.pack();

        JButton button =new JButton("ok" );
        removeMember.add(button);
        add(removeMember,BorderLayout.SOUTH);

        this.pack();
        setSize(400, 200);
    }

    public void closeTeam()
    {
        JPanel removeTeam = new JPanel();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("remove team");

        JLabel label=new JLabel("which team would you like to remove:");
        removeTeam.add(label , BorderLayout.NORTH);
        add(removeTeam , BorderLayout.NORTH);

        JComboBox comboBox=new JComboBox();
        LinkedList<String> allThePosibolleSystemManager=serviceController.removeTeamComboBox();
        for (int i = 0; i < allThePosibolleSystemManager.size(); i++) {
            comboBox.addItem(allThePosibolleSystemManager.get(i));
        }

        removeTeam.add(comboBox,  BorderLayout.CENTER); // comboBox added to transparent frame
        this.add(removeTeam, BorderLayout.CENTER);
        //this.pack();

        JButton button =new JButton("ok" );
        removeTeam.add(button);
        add(removeTeam,BorderLayout.SOUTH);

        this.pack();
        setSize(400, 200);
    }

    public void createNewTeam() throws DontHavePermissionException {
        JPanel AddTeam = new JPanel();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("add new team");

        JLabel label=new JLabel("which member would you like to be your team owner:");
        AddTeam.add(AddTeam , BorderLayout.PAGE_START);
     //   add(label, BorderLayout.PAGE_START);

        JComboBox comboBox=new JComboBox();
        LinkedList<String> allThePosibolleSystemManager=serviceController.addTeamComboBox();
        for (int i = 0; i < allThePosibolleSystemManager.size(); i++) {
            comboBox.addItem(allThePosibolleSystemManager.get(i));
        }

        AddTeam.add(comboBox,  BorderLayout.AFTER_LINE_ENDS); // comboBox added to transparent frame
      //  this.add(AddTeam, BorderLayout.AFTER_LINE_ENDS);
        //this.pack();

        AddTeam.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        JLabel label2=new JLabel("please enter your team name:");
        AddTeam.add(label2 ,BorderLayout.LINE_START);
       // add(AddTeam,BorderLayout.LINE_START);

        JTextField textField=new JTextField(20 );
        textField.setBounds(0,0,15,13);
        AddTeam.add(textField ,BorderLayout.CENTER);
    //    add(AddTeam , BorderLayout.CENTER);

        JButton button =new JButton("ok" );
        AddTeam.add(button);

        add(AddTeam);//,BorderLayout.PAGE_END);

        this.pack();
        setSize(400, 200);
    }



    private void viewComplaint()
    {

    }
    private void viewSystemInformation()
    {

    }


}
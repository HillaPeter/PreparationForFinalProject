package Presentation.Guest;

import Presentation.Menu;
import com.sun.crypto.provider.JceKeyStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestMenu extends JFrame implements Menu {
    private JButton button1;
    private JComboBox comboBox1;
    private JSplitPane splitpane;


    public GuestMenu() {
        JPanel panel = new JPanel();
        splitpane = new JSplitPane();
        button1 = new JButton();
        panel.add(button1);


        setTitle("My Gui");
        setSize(400, 400);

        // Create JButton and JPanel
        JButton button = new JButton("Click here!");

        // Add button to JPanel
        panel.add(button);
        // And JPanel needs to be added to the JFrame itself!
        this.getContentPane().add(panel);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    @Override
    public void showMenu() {

    }

    @Override
    public void exitMenu() {

    }
}

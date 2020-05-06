package Presentation;

import Presentation.Guest.GuestMenu;

import javax.swing.*;

public class mainPresentation {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GuestMenu frame = new GuestMenu();
                frame.setVisible(true);
            }
        });
    }
}

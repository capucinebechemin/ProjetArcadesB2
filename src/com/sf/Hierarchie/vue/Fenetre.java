package com.sf.Hierarchie.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {
    public static final int ID = 3;
    public Fenetre() {
        initFenetre();
    }
    private void initFenetre() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // positionement + taille
        setBounds(250, 50, 900, 900);
        setTitle("Ecran d'instruction");

        // creation du panel
        JPanel monPanel = new JPanel();
        setContentPane(monPanel);
        // definition du layout
        setLayout(null);
        monPanel.setBackground(new Color(172, 181, 186));

        JLabel titre = new JLabel("Instructions");
        titre.setBounds(200, 10, 200, 50);
        monPanel.add(titre);

        JTextArea regles = new JTextArea("Lorem ipsum dolor sit amet, consectetur adipiscing elit," + '\n'+" sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim " + '\n'+" ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip " + '\n'+ "ommodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit  " + '\n'+" fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, " + '\n'+"qui officia deserunt mollit anim id est laborum.");
        regles.setForeground(new Color(255, 255, 255));
        regles.setBackground(new Color(70, 156, 186));
        regles.setBounds(50, 50, 400, 300);
        monPanel.add(regles);

        JButton retour = new JButton("Retour");
        retour.setForeground(new Color(255, 255, 255));
        retour.setFont(new Font("Tahoma", Font.BOLD, 15));
        retour.setBackground(new Color(32, 85, 109));
        retour.setBounds(100, 400, 100, 50);
        monPanel.add(retour);

        JButton option = new JButton("Options");
        option.setForeground(new Color(255, 255, 255));
        option.setFont(new Font("Tahoma", Font.BOLD, 15));
        option.setBackground(new Color(32, 85, 109));
        option.setBounds(300, 400, 100, 50);
        monPanel.add(option);

        setVisible(true);

        option.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FOptions foption = new FOptions();
                dispose();
            }
        });

        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


    }

    public static int getID() {
        return ID;
    }
}

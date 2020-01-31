package com.sf.Hierarchie.vue;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {
    public static final int ID = 3;
    public Fenetre() {
        initFenetre();
    }
    private void initFenetre() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // positionement + taille
        setBounds(50, 50, 500, 500);
        setTitle("Ecran d'instruction");

        // creation du panel
        JPanel monPanel = new JPanel();
        setContentPane(monPanel);
        // definition du layout
        setLayout(null);

        JLabel titre = new JLabel("Instructions");
        titre.setBounds(200, 10, 200, 50);
        monPanel.add(titre);

        JTextArea regles = new JTextArea("Lorem ipsum dolor sit amet, consectetur adipiscing elit," + '\n'+" sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim " + '\n'+" ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip " + '\n'+ "ommodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit  " + '\n'+" fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, " + '\n'+"qui officia deserunt mollit anim id est laborum.");
        regles.setForeground(new Color(255, 255, 255));
        regles.setBackground(new Color(43, 71, 98));
        regles.setBounds(50, 50, 400, 300);
        monPanel.add(regles);

        JButton jouer = new JButton("Retour");
        jouer.setForeground(new Color(255, 255, 255));
        jouer.setFont(new Font("Tahoma", Font.BOLD, 15));
        jouer.setBackground(new Color(103, 189, 235));
        jouer.setBounds(100, 400, 100, 50);
        monPanel.add(jouer);

        JButton option = new JButton("Options");
        option.setBounds(300, 400, 100, 50);
        monPanel.add(option);

        setVisible(true);

    }

    public static int getID() {
        return ID;
    }
}

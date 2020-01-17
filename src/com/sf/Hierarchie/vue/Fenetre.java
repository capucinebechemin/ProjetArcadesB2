package com.sf.Hierarchie.vue;

import javax.swing.*;

public class Fenetre extends JFrame {
    public Fenetre() {
        initFenetre();
    }
    private void initFenetre() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // positionement + taille
        setBounds(50, 50, 500, 500);
        setTitle("Ecran d'accueil");

        // creation du panel
        JPanel monPanel = new JPanel();
        setContentPane(monPanel);
        // definition du layout
        setLayout(null);

        JButton jouer = new JButton("Jouer");
        jouer.setBounds(150, 50, 200, 50);
        monPanel.add(jouer);

        JButton option = new JButton("Options");
        option.setBounds(150, 150, 200, 50);
        monPanel.add(option);

        JButton pres = new JButton("Instructions");
        pres.setBounds(150, 250, 200, 50);
        monPanel.add(pres);

        setVisible(true);



    }
}

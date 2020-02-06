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
        monPanel.setBackground(new Color(197, 201, 212));

        JLabel titre = new JLabel("INSTRUCTIONS");
        titre.setForeground(new Color(0, 0, 0));
        titre.setBounds(20, 100, 700, 50);
        titre.setFont(new Font("Tahoma", Font.BOLD, 30));
        monPanel.add(titre);

        JTextArea regles = new JTextArea("Bienvenue dans le Donjon ! \n Koopa le squelette et l'agent James se combattent dans ce donjon de pierre. \n Il s'y trouve le joyaux tant convoité de la renaissance. \n Les morts pourront-ils revenir à la vie grâce à Koopa ou le joyaux sera remis aux autoritées \n par James permettant ainsi le maintient du cycle naturel de la vie. \n \n Koopa sera dirigé par les commandes rouge et James par les bleues. \n Les joysticks vous permettent de vous déplacer dans votre moitié d'écran et le bouton à \n droite d'envoyer votre projectile. \n Attention lorsque vous envoyer un projectile \n vous êtes vulnérable, si vous bouger votre balle s'arrete dans sa course. \n Votre barre de vie est visible en haut de l'écran lorsqu'elle sera vide la partie s'arrête. \n Les trois boutons présent à l'avant permettent de naviguer dans le menu. ");
        regles.setForeground(new Color(0, 0, 0));
        regles.setBackground(new Color(197, 201, 212));
        regles.setBounds(50, 200, 760, 300);
        regles.setFont(new Font("Tahoma", Font.BOLD, 15));
        monPanel.add(regles);

        JButton retour = new JButton("Retour");
        retour.setForeground(new Color(255, 255, 255));
        retour.setFont(new Font("Tahoma", Font.BOLD, 15));
        retour.setBackground(new Color(5, 21, 46));
        retour.setBounds(100, 700, 150, 50);
        monPanel.add(retour);

        JButton option = new JButton("Options");
        option.setForeground(new Color(255, 255, 255));
        option.setFont(new Font("Tahoma", Font.BOLD, 15));
        option.setBackground(new Color(5, 21, 46));
        option.setBounds(300, 700, 150, 50);
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
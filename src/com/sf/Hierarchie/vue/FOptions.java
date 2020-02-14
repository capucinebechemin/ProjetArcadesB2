package com.sf.Hierarchie.vue;
import com.sf.Hierarchie.model.LienBDD;
import com.sf.Hierarchie.model.Personnage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FOptions extends JFrame {

    public static final int ID = 2;
    public FOptions() {
        initFenetre();
    }
    private void initFenetre() {
        DefaultListModel liste_personnages = new DefaultListModel();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // positionement + taille
        setBounds(280, 40, 900, 900);
        setTitle("Ecran d'options");

        // creation du panel
        JPanel monPanel = new JPanel();
        monPanel.setForeground(new Color(0, 0, 0));
        monPanel.setFont(new Font("Tahoma", Font.BOLD, 15));
        monPanel.setBackground(new Color(197, 201, 212));
        setContentPane(monPanel);
        // definition du layout
        setLayout(null);
        //Ajout des labels et des zones de texte
        JLabel titre = new JLabel("OPTIONS");
        titre.setForeground(new Color(0, 0, 0));
        titre.setBounds(20, 50, 200, 50);
        titre.setFont(new Font("Tahoma", Font.BOLD, 30));
        monPanel.add(titre);

        JLabel info = new JLabel("Si le texte apparait en rouge la donnée n'a pas été prise en compte");
        info.setForeground(new Color(0, 0, 0));
        info.setBounds(20, 100, 2000, 50);
        info.setFont(new Font("Tahoma", Font.ITALIC, 15));
        monPanel.add(info);

        JLabel monLabel = new JLabel();
        monLabel.setText("Vitesse Personnage : Entre 1 et 9");
        // positionner le label
        monLabel.setBounds(100, 150, 200, 20);
        monLabel.setForeground(Color.black);
        monPanel.add(monLabel);

        JTextField monTexte = new JTextField();
        monTexte.setBounds(100, 200, 100, 40);
        monPanel.add(monTexte);


        JLabel monLabel2 = new JLabel();
        monLabel2.setText("Points de vie : Entre 30 et 100");
        monLabel.setForeground(Color.black);

        // positionner le label
        monLabel2.setBounds(100, 250, 200, 20);
        monPanel.add(monLabel2);

        JTextField monTexte2 = new JTextField();
        monTexte2.setBounds(100, 300, 100, 40);
        monPanel.add(monTexte2);


        JLabel monLabel4 = new JLabel();
        monLabel4.setText("Puissance Projectile : Entre 10 et 50");
        monLabel.setForeground(Color.black);

        // positionner le label
        monLabel4.setBounds(100, 450, 210, 20);
        monPanel.add(monLabel4);

        JTextField monTexte4= new JTextField();
        monTexte4.setBounds(100, 500, 100, 40);
        monPanel.add(monTexte4);


        JLabel monLabel6 = new JLabel();
        monLabel6.setText("Vitesse Projectile : Entre 1 et 3");
        monLabel.setForeground(Color.black);

        // positionner le label
        monLabel6.setBounds(100, 350, 200, 20);
        monPanel.add(monLabel6);

        JTextField monTexte6 = new JTextField();
        monTexte6.setBounds(100, 400, 100, 40);
        monPanel.add(monTexte6);

        ArrayList<Personnage> liste = LienBDD.selectAll();
        //Liste des personnages du jeu reliée à la base de données
        for(Personnage p : liste){
            liste_personnages.addElement(p);
        }

        JList liste_perso = new JList(liste_personnages);
        liste_perso.setBounds(500, 200, 150, 200);
        monPanel.add(liste_perso);

        /**
         *Ajout des boutons
         */
        JButton retour = new JButton("Retour");
        retour.setForeground(new Color(255, 255, 255));
        retour.setFont(new Font("Tahoma", Font.BOLD, 15));
        retour.setBackground(new Color(5, 21, 46));
        retour.setBounds(100, 600, 150, 50);
        monPanel.add(retour);

        JButton valider = new JButton("Modifier");
        valider.setForeground(new Color(255, 255, 255));
        valider.setFont(new Font("Tahoma", Font.BOLD, 15));
        valider.setBackground(new Color(5, 21, 46));
        valider.setBounds(300, 600, 150, 50);
        monPanel.add(valider);

        JButton instruction = new JButton("Instructions");
        instruction.setForeground(new Color(255, 255, 255));
        instruction.setFont(new Font("Tahoma", Font.BOLD, 15));
        instruction.setBackground(new Color(5, 21, 46));
        instruction.setBounds(500, 600, 150, 50);
        monPanel.add(instruction);

        setVisible(true);

        /**
         *Validation des nouvelles caractéristiques des personnages
         */
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Personnage p = (Personnage) liste_perso.getModel().getElementAt(liste_perso.getSelectedIndex());
                int vp = Integer.parseInt(monTexte.getText());
                if(vp >= 1 && vp <= 9){
                    p.setVitesse_personnage(vp);
                    monLabel.setForeground(Color.black);

                }
                else{
                    monLabel.setForeground(Color.red);
                }
                p.setVitesse_personnage(vp);
                int pv = Integer.parseInt(monTexte2.getText());
                if(pv >= 30 && pv <= 100){
                    p.setPoints_vie(pv);
                    monLabel2.setForeground(Color.black);

                }
                else{
                    monLabel2.setForeground(Color.red);
                }

                int pp = Integer.parseInt(monTexte4.getText());
                if(pp >= 10 && pp <= 50){
                    p.setPuissance_tir(pp);
                    monLabel4.setForeground(Color.black);

                }
                else{
                    monLabel4.setForeground(Color.red);
                }

                int vip = Integer.parseInt(monTexte6.getText());
                if(vip >= 1 && vip <= 3){
                    p.setVitesse_projectil(vip);
                    monLabel6.setForeground(Color.black);

                }
                else{
                    monLabel6.setForeground(Color.red);
                }
                //Appelle de la requete Update de la base de données
                LienBDD.update(p);
                ArrayList<Personnage> liste = LienBDD.selectAll();
                liste_personnages.clear();
                for(Personnage pers : liste){
                    liste_personnages.addElement(pers);
                }
                //Remise à vide des champs de texte
                monTexte.setText("");
                monTexte2.setText("");
                monTexte4.setText("");
                monTexte6.setText("");
            }
        });

        /**
         *Fermeture de cette fenetre et ouverture de la fenetre d'informations.
         */
        instruction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fenetre fenetre = new Fenetre();
                dispose();
            }
        });

        /**
         *Fermeture de cette fenetre et retour au menu principal
         */
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
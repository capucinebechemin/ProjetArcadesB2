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
        setBounds(50, 50, 960, 960);
        setTitle("Ecran d'options");

        // creation du panel
        JPanel monPanel = new JPanel();
        monPanel.setForeground(new Color(0, 0, 0));
        monPanel.setFont(new Font("Tahoma", Font.BOLD, 15));
        monPanel.setBackground(new Color(197, 201, 212));
        setContentPane(monPanel);
        // definition du layout
        setLayout(null);

        JLabel titre = new JLabel("OPTIONS");
        titre.setForeground(new Color(0, 0, 0));
        titre.setBounds(20, 100, 200, 50);
        titre.setFont(new Font("Tahoma", Font.BOLD, 30));
        monPanel.add(titre);

        JLabel monLabel = new JLabel();
        monLabel.setText("Vitesse Personnage :");
        // positionner le label
        monLabel.setBounds(100, 200, 200, 20);
        monPanel.add(monLabel);

        JTextField monTexte = new JTextField();
        monTexte.setBounds(100, 250, 100, 40);
        monPanel.add(monTexte);


        JLabel monLabel2 = new JLabel();
        monLabel2.setText("Points de vie :");
        // positionner le label
        monLabel2.setBounds(100, 300, 200, 20);
        monPanel.add(monLabel2);

        JTextField monTexte2 = new JTextField();
        monTexte2.setBounds(100, 350, 100, 40);
        monPanel.add(monTexte2);


        JLabel monLabel4 = new JLabel();
        monLabel4.setText("Puissance Projectile :");
        // positionner le label
        monLabel4.setBounds(100, 500, 200, 20);
        monPanel.add(monLabel4);

        JTextField monTexte4= new JTextField();
        monTexte4.setBounds(100, 550, 100, 40);
        monPanel.add(monTexte4);


        JLabel monLabel6 = new JLabel();
        monLabel6.setText("Vitesse Projectile :");
        // positionner le label
        monLabel6.setBounds(100, 400, 200, 20);
        monPanel.add(monLabel6);

        JTextField monTexte6 = new JTextField();
        monTexte6.setBounds(100, 450, 100, 40);
        monPanel.add(monTexte6);

        ArrayList<Personnage> liste = LienBDD.selectAll();

        for(Personnage p : liste){
            liste_personnages.addElement(p);
        }

        JList liste_perso = new JList(liste_personnages);
        liste_perso.setBounds(500, 250, 150, 200);
        monPanel.add(liste_perso);


        JButton retour = new JButton("Retour");
        retour.setForeground(new Color(255, 255, 255));
        retour.setFont(new Font("Tahoma", Font.BOLD, 15));
        retour.setBackground(new Color(5, 21, 46));
        retour.setBounds(100, 700, 150, 50);
        monPanel.add(retour);

        JButton valider = new JButton("Modifier");
        valider.setForeground(new Color(255, 255, 255));
        valider.setFont(new Font("Tahoma", Font.BOLD, 15));
        valider.setBackground(new Color(5, 21, 46));
        valider.setBounds(300, 700, 150, 50);
        monPanel.add(valider);

        JButton instruction = new JButton("Instructions");
        instruction.setForeground(new Color(255, 255, 255));
        instruction.setFont(new Font("Tahoma", Font.BOLD, 15));
        instruction.setBackground(new Color(5, 21, 46));
        instruction.setBounds(500, 700, 150, 50);
        monPanel.add(instruction);

        setVisible(true);


        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Personnage p = (Personnage) liste_perso.getModel().getElementAt(liste_perso.getSelectedIndex());
                int vp = Integer.parseInt(monTexte.getText());
                p.setVitesse_personnage(vp);
                int pv = Integer.parseInt(monTexte2.getText());
                p.setPoints_vie(pv);
//                int dt = Integer.parseInt(monTexte3.getText());
//                p.setDelai_tir(dt);
                int pp = Integer.parseInt(monTexte4.getText());
                p.setPuissance_tir(pp);
//                int vr = Integer.parseInt(monTexte5.getText());
//                p.setVitesse_rotaion(vr);
                int vip = Integer.parseInt(monTexte6.getText());
                p.setVitesse_projectil(vip);

                LienBDD.update(p);
                ArrayList<Personnage> liste = LienBDD.selectAll();
                liste_personnages.clear();
                for(Personnage pers : liste){
                    liste_personnages.addElement(pers);
                }

                monTexte.setText("");
                monTexte2.setText("");
//                monTexte3.setText("");
                monTexte4.setText("");
//                monTexte5.setText("");
                monTexte6.setText("");
            }
        });


        instruction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fenetre fenetre = new Fenetre();
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

    public void controllerButtonPressed(int controller, int button) {
        System.out.println(controller);
        System.out.println(button);

        switch (button) {
            case 1:
                System.out.println("test");
                break;
            case 2: Fenetre fenetre = new Fenetre();
                dispose();
                break;
            case 3: dispose();
                break;
        }
    }
}
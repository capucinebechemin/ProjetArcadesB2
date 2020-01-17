package com.sf.Hierarchie.vue;
import com.sf.Hierarchie.model.LienBDD;
import com.sf.Hierarchie.model.Personnage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FOptions extends JFrame {
    public FOptions() {
        initFenetre();
    }
    private void initFenetre() {
        DefaultListModel liste_personnages = new DefaultListModel();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // positionement + taille
        setBounds(50, 50, 500, 500);
        setTitle("Ecran d'options");

        // creation du panel
        JPanel monPanel = new JPanel();
        setContentPane(monPanel);
        // definition du layout
        setLayout(null);

        JLabel monLabel = new JLabel();
        monLabel.setText("Vitesse Personnage :");
        // positionner le label
        monLabel.setBounds(20, 20, 200, 20);
        monPanel.add(monLabel);

        JTextField monTexte = new JTextField();
        monTexte.setBounds(20, 50, 100, 20);
        monPanel.add(monTexte);


        JLabel monLabel2 = new JLabel();
        monLabel2.setText("Points vie personnage :");
        // positionner le label
        monLabel2.setBounds(20, 70, 200, 20);
        monPanel.add(monLabel2);

        JTextField monTexte2 = new JTextField();
        monTexte2.setBounds(20, 100, 100, 20);
        monPanel.add(monTexte2);


        JLabel monLabel3 = new JLabel();
        monLabel3.setText("Delai Projectile :");
        // positionner le label
        monLabel3.setBounds(20, 120, 200, 20);
        monPanel.add(monLabel3);

        JTextField monTexte3 = new JTextField();
        monTexte3.setBounds(20, 150, 100, 20);
        monPanel.add(monTexte3);


        JLabel monLabel4 = new JLabel();
        monLabel4.setText("Puissance Projectile :");
        // positionner le label
        monLabel4.setBounds(20, 170, 200, 20);
        monPanel.add(monLabel4);

        JTextField monTexte4 = new JTextField();
        monTexte4.setBounds(20, 200, 100, 20);
        monPanel.add(monTexte4);


        JLabel monLabel5 = new JLabel();
        monLabel5.setText("Vitesse Rotation :");
        // positionner le label
        monLabel5.setBounds(20, 220, 200, 20);
        monPanel.add(monLabel5);

        JTextField monTexte5 = new JTextField();
        monTexte5.setBounds(20, 250, 100, 20);
        monPanel.add(monTexte5);


        JLabel monLabel6 = new JLabel();
        monLabel6.setText("Vitesse Projectile :");
        // positionner le label
        monLabel6.setBounds(20, 270, 200, 20);
        monPanel.add(monLabel6);

        JTextField monTexte6 = new JTextField();
        monTexte6.setBounds(20, 300, 100, 20);
        monPanel.add(monTexte6);

        ArrayList<Personnage> liste = LienBDD.selectAll();

        for(Personnage p : liste){
            liste_personnages.addElement(p);
        }

        JList liste_perso = new JList(liste_personnages);
        liste_perso.setBounds(150, 10, 150, 240);
        monPanel.add(liste_perso);


        JButton valider = new JButton("Modifier");
        valider.setBounds(200, 350, 100, 60);
        monPanel.add(valider);

        JButton retour = new JButton("Retour");
        retour.setBounds(50, 350, 100, 60);
        monPanel.add(retour);

        setVisible(true);


        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Personnage p = (Personnage) liste_perso.getModel().getElementAt(liste_perso.getSelectedIndex());
                int vp = Integer.parseInt(monTexte.getText());
                p.setVitesse_personnage(vp);
                int pv = Integer.parseInt(monTexte2.getText());
                p.setPoints_vie(pv);
                int dt = Integer.parseInt(monTexte3.getText());
                p.setDelai_tir(dt);
                int pp = Integer.parseInt(monTexte4.getText());
                p.setPuissance_tir(pp);
                int vr = Integer.parseInt(monTexte5.getText());
                p.setVitesse_rotaion(vr);
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
                monTexte3.setText("");
                monTexte4.setText("");
                monTexte5.setText("");
                monTexte6.setText("");
            }
        });


    }
}

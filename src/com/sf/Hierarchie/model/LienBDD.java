package com.sf.Hierarchie.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class LienBDD {

    public static Connection getConnection() {
        Connection connection = null;
        // ------------------------------
        String url = "jdbc:mysql://localhost:3306/arcade_bdd" + "?autoReconnect=true&useSSL=false";
        String user = "root";
        String pwd = "";
        try {
            connection = DriverManager.getConnection(url, user, pwd);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // ------------------------------
        return connection;
    }

    public static ArrayList<Personnage> selectAll() {
        ArrayList<Personnage> liste = new ArrayList<Personnage>();
        Connection connection = getConnection();
        // ------------------------------
        String requete = "SELECT * FROM personnage";
        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet result = state.executeQuery(requete);
            while (result.next()) {
                String nom  = result.getString("nom");
                int points_vie  = result.getInt("points_vie");
                int puissance_tir = result.getInt("puissance_tir");
                int id = result.getInt("id");
                // System.out.println(prenom);
                Personnage p = new Personnage(nom, points_vie, puissance_tir, id);
                liste.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // ------------------------------
        return liste;
    }
    public static void update(Personnage p) {
        String requete = "UPDATE personnage SET points_vie = ?,puissance_tir =?, delai_tir =?, vitesse_deplacement =?, vitesse_rotation =?, vitesse_projectile =? WHERE id=?";
        Connection connection = getConnection();
        try {
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(requete);
            // je viens binder le 1er ?
            prepare.setInt(1, p.getPoints_vie());
            prepare.setInt(2, p.getPuissance_tir());
            prepare.setInt(3, p.getDelai_tir());
            prepare.setInt(4, p.getVitesse_personnage());
            prepare.setInt(5, p.getVitesse_rotaion());
            prepare.setInt(6, p.getVitesse_projectil());
            prepare.setInt(7, p.getId());
            prepare.execute();
            prepare.close();
            connection.commit();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
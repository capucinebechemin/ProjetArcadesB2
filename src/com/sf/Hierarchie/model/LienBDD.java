package com.sf.Hierarchie.model;

import java.sql.*;
import java.util.ArrayList;

public class LienBDD {

    public static Connection getConnection() {
        Connection connection = null;
        // Connexion à la base de données
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
    //récupération de tous les personnages du jeu
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

    // Modification des caractéristiques des personnages dans la base de données
    public static void update(Personnage p) {
        String requete = "UPDATE personnage SET points_vie = ?,puissance_tir =?, vitesse_deplacement =?, vitesse_projectile =? WHERE id=?";
        Connection connection = getConnection();
        try {
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(requete);
            // je viens binder le 1er ?
            prepare.setInt(1, p.getPoints_vie());
            prepare.setInt(2, p.getPuissance_tir());
//            prepare.setInt(3, p.getDelai_tir());
            prepare.setInt(3, p.getVitesse_personnage());
//            prepare.setInt(5, p.getVitesse_rotaion());
            prepare.setInt(4, p.getVitesse_projectil());
            prepare.setInt(5, p.getId());
            prepare.execute();
            prepare.close();
            connection.commit();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Récupération de la colonne Vitesse en fonction du personnage
    public static Integer selectVitesse(int i) {

        Connection connection = getConnection();
        // ------------------------------
        String requete = "SELECT vitesse_deplacement FROM personnage WHERE id =" + i;
        int vitesse = 0;
        try {
            Statement state ;
            ResultSet result ;


            state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = state.executeQuery(requete);

            while (result.next()) {
                vitesse = result.getInt("vitesse_deplacement");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vitesse;
    }

    // Récupération de la colonne Vie en fonction du personnage
    public static Integer selectLife(int i) {

        Connection connection = getConnection();
        // ------------------------------
        String requete = "SELECT points_vie FROM personnage WHERE id =" + i;
        int life = 0;
        try {
            Statement state ;
            ResultSet result ;


            state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = state.executeQuery(requete);

            while (result.next()) {
                life = result.getInt("points_vie");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return life;
    }
    // Récupération de la colonne Puissance en fonction du personnage
    public static Integer selectPuissance(int i) {

        Connection connection = getConnection();
        // ------------------------------
        String requete = "SELECT puissance_tir FROM personnage WHERE id =" + i;
        int power = 0;
        try {
            Statement state ;
            ResultSet result ;


            state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = state.executeQuery(requete);

            while (result.next()) {
                power = result.getInt("puissance_tir");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return power;
    }
    // Récupération de la colonne Vitesse de tir en fonction du personnage
    public static Integer selectVitesseTir(int i) {

        Connection connection = getConnection();
        // ------------------------------
        String requete = "SELECT vitesse_projectile FROM personnage WHERE id =" + i;
        int speed_shoot = 0;
        try {
            Statement state ;
            ResultSet result ;


            state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = state.executeQuery(requete);

            while (result.next()) {
                speed_shoot = result.getInt("vitesse_projectile");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return speed_shoot;
    }
    // Récupération des dix scores les plus élevés dans l'ordre décroissant avec le personnage.
    public static ArrayList<String> selectScore () {

        Connection connection = getConnection();
        // ------------------------------
        String requete = "SELECT pseudo, best_score FROM player ORDER BY  best_score DESC LIMIT 10";
        ArrayList<String> tableau_scores = new ArrayList<>();
        String pseudo = "";
        String line = "";
        int best_score = 0;
        try {
            Statement state ;
            ResultSet result ;


            state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = state.executeQuery(requete);

            while (result.next()) {
                pseudo = result.getString("pseudo");
                best_score = result.getInt("best_score");
                line = (pseudo.toUpperCase() + " Score : " + best_score);
                tableau_scores.add(line);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tableau_scores;
    }


    //Ajout d'un nouveau score à la base de données.
    public static void insertScore (String pseudo, int score) {

        Connection connection = getConnection();
        // ------------------------------
        String requete = "INSERT INTO player (pseudo, best_score) VALUES (?, ?)";

        try {
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(requete);
            // je viens binder le 1er ?
            prepare.setString(1, pseudo);
            prepare.setInt(2, score);
            prepare.execute();
            prepare.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
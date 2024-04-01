package tp2.platsetutilisateurs;

import java.sql.*;
import java.util.ArrayList;

public class UtilisateurPlatBD implements UtilisateurPlatBDInterface {
    /**
     * Accès à la base de données (session)
     */
    protected Connection dbConnection ;

    /**
     * Constructeur de la classe
     * @param infoConnection chaîne de caractères avec les informations de connexion
     *                       (p.ex. jdbc:mariadb://mysql-[compte].alwaysdata.net/[compte]_library_db
     * @param user chaîne de caractères contenant l'identifiant de connexion à la base de données
     * @param pwd chaîne de caractères contenant le mot de passe à utiliser
     */
    public UtilisateurPlatBD(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }

    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Utilisateur getUtilisateur(String mail) {
        Utilisateur selectionUtilisateur = null;

        String query = "SELECT * FROM Utilisateur WHERE mail=?";

        // construction et exécution d'une requête préparée
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, mail);

            // exécution de la requête
            ResultSet result = ps.executeQuery();

            // récupération du premier (et seul) tuple résultat
            // (si le nom renseigné est valide)
            if (result.next()) {
                String nom = result.getString("nom");
                String mdp = result.getString("mdp");
                String mailUtilisateur = result.getString("mail");

                // création et initialisation de l'objet Reservation
                selectionUtilisateur = new Utilisateur(nom, mdp, mailUtilisateur);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectionUtilisateur;
    }

    @Override
    public ArrayList<Utilisateur> getAllUtilisateurs() {
        ArrayList<Utilisateur> listUtilisateurs;

        String query = "SELECT * FROM Utilisateur";

        // construction et exécution d'une requête préparée
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listUtilisateurs = new ArrayList<>();

            // récupération des tuples résultat
            while (result.next()) {
                String nom = result.getString("nom");
                String mdp = result.getString("mdp");
                String mail = result.getString("mail");

                // création de la réservation courante
                Utilisateur utilisateurActuel = new Utilisateur(nom, mdp, mail);

                listUtilisateurs.add(utilisateurActuel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUtilisateurs;
    }

    @Override
    public Plat getPlat(String nomPlat) {
        Plat selectionPlat = null;

        String query = "SELECT * FROM Plat WHERE nomPlat=?";

        // construction et exécution d'une requête préparée
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, nomPlat);

            // exécution de la requête
            ResultSet result = ps.executeQuery();

            // récupération du premier (et seul) tuple résultat
            // (si le nom renseigné est valide)
            if (result.next()) {
                String nom = result.getString("nomPlat");
                float prix = result.getFloat("prix");

                // création et initialisation de l'objet Reservation
                selectionPlat = new Plat(nom, prix);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectionPlat;
    }

    @Override
    public ArrayList<Plat> getAllPlats() {
        ArrayList<Plat> listPlats;

        String query = "SELECT * FROM Plat";

        // construction et exécution d'une requête préparée
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listPlats = new ArrayList<>();

            // récupération des tuples résultat
            while (result.next()) {
                String nom = result.getString("nomPlat");
                float prix = result.getFloat("prix");

                // création et initialisation de l'objet Reservation
                Plat PlatActuel = new Plat(nom, prix);

                listPlats.add(PlatActuel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listPlats;
    }
}

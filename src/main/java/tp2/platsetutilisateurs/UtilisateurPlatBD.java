package tp2.platsetutilisateurs;

import java.sql.*;
import java.util.ArrayList;

/**
 * Cette classe gère l'accès à la base de données pour les utilisateurs et les plats.
 */
public class UtilisateurPlatBD implements UtilisateurPlatBDInterface {
    /**
     * Accès à la base de données (session)
     */
    protected Connection dbConnection ;

    /**
     * Constructeur de la classe.
     *
     * @param infoConnection chaîne de caractères avec les informations de connexion
     *                       (p.ex. jdbc:mariadb://mysql-[compte].alwaysdata.net/[compte]_library_db
     * @param user chaîne de caractères contenant l'identifiant de connexion à la base de données
     * @param pwd chaîne de caractères contenant le mot de passe à utiliser
     * @throws SQLException en cas d'erreur lors de la connexion à la base de données
     * @throws ClassNotFoundException si la classe du pilote JDBC n'est pas trouvée
     */
    public UtilisateurPlatBD(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }

    /**
     * Ferme la connexion à la base de données.
     */
    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Récupère un utilisateur à partir de son adresse e-mail.
     *
     * @param mail l'adresse e-mail de l'utilisateur à récupérer
     * @return l'objet Utilisateur correspondant à l'adresse e-mail spécifiée, ou null si aucun utilisateur correspondant n'est trouvé
     */
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

                // création et initialisation de l'objet Utilisateur
                selectionUtilisateur = new Utilisateur(nom, mdp, mailUtilisateur);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectionUtilisateur;
    }

    /**
     * Récupère tous les utilisateurs de la base de données.
     *
     * @return une liste d'objets Utilisateur représentant tous les utilisateurs dans la base de données
     */
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

                // création de l'utilisateur courant
                Utilisateur utilisateurActuel = new Utilisateur(nom, mdp, mail);

                listUtilisateurs.add(utilisateurActuel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUtilisateurs;
    }

    /**
     * Récupère un plat à partir de son nom.
     *
     * @param nomPlat le nom du plat à récupérer
     * @return l'objet Plat correspondant au nom spécifié, ou null si aucun plat correspondant n'est trouvé
     */
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
                String description = result.getString("description");

                // création et initialisation de l'objet Plat
                selectionPlat = new Plat(nom, prix, description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectionPlat;
    }

    /**
     * Récupère tous les plats de la base de données.
     *
     * @return une liste d'objets Plat représentant tous les plats dans la base de données
     */
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
                String description = result.getString("description");

                // création de l'objet Plat courant
                Plat PlatActuel = new Plat(nom, prix,description);

                listPlats.add(PlatActuel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listPlats;
    }

    /**
     * Ajoute un nouvel utilisateur à la base de données.
     *
     * @param nom le nom de l'utilisateur
     * @param email l'adresse e-mail de l'utilisateur
     * @param mdp le mot de passe de l'utilisateur
     * @return true si l'ajout est réussi, false sinon
     */
    @Override
    public boolean setNouveauUtilisateur(String nom, String email, String mdp) {
        boolean result = false;

        String query = "INSERT INTO Utilisateur (nom,mail,mdp) VALUES (?,?,?)";

        // construction et exécution d'une requête pré
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, nom);
            ps.setString(2, email);
            ps.setString(3, mdp);
            ps.executeUpdate();

            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}

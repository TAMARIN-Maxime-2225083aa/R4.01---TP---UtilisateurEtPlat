package tp2.platsetutilisateurs;

import java.sql.*;
import java.util.ArrayList;

public class PlatBD implements PlatBDInterface {
    /**
     * Accès à la base de données (session)
     */
    protected Connection dbConnection;

    /**
     * Constructeur de la classe.
     *
     * @param infoConnection chaîne de caractères avec les informations de connexion
     *                       (p.ex. jdbc:mariadb://mysql-[compte].alwaysdata.net/[compte]_library_db
     * @param user           chaîne de caractères contenant l'identifiant de connexion à la base de données
     * @param pwd            chaîne de caractères contenant le mot de passe à utiliser
     * @throws SQLException           en cas d'erreur lors de la connexion à la base de données
     * @throws ClassNotFoundException si la classe du pilote JDBC n'est pas trouvée
     */
    public PlatBD(String infoConnection, String user, String pwd) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
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
                Plat PlatActuel = new Plat(nom, prix, description);

                listPlats.add(PlatActuel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listPlats;
    }

    /**
     * Méthode permettant d'ajouter un plat
     *
     * @param nom         nom du plat
     * @param prix        prix du plat
     * @param description description du plat
     * @return un booleen montrant si l'ajout à eu lieu ou non
     */
    public boolean nouveauPlat(String nom, String prix, String description) {
        boolean result = false;

        String query = "INSERT INTO Plat (nomPlat,prix,description) VALUES (?,?,?)";

        // construction et exécution d'une requête pré
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, nom);
            ps.setDouble(2, Double.parseDouble(prix));
            ps.setString(3, description);
            ps.executeUpdate();

            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * Méthode permettant de modifier les information d'un plat (prix ou description)
     *
     * @param nom         nom du plat
     * @param prix        prix du plat
     * @param description description du plat
     * @return un booleen montrant si la modification à eu lieu ou non
     */
    public boolean modificationPlat(String nom, String prix, String description) {
        boolean result = false;

        String query = "UPDATE Plat SET prix = ?,description = ? WHERE nomPlat = ?";

        // construction et exécution d'une requête pré
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setDouble(1, Double.parseDouble(prix));
            ps.setString(2, description);
            ps.setString(3, nom);
            ps.executeUpdate();

            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * Méthode permettant de supprimer un plat à partir de son nom
     *
     * @param nom nom du plat à supprimer
     * @return un booleen en fonction de l'issue de la suppression
     */
    public boolean suppressionPlat(String nom) {
        boolean result = false;

        String query = "DELETE FROM Plat WHERE nomPlat = ?";

        // construction et exécution d'une requête pré
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, nom);
            ps.executeUpdate();

            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}

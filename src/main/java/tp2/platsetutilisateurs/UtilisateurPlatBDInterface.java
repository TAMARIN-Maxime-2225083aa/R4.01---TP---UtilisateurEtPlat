package tp2.platsetutilisateurs;

import java.util.ArrayList;

/**
 * Interface d'accès aux données des utilisateurs
 */
public interface UtilisateurPlatBDInterface {
    /**
     * Méthode fermant le dépôt où sont stockées les informations sur les utilisateurs
     */
    public void close();

    /**
     * Méthode retournant l'utilisateur dont le nom est passée en paramètre
     *
     * @param mail mail de l'utilisateur recherché
     * @return un objet Utilisateur représentant l'utilisateur recherché
     */
    public Utilisateur getUtilisateur(String mail);

    /**
     * Méthode retournant la liste des utilisateurs
     *
     * @return une liste d'objets Utilisateur
     */
    public ArrayList<Utilisateur> getAllUtilisateurs();

    /**
     * Méthode permettant d'ajouter un utilisateur
     *
     * @param nom   nom de l'utilisateur
     * @param email mail de l'utilisateur
     * @param mdp   mot de passe de l'utilisateur
     * @return un booleen montrant si l'ajout à eu lieu ou non
     */
    public boolean nouveauUtilisateur(String nom, String email, String mdp);

    /**
     * Méthode permettant de modifier les information d'un utilisateur (mot de passe ou nom)
     *
     * @param nom   nom de l'utilisateur (potentiellement nouveau)
     * @param email mail de l'utilisateur
     * @param mdp   mot de passe de l'utilisateur (potentiellement nouveau)
     * @return un booleen montrant si la modification à eu lieu ou non
     */
    public boolean modificationUtilisateur(String nom, String email, String mdp);

    /**
     * Méthode permettant de supprimer un utilisateur à partir de son mail
     *
     * @param mail mail de l'utilisateur à supprimer
     * @return un booleen en fonction de l'issue de la suppression
     */
    public boolean suppressionUtilisateur(String mail);

    /**
     * Méthode retournant le plat dont le nom est passé en paramètre
     *
     * @param nomPlat Nom du plat recherché
     * @return Un objet Plat représentant le plat recherché
     */
    public Plat getPlat(String nomPlat);

    /**
     * Méthode retournant la liste de tous les plats
     *
     * @return Une liste d'objets Plat
     */
    public ArrayList<Plat> getAllPlats();

    /**
     * Méthode permettant d'ajouter un plat
     *
     * @param nom   nom du plat
     * @param prix prix du plat
     * @param description description du plat
     * @return un booleen montrant si l'ajout à eu lieu ou non
     */
    public boolean nouveauPlat(String nom, String prix, String description);

    /**
     * Méthode permettant de modifier les information d'un plat (prix ou description)
     *
     * @param nom   nom du plat
     * @param prix prix du plat
     * @param description description du plat
     * @return un booleen montrant si la modification à eu lieu ou non
     */
    public boolean modificationPlat(String nom, String prix, String description);

    /**
     * Méthode permettant de supprimer un plat à partir de son nom
     *
     * @param nom   nom du plat à supprimer
     * @return un booleen en fonction de l'issue de la suppression
     */
    public boolean suppressionPlat(String nom);
}

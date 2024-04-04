package tp2.platsetutilisateurs;

import java.util.ArrayList;

/**
 * Interface d'accès aux données des plats
 */
public interface PlatBDInterface {
    /**
     * Méthode fermant le dépôt où sont stockées les informations sur les utilisateurs
     */
    public void close();

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
     * @param nom         nom du plat
     * @param prix        prix du plat
     * @param description description du plat
     * @return un booleen montrant si l'ajout à eu lieu ou non
     */
    public boolean nouveauPlat(String nom, String prix, String description);

    /**
     * Méthode permettant de modifier les information d'un plat (prix ou description)
     *
     * @param nom         nom du plat
     * @param prix        prix du plat
     * @param description description du plat
     * @return un booleen montrant si la modification à eu lieu ou non
     */
    public boolean modificationPlat(String nom, String prix, String description);

    /**
     * Méthode permettant de supprimer un plat à partir de son nom
     *
     * @param nom nom du plat à supprimer
     * @return un booleen en fonction de l'issue de la suppression
     */
    public boolean suppressionPlat(String nom);
}

package tp2.platsetutilisateurs;

import java.util.ArrayList;

/**
 * Interface d'accès aux données des utilisateurs
 */
public interface UtilisateurPlatBDInterface {
    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les utilisateurs
     */
    public void close();
    /**
     * Méthode retournant l'utilisateur dont le nom est passée en paramètre
     * @param nom nom de l'utilisateur recherché
     * @return un objet Utilisateur représentant l'utilisateur recherché
     */
    public Utilisateur getUtilisateur( String nom );

    /**
     * Méthode retournant la liste des utilisateurs
     * @return une liste d'objets Utilisateur
     */
    public ArrayList<Utilisateur> getAllUtilisateurs() ;

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
}

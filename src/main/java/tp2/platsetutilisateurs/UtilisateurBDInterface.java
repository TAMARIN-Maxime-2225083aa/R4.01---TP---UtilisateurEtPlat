package tp2.platsetutilisateurs;

import java.util.ArrayList;

/**
 * Interface d'accès aux données des utilisateurs
 */
public interface UtilisateurBDInterface {
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

}

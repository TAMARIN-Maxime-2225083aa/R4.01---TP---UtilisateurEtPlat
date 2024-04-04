package tp2.platsetutilisateurs;

import java.util.ArrayList;

public interface UtilisateurBDInterface{
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
}

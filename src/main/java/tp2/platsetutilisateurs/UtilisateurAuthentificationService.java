package tp2.platsetutilisateurs;

/**
 * Classe représentant le cas d'utilisation "authentifier un utilisateur"
 */
public class UtilisateurAuthentificationService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les utilisateurs
     */
    protected UtilisateurPlatBDInterface utilisateurPlatBDInterface;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     *
     * @param utilisateurPlatBDInterface objet implémentant l'interface d'accès aux données
     */
    public UtilisateurAuthentificationService(UtilisateurPlatBDInterface utilisateurPlatBDInterface) {
        this.utilisateurPlatBDInterface = utilisateurPlatBDInterface;
    }

    /**
     * Méthode d'authentifier un utilisateur
     *
     * @param mail mail de l'utilisateur
     * @param mdp  mot de passe de l'utilisateur
     * @return true si l'utilisateur a été authentifié, false sinon
     */
    public boolean isValidUser(String mail, String mdp) {

        Utilisateur currentUser = utilisateurPlatBDInterface.getUtilisateur(mail);

        // si l'utilisateur n'a pas été trouvé
        if (currentUser == null)
            return false;

        // si le mot de passe n'est pas correcte
        if (!currentUser.mdp.equals(mdp))
            return false;

        return true;
    }

    /**
     * Méthode permettant l'enregistrement d'un nouveau utilisateur
     *
     * @param nom   nom de l'utilisateur
     * @param email mail de l'utilisateur
     * @param mdp   mot de passe de l'utilisateur
     * @return true si enregistrement fait. Faux sinon.
     */
    public boolean enregisteUtilisateur(String nom, String email, String mdp) {
        boolean result = false;

        // si un utilisateur a déjà cette adresse email on n'enregistre pas le nouveau utilisateur
        Utilisateur utilisateur = utilisateurPlatBDInterface.getUtilisateur(email);

        //si l'utilisateur est trouvé
        if (utilisateur != null)
            return result;

        // si l'utilisateur est disponible
        result = utilisateurPlatBDInterface.setNouveauUtilisateur(nom, email, mdp);

        return result;
    }
}

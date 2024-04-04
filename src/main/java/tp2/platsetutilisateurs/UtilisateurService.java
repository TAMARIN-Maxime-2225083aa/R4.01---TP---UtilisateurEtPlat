package tp2.platsetutilisateurs;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;

/**
 * Service utilisé pour récupérer les informations nécessaires à la ressource des utilisateurs et des plats
 * (permet de dissocier ressource et mode d'accès aux données)
 */
public class UtilisateurService {
    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les utilisateurs
     */
    protected UtilisateurBDInterface utilisateurBD;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     *
     * @param utilisateurBD objet implémentant l'interface d'accès aux données des utilisateurs
     */
    public UtilisateurService(UtilisateurBDInterface utilisateurBD) {
        this.utilisateurBD = utilisateurBD;
    }

    /**
     * Méthode retournant les informations (sans mail et mot de passe) sur les utilisateurs au format JSON
     *
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllUtilisateursJSON() {

        ArrayList<Utilisateur> allUtilisateurs = utilisateurBD.getAllUtilisateurs();

        // on supprime les informations sensibles
        for (Utilisateur utilisateur : allUtilisateurs) {
            utilisateur.setMail("");
            utilisateur.setMdp("");
        }

        // création du JSON et conversion de la liste des utilisateurs
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allUtilisateurs);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    /**
     * Méthode retournant au format JSON les informations sur un utilisateur recherché
     *
     * @param mail mail de l'utilisateur recherché
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getUtilisateurJSON(String mail) {
        String result = null;
        Utilisateur utilisateur = utilisateurBD.getUtilisateur(mail);

        // si l'utilisateur a été trouvé
        if (utilisateur != null) {

            // création du JSON et conversion de l'utilisateur
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(utilisateur);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Méthode permettant la modification d'un utilisateur
     *
     * @param nom   nom de l'utilisateur
     * @param email mail de l'utilisateur
     * @param mdp   mot de passe de l'utilisateur
     * @return true si modification fait. Faux sinon.
     */
    public boolean enregisteUtilisateur(String nom, String email, String mdp) {
        boolean result = false;

        // si un utilisateur a déjà cette adresse email on n'enregistre pas le nouveau utilisateur
        Utilisateur utilisateur = utilisateurBD.getUtilisateur(email);

        //si l'utilisateur est trouvé
        if (utilisateur != null)
            return result;

        // si l'utilisateur est disponible
        result = utilisateurBD.nouveauUtilisateur(nom, email, mdp);

        return result;
    }

    /**
     * Méthode permettant de modifier les informations d'un utilisateur
     *
     * @param nom   nom de l'utilisateur
     * @param email mail de l'utilisateur
     * @param mdp   mot de passe de l'utilisateur
     * @return un booleen en fonction de la réussite de la modification
     */
    public boolean modificationUtilisateur(String nom, String email, String mdp) {
        boolean result = false;

        Utilisateur utilisateur = utilisateurBD.getUtilisateur(email);

        // si pas d'utilisateur avec cette adresse mail on sort
        if (utilisateur == null)
            return result;

        // on vérifie que les infos à mettre à jour ne sont pas identique aux infos actuel
        if (!nom.equals(utilisateur.nom) || !mdp.equals(utilisateur.mdp))
            result = utilisateurBD.modificationUtilisateur(nom, email, mdp);

        return result;
    }

    /**
     * Méthode permettant de supprimer un utilisateur
     *
     * @param email mail de l'utilisateur
     * @return un booleen en fonction de la réussite de la modification
     */
    public boolean suppressionUtilisateur(String email) {
        boolean result = false;

        Utilisateur utilisateur = utilisateurBD.getUtilisateur(email);

        // si pas d'utilisateur avec cette adresse mail on retourne faux
        if (utilisateur == null)
            return result;

        result = utilisateurBD.suppressionUtilisateur(email);

        return result;
    }
}

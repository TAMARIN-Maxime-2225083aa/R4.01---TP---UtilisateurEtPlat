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
    protected UtilisateurPlatBDInterface utilisateurBD;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     *
     * @param utilisateurBD objet implémentant l'interface d'accès aux données des utilisateurs
     */
    public UtilisateurService(UtilisateurPlatBDInterface utilisateurBD) {
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
}

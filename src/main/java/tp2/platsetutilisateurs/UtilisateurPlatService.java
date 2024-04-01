package tp2.platsetutilisateurs;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource
 * (permet de dissocier ressource et mode d'accès aux données)
 */
public class UtilisateurPlatService {
    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les utilisateurs
     */
    protected UtilisateurPlatBDInterface utilisateurPlatBD;

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les plats
     */

    /**
     * Constructeur permettant d'injecter l'accès aux données
     *
     * @param utilisateurPlatBD objet implémentant l'interface d'accès aux données des utilisateur
     */
    public UtilisateurPlatService(UtilisateurPlatBDInterface utilisateurPlatBD) {
        this.utilisateurPlatBD = utilisateurPlatBD;
    }

    /**
     * Méthode retournant les informations (sans mail et mot de passe) sur les utilisateurs au format JSON
     *
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllUtilisateursJSON() {

        ArrayList<Utilisateur> allUtilisateurs = utilisateurPlatBD.getAllUtilisateurs();

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
        Utilisateur utilisateur = utilisateurPlatBD.getUtilisateur(mail);

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
     * Méthode retournant les informations sur tous les plats au format JSON
     *
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllPlatsJSON() {
        ArrayList<Plat> allPlats = utilisateurPlatBD.getAllPlats();

        // création du JSON et conversion de la liste des plats
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allPlats);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    /**
     * Méthode retournant au format JSON les informations sur un plat recherché
     *
     * @param nom nom du plat recherché
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getPlatJSON(String nom) {
        String result = null;
        Plat plat = utilisateurPlatBD.getPlat(nom);

        // si le plat a été trouvé
        if (plat != null) {

            // création du JSON et conversion du plat
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(plat);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }
}

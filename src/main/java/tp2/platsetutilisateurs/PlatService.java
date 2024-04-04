package tp2.platsetutilisateurs;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;

/**
 * Service utilisé pour récupérer les informations nécessaires à la ressource des plats
 * (permet de dissocier ressource et mode d'accès aux données)
 */
public class PlatService {
    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les plats
     */
    protected UtilisateurPlatBDInterface platBD;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     *
     * @param platBD objet implémentant l'interface d'accès aux données des plats
     */
    public PlatService(UtilisateurPlatBDInterface platBD) {
        this.platBD = platBD;
    }

    /**
     * Méthode retournant les informations sur tous les plats au format JSON
     *
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllPlatsJSON() {
        ArrayList<Plat> allPlats = platBD.getAllPlats();

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
        Plat plat = platBD.getPlat(nom);

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

    /**
     * Méthode permettant l'ajout d'un plat
     *
     * @param nom         nom du plat
     * @param prix        prix du plat
     * @param description description du plat
     * @return true si l'enregistrement fait. Faux sinon.
     */
    public boolean enregistePlat(String nom, String prix, String description) {
        boolean result = false;

        // si un plat a déjà ce nom on n'enregistre pas le nouveau plat
        Plat plat = platBD.getPlat(nom);

        //si le plat est trouvé
        if (plat != null)
            return result;

        // si le nom libre
        result = platBD.nouveauPlat(nom, prix, description);

        return result;
    }

    /**
     * Méthode permettant de modifier les informations d'un plat
     *
     * @param nom         nom du plat
     * @param prix        prix du plat
     * @param description description du plat
     * @return un booleen en fonction de la réussite de la modification
     */
    public boolean modificationPlat(String nom, String prix, String description) {
        boolean result = false;

        Plat plat = platBD.getPlat(nom);

        // si pas de plat avec ce nom on sort
        if (plat == null)
            return result;

        // on vérifie que les infos à mettre à jour ne sont pas identique aux infos actuel
        if (!(Double.parseDouble(prix) - plat.prix < 0.00001)  || !description.equals(plat.description))
            result = platBD.modificationPlat(nom, prix, description);

        return result;
    }

    /**
     * Méthode permettant de supprimer un plat
     *
     * @param nom nom du plat
     * @return un booleen en fonction de la réussite de la modification
     */
    public boolean suppressionPlat(String nom) {
        boolean result = false;

        Plat plat = platBD.getPlat(nom);

        // si pas de plat avec ce nom on retourne faux
        if (plat == null)
            return result;

        result = platBD.suppressionPlat(nom);

        return result;
    }
}

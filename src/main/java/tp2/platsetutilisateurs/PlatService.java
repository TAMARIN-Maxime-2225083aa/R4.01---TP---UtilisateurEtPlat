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
}

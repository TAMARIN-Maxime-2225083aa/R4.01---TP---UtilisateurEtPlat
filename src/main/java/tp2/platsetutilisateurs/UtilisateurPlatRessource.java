package tp2.platsetutilisateurs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

/**
 * Ressource associée aux utilisateurs et aux plats
 * (point d'accès de l'API REST)
 */
@Path("/")
@ApplicationScoped
public class UtilisateurPlatRessource {

    /**
     * Service utilisé pour accéder aux données des utilisateurs et des plats
     */
    private UtilisateurPlatService service;

    /**
     * Constructeur par défaut
     */
    public UtilisateurPlatRessource() {
    }

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     *
     * @param utilisateurPlatBD objet implémentant l'interface d'accès aux données des utilisateurs et des plats
     */
    public @Inject UtilisateurPlatRessource(UtilisateurPlatBDInterface utilisateurPlatBD) {
        this.service = new UtilisateurPlatService(utilisateurPlatBD);
    }

    /**
     * Enpoint permettant de publier tous les utilisateurs enregistrés
     *
     * @return la liste des utilisateurs (avec leurs informations) au format JSON
     */
    @GET
    @Path("/utilisateurs")
    @Produces("application/json")
    public String getAllUtilisateurs() {
        return service.getAllUtilisateursJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'un utilisateur dont le nom est passé paramètre dans le chemin
     *
     * @param nom nom de l'utilisateur recherché
     * @return les informations de l'utilisateur recherché au format JSON
     */
    @GET
    @Path("/utilisateurs/{nom}")
    @Produces("application/json")
    public String getUtilisateur(@PathParam("nom") String nom) {
        String result = service.getUtilisateurJSON(nom);
        if (result == null)
            throw new NotFoundException();
        return result;
    }

    /**
     * Enpoint permettant de publier de tous les plats enregistrés
     *
     * @return la liste des plats (avec leurs informations) au format JSON
     */
    @GET
    @Path("/plats")
    @Produces("application/json")
    public String getAllPlats() {
        return service.getAllPlatsJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'un plat dont le nom est passé paramètre dans le chemin
     *
     * @param nom nom du plat recherché
     * @return les informations du plat recherché au format JSON
     */
    @GET
    @Path("/plats/{nom}")
    @Produces("application/json")
    public String getPlat(@PathParam("nom") String nom) {
        String result = service.getPlatJSON(nom);
        if (result == null)
            throw new NotFoundException();
        return result;
    }
}

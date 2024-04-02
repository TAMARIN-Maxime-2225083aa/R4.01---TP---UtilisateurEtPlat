package tp2.platsetutilisateurs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

/**
 * Ressource associée aux plats
 * (point d'accès de l'API REST pour les plats)
 */
@Path("/plats")
@ApplicationScoped
public class PlatRessource {

    /**
     * Service utilisé pour accéder aux données des plats
     */
    private PlatService service;

    /**
     * Constructeur par défaut
     */
    public PlatRessource() {
    }

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     *
     * @param platBD objet implémentant l'interface d'accès aux données des plats
     */
    public @Inject PlatRessource(UtilisateurPlatBDInterface platBD) {
        this.service = new PlatService(platBD);
    }

    /**
     * Enpoint permettant de publier de tous les plats enregistrés
     *
     * @return la liste des plats (avec leurs informations) au format JSON
     */
    @GET
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
    @Path("/{nom}")
    @Produces("application/json")
    public String getPlat(@PathParam("nom") String nom) {
        String result = service.getPlatJSON(nom);
        if (result == null)
            throw new NotFoundException();
        return result;
    }
}

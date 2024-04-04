package tp2.platsetutilisateurs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

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

    /**
     * Endpoint permettant d'enregister les informations d'un plat
     *
     * @param nom         nom du plat
     * @param prix        prix du plat
     * @param description description du plat
     * @return un booleen selon l'issue de l'enregistement
     */
    @POST
    @Path("/nouveau")
    @Consumes("application/x-www-form-urlencoded")
    public Response enregistrePlat(@FormParam("nom") String nom, @FormParam("prix") String prix, @FormParam("description") String description) {

        boolean res = service.enregistePlat(nom, prix, description);

        // renvoie true ou false selon si l'enregistrement se passe bien ou non
        return Response.ok(String.valueOf(res)).build();
    }

    /**
     * Endpoint permettant de mettre à jour les informations d'un plat
     *
     * @param nom         nom du plat
     * @param prix        prix du plat
     * @param description description du plat
     * @return un booleen selon l'issue de l'enregistement
     */
    @POST
    @Path("/modification")
    @Consumes("application/x-www-form-urlencoded")
    public Response miseAJourPlat(@FormParam("nom") String nom, @FormParam("prix") String prix, @FormParam("description") String description) {

        boolean res = service.modificationPlat(nom, prix, description);

        // renvoie true ou false selon si la mise à jour se passe bien ou non
        return Response.ok(String.valueOf(res)).build();
    }

    /**
     * Endpoint permettant de supprimer un plat
     *
     * @param nom nom du plat
     * @return un booleen selon l'issue de la suppression
     */
    @DELETE
    @Path("{nom}")
    public Response supprimePlat(@PathParam("nom") String nom) {

        boolean res = service.suppressionPlat(nom);

        // renvoie true ou false selon si la suppression se passe bien ou non
        return Response.ok(String.valueOf(res)).build();
    }
}

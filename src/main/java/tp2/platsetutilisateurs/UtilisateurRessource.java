package tp2.platsetutilisateurs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource associée aux utilisateurs
 * (point d'accès de l'API REST pour les utilisateurs)
 */
@Path("/utilisateurs")
@ApplicationScoped
public class UtilisateurRessource {

    /**
     * Service utilisé pour accéder aux données des utilisateurs
     */
    private UtilisateurService service;

    /**
     * Constructeur par défaut
     */
    public UtilisateurRessource() {
    }

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     *
     * @param utilisateurBD objet implémentant l'interface d'accès aux données des utilisateurs
     */
    public @Inject UtilisateurRessource(UtilisateurPlatBDInterface utilisateurBD) {
        this.service = new UtilisateurService(utilisateurBD);
    }

    /**
     * Enpoint permettant de publier tous les utilisateurs enregistrés
     *
     * @return la liste des utilisateurs (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllUtilisateurs() {
        return service.getAllUtilisateursJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'un utilisateur dont le mail est passé paramètre dans le chemin
     *
     * @param mail mail de l'utilisateur recherché
     * @return les informations de l'utilisateur recherché au format JSON
     */
    @GET
    @Path("/{mail}")
    @Produces("application/json")
    public String getUtilisateur(@PathParam("mail") String mail) {
        String result = service.getUtilisateurJSON(mail);
        if (result == null)
            throw new NotFoundException();
        return result;
    }

    /**
     * Endpoint permettant d'enregister les informations d'un utilisateur
     *
     * @param nom  nom du nouveau utilisateur
     * @param mail mail du nouveau utilisateur
     * @param mdp  mot de passe du nouveau utilisateur
     * @return un bolean selon l'issue de l'enregistement
     */
    @POST
    @Path("/nouveau")
    @Consumes("application/x-www-form-urlencoded")
    public Response enregistreUtilisateur(@FormParam("nom") String nom, @FormParam("mail") String mail, @FormParam("mdp") String mdp) {

        boolean res = service.enregisteUtilisateur(nom, mail, mdp);

        // renvoie true ou false selon si l'enregistrement se passe bien ou non
        return Response.ok(String.valueOf(res)).build();
    }

    /**
     * Endpoint permettant de mettre à jour les informations d'un utilisateur
     *
     * @param nom  nom du nouveau utilisateur
     * @param mail mail du nouveau utilisateur
     * @param mdp  mot de passe du nouveau utilisateur
     * @return un booleen selon l'issue de l'enregistement
     */
    @POST
    @Path("/modification")
    @Consumes("application/x-www-form-urlencoded")
    public Response miseAJourUtilisateur(@FormParam("nom") String nom, @FormParam("mail") String mail, @FormParam("mdp") String mdp) {

        boolean res = service.modificationUtilisateur(nom, mail, mdp);

        // renvoie true ou false selon si la mise à jour se passe bien ou non
        return Response.ok(String.valueOf(res)).build();
    }

    /**
     * Endpoint permettant de supprimer un utilisateur
     *
     * @param mail mail de l'utilisateur
     * @return un booleen selon l'issue de la suppression
     */
    @DELETE
    @Path("{mail}")
    public Response supprimeUtilisateur(@PathParam("mail") String mail) {

        boolean res = service.suppressionUtilisateur(mail);

        // renvoie true ou false selon si la suppression se passe bien ou non
        return Response.ok(String.valueOf(res)).build();
    }
}

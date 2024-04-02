package tp2.platsetutilisateurs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

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
}

package tp2.platsetutilisateurs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.io.UnsupportedEncodingException;

/**
 * Ressource associée à l'authentification des utilisateurs
 * (point d'accès de l'API REST)
 */
@Path("/authentification")
@ApplicationScoped
public class UtilisateurAuthentificationRessource {
    /**
     * Service utilisé pour accéder aux données des utilisateurs
     */
    private UtilisateurAuthentificationService auth;

    /**
     * Constructeur par défaut
     */
    public UtilisateurAuthentificationRessource(){}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     * @param utilisateurBDInterface objet implémentant l'interface d'accès aux données
     */
    public @Inject UtilisateurAuthentificationRessource(UtilisateurBDInterface utilisateurBDInterface ){
        this.auth = new UtilisateurAuthentificationService( utilisateurBDInterface ) ;
    }

    /**
     * Enpoint permettant de publier de tous les utilisateurs enregistrés
     * @return la liste des utilisateurs (avec leurs informations) au format JSON
     */
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response authenticate(@FormParam("mail") String mail, @FormParam("mdp") String mdp ) throws UnsupportedEncodingException {
        boolean res = false;

        // authentification
        res = auth.isValidUser(mail, mdp);

        // envoie d'une réponse avec la valeur de l'authentification
        return Response.ok(String.valueOf(res)).build();
    }

}

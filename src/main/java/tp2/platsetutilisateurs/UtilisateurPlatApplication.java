package tp2.platsetutilisateurs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class UtilisateurPlatApplication extends Application {
    /**
     * Méthode appelée par l'API CDI pour injecter la connexion à la base de données au moment de la création
     * de la ressource
     *
     * @return un objet implémentant l'interface UtilisateurBDInterface utilisée
     * pour accéder aux données des utilisateurs
     */
    @Produces
    private UtilisateurBDInterface ouvertureConnexionBDUtilisateur() {
        UtilisateurBDInterface db = null;

        try {
            db = new UtilisateurBD("jdbc:mariadb://mysql-tamarin.alwaysdata.net/tamarin_plat_db", "tamarin_plat", "TamarinT22003230.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     * Méthode permettant de fermer la connexion à la base de données lorsque l'application est arrêtée
     *
     * @param utilisateurBDInterface la connexion à la base de données instanciée dans la méthode @openDbConnection
     */
    private void fermetureConnexionUtilisateur(@Disposes UtilisateurBDInterface utilisateurBDInterface) {
        utilisateurBDInterface.close();
    }

    /**
     * Méthode appelée par l'API CDI pour injecter la connexion à la base de données au moment de la création
     * de la ressource
     *
     * @return un objet implémentant l'interface PlatBDInterface utilisée
     * pour accéder aux données des plats
     */
    @Produces
    private PlatBDInterface ouvertureConnexionBDPlat() {
        PlatBDInterface db = null;

        try {
            db = new PlatBD("jdbc:mariadb://mysql-tamarin.alwaysdata.net/tamarin_plat_db", "tamarin_plat", "TamarinT22003230.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     * Méthode permettant de fermer la connexion à la base de données lorsque l'application est arrêtée
     *
     * @param utilisateurBDInterface la connexion à la base de données instanciée dans la méthode @openDbConnection
     */
    private void fermetureConnexionPlat(@Disposes PlatBDInterface utilisateurBDInterface) {
        utilisateurBDInterface.close();
    }
}

package tp2.platsetutilisateurs;

import java.util.ArrayList;

/**
 * Interface d'accès aux données des plats
 */
public interface PlatBDInterface {
    /**
     * Méthode fermant l'accès à la base de données des plats
     */
    public void close();

    /**
     * Méthode retournant le plat dont le nom est passé en paramètre
     *
     * @param nomPlat Nom du plat recherché
     * @return Un objet Plat représentant le plat recherché
     */
    public Plat getPlat(String nomPlat);

    /**
     * Méthode retournant la liste de tous les plats
     *
     * @return Une liste d'objets Plat
     */
    public ArrayList<Plat> getAllPlats();
}

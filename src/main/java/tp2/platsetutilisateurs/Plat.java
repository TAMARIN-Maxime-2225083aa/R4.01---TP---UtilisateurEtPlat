package tp2.platsetutilisateurs;

public class Plat {

    /**
     * Nom du plat
     */
    private String nomPlat;

    /**
     * Prix du plat
     */
    private float prix;

    /**
     * Constructeur par défaut
     */
    public Plat() {
    }

    /**
     * Constructeur de plat
     *
     * @param nomPlat Nom du plat
     * @param prix    Prix du plat
     */
    public Plat(String nomPlat, float prix) {
        this.nomPlat = nomPlat;
        this.prix = prix;
    }

    /**
     * Méthode permettant d'accéder au nom du plat
     *
     * @return Un chaîne de caractères avec le nom du plat
     */
    public String getNomPlat() {
        return nomPlat;
    }

    /**
     * Méthode permettant d'accéder au prix du plat
     *
     * @return Un float représentant le prix du plat
     */
    public float getPrix() {
        return prix;
    }

    /**
     * Méthode permettant de modifier le nom du plat
     *
     * @param nomPlat Nouveau nom du plat
     */
    public void setNomPlat(String nomPlat) {
        this.nomPlat = nomPlat;
    }

    /**
     * Méthode permettant de modifier le prix du plat
     *
     * @param prix Nouveau prix du plat
     */
    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Plat{" +
                "nomPlat='" + nomPlat + '\'' +
                ", prix=" + prix +
                '}';
    }
}

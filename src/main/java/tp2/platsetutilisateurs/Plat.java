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
     * Description du plat
     */
    private String description;

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
     * @param description    Description du plat
     */
    public Plat(String nomPlat, float prix, String description) {
        this.nomPlat = nomPlat;
        this.prix = prix;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Plat{" +
                "nomPlat='" + nomPlat + '\'' +
                ", prix=" + prix + '\'' +
                ", description=" +description +
                '}';
    }
}

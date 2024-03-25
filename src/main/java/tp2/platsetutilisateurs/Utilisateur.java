package tp2.platsetutilisateurs;

public class Utilisateur {

    /**
     * nom de l'utilisateur
     */
    protected String nom;

    /**
     * mot de passe de l'utilisateur
     */
    protected String mdp;

    /**
     * mail de l'utilisateur
     */
    protected String mail;

    /**
     * Constructeur par défaut
     */
    public Utilisateur(){
    }

    /**
     * Constructeur de livre
     * @param nom nom de l'utilisateur
     * @param mdp mot de passe de l'utilisateur
     * @param mail mail de l'utilisateur
     */
    public Utilisateur(String nom, String mdp, String mail){
        this.nom = nom;
        this.mdp = mdp;
        this.mail = mail;
    }

    /**
     * Méthode permettant d'accéder au nom de l'utilisateur
     * @return un chaîne de caractères avec le nom de l'utilisateur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode permettant d'accéder au mot de passe de l'utilisateur
     * @return une chaîne de caractères avec le mot de passe de l'utilisateur
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Méthode permettant d'accéder au  mail de l'utilisateur
     * @return  une chaîne de caractères avec le mail de l'utilisateur
     */
    public String getMail() {
        return mail;
    }

    /**
     * Méthode permettant de modifier le nom de l'utilisateur
     * @param nom une chaîne de caractères avec le nom de l'utilisateur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode permettant de modifier le mot de passe de l'utilisateur
     * @param mdp une chaîne de caractères avec le mot de passe de l'utilisateur
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * Méthode permettant de modifier le mail de l'utilisateur
     * @param mail une chaîne de caractères avec mail de l'utilisateur
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "name='" + nom + '\'' +
                ", pwd='" + mdp + '\'' +
                ", mail=" + mail +
                '}';
    }
}

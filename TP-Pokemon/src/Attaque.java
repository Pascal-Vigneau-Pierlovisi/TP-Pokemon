public class Attaque{

    private String nom;
    private int genre;
    private int precision;
    private int puissance;
    private Types_Poke typeAtk;
    private static String[] nomGenre = {"Physique", "Special", "Statue"};


    public Attaque(String nNom, int nGenre, int nPrecision, int nPuissance){
        nom = nNom;
        genre = nGenre;
        precision = nPrecision;
        puissance = nPuissance;
    }

    //Getter
    public String getNom() {
        return nom;
    }

    public String getNomGenre() {
        return nomGenre[genre];
    }

    public int getPrecision() {
        return precision;
    }

    public int getPuissance() {
        return puissance;
    }

    public int calculEfficacite(Pokemon pokAdv){
        int degat = this.puissance;
        for (Types_Poke type : pokAdv.getLst_types()) {
            if (type.getFaiblesse().contains(this.typeAtk)){
                degat = 2 * this.puissance;
            }
        }
        return degat;
    }

}
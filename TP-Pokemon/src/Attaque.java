public class Attaque{

    private String nom;
    private int genre;
    private int precision;
    private int puissance;
    private String typeAtk;
    private static String[] nomGenre = {"Physique", "Special", "Statue"};


    public Attaque(String nNom, int nGenre, int nPrecision, int nPuissance, String nTypeAtk){
        nom = nNom;
        genre = nGenre;
        precision = nPrecision;
        puissance = nPuissance;
        if (nGenre == 2){
            puissance = 0;
        }
        try {
            typeAtk = nTypeAtk;
        } catch (Exception e) {
            // TODO: handle exception
        }
         
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

 
    public int calculEfficacite(Pokemon pokAdv, Types_Poke tabTypes){
        int degat = this.puissance;
        for (String type : pokAdv.getLst_types()) {
            if (tabTypes.getEfficacite(type).contains(this.typeAtk)){
                degat = 2 * this.puissance;
            }
        }
        return degat;
    }

    public int calculResistance(Pokemon pokAdv, Types_Poke tabTypes){
        int degat = this.puissance;
        for (String type : pokAdv.getLst_types()) {
            if (tabTypes.getPeuEfficace(type).contains(this.typeAtk)){
                degat = (int)0.5 * this.puissance;
            }
        }
        return degat;
    }

    public int isNeutre(Pokemon pokAdv, Types_Poke tabTypes){
        int degat = this.puissance;
        for (String type : pokAdv.getLst_types()) {
            if (tabTypes.getNeutralite(type).contains(this.typeAtk)){
                degat = (int)0 * this.puissance;
            }
        }
        return degat;
    }
    

}
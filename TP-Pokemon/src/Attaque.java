import java.io.Serializable;
import java.util.List;

public class Attaque implements Serializable{

    private String nom;
    private String genre;
    private int precision;
    private int puissance;
    private String typeAtk;
    private static String[] nomGenre = {"Physique", "Special", "Statut"};


    public Attaque(String nNom, String nGenre, int nPrecision, int nPuissance, String nTypeAtk, List<String> listType) throws NotATypeException{
        System.out.println(nTypeAtk);
        for (String type : listType){
            if (type.equals(nTypeAtk)) {
                typeAtk = nTypeAtk;
                nom = nNom;
                genre = nGenre;
                precision = nPrecision;
                puissance = nPuissance;
                if (nGenre == "Statut"){
                    puissance = 0;
                }
            }
        }
        
    
    }

    //Getter
    public String getNom() {
        return nom;
    }

    public String getNomGenre() {
        return genre;
    }

    public int getPrecision() {
        return precision;
    }

    public int getPuissance() {
        return puissance;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return nom;
    }

 
    public int calculEfficacite(Pokemon pokAdv){
        Types_Poke tabTypes = new Types_Poke();
        int degat = this.puissance;
        for (String type : pokAdv.getLst_types()) {
            if (tabTypes.getEfficacite(type).contains(this.typeAtk)){
                degat = 2 * this.puissance;
            }
        }
        return degat;
    }

    public int calculResistance(Pokemon pokAdv){
        Types_Poke tabTypes = new Types_Poke();
        int degat = this.puissance;
        for (String type : pokAdv.getLst_types()) {
            if (tabTypes.getPeuEfficace(type).contains(this.typeAtk)){
                degat = (int)0.5 * this.puissance;
            }
        }
        return degat;
    }

    public int isNeutre(Pokemon pokAdv){
        Types_Poke tabTypes = new Types_Poke();
        int degat = this.puissance;
        for (String type : pokAdv.getLst_types()) {
            if (tabTypes.getNeutralite(type).contains(this.typeAtk)){
                degat = (int)0 * this.puissance;
            }
        }
        return degat;
    }
    

}
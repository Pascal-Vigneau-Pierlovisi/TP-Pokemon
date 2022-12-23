import java.io.Serializable;
import java.util.List;

public class Attaque implements Serializable{

    private String nom;
    private String genre;
    private int precision;
    private int puissance;
    private String typeAtk;
    private static String[] nomGenre = {"Physique", "Special", "Statut"};

    /*Classe implémentant les attaques
     * contrairement aux Pokemons ou la table des types, elles ne sont pas tirées d'un fichier Excel
     * donc elles seront instancier en dur avec les Pokemons en fonctions de leur types.
     */
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

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return nom;
    }

 
    public int calculEfficacite(Pokemon pokAdv){
        Types_Poke tabTypes = new Types_Poke();
        int mult = 1;
        for (String type : pokAdv.getLst_types()) {
            if (tabTypes.getEfficacite(this.typeAtk).contains(type)){
                mult *= 2;
            }
        }
        return mult;
    }

    public int calculResistance(Pokemon pokAdv){
        Types_Poke tabTypes = new Types_Poke();
        int mult = 1;
        for (String type : pokAdv.getLst_types()) {
            if (tabTypes.getPeuEfficace(typeAtk).contains(type)){
                mult *= 0.5;
            }
        }
        return mult;
    }

    public int isNeutre(Pokemon pokAdv){
        Types_Poke tabTypes = new Types_Poke();
        int degat = 1;
        for (String type : pokAdv.getLst_types()) {
            if (tabTypes.getNeutralite(typeAtk).contains(type)){
                degat *= 0;
            }
        }
        return degat;
    }
    

}
import java.util.ArrayList;

public class Types_Poke {

    private String nom;
    private ArrayList<Types_Poke> faiblesse;
    private ArrayList<Types_Poke> efficacite;
    private ArrayList<Types_Poke> neutralite;

    public Types_Poke(String nNom, ArrayList<Types_Poke> lstFaiblesse, ArrayList<Types_Poke> lstEfficacite, ArrayList<Types_Poke> lstNeutralite){
        nom = nNom;
        faiblesse = lstFaiblesse;
        efficacite = lstEfficacite;
        lstNeutralite = neutralite;
    }

    //Getter
    public String getNom() {
        return nom;
    }

    public ArrayList<Types_Poke> getEfficacite() {
        return efficacite;
    }

    public ArrayList<Types_Poke> getFaiblesse() {
        return faiblesse;
    }

    public ArrayList<Types_Poke> getNeutralite() {
        return neutralite;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}

import java.util.ArrayList;

public class Pokemon<Types_Poke>{

    private String nom;
    //private Pokedex numPokedex;
    private int pv;
    private int atk;
    private int def;
    private int atkSpe;
    private int defSpe;
    private int vitesse;
    private ArrayList<Types_Poke> lst_types;

// Getter

    public int getAtk() {
        return atk;
    }

    public int getAtkSpe() {
        return atkSpe;
    }

    public int getDefSpe() {
        return defSpe;
    }

    public int getDefense() {
        return def;
    }

    public String getNom() {
        return nom;
    }

    public int getPv() {
        return pv;
    }

    public int getVitesse() {
        return vitesse;
    }

    public ArrayList<Types_Poke> getLst_types() {
        return lst_types;
    }

    // Setter

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setAtkSpe(int atkSpe) {
        this.atkSpe = atkSpe;
    }

    public void setDefSpe(int defSpe) {
        this.defSpe = defSpe;
    }

    public void setDefense(int defense) {
        this.def = defense;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public void setLst_types(ArrayList<Types_Poke> lst_types) {
        this.lst_types = lst_types;
    }

    // Constructeur

    public Pokemon(String nNom, int nPv, int nAtk, int nDef, int nAtkSpe, int nDefSpe, int nVitesse, ArrayList<Types_Poke> nTypes){
        nom = nNom;
        pv = nPv;
        atk = nAtk;
        def = nDef;
        atkSpe = nAtkSpe;
        defSpe = nDefSpe;
        vitesse = nVitesse;
    }

    public void evoluer(){
        System.out.println("KKK");
    }
}
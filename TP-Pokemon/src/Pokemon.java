import java.util.ArrayList;
import java.util.Random;

public class Pokemon<Types_Poke>{

    private String nom;
    private int numPokedex;
    private int pv;
    private int atk;
    private int def;
    private int atkSpe;
    private int defSpe;
    private int vitesse;
    private ArrayList<Types_Poke> lst_types;
    private ArrayList<Attaque> lesAttaques;
    Random r = new Random();

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

    public int getDef() {
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

    public ArrayList<Attaque> getLesAttaques() {
        return lesAttaques;
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

    public void setDef(int defense) {
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

    public void setLesAttaques(ArrayList<Attaque> lesAttaques) {
        this.lesAttaques = lesAttaques;
    }

    // Constructeur

    public Pokemon(){
        numPokedex = r.nextInt(37);

    }

    public void evoluer(){
        System.out.println("KKK");
    }
}
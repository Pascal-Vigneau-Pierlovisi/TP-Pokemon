import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Pokemon{

    private String nom;
    private int numPokedex;
    private double pv;
    private double atk;
    private double def;
    private double atkSpe;
    private double defSpe;
    private double vitesse;
    private ArrayList<Types_Poke> lst_types;
    private ArrayList<Attaque> lesAttaques;
    Random r = new Random();


    // Constructeur

    public Pokemon(Pokedex pokedex){  
        numPokedex = r.nextInt(37);
        System.out.println(pokedex.getPokedex().get(numPokedex).get(6).getClass() +"hého");
        nom = (String)pokedex.getPokedex().get(numPokedex).get(1);
        pv = (double)pokedex.getPokedex().get(numPokedex).get(6);
        atk = (double)pokedex.getPokedex().get(numPokedex).get(7);
        def = (double)pokedex.getPokedex().get(numPokedex).get(8);
        atkSpe = (double)pokedex.getPokedex().get(numPokedex).get(9);
        defSpe = (double)pokedex.getPokedex().get(numPokedex).get(10);
        vitesse = (double)pokedex.getPokedex().get(numPokedex).get(11);
        lst_types.set(0, (Types_Poke)pokedex.getPokedex().get(numPokedex).get(3));
        if ((String)pokedex.getPokedex().get(numPokedex).get(4) != " "){
            lst_types.set(0, (Types_Poke)pokedex.getPokedex().get(numPokedex).get(4));
        }
    }

// Getter

    public int getNumPokedex() {
        return numPokedex;
    }

    public double getAtk() {
        return atk;
    }

    public double getAtkSpe() {
        return atkSpe;
    }

    public double getDefSpe() {
        return defSpe;
    }

    public double getDef() {
        return def;
    }

    public String getNom() {
        return nom;
    }

    public double getPv() {
        return pv;
    }

    public double getVitesse() {
        return vitesse;
    }

    public ArrayList<Types_Poke> getLst_types() {
        return lst_types;
    }

    public ArrayList<Attaque> getLesAttaques() {
        return lesAttaques;
    }

    // Setter

    public void setAtk(double atk) {
        this.atk = atk;
    }

    public void setAtkSpe(double atkSpe) {
        this.atkSpe = atkSpe;
    }

    public void setDefSpe(double defSpe) {
        this.defSpe = defSpe;
    }

    public void setDef(double defense) {
        this.def = defense;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPv(double pv) {
        this.pv = pv;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public void setLst_types(ArrayList<Types_Poke> lst_types) {
        this.lst_types = lst_types;
    }

    public void setLesAttaques(ArrayList<Attaque> lesAttaques) {
        this.lesAttaques = lesAttaques;
    }

    public void evoluer(){
        System.out.println("KKK");
    }
}
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Pokemon implements Serializable{

    private String nom;
    private int numPokedex;
    private double pvBase;
    private double atkBase;
    private double defBase;
    private double atkSpeBase;
    private double defSpeBase;
    private double vitesseBase;
    private ArrayList<String> lst_types = new ArrayList<>();
    private ArrayList<Attaque> lesAttaques = new ArrayList<>(Arrays.asList(null, null, null, null));
    private Types_Poke listTypes = new Types_Poke();
    private int niveau = 1;
    private int pv = (int) (2 * pvBase * niveau/15 + 10 + niveau);
    private int atk = (int) (2 * atkBase * niveau/15 + 10 + niveau);
    private int def = (int) (2 * defBase * niveau/15 + 10 + niveau);
    private int atkSpe = (int) (2 * atkSpeBase * niveau/15 + 10 + niveau);
    private int defSpe = (int) (2 * defSpeBase * niveau/15 + 10 + niveau);
    private int vitesse = (int) (2 * vitesseBase * niveau/15 + 10 + niveau);
    private boolean ko;
    private Pokedex pokedex = new Pokedex(new File("/Users/pascalvigneau/Desktop/L3/Programmation - Orientée - Objet/TP-2/TP-Pokemon/TP-Pokemon/csv/kanto.xlsx"));
    
    Random r = new Random();
    


    // Constructeur
    /*
     * Construit le pokémon en obligeant qu'il soit au premier stade d'évolution
     * et lui donne toutes les stats répertorié dans le Pokedex.
     * Le constructeur donne aussi une ou deux attaques en fonction de son ou ses types
     */
    public Pokemon() throws NotATypeException{
        numPokedex = r.nextInt(37);
        while(!(boolean)pokedex.getPokedex().get(numPokedex).get(5)){
            numPokedex = r.nextInt(37);
        }
        nom = (String)pokedex.getPokedex().get(numPokedex).get(1);
        pvBase = (double)pokedex.getPokedex().get(numPokedex).get(6);
        atkBase = (double)pokedex.getPokedex().get(numPokedex).get(7);
        defBase = (double)pokedex.getPokedex().get(numPokedex).get(8);
        atkSpeBase = (double)pokedex.getPokedex().get(numPokedex).get(9);
        defSpeBase = (double)pokedex.getPokedex().get(numPokedex).get(10);
        vitesseBase = (double)pokedex.getPokedex().get(numPokedex).get(11);
        ko = false;
        lst_types.add((String)pokedex.getPokedex().get(numPokedex).get(3));
        if ((String)pokedex.getPokedex().get(numPokedex).get(4) != " "){
            lst_types.add((String)pokedex.getPokedex().get(numPokedex).get(4));
        }
        for(String type : this.getLst_types()){
            if(lesAttaques.get(0) == null){
                switch(type){
                    case "FIRE":
                        lesAttaques.set(0, new Attaque("Flammeche", "Special", 100, 40, "FIRE", listTypes.getListType())); 
                        break;
                    case "NORMAL":
                        lesAttaques.set(0, new Attaque("Charge", "Physique", 100, 40, "NORMAL", listTypes.getListType()));
                        break;
                    case "WATER":
                        lesAttaques.set(0, new Attaque("Pistolet à O", "Special", 100, 40, "WATER", listTypes.getListType()));
                        break;
                    case "POISON":
                        lesAttaques.set(0, new Attaque("Dard-Venin", "Physique", 100, 40, "POISON", listTypes.getListType()));
                        break;    
                    case "PLANT":
                        lesAttaques.set(0, new Attaque("Fouet liane", "Physique", 100, 40, "PLANT", listTypes.getListType()));
                        break;
                    case "ELECTRIC":
                        lesAttaques.set(0, new Attaque("Eclair", "Special", 100, 40, "ELECTRIC", listTypes.getListType()));
                        break;
                    case "GROUND":
                        lesAttaques.set(0, new Attaque("Coud'Boue", "Special", 100, 20, "GROUND", listTypes.getListType()));
                        break;
                    case "DRAGON":
                        lesAttaques.set(0, new Attaque("Draco-Souffle", "Special", 100, 60, "DRAGON", listTypes.getListType()));
                        break;
                    case "ICE":
                        lesAttaques.set(0, new Attaque("Eclat-glace", "Physique", 100, 40, "ICE", listTypes.getListType()));
                        break;
                    case "FLYING":
                        lesAttaques.set(0, new Attaque("Picpic", "Physique", 100, 35, "FLYING", listTypes.getListType()));
                        break;
                    case "PSYCHIC":
                        lesAttaques.set(0, new Attaque("Choc Mental", "Special", 100, 50, "PSYCHIC", listTypes.getListType()));
                        break;
                    case "COMBAT":
                        lesAttaques.set(0, new Attaque("Eclate-Roc", "Physique", 100, 40, "COMBAT", listTypes.getListType()));
                        break;
                    case "BUG":
                        lesAttaques.set(0, new Attaque("Dard-Nuée", "Physique", 100, 40, "BUG", listTypes.getListType()));
                        break;
                }
            } else{
                switch(type){
                    case "FIRE":
                        lesAttaques.set(1, new Attaque("Flammeche", "Special", 100, 40, "FIRE", listTypes.getListType())); 
                        break;
                    case "NORMAL":
                        lesAttaques.set(1, new Attaque("Charge", "Physique", 100, 40, "NORMAL", listTypes.getListType()));
                        break;
                    case "WATER":
                        lesAttaques.set(1, new Attaque("Pistolet à O", "Special", 100, 40, "WATER", listTypes.getListType()));
                        break;
                    case "POISON":
                        lesAttaques.set(1, new Attaque("Dard-Venin", "Physique", 100, 40, "POISON", listTypes.getListType()));
                        break;    
                    case "PLANT":
                        lesAttaques.set(1, new Attaque("Fouet liane", "Physique", 100, 40, "PLANT", listTypes.getListType()));
                        break;
                    case "ELECTRIC":
                        lesAttaques.set(1, new Attaque("Eclair", "Special", 100, 40, "ELECTRIC", listTypes.getListType()));
                        break;
                    case "GROUND":
                        lesAttaques.set(1, new Attaque("Coud'Boue", "Special", 100, 20, "GROUND", listTypes.getListType()));
                        break;
                    case "DRAGON":
                        lesAttaques.set(1, new Attaque("Draco-Souffle", "Special", 100, 60, "DRAGON", listTypes.getListType()));
                        break;
                    case "ICE":
                        lesAttaques.set(1, new Attaque("Eclat-glace", "Physique", 100, 40, "ICE", listTypes.getListType()));
                        break;
                    case "FLYING":
                        lesAttaques.set(1, new Attaque("Picpic", "Physique", 100, 35, "FLYING", listTypes.getListType()));
                        break;
                    case "PSYCHIC":
                        lesAttaques.set(1, new Attaque("Choc Mental", "Special", 100, 50, "PSYCHIC", listTypes.getListType()));
                        break;
                    case "COMBAT":
                        lesAttaques.set(1, new Attaque("Eclate-Roc", "Physique", 100, 40, "COMBAT", listTypes.getListType()));
                        break;
                    case "BUG":
                        lesAttaques.set(1, new Attaque("Dard-Nuée", "Physique", 100, 40, "BUG", listTypes.getListType()));
                        break;
                }
            }
        }
        
    }

// Getter

    public int getAtk() {
        return atk;
    }

    public int getAtkSpe() {
        return atkSpe;
    }

    public int getDef() {
        return def;
    }

    public int getDefSpe() {
        return defSpe;
    }

    public double getPvBase() {
        return pvBase;
    }

    public Types_Poke getListTypes() {
        return listTypes;
    }

    public double getVitesseBase() {
        return vitesseBase;
    }

    public int getNiveau() {
        return niveau;
    }

    public int getNumPokedex() {
        return numPokedex;
    }

    public double getAtkBase() {
        return atkBase;
    }

    public double getAtkSpeBase() {
        return atkSpeBase;
    }

    public double getDefBase() {
        return defBase;
    }

    public double getDefSpeBase() {
        return defSpeBase;
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

    public ArrayList<String> getLst_types() {
        return lst_types;
    }

    public ArrayList<Attaque> getLesAttaques() {
        return lesAttaques;
    }

    // Setter

    public void setAtkBase(double atkBase) {
        this.atkBase = atkBase;
    }

    public void setAtkSpeBase(double atkSpeBase) {
        this.atkSpeBase = atkSpeBase;
    }

    public void setDefBase(double defBase) {
        this.defBase = defBase;
    }

    public void setDefSpeBase(double defSpeBase) {
        this.defSpeBase = defSpeBase;
    }

    public void setListTypes(Types_Poke listTypes) {
        this.listTypes = listTypes;
    }

    public void setNumPokedex(int numPokedex) {
        this.numPokedex = numPokedex;
    }

    public void setPvBase(double pvBase) {
        this.pvBase = pvBase;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

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

    public void setLst_types(ArrayList<String> lst_types) {
        this.lst_types = lst_types;
    }

    public void setLesAttaques(ArrayList<Attaque> lesAttaques) {
        this.lesAttaques = lesAttaques;
    }

    public void setKo(boolean ko) {
        this.ko = ko;
    }

    //toString
    @Override
    public String toString() {
        
        return "Nom: " + this.nom + "; N°: " + this.numPokedex + "; Type: " + this.lst_types + "; Ses attaques: " + this.lesAttaques;
    }

    /*Fait évoluer le pokémon en lui rajoutant une ou deux attaques en fonction de ses types */
    public void evoluer() throws NotATypeException{
        if((boolean) pokedex.getPokedex().get(numPokedex).get(2)){
            numPokedex += 1; 
            nom = (String)pokedex.getPokedex().get(numPokedex).get(1);
            pvBase = (double)pokedex.getPokedex().get(numPokedex).get(6);
            atkBase = (double)pokedex.getPokedex().get(numPokedex).get(7);
            defBase = (double)pokedex.getPokedex().get(numPokedex).get(8);
            atkSpeBase = (double)pokedex.getPokedex().get(numPokedex).get(9);
            defSpeBase = (double)pokedex.getPokedex().get(numPokedex).get(10);
            vitesseBase = (double)pokedex.getPokedex().get(numPokedex).get(11);
            lst_types.clear();
            lst_types.add((String)pokedex.getPokedex().get(numPokedex).get(3));
            if ((String)pokedex.getPokedex().get(numPokedex).get(4) != " "){
                lst_types.add((String)pokedex.getPokedex().get(numPokedex).get(4));
            }
            if((boolean) pokedex.getPokedex().get(numPokedex).get(2)) {
                for(String type : this.getLst_types()){
                    if(lesAttaques.get(1) == null){
                        switch(type){
                            case "FIRE":
                                lesAttaques.set(1, new Attaque("Lance-Flamme", "Special", 100, 80, "FIRE", listTypes.getListType()));
                                break;
                            case "NORMAL":
                                lesAttaques.set(1, new Attaque("Attrition", "Physique", 100, 70, "NORMAL", listTypes.getListType()));
                                break;
                            case "WATER":
                                lesAttaques.set(1, new Attaque("Bulle d'O", "Special", 100, 60, "WATER", listTypes.getListType()));
                                break;
                            case "POISON":
                                lesAttaques.set(1, new Attaque("Choc Venin", "Physique", 100, 65, "POISON", listTypes.getListType()));
                                break;    
                            case "PLANT":
                                lesAttaques.set(1, new Attaque("Feuille Magic", "Special", 1000, 60, "PLANT", listTypes.getListType()));
                                break;
                            case "ELECTRIC":
                                lesAttaques.set(1, new Attaque("Etincelle", "Physique", 100, 60, "ELECTRIC", listTypes.getListType()));
                                break;
                            case "GROUND":
                                lesAttaques.set(1, new Attaque("Pietisol", "Physique", 100, 60, "GROUND", listTypes.getListType()));
                                break;
                            case "DRAGON":
                                lesAttaques.set(1, new Attaque("Draco-Griffe", "Physique", 100, 80, "DRAGON", listTypes.getListType()));
                                break;
                            case "ICE":
                                lesAttaques.set(1, new Attaque("Onde Boréale", "Special", 100, 65, "ICE", listTypes.getListType()));
                                break;
                            case "FLYING":
                                lesAttaques.set(1, new Attaque("Cru-aile", "Physique", 100, 60, "FLYING", listTypes.getListType()));
                                break;
                            case "PSYCHIC":
                                lesAttaques.set(1, new Attaque("Coupe Psycho", "Physique", 100, 70, "PSYCHIC", listTypes.getListType()));
                                break;
                            case "COMBAT":
                                lesAttaques.set(1, new Attaque("Balayette", "Physique", 100, 60, "COMBAT", listTypes.getListType()));
                                break;
                            case "BUG":
                                lesAttaques.set(1, new Attaque("Rayon Signal", "Special", 100, 75, "BUG", listTypes.getListType()));
                                break;
                        }
                    
                    } else if(lesAttaques.get(2) == null){
                        switch(type){
                            case "FIRE":
                                lesAttaques.set(2, new Attaque("Lance-Flamme", "Special", 100, 80, "FIRE", listTypes.getListType()));
                                break;
                            case "NORMAL":
                                lesAttaques.set(2, new Attaque("Attrition", "Physique", 100, 70, "NORMAL", listTypes.getListType()));
                                break;
                            case "WATER":
                                lesAttaques.set(2, new Attaque("Bulle d'O", "Special", 100, 60, "WATER", listTypes.getListType()));
                                break;
                            case "POISON":
                                lesAttaques.set(2, new Attaque("Choc Venin", "Physique", 100, 65, "POISON", listTypes.getListType()));
                                break;    
                            case "PLANT":
                                lesAttaques.set(2, new Attaque("Feuille Magic", "Special", 1000, 60, "PLANT", listTypes.getListType()));
                                break;
                            case "ELECTRIC":
                                lesAttaques.set(2, new Attaque("Etincelle", "Physique", 100, 60, "ELECTRIC", listTypes.getListType()));
                                break;
                            case "GROUND":
                                lesAttaques.set(2, new Attaque("Pietisol", "Physique", 100, 60, "GROUND", listTypes.getListType()));
                                break;
                            case "DRAGON":
                                lesAttaques.set(2, new Attaque("Draco-Griffe", "Physique", 100, 80, "DRAGON", listTypes.getListType()));
                                break;
                            case "ICE":
                                lesAttaques.set(2, new Attaque("Onde Boréale", "Special", 100, 65, "ICE", listTypes.getListType()));
                                break;
                            case "FLYING":
                                lesAttaques.set(2, new Attaque("Cru-aile", "Physique", 100, 60, "FLYING", listTypes.getListType()));
                                break;
                            case "PSYCHIC":
                                lesAttaques.set(2, new Attaque("Coupe Psycho", "Physique", 100, 70, "PSYCHIC", listTypes.getListType()));
                                break;
                            case "COMBAT":
                                lesAttaques.set(2, new Attaque("Balayette", "Physique", 100, 60, "COMBAT", listTypes.getListType()));
                                break;
                            case "BUG":
                                lesAttaques.set(2, new Attaque("Rayon Signal", "Special", 100, 75, "BUG", listTypes.getListType()));
                                break;
                        }
                    }else{
                        switch(type){
                            case "FIRE":
                                lesAttaques.set(3, new Attaque("Lance-Flamme", "Special", 100, 80, "FIRE", listTypes.getListType()));
                                break;
                            case "NORMAL":
                                lesAttaques.set(3, new Attaque("Attrition", "Physique", 100, 70, "NORMAL", listTypes.getListType()));
                                break;
                            case "WATER":
                                lesAttaques.set(3, new Attaque("Bulle d'O", "Special", 100, 60, "WATER", listTypes.getListType()));
                                break;
                            case "POISON":
                                lesAttaques.set(3, new Attaque("Choc Venin", "Physique", 100, 65, "POISON", listTypes.getListType()));
                                break;    
                            case "PLANT":
                                lesAttaques.set(3, new Attaque("Feuille Magic", "Special", 1000, 60, "PLANT", listTypes.getListType()));
                                break;
                            case "ELECTRIC":
                                lesAttaques.set(3, new Attaque("Etincelle", "Physique", 100, 60, "ELECTRIC", listTypes.getListType()));
                                break;
                            case "GROUND":
                                lesAttaques.set(3, new Attaque("Pietisol", "Physique", 100, 60, "GROUND", listTypes.getListType()));
                                break;
                            case "DRAGON":
                                lesAttaques.set(3, new Attaque("Draco-Griffe", "Physique", 100, 80, "DRAGON", listTypes.getListType()));
                                break;
                            case "ICE":
                                lesAttaques.set(3, new Attaque("Onde Boréale", "Special", 100, 65, "ICE", listTypes.getListType()));
                                break;
                            case "FLYING":
                                lesAttaques.set(3, new Attaque("Cru-aile", "Physique", 100, 60, "FLYING", listTypes.getListType()));
                                break;
                            case "PSYCHIC":
                                lesAttaques.set(3, new Attaque("Coupe Psycho", "Physique", 100, 70, "PSYCHIC", listTypes.getListType()));
                                break;
                            case "COMBAT":
                                lesAttaques.set(3, new Attaque("Balayette", "Physique", 100, 60, "COMBAT", listTypes.getListType()));
                                break;
                            case "BUG":
                                lesAttaques.set(3, new Attaque("Rayon Signal", "Special", 100, 75, "BUG", listTypes.getListType()));
                                break;
                        }
                    }
                }
            }
            
        };
    }

    /*
     * Permet d'attaquer un autre pokémon dans un combat en ligne, calculant tout les dégâts infligés.
     */
    public void attaquer(Pokemon pokAdv){
        Scanner myObj = new Scanner(System.in);
        int atkUtilise = myObj.nextInt();
        pokAdv.pv -= ((this.niveau * 0.4 + 2) * this.atk * this.lesAttaques.get(atkUtilise).getPuissance() / pokAdv.def * 50)
         * this.lesAttaques.get(atkUtilise).calculEfficacite(pokAdv) * this.lesAttaques.get(atkUtilise).calculResistance(pokAdv) * this.lesAttaques.get(atkUtilise).isNeutre(pokAdv);
        if (pokAdv.pv <= 0){
            pokAdv.ko = true;
        }
        myObj.close();
    }
}
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Pokemon implements Serializable {

    private String nom;
    private int numPokedex;
    private double pvBase;
    private double atkBase;
    private double defBase;
    private double atkSpeBase;
    private double defSpeBase;
    private double vitesseBase;
    private double tauxCapture;
    private ArrayList<String> lst_types = new ArrayList<>();
    private ArrayList<Attaque> lesAttaques = new ArrayList<>(Arrays.asList(null, null, null, null));
    private Types_Poke listTypes = new Types_Poke();
    private int niveau;
    private int pv;
    private int atk;
    private int def;
    private int atkSpe;
    private int defSpe;
    private int vitesse;
    private boolean ko;
    private Pokedex pokedex = new Pokedex(new File("TP-Pokemon/csv/kanto.xlsx"));

    Random r = new Random();

    // Constructeur
    /*
     * Construit le pokémon starter d'un nouveau joueur, soit
     * Bulbizarre, Salamèche et Carapuce(D'autre pour être rajouté avec le Excel).
     * Comme dit dans Attaque, les attaques des pokemons sont instanciés en dur à leur création
     * par soucis de Movepool de pokemon sous forme d'Excel existant.
     * Les statistique données sont plus au moins les stats officiels des pokemons
     * sans le calcul D'IV et d'EV, par soucis de logique d'implémentation.
     */
    public Pokemon(Dresseur dresseur) throws NotATypeException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choisissez votre starter! \n 1. Bulbizarre \n 4. Salamèche \n 7. Carapuce");
        int numPokedex = scan.nextInt();
        while (numPokedex != 1 && numPokedex != 4 && numPokedex != 7) {
            System.out.println("Ca ne correspond pas à un N° de starter!");
            numPokedex = scan.nextInt();
        }
        dresseur.getEquipe().add(this);
        niveau = 6;
        nom = (String) pokedex.getPokedex().get(numPokedex).get(1);
        pvBase = (double) pokedex.getPokedex().get(numPokedex).get(6);
        atkBase = (double) pokedex.getPokedex().get(numPokedex).get(7);
        defBase = (double) pokedex.getPokedex().get(numPokedex).get(8);
        atkSpeBase = (double) pokedex.getPokedex().get(numPokedex).get(9);
        defSpeBase = (double) pokedex.getPokedex().get(numPokedex).get(10);
        vitesseBase = (double) pokedex.getPokedex().get(numPokedex).get(11);
        tauxCapture = (double) pokedex.getPokedex().get(numPokedex).get(12);
        pv = (int) (2 * pvBase * niveau / 100 + 10 + niveau);
        atk = (int) ((2 * atkBase * niveau / 100) + 5);
        def = (int) ((2 * defBase * niveau / 100) + 5);
        atkSpe = (int) ((2 * atkSpeBase * niveau / 100) + 5);
        defSpe = (int) ((2 * defSpeBase * niveau / 100) + 5);
        vitesse = (int) ((2 * vitesseBase * niveau / 100) + 5);
        ko = false;
        lst_types.add((String) pokedex.getPokedex().get(numPokedex).get(3));
        if ((String) pokedex.getPokedex().get(numPokedex).get(4) != " ") {
            lst_types.add((String) pokedex.getPokedex().get(numPokedex).get(4));
        }
        for (String type : this.getLst_types()) {
            if (lesAttaques.get(0) == null) {
                switch (type) {
                    case "FIRE":
                        lesAttaques.set(0,
                                new Attaque("Flammeche", "Special", 100, 40, "FIRE", listTypes.getListType()));
                        break;
                    case "NORMAL":
                        lesAttaques.set(0,
                                new Attaque("Charge", "Physique", 100, 40, "NORMAL", listTypes.getListType()));
                        break;
                    case "WATER":
                        lesAttaques.set(0,
                                new Attaque("Pistolet à O", "Special", 100, 40, "WATER", listTypes.getListType()));
                        break;
                    case "POISON":
                        lesAttaques.set(0,
                                new Attaque("Dard-Venin", "Physique", 100, 40, "POISON", listTypes.getListType()));
                        break;
                    case "GRASS":
                        lesAttaques.set(0,
                                new Attaque("Fouet liane", "Physique", 100, 40, "GRASS", listTypes.getListType()));
                        break;
                    case "ELECTRIC":
                        lesAttaques.set(0,
                                new Attaque("Eclair", "Special", 100, 40, "ELECTRIC", listTypes.getListType()));
                        break;
                    case "GROUND":
                        lesAttaques.set(0,
                                new Attaque("Coud'Boue", "Special", 100, 20, "GROUND", listTypes.getListType()));
                        break;
                    case "DRAGON":
                        lesAttaques.set(0,
                                new Attaque("Draco-Souffle", "Special", 100, 60, "DRAGON", listTypes.getListType()));
                        break;
                    case "ICE":
                        lesAttaques.set(0,
                                new Attaque("Eclat-glace", "Physique", 100, 40, "ICE", listTypes.getListType()));
                        break;
                    case "FLYING":
                        lesAttaques.set(0,
                                new Attaque("Picpic", "Physique", 100, 35, "FLYING", listTypes.getListType()));
                        break;
                    case "PSYCHIC":
                        lesAttaques.set(0,
                                new Attaque("Choc Mental", "Special", 100, 50, "PSYCHIC", listTypes.getListType()));
                        break;
                    case "COMBAT":
                        lesAttaques.set(0,
                                new Attaque("Eclate-Roc", "Physique", 100, 40, "COMBAT", listTypes.getListType()));
                        break;
                    case "BUG":
                        lesAttaques.set(0,
                                new Attaque("Dard-Nuée", "Physique", 100, 40, "BUG", listTypes.getListType()));
                        break;
                }
            } else {
                switch (type) {
                    case "FIRE":
                        lesAttaques.set(1,
                                new Attaque("Flammeche", "Special", 100, 40, "FIRE", listTypes.getListType()));
                        break;
                    case "NORMAL":
                        lesAttaques.set(1,
                                new Attaque("Charge", "Physique", 100, 40, "NORMAL", listTypes.getListType()));
                        break;
                    case "WATER":
                        lesAttaques.set(1,
                                new Attaque("Pistolet à O", "Special", 100, 40, "WATER", listTypes.getListType()));
                        break;
                    case "POISON":
                        lesAttaques.set(1,
                                new Attaque("Dard-Venin", "Physique", 100, 40, "POISON", listTypes.getListType()));
                        break;
                    case "GRASS":
                        lesAttaques.set(1,
                                new Attaque("Fouet liane", "Physique", 100, 40, "GRASS", listTypes.getListType()));
                        break;
                    case "ELECTRIC":
                        lesAttaques.set(1,
                                new Attaque("Eclair", "Special", 100, 40, "ELECTRIC", listTypes.getListType()));
                        break;
                    case "GROUND":
                        lesAttaques.set(1,
                                new Attaque("Coud'Boue", "Special", 100, 20, "GROUND", listTypes.getListType()));
                        break;
                    case "DRAGON":
                        lesAttaques.set(1,
                                new Attaque("Draco-Souffle", "Special", 100, 60, "DRAGON", listTypes.getListType()));
                        break;
                    case "ICE":
                        lesAttaques.set(1,
                                new Attaque("Eclat-glace", "Physique", 100, 40, "ICE", listTypes.getListType()));
                        break;
                    case "FLYING":
                        lesAttaques.set(1,
                                new Attaque("Picpic", "Physique", 100, 35, "FLYING", listTypes.getListType()));
                        break;
                    case "PSYCHIC":
                        lesAttaques.set(1,
                                new Attaque("Choc Mental", "Special", 100, 50, "PSYCHIC", listTypes.getListType()));
                        break;
                    case "COMBAT":
                        lesAttaques.set(1,
                                new Attaque("Eclate-Roc", "Physique", 100, 40, "COMBAT", listTypes.getListType()));
                        break;
                    case "BUG":
                        lesAttaques.set(1,
                                new Attaque("Dard-Nuée", "Physique", 100, 40, "BUG", listTypes.getListType()));
                        break;
                }
            }
        }

    }


    public Pokemon(int nNiveau) throws NotATypeException {
        numPokedex = r.nextInt(pokedex.getPokedex().size()+1);
        niveau = r.nextInt((int) (nNiveau/1.5) ,nNiveau);
        nom = (String) pokedex.getPokedex().get(numPokedex).get(1);
        pvBase = (double) pokedex.getPokedex().get(numPokedex).get(6);
        atkBase = (double) pokedex.getPokedex().get(numPokedex).get(7);
        defBase = (double) pokedex.getPokedex().get(numPokedex).get(8);
        atkSpeBase = (double) pokedex.getPokedex().get(numPokedex).get(9);
        defSpeBase = (double) pokedex.getPokedex().get(numPokedex).get(10);
        vitesseBase = (double) pokedex.getPokedex().get(numPokedex).get(11);
        tauxCapture = (double) pokedex.getPokedex().get(numPokedex).get(12);
        pv = (int) (2 * pvBase * niveau / 100 + 10 + niveau);
        atk = (int) ((2 * atkBase * niveau / 100) + 5);
        def = (int) ((2 * defBase * niveau / 100) + 5);
        atkSpe = (int) ((2 * atkSpeBase * niveau / 100) + 5);
        defSpe = (int) ((2 * defSpeBase * niveau / 100) + 5);
        vitesse = (int) ((2 * vitesseBase * niveau / 100) + 5);
        ko = false;
        lst_types.add((String) pokedex.getPokedex().get(numPokedex).get(3));
        if ((String) pokedex.getPokedex().get(numPokedex).get(4) != " ") {
            lst_types.add((String) pokedex.getPokedex().get(numPokedex).get(4));
        }
        for (String type : this.getLst_types()) {
            if (lesAttaques.get(0) == null) {
                switch (type) {
                    case "FIRE":
                        lesAttaques.set(0,
                                new Attaque("Flammeche", "Special", 100, 40, "FIRE", listTypes.getListType()));
                        break;
                    case "NORMAL":
                        lesAttaques.set(0,
                                new Attaque("Charge", "Physique", 100, 40, "NORMAL", listTypes.getListType()));
                        break;
                    case "WATER":
                        lesAttaques.set(0,
                                new Attaque("Pistolet à O", "Special", 100, 40, "WATER", listTypes.getListType()));
                        break;
                    case "POISON":
                        lesAttaques.set(0,
                                new Attaque("Dard-Venin", "Physique", 100, 40, "POISON", listTypes.getListType()));
                        break;
                    case "GRASS":
                        lesAttaques.set(0,
                                new Attaque("Fouet liane", "Physique", 100, 40, "GRASS", listTypes.getListType()));
                        break;
                    case "ELECTRIC":
                        lesAttaques.set(0,
                                new Attaque("Eclair", "Special", 100, 40, "ELECTRIC", listTypes.getListType()));
                        break;
                    case "GROUND":
                        lesAttaques.set(0,
                                new Attaque("Coud'Boue", "Special", 100, 20, "GROUND", listTypes.getListType()));
                        break;
                    case "DRAGON":
                        lesAttaques.set(0,
                                new Attaque("Draco-Souffle", "Special", 100, 60, "DRAGON", listTypes.getListType()));
                        break;
                    case "ICE":
                        lesAttaques.set(0,
                                new Attaque("Eclat-glace", "Physique", 100, 40, "ICE", listTypes.getListType()));
                        break;
                    case "FLYING":
                        lesAttaques.set(0,
                                new Attaque("Picpic", "Physique", 100, 35, "FLYING", listTypes.getListType()));
                        break;
                    case "PSYCHIC":
                        lesAttaques.set(0,
                                new Attaque("Choc Mental", "Special", 100, 50, "PSYCHIC", listTypes.getListType()));
                        break;
                    case "COMBAT":
                        lesAttaques.set(0,
                                new Attaque("Eclate-Roc", "Physique", 100, 40, "COMBAT", listTypes.getListType()));
                        break;
                    case "BUG":
                        lesAttaques.set(0,
                                new Attaque("Dard-Nuée", "Physique", 100, 40, "BUG", listTypes.getListType()));
                        break;
                }
            } else {
                switch (type) {
                    case "FIRE":
                        lesAttaques.set(1,
                                new Attaque("Flammeche", "Special", 100, 40, "FIRE", listTypes.getListType()));
                        break;
                    case "NORMAL":
                        lesAttaques.set(1,
                                new Attaque("Charge", "Physique", 100, 40, "NORMAL", listTypes.getListType()));
                        break;
                    case "WATER":
                        lesAttaques.set(1,
                                new Attaque("Pistolet à O", "Special", 100, 40, "WATER", listTypes.getListType()));
                        break;
                    case "POISON":
                        lesAttaques.set(1,
                                new Attaque("Dard-Venin", "Physique", 100, 40, "POISON", listTypes.getListType()));
                        break;
                    case "GRASS":
                        lesAttaques.set(1,
                                new Attaque("Fouet liane", "Physique", 100, 40, "GRASS", listTypes.getListType()));
                        break;
                    case "ELECTRIC":
                        lesAttaques.set(1,
                                new Attaque("Eclair", "Special", 100, 40, "ELECTRIC", listTypes.getListType()));
                        break;
                    case "GROUND":
                        lesAttaques.set(1,
                                new Attaque("Coud'Boue", "Special", 100, 20, "GROUND", listTypes.getListType()));
                        break;
                    case "DRAGON":
                        lesAttaques.set(1,
                                new Attaque("Draco-Souffle", "Special", 100, 60, "DRAGON", listTypes.getListType()));
                        break;
                    case "ICE":
                        lesAttaques.set(1,
                                new Attaque("Eclat-glace", "Physique", 100, 40, "ICE", listTypes.getListType()));
                        break;
                    case "FLYING":
                        lesAttaques.set(1,
                                new Attaque("Picpic", "Physique", 100, 35, "FLYING", listTypes.getListType()));
                        break;
                    case "PSYCHIC":
                        lesAttaques.set(1,
                                new Attaque("Choc Mental", "Special", 100, 50, "PSYCHIC", listTypes.getListType()));
                        break;
                    case "COMBAT":
                        lesAttaques.set(1,
                                new Attaque("Eclate-Roc", "Physique", 100, 40, "COMBAT", listTypes.getListType()));
                        break;
                    case "BUG":
                        lesAttaques.set(1,
                                new Attaque("Dard-Nuée", "Physique", 100, 40, "BUG", listTypes.getListType()));
                        break;
                }
            }
        }

    }

    // Getter

    public double getTauxCapture() {
        return tauxCapture;
    }

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

    public boolean getKo() {
        return ko;
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

    /*Setter particulier car il redéfinie les stats des pokemons à chaque
     * passage de niveau de celui ci, et le fait aussi évoluer au passage
     * de niveau voulu
     */
    public void setNiveauIncr() throws NotATypeException {
        this.niveau += 1;
        pv = (int) (2 * pvBase * niveau / 100 + 10 + niveau);
        atk = (int) (2 * atkBase * niveau / 100 + 5);
        def = (int) ((2 * defBase * niveau / 100) + 5);
        atkSpe = (int) ((2 * atkSpeBase * niveau / 100) + 5);
        defSpe = (int) ((2 * defSpeBase * niveau / 100) + 5);
        vitesse = (int) ((2 * vitesseBase * niveau / 100) + 5);
        if (niveau == 18) {
            String nomPre = nom;
            System.out.println("Quoi?! " + nom + " évolue!");
            evoluer1();
            System.out.println("Félicitations! " + nomPre + " a évolué en " + nom);
        }
        if (niveau == 36) {
            String nomPre = nom;
            System.out.println("Quoi?! " + nom + " évolue!");
            evoluer2();
            System.out.println("Félicitations! " + nomPre + " a évolué en " + nom);
        }
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

    public void setTauxCapture(double tauxCapture) {
        this.tauxCapture = tauxCapture;
    }

    // toString
    @Override
    public String toString() {

        return "Nom: " + this.nom +"; PV: " + this.pv + "/" + (int) (2 * pvBase * niveau / 100 + 10 + niveau) + "; Type: " + this.lst_types + "; Ses attaques: "
                + this.lesAttaques + " Stats: " + this.atk+"|" + this.atkSpe +"|" + this.def +"|" + this.defSpe +"|" + this.vitesse + " KO? " + this.ko;
    }

    /* Enlève des PVs aux pokémons après avoir subit des dégâts */
    public void subirDegat(int degat){
        this.pv = pv - degat; 
    }

    /* Soigne le pokemon en remettant ses PVs au maximum
     * et changeant son état de KO si nécessaire.
     */
    public void soigner() {
        pv = (int) (2 * pvBase * niveau / 100 + 10 + niveau);
        if(ko == true){
            ko = false;
        }
    }

    /*
     * Fait évoluer le pokémon en lui rajoutant une ou deux attaques en fonction de
     * ses types. evoluer1() est faites par le passage du premier au second stade
     * et evoluer2() du second au troisième.
     */
    public void evoluer2() throws NotATypeException {
        if ((boolean) pokedex.getPokedex().get(numPokedex).get(2)) {
            numPokedex += 1;
            nom = (String) pokedex.getPokedex().get(numPokedex).get(1);
            pvBase = (double) pokedex.getPokedex().get(numPokedex).get(6);
            atkBase = (double) pokedex.getPokedex().get(numPokedex).get(7);
            defBase = (double) pokedex.getPokedex().get(numPokedex).get(8);
            atkSpeBase = (double) pokedex.getPokedex().get(numPokedex).get(9);
            defSpeBase = (double) pokedex.getPokedex().get(numPokedex).get(10);
            vitesseBase = (double) pokedex.getPokedex().get(numPokedex).get(11);
            pv = (int) (2 * pvBase * niveau / 100 + 10 + niveau);
            atk = (int) ((2 * atkBase * niveau / 100) + 5);
            def = (int) ((2 * defBase * niveau / 100) + 5);
            atkSpe = (int) ((2 * atkSpeBase * niveau / 100) + 5);
            defSpe = (int) ((2 * defSpeBase * niveau / 100) + 5);
            vitesse = (int) ((2 * vitesseBase * niveau / 100) + 5);
            lst_types.clear();
            lst_types.add((String) pokedex.getPokedex().get(numPokedex).get(3));
            if ((String) pokedex.getPokedex().get(numPokedex).get(4) != " ") {
                lst_types.add((String) pokedex.getPokedex().get(numPokedex).get(4));
            }
            if ((boolean) pokedex.getPokedex().get(numPokedex).get(2)) {
                for (String type : this.getLst_types()) {
                    if (lesAttaques.get(1).getPuissance() < 50) {
                        switch (type) {
                            case "FIRE":
                                lesAttaques.set(1, new Attaque("Boutefeu", "Physique", 100, 120, "FIRE",
                                        listTypes.getListType()));
                                break;
                            case "NORMAL":
                                lesAttaques.set(1, new Attaque("Ultra-Laser", "Special", 90, 150, "NORMAL",
                                        listTypes.getListType()));
                                break;
                            case "WATER":
                                lesAttaques.set(1,
                                        new Attaque("Hydrocannon", "Special", 100, 80, "WATER",
                                                listTypes.getListType()));
                                break;
                            case "POISON":
                                lesAttaques.set(1, new Attaque("Détricanon", "Special", 100, 120, "POISON",
                                        listTypes.getListType()));
                                break;
                            case "GRASS":
                                lesAttaques.set(1, new Attaque("Mega-Fouet", "Physique", 85, 120, "GRASS",
                                        listTypes.getListType()));
                                break;
                            case "ELECTRIC":
                                lesAttaques.set(1, new Attaque("Tonnerre", "Physique", 100, 90, "ELECTRIC",
                                        listTypes.getListType()));
                                break;
                            case "GROUND":
                                lesAttaques.set(1, new Attaque("Seisme", "Physique", 100, 100, "GROUND",
                                        listTypes.getListType()));
                                break;
                            case "DRAGON":
                                lesAttaques.set(1, new Attaque("Draco-Météore", "Physique", 100, 130, "DRAGON",
                                        listTypes.getListType()));
                                break;
                            case "ICE":
                                lesAttaques.set(1, new Attaque("Laser glace", "Special", 100, 90, "ICE",
                                        listTypes.getListType()));
                                break;
                            case "FLYING":
                                lesAttaques.set(1, new Attaque("Vent Violent", "Physique", 100, 100, "FLYING",
                                        listTypes.getListType()));
                                break;
                            case "PSYCHIC":
                                lesAttaques.set(1, new Attaque("Psyko", "Physique", 100, 90, "PSYCHIC",
                                        listTypes.getListType()));
                                break;
                            case "COMBAT":
                                lesAttaques.set(1, new Attaque("Close Combat", "Physique", 100, 120, "COMBAT",
                                        listTypes.getListType()));
                                break;
                            case "BUG":
                                lesAttaques.set(1, new Attaque("Bourdon", "Special", 100, 90, "BUG",
                                        listTypes.getListType()));
                                break;
                        }

                    } else if (lesAttaques.get(2).getPuissance() < 50) {
                        switch (type) {
                            case "FIRE":
                                lesAttaques.set(1, new Attaque("Boutefeu", "Physique", 100, 120, "FIRE",
                                        listTypes.getListType()));
                                break;
                            case "NORMAL":
                                lesAttaques.set(1, new Attaque("Ultra-Laser", "Special", 90, 150, "NORMAL",
                                        listTypes.getListType()));
                                break;
                            case "WATER":
                                lesAttaques.set(1,
                                        new Attaque("Hydrocannon", "Special", 100, 80, "WATER",
                                                listTypes.getListType()));
                                break;
                            case "POISON":
                                lesAttaques.set(1, new Attaque("Détricanon", "Special", 100, 120, "POISON",
                                        listTypes.getListType()));
                                break;
                            case "GRASS":
                                lesAttaques.set(1, new Attaque("Mega-Fouet", "Physique", 85, 120, "GRASS",
                                        listTypes.getListType()));
                                break;
                            case "ELECTRIC":
                                lesAttaques.set(1, new Attaque("Tonnerre", "Physique", 100, 90, "ELECTRIC",
                                        listTypes.getListType()));
                                break;
                            case "GROUND":
                                lesAttaques.set(1, new Attaque("Seisme", "Physique", 100, 100, "GROUND",
                                        listTypes.getListType()));
                                break;
                            case "DRAGON":
                                lesAttaques.set(1, new Attaque("Draco-Météore", "Physique", 100, 130, "DRAGON",
                                        listTypes.getListType()));
                                break;
                            case "ICE":
                                lesAttaques.set(1, new Attaque("Laser glace", "Special", 100, 90, "ICE",
                                        listTypes.getListType()));
                                break;
                            case "FLYING":
                                lesAttaques.set(1, new Attaque("Vent Violent", "Physique", 100, 100, "FLYING",
                                        listTypes.getListType()));
                                break;
                            case "PSYCHIC":
                                lesAttaques.set(1, new Attaque("Psyko", "Physique", 100, 90, "PSYCHIC",
                                        listTypes.getListType()));
                                break;
                            case "COMBAT":
                                lesAttaques.set(1, new Attaque("Close Combat", "Physique", 100, 120, "COMBAT",
                                        listTypes.getListType()));
                                break;
                            case "BUG":
                                lesAttaques.set(1, new Attaque("Bourdon", "Special", 100, 90, "BUG",
                                        listTypes.getListType()));
                                break;
                        }
                    } else {
                        switch (type) {
                            case "FIRE":
                                lesAttaques.set(3, new Attaque("Lance-Flamme", "Special", 100, 80, "FIRE",
                                        listTypes.getListType()));
                                break;
                            case "NORMAL":
                                lesAttaques.set(3, new Attaque("Attrition", "Physique", 100, 70, "NORMAL",
                                        listTypes.getListType()));
                                break;
                            case "WATER":
                                lesAttaques.set(3,
                                        new Attaque("Bulle d'O", "Special", 100, 60, "WATER", listTypes.getListType()));
                                break;
                            case "POISON":
                                lesAttaques.set(3, new Attaque("Choc Venin", "Physique", 100, 65, "POISON",
                                        listTypes.getListType()));
                                break;
                            case "GRASS":
                                lesAttaques.set(3, new Attaque("Feuille Magic", "Special", 1000, 60, "GRASS",
                                        listTypes.getListType()));
                                break;
                            case "ELECTRIC":
                                lesAttaques.set(3, new Attaque("Etincelle", "Physique", 100, 60, "ELECTRIC",
                                        listTypes.getListType()));
                                break;
                            case "GROUND":
                                lesAttaques.set(3, new Attaque("Pietisol", "Physique", 100, 60, "GROUND",
                                        listTypes.getListType()));
                                break;
                            case "DRAGON":
                                lesAttaques.set(3, new Attaque("Draco-Griffe", "Physique", 100, 80, "DRAGON",
                                        listTypes.getListType()));
                                break;
                            case "ICE":
                                lesAttaques.set(3, new Attaque("Onde Boréale", "Special", 100, 65, "ICE",
                                        listTypes.getListType()));
                                break;
                            case "FLYING":
                                lesAttaques.set(3, new Attaque("Cru-aile", "Physique", 100, 60, "FLYING",
                                        listTypes.getListType()));
                                break;
                            case "PSYCHIC":
                                lesAttaques.set(3, new Attaque("Coupe Psycho", "Physique", 100, 70, "PSYCHIC",
                                        listTypes.getListType()));
                                break;
                            case "COMBAT":
                                lesAttaques.set(3, new Attaque("Balayette", "Physique", 100, 60, "COMBAT",
                                        listTypes.getListType()));
                                break;
                            case "BUG":
                                lesAttaques.set(3, new Attaque("Rayon Signal", "Special", 100, 75, "BUG",
                                        listTypes.getListType()));
                                break;
                        }
                    }
                }
            }

        }
        ;
    }

    public void evoluer1() throws NotATypeException {
        if ((boolean) pokedex.getPokedex().get(numPokedex).get(2)) {
            numPokedex += 1;
            nom = (String) pokedex.getPokedex().get(numPokedex).get(1);
            pvBase = (double) pokedex.getPokedex().get(numPokedex).get(6);
            atkBase = (double) pokedex.getPokedex().get(numPokedex).get(7);
            defBase = (double) pokedex.getPokedex().get(numPokedex).get(8);
            atkSpeBase = (double) pokedex.getPokedex().get(numPokedex).get(9);
            defSpeBase = (double) pokedex.getPokedex().get(numPokedex).get(10);
            vitesseBase = (double) pokedex.getPokedex().get(numPokedex).get(11);
            pv = (int) (2 * pvBase * niveau / 100 + 10 + niveau);
            atk = (int) ((2 * atkBase * niveau / 100) + 5);
            def = (int) ((2 * defBase * niveau / 100) + 5);
            atkSpe = (int) ((2 * atkSpeBase * niveau / 100) + 5);
            defSpe = (int) ((2 * defSpeBase * niveau / 100) + 5);
            vitesse = (int) ((2 * vitesseBase * niveau / 100) + 5);
            lst_types.clear();
            lst_types.add((String) pokedex.getPokedex().get(numPokedex).get(3));
            if ((String) pokedex.getPokedex().get(numPokedex).get(4) != " ") {
                lst_types.add((String) pokedex.getPokedex().get(numPokedex).get(4));
            }
            if ((boolean) pokedex.getPokedex().get(numPokedex).get(2)) {
                for (String type : this.getLst_types()) {
                    if (lesAttaques.get(1) == null) {
                        switch (type) {
                            case "FIRE":
                                lesAttaques.set(1, new Attaque("Lance-Flamme", "Special", 100, 80, "FIRE",
                                        listTypes.getListType()));
                                break;
                            case "NORMAL":
                                lesAttaques.set(1, new Attaque("Attrition", "Physique", 100, 70, "NORMAL",
                                        listTypes.getListType()));
                                break;
                            case "WATER":
                                lesAttaques.set(1,
                                        new Attaque("Bulle d'O", "Special", 100, 60, "WATER", listTypes.getListType()));
                                break;
                            case "POISON":
                                lesAttaques.set(1, new Attaque("Choc Venin", "Physique", 100, 65, "POISON",
                                        listTypes.getListType()));
                                break;
                            case "GRASS":
                                lesAttaques.set(1, new Attaque("Feuille Magic", "Special", 1000, 60, "GRASS",
                                        listTypes.getListType()));
                                break;
                            case "ELECTRIC":
                                lesAttaques.set(1, new Attaque("Etincelle", "Physique", 100, 60, "ELECTRIC",
                                        listTypes.getListType()));
                                break;
                            case "GROUND":
                                lesAttaques.set(1, new Attaque("Pietisol", "Physique", 100, 60, "GROUND",
                                        listTypes.getListType()));
                                break;
                            case "DRAGON":
                                lesAttaques.set(1, new Attaque("Draco-Griffe", "Physique", 100, 80, "DRAGON",
                                        listTypes.getListType()));
                                break;
                            case "ICE":
                                lesAttaques.set(1, new Attaque("Onde Boréale", "Special", 100, 65, "ICE",
                                        listTypes.getListType()));
                                break;
                            case "FLYING":
                                lesAttaques.set(1, new Attaque("Cru-aile", "Physique", 100, 60, "FLYING",
                                        listTypes.getListType()));
                                break;
                            case "PSYCHIC":
                                lesAttaques.set(1, new Attaque("Coupe Psycho", "Physique", 100, 70, "PSYCHIC",
                                        listTypes.getListType()));
                                break;
                            case "COMBAT":
                                lesAttaques.set(1, new Attaque("Balayette", "Physique", 100, 60, "COMBAT",
                                        listTypes.getListType()));
                                break;
                            case "BUG":
                                lesAttaques.set(1, new Attaque("Rayon Signal", "Special", 100, 75, "BUG",
                                        listTypes.getListType()));
                                break;
                        }

                    } else if (lesAttaques.get(2) == null) {
                        switch (type) {
                            case "FIRE":
                                lesAttaques.set(2, new Attaque("Lance-Flamme", "Special", 100, 80, "FIRE",
                                        listTypes.getListType()));
                                break;
                            case "NORMAL":
                                lesAttaques.set(2, new Attaque("Attrition", "Physique", 100, 70, "NORMAL",
                                        listTypes.getListType()));
                                break;
                            case "WATER":
                                lesAttaques.set(2,
                                        new Attaque("Bulle d'O", "Special", 100, 60, "WATER", listTypes.getListType()));
                                break;
                            case "POISON":
                                lesAttaques.set(2, new Attaque("Choc Venin", "Physique", 100, 65, "POISON",
                                        listTypes.getListType()));
                                break;
                            case "GRASS":
                                lesAttaques.set(2, new Attaque("Feuille Magic", "Special", 1000, 60, "GRASS",
                                        listTypes.getListType()));
                                break;
                            case "ELECTRIC":
                                lesAttaques.set(2, new Attaque("Etincelle", "Physique", 100, 60, "ELECTRIC",
                                        listTypes.getListType()));
                                break;
                            case "GROUND":
                                lesAttaques.set(2, new Attaque("Pietisol", "Physique", 100, 60, "GROUND",
                                        listTypes.getListType()));
                                break;
                            case "DRAGON":
                                lesAttaques.set(2, new Attaque("Draco-Griffe", "Physique", 100, 80, "DRAGON",
                                        listTypes.getListType()));
                                break;
                            case "ICE":
                                lesAttaques.set(2, new Attaque("Onde Boréale", "Special", 100, 65, "ICE",
                                        listTypes.getListType()));
                                break;
                            case "FLYING":
                                lesAttaques.set(2, new Attaque("Cru-aile", "Physique", 100, 60, "FLYING",
                                        listTypes.getListType()));
                                break;
                            case "PSYCHIC":
                                lesAttaques.set(2, new Attaque("Coupe Psycho", "Physique", 100, 70, "PSYCHIC",
                                        listTypes.getListType()));
                                break;
                            case "COMBAT":
                                lesAttaques.set(2, new Attaque("Balayette", "Physique", 100, 60, "COMBAT",
                                        listTypes.getListType()));
                                break;
                            case "BUG":
                                lesAttaques.set(2, new Attaque("Rayon Signal", "Special", 100, 75, "BUG",
                                        listTypes.getListType()));
                                break;
                        }
                    } else {
                        switch (type) {
                            case "FIRE":
                                lesAttaques.set(3, new Attaque("Lance-Flamme", "Special", 100, 80, "FIRE",
                                        listTypes.getListType()));
                                break;
                            case "NORMAL":
                                lesAttaques.set(3, new Attaque("Attrition", "Physique", 100, 70, "NORMAL",
                                        listTypes.getListType()));
                                break;
                            case "WATER":
                                lesAttaques.set(3,
                                        new Attaque("Bulle d'O", "Special", 100, 60, "WATER", listTypes.getListType()));
                                break;
                            case "POISON":
                                lesAttaques.set(3, new Attaque("Choc Venin", "Physique", 100, 65, "POISON",
                                        listTypes.getListType()));
                                break;
                            case "GRASS":
                                lesAttaques.set(3, new Attaque("Feuille Magic", "Special", 1000, 60, "GRASS",
                                        listTypes.getListType()));
                                break;
                            case "ELECTRIC":
                                lesAttaques.set(3, new Attaque("Etincelle", "Physique", 100, 60, "ELECTRIC",
                                        listTypes.getListType()));
                                break;
                            case "GROUND":
                                lesAttaques.set(3, new Attaque("Pietisol", "Physique", 100, 60, "GROUND",
                                        listTypes.getListType()));
                                break;
                            case "DRAGON":
                                lesAttaques.set(3, new Attaque("Draco-Griffe", "Physique", 100, 80, "DRAGON",
                                        listTypes.getListType()));
                                break;
                            case "ICE":
                                lesAttaques.set(3, new Attaque("Onde Boréale", "Special", 100, 65, "ICE",
                                        listTypes.getListType()));
                                break;
                            case "FLYING":
                                lesAttaques.set(3, new Attaque("Cru-aile", "Physique", 100, 60, "FLYING",
                                        listTypes.getListType()));
                                break;
                            case "PSYCHIC":
                                lesAttaques.set(3, new Attaque("Coupe Psycho", "Physique", 100, 70, "PSYCHIC",
                                        listTypes.getListType()));
                                break;
                            case "COMBAT":
                                lesAttaques.set(3, new Attaque("Balayette", "Physique", 100, 60, "COMBAT",
                                        listTypes.getListType()));
                                break;
                            case "BUG":
                                lesAttaques.set(3, new Attaque("Rayon Signal", "Special", 100, 75, "BUG",
                                        listTypes.getListType()));
                                break;
                        }
                    }
                }
            }

        }
        ;
    }
}
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.io.Serializable;

/*Classe qui va servir de Client pour la communication
* avec le serveur(Arène). Il attendra que d'autre Drasseur 
* se connecte à l'Arène puis pourra utiliser une méthode
* combatPokemon(DresseurAdverse) pour demander un combat
*/
public class Dresseur implements Serializable {
    private String pseudo;
    private List<Pokemon> equipe = new ArrayList<>();
    private int pokeDollars;
    private boolean enCombat;
    private boolean enQueue;
    private List<Pokemon> pokeKo = new ArrayList<>();
    private int pokeball;
    private int superbonbon;
    private boolean attente;

    /* Le joueur principal utilisé pour la partie en ligne et solo
     * instancié directement avec un Pokemon en starter parmis
     * Bulbizarre, Salamèche et Carapuce.
     */
    public Dresseur(String nPseudo) throws NotATypeException {
        Scanner scan = new Scanner(System.in);
        pseudo = nPseudo;
        pokeDollars = 0;
        enCombat = false;
        attente = false;
        Pokemon pokemon = new Pokemon(this);
    }

    // Getter

    public boolean getEnCombat() {
        return enCombat;
    }

    public int getPokeball() {
        return pokeball;
    }

    public int getSuperbonbon() {
        return superbonbon;
    }

    public boolean getAttente(){
        return attente;
    }

    public int getPokeDollars() {
        return pokeDollars;
    }

    public String getPseudo() {
        return pseudo;
    }

    public boolean getEnQueue() {
        return enQueue;
    }

    public List<Pokemon> getEquipe() {
        return equipe;
    }

    public List<Pokemon> getPokeKo() {
        return pokeKo;
    }

    // Setter
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setEnQueue(boolean enQueue) {
        this.enQueue = enQueue;
    }

    public void setEnCombat(boolean enCombat) {
        this.enCombat = enCombat;
    }

    public void setEquipe(List<Pokemon> equipe) {
        this.equipe = equipe;
    }

    public void setAttente(boolean attente) {
        this.attente = attente;
    }

    public void setPokeDollars(int pokeDollars) {
        this.pokeDollars = pokeDollars;
    }

    public void setPokeKo(List<Pokemon> pokeKo) {
        this.pokeKo = pokeKo;
    }

    @Override
    public String toString() {
        return "; Pseudo: " + this.pseudo + "; equipe: " + this.equipe + "; argent: "
                + this.pokeDollars;
    }

    /* Interface de combat contre les pokemons sauvages */
    public void combat(Pokemon pokeAdv) throws NotATypeException, InterruptedException {
        System.out.println("Un " + pokeAdv.getNom() + " sauvage apparaît!\n");
        Random r = new Random();
        Pokemon combattant = choisirPremierPokemon();
        Scanner scan = new Scanner(System.in);
        Attaque atkChoisi = null;
        while (enCombat) {
            System.out.println(combattant.getNom() +"\nNv." + combattant.getNiveau() +" "+ combattant.getPv() + "/"
                    + (int) (2 * combattant.getPvBase() * combattant.getNiveau() / 100 + 10 + combattant.getNiveau())+ "\n");
            System.out.println(pokeAdv.getNom() +"\nNv." + pokeAdv.getNiveau() +" "+ pokeAdv.getPv() + "/"
                    + (int) (2 * pokeAdv.getPvBase() * pokeAdv.getNiveau() / 100 + 10 + pokeAdv.getNiveau()));
            System.out.println(" 1. Attaquer \n 2. Changer de Pokemon \n 3. Utiliser une pokeball \n 4. Fuite");
            int input = scan.nextInt();
            if (input == 1) {
                atkChoisi = this.choisirAttaquePokemon(combattant);
                // Compare la vitesse des pokemons et les laissent attaquer en fonction de la
                // priorité
                if (combattant.getVitesse() < pokeAdv.getVitesse()) {
                    System.out.println(combattant.getNom() + " est moins rapide que " + pokeAdv.getNom());
                    List<Attaque> atksAdv = new ArrayList<>();
                    for(Attaque atkAdv : pokeAdv.getLesAttaques()){
                        if(atkAdv != null){   
                            atksAdv.add(atkAdv);
                        }
                    }
                    int intAtk = r.nextInt(atksAdv.size());
                    Attaque atkAdv = pokeAdv.getLesAttaques().get(intAtk);
                    attaquer(pokeAdv, atkAdv, combattant);
                    if (combattant.getKo() == false) {
                        attaquer(combattant, atkChoisi, pokeAdv);
                    }
                    else{
                        pokeKo.add(combattant);
                    }
                } else {
                    List<Attaque> atksAdv = new ArrayList<>();
                    for(Attaque atkAdv : pokeAdv.getLesAttaques()){
                        if(atkAdv != null){   
                            atksAdv.add(atkAdv);
                        }
                    }
                    int intAtk = r.nextInt(atksAdv.size());
                    Attaque atkAdv = pokeAdv.getLesAttaques().get(intAtk);
                    attaquer(combattant, atkChoisi, pokeAdv);
                    System.out.println(combattant.getNom() + " est plus rapide que " + pokeAdv.getNom());
                    if (pokeAdv.getKo() == false) {
                        attaquer(pokeAdv, atkAdv, combattant);
                    }
                }
                if (pokeAdv.getKo()) {
                    System.out.println(pokeAdv.getNom() + " est KO, vous remportez 100P");
                    pokeDollars += 100;
                    enCombat = false;
                }
                if (combattant.getKo()) {
                    pokeKo.add(combattant);
                    changerPokemon();
                }
                if (pokeKo.size() == equipe.size()) {
                    System.out.println("Vous êtes incapable de continuer le combat.");
                    System.out.print("Vous courrez vers le centre Pokemon.");
                    System.out.print("\u001B[A");
                    System.out.print("Vous courrez vers le centre Pokemon..");
                    System.out.print("\u001B[A");
                    System.out.print("Vous courrez vers le centre Pokemon...");
                    System.out.print("\u001B[A");
                    pokeDollars *= 9 / 10;
                    enCombat = false;
                    centrePokemon(equipe);
                }
            } else if (input == 2) {
                combattant = this.changerPokemon();
            } else if (input == 3){
                capturer(pokeAdv);
            }
            else{
                System.out.println("Vous prenez la fuite!");
                break;
            }

        }
    }

    /*
     * Fonction de transition entre interface et combat
     * 4 niveaux différents(Pour l'instant inutile)
     */
    public void hautesHerbesFacile() throws NotATypeException, InterruptedException {
        System.out.print("Recherche de pokemon sauvage.\n");
        Thread.sleep(1000);
        System.out.print("\u001B[A");
        System.out.print("Recherche de pokemon sauvage..\n");
        Thread.sleep(1000);
        System.out.print("\u001B[A");
        System.out.print("Recherche de pokemon sauvage...\n");
        Thread.sleep(1000);
        System.out.print("\u001B[A");
        Pokemon pokeAdv = new Pokemon(18);
        enCombat = true;
        combat(pokeAdv);
    }

    public void hautesHerbesTresFacile() throws NotATypeException, InterruptedException {
        System.out.print("Recherche de pokemon sauvage.\n");
        Thread.sleep(1000);
        System.out.print("\u001B[A");
        System.out.print("Recherche de pokemon sauvage..\n");
        Thread.sleep(1000);
        System.out.print("\u001B[A");
        System.out.print("Recherche de pokemon sauvage...\n");
        Thread.sleep(1000);
        System.out.print("\u001B[A");
        Pokemon pokeAdv = new Pokemon(10);
        enCombat = true;
        combat(pokeAdv);
    }

    public void hautesHerbesMoyen() throws NotATypeException, InterruptedException {
        System.out.print("Recherche de pokemon sauvage.\n");
        Thread.sleep(1000);
        System.out.print("\u001B[A");
        System.out.print("Recherche de pokemon sauvage..\n");
        Thread.sleep(1000);
        System.out.print("\u001B[A");
        System.out.print("Recherche de pokemon sauvage...\n");
        Thread.sleep(1000);
        System.out.print("\u001B[A");
        Pokemon pokeAdv = new Pokemon(36);
        enCombat = true;
        combat(pokeAdv);
    }

    public void hautesHerbesDifficile() throws NotATypeException, InterruptedException {
        System.out.print("Recherche de pokemon sauvage.\n");
        Thread.sleep(1000);
        System.out.print("\u001B[A");
        System.out.print("Recherche de pokemon sauvage..\n");
        Thread.sleep(1000);
        System.out.print("\u001B[A");
        System.out.print("Recherche de pokemon sauvage...\n");
        Thread.sleep(1000);
        System.out.print("\u001B[A");
        Pokemon pokeAdv = new Pokemon(54);
        enCombat = true;
        combat(pokeAdv);
    }

    /*
     * Fonction principale pour jouer en solo
     * le dresseur est(normalement) sauvegarder
     * après chaque tour dans l'interface.
     */
    public void interfaceDresseur() throws NotATypeException, IOException, InterruptedException {
        Save save = new Save(pseudo);
        Scanner scan = new Scanner(System.in);
        int intReceived;
        aa: while (true) {
            System.out.println(
                    " 1. Aller dans les herbes hautes\n 2. Aller au magasin\n 3. Aller au centre pokemon \n"
                    +" 4. Voir mes pokemons \n 5. Donner un superbonbon \n ...n'importe quoi d'autre pour partir");
            intReceived = scan.nextInt();
            switch (intReceived) {
                case 1:
                    System.out.println(
                            "Où vous aller vous?\n 0. Le jardin \n 1. La prairie d'à côté \n 2. Les bois sombres \n 3. La Route Victoire");
                    int difficulte = scan.nextInt();
                    if (difficulte == 1) {
                        hautesHerbesFacile();
                    } else if (difficulte == 2) {
                        hautesHerbesMoyen();
                    } else if (difficulte == 3) {
                        hautesHerbesDifficile();
                    } else if (difficulte == 0){
                        hautesHerbesTresFacile();
                    }else{
                        System.out.println("En fait non.");
                        continue;
                    }
                    break;
                case 2:
                    magasin();
                    break;
                case 3:
                    centrePokemon(equipe);
                    break;
                case 4:
                    for (Pokemon mesPoke : equipe) {
                        System.out.println(mesPoke);
                    }
                    break;
                case 5:
                    donnerSuperbonbon();
                    break;
                default:
                    System.out.println("Je rentre");
                    break aa;
            }
            save.transToFolder(this);
        }
        scan.close();
    }

    /*
     * Place en premier dans l'équipe le pokemon qui sera rentré
     * dans l'input pour le début d'un combat
     */
    public Pokemon choisirPremierPokemon() {
        Scanner scan = new Scanner(System.in);
        if (equipe.size() > 1) {
            System.out.println("Qui commence le combat?\n" + equipe);
            int lePokemon = scan.nextInt();
            Collections.swap(equipe, 0, lePokemon);
        }
        System.out.println(equipe.get(0).getNom() + ", go!");

        return equipe.get(0);
    }

    /*
     * Remplace le pokemon en tête(généralement le combattant)
     * par le pokemon rentré dans l'input
     */
    public Pokemon changerPokemon() {
        Scanner scan = new Scanner(System.in);
        if (equipe.get(0).getKo()) {
            System.out.println("Non " + equipe.get(0).getNom() + "! Reviens vite!");
        } else {
            System.out.println("Reviens " + equipe.get(0).getNom() + " !");
        }
        if (equipe.size() == pokeKo.size()) {
            System.out.println("Vite! Je dois courir au centre!");
            return null;
        }
        System.out.println("Qui rentrer?");
        int lePokemon = scan.nextInt();
        while (equipe.get(lePokemon).getKo()) {
            System.out.println(equipe.get(lePokemon).getNom() + " est KO");
            lePokemon = scan.nextInt();
        }
        Collections.swap(equipe, 0, lePokemon);
        System.out.println(equipe.get(0).getNom() + ", go!");
        return equipe.get(0);

    }

    /* Retoune l'attaque du pokemon rentré dans l'input */
    public Attaque choisirAttaquePokemon(Pokemon lePokemon) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Quelle attaque utilisée? 0: " + lePokemon.getLesAttaques().get(0) +
                " 1: " + lePokemon.getLesAttaques().get(1) +
                " 2: " + lePokemon.getLesAttaques().get(2) +
                " 3: " + lePokemon.getLesAttaques().get(3));
        int lAttaque = scan.nextInt();
        while (lePokemon.getLesAttaques().get(lAttaque) == null) {
            lAttaque = scan.nextInt();
        }
        return lePokemon.getLesAttaques().get(lAttaque);
    }

    /*
     * Permet d'attaquer un autre pokémon dans un combat en ligne, calculant tout
     * les dégâts infligés(Selon le calcul officiel).
     */
    public void attaquer(Pokemon monPoke, Attaque atkChoisi, Pokemon pokAdv) throws NotATypeException {
        int degat = 0;
        if (atkChoisi.getGenre().equals("Physique")) {
            degat = (int) (((((monPoke.getNiveau() * 0.4 + 2) * monPoke.getAtk() * atkChoisi.getPuissance())
                    / pokAdv.getDef()
                    / 50) + 2) * atkChoisi.calculEfficacite(pokAdv)
                    * atkChoisi.calculResistance(pokAdv)
                    * atkChoisi.isNeutre(pokAdv));
        } else {
            degat = (int) (((((monPoke.getNiveau() * 0.4 + 2) * monPoke.getAtkSpe() * atkChoisi.getPuissance())
                    / pokAdv.getDefSpe() / 50) + 2) * atkChoisi.calculEfficacite(pokAdv)
                    * atkChoisi.calculResistance(pokAdv) * atkChoisi.isNeutre(pokAdv));
        }
        System.out.println(monPoke.getNom() + " utilise " + atkChoisi + "!");
        System.out.println(pokAdv.getNom() + " a subit " + degat + " degats");
        pokAdv.subirDegat(degat);
        if (pokAdv.getPv() <= 0) {
            monPoke.setNiveauIncr();
            pokAdv.setKo(true);
        }
    }

    /* Soigne tous les pokemons dans la liste */
    public void centrePokemon(List<Pokemon> equipe) throws InterruptedException {
        System.out.println("Confiez moi vos pokemons, ils seront rétablis en un rien de temps!");
        for (Pokemon pokemon : equipe) {
            pokemon.soigner();
            pokeKo.remove(pokemon);
        }

        Thread.sleep(4000);
        System.out.println("Voilà vos pokemons! A bientôt!");
    }

    /* Tente la capture du pokemon sauvage dans les hautes herbes */
    public void capturer(Pokemon pokeAdv) {
        if(pokeball > 0){
            Random r = new Random();
        double a = ((1 - (2 / 3)
                * (pokeAdv.getPv() / (2 * pokeAdv.getPvBase() * pokeAdv.getNiveau() / 100 + 10 + pokeAdv.getNiveau())))
                * pokeAdv.getTauxCapture());
        if (a >= 255) {
            System.out.println(pokeAdv.getNom() + "capturé!");
            equipe.add(pokeAdv);
            enCombat = false;

        } else {
            double b = (Math.pow(2, 16) - 1) * Math.sqrt(a / (Math.pow(2, 8) - 1)) * 2 * 100;
            double p = r.nextDouble(101);
            if (p < b) {
                System.out.println(pokeAdv.getNom() + "capturé!");
                equipe.add(pokeAdv);
                enCombat = false;
            } else {
                System.out.println(pokeAdv + " s'est échappé!");
            }
        }
        }else{
            System.out.println("Je n'ai plus de pokeball");
        }
        
    }

    /*
     * Permet d'acheter des objets(Pokeballs et superbonbons pour l'instant)
     * en échange de pokeDollars
     */
    public void magasin() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Bienvenu!");
        int intReceived = 0;
        int nombre = 0;
        while (true) {
            System.out.println(
                    " Les articles:\n 1.pokeball 100P \n 2.superbonbon 500P\n ...n'importe quoi d'autre pour partir");
            intReceived = scan.nextInt();
            if (intReceived == 1) {
                System.out.println("Combien?");
                nombre = scan.nextInt();
                if (pokeDollars - nombre * 100 >= 0) {
                    for (int i = 0; i < nombre; i++) {
                        pokeDollars -= 100;
                        pokeball += 1;
                    }
                    System.out.println("Merci pour votre achat!");
                } else {
                    System.out.println("Trop cher!");
                }
            } else if (intReceived == 2) {
                if (pokeDollars - nombre * 500 > 0) {
                    for (int i = 0; i < nombre; i++) {
                        pokeDollars -= 500;
                        superbonbon += 1;
                    }
                    System.out.println("Merci pour votre achat!");
                } else {
                    System.out.println("Trop cher!");
                }
            } else {
                System.out.println("Au plaisir de vous revoir!");
                break;
            }
            System.out.println("Besoin d'autre chose?");
        }

    }

    public void donnerSuperbonbon() throws NotATypeException {
        Scanner scan = new Scanner(System.in);
        if (superbonbon > 0) {

            System.out.println("A qui donner un superbonbon?");
            for (int i = 0; i < equipe.size(); i++) {
                System.out.println(i + "" + " " + equipe.get(i));
            }
            int intReceived = scan.nextInt();
            while (0 > intReceived || intReceived > equipe.size()) {
                System.out.println("Mauvais emplacement");
                intReceived = scan.nextInt();
            }

            equipe.get(intReceived).setNiveauIncr();
        } else {
            System.out.println("Vous n'avez plus de supperbonbon");
        }
    }
}

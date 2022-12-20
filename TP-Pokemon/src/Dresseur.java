import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.Serializable;
import java.net.*;

/*Classe qui va servir de Client pour la communication
* avec le serveur(Arène). Il attendra que d'autre Drasseur 
* se connecte à l'Arène puis pourra utiliser une méthode
* combatPokemon(DresseurAdverse) pour demander un combat
*/
public class Dresseur implements Serializable
{
    private String pseudo;
    private List<Pokemon> equipe = new ArrayList<>();
    private String socket;
    private int ticket;
    private boolean enCombat;
    private boolean enQueue;
    private List<Pokemon> pokeKoD1 = new ArrayList<>();
    private List<Pokemon> pokeKoD2 = new ArrayList<>();
    

    public Dresseur(String nPseudo, String nSocket) throws NotATypeException{
        pseudo = nPseudo;
        ticket = 0;
        enCombat = false;
        enQueue = false;
        Pokemon pokemon = new Pokemon();
        equipe.add(pokemon);
        socket = nSocket;
    }

    //Getter
    public String getSocket() {
        return socket;
    }

    public boolean getEnCombat(){
        return enCombat;
    }


    public int getTicket() {
        return ticket;
    }

    public String getPseudo() {
        return pseudo;
    }

    public boolean getEnQueue(){
        return enQueue;
    }
    
    public List<Pokemon> getEquipe() {
            return equipe;
    }

    
    //Setter
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

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public void lootboxing() throws NotATypeException{
        if (ticket > 0){
            if(this.equipe.size() < 7){
                Pokemon pokemon = new Pokemon();
                this.equipe.add(pokemon); 
            }
        }   
    }

    public Pokemon choisirPremierPokemon(){
        Scanner ourNewscanner = new Scanner(System.in);
        if (this.equipe.size() > 1){
            System.out.println("Qui commence le combat?\n" + this.equipe);
            int lePokemon = ourNewscanner.nextInt();
            Collections.swap(equipe, 0, lePokemon);
        }
        ourNewscanner.close();
        System.out.println(this.equipe.get(0).getNom() + ", go!");
        return this.equipe.get(0);
    }

    public Pokemon changerPokemon(){
        Scanner ourNewscanner = new Scanner(System.in);
        System.out.println("Revient " + this.equipe.get(0).getNom() + " !");
        int lePokemon = ourNewscanner.nextInt();
        Collections.swap(equipe, 0, lePokemon);
        System.out.println(this.equipe.get(0).getNom() + ", go!");
        ourNewscanner.close();
        return this.equipe.get(0);
        
    }

    public Attaque choisirAttaquePokemon(Pokemon lePokemon){
        Scanner ourNewscanner = new Scanner(System.in);
        System.out.println("Quelle attaque utilisée? 1: " + lePokemon.getLesAttaques().get(0) +
                                                    "2: " + lePokemon.getLesAttaques().get(1) +
                                                    "3: " + lePokemon.getLesAttaques().get(2) + 
                                                    "4: " + lePokemon.getLesAttaques().get(3));
        int lAttaque = ourNewscanner.nextInt();  
        ourNewscanner.close();      
        return lePokemon.getLesAttaques().get(lAttaque);
    }

    @Override
    public String toString() {
        return "Socket: " + this.socket + "; Pseudo: " + this.pseudo + "; equipe: " + this.equipe;
    }
}
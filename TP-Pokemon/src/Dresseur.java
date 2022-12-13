import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.net.*;

/*Classe qui va servir de Client pour la communication
* avec le serveur(Arène). Il attendra que d'autre Drasseur 
* se connecte à l'Arène puis pourra utiliser une méthode
* combatPokemon(DresseurAdverse) pour demander un combat
*/
public class Dresseur
{
    private String pseudo;
    private List<Pokemon> equipe = new ArrayList<>();
    private long id;
    private int ticket;
    private boolean enCombat;
    private Socket mySocket;
    

    public Dresseur(String nPseudo, Socket nMySocket, long nID) throws NotATypeException{
        pseudo = nPseudo;
        ticket = 0;
        mySocket = nMySocket;
        enCombat = false;
        Pokemon pokemon = new Pokemon();
        equipe.add(pokemon);
        id = nID;
    }

    //Getter
    public long getId() {
        return id;
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

    public List<Pokemon> getEquipe() {
            return equipe;
    }

    public Socket getMySocket() {
        return mySocket;
    }

    
    //Setter
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setEnCombat(boolean enCombat) {
        this.enCombat = enCombat;
    }

    public void setEquipe(List<Pokemon> equipe) {
        this.equipe = equipe;
    }

    public void setId(int id) {
        this.id = id;
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
        return "ID: " + this.id + "; Pseudo: " + this.pseudo + "; equipe: " + this.equipe;
    }
}


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
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
    private int id;
    private int ticket;

    public Dresseur(String nPseudo, int nId) throws NotATypeException{
        pseudo = nPseudo;
        ticket = 0;
        id = nId;
        Pokemon pokemon = new Pokemon();
        equipe.add(pokemon);
    }

    public static void main(String[] args) throws IOException
    {
        try
        {
            Scanner ourNewscanner = new Scanner(System.in);
            InetAddress inetadress = InetAddress.getByName("localhost");
            // on établie la connexion
            Socket ournewsocket = new Socket(inetadress, 18000);           
            DataInputStream ournewDataInputstream = new DataInputStream(ournewsocket.getInputStream());
            DataOutputStream ournewDataOutputstream = new DataOutputStream(ournewsocket.getOutputStream());
            // Dans la boucle qui suit, le client et l'handler echange des données
            System.out.println(ournewDataInputstream.readUTF());
            String pseudoToSend = ourNewscanner.nextLine();
            ournewDataOutputstream.writeUTF(pseudoToSend);

            while (true)
            {
                System.out.println(ournewDataInputstream.readUTF());
                int tosend = ourNewscanner.nextInt();
                ournewDataOutputstream.writeInt(tosend);
                
                if(tosend == 1)
                {
                    System.out.println(ournewDataInputstream.readUTF());
                    int dresseurAdv = ourNewscanner.nextInt();
                    ournewDataOutputstream.writeInt(dresseurAdv);
                }
                // Sortir de la boucle while doit être quand un client rentre Exit.
                if(tosend == 3)
                {
                    System.out.println("Connection closing... : " + ournewsocket);
                    ournewsocket.close();
                    System.out.println("Closed");
                    break;
                }
                
                // affiche la date et l'heure à la demande du client
                String newresuiltReceivedString = ournewDataInputstream.readUTF();
                System.out.println(newresuiltReceivedString);
            }
            
            ourNewscanner.close();
            ournewDataInputstream.close();
            ournewDataOutputstream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //Getter
    public int getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public List<Pokemon> getEquipe() {
            return equipe;
    }
    
    //Setter
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
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

    public void duel(int dresseurAdv){

    }

    @Override
    public String toString() {
        return "ID: " + this.id + "; Pseudo: " + this.pseudo + "; equipe: " + this.equipe;
    }
}
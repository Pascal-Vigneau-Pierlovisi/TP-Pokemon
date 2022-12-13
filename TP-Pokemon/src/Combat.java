import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Combat implements Runnable{
    private Dresseur dresseur1;
    private Dresseur dresseur2;
    private List<Pokemon> pokeKoD1 = new ArrayList<>();
    private List<Pokemon> pokeKoD2 = new ArrayList<>();
    private String vainqueur;
    final DataInputStream ournewDataInputstream;
	final DataOutputStream ournewDataOutputstream;
	final DatagramSocket mynewSocket;
    private Thread combatThread;
    

    public Combat(Dresseur nD1, Dresseur nD2, DatagramSocket combatSocket, DataInputStream ournewDataInputstream, DataOutputStream ournewDataOutputstream){
        dresseur1 = nD1;
        dresseur2 = nD2;
        this.mynewSocket = combatSocket;
		this.ournewDataInputstream = ournewDataInputstream;
		this.ournewDataOutputstream = ournewDataOutputstream;
        combatThread = new Thread(this);
        combatThread.start();
    }

    @Override
    public void run(){

        Socket socketD1 = dresseur1.getMySocket();
        Socket socketD2 = dresseur2.getMySocket(); 
        Scanner ourNewscanner = new Scanner(System.in);
        Pokemon combattant = dresseur1.choisirPremierPokemon();
        String stringToReturn;
        int intReceived;
        while(vainqueur == null){
            boolean AttOuChang = false;
            while (AttOuChang == false){
                try {
                    stringToReturn = "1. Attaquer ou 2. Changer de Pokemon?";
                    intReceived = ournewDataInputstream.readInt();
                    if (intReceived == 1){
                        boolean choix = false;
                        while(choix == false){
                            dresseur1.choisirAttaquePokemon(combattant);
                            choix = true;
                        }
                    } else {
                        dresseur1.changerPokemon();
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}

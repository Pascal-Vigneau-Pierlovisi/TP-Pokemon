import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Combat extends Thread{
    private Dresseur dresseur1;
    private Dresseur dresseur2;
    private List<Pokemon> pokeKoD1 = new ArrayList<>();
    private List<Pokemon> pokeKoD2 = new ArrayList<>();
    private String vainqueur;
    final DataInputStream ournewDataInputstream;
	final DataOutputStream ournewDataOutputstream;
	final Socket mynewSocket;
    

    public Combat(Dresseur nD1, Dresseur nD2, Socket mynewSocket, DataInputStream ournewDataInputstream, DataOutputStream ournewDataOutputstream){
        dresseur1 = nD1;
        dresseur2 = nD2;
        this.mynewSocket = mynewSocket;
		this.ournewDataInputstream = ournewDataInputstream;
		this.ournewDataOutputstream = ournewDataOutputstream;
    }

    @Override
    public void run(){
        Scanner ourNewscanner = new Scanner(System.in);
        Pokemon combattant1 = dresseur1.choisirPremierPokemon();
        Pokemon combattant2 = dresseur2.choisirPremierPokemon();
        while(vainqueur == null){
            
        }
    }
}

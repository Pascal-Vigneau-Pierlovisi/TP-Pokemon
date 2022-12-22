import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Combat extends ServerSocket implements Runnable {
    private Socket combat;
    private DresseurHandler dresseur1;
    private DresseurHandler dresseur2;
    private String vainqueur;
    private BufferedReader in;
    private PrintWriter out;
    private Thread combatThread;
    private boolean dresseursPret;

    public Combat(DresseurHandler nD1, DresseurHandler nD2, Socket nCombat) throws IOException {
        dresseur1 = nD1;
        dresseur2 = nD2;
        in = new BufferedReader(new InputStreamReader(combat.getInputStream()));
        out = new PrintWriter(combat.getOutputStream(), true);
        dresseursPret = false;
    }

    @Override
    public void run() {
        boolean AttOuChang = false;
        String stringToReturn;
        int intReceived;
        Pokemon combattant1 = dresseur1.getD1().choisirPremierPokemon();
        Pokemon combattant2 = dresseur2.getD1().choisirPremierPokemon();

        while (AttOuChang == false) {
            try {
                interfaceCombat();
                intReceived = in.read(); 
                if (intReceived == 1) {
                    boolean choix = false;
                    while (choix == false) {
                        dresseur1.getD1().choisirAttaquePokemon(combattant1);
                        dresseursPret = true;
                    }
                } else {
                    dresseur1.getD1().changerPokemon();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    // Getters
    public DresseurHandler getDresseur1() {
        return dresseur1;
    }

    public DresseurHandler getDresseur2() {
        return dresseur2;
    }

    public String getVainqueur() {
        return vainqueur;
    }


    public Thread getCombatThread() {
        return combatThread;
    }

    public boolean isDresseursPret() {
        return dresseursPret;
    }

    // Setters
    public void setDresseur1(DresseurHandler dresseur1) {
        this.dresseur1 = dresseur1;
    }

    public void setDresseur2(DresseurHandler dresseur2) {
        this.dresseur2 = dresseur2;
    }

    public void setVainqueur(String vainqueur) {
        this.vainqueur = vainqueur;
    }

    public void setCombatThread(Thread combatThread) {
        this.combatThread = combatThread;
    }

    public void setDresseursPret(boolean dresseursPret) {
        this.dresseursPret = dresseursPret;
    }

}

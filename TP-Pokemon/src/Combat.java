import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.nio.ByteBuffer;

public class Combat extends ServerSocket implements Runnable {
    private Dresseur dresseur1;
    private Dresseur dresseur2;
    private String vainqueur;
    final ByteBuffer buffer;
    private Thread combatThread;
    private boolean dresseursPret;

    public Combat(Dresseur nD1, Dresseur nD2, ByteBuffer nBuffer) throws IOException {
        dresseur1 = nD1;
        dresseur2 = nD2;
        buffer = nBuffer;
        combatThread = new Thread(this);
        combatThread.start();
        dresseursPret = false;
    }

    @Override
    public void run() {
        boolean AttOuChang = false;
        String stringToReturn;
        int intReceived;
        Pokemon combattant1 = dresseur1.choisirPremierPokemon();
        Pokemon combattant2 = dresseur2.choisirPremierPokemon();

        while (AttOuChang == false) {
            try {
                stringToReturn = "1. Attaquer ou 2. Changer de Pokemon?";
                intReceived = ournewDataInputstream.readInt();
                if (intReceived == 1) {
                    boolean choix = false;
                    while (choix == false) {
                        dresseur1.choisirAttaquePokemon(combattant1);
                        dresseursPret = true;
                    }
                } else {
                    dresseur1.changerPokemon();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    // Getters
    public Dresseur getDresseur1() {
        return dresseur1;
    }

    public Dresseur getDresseur2() {
        return dresseur2;
    }

    public String getVainqueur() {
        return vainqueur;
    }

    public DataInputStream getOurnewDataInputstream() {
        return ournewDataInputstream;
    }

    public DataOutputStream getOurnewDataOutputstream() {
        return ournewDataOutputstream;
    }

    public Thread getCombatThread() {
        return combatThread;
    }

    public boolean isDresseursPret() {
        return dresseursPret;
    }

    // Setters
    public void setDresseur1(Dresseur dresseur1) {
        this.dresseur1 = dresseur1;
    }

    public void setDresseur2(Dresseur dresseur2) {
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

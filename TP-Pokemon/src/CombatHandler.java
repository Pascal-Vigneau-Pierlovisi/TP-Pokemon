import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CombatHandler implements Runnable {

    private Socket combat;
    private BufferedReader in;
    private PrintWriter out;


    public CombatHandler(Socket s) throws IOException{
        combat = s;
        in = new BufferedReader(new InputStreamReader(combat.getInputStream()));
        out = new PrintWriter(combat.getOutputStream(), true);
    }

    @Override
    public void run() {
        
        
    }
    
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeoutException;

public class DresseurConnect {

    /*Programme client, permet de se connecter au serveur pour un combat en ligne.
     * ATTENTION! Un dresseur doit obligatoirement existé avec de commencer tout
     * combat en ligne dans les sauvegardes.
     */
    public static void main(String[] args)
            throws IOException, TimeoutException, ClassNotFoundException, InterruptedException {
        Socket socket = new Socket("localhost", 18000);

        AreneConnection serverConn = new AreneConnection(socket);
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        new Thread(serverConn).start();
        

        String command = null;
        String pseudo = keyboard.readLine();
        out.println(pseudo);
        Save save = new Save(pseudo);
        Dresseur myDresseur = save.readToFolder(pseudo);
        myDresseur.setEnCombat(true); 
        while (myDresseur.getEnCombat()) {
            command = keyboard.readLine();
            out.println(command);
        }
        socket.close();
        System.exit(0);
    }
}

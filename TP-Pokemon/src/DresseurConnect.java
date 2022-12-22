import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeoutException;

public class DresseurConnect {
    public static void main(String[] args)
            throws IOException, TimeoutException, ClassNotFoundException, InterruptedException {
        Socket socket = new Socket("localhost", 18000);

        AreneConnection serverConn = new AreneConnection(socket);
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        new Thread(serverConn).start();
        

        String response = " ";
        boolean combat = false;
        String pseudo = null;
        while (true) {
            if (pseudo == null) {
                pseudo = keyboard.readLine();
                out.println(pseudo);
            }
            Thread.sleep(2000);
            Save save = new Save(pseudo);
            Dresseur myDresseur = save.readToFolder(pseudo);
            System.out.println("> ");
            String command = keyboard.readLine();
            if (command.equals("partir"))
                break;
            if (command.contains("COMBAT")) {
                out.println(command);
                Thread.sleep(2000);
                response = in.readLine();
                if (response.contains("accepte")) {
                    combat = true;
                    while (combat) {
                        int choice = keyboard.read();
                        out.println(choice);
                    }
                }
            }
            if (command.contains("Oui")) {
                out.println(command);
                Thread.sleep(2000);
                combat = true;
                while (combat) {
                    String choice = keyboard.readLine();
                    out.println(choice);
                }

            }

            String concat = pseudo + ": " + command;
            out.println(pseudo + ": " + concat);

        }
        socket.close();
        System.exit(0);
    }
}

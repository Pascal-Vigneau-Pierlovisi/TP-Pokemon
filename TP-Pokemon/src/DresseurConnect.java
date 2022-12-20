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
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class DresseurConnect {
    public static void main(String[] args) throws IOException, TimeoutException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 18000);

        AreneConnection serverConn = new AreneConnection(socket);
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Save save = new Save();

        new Thread(serverConn).start();


        String pseudo = null;
        while (true) {
            if (pseudo == null){
                pseudo = keyboard.readLine();
                out.println(pseudo);
            }
            Dresseur myDresseur = save.readToFolder();
            System.out.println("> ");
            String command = keyboard.readLine();   
            if (command.equals("partir")) break;
            String concat = pseudo + ": " + command;
            out.println(pseudo + ": " + concat);
            

        }
        socket.close();
        System.exit(0);
    }
}

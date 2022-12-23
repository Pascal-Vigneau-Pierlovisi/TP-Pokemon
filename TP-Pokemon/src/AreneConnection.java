import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AreneConnection implements Runnable {

    private Socket server;
    private BufferedReader in;
    private PrintWriter out;

    /*Fonction permettant la diffusion des messages entre les 2 dresseurs */

    public AreneConnection(Socket s) throws IOException {
        server = s;
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        out = new PrintWriter(server.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String serverResponse = in.readLine();
                if(serverResponse == null) break;
                System.out.println(serverResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}

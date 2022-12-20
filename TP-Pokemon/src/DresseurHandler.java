import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.ArrayList;
import java.util.concurrent.Future;

public class DresseurHandler implements Runnable {

	private Socket dresseur;
	private BufferedReader in;
	private PrintWriter out;
	private ArrayList<DresseurHandler> dresseursHandler;
	private ArrayList<Dresseur> dresseurs = new ArrayList<>();

	private Save save = new Save();

	// Constructor
	public DresseurHandler(Socket dresseurSocket, ArrayList<DresseurHandler> nDresseurs) throws IOException {
		this.dresseur = dresseurSocket;
		in = new BufferedReader(new InputStreamReader(dresseur.getInputStream()));
		out = new PrintWriter(dresseur.getOutputStream(), true);
		dresseursHandler = nDresseurs;
	}

	@Override
	public void run() {
		String pseudoRequest = "Bienvenu dans l'Arene! Quel est votre pseudo?";
		out.println(pseudoRequest);
		try {
			String dresseurPseudo = in.readLine();
			Dresseur d1 = null;
			if (save.readToFolder().getPseudo().equals(dresseurPseudo)){
				d1 = save.readToFolder();
				dresseurs.add(d1);
			} else {
				d1 = new Dresseur(dresseurPseudo, dresseur.toString());
				dresseurs.add(d1);
			}
			save.transToFolder(d1);
			while(true){
				String request = in.readLine();
				if (request.contains("partir")) break;
				else if (request.contains("COMBAT")){
					out.println("En attente de joueur...");
				}
				else {
					int firstSpace = request.indexOf(" ");
					if(firstSpace != -1) {
						outToAll(request.substring(firstSpace+1));
					}
				}
			}
		} catch (IOException | NotATypeException | ClassNotFoundException e){
			System.err.println("IO exception in client handler");
			System.err.println(e.getStackTrace());
		} finally {
			out.close();
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void outToAll(String msg) {
		for (DresseurHandler dresseurClient : dresseursHandler){
			dresseurClient.out.println(msg);
		}
	}

	// Getters
	public Socket getDresseur() {
		return dresseur;
	}

	public BufferedReader getIn() {
		return in;
	}

	public PrintWriter getOut() {
		return out;
	}

	//Setters
	public void setDresseur(Socket dresseur) {
		this.dresseur = dresseur;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	

	

}

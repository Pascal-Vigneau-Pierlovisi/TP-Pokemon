
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Arene {
	private static ArrayList<DresseurHandler> dresseurs = new ArrayList<>();
	private static ExecutorService pool = Executors.newFixedThreadPool(2);

	/*Fonction de lancement du combat en ligne, démarrage nécessaire
	 * avant tout démarrage de client.
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(18000);

		while (true) {
			System.out.println("[ARENE] En attente de dresseur...");
			Socket dresseur = listener.accept();
			System.out.println("[ARENE] Un dresseur est rentré!");
			DresseurHandler dresseurThread = new DresseurHandler(dresseur, dresseurs);
			dresseurThread.outToAll("[ARENE] Un dresseur est rentré!");

			pool.execute(dresseurThread);
		}

	}

	public static ArrayList<DresseurHandler> getDresseurs() {
		return dresseurs;
	}
}
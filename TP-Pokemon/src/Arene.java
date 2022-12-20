
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Arene {
	private static ArrayList<DresseurHandler> dresseurs = new ArrayList<>();
	private static ExecutorService pool = Executors.newFixedThreadPool(4);

	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(18000);

		while (true) {
			System.out.println("[ARENE] En attente de dresseur...");
			Socket dresseur = listener.accept();
			System.out.println("[ARENE] Un dresseur est rentré!");
			DresseurHandler dresseurThread = new DresseurHandler(dresseur, dresseurs);
			dresseurThread.outToAll("[ARENE] Un dresseur est rentré!");
			dresseurs.add(dresseurThread);

			pool.execute(dresseurThread);
		}

	}
}
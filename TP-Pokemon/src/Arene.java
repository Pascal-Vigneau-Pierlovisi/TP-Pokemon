

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/* Classe qui servira de serveur, elle attendra que des joueurs se connecte
* pour acceuillir des combats.
*/
public class Arene
{

	private static Map<Long, Dresseur> dresseurs = new HashMap<Long, Dresseur>();
	public static void main(String[] args) throws IOException
	{
		ServerSocket myserverSocket = new ServerSocket(18000);
		// getting client request
		while (true)
        // running infinite loop 
		{
			Socket mynewSocket = null;
			
			try
			{
				// mynewSocket object to receive incoming client requests
				mynewSocket = myserverSocket.accept();
				
				System.out.println("A new connection identified : " + mynewSocket);
                // obtaining input and out streams
				DataInputStream ournewDataInputstream = new DataInputStream(mynewSocket.getInputStream());
				DataOutputStream ournewDataOutputstream = new DataOutputStream(mynewSocket.getOutputStream());
				
				System.out.println("Thread assigned");

				Thread myThread = new DresseurHandler(mynewSocket, ournewDataInputstream, ournewDataOutputstream);
				// starting
				myThread.start();
				String pseudo = ournewDataInputstream.readUTF();
				dresseurs.put(myThread.threadId(), new Dresseur(pseudo, myThread.threadId()));
				if (ournewDataInputstream.readInt() == 1){
					long idAdv = ournewDataInputstream.readLong();
					if (dresseurs.containsKey(idAdv)){
						Combat combat = new Combat(dresseurs.get(myThread.threadId()), dresseurs.get(idAdv), mynewSocket, ournewDataInputstream, ournewDataOutputstream);
						combat.start();
					}
				}

				
			}
			catch (Exception e){
				mynewSocket.close();
				e.printStackTrace();
			}
		}
	}
	public static Map<Long, Dresseur> getDresseurs() {
		return dresseurs;
	}
	public static void setDresseurs(Map<Long, Dresseur> dresseurs) {
		Arene.dresseurs = dresseurs;
	}

	
}
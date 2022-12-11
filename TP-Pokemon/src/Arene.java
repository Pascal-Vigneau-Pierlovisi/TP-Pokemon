

import java.io.*;
import java.net.*;


/* Classe qui servira de serveur, elle attendra que des joueurs se connecte
* pour acceuillir des combats.
*/
public class Arene
{
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
				
			}
			catch (Exception e){
				mynewSocket.close();
				e.printStackTrace();
			}
		}
	}
}
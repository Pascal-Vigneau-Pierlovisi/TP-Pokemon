import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class DresseurHandler extends Thread{

	final DataInputStream ournewDataInputstream;
	final DataOutputStream ournewDataOutputstream;
	final Socket mynewSocket;
	private String pseudoRecu;
    
	

	// Constructor
	public DresseurHandler(Socket mynewSocket, DataInputStream ournewDataInputstream, DataOutputStream ournewDataOutputstream)
	{
		this.mynewSocket = mynewSocket;
		this.ournewDataInputstream = ournewDataInputstream;
		this.ournewDataOutputstream = ournewDataOutputstream;
	}

	@Override
	public void run()
	{
		System.out.println(mynewSocket);
		int receivedInt;
		String stringToReturn;
        try {
            ournewDataOutputstream.writeUTF("Votre pseudo?");
            pseudoRecu = ournewDataInputstream.readUTF();
			
        } catch (IOException /*| NotATypeException */e1 ) {
            e1.printStackTrace();
        }
		while (true)
		{
			try {
				ournewDataOutputstream.writeUTF("Choose 1: Duel; 2: Liste de dresseur; 3. Lootboxing\n"+
							"Ou 3. Partir");	
				// reponse d'un client
				receivedInt = ournewDataInputstream.readInt();

				if(receivedInt == 4)
				{
					System.out.println("Client " + this.mynewSocket + " sends exit...");
					System.out.println("Connection closing...");
                    //dresseurs.remove();
					this.mynewSocket.close();
					System.out.println("Closed");
					break;
				}

				switch (receivedInt) {
				
					case 1 :
						stringToReturn = "Quel dresseur veux tu affronter?";
                        ournewDataOutputstream.writeUTF(stringToReturn);
                        try {
							int idAdv = ournewDataInputstream.readInt();
                            //dresseurs.get(id).duel(idAdv);
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
						break;
						
					case 2 :
						stringToReturn = Arene.getDresseurs().toString();
                        ournewDataOutputstream.writeUTF(stringToReturn);
						break;
					case 3 :
                        break;
					default:
						ournewDataOutputstream.writeUTF("Invalid input");
						break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try
		{
			// on ferme les Streams
			this.ournewDataInputstream.close();
			this.ournewDataOutputstream.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public DataInputStream getOurnewDataInputstream() {
		return ournewDataInputstream;
	}

	public DataOutputStream getOurnewDataOutputstream() {
		return ournewDataOutputstream;
	}

	public Socket getMynewSocket() {
		return mynewSocket;
	}

	public String getPseudoRecu() {
		return pseudoRecu;
	}

	public void setPseudoRecu(String pseudoRecu) {
		this.pseudoRecu = pseudoRecu;
	}
    
}

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class DresseurHandler extends Thread{

	final DataInputStream ournewDataInputstream;
	final DataOutputStream ournewDataOutputstream;
	final Socket mynewSocket;
    private static HashSet<Dresseur> dresseurs = new HashSet<Dresseur>();
	

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
        String pseudoRecu;
		int receivedInt;
		String stringToReturn;
        try {
            ournewDataOutputstream.writeUTF("Votre pseudo?");
            pseudoRecu = ournewDataInputstream.readUTF();
            dresseurs.add(new Dresseur(pseudoRecu, dresseurs.size()));
        } catch (IOException | NotATypeException e1) {
            e1.printStackTrace();
        }
		while (true)
		{
			try {
				ournewDataOutputstream.writeUTF("Choose 1: Duel; 2: Liste de dresseur; 3. Lootboxing\n"+
							"Ou 3. Partir");	
				// reponse d'un client
				receivedInt = ournewDataInputstream.readInt();
				if(receivedInt == 3)
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
                            
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
						break;
						
					case 2 :
						stringToReturn = dresseurs.toString();
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
    
}

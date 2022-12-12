import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class DresseurHandler implements Runnable{

	final DataInputStream ournewDataInputstream;
	final DataOutputStream ournewDataOutputstream;
	final Socket mynewSocket;
	private String pseudoRecu;
	private Thread myThread;
    
	

	// Constructor
	public DresseurHandler(Socket mynewSocket, DataInputStream ournewDataInputstream, DataOutputStream ournewDataOutputstream)
	{
		this.mynewSocket = mynewSocket;
		this.ournewDataInputstream = ournewDataInputstream;
		this.ournewDataOutputstream = ournewDataOutputstream;
		myThread = new Thread(this);
		myThread.start();
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
			//if(pseudoRecu)
			Arene.setDresseurs(new Dresseur(pseudoRecu, mynewSocket, myThread.getId()));
			
        } catch (IOException | NotATypeException e1 ) {
            e1.printStackTrace();
        }
		while (true)
		{
			try {
				ournewDataOutputstream.writeUTF("Choose 1: Duel; 2: Liste de dresseur; 3. Lootboxing\n"+
							"Ou 4. Partir");	
				// reponse d'un client
				receivedInt = ournewDataInputstream.readInt();

				if(receivedInt == 4)
				{
					System.out.println("Client " + this.mynewSocket + " sends exit...");
					System.out.println("Connection closing...");
                    Arene.removeDresseur(myThread.getId());
					this.mynewSocket.close();
					System.out.println("Closed");
					break;
				}

				switch (receivedInt) {
				
					case 1 :
						stringToReturn = "Quel dresseur veux tu affronter(Rentrer l'ID)?";
                        ournewDataOutputstream.writeUTF(stringToReturn);
						long idD1 = myThread.getId();
						long idD2 = ournewDataInputstream.readLong();
						if (Arene.getDresseurs().containsKey(idD2)){
							Arene.getDresseurs().get(idD1).setEnCombat(true);
							Arene.getDresseurs().get(idD2).setEnCombat(true);
							if(Arene.getDresseurs().get(idD1).getEnCombat() == true){
								Combat combat = new Combat(Arene.getDresseurs().get(idD1), Arene.getDresseurs().get(idD2), mynewSocket, ournewDataInputstream, ournewDataOutputstream);
							}
						
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

	//Getters

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

	public Thread getMyThread() {
		return myThread;
	}

	//Stter
	public void setPseudoRecu(String pseudoRecu) {
		this.pseudoRecu = pseudoRecu;
	}
    
}

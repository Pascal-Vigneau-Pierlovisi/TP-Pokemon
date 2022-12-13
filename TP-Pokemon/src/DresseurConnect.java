import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class DresseurConnect {
    public static void main(String[] args) throws IOException
    {
        try
        {
            Scanner ourNewscanner = new Scanner(System.in);
            InetAddress inetadress = InetAddress.getByName("localhost");
            // on établie la connexion
            Socket ournewsocket = new Socket(inetadress, 18000);           
            DataInputStream ournewDataInputstream = new DataInputStream(ournewsocket.getInputStream());
            DataOutputStream ournewDataOutputstream = new DataOutputStream(ournewsocket.getOutputStream());
            // Dans la boucle qui suit, le client et l'handler echange des données
            System.out.println(ournewDataInputstream.readUTF());
            String pseudoToSend = ourNewscanner.nextLine();
            ournewDataOutputstream.writeUTF(pseudoToSend);
                
            Dresseur dresseurActuel = null;
            
            while(dresseurActuel==null)
            {
                for (Dresseur dresseur : Arene.getDresseurs().values()){
                    if (dresseur.getId() == ){
                        dresseurActuel = dresseur;
                    }
                }
            }
            System.out.println("hého");
            while (true)
            {
                System.out.println(ournewDataInputstream.readUTF());
                    int tosend = ourNewscanner.nextInt();
                    ournewDataOutputstream.writeInt(tosend);
                    
                
                if(tosend == 1)
                {
                    System.out.println(ournewDataInputstream.readUTF());
                    long dresseurAdv = ourNewscanner.nextLong();
                    ournewDataOutputstream.writeLong(dresseurAdv);
                }
                // Sortir de la boucle while doit être quand un client rentre Exit.
                if(tosend == 4)
                {
                    System.out.println("Connection closing... : " + ournewsocket);
                    ournewsocket.close();
                    System.out.println("Closed");
                    break;
                }
                    
                String newresuiltReceivedString = ournewDataInputstream.readUTF();
                System.out.println(newresuiltReceivedString);
                while(!Arene.getDresseurs().get(dresseurActuel.getId()).getEnCombat())
                {

                }
                while(Arene.getDresseurs().get(dresseurActuel.getId()).getEnCombat()){
                    ournewsocket = new Socket(inetadress, 19000);
                    ournewDataInputstream = new DataInputStream(ournewsocket.getInputStream());
                    ournewDataOutputstream = new DataOutputStream(ournewsocket.getOutputStream());
                }

            }     
                
        ourNewscanner.close();
        ournewDataInputstream.close();
        ournewDataOutputstream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;


public class Save implements Serializable{
    public Dresseur[] getDresseurs() {
        return dresseurs;
    }
    private Dresseur[] dresseurs;
    public Save(){}

    public void transToFolder(ArrayList<Dresseur> dresseurs) throws IOException {
        FileOutputStream fichier = new FileOutputStream("TP-Pokemon/csv/save.txt");
        ObjectOutputStream flux = new ObjectOutputStream(fichier);
        flux.writeObject(dresseurs);
    }

    public Dresseur readToFolder(String pseudo) throws IOException, ClassNotFoundException {
        FileInputStream fichier = new FileInputStream("TP-Pokemon/csv/save.txt");
        ObjectInput flux = new ObjectInputStream(fichier);
        Object listDresseur =  flux.readObject();
        Dresseur objet = null;
        for(Dresseur dresseur : (ArrayList<Dresseur>) listDresseur){
            if(dresseur.getPseudo().equals(pseudo)){
                objet = dresseur;
            }
        }
        System.out.println(objet);
        return objet;
    }
}
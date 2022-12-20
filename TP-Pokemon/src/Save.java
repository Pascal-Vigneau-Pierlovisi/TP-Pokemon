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

    public void transToFolder(Dresseur dresseur) throws IOException {
        FileOutputStream fichier = new FileOutputStream("./csv/" + dresseur.getPseudo() + ".txt");
        ObjectOutputStream flux = new ObjectOutputStream(fichier);
        flux.writeObject(dresseurs);
    }

    public Dresseur readToFolder(String pseudo) throws IOException, ClassNotFoundException {
        FileInputStream fichier = new FileInputStream("./csv/" + pseudo + ".txt");
        ObjectInput flux = new ObjectInputStream(fichier);
        Dresseur objet =  (Dresseur) flux.readObject();
        return objet;
    }
}
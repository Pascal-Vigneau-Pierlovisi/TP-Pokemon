import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;


public class Save{
    public Dresseur[] getDresseurs() {
        return dresseurs;
    }
    private Dresseur[] dresseurs;
    public Save(){}

    public void transToFolder(Dresseur dresseur) throws IOException {
        FileOutputStream fichier = new FileOutputStream("monfichier.txt");
        ObjectOutputStream flux = new ObjectOutputStream(fichier);
        flux.writeObject(dresseur);
    }

    public void readToFolder() throws IOException, ClassNotFoundException {
        FileInputStream fichier = new FileInputStream("monfichier.txt");
        ObjectInput flux = new ObjectInputStream(fichier);
        Object objet = (Dresseur) flux.readObject();
        System.out.println(objet.toString());
    }
}
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;


public class Save{
    private Dresseur[] dresseurs;
    public Save(){}

    public void transToFolder(Dresseur dresseur) throws IOException {
        FileOutputStream fichier = new FileOutputStream("save.txt");
     

    public void readToFolder() throws IOException, ClassNotFoundException {
        FileInputStream fichier = new FileInputStream("save.txt");
        ObjectInput flux = new ObjectInputStream(fichier);
        Object objet = (Dresseur) flux.readObject();
        System.out.println(objet.toString());
    }   ObjectOutputStream flux = new ObjectOutputStream(fichier);
        flux.writeObject(dresseur);
    }




}
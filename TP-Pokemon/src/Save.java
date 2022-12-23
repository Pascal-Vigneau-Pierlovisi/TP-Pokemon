import java.io.*;

/* Moyen de sauvegarde d'un Dresseur par sérialisation */
public class Save implements Serializable {

    private File save;

    public Save(String pseudo) throws IOException {
        save = new File("D:\\code\\TP-Pokemon\\TP-Pokemon\\csv\\" + pseudo + ".txt");
    }

    public File getSave() {
        return save;
    }

    public void transToFolder(Dresseur dresseur) throws IOException {
            try {
                boolean result = save.createNewFile(); // creates a new save
            } catch (IOException e) {
                e.printStackTrace(); // prints exception if any
            }
            FileOutputStream fichier = new FileOutputStream(save);
            ObjectOutputStream flux = new ObjectOutputStream(fichier);
            flux.writeObject(dresseur);
        }
        
    public Dresseur readToFolder(String pseudo) throws IOException, ClassNotFoundException {
        FileInputStream fichier = new FileInputStream(save);
        ObjectInput flux = new ObjectInputStream(fichier);
        Dresseur objet = (Dresseur) flux.readObject();
        return objet;
    }

    public boolean saveExist(String pseudo) {
        File f = new File("D:\\code\\TP-Pokemon\\TP-Pokemon\\csv\\" + pseudo + ".txt");
        if (f.exists() && !f.isDirectory())
            return true;
        return false;
    }
}
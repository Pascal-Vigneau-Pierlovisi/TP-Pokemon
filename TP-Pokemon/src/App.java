import java.io.File;
import java.net.Socket;

public class App {
    public static void main(String[] args) throws Exception {
        Dresseur dresseur = new Dresseur("TheTrlol", new Socket(), 1999);
        Save save = new Save();
        save.transToFolder(dresseur);
        save.readToFolder();
    }
}


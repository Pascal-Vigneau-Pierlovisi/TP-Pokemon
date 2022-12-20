import java.io.File;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Pokedex pokedex = new Pokedex(new File("E:/code/TP-Pokemon/TP-Pokemon/csv/kanto.xlsx"));
        Dresseur dresseur = new Dresseur("TheTrlol", "1999");
        Dresseur dresseur2 = new Dresseur("Vietco", "lalalal");
        ArrayList<Dresseur> listDresseur = new ArrayList<>();
        listDresseur.add(dresseur2);
        listDresseur.add(dresseur);
        Save save = new Save();
        save.transToFolder(listDresseur);
        save.readToFolder("Vietco");
        save.readToFolder("TheTrlol");

    }
}


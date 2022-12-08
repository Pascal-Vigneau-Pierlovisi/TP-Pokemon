import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        Pokedex pokedex = new Pokedex(new File("./TP-Pokemon/csv/kanto.xlsx"));
        System.out.println(pokedex.getPokedex());
    }
}


import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        Pokedex pokedex = new Pokedex(new File("./TP-Pokemon/csv/kanto.xlsx"));
        Types_Poke tabTypes = new Types_Poke();
        System.out.println(tabTypes.getEfficacite());
        System.out.println(pokedex.getPokedex());
        Pokemon p1 = new Pokemon(pokedex);
    }
}


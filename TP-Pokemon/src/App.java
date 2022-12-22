import java.io.File;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Save save = new Save("Vietco");
        Dresseur d1 = null;
        if(save.getSave().exists()){
            d1 = save.readToFolder("Vietco");
        }else{
            d1 = new Dresseur();
        save.transToFolder(d1);
        }
        d1.interfaceDresseur();
    }
}


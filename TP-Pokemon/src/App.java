import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    /*Application solo principale du projet, 
    aucun réel prérequis car le pseudo est demandé à son lancement
    et suffit amplement pour démarrer une partie solo.
    ATTENTION! Lorsque l'on veut reprendre une sauvegarde,
    il faut obligatoirement taper en console le pseudo
    lettre pour lettre. */
    public static void main(String[] args) throws Exception {
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Quel est ton nom?");
        String pseudo = scan.nextLine();
        Save save = new Save(pseudo);
        Dresseur d1 = null;
        if(save.getSave().exists()){
            d1 = save.readToFolder(pseudo);
        }else{
            d1 = new Dresseur(pseudo);
        save.transToFolder(d1);
        }
        d1.interfaceDresseur();
    }
}


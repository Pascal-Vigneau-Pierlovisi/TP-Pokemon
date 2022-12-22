import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.Future;

public class DresseurHandler implements Runnable {

	private Socket dresseur;
	private BufferedReader in;
	private PrintWriter out;
	private ArrayList<DresseurHandler> dresseursHandler;
	private ArrayList<Dresseur> dresseurs = new ArrayList<>();
	private Dresseur d1;
	private boolean combat;
	private Scanner scan = new Scanner(System.in);
	private boolean dresseurPret;

	private Save save;

	// Constructor
	public DresseurHandler(Socket dresseurSocket, ArrayList<DresseurHandler> nDresseurs) throws IOException {
		this.dresseur = dresseurSocket;
		in = new BufferedReader(new InputStreamReader(dresseur.getInputStream()));
		out = new PrintWriter(dresseur.getOutputStream(), true);
		dresseursHandler = nDresseurs;
		combat = false;
	}

	@Override
	public void run() {
		String pseudoRequest = "Bienvenu dans l'Arene! Quel est votre pseudo?";
		out.println(pseudoRequest);
		try {
			String dresseurPseudo = in.readLine();
			save = new Save(dresseurPseudo);
			if (save.saveExist(dresseurPseudo)){
				d1 = save.readToFolder(dresseurPseudo);
			} else {
				d1 = new Dresseur(dresseurPseudo, dresseur.toString());
			}
			save.transToFolder(d1);
			while(true){
				String request = in.readLine();
				if (request.contains("partir")) break;
				else if (request.contains("COMBAT")){
					DresseurHandler dresseurToDuel = null;
					for (DresseurHandler dresseurClient : dresseursHandler){
						if (request.contains(dresseurClient.d1.getPseudo())){
							dresseurClient.out.println(d1.getPseudo() + "demande un combat! Acceptez vous?");
							String clientResponse = dresseurClient.in.readLine();
							if(clientResponse.contains("Oui")){
		
								dresseurToDuel = dresseurClient;
								combat = true;
								Pokemon combattant = this.choisirPremierPokemon(d1);
								while(combat){
									interfaceCombat(combattant, dresseurToDuel);
									if(combattant.getKo()){
										this.changerPokemon(d1);
									}
									if(d1.getPokeKo().size() == 6){
										out.println("Vous avez perdu!" + dresseurToDuel.d1.getPseudo() + " a gagné!");
										combat = false;
									}
									if(dresseurToDuel.d1.getPokeKo().size() == 6){
										out.println("Vous avez gagné!" + dresseurToDuel.d1.getPseudo() + " a perdu!");
										combat = false;
									}
									dresseurPret = false;
								}
								
							}
						}
					}
				} else if (request.contains("Oui")) {
					DresseurHandler dresseurToDuel = null;
					for (DresseurHandler dresseurClient : dresseursHandler) {
						if (!dresseurClient.equals(this)) {
							dresseurToDuel = dresseurClient;
							dresseurToDuel.out.println(d1.getPseudo() + " accepte");
							combat = true;
							Pokemon combattant = this.choisirPremierPokemon(d1);
							while (combat) {
								interfaceCombat(combattant, dresseurToDuel);
								if (combattant.getKo()) {
									d1.getEquipe().add(combattant);
									this.changerPokemon(d1);
								}
								if (d1.getPokeKo().size() == d1.getEquipe().size()) {
									out.println("Vous avez perdu!" + dresseurToDuel.d1.getPseudo() + " a gagné!");
									combat = false;
								}
								if (dresseurToDuel.d1.getPokeKo().size() == 6) {
									out.println("Vous avez gagné!" + dresseurToDuel.d1.getPseudo() + " a perdu!");
									combat = false;
								}
								dresseurPret = false;
							}

						}
					}
				} else {
					int firstSpace = request.indexOf(" ");
					if(firstSpace != -1) {
						outToAll(request.substring(firstSpace+1));
					}
				}
			}
		} catch (IOException | NotATypeException | ClassNotFoundException e){
			System.err.println("IO exception in client handler");
			System.err.println(e.getLocalizedMessage());
		} finally {
			out.close();
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				e.getLocalizedMessage();
			}
		}
	}

	public void outToAll(String msg) {
		for (DresseurHandler dresseurClient : dresseursHandler){
			dresseurClient.out.println(msg);
		}
	}

	// Getters
	public Socket getDresseur() {
		return dresseur;
	}

	public BufferedReader getIn() {
		return in;
	}

	public PrintWriter getOut() {
		return out;
	}

	public ArrayList<DresseurHandler> getDresseursHandler() {
		return dresseursHandler;
	}

	public ArrayList<Dresseur> getDresseurs() {
		return dresseurs;
	}

	public Dresseur getD1() {
		return d1;
	}

	public Save getSave() {
		return save;
	}

	//Setters
	public void setDresseur(Socket dresseur) {
		this.dresseur = dresseur;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public void interfaceCombat(Pokemon combattant, DresseurHandler dresseurAdv) throws NotATypeException {
		out.println("1. Attaquer ou 2. Changer de Pokemon?");
		try {
			Attaque atkChoisi = null;
			while (dresseurPret == false) {
				String input = in.readLine();
				System.out.println(input);
				if (input.equals("1")) {
					boolean choix = false;
						atkChoisi = this.choisirAttaquePokemon(combattant);
						dresseurPret = true;
				} else {
					combattant = this.changerPokemon(d1);
					dresseurPret = true;
				}
			}
			while(this.dresseurPret != dresseurAdv.dresseurPret){

			}
			System.out.println(combattant.getVitesse() +" "+ combattant.getVitesseBase());
			System.out.println(dresseurAdv.d1.getEquipe().get(0).getVitesse() +" "+ dresseurAdv.d1.getEquipe().get(0).getVitesseBase());
			if(combattant.getVitesse() < dresseurAdv.d1.getEquipe().get(0).getVitesse()){
				
				out.println(combattant.getNom() + "est moins rapide que " + dresseurAdv.d1.getEquipe().get(0).getNom());
				Thread.sleep(5000);
				if(!combattant.getKo()){
					phaseAttaque(combattant, dresseurAdv, atkChoisi);
				}
			}else{
				out.println(combattant.getNom() + "est plus rapide que " + dresseurAdv.d1.getEquipe().get(0).getNom());
				phaseAttaque(combattant, dresseurAdv, atkChoisi);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void phaseAttaque(Pokemon combattant, DresseurHandler dresseurAdv, Attaque atkChoisi) throws NotATypeException{
		attaquer(combattant, atkChoisi, dresseurAdv.d1.getEquipe().get(0));
	}

	public Pokemon choisirPremierPokemon(Dresseur dresseur) throws IOException{
        if (dresseur.getEquipe().size() > 1){
            out.println("Qui commence le combat?\n" + dresseur.getEquipe());
            int lePokemon = in.read();
            Collections.swap(dresseur.getEquipe(), 0, lePokemon);
        }
        out.println(dresseur.getEquipe().get(0).getNom() + ", go!");
        return dresseur.getEquipe().get(0);
    }

    public Pokemon changerPokemon(Dresseur dresseur) throws IOException{
        out.println("Revient " + dresseur.getEquipe().get(0).getNom() + " !");
        String lePokemon = in.readLine();
		int index = Integer.parseInt(lePokemon);
		System.out.println(lePokemon);
        Collections.swap(dresseur.getEquipe(), 0, index);
        out.println(dresseur.getEquipe().get(0).getNom() + ", go!");
        scan.close();
        return dresseur.getEquipe().get(0);
        
    }

    public Attaque choisirAttaquePokemon(Pokemon lePokemon) throws IOException{
        out.println("Quelle attaque utilisée? 1: " + lePokemon.getLesAttaques().get(0) +
                                                    "2: " + lePokemon.getLesAttaques().get(1) +
                                                    "3: " + lePokemon.getLesAttaques().get(2) + 
                                                    "4: " + lePokemon.getLesAttaques().get(3));
        String lAttaqueStr = in.readLine();
		int lAttaque = Integer.parseInt(lAttaqueStr); 
		System.out.println(lAttaqueStr);       
        return lePokemon.getLesAttaques().get(lAttaque);
    }

	/*
     * Permet d'attaquer un autre pokémon dans un combat en ligne, calculant tout
     * les dégâts infligés(Selon le calcul officiel).
     */
    public void attaquer(Pokemon monPoke, Attaque atkChoisi, Pokemon pokAdv) throws NotATypeException {
		int pvRestant = (int) ((int) pokAdv.getPv() - ((monPoke.getNiveau() * 0.4 + 2) * monPoke.getAtk() * atkChoisi.getPuissance() / pokAdv.getDef()
		* 50)
		* atkChoisi.calculEfficacite(pokAdv)
		* atkChoisi.calculResistance(pokAdv)
		* atkChoisi.isNeutre(pokAdv));
        pokAdv.setPv(pvRestant);
        if (pokAdv.getPv() <= 0) {
			monPoke.setNiveauIncr();
            pokAdv.setKo(true); 
        }
    }
}

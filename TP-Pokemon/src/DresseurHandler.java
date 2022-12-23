import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


/* Interface côté serveur d'un client, le premier dresseur est mis en attente
 * en attendant le deuxième. le combat commence directement après.
 */
public class DresseurHandler implements Runnable {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private static ArrayList<DresseurHandler> dresseursHandler;
	private static ArrayList<Dresseur> dresseurs = new ArrayList<>();
	private Dresseur dresseur;
	private boolean combat;
	private Scanner scan = new Scanner(System.in);
	private boolean dresseurPret;

	private Save save;

	// Constructor
	public DresseurHandler(Socket dresseurSocket, ArrayList<DresseurHandler> nDresseurs) throws IOException {
		this.socket = dresseurSocket;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		dresseursHandler = nDresseurs;
		combat = false;
	}

	@Override
	public synchronized void run() {
		String pseudoRequest = "Bienvenu dans l'Arene! Quel est votre pseudo?";
		out.println(pseudoRequest);
		try {
			String dresseurPseudo = in.readLine();
			save = new Save(dresseurPseudo);
			dresseur = save.readToFolder(dresseurPseudo);

			save.transToFolder(dresseur);
			dresseursHandler.add(this);
			dresseurs.add(dresseur);
			combat: while (true) {
				DresseurHandler dresseurAdv = null;
				while (Arene.getDresseurs().size() < 2) {
					out.print("En attente d'adversaire.\n");
					out.print("\u001B[A");
				}
				for (DresseurHandler dresseur : dresseursHandler) {
					if (!dresseur.equals(this)) {
						dresseurAdv = dresseur;
					}
				}
				synchronized (this) {
					combat = true;
					out.println(dresseur.getEquipe().get(0).getNom() + ", go!");
					dresseurPret = true;
					Pokemon combattant = dresseur.getEquipe().get(0);
					Pokemon pokeAdv = dresseurAdv.dresseur.getEquipe().get(0);
					/* La phase de combat en mode en ligne est plus ou moins la même
					 * que celle du combat en mode solo mais le code n'est pas placé
					 * dans des fonctions. Le calcul de vitesse est "simulé" avec 
					 * un sleep du Thread qui a le dresseur avec le Pokemon le plus lent.
					 * 
					 */
					while (combat) {
						dresseurPret = false;
						out.println(
								combattant.getNom() + "\nNv." + combattant.getNiveau() + " " + combattant.getPv() + "/"
										+ (int) (2 * combattant.getPvBase() * combattant.getNiveau() / 100 + 10
												+ combattant.getNiveau())
										+ "\n");
						out.println(pokeAdv.getNom() + "\nNv."
								+ pokeAdv.getNiveau() + " "
								+ pokeAdv.getPv() + "/"
								+ (int) (2 * pokeAdv.getPvBase()
										* pokeAdv.getNiveau() / 100 + 10
										+ pokeAdv.getNiveau()));
						out.println("1. Attaquer ou 2. Changer de Pokemon?");

						Attaque atkChoisi = null;
						turn: while (dresseurPret == false) {
							String input = in.readLine();
							System.out.println(input);
							if (Integer.parseInt(input) == 1) {
								out.println("Quelle attaque utilisée? 0: " + combattant.getLesAttaques().get(0) +
										"1: " + combattant.getLesAttaques().get(1) +
										"2: " + combattant.getLesAttaques().get(2) +
										"3: " + combattant.getLesAttaques().get(3));
								String lAttaqueStr = in.readLine();
								int lAttaque = Integer.parseInt(lAttaqueStr);
								atkChoisi = combattant.getLesAttaques().get(lAttaque);
								dresseurPret = true;
								while (this.dresseurPret == false || dresseurAdv.dresseurPret == false) {
									out.print("En attente de l'adversaire.\n");
									out.print("\u001B[A");
									out.print("En attente de l'adversaire..\n");
									out.print("\u001B[A");
									out.print("En attente de l'adversaire...\n");
									out.print("\u001B[A");
								}
								if (combattant.getVitesse() < dresseurAdv.dresseur.getEquipe().get(0)
										.getVitesse()) {
									out.println(combattant.getNom() + " est moins rapide que "
											+ dresseurAdv.dresseur.getEquipe().get(0).getNom());
									Thread.sleep(2000);
									if (!combattant.getKo()) {
										phaseAttaque(combattant, dresseurAdv, atkChoisi);
									}
								} else {
									out.println(combattant.getNom() + " est plus rapide que "
											+ dresseurAdv.dresseur.getEquipe().get(0).getNom());
									phaseAttaque(combattant, dresseurAdv, atkChoisi);
								}
								if (combattant.getKo()) {
									/*Bloc de condition équivalent à la fonction changerPokemon()
									 * lorsqu'un Pokemon est KO
									*/
									dresseur.getPokeKo().add(combattant);
									if (dresseur.getEquipe().size() == dresseur.getPokeKo().size()){
										out.println("Tous vos pokemons sont KO!");
									}else{
										if (dresseur.getEquipe().get(0).getKo()) {
											this.out.println(
													"Non " + dresseur.getEquipe().get(0).getNom() + "! Reviens vite!");
										} else {
											out.println("Revient " + dresseur.getEquipe().get(0).getNom() + " !");
										}
	
										String lePokemon = in.readLine();
										int index = Integer.parseInt(lePokemon);
										while (dresseur.getEquipe().get(Integer.parseInt(lePokemon)).getKo()) {
											this.out.println(
													dresseur.getEquipe().get(Integer.parseInt(lePokemon)) + " est KO");
											lePokemon = in.readLine();
										}
										Collections.swap(dresseur.getEquipe(), 0, index);
										this.out.println(dresseur.getEquipe().get(0).getNom() + ", go!");
										combattant = dresseur.getEquipe().get(0);
									}
									
								}
								if (dresseur.getEquipe().size() == dresseur.getPokeKo().size()) {
									//Fin de la boucle principale quand le combat est fini
									out.println("Vous êtes incapable de continuer le combat");
									combat = false;
									this.dresseur.setEnCombat(false);
									break combat;
								}
								if (dresseurAdv.dresseur.getEquipe().size() == dresseurAdv.dresseur.getPokeKo()
										.size()) {
									out.println("Vous remportez le combat!");
									combat = false;
									this.dresseur.setEnCombat(false);
									break combat;
								}
								break turn;

							} else {
								if (dresseur.getEquipe().get(0).getKo()) {
									this.out.println("Non " + dresseur.getEquipe().get(0).getNom() + "! Reviens vite!");
								} else {
									out.println("Revient " + dresseur.getEquipe().get(0).getNom() + " !");
								}
								String lePokemon = in.readLine();
								int index = Integer.parseInt(lePokemon);
								while (dresseur.getEquipe().get(Integer.parseInt(lePokemon)).getKo()) {
									this.out.println(dresseur.getEquipe().get(Integer.parseInt(lePokemon)) + " est KO");
									lePokemon = in.readLine();
								}
								Collections.swap(dresseur.getEquipe(), 0, index);
								this.out.println(dresseur.getEquipe().get(0).getNom() + ", go!");
								combattant = dresseur.getEquipe().get(0);
								dresseurPret = true;
								while (this.dresseurPret == false && dresseurAdv.dresseurPret == false) {
									out.print("En attente de l'adversaire.\n");
									out.print("\u001B[A");
									out.print("En attente de l'adversaire..\n");
									out.print("\u001B[A");
									out.print("En attente de l'adversaire...\n");
									out.print("\u001B[A");
								}
								break turn;
							}
						}

					}
				}

			}
		} catch (IOException | NotATypeException | ClassNotFoundException | InterruptedException e) {
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
		for (DresseurHandler dresseurClient : dresseursHandler) {
			dresseurClient.out.println(msg);
		}
	}

	// Getters
	public Socket getSocket() {
		return socket;
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

	public Dresseur getdresseur() {
		return dresseur;
	}

	public Save getSave() {
		return save;
	}

	// Setters
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public void phaseAttaque(Pokemon combattant, DresseurHandler dresseurAdv, Attaque atkChoisi)
			throws NotATypeException {
		attaquer(combattant, atkChoisi, dresseurAdv.dresseur.getEquipe().get(0));
	}

	/*
	 * Permet d'attaquer un autre pokémon dans un combat en ligne, calculant tout
	 * les dégâts infligés(Selon le calcul officiel).
	 */
	public void attaquer(Pokemon monPoke, Attaque atkChoisi, Pokemon pokAdv) throws NotATypeException {
		int degat = 0;
		if (atkChoisi.getGenre().equals("Physique")) {
			degat = (int) (((((monPoke.getNiveau() * 0.4 + 2) * monPoke.getAtk() * atkChoisi.getPuissance())
					/ pokAdv.getDef()
					/ 50) + 2) * atkChoisi.calculEfficacite(pokAdv)
					* atkChoisi.calculResistance(pokAdv)
					* atkChoisi.isNeutre(pokAdv));
		} else {
			degat = (int) (((((monPoke.getNiveau() * 0.4 + 2) * monPoke.getAtkSpe() * atkChoisi.getPuissance())
					/ pokAdv.getDefSpe() / 50) + 2) * atkChoisi.calculEfficacite(pokAdv)
					* atkChoisi.calculResistance(pokAdv) * atkChoisi.isNeutre(pokAdv));
		}
		outToAll(monPoke.getNom() + " utilise " + atkChoisi + "!");
		outToAll(pokAdv.getNom() + " a subit " + degat + " degats");
		pokAdv.subirDegat(degat);
		if (pokAdv.getPv() <= 0) {
			pokAdv.setKo(true);
		}
	}
}

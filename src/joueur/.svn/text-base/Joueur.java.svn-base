package joueur;

import java.util.ArrayList;

import jeu.Numero;

import combinaison.*;
import exception.BesaceInsuffisanteException;
import exception.MiseMaxException;


public abstract class Joueur {
	private String nom;
	private int besace;
	private ArrayList<Combinaison> mises;
	private int besace_init;
	private int nombre_de_jeu = 0;


	/**
	 * Constructeur de la classe: on affecte au joueur le nom par défaut"Joueur X", et on initialise la valeur de sa besace par défaut à 100.
	 */
	public Joueur(){
		this.nom = "Joueur X";
		this.besace = 100;
		this.besace_init=this.besace;
	}


	/**
	 * Constructeur avec paramètres:on affecte au nom du joueur le nom entré en paramètreet à sa besace,
	 * l'entier entré en paramètre; et on crée une liste "mises" qui contiendra les valeurs de ses mises. 
	 * @param nom
	 * @param besace
	 * @throws BesaceInsuffisanteException 
	 */
	public Joueur(String nom, int besace) throws BesaceInsuffisanteException{
		this.nom = nom;
		setBesace(besace);
		this.besace_init=this.besace;
		this.mises = new ArrayList<Combinaison>();
	}


	/**
	 * Fonction qui retourne la valeur de la besace.
	 * @return la besace.
	 */
	public int getBesace(){
		return this.besace;
	}

	/**
	 * Fonction qui retourne la valeur initiale de la besace.
	 * @return la besace initiale.
	 */
	public int getBesaceInit(){
		return this.besace_init;
	}

	/**
	 * Fonction qui retourne les gains ou les pertes
	 * @return besace-besace_init.
	 */
	public int gainPerte(){
		int argent_mise = 0;
		for(Combinaison c:mises){
			argent_mise+=c.getMise();
		}
		return getBesace()+argent_mise-getBesaceInit();
	}

	/**
	 * Fonction qui modifie la valeur de la besace avec l'entier entré en paramètre b. 
	 * @param b montant à initialiser dans la besace
	 * @throws BesaceInsuffisanteException 
	 */
	public void setBesace(int b) throws BesaceInsuffisanteException{
		if(b<0){
			throw new BesaceInsuffisanteException(b);
		}
		else{
			this.besace=b;
		}
	}

	/**
	 * Fonction qui modifie la valeur de la besace intiale avec l'entier entré en paramètre b. 
	 * @param b montant à initialiser dans la besace intiale
	 */
	public void setBesaceInit(int b){
		this.besace_init=b;
	}


	/**
	 * Fonction qui retourne le nom du joueur.
	 * @return nom du joueur
	 */
	public String getNom(){
		return this.nom;
	}




	/**
	 * Fonction qui permet:
	 * -de soustraire la valeur de la mise en paramètre de la besace,
	 * -d'ajouter la mise sur Rouge à la liste des mises du joueur.
	 * @param mise
	 * @throws MiseMaxException 
	 * @throws BesaceInsuffisanteException 
	 */
	public void miserRouge(int mise) throws MiseMaxException, BesaceInsuffisanteException{
		mises.add(new CombinaisonSimple(ChanceSimple.Rouge, mise));
		this.factorisationMise();
		retirerMiser(mise);
		System.out.println(getNom()+" mise "+mise+" euros sur rouge.");
	}


	/**
	 * Fonction qui permet:
	 * -de soustraire la valeur de la mise en paramètre de la besace,
	 * -d'ajouter la mise sur Noir à la liste des mises du joueur.
	 * @param mise
	 * @throws MiseMaxException 
	 * @throws BesaceInsuffisanteException 
	 */
	public void miserNoir(int mise) throws MiseMaxException, BesaceInsuffisanteException{
		mises.add(new CombinaisonSimple(ChanceSimple.Noir, mise));
		this.factorisationMise();
		retirerMiser(mise);
		System.out.println(getNom()+" mise "+mise+" euros sur noir.");
	}


	/**
	 * Fonction qui permet:
	 * -de soustraire la valeur de la mise en paramètre de la besace,
	 * -d'ajouter la mise sur Pair à la liste des mises du joueur.
	 * @param mise
	 * @throws MiseMaxException 
	 * @throws BesaceInsuffisanteException 
	 */
	public void miserPair(int mise) throws MiseMaxException, BesaceInsuffisanteException{
		mises.add(new CombinaisonSimple(ChanceSimple.Pair, mise));
		this.factorisationMise();
		retirerMiser(mise);
		System.out.println(getNom()+" mise "+mise+" euros sur pair.");
	}

	/**
	 * Fonction qui permet:
	 * -de soustraire la valeur de la mise en paramètre de la besace,
	 * -d'ajouter la mise sur Impair à la liste des mises du joueur.
	 * @param mise
	 * @throws MiseMaxException 
	 * @throws BesaceInsuffisanteException 
	 */
	public void miserImpair(int mise) throws MiseMaxException, BesaceInsuffisanteException{
		mises.add(new CombinaisonSimple(ChanceSimple.Impair, mise));
		this.factorisationMise();
		retirerMiser(mise);
		System.out.println(getNom()+" mise "+mise+" euros sur impair.");
	}


	/**
	 * Fonction qui permet:
	 * -de soustraire la valeur de la mise en paramètre de la besace,
	 * -d'ajouter la mise sur Passe à la liste des mises du joueur.
	 * @param mise
	 * @throws MiseMaxException 
	 * @throws BesaceInsuffisanteException 
	 */
	public void miserPasse(int mise) throws MiseMaxException, BesaceInsuffisanteException{
		mises.add(new CombinaisonSimple(ChanceSimple.Passe, mise));
		this.factorisationMise();
		retirerMiser(mise);
		System.out.println(getNom()+" mise "+mise+" euros sur passe.");
	}


	/**
	 * Fonction qui permet:
	 * -de soustraire la valeur de la mise en paramètre de la besace,
	 * -d'ajouter la mise sur Manque à la liste des mises du joueur.
	 * @param mise
	 * @throws MiseMaxException 
	 * @throws BesaceInsuffisanteException 
	 */
	public void miserManque(int mise) throws MiseMaxException, BesaceInsuffisanteException{
		mises.add(new CombinaisonSimple(ChanceSimple.Manque, mise));
		this.factorisationMise();
		retirerMiser(mise);
		System.out.println(getNom()+" mise "+mise+" euros sur manque.");
	}

	/**
	 * Fonction qui permet:
	 * -de soustraire la valeur de la mise en paramètre de la besace,
	 * -d'ajouter la mise sur la première douzaine à la liste des mises du joueur.
	 * @param mise
	 * @throws MiseMaxException 
	 * @throws BesaceInsuffisanteException 
	 */
	public void miserP(int mise) throws MiseMaxException, BesaceInsuffisanteException{
		mises.add(new CombinaisonDouzaine(ChanceDouzaine.P, mise));
		this.factorisationMise();
		retirerMiser(mise);
		System.out.println(getNom()+" mise "+mise+" euros sur les 12 premiers numeros.");
	}

	/**
	 * Fonction qui permet:
	 * -de soustraire la valeur de la mise en paramètre de la besace,
	 * -d'ajouter la mise sur la deuxième douzaine à la liste des mises du joueur.
	 * @param mise
	 * @throws MiseMaxException 
	 * @throws BesaceInsuffisanteException 
	 */
	public void miserM(int mise) throws MiseMaxException, BesaceInsuffisanteException{
		mises.add(new CombinaisonDouzaine(ChanceDouzaine.M, mise));
		this.factorisationMise();
		retirerMiser(mise);
		System.out.println(getNom()+" mise "+mise+" euros sur les 12 numeros du milieu.");
	}

	/**
	 * Fonction qui permet:
	 * -de soustraire la valeur de la mise en paramètre de la besace,
	 * -d'ajouter la mise sur la derniere douzaine à la liste des mises du joueur.
	 * @param mise
	 * @throws MiseMaxException 
	 * @throws BesaceInsuffisanteException 
	 */
	public void miserD(int mise) throws MiseMaxException, BesaceInsuffisanteException{
		mises.add(new CombinaisonDouzaine(ChanceDouzaine.D, mise));
		this.factorisationMise();
		retirerMiser(mise);
		System.out.println(getNom()+" mise "+mise+" euros sur les 12 derniers numeros.");
	}

	/**
	 * Fonction qui permet:
	 * -de soustraire la valeur de la mise en paramètre de la besace,
	 * -d'ajouter la mise sur la première colonne à la liste des mises du joueur.
	 * @param mise
	 * @throws MiseMaxException 
	 * @throws BesaceInsuffisanteException 
	 */
	public void miserC1(int mise) throws MiseMaxException, BesaceInsuffisanteException{
		mises.add(new CombinaisonDouzaine(ChanceDouzaine.C1, mise));
		this.factorisationMise();
		retirerMiser(mise);
		System.out.println(getNom()+" mise "+mise+" euros sur la premiere colonne.");
	}

	/**
	 * Fonction qui permet:
	 * -de soustraire la valeur de la mise en paramètre de la besace,
	 * -d'ajouter la mise sur la deuxième colonne à la liste des mises du joueur.
	 * @param mise
	 * @throws MiseMaxException 
	 * @throws BesaceInsuffisanteException 
	 */
	public void miserC2(int mise) throws MiseMaxException, BesaceInsuffisanteException{
		mises.add(new CombinaisonDouzaine(ChanceDouzaine.C2, mise));
		this.factorisationMise();
		retirerMiser(mise);
		System.out.println(getNom()+" mise "+mise+" euros sur la deuxieme colonne.");
	}

	/**
	 * Fonction qui permet:
	 * -de soustraire la valeur de la mise en paramètre de la besace,
	 * -d'ajouter la mise sur la dernière colonne à la liste des mises du joueur.
	 * @param mise
	 * @throws MiseMaxException 
	 * @throws BesaceInsuffisanteException 
	 */
	public void miserC3(int mise) throws MiseMaxException, BesaceInsuffisanteException{
		mises.add(new CombinaisonDouzaine(ChanceDouzaine.C3, mise));
		this.factorisationMise();
		retirerMiser(mise);
		System.out.println(getNom()+" mise "+mise+" euros sur la derniere colonne.");
	}

	/**
	 * Fonction qui permet:
	 * -de soustraire la valeur de la mise en paramètre de la besace,
	 * -d'ajouter la mise sur un sixain à la liste des mises du joueur.
	 * @param mise et type du sixan
	 * @throws MiseMaxException 
	 * @throws BesaceInsuffisanteException 
	 */
	public void miserSixain(int mise, ChanceSixain c) throws MiseMaxException, BesaceInsuffisanteException{
		mises.add(new CombinaisonSixain(c, mise));
		this.factorisationMise();
		retirerMiser(mise);
		System.out.println(getNom()+" mise "+mise+" euros sur le "+mises.get(mises.size()-1)+".");
	}

	/**
	 * Fonction qui permet:
	 * -de soustraire la valeur de la mise en paramètre de la besace,
	 * -d'ajouter la mise sur un carré à la liste des mises du joueur.
	 * @param mise et type du carré
	 * @throws MiseMaxException 
	 * @throws BesaceInsuffisanteException 
	 */
	public void miserCarre(int mise, ChanceCarre c) throws MiseMaxException, BesaceInsuffisanteException{
		mises.add(new CombinaisonCarre(c, mise));
		this.factorisationMise();
		retirerMiser(mise);
		System.out.println(getNom()+" mise "+mise+" euros sur le "+mises.get(mises.size()-1)+".");
	}

	/**
	 * Fonction qui permet:
	 * -de soustraire la valeur de la mise en paramètre de la besace,
	 * -d'ajouter la mise sur une transversale à la liste des mises du joueur.
	 * @param mise et type de la transversale
	 * @throws MiseMaxException 
	 * @throws BesaceInsuffisanteException 
	 */
	public void miserTransversale(int mise, ChanceTransversale c) throws MiseMaxException, BesaceInsuffisanteException{
		mises.add(new CombinaisonTransversale(c, mise));
		this.factorisationMise();
		retirerMiser(mise);
		System.out.println(getNom()+" mise "+mise+" euros sur la "+mises.get(mises.size()-1)+".");
	}

	/**
	 * Fonction qui permet:
	 * -de soustraire la valeur de la mise en paramètre de la besace,
	 * -d'ajouter la mise sur un cheval à la liste des mises du joueur.
	 * @param mise et type du cheval
	 * @throws MiseMaxException 
	 * @throws BesaceInsuffisanteException 
	 */
	public void miserCheval(int mise, ChanceCheval c) throws MiseMaxException, BesaceInsuffisanteException{
		mises.add(new CombinaisonCheval(c, mise));
		this.factorisationMise();
		retirerMiser(mise);
		System.out.println(getNom()+" mise "+mise+" euros sur le "+mises.get(mises.size()-1)+".");
	}

	/**
	 * Fonction qui permet:
	 * -de soustraire la valeur de la mise en paramètre de la besace,
	 * -d'ajouter la mise sur le numéro à la liste des mises du joueur.
	 * @param mise et le numero
	 * @throws MiseMaxException 
	 * @throws BesaceInsuffisanteException 
	 */
	public void miserPlein(int mise, int numero) throws MiseMaxException, BesaceInsuffisanteException{
		Combinaison c = new CombinaisonPlein(numero, mise);
		mises.add(c);
		this.factorisationMise();
		retirerMiser(mise);
		System.out.println(getNom()+" mise "+mise+" euros sur le "+mises.get(mises.size()-1)+".");
	}



	/**
	 * Fonction qui retourne la liste des mises d'un joueur.
	 * @return mises
	 */
	public ArrayList<Combinaison> getMises(){
		return mises;
	}


	/**
	 * Retire le montant de la mise de la besace lors d'un pari
	 * @return mises à retirer de la besace (lors d'une mise)
	 * @throws BesaceInsuffisanteException 
	 */
	public void retirerMiser(int mise) throws BesaceInsuffisanteException{
		if(this.besace-mise>=0){
			setBesace(this.besace-mise);
			this.nombre_de_jeu++;
		}
		else{
			mises.remove(mises.size()-1);
			throw new BesaceInsuffisanteException();
		}
	}

	/**
	 * Procedure qui permet d'appeler la methode du même nom des classes héritant de cette classe.
	 * @throws BesaceInsuffisanteException 
	 * @throws MiseMaxException 
	 */
	public void miser() throws MiseMaxException, BesaceInsuffisanteException{}

	/**
	 * Procedure qui permet d'appeler la methode du même nom des classes héritant de cette classe.
	 */
	public void gains(Numero n_tirage){}

	/**
	 * Fonction qui permet de parcourir les mises et d'ajouter les gains à la besace
	 * @param numero du tirage
	 */
	public void recuperationDesGains(Numero n_tirage){
		for(Combinaison c: getMises()){
			try {//On attrape l'exception car on ne peut théoriquement pas en lever puisqu'on ajoute a la besace
				setBesace(getBesace()+c.gain(n_tirage));
			} catch (BesaceInsuffisanteException e) {
				e.printStackTrace();
			}
		}
		getMises().clear();
	}

	/**
	 * Fonction qui permet de rassembler les mises de même type.
	 * @throws MiseMaxException 
	 * @throws BesaceInsuffisanteException 
	 */
	public void factorisationMise() throws MiseMaxException, BesaceInsuffisanteException {
		if(mises.size()>=1){
			Combinaison c = mises.get(mises.size()-1);
			for(int i=0;i<mises.size()-1;i++){
				if(c.toString().equals(mises.get(i).toString())){
					if(c.getMise()+mises.get(i).getMise()<=c.getMise_maximum() && this.besace-c.getMise()>=0){
					mises.get(i).setMise(c.getMise()+mises.get(i).getMise());
					mises.remove(c);
					}
					else if(this.besace-c.getMise()<0){
						mises.remove(c);
						throw new BesaceInsuffisanteException();
					}
					else{
					mises.remove(c);
					throw new MiseMaxException();
					}
				}
			}
		}
	}

	/**
	 * Fontion qui permet d'annuler les mises
	 */
	public void annulerMise(){
		int montant_mise=0;
		for(Combinaison c:mises){
			montant_mise+=c.getMise();
		}
		mises.clear();
		try {
			setBesace(getBesace()+montant_mise);
			System.out.println(this.getNom()+" reprend ses jetons : "+montant_mise+" euros.");
		} catch (BesaceInsuffisanteException e) {}
	}


	public int getNombredejeu() {
		return nombre_de_jeu;
	}


	public void setNombredejeu(int nombre_de_jeu) {
		this.nombre_de_jeu = nombre_de_jeu;
	}
}

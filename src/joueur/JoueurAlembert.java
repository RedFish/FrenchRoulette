package joueur;

import java.util.Random;

import jeu.Numero;

import combinaison.ChanceSimple;

import exception.BesaceInsuffisanteException;
import exception.MiseMaxException;

public class JoueurAlembert extends Joueur {
	private boolean gain_coup_precedent;
	private int mise;
	private int mise_min;
	private boolean peut_miser_plus;
	private ChanceSimple type_joueur;

	/**
	 * Constructeur par defaut
	 * @throws BesaceInsuffisanteException 
	 */
	public JoueurAlembert() throws BesaceInsuffisanteException {
		this("Joueur Alembert",100);
	}

	public JoueurAlembert(String nom, int besace) throws BesaceInsuffisanteException {
		super(nom, besace);
		this.gain_coup_precedent=true;
		this.mise = 1;					 //mise initiale
		this.mise_min=this.mise;
		if(this.mise+1<this.getBesace()){				 //initialisation de la variable qui permet miser plus
			this.peut_miser_plus=true;
		}
		else{
			this.peut_miser_plus=false;
		}

		Random r = new Random();		 //on initialise le type de joueur (s'il préfere jouer rouge, pair ou manque)

		switch (r.nextInt(6)) {
		case 0:
			this.type_joueur = ChanceSimple.Rouge;
			break;
		case 1:
			this.type_joueur = ChanceSimple.Noir;
			break;
		case 2:
			this.type_joueur = ChanceSimple.Pair;
			break;
		case 3:
			this.type_joueur = ChanceSimple.Impair;
			break;
		case 4:
			this.type_joueur = ChanceSimple.Passe;
			break;
		case 5:
			this.type_joueur = ChanceSimple.Manque;
			break;
		}
	}

	public boolean peutMiserPlus(){
		return this.peut_miser_plus;
	}
	
	public void miserPlus(){
		if(mise+1<=getBesace()){
			mise++;
		}
		else{
			this.peut_miser_plus=false;
		}
	}
	
	public void miserMoins(){
		if(mise-1>=this.mise_min){
			mise--;
		}
		else{
			this.mise=this.mise_min;
		}
	}
	
	

	public void miser() throws MiseMaxException, BesaceInsuffisanteException{
		if(this.gain_coup_precedent){
			miserMoins();
		}
		else{
			miserPlus();
		}
		//on ajoute la mise du joueur à la liste de ses mises
		if(peutMiserPlus()){
			switch (type_joueur) {
			case Rouge:
				this.miserRouge(mise);
				break;
			case Noir:
				this.miserNoir(mise);
				break;
			case Pair:
				this.miserPair(mise);
				break;
			case Impair:
				this.miserImpair(mise);
				break;
			case Passe:
				this.miserPasse(mise);
				break;
			case Manque:
				this.miserManque(mise);
				break;
			}
		}
	}
	
	/**
	 * Fonction qui permet de donner les gains en fonction du tirage et des mises
	 * et détecte s'il y a eut un gain pour applique la stratégie
	 */
	public void gains(Numero n_tirage){
		int b1 = getBesace();
		recuperationDesGains(n_tirage);
		if(b1<getBesace()) {
			this.gain_coup_precedent=true;
			int gain=getBesace()-b1;
			System.out.println(getNom()+" a gagne "+gain+" euros (sa besace vaut a present "+getBesace()+" euros)");
		}
		else{
			this.gain_coup_precedent=false;
			System.out.println(getNom()+" perd sa mise (sa besace vaut a present "+getBesace()+" euros)");
		}
	}
}

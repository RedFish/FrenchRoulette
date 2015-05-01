package joueur;

import java.util.Random;

import combinaison.ChanceSimple;

import jeu.Numero;
import exception.BesaceInsuffisanteException;
import exception.MiseMaxException;

/**
 * Classe JoueurHawks qui permet de définir un joueur qui suit la stratégie de Hawks.
 * @author Fadwa
 *
 */

public class JoueurHawks extends Joueur {
	private boolean gain_coup_precedent;
	private int mise;
	private boolean peut_doubler;
	private ChanceSimple type_joueur;




	/**
	 * Constructeur par defaut
	 * @throws BesaceInsuffisanteException 
	 */
	public JoueurHawks() throws BesaceInsuffisanteException {
		this("Joueur Hawks",100);
	}




	/**
	 * Le constructeur qui permet à partir d'une chaine de caractères et d'un entier, de définir le nom 
	 * du joueur et le contenu de sa besace, et d'initialiser la variable boolèenne gain_coup_precedent à vrai. 
	 * @param nom
	 * @param besace
	 * @throws BesaceInsuffisanteException 
	 */
	public JoueurHawks(String nom, int besace) throws BesaceInsuffisanteException {
		super(nom, besace);
		this.gain_coup_precedent=true;
		this.mise = 1;					 //mise initiale
		if(this.mise*2<=getBesace()){	 //initialisation de la variable qui permet de doubler
			this.peut_doubler=true;
		}
		else{
			this.peut_doubler=false;
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





	/**
	 * Fonction qui double la valeur de la mise si le double de la mise ne dépasse pas la besace, sinon 
	 * afficher un message qui indique que le joueur ne peut plus miser.
	 */
	private void doublerLaMise(){
		if(mise*2<=getBesace()){
			mise=mise*2;
		}
		else{
			this.peut_doubler=false;
		}
	}

	/**
	 * Fonction qui retourne la valeur du booléen peut_doubler.
	 * @return peut_doubler.
	 */
	public boolean peutDoubler(){
		return peut_doubler;
	}



	/**
	 * Fonction qui permet d'affecter a la valeur de la mise, la mise du joueur en fonction de son gain 
	 * au coup précedent, et de reirer cette valeur de sa besace.
	 */
	public void miser() throws MiseMaxException, BesaceInsuffisanteException{

		if(this.gain_coup_precedent){//On modifie la valeur de la mise en faonction ducoup précedent.
			//on repart à la mise minimale
			this.mise = 1;
		}
		else{
			//on double la mise précédente
			doublerLaMise();
		}
		//on ajoute la mise du joueur à la liste de ses mises
		if(this.peutDoubler()){
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

package joueur;

import java.util.Random;

import jeu.Numero;

import combinaison.ChanceSimple;
import exception.BesaceInsuffisanteException;
import exception.MiseMaxException;


public class JoueurESIAL extends Joueur {
	private boolean gain_coup_precedent;
	private int mise;
	private boolean peut_doubler;
	private ChanceSimple type_joueur;

	public JoueurESIAL(String nom, int besace) throws BesaceInsuffisanteException {
		super(nom, besace);
		this.gain_coup_precedent = true; // on initialise la varaible pour que le joueur ne double pas au premier coup
		this.mise = 1;					 //mise initiale
		if(this.mise*2<=getBesace()){	 //initialisation de la variable qui permet de doubler
			this.peut_doubler=true;
		}
		else{
			this.peut_doubler=false;
		}

		Random r = new Random();		 //on initialise le type de joueur (s'il préfere jouer rouge, pair ou manque)

		switch (r.nextInt(3)) {
		case 0:
			this.type_joueur = ChanceSimple.Rouge;
			break;
		case 1:
			this.type_joueur = ChanceSimple.Pair;
			break;
		case 2:
			this.type_joueur = ChanceSimple.Manque;
			break;
		}
	}




	/**
	 * Finction qui permet de doubler la valeur de la mise si elle ne dépasse pas la valeur de la besace 
	 * du joueur
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
	 * Fonction qui permet de:
	 * -Modifier la valeur de la mise en fonction du gain ou de laperte du coup précédent,
	 * -Ajouter la mise du joueur à la liste de ses mises,
	 * -Recalculer le contenu de la besace du joueur après la mise,
	 * -Retourner un message indiquant le nom du joueur, le montant de sa mise et sur quoi il a misé.
	 * @throws BesaceInsuffisanteException 
	 * @throws MiseMaxException 
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
			case Pair:
				this.miserPair(mise);
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

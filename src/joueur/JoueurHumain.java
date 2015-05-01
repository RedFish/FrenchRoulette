package joueur;

import java.util.Scanner;

import jeu.Numero;
import exception.BesaceInsuffisanteException;
import exception.MiseMaxException;

public class JoueurHumain extends Joueur {

	public JoueurHumain() throws BesaceInsuffisanteException {
		this("Joueur Humain", 100);
	}

	public JoueurHumain(String nom, int besace) throws BesaceInsuffisanteException {
		super(nom, besace);
	}

	/**
	 * Fonction qui permet a l'utilisateur de saisir la mise qu'il souhaite faire.
	 * @throws BesaceInsuffisanteException 
	 * @throws MiseMaxException 
	 */
	public void miser() throws MiseMaxException, BesaceInsuffisanteException{
		int mise=1;
		int type=0;
		System.out.println(	"**********************\n" +
							"*    Type de mise :  *\n" +
							"*     0 : Rouge      *\n" +
							"*     1 : Noir       *\n" +
							"*     2 : Pair       *\n" +
							"*     3 : Impair     *\n" +
							"*     4 : Passe      *\n" +
							"*     5 : Manque     *\n" +
							"**********************\n"
							);
		Scanner clavier = new Scanner(System.in);
		do{
			if(type<0 || type>5){
				System.out.println("LE TYPE DE LA MISE DOIT ETRE COMPRIS ENTRE 0 et 5");
			}
			System.out.print("Entrer le type de la mise : ");
			try{
				type = Integer.decode(clavier.nextLine());
			}
			catch(Exception e){ type = -1; }
		} while(type<0 || type>5);
		
		do{
			if(mise<1 || mise>this.getBesace()){
				System.out.println("LE TYPE DE LA MISE DOIT ETRE COMPRIS ENTRE "+1+" et "+this.getBesace());
			}
			System.out.print("Entrer le montant de la mise : ");
			try{
				mise = Integer.decode(clavier.nextLine());
			}
			catch(Exception e){ mise = -1; }
		} while(mise<1 || mise>this.getBesace());
		
		//on ajoute la mise du joueur à la liste de ses mises
		switch (type) {// on affiche un message d'information sur le joueur et sa mise, tout en considérant tous les cas de mises possibles.
		case 0:
			this.miserRouge(mise);
			break;
		case 1:
			this.miserNoir(mise);
			break;
		case 2:
			this.miserPair(mise);
			break;
		case 3:
			this.miserImpair(mise);
			break;
		case 4:
			this.miserPasse(mise);
			break;
		case 5:
			this.miserManque(mise);
			break;
		}
	}
	
	/**
	 * Fonction qui permet de donner les gains en fonction du tirage et des mises
	 */
	public void gains(Numero n_tirage){
		int b1 = getBesace();
		recuperationDesGains(n_tirage);
		if(b1<getBesace()) {
			int gain=getBesace()-b1;
			System.out.println(getNom()+" a gagne "+gain+" euros (sa besace vaut a present "+getBesace()+" euros)");
		}
		else if(b1>getBesace()){
			System.out.println(getNom()+" perd sa mise (sa besace vaut a present "+getBesace()+" euros)");
		}
	}
}

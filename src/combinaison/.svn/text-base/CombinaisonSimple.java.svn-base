package combinaison;

import exception.MiseMaxException;
import jeu.Numero;

public class CombinaisonSimple extends Combinaison {
	private ChanceSimple cs;
	
	public CombinaisonSimple(ChanceSimple cs, int mise) throws MiseMaxException{
		super(1000,2,mise);
		this.cs=cs;
		switch (cs) {
		case Rouge:
			for(int i=1; i<37; i++){
				Numero n = new Numero(i);
				if(n.estRouge()){
					getNumeros().add(n);
				}
			}
			break;
		case Noir:
			for(int i=0; i<37; i++){
				Numero n = new Numero(i);
				if(n.estNoir()){
					getNumeros().add(n);
				}
			}
			break;

		case Pair:
			for(int i=2; i<37; i+=2){
				getNumeros().add(new Numero(i));
			}
			break;
		case Impair:
			for(int i=1; i<37; i+=2){
				getNumeros().add(new Numero(i));
			}
			break;
		case Passe:
			for(int i=19; i<37; i++){
				getNumeros().add(new Numero(i));
			}
			break;
		case Manque:
			for(int i=11; i<19; i++){
				getNumeros().add(new Numero(i));
			}
			break;
		default:
			System.out.println("ChanceSimple inconnue");
			break;
		}
	}
	
	public String toString(){
		switch (cs) {
		case Rouge:
			return "Rouge";
		case Noir:
			return "Noir";
		case Pair:
			return "Pair";
		case Impair:
			return "Impair";
		case Passe:
			return "Passe";
		case Manque:
			return "Manque";
		default:
			return "ChanceSimple inconnue";
		}
	}
}

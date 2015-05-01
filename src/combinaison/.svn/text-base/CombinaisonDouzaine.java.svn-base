package combinaison;

import jeu.Numero;
import exception.MiseMaxException;

public class CombinaisonDouzaine extends Combinaison {
	private ChanceDouzaine cd;
	
	public CombinaisonDouzaine(ChanceDouzaine cd, int mise) throws MiseMaxException {
		super(500,3,mise);
		this.cd=cd;
		int start;
		switch (cd) {
		case P:
			start = 1;
			for(int i=start; i<start+12; i++){
				getNumeros().add(new Numero(i));
			}
			break;
		case M:
			start = 13;
			for(int i=start; i<start+12; i++){
				getNumeros().add(new Numero(i));
			}
			break;
		case D:
			start = 25;
			for(int i=start; i<start+12; i++){
				getNumeros().add(new Numero(i));
			}
			break;
		case C1:
			start = 1;
			for(int i=start; i<35; i+=3){
				getNumeros().add(new Numero(i));
			}
			break;
		case C2:
			start = 2;
			for(int i=start; i<36; i+=3){
				getNumeros().add(new Numero(i));
			}
			break;
		case C3:
			start = 3;
			for(int i=start; i<37; i+=3){
				getNumeros().add(new Numero(i));
			}
			break;
		default:
			System.out.println("ChanceDouzaine inconnue");
			break;
		}
	}
	
	public String toString(){
		switch (cd) {
		case P:
			return "P (1 a 12)";
		case M:
			return "M (13 a 24)";
		case D:
			return "D (25 a 36)";
		case C1:
			return "Colonne 1";
		case C2:
			return "Colonne 2";
		case C3:
			return "Colonne 3";
		default:
			return "ChanceDouzaine inconnue";
		}
	}
}

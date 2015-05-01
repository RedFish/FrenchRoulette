package combinaison;

import exception.MiseMaxException;
import jeu.Numero;

public class CombinaisonPlein extends Combinaison {

	public CombinaisonPlein(int valeur,int mise) throws MiseMaxException {
		super(30,36,mise);
		getNumeros().add(new Numero(valeur));
	}

	public String toString(){
		return "Plein (numero "+this.getNumeros().get(0).getValeur()+")";
	}
}

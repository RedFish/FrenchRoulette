package jeu;


import java.util.ArrayList;
import java.util.Random;

/**
 * Classe qui permet de construire la roulette, qui contiendra les 37 numeros qui consitutent les objets
 * des paries des joueurs,  de définire les mises minimale et maximale, et de retourner un numero après 
 * un tirage aléatoire.
 * @author Fafa
 */
public class Roulette {
	private ArrayList<Numero> numeros = new ArrayList<Numero>();
	private final int total_occurance = 370000;
/**
 * Le constructeur qui permet d'ajouter les numeros de 0 à 36 a la roulette
 */
	public Roulette() {
		numeros.add(new Numero(0));//on ajoute le numéro 0.
		numeros.add(new Numero(32));// on ajoute le numéro 32, etc...
		numeros.add(new Numero(15));
		numeros.add(new Numero(19));
		numeros.add(new Numero(4));
		numeros.add(new Numero(21));
		numeros.add(new Numero(2));
		numeros.add(new Numero(25));
		numeros.add(new Numero(17));
		numeros.add(new Numero(34));
		numeros.add(new Numero(6));
		numeros.add(new Numero(27));
		numeros.add(new Numero(13));
		numeros.add(new Numero(36));
		numeros.add(new Numero(11));
		numeros.add(new Numero(30));
		numeros.add(new Numero(8));
		numeros.add(new Numero(23));
		numeros.add(new Numero(10));
		numeros.add(new Numero(5));
		numeros.add(new Numero(24));
		numeros.add(new Numero(16));
		numeros.add(new Numero(33));
		numeros.add(new Numero(1));
		numeros.add(new Numero(20));
		numeros.add(new Numero(14));
		numeros.add(new Numero(31));
		numeros.add(new Numero(9));
		numeros.add(new Numero(22));
		numeros.add(new Numero(18));
		numeros.add(new Numero(29));
		numeros.add(new Numero(7));
		numeros.add(new Numero(28));
		numeros.add(new Numero(12));
		numeros.add(new Numero(35));
		numeros.add(new Numero(3));
		numeros.add(new Numero(26));
	}
	
	
	
	
	/**
	 * Fonction qui retourne le numéro s'il existe dans la roulette, sinon elle retourne zéro.
	 * @param valeur
	 * @return le numéro correspondant à la valeur en paramètre
	 */
	public Numero getNumero(int valeur){
		for(Numero n:numeros){
			if(n.getValeur()==valeur) return n;
		}
		return null;	
	}
	
	
	
	/**
	 * Fonction qui retourne un numéro, parmi les 37 numéros de la roulette, choisi aléatoirement.
	 * @return la valeur qui correspond à un numero choisi aléatoirement.
	 */
	public int tirage(){
		Random r = new Random();
		return numeros.get(r.nextInt(37)).getValeur();
	}
	
	
	
	/**
	 * Fonction qui génére un tableau "tab_occurence" de 37 entiers, et qui le remplie avec les résultats
	 * du tirage aléatoire.
	 * @return tab_occurrence
	 */
    public int[] testRoulette() {
		int tab_occurance[] = new int[37];
		for(int i=0; i< getTotalOccurance(); i++){
			tab_occurance[this.tirage()]++;
		}
		return tab_occurance;
    }
    
	
	
	
	/**
	 * Fonction qui retourne le nombre total d'occurence,370000.
	 * @return 370000
	 */
	public int getTotalOccurance() {
		return total_occurance;
	 }
	
}

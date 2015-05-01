package jeu;

import java.util.ArrayList;

import exception.BesaceInsuffisanteException;
import exception.MiseMaxException;

import joueur.Joueur;



/** Fonction qui permet de construire l'espace de jeu,de le modifier par rapport à un ajout ou une suppression
 * de joueurs, de miser et d'apercevoir les gains.
 *
 */

public class Table {
	
	private Roulette roulette;
	private ArrayList<Joueur> joueurs;
	private ArrayList<Numero> tirages;
	
	
	
	/**Le constructeur par défaut de la table.
	 */
	public Table() {
		this.roulette = new Roulette();
		this.joueurs = new ArrayList<Joueur>();
		this.tirages = new ArrayList<Numero>();
	}
	
	
	
	/**
	 * Fonction qui permet à tous les joueurs de miser. 
	 * @throws BesaceInsuffisanteException 
	 * @throws MiseMaxException 
	 */
	public void miser() throws MiseMaxException, BesaceInsuffisanteException{
		for(Joueur j:joueurs){
			j.miser();
		}
	}
	
	
	/**
	 * Fonction qui ajoute un tirage aléatoire à la liste des tirages.
	 */
	public void tirage(){
		tirages.add(new Numero(roulette.tirage()));
		System.out.println("-----");
		System.out.println("CROUPIER: "+this.dernierTirage());
		System.out.println("-----");
	}
	
	/**
	 * Fonction qui retourne le numéro du dernier tirage à partir de la liste des tirage.
	 * @return le numero du dernier tirage.
	 */
	public Numero dernierTirage(){
		return tirages.get(tirages.size()-1);
	}
	
	/**
	 * Fonction qui nous permet de savoir les gains et les pertes de chacun des joueurs de la table par 
	 * rapport au dernier tirage.
	 */
	public void gains(){
		for(Joueur j:joueurs){
			j.gains(dernierTirage());
		}
	}
	
	/**
	 * Fonction qui permet d'ajouter un joueur à la liste des joueurs.
	 * @param joueurX
	 */
	public void addJoueur(Joueur joueurX){
		this.joueurs.add(joueurX);
		System.out.println(joueurX.getNom()+" prend place a la table de jeu avec "+joueurX.getBesaceInit()+" euros.");
	}
	
	/**
	 * Fonction qui permet de supprimer un joueur de la liste des joueurs.
	 * @param joueurY
	 */
	public void removeJoueur(Joueur joueurY){
		System.out.println(joueurY.getNom()+" quitte la table avec "+joueurY.getBesace()+" euros.");
		this.joueurs.remove(joueurY);
	}
	
	/**
	 * Fonction qui permet de retourner la liste des joueurs présents autour de la table.
	 * @return la liste des joueurs.
	 */
	public ArrayList<Joueur> getJoueurs(){
		return this.joueurs;
	}
	
	/**
	 * Fonction qui modifie la liste des joueurs (initialisation de la liste).
	 * @param joueurs
	 */
	public void setJoueurs(ArrayList<Joueur> joueurs){
		for(Joueur j:joueurs){
			this.addJoueur(j);
		}
	}


	/**
	 * Fonction qui permet de retourner la liste des tirages.
	 * @return la liste des tirages.
	 */
	public ArrayList<Numero> getTirages() {
		return tirages;
	}
	
	/**
	 * Fonction qui retourne la roulette
	 * @return roulette
	 */
	public Roulette getRoulette(){
		return roulette;
	}
}

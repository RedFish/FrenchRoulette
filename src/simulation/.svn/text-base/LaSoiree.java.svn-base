package simulation;

import java.util.ArrayList;

import exception.BesaceInsuffisanteException;
import exception.MiseMaxException;

import jeu.*;
import joueur.Joueur;
import joueur.JoueurESIAL;

public class LaSoiree {

	public LaSoiree(){
		//On initialise la somme que les étuduants sont pret à jouer
		int besace_initiale = 72;

		//On crée les 7 joueurs ESIAL
		ArrayList<Joueur> joueur_esial = new ArrayList<Joueur>();
		for(int i=0; i<7; i++){
			try {
				joueur_esial.add(new JoueurESIAL("Joueur ESIAL "+i, besace_initiale));
			} catch (BesaceInsuffisanteException e) {
				e.printStackTrace();
			}
		}


		//On instantie une nouvelle table de jeu
		Table table = new Table();
		//Les joueurs s'assoient à la table
		table.setJoueurs(joueur_esial);

		//Début de la partie
		int gain=0;
		while(gain<56 && table.getJoueurs().size() > 0){
			System.out.println("\n***** Nouvelle partie *****");
			try {
				table.miser();
			} catch (MiseMaxException e) {
				e.printStackTrace();
			} catch (BesaceInsuffisanteException e) {
				e.printStackTrace();
			}
			table.tirage();
			table.gains();
			gain=0;
			for(Joueur j:joueur_esial){
				if(table.getJoueurs().contains(j)){
					//Si un étudiant a gagné 8 euros il s'arrete de jouer
					if(j.getBesace()>=(besace_initiale+8)){
						System.out.println(j.getNom()+" a atteint son objectif de 8 euros.");
						table.removeJoueur(j);
					}
					else if(j.getBesace()==0){
						System.out.println(j.getNom()+" a tout perdu.");
						table.removeJoueur(j);
					}
					else if(!(((JoueurESIAL) j).peutDoubler())){
						System.out.println(j.getNom()+" ne peut plus doubler.");
						table.removeJoueur(j);
					}
				}
				gain+=j.gainPerte();
			}
			//
		}
		if(gain>=56){
			System.out.println("\nLes etudiants de l'ESIAL peuvent donc payer l'essence et aller au cinema avec les gain du casino.");
		}
		else{
			System.out.println("\nL'objectif n'est pas atteint:");
			ArrayList<Integer> l_j = new ArrayList<Integer>();
			for(Joueur j:joueur_esial){
				System.out.println(j.getNom()+" sort du casino avec "+j.getBesace()+" euros.");
				if(j.getBesace()<80){
					l_j.add(joueur_esial.indexOf(j));
				}
			}
			
			String s="";
			if(l_j.size()>1){
				s = "Les etudiants de l'ESIAL ";
			}
			else{
				s = "L'etudiant de l'ESIAL ";
			}
			
			for(int i:l_j){
				s=s+i;
				if(l_j.indexOf(i)<l_j.size()-2) s=s+", ";
				else if(l_j.indexOf(i)==l_j.size()-2) s=s+" et ";
			}
			
			if(l_j.size()>1){
				s = s+ " doivent";
			}
			else{
				s = s+ " doit";
			}
			s=s+" donc debourser 8 euros pour les frais de transport et le billet de cinema en plus des pertes au casino.";
			System.out.println(s);
		}
	}

	public static void main(String[] args) {
		new LaSoiree();
	}

}

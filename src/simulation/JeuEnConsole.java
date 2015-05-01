package simulation;

import java.util.Scanner;

import combinaison.Combinaison;

import exception.BesaceInsuffisanteException;
import exception.MiseMaxException;

import jeu.Table;
import joueur.JoueurHumain;

public class JeuEnConsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner clavier = new Scanner(System.in);
		Table table = new Table();
		String rep = "o";

		//Saisie du nom
		System.out.print("Entrer votre nom : ");
		String nom = clavier.next();
		clavier.nextLine();

		//Saisie de la besace
		int besace=1;
		do{
			if(besace<1){
				System.out.println("LA BESACE DE DEPART DOIT ETRE COMPRISE ENTRE 1 et "+Integer.MAX_VALUE);
			}
			System.out.print("Entrer la besace initiale : ");
			try{
				besace = Integer.decode(clavier.nextLine());
			}
			catch(Exception e){ besace = -1; }
		} while(besace<1);
		try {
			//On déclare le joueur et on l'ajoute à la table
			JoueurHumain jh = new JoueurHumain(nom, besace);
			table.addJoueur(jh);

			boolean rester;
			do{
				rester = true;
				System.out.println("\n-----");
				System.out.println("CROUPIER: Faites vos jeux!");
				System.out.println("-----");
				boolean new_mise;
				do{
					new_mise=true;

					try {
						table.miser();
					} catch (MiseMaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if(table.getJoueurs().get(0).getBesace()>0){
						do{
							if(!(rep.charAt(0)=='o') && !(rep.charAt(0)=='n')){
								System.out.println("ERREUR DE SAISIE!");
							}
							System.out.print("Nouvelle mise (o|n) ? ");
							try{
								rep = clavier.next();
							}
							catch(Exception e){}
						} while(!((rep.charAt(0)=='o') || (rep.charAt(0)=='n')));
						if(rep.charAt(0)=='n'){new_mise=false;}
					}
					else{
						new_mise=false;
					}
				} while(new_mise);

				System.out.println("\n-----");
				System.out.println("CROUPIER: Rien ne va plus!");
				System.out.println("-----\n");

				System.out.println("Récapitulatif des mises:");
				for(Combinaison c:table.getJoueurs().get(0).getMises()){
					System.out.println("\t"+c+"  :  "+c.getMise()+" euros");
				}
				System.out.println();
				table.tirage();
				table.gains();

				if(table.getJoueurs().get(0).getBesace()>0){
					rep = "o";
					do{
						if(!(rep.charAt(0)=='o') && !(rep.charAt(0)=='n')){
							System.out.println("ERREUR DE SAISIE!");
						}
						System.out.print("\nCommencer une nouvelle partie (o|n) ? ");
						try{
							rep = clavier.next();
						}
						catch(Exception e){}
					} while(!((rep.charAt(0)=='o') || (rep.charAt(0)=='n')));
					if(rep.charAt(0)=='n'){rester = false;}
				}
				else{rester = false;}
			}while(rester);
			System.out.println();
			table.removeJoueur(jh);
		} catch (BesaceInsuffisanteException e) {
			e.printStackTrace();
		}

	}

}

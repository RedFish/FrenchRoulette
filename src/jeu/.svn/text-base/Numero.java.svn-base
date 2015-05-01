package jeu;

/**
 * Classe Numero qui permet de déclarer les numéros constituant la roulette et ceux sur lesquels on peut parier.
 * @author Richard GUERCI
 */
public class Numero {
	private int valeur;
	private String couleur;
	//commentaire
	
	/**
	 * Le constructeur permet à partir d'une valeur de définir automatiquement la couleur qui lui correspond.
	 * @param valeur indique la valeur que va prendre le numero.
	 */
	public Numero(int valeur) {
		this.valeur = valeur; //La valeur est initialisée.
		//On déclare la 'liste' des nombres étant de couleur rouge.
		int rouge[] = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
		if(this.valeur==0){ //si la valeur du Numero est zero alors on initialise la couleur à "Vert".
			this.couleur = "Vert";
		}
		//Sinon si la valeur du nombre appartient à la 'liste' des nombres de couleur rouge alors on initialise la couleur à "Rouge".
		else if(java.util.Arrays.binarySearch(rouge, valeur) >= 0){
			this.couleur = "Rouge";
		}
		else{ //Sinon la couleur à attribuer est "Noir".
			this.couleur = "Noir";
		}
	}
	
	/**
	 * Fonction qui retourne la valeur du Numero
	 * @return valeur la valeur du Numero
	 */
	public int getValeur(){
		return this.valeur;
	}
	
	/**
	 * Fonction qui retourne la couleur sous forme de chaine de caractères
	 * @return couleur la couleur du Numero
	 */
	public String getCouleur(){
		return this.couleur;
	}
	
	/**
	 * Fonction qui retourne vrai si le Nombre est de couleur rouge
	 * @return boolean ˆ vrai si c'est rouge, faux sinon
	 */
	public boolean estRouge(){
		return (this.couleur=="Rouge");
	}

	/**
	 * Fonction qui retourne vrai si le Nombre est de couleur noir
	 * @return boolean à vrai si c'est noir, faux sinon
	 */
	public boolean estNoir(){
		return (this.couleur=="Noir");
	}

	/**
	 * Fonction qui retourne vrai si le Nombre est de couleur verte
	 * @return boolean à vrai si c'est vert, faux sinon
	 */
	public boolean estVert(){
		return (this.couleur=="Vert");
	}

	/**
	 * Fonction qui retourne vrai si le Nombre est pair
	 * Le zéro est exclu
	 * @return boolean à vrai si c'est pair
	 */
	public boolean estPair(){
		if(this.valeur==0) return false;
		return (this.valeur%2==0);
	}
	
	/**
	 * Fonction qui retourne vrai si le Nombre est impair
	 * @return boolean à vrai si c'est impair
	 */
	public boolean estImpair(){
		return (this.valeur%2==1);
	}
	
	/**
	 * Fonction qui retourne vrai si le Nombre est passe valeur>18
	 * @return boolean à vrai si la valeur est > 18
	 */
	public boolean estPasse(){
		return (this.valeur>18);
	}
	
	/**
	 * Fonction qui retourne vrai si le Nombre est passe valeur>18
	 * @return boolean à vrai si 0<valeur<19
	 */
	public boolean estManque(){
		return (this.valeur<=18 && this.valeur>0);
	}
	
	public boolean equals(Object o){
		if(!(o instanceof Numero)){
			return false;
		}
		else if (o == this) {
			return true;
		}
		else {
			Numero n = (Numero) o;
			return (this.getValeur()==n.getValeur()&&this.getCouleur()==n.getCouleur());
		}
		
	}
	
	/**
	 * Fonction toString() qui permet de retourner ce que le croupier dit en fonction du numéro
	 * @param chaine de caractères
	 */
	public String toString(){
		String str = "Le ";
		str=Integer.toString(this.getValeur());
		if(this.estRouge()){
			str+=" rouge";
		}
		else if (this.estNoir()){
			str+=" noir";
		}
		
		if(this.estPair()){
			str+=" pair";
		}
		else if (this.estPasse()){
			str+=" impair";
		}
		if(this.estPasse()){
			str+=" et passe";
		}
		else if(this.estManque()){
			str+=" et manque";
		}
		return str;
	}
}

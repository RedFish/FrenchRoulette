package combinaison;

import java.util.ArrayList;

import exception.MiseMaxException;

import jeu.Numero;

public abstract class Combinaison {
	private ArrayList<Numero> numeros = new ArrayList<Numero>();
	private int multiplicateur;
	private int mise;
	private int mise_minimum;
	private int mise_maximum;

	public Combinaison() {
		setMise_minimum(1);
		setMise_maximum(1000);
		mise=0;
	}

	public Combinaison(int mise_maxi,int multiplicateur,int mise) throws MiseMaxException {
		if(mise>mise_maxi){
			throw new MiseMaxException();
		}
		else{
			setMise_minimum(1);
			setMise_maximum(mise_maxi);
			setMultiplicateur(multiplicateur);
			setMise(mise);
		}
	}

	public void addNumero(Numero n){
		numeros.add(n);
	}

	public int getMultiplicateur() {
		return multiplicateur;
	}

	public void setMultiplicateur(int multiplicateur) {
		this.multiplicateur = multiplicateur;
	}

	public ArrayList<Numero> getNumeros() {
		return numeros;
	}

	public void setNumeros(ArrayList<Numero> numeros) {
		this.numeros = numeros;
	}

	public boolean contient(Numero n){
		return numeros.contains(n);
	}

	public int gain(Numero n) {
		if(numeros.contains(n)) return getMise()*getMultiplicateur();
		return 0;
	}



	public int getMise() {
		return this.mise ;
	}

	public void setMise(int mise) throws MiseMaxException {
		if(mise>mise_maximum){
			throw new MiseMaxException();
		}
		else{
			this.mise = mise;
		}
	}

	public int getMise_minimum() {
		return mise_minimum;
	}

	public void setMise_minimum(int mise_minimum) {
		this.mise_minimum = mise_minimum;
	}

	public int getMise_maximum() {
		return mise_maximum;
	}

	public void setMise_maximum(int mise_maximum) {
		this.mise_maximum = mise_maximum;
	}
	
	public boolean equals(ArrayList<Numero> nums){
		if(numeros.size()!=nums.size()) return false;
		for(int i=0; i<numeros.size();i++){
			if(!numeros.get(i).equals(nums.get(i))) return false;
		}
		return true;
	}
}

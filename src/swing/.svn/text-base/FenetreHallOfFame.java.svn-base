package swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


import jeu.Numero;
import jeu.Table;

public class FenetreHallOfFame extends JFrame {
	private ArrayList<JLabel> labels_tirage;
	private ArrayList<Numero> tirages;
	private JPanel panel_historique;
	private JPanel panel_stat;
	private JPanel panel_stat1;
	GridLayout grid;
	JLabel tab_label[];
	
	public FenetreHallOfFame(ArrayList<Numero> t){
		setTitle("Hall of Fame");
		setSize(375, 270);
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		//on récupere la taille de l'écran
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)(dimension.getHeight()/2)-200;
		int width  = (int)(dimension.getWidth()/2)+400;
		this.setLocation(width, height);
		
		
		tirages=t;
		labels_tirage = new ArrayList<JLabel>();
		
		JLayeredPane panel_principal = new JLayeredPane(); 
		
		
		//Gestion du premier panel de numéro
		grid = new GridLayout(10,1);
		panel_historique = new JPanel(new GridLayout(10,1));
		panel_historique.setBounds(10, 8, 90, 230);
		TitledBorder borderP1 = BorderFactory.createTitledBorder("Numéros");
		panel_historique.setBorder(borderP1);
		panel_principal.add(panel_historique);
		
		//Gestion du second panel 'statistique'
		panel_stat = new JPanel();
		panel_stat.setBounds(110, 8, 255, 230);
		TitledBorder borderP2 = BorderFactory.createTitledBorder("Statistiques");
		panel_stat.setBorder(borderP2);
		panel_principal.add(panel_stat);
		
		panel_stat1 = new JPanel(new GridLayout(8,2,5,8));
		
		tab_label = new JLabel[8*2];
		tab_label[0] = new JLabel("Zero");
		tab_label[1] = new JLabel("%");
		tab_label[1].setHorizontalAlignment(JLabel.RIGHT);
		tab_label[2] = new JLabel("Rouge");
		tab_label[3] = new JLabel("%");
		tab_label[3].setHorizontalAlignment(JLabel.RIGHT);
		tab_label[4] = new JLabel("Noir");
		tab_label[5] = new JLabel("%");
		tab_label[5].setHorizontalAlignment(JLabel.RIGHT);
		tab_label[6] = new JLabel("Manque");
		tab_label[7] = new JLabel("%");
		tab_label[7].setHorizontalAlignment(JLabel.RIGHT);
		tab_label[8] = new JLabel("Passe");
		tab_label[9] = new JLabel("%");
		tab_label[9].setHorizontalAlignment(JLabel.RIGHT);
		tab_label[10] = new JLabel("Pair");
		tab_label[11] = new JLabel("%");
		tab_label[11].setHorizontalAlignment(JLabel.RIGHT);
		tab_label[12] = new JLabel("Impair");
		tab_label[13] = new JLabel("%");
		tab_label[13].setHorizontalAlignment(JLabel.RIGHT);
		tab_label[14] = new JLabel("Nombre de tirages");
		tab_label[15] = new JLabel("%");
		tab_label[15].setHorizontalAlignment(JLabel.RIGHT);
		
		for(int i = 0; i< tab_label.length; i++){
			panel_stat1.add(tab_label[i]);
		}

		panel_stat.add(panel_stat1);
		refresh();
		

		add(panel_principal);
	}
	
	//mise a jour de la liste des derniers numero dans le Hall Of Fame
	public void refresh(){
		for(JLabel l:labels_tirage){
		grid.removeLayoutComponent(l);
		}
		panel_historique.removeAll();
		labels_tirage.clear();
		
		for(int i=tirages.size()-1; i>=Math.max(tirages.size()-10,0); i--){
			Numero n = tirages.get(i);
			JLabel label;
			if(n.estRouge()){
				label = new JLabel(" "+n.getValeur());
				label.setForeground(new Color(220,0,0));
			}
			else if(n.estVert()){
				label = new JLabel(""+n.getValeur());
				label.setForeground(new Color(0,130,0));
				label.setHorizontalAlignment(JLabel.CENTER);
			}
			else{
				label = new JLabel(n.getValeur()+" ");
				label.setHorizontalAlignment(JLabel.RIGHT);
			}
			//on met en gras
			Font newLabelFont=new Font(label.getFont().getName(),Font.BOLD,label.getFont().getSize());  
			label.setFont(newLabelFont);
			
			if(i==tirages.size()-1){
				labels_tirage.add(new BlinkingLabel(label, Color.GRAY));
			}
			else{
				labels_tirage.add(label);
			}
		}
		
		for(JLabel l:labels_tirage){
			panel_historique.add(l);
		}
		panel_historique.repaint();
		
		//calcul des pourcentages.
		int tab_occurance[] = new int[37];
		for(Numero n:tirages){
			tab_occurance[n.getValeur()]++;
		}
		int total = 0;
		int nb_pair = 0;
		int nb_impair = 0;
		int nb_manque = 0;
		int nb_passe = 0;
		int nb_rouge = 0;
		int nb_noir = 0;
		int nb_zero = 0;
		for(int i=0;i<tab_occurance.length;i++){
			total+=tab_occurance[i];
			
			Numero n = new Numero(i);
			if(n.estPair()){nb_pair+=tab_occurance[i];}
			else if (n.estImpair()){nb_impair+=tab_occurance[i];}
			
			if(n.estManque()){nb_manque+=tab_occurance[i];}
			else if(n.estPasse()){nb_passe+=tab_occurance[i];}
			
			if(n.estRouge()){nb_rouge+=tab_occurance[i];}
			else if(n.estNoir()){nb_noir+=tab_occurance[i];}
			else if(n.estVert()){nb_zero+=tab_occurance[i];}
		}
		if(total == 0){
			total=1;
			//Nombre de tirage
			tab_label[15].setText("0");
		}
		else{
			//Nombre de tirage
			tab_label[15].setText(total+"");
		}

		//Zero
		tab_label[1].setText(((double)(int)((((double) nb_zero/(double) total))*100*100))/100+" %");
		//Rouge
		tab_label[3].setText(((double)(int)((((double) nb_rouge/(double) total))*100*100))/100+" %");
		//Noir
		tab_label[5].setText(((double)(int)((((double) nb_noir/(double) total))*100*100))/100+" %");
		//Manque
		tab_label[7].setText(((double)(int)((((double) nb_manque/(double) total))*100*100))/100+" %");
		//Passe
		tab_label[9].setText(((double)(int)((((double) nb_passe/(double) total))*100*100))/100+" %");
		//Pair
		tab_label[11].setText(((double)(int)((((double) nb_pair/(double) total))*100*100))/100+" %");
		//Impair
		tab_label[13].setText(((double)(int)((((double) nb_impair/(double) total))*100*100))/100+" %");
		
		
		this.validate();
	}
	
	public void afficherMasquer(){
		this.setVisible(!this.isVisible());
	}
	
	public static void main(String[] args) {
		//On instantie une nouvelle table de jeu
		Table table = new Table();
		for(int i = 0; i<10 ; i++){
			table.tirage();
		}
		FenetreHallOfFame h = new FenetreHallOfFame(table.getTirages());

		Scanner sc = new Scanner(System.in);
		while(!sc.next().equals("azerftghyjk")){
			table.tirage();
			h.refresh();
		}
	}
}

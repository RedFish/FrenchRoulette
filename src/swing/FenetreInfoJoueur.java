package swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import combinaison.ChanceCarre;
import exception.BesaceInsuffisanteException;
import exception.MiseMaxException;

import joueur.Joueur;
import joueur.JoueurAlembert;
import joueur.JoueurESIAL;
import joueur.JoueurHawks;

public class FenetreInfoJoueur extends JFrame implements ActionListener {
	JLabel tab_label[];
	Joueur joueur;
	JButton bouton_retour;
	JLabel label_si_mise;
	JTable tableau;
	UneditableTableModel model_tableau;
	JScrollPane tableau_scroll;
	JLayeredPane panel_mises;
	Border border_line;

	public FenetreInfoJoueur(Joueur j){
		joueur=j;
		setTitle("Information du joueur : "+j.getNom());
		setSize(375, 405);
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		//on récupere la taille de l'écran
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		Random r = new Random();
		int height = (int)(dimension.getHeight()/2)-370+r.nextInt(100);
		int width  = (int)(dimension.getWidth()/2)-775+r.nextInt(100);
		this.setLocation(width, height);

		JLayeredPane panel_principal = new JLayeredPane(); 

		ImagePanel panel_image;
		if(j instanceof JoueurESIAL){
			panel_image = new ImagePanel(new ImageIcon(getClass().getResource("logo_esial.jpeg")).getImage());
		}
		else if(j instanceof JoueurHawks){
			panel_image = new ImagePanel(new ImageIcon(getClass().getResource("logo_hawk.jpeg")).getImage());
		}
		else if(j instanceof JoueurAlembert){
			panel_image = new ImagePanel(new ImageIcon(getClass().getResource("logo_dalembert.jpeg")).getImage());
		}
		else{
			panel_image = new ImagePanel(new ImageIcon(getClass().getResource("logo_profil.jpeg")).getImage());
		}
		border_line = BorderFactory.createLineBorder(Color.GRAY);
		panel_image.setBorder(border_line);
		panel_image.setBounds(30, 40, 100, 100);
		panel_principal.add(panel_image);

		//Gestion du premier panel du profil
		JLayeredPane panel_profil = new JLayeredPane();
		panel_profil.setBounds(10, 8, 355, 155);
		TitledBorder borderP1 = BorderFactory.createTitledBorder("Profil du joueur");
		panel_profil.setBorder(borderP1);
		panel_principal.add(panel_profil);

		//panel d'info
		JPanel panel_info = new JPanel(new GridLayout(3,2));
		panel_info.setBounds(140, 35, 190, 100);
		tab_label = new JLabel[6];
		tab_label[0] = new JLabel("Nom du joueur");
		tab_label[1] = new JLabel(joueur.getNom());
		tab_label[1].setHorizontalAlignment(JLabel.RIGHT);
		tab_label[2] = new JLabel("Besace");
		tab_label[3] = new JLabel(joueur.getBesace()+" €");
		tab_label[3].setHorizontalAlignment(JLabel.RIGHT);
		tab_label[4] = new JLabel("Gains/Pertes");
		tab_label[5] = new JLabel(joueur.gainPerte()+" €");
		tab_label[5].setHorizontalAlignment(JLabel.RIGHT);
		Font labelFontBold =new Font(tab_label[0].getFont().getName(),Font.BOLD,tab_label[0].getFont().getSize());
		tab_label[1].setFont(labelFontBold);
		tab_label[3].setFont(labelFontBold);
		tab_label[5].setFont(labelFontBold);
		for(int i = 0; i< tab_label.length; i++){
			panel_info.add(tab_label[i]);
		}
		panel_profil.add(panel_info);

		//Gestion du premier panel du profil
		panel_mises = new JLayeredPane();
		panel_mises.setBounds(10, 170, 355, 155);
		TitledBorder borderP2 = BorderFactory.createTitledBorder("Mises du joueur");
		panel_mises.setBorder(borderP2);
		panel_principal.add(panel_mises);

		//label
		label_si_mise = new JLabel("Il n'y a pas de mise");
		Font labelFontBoldMise =new Font(label_si_mise.getFont().getName(),Font.BOLD,label_si_mise.getFont().getSize()+1);
		label_si_mise.setFont(labelFontBoldMise);
		label_si_mise.setBounds(110, 70, 180, 18);

		refresh();


		//Gestion du bouton de retour
		bouton_retour = new JButton("Fermer");
		bouton_retour.setBounds(10, 335, 355, 40);
		bouton_retour.addActionListener(this);
		panel_principal.add(bouton_retour);



		add(panel_principal);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == bouton_retour){
			this.setVisible(false);
		}
	}


	public void refresh() {
		//on supprime tous les objets du panel
		panel_mises.removeAll();

		//maj de la besace
		tab_label[3].setText(joueur.getBesace()+" €");
		//maj des gains/perts
		tab_label[5].setText(joueur.gainPerte()+" €");
		//mise en couleur du label en fonction des gains ou des pertes
		if(joueur.gainPerte()<0){
			tab_label[5].setForeground(new Color(220,0,0));
		}
		else if(joueur.gainPerte()>0){
			tab_label[5].setForeground(new Color(0,130,0));
		}
		else{
			tab_label[5].setForeground(new Color(0,0,0));
		}

		//maj du tableau
		String[] entetes = {"Type de la mise", "Mise (€)"};
		Object[][] donnees = new Object[joueur.getMises().size()][2];
		for(int i=0;i<joueur.getMises().size();i++){
			donnees[i][0]=joueur.getMises().get(i);
			donnees[i][1]=joueur.getMises().get(i).getMise();
		}
		model_tableau = new UneditableTableModel(donnees,entetes);
		tableau = new JTable();
		tableau.setModel(model_tableau);
		tableau.setAutoCreateRowSorter(true);
		tableau_scroll = new JScrollPane(tableau);
		tableau_scroll.setBounds(22, 23, 310, 116);
		tableau_scroll.setBorder(border_line);

		panel_mises.add(label_si_mise);
		panel_mises.add(tableau_scroll);
		label_si_mise.setVisible(false);
		tableau_scroll.setVisible(false);
		//on affiche soit le label soit les mises (s'il y en a)
		if(joueur.getMises().size()==0){
			label_si_mise.setVisible(true);
		}
		else{
			tableau_scroll.setVisible(true);
		}

		this.validate();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//tests
		JoueurHawks je;
		try {
			je = new JoueurHawks("Bogy",130);
			
			//une liste de mis

			je.miserCarre(2, ChanceCarre._13_14_16_17);
			je.miserRouge(1);
			je.miserRouge(1);
			je.miserP(1);
			je.miserManque(1);
			je.miserNoir(1);
			je.miserRouge(1);
			je.miserC2(30);
			je.miserRouge(50);
			je.miserCarre(2, ChanceCarre._13_14_16_17);
			FenetreInfoJoueur f = new FenetreInfoJoueur(je);
			f.setVisible(true);
			Scanner sc = new Scanner(System.in);
			while(!sc.next().equals("azerftghyjk")){
				je.miserPlein(15, 10);
				f.refresh();
			}
		} catch (BesaceInsuffisanteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MiseMaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Joueur getJoueur(){
		return joueur;
	}

}

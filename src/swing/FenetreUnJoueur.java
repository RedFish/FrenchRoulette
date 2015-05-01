package swing;


import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableColumn;
import javax.swing.*;


import combinaison.ChanceCarre;
import combinaison.ChanceCheval;
import combinaison.ChanceSixain;
import combinaison.ChanceTransversale;

import exception.BesaceInsuffisanteException;
import exception.MiseMaxException;


import jeu.Table;
import joueur.Joueur;
import joueur.JoueurAlembert;
import joueur.JoueurESIAL;
import joueur.JoueurHawks;
import joueur.JoueurHumain;

@SuppressWarnings("serial")
public class FenetreUnJoueur extends JFrame implements ActionListener, ChangeListener, MouseListener{
	//Objet utiliser pour le jeu
	Table table;
	int mise;
	//Composants principaux
	JLayeredPane panel_principal;
	JLayeredPane panel_joueurs;
	JLayeredPane panel_mises;
	JLayeredPane panel_console;
	JLayeredPane panel_table;
	JLayeredPane panel_info;
	JLayeredPane panel_bouton;
	//Composants info joueur
	JLabel label_besace;
	JLabel label_gain_perte;
	JButton bouton_euros;
	JSlider curseur;
	JLabel label_mise;
	//Composants pour afficher les joueurs
	JLabel label_si_pas_de_joueurs;
	JTable tableau_joueurs;
	UneditableTableModel model_tableau_joueurs;
	Border border_line;
	JScrollPane tableau_scroll;
	//Composants des mises
	JTable tableau_mes_mises;
	UneditableTableModel model_tableau_mises;
	JScrollPane tableau_scroll_mises;
	JLabel label_si_pas_de_mise;
	//Composants des numeros/paris
	JLayeredPane panel_numeros;
	JTable tableau_numeros;
	UneditableTableModel model_tableau_numeros;
	JLayeredPane panel_0;
	JTable tableau_0;
	UneditableTableModel model_tableau_0;
	JLayeredPane panel_12n;
	JTable tableau_12n;
	UneditableTableModel model_tableau_12n;
	JLayeredPane panel_chance_simple;
	JTable tableau_chance_simple;
	UneditableTableModel model_tableau_chance_simple;
	//Composants boutons
	JButton bouton_halloffame;
	JButton bouton_calibrage;
	JButton bouton_fermer;
	JButton bouton_lancer;
	JButton bouton_annuler;


	//contient toute les fenetres des joueurs de la table
	ArrayList<FenetreInfoJoueur> fenetres_info_joueur;
	FenetreHallOfFame fenetre_hall_of_fame;
	FenetreCalibration fenetre_calibrage;
	//Console
	JTextArea txt_console;
	JScrollPane sbrText;
	//Jetons
	ArrayList<ImagePanel> jetons;

	public FenetreUnJoueur(){
		//On initialise la table
		table = new Table();
		mise=1;
		jetons = new ArrayList<ImagePanel>();

		//initialisation des fenetres
		fenetres_info_joueur = new ArrayList<FenetreInfoJoueur>();
		fenetre_hall_of_fame = new FenetreHallOfFame(table.getTirages());
		fenetre_calibrage = new FenetreCalibration(table.getRoulette());

		//Initialisation de la fenetre
		setTitle("Roulette Française");
		setSize(1045,695);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		panel_principal=new JLayeredPane() ;
		panel_joueurs=new JLayeredPane();
		panel_mises=new JLayeredPane();
		panel_console=new JLayeredPane();
		panel_table=new  JLayeredPane();
		panel_info = new JLayeredPane();
		panel_bouton = new JLayeredPane();


		//Gestion du panel console;
		panel_console.setBounds(740, 425, 300, 240);
		panel_principal.add(panel_console);
		TitledBorder border_panel_console =BorderFactory.createTitledBorder("Console");
		panel_console.setBorder(border_panel_console);
		// Create an instance of javax.swing.JTextArea control
		txt_console = new JTextArea();
		txt_console.setEditable(false);
		// Now create a new TextAreaOutputStream to write to our JTextArea control and wrap a
		// PrintStream around it to support the println/printf methods.
		PrintStream out = new PrintStream( new TextAreaOutputStream( txt_console ) );
		// redirect standard output stream to the TextAreaOutputStream
		System.setOut(out);		
		txt_console.setLineWrap(true);
		sbrText = new JScrollPane(txt_console);
		sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sbrText.setBounds(25, 30, 250, 190);
		panel_console.add(sbrText);

		//on ajoute des joueur et on les fait miser
		try {
			JoueurHumain joueur1 = new JoueurHumain("Joueur 1",100);
			table.addJoueur(joueur1);
		} catch (BesaceInsuffisanteException e1) {}
		Random r = new Random();
		ajouterJoueurs(r.nextInt(10));

		try {
			for(int i=1;i<table.getJoueurs().size();i++){
				table.getJoueurs().get(i).miser();
			}
		}catch (MiseMaxException e1) {} catch (BesaceInsuffisanteException e1) {}
		refreshFenetreInfoJoueur();

		//Gestion info joueur
		panel_info.setBounds(5, 5, 300, 220);
		panel_principal.add(panel_info);
		TitledBorder border_panel_info = BorderFactory.createTitledBorder("Info : "+table.getJoueurs().get(0).getNom());
		panel_info.setBorder(border_panel_info);
		ImagePanel panel_image = new ImagePanel(new ImageIcon(getClass().getResource("logo_profil.jpeg")).getImage());
		Border border_line_img = BorderFactory.createLineBorder(Color.GRAY);
		panel_image.setBorder(border_line_img);
		panel_image.setBounds(20, 35, 100, 100);
		panel_info.add(panel_image);
		JLabel label_nombesace = new JLabel("Besace");
		label_nombesace.setBounds(135, 60, 50, 15);
		JLabel label_gainperte = new JLabel("Gains / Pertes");
		label_gainperte.setBounds(135, 100, 100, 15);
		panel_info.add(label_nombesace);
		panel_info.add(label_gainperte);
		label_besace = new JLabel(table.getJoueurs().get(0).getBesace()+" €");
		label_besace.setBounds(230, 60, 50, 15);
		label_gain_perte = new JLabel(table.getJoueurs().get(0).gainPerte()+" €");
		label_gain_perte.setBounds(230, 100, 50, 15);
		Font labelFontBold =new Font(label_besace.getFont().getName(),Font.BOLD,label_besace.getFont().getSize());
		label_besace.setFont(labelFontBold);
		label_gain_perte.setFont(labelFontBold);
		label_besace.setHorizontalAlignment(JLabel.RIGHT);
		label_gain_perte.setHorizontalAlignment(JLabel.RIGHT);
		bouton_euros = new JButton("+ 100 €");
		bouton_euros.setBounds(20, 160, 100, 25);
		bouton_euros.addActionListener(this);
		panel_info.add(bouton_euros);
		curseur = new JSlider(1, table.getJoueurs().get(0).getBesace(), 1);
		curseur.setBounds(130, 130, 160, 50);
		curseur.setMajorTickSpacing(20);
		curseur.setPaintTicks(true);Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer( 1 ), new JLabel("1 €") );
		labelTable.put(new Integer( table.getJoueurs().get(0).getBesace() ), new JLabel(table.getJoueurs().get(0).getBesace()+" €") );
		curseur.setLabelTable( labelTable );
		curseur.setPaintLabels(true);
		curseur.addChangeListener(this);
		JLabel label_nmise = new JLabel("montant de la mise : ");
		label_nmise.setBounds(135, 185, 150, 15);
		Font labelFontBold1 =new Font(label_nmise.getFont().getName(),Font.PLAIN,label_nmise.getFont().getSize()-2);
		label_nmise.setFont(labelFontBold1);
		panel_info.add(label_nmise);
		label_mise = new JLabel(curseur.getValue()+" €");
		label_mise.setBounds(180, 185, 100, 15);
		Font labelFontBold2 =new Font(label_mise.getFont().getName(),Font.BOLD,label_mise.getFont().getSize()-2);
		label_mise.setFont(labelFontBold2);
		label_mise.setHorizontalAlignment(JLabel.RIGHT);
		label_mise.setFont(labelFontBold1);
		panel_info.add(label_mise);

		panel_info.add(curseur);
		panel_info.add(label_besace);
		panel_info.add(label_gain_perte);

		//Gestion du panel_joueurs;
		panel_joueurs.setBounds(5,445,300,220);
		panel_principal.add(panel_joueurs);
		TitledBorder border_panel_joueur = BorderFactory.createTitledBorder("Joueurs");
		panel_joueurs.setBorder(border_panel_joueur);
		String[] entetes = {"joueurs", "besace (€)","information"};
		Object[][] donnees = new Object[table.getJoueurs().size()-1][3];
		for(int i=1;i<table.getJoueurs().size();i++){
			donnees[i-1][0]=table.getJoueurs().get(i).getNom();
			donnees[i-1][1]=table.getJoueurs().get(i).getBesace();
			donnees[i-1][2]="Afficher/Masquer";
		}
		model_tableau_joueurs = new UneditableTableModel(donnees,entetes);
		tableau_joueurs=new JTable();
		tableau_joueurs.setModel(model_tableau_joueurs);
		tableau_joueurs.getColumn("information").setCellRenderer(new ButtonRenderer());//on donne l'aspect d'un bouton au cellules de la colonne information
		tableau_joueurs.addMouseListener(new MouseAdapter() {//on appel la methode pour afficher un ou masque la fenetreInfoJueur s'il y a un clic
			public void mouseClicked(MouseEvent e) {
				int row = tableau_joueurs.getSelectedRow();
				int column = tableau_joueurs.getSelectedColumn();
				afficherMasquerInfoJoueur(row,column);
			}
		});
		tableau_joueurs.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableau_joueurs.getColumnModel().getColumn(1).setPreferredWidth(10);
		tableau_scroll = new JScrollPane(tableau_joueurs);
		tableau_scroll.setBounds(25, 30, 250, 170);
		tableau_scroll.setBorder(border_line);
		//label si pas d'autres joueurs
		label_si_pas_de_joueurs = new JLabel("Il n'y a pas d'autres joueurs sur la table!");
		Font labelFontBoldMise =new Font(label_si_pas_de_joueurs.getFont().getName(),Font.BOLD,label_si_pas_de_joueurs.getFont().getSize()+1);
		label_si_pas_de_joueurs.setFont(labelFontBoldMise);
		label_si_pas_de_joueurs.setBounds(22,100, 270, 18);
		if(table.getJoueurs().size()==1){
			label_si_pas_de_joueurs.setVisible(true);
			tableau_scroll.setVisible(false);
		}
		else{
			label_si_pas_de_joueurs.setVisible(false);
			tableau_scroll.setVisible(true);
		}

		panel_joueurs.add(label_si_pas_de_joueurs);
		panel_joueurs.add(tableau_scroll);




		//Gestion du panel_mises;
		panel_mises.setBounds(5,225,300,220);
		panel_principal.add(panel_mises);
		TitledBorder border_panel_mises =BorderFactory.createTitledBorder("Mises");
		panel_mises.setBorder(border_panel_mises);
		String[] entetes_mises = {"Type de la mise", "Mise (€)"};
		Object[][] donnees_mises = new Object[table.getJoueurs().get(0).getMises().size()][2];
		for(int i=0;i<table.getJoueurs().get(0).getMises().size();i++){
			donnees_mises[i][0]=table.getJoueurs().get(0).getMises().get(i);
			donnees_mises[i][1]=table.getJoueurs().get(0).getMises().get(i).getMise();
		}
		model_tableau_mises = new UneditableTableModel(donnees_mises,entetes_mises);
		tableau_mes_mises = new JTable();
		tableau_mes_mises.setModel(model_tableau_mises);
		tableau_mes_mises.setAutoCreateRowSorter(true);
		tableau_scroll_mises = new JScrollPane(tableau_mes_mises);
		tableau_scroll_mises.setBounds(25, 30, 250, 170);
		tableau_scroll_mises.setBorder(border_line);
		panel_mises.add(tableau_scroll_mises);
		//Label si pas de mise
		label_si_pas_de_mise = new JLabel("Pas de mises!");
		label_si_pas_de_mise.setFont(labelFontBoldMise);
		label_si_pas_de_mise.setBounds(95,90, 270, 18);
		panel_mises.add(label_si_pas_de_mise);
		if(table.getJoueurs().get(0).getMises().size()==0){
			label_si_pas_de_mise.setVisible(true);
			tableau_scroll_mises.setVisible(false);
		}
		else{
			label_si_pas_de_mise.setVisible(false);
			tableau_scroll_mises.setVisible(true);
		}




		//Gestion du panel table;
		panel_table.setBounds(310,5, 425, 660);
		panel_principal.add(panel_table);
		TitledBorder border_panel_table =BorderFactory.createTitledBorder("Table");
		panel_table.setBorder(border_panel_table);

		panel_numeros=new JLayeredPane();
		panel_numeros.setBounds(200,55, 200, 585);
		//TitledBorder border_panel_carte =BorderFactory.createTitledBorder("");
		//panel_carte.setBorder(border_panel_carte);
		String[] entetes_carte={"","","","","",""};
		String[][] numeros=new String[25][6];
		numeros[1][1]=""+1;
		numeros[1][3]=""+2;
		numeros[1][5]=""+3;
		for(int i=3;i<25;i+=2){
			numeros[i][1]=""+(Integer.decode(numeros[i-2][1])+3);
			numeros[i][3]=""+(Integer.decode(numeros[i-2][3])+3);
			numeros[i][5]=""+(Integer.decode(numeros[i-2][5])+3);
		}
		numeros[24][1]="C1";
		numeros[24][3]="C2";
		numeros[24][5]="C3";
		model_tableau_numeros = new UneditableTableModel(numeros,entetes_carte);
		tableau_numeros=new JTable();
		tableau_numeros.setModel(model_tableau_numeros);
		//coloriage
		tableau_numeros.setDefaultRenderer(Object.class, new JTableRender());
		//size
		TableColumn column = null;
		for (int i = 0; i < 5; i++) {
			column = tableau_numeros.getColumnModel().getColumn(i);
			if (i%2==0){
				column.setPreferredWidth(8);
			} 
		}
		for (int i = 1; i < 24; i+=2) {
			tableau_numeros.setRowHeight(i, 30);
		}
		tableau_numeros.setRowHeight(24, 32);

		tableau_numeros.setBounds(1,1, 200, 600);
		tableau_numeros.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tableau_numeros.getSelectedRow();
				int column = tableau_numeros.getSelectedColumn();
				miserNumeros(row,column);
				refreshInfo();
				refreshMise();
			}
		});
		tableau_numeros.setBorder(border_line);
		panel_numeros.add(tableau_numeros);
		panel_table.add(panel_numeros);
		//ZERO
		panel_0=new JLayeredPane();
		panel_0.setBounds(200,22, 200, 33);
		String[] entetes_0={""};
		String[][] zero=new String[1][1];
		zero[0][0]="0";
		model_tableau_0 = new UneditableTableModel(zero,entetes_0);
		tableau_0=new JTable();
		tableau_0.setModel(model_tableau_0);
		tableau_0.setDefaultRenderer(Object.class, new JTableRender());
		tableau_0.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableau_0.setRowHeight(0, 33);
		tableau_0.setBounds(1,1, 200, 33);
		tableau_0.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tableau_0.getSelectedRow();
				int column = tableau_0.getSelectedColumn();
				Miser0(row,column);
				refreshInfo();
				refreshMise();
			}
		});
		tableau_0.setBorder(border_line);
		panel_0.add(tableau_0);
		panel_table.add(panel_0);
		//DOUZAINE
		panel_12n=new JLayeredPane();
		panel_12n.setBounds(110,55, 90, 552);
		String[] entetes_12n={""};
		String[][] d12n=new String[3][1];
		d12n[0][0]="P\n1 à 12";
		d12n[1][0]="M\n13 à 24";
		d12n[2][0]="D\n25 à 36";
		model_tableau_12n = new UneditableTableModel(d12n,entetes_12n);
		tableau_12n=new JTable();
		tableau_12n.setModel(model_tableau_12n);
		tableau_12n.setDefaultRenderer(Object.class, new JTableRender());
		tableau_12n.getColumnModel().getColumn(0).setPreferredWidth(90);
		tableau_12n.setRowHeight(0, 184);
		tableau_12n.setRowHeight(1, 184);
		tableau_12n.setRowHeight(2, 184);
		tableau_12n.setBounds(1,1, 90, 585);
		tableau_12n.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tableau_12n.getSelectedRow();
				int column = tableau_12n.getSelectedColumn();
				miser12n(row,column);
				refreshInfo();
				refreshMise();
			}
		});
		tableau_12n.setBorder(border_line);
		panel_12n.add(tableau_12n);
		panel_table.add(panel_12n);
		//CHANCE SIMPLE
		panel_chance_simple=new JLayeredPane();
		panel_chance_simple.setBounds(20,55, 90, 552);
		String[] entetes_chance_simple = {""};
		String[][] chance_simple = new String[6][1];
		chance_simple[0][0]="Pair";
		chance_simple[1][0]="Impair";
		chance_simple[2][0]="Rouge";
		chance_simple[3][0]="Noir";
		chance_simple[4][0]="Manque";
		chance_simple[5][0]="Passe";
		model_tableau_chance_simple = new UneditableTableModel(chance_simple,entetes_chance_simple);
		tableau_chance_simple=new JTable();
		tableau_chance_simple.setModel(model_tableau_chance_simple);
		tableau_chance_simple.setDefaultRenderer(Object.class, new JTableRender());
		tableau_chance_simple.getColumnModel().getColumn(0).setPreferredWidth(90);
		for(int i = 0; i<6; i++){
			tableau_chance_simple.setRowHeight(i, 92);
		}
		tableau_chance_simple.setBounds(1,1, 90, 585);
		tableau_chance_simple.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tableau_chance_simple.getSelectedRow();
				int column = tableau_chance_simple.getSelectedColumn();
				miserChanceSimple(row,column);
				refreshInfo();
				refreshMise();
			}
		});
		tableau_chance_simple.setBorder(border_line);
		panel_chance_simple.add(tableau_chance_simple);
		panel_table.add(panel_chance_simple);
		//permet d'ajouter des images de jetons lorsqu'on clique
		this.tableau_numeros.addMouseListener(this);
		this.tableau_0.addMouseListener(this);
		this.tableau_chance_simple.addMouseListener(this);
		this.tableau_12n.addMouseListener(this);



		//Gestion des boutons
		panel_bouton.setBounds(740, 5, 300, 420);
		TitledBorder border_panel_bouton =BorderFactory.createTitledBorder("Actions");
		panel_bouton.setBorder(border_panel_bouton);
		bouton_halloffame = new JButton("Hall Of Fame");
		bouton_calibrage = new JButton("Calibration de la roulette");
		bouton_fermer = new JButton("Fermer");
		bouton_lancer = new JButton("Lancer la roulette");
		bouton_annuler = new JButton("Annuler les mises");
		bouton_lancer.setBounds(50, 50, 200, 50);
		bouton_annuler.setBounds(50, 120, 200, 50);
		bouton_halloffame.setBounds(50, 190, 200, 50);
		bouton_calibrage.setBounds(50, 260, 200, 50);
		bouton_fermer.setBounds(50, 330, 200, 50);
		bouton_lancer.addActionListener(this);
		bouton_annuler.addActionListener(this);
		bouton_halloffame.addActionListener(this);
		bouton_calibrage.addActionListener(this);
		bouton_fermer.addActionListener(this);
		panel_bouton.add(bouton_halloffame);
		panel_bouton.add(bouton_calibrage);
		panel_bouton.add(bouton_fermer);
		panel_bouton.add(bouton_lancer);
		panel_bouton.add(bouton_annuler);

		panel_principal.add(panel_bouton);

		add(panel_principal);

		setVisible(true);
	}

	protected void miserChanceSimple(int row, int column) {
		if(column==0){
			try {
				switch(row){
				case 0:
					table.getJoueurs().get(0).miserPair(mise);
					break;
				case 1:
					table.getJoueurs().get(0).miserImpair(mise);
					break;
				case 2:
					table.getJoueurs().get(0).miserRouge(mise);
					break;
				case 3:
					table.getJoueurs().get(0).miserNoir(mise);
					break;
				case 4:
					table.getJoueurs().get(0).miserManque(mise);
					break;
				case 5:
					table.getJoueurs().get(0).miserPasse(mise);
					break;
				}
			} catch (MiseMaxException e) {
				dialogMiseMaxException();
			} catch (BesaceInsuffisanteException e) {
				dialogBesaceInsuffisanteException();
			}
		}
	}

	protected void miser12n(int row, int column) {
		if(column==0){
			try {
				switch(row){
				case 0:
					table.getJoueurs().get(0).miserP(mise);
					break;
				case 1:
					table.getJoueurs().get(0).miserM(mise);
					break;
				case 2:
					table.getJoueurs().get(0).miserD(mise);
					break;
				}
			} catch (MiseMaxException e) {
				dialogMiseMaxException();
			} catch (BesaceInsuffisanteException e) {
				dialogBesaceInsuffisanteException();
			}
		}
	}

	protected void Miser0(int row, int column) {
		if(row==0&&column==0){
			try {
				table.getJoueurs().get(0).miserPlein(mise, 0);
			} catch (MiseMaxException e) {
				dialogMiseMaxException();
			} catch (BesaceInsuffisanteException e) {
				dialogBesaceInsuffisanteException();
			}
		}
	}

	protected void miserNumeros(int row, int column) {
		try {
			switch(row){
			case 0:
				switch(column){
				case 0:
					//Pas d'action
					break;
				case 1:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._0_1);
					break;
				case 2:
					//Pas d'action
					break;
				case 3:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._0_2);
					break;
				case 4:
					//Pas d'action
					break;
				case 5:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._0_3);
					break;
				}
				break;
			case 1:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserTransversale(mise, ChanceTransversale._1_2_3);
					break;
				case 1:
					table.getJoueurs().get(0).miserPlein(mise, 1);
					break;
				case 2:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._1_2);
					break;
				case 3:
					table.getJoueurs().get(0).miserPlein(mise, 2);
					break;
				case 4:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._2_3);
					break;
				case 5:
					table.getJoueurs().get(0).miserPlein(mise, 3);
					break;
				}
				break;
			case 2:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserSixain(mise, ChanceSixain._1_2_3_4_5_6);
					break;
				case 1:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._1_4);
					break;
				case 2:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._1_2_4_5);
					break;
				case 3:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._2_5);
					break;
				case 4:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._2_3_5_6);
					break;
				case 5:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._3_6);
					break;
				}
				break;
			case 3:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserTransversale(mise, ChanceTransversale._4_5_6);
					break;
				case 1:
					table.getJoueurs().get(0).miserPlein(mise, 4);
					break;
				case 2:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._4_5);
					break;
				case 3:
					table.getJoueurs().get(0).miserPlein(mise, 5);
					break;
				case 4:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._5_6);
					break;
				case 5:
					table.getJoueurs().get(0).miserPlein(mise, 6);
					break;
				}
				break;
			case 4:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserSixain(mise, ChanceSixain._4_5_6_7_8_9);
					break;
				case 1:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._4_7);
					break;
				case 2:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._4_5_7_8);
					break;
				case 3:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._5_8);
					break;
				case 4:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._5_6_8_9);
					break;
				case 5:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._6_9);
					break;
				}
				break;
			case 5:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserTransversale(mise, ChanceTransversale._7_8_9);
					break;
				case 1:
					table.getJoueurs().get(0).miserPlein(mise, 7);
					break;
				case 2:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._7_8);
					break;
				case 3:
					table.getJoueurs().get(0).miserPlein(mise, 8);
					break;
				case 4:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._8_9);
					break;
				case 5:
					table.getJoueurs().get(0).miserPlein(mise, 9);
					break;
				}
				break;
			case 6:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserSixain(mise, ChanceSixain._7_8_9_10_11_12);
					break;
				case 1:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._7_10);
					break;
				case 2:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._7_8_10_11);
					break;
				case 3:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._8_11);
					break;
				case 4:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._8_9_11_12);
					break;
				case 5:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._9_12);
					break;
				}
				break;
			case 7:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserTransversale(mise, ChanceTransversale._10_11_12);
					break;
				case 1:
					table.getJoueurs().get(0).miserPlein(mise, 10);
					break;
				case 2:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._10_11);
					break;
				case 3:
					table.getJoueurs().get(0).miserPlein(mise, 11);
					break;
				case 4:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._11_12);
					break;
				case 5:
					table.getJoueurs().get(0).miserPlein(mise, 12);
					break;
				}
				break;
			case 8:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserSixain(mise, ChanceSixain._10_11_12_13_14_15);
					break;
				case 1:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._10_13);
					break;
				case 2:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._10_11_13_14);
					break;
				case 3:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._11_14);
					break;
				case 4:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._11_12_14_15);
					break;
				case 5:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._12_15);
					break;
				}
				break;
			case 9:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserTransversale(mise, ChanceTransversale._13_14_15);
					break;
				case 1:
					table.getJoueurs().get(0).miserPlein(mise, 13);
					break;
				case 2:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._13_14);
					break;
				case 3:
					table.getJoueurs().get(0).miserPlein(mise, 14);
					break;
				case 4:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._14_15);
					break;
				case 5:
					table.getJoueurs().get(0).miserPlein(mise, 15);
					break;
				}
				break;
			case 10:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserSixain(mise, ChanceSixain._13_14_15_16_17_18);
					break;
				case 1:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._13_16);
					break;
				case 2:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._13_14_16_17);
					break;
				case 3:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._14_17);
					break;
				case 4:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._14_15_17_18);
					break;
				case 5:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._15_18);
					break;
				}
				break;
			case 11:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserTransversale(mise, ChanceTransversale._16_17_18);
					break;
				case 1:
					table.getJoueurs().get(0).miserPlein(mise, 16);
					break;
				case 2:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._16_17);
					break;
				case 3:
					table.getJoueurs().get(0).miserPlein(mise, 17);
					break;
				case 4:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._17_18);
					break;
				case 5:
					table.getJoueurs().get(0).miserPlein(mise, 18);
					break;
				}
				break;
			case 12:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserSixain(mise, ChanceSixain._16_17_18_19_20_21);
					break;
				case 1:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._16_19);
					break;
				case 2:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._16_17_19_20);
					break;
				case 3:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._17_20);
					break;
				case 4:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._17_18_20_21);
					break;
				case 5:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._18_21);
					break;
				}
				break;
			case 13:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserTransversale(mise, ChanceTransversale._19_20_21);
					break;
				case 1:
					table.getJoueurs().get(0).miserPlein(mise, 19);
					break;
				case 2:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._19_20);
					break;
				case 3:
					table.getJoueurs().get(0).miserPlein(mise, 20);
					break;
				case 4:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._20_21);
					break;
				case 5:
					table.getJoueurs().get(0).miserPlein(mise, 21);
					break;
				}
				break;
			case 14:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserSixain(mise, ChanceSixain._19_20_21_22_23_24);
					break;
				case 1:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._19_22);
					break;
				case 2:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._19_20_22_23);
					break;
				case 3:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._20_23);
					break;
				case 4:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._20_21_23_24);
					break;
				case 5:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._21_24);
					break;
				}
				break;
			case 15:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserTransversale(mise, ChanceTransversale._22_23_24);
					break;
				case 1:
					table.getJoueurs().get(0).miserPlein(mise, 22);
					break;
				case 2:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._22_23);
					break;
				case 3:
					table.getJoueurs().get(0).miserPlein(mise, 23);
					break;
				case 4:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._23_24);
					break;
				case 5:
					table.getJoueurs().get(0).miserPlein(mise, 24);
					break;
				}
				break;
			case 16:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserSixain(mise, ChanceSixain._22_23_24_25_26_27);
					break;
				case 1:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._22_25);
					break;
				case 2:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._22_23_25_26);
					break;
				case 3:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._23_26);
					break;
				case 4:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._23_24_26_27);
					break;
				case 5:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._24_27);
					break;
				}
				break;
			case 17:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserTransversale(mise, ChanceTransversale._25_26_27);
					break;
				case 1:
					table.getJoueurs().get(0).miserPlein(mise, 25);
					break;
				case 2:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._25_26);
					break;
				case 3:
					table.getJoueurs().get(0).miserPlein(mise, 26);
					break;
				case 4:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._26_27);
					break;
				case 5:
					table.getJoueurs().get(0).miserPlein(mise, 27);
					break;
				}
				break;
			case 18:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserSixain(mise, ChanceSixain._25_26_27_28_29_30);
					break;
				case 1:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._25_28);
					break;
				case 2:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._25_26_28_29);
					break;
				case 3:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._26_29);
					break;
				case 4:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._26_27_29_30);
					break;
				case 5:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._27_30);
					break;
				}
				break;
			case 19:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserTransversale(mise, ChanceTransversale._28_29_30);
					break;
				case 1:
					table.getJoueurs().get(0).miserPlein(mise, 28);
					break;
				case 2:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._28_29);
					break;
				case 3:
					table.getJoueurs().get(0).miserPlein(mise, 29);
					break;
				case 4:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._29_30);
					break;
				case 5:
					table.getJoueurs().get(0).miserPlein(mise, 30);
					break;
				}
				break;
			case 20:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserSixain(mise, ChanceSixain._28_29_30_31_32_33);
					break;
				case 1:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._28_31);
					break;
				case 2:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._28_29_31_32);
					break;
				case 3:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._29_32);
					break;
				case 4:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._29_30_32_33);
					break;
				case 5:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._30_33);
					break;
				}
				break;
			case 21:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserTransversale(mise, ChanceTransversale._31_32_33);
					break;
				case 1:
					table.getJoueurs().get(0).miserPlein(mise, 31);
					break;
				case 2:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._31_32);
					break;
				case 3:
					table.getJoueurs().get(0).miserPlein(mise, 32);
					break;
				case 4:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._32_33);
					break;
				case 5:
					table.getJoueurs().get(0).miserPlein(mise, 33);
					break;
				}
				break;
			case 22:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserSixain(mise, ChanceSixain._31_32_33_34_35_36);
					break;
				case 1:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._31_34);
					break;
				case 2:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._31_32_34_35);
					break;
				case 3:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._32_35);
					break;
				case 4:
					table.getJoueurs().get(0).miserCarre(mise, ChanceCarre._32_33_35_36);
					break;
				case 5:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._33_36);
					break;
				}
				break;
			case 23:
				switch(column){
				case 0:
					table.getJoueurs().get(0).miserTransversale(mise, ChanceTransversale._34_35_36);
					break;
				case 1:
					table.getJoueurs().get(0).miserPlein(mise, 34);
					break;
				case 2:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._34_35);
					break;
				case 3:
					table.getJoueurs().get(0).miserPlein(mise, 35);
					break;
				case 4:
					table.getJoueurs().get(0).miserCheval(mise, ChanceCheval._35_36);
					break;
				case 5:
					table.getJoueurs().get(0).miserPlein(mise, 36);
					break;
				}
				break;
			case 24:
				switch(column){
				case 0:
					//Pas d'action
					break;
				case 1:
					table.getJoueurs().get(0).miserC1(mise);
					break;
				case 2:
					//Pas d'action
					break;
				case 3:
					table.getJoueurs().get(0).miserC2(mise);
					break;
				case 4:
					//Pas d'action
					break;
				case 5:
					table.getJoueurs().get(0).miserC3(mise);
					break;
				}
				break;
			}
		} catch (MiseMaxException e) {
			dialogMiseMaxException();
		} catch (BesaceInsuffisanteException e) {
			dialogBesaceInsuffisanteException();
		}
	}

	public void dialogMiseMaxException(){
		jetons.remove(jetons.size()-1);
		JOptionPane.showMessageDialog(this,"Mise Maximum atteinte !", "ErreurMiseMax",JOptionPane.ERROR_MESSAGE);
	}

	public void dialogBesaceInsuffisanteException(){
		jetons.remove(jetons.size()-1);
		JOptionPane.showMessageDialog(this,"Il n'y pas assez d'argent dans la besace !", "ErreurBesace",JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting()) {
			mise = (int)source.getValue();
			this.label_mise.setText(mise+" €");
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		JTable source = (JTable)arg0.getSource();
		Point location_absolu = arg0.getPoint();
		Point location_correction = new Point();
		if(source == tableau_chance_simple){location_correction=panel_chance_simple.getLocation();}
		else if(source == tableau_12n){location_correction=panel_12n.getLocation();}
		else if(source == tableau_0){location_correction=panel_0.getLocation();}
		else if(source == tableau_numeros){location_correction=panel_numeros.getLocation();}
		Point location = new Point(location_absolu.x+location_correction.x,location_absolu.y+location_correction.y);
		ImagePanel panel_image = new ImagePanel(new ImageIcon(getClass().getResource("jeton.png")).getImage());
		panel_image.setSize(40, 40);
		panel_image.setBounds(location.x-15, location.y-15, 40, 40);
		jetons.add(panel_image);
		afficherJetons();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {mouseClicked(arg0);}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == this.bouton_euros){
			try {
				table.getJoueurs().get(0).setBesace(table.getJoueurs().get(0).getBesace()+100);
				table.getJoueurs().get(0).setBesaceInit(table.getJoueurs().get(0).getBesaceInit()+100);
			} catch (BesaceInsuffisanteException e1) {}
			refreshInfo();
		}
		else if (source == this.bouton_lancer){
			//"Les Jeux sont fait!"
			table.tirage();
			JOptionPane.showMessageDialog(this,table.dernierTirage(),"Croupier :",JOptionPane.PLAIN_MESSAGE);
			//"Rien ne va plus!"
			fenetre_hall_of_fame.refresh();
			int b=table.getJoueurs().get(0).gainPerte();
			table.gains();
			if(b<table.getJoueurs().get(0).gainPerte()){
				JOptionPane.showMessageDialog(this,table.getJoueurs().get(0).getNom()+" gagne "+(table.getJoueurs().get(0).gainPerte()-b)+" €!","Bravo!",JOptionPane.PLAIN_MESSAGE);
			}
			else if(b>table.getJoueurs().get(0).gainPerte()){
				JOptionPane.showMessageDialog(this,table.getJoueurs().get(0).getNom()+" perd "+(b-table.getJoueurs().get(0).gainPerte())+" €.","La chance va tourner!",JOptionPane.PLAIN_MESSAGE);
			}
			effacerJeton();
			refreshInfo();
			refreshMise();
			purgerJoueur();
			Random r = new Random();
			if(r.nextInt(5)==3){
				ajouterJoueurs(r.nextInt(2));
			}
			try {
				for(int i=1;i<table.getJoueurs().size();i++){
					table.getJoueurs().get(i).miser();
				}
			}catch (MiseMaxException e1) {} catch (BesaceInsuffisanteException e1) {}
			refreshFenetreInfoJoueur();
			refreshJoueur();

			//"Faites vos jeux!"
		}
		else if (source == this.bouton_annuler){
			table.getJoueurs().get(0).annulerMise();
			refreshInfo();
			refreshMise();
			effacerJeton();
		}
		else if (source == this.bouton_halloffame){
			fenetre_hall_of_fame.afficherMasquer();
		}
		else if (source == this.bouton_calibrage){
			fenetre_calibrage.afficherMasquer();
		}
		else if (source == this.bouton_fermer){
			fenetre_hall_of_fame.setVisible(false);
			fenetre_calibrage.setVisible(false);
			for(FenetreInfoJoueur fij:fenetres_info_joueur){
				fij.setVisible(false);
			}
			this.setVisible(false);
		}
	}

	public void afficherJetons(){
		for(ImagePanel p: jetons){
			panel_table.remove(p);
		}
		for(ImagePanel p: jetons){
			panel_table.add(p,0);
		}
		panel_table.repaint();
	}

	public void effacerJeton(){
		for(ImagePanel p: jetons){
			panel_table.remove(p);
		}
		jetons.clear();
		panel_table.repaint();
	}

	public void refreshInfo(){
		this.label_besace.setText(table.getJoueurs().get(0).getBesace()+" €");
		this.label_gain_perte.setText(table.getJoueurs().get(0).gainPerte()+" €");
		if(table.getJoueurs().get(0).gainPerte()<0){
			label_gain_perte.setForeground(new Color(220,0,0));
		}
		else if(table.getJoueurs().get(0).gainPerte()>0){
			label_gain_perte.setForeground(new Color(0,130,0));
		}
		else{
			label_gain_perte.setForeground(new Color(0,0,0));
		}
	}

	public void refreshMise(){
		//on supprime tous les objets du panel
		panel_mises.removeAll();
		String[] entetes_mises = {"Type de la mise", "Mise (€)"};
		Object[][] donnees_mises = new Object[table.getJoueurs().get(0).getMises().size()][2];
		for(int i=0;i<table.getJoueurs().get(0).getMises().size();i++){
			donnees_mises[i][0]=table.getJoueurs().get(0).getMises().get(i);
			donnees_mises[i][1]=table.getJoueurs().get(0).getMises().get(i).getMise();
		}
		model_tableau_mises = new UneditableTableModel(donnees_mises,entetes_mises);
		tableau_mes_mises = new JTable();
		tableau_mes_mises.setModel(model_tableau_mises);
		tableau_mes_mises.setAutoCreateRowSorter(true);
		tableau_scroll_mises = new JScrollPane(tableau_mes_mises);
		tableau_scroll_mises.setBounds(25, 30, 250, 170);
		tableau_scroll_mises.setBorder(border_line);
		panel_mises.add(tableau_scroll_mises);
		panel_mises.add(label_si_pas_de_mise);
		if(table.getJoueurs().get(0).getMises().size()==0){
			label_si_pas_de_mise.setVisible(true);
			tableau_scroll_mises.setVisible(false);
		}
		else{
			label_si_pas_de_mise.setVisible(false);
			tableau_scroll_mises.setVisible(true);
		}
		this.validate();
	}

	public void refreshJoueur(){
		panel_joueurs.removeAll();
		String[] entetes = {"joueurs", "besace (€)","information"};
		Object[][] donnees = new Object[table.getJoueurs().size()-1][3];
		for(int i=1;i<table.getJoueurs().size();i++){
			donnees[i-1][0]=table.getJoueurs().get(i).getNom();
			donnees[i-1][1]=table.getJoueurs().get(i).getBesace();
			donnees[i-1][2]="Afficher/Masquer";
		}
		model_tableau_joueurs = new UneditableTableModel(donnees,entetes);
		tableau_joueurs=new JTable();
		tableau_joueurs.setModel(model_tableau_joueurs);
		tableau_joueurs.getColumn("information").setCellRenderer(new ButtonRenderer());//on donne l'aspect d'un bouton au cellules de la colonne information
		tableau_joueurs.addMouseListener(new MouseAdapter() {//on appel la methode pour afficher un ou masque la fenetreInfoJueur s'il y a un clic
			public void mouseClicked(MouseEvent e) {
				int row = tableau_joueurs.getSelectedRow();
				int column = tableau_joueurs.getSelectedColumn();
				afficherMasquerInfoJoueur(row,column);
			}
		});
		tableau_joueurs.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableau_joueurs.getColumnModel().getColumn(1).setPreferredWidth(10);
		tableau_scroll = new JScrollPane(tableau_joueurs);
		tableau_scroll.setBounds(25, 30, 250, 170);
		tableau_scroll.setBorder(border_line);
		if(table.getJoueurs().size()==1){
			label_si_pas_de_joueurs.setVisible(true);
			tableau_scroll.setVisible(false);
		}
		else{
			label_si_pas_de_joueurs.setVisible(false);
			tableau_scroll.setVisible(true);
		}
		panel_joueurs.add(label_si_pas_de_joueurs);
		panel_joueurs.add(tableau_scroll);
		this.validate();
	}

	public void refreshFenetreInfoJoueur(){
		for(FenetreInfoJoueur f:fenetres_info_joueur){
			f.refresh();
		}
	}

	public void afficherMasquerInfoJoueur(int row, int column) {
		if(column==2){
			if(fenetres_info_joueur.get(row).isVisible()){
				fenetres_info_joueur.get(row).setVisible(false);
			}
			else{
				fenetres_info_joueur.get(row).setVisible(true);
			}
		}
	}

	public void ajouterJoueurs(int n) {
		Random r = new Random();
		String tab_nom[] = {"Richard","Fadoua","Sonia","Maxime","Alexandre","Pascal","Marie","Claire","Anis","Mathieu","Julien","Théo","Julie","Fred","Cyril","Brittany","Celine","Lucas","Quentin",
				"Lola","Marine","Michael","Olivier","Arthur","Robert","Olivia","Natacha","Amandine","Amy","Chloe","Etienne","Laura","Melanie","Sabrina","Florent","Johann","Thomas","Romain"};
		int nb_joueur = n;
		Joueur j = null;
		for(int i=0; i<=nb_joueur; i++){
			try {
				switch(r.nextInt(3)){
				case 0:
					j = new JoueurHawks(tab_nom[r.nextInt(tab_nom.length)],r.nextInt(200)+20);
					break;
				case 1:
					j = new JoueurAlembert(tab_nom[r.nextInt(tab_nom.length)],r.nextInt(150)+20);
					break;
				case 2:
					j = new JoueurESIAL(tab_nom[r.nextInt(tab_nom.length)],r.nextInt(60)+20);
					break;
				}

			} catch (BesaceInsuffisanteException e) {}
			fenetres_info_joueur.add(new FenetreInfoJoueur(j));
			table.addJoueur(j);
		}

		refreshFenetreInfoJoueur();
	}

	public void purgerJoueur(){
		Random r = new Random();
		Joueur j=null;
		boolean supprimer,possibilite_recave;
		for(int i=1;i<table.getJoueurs().size();i++){
			supprimer=false;
			possibilite_recave=true;
			j=table.getJoueurs().get(i);
			if(j.getBesace()==0){
				supprimer=true;
			}
			if(j instanceof JoueurAlembert){
				JoueurAlembert a = (JoueurAlembert) j;
				if(!a.peutMiserPlus()){
					supprimer=true;
				}
			}
			else if(j instanceof JoueurHawks){
				JoueurHawks a = (JoueurHawks) j;
				if(!a.peutDoubler()){
					supprimer=true;
				}
			}
			else if(j instanceof JoueurESIAL){
				JoueurESIAL a = (JoueurESIAL) j;
				if(!a.peutDoubler()){
					supprimer=true;
				}
			}

			if((r.nextInt(5)==1 || j.gainPerte()>=10)&&j.getNombredejeu()>r.nextInt(30)+10){
				possibilite_recave=false;
				supprimer=true;
			}

			if(supprimer){
				if(r.nextInt(5)==1 && possibilite_recave){
					int n=r.nextInt(50)+20;
					try {
						j.setBesace(j.getBesace()+n);
						j.setBesaceInit(j.getBesaceInit()+n);
					} catch (BesaceInsuffisanteException e) {}
					System.out.println(j.getNom()+" recave de "+n+" €.");
				}
				else{
					table.removeJoueur(j);
					FenetreInfoJoueur fenetre_a_supp = null;
					for(FenetreInfoJoueur fij:fenetres_info_joueur){
						if(j==fij.getJoueur()){
							fenetre_a_supp = fij;
						}
					}
					fenetre_a_supp.setVisible(false);
					fenetres_info_joueur.remove(fenetre_a_supp);
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new FenetreUnJoueur();
	}

}


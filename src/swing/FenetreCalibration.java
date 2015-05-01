package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import simulation.LaSoiree;

import jeu.Numero;
import jeu.Roulette;

public class FenetreCalibration extends JFrame implements ActionListener {
	private JButton bouton_refresh;
	private JButton bouton_retour;
	JLabel tab_label[];
	JProgressBar tab_progressbar[];
	
	private Roulette roulette;
	
	public FenetreCalibration(){
		this(new Roulette());
	}
	
	public FenetreCalibration(Roulette r){
		roulette = r;
		int tab_occurance[] = roulette.testRoulette();
		int total_occurance = roulette.getTotalOccurance();
		tab_label = new JLabel[tab_occurance.length*2+2];
		tab_progressbar = new JProgressBar[tab_occurance.length+1];
		
		int total = 0;
		for(int i = 0; i< tab_occurance.length; i++){
			double m = ((double)(int)((((double) tab_occurance[i]/ (double) total_occurance))*100*100))/100;
			total+=tab_occurance[i];
			tab_label[i] = new JLabel("   Numero   "+i);
			Numero n = new Numero(i);
			if(n.estRouge()){tab_label[i].setForeground(new Color(210,0,0));}
			else if(n.estVert()){tab_label[i].setForeground(new Color(0,130,0));}
			tab_label[i+tab_occurance.length+1] = new JLabel(m+"% ("+new Integer(tab_occurance[i])+"/"+ new Integer(total_occurance)+")   ");
			tab_progressbar[i] = new JProgressBar();
			tab_progressbar[i].setValue((int) m);
			tab_progressbar[i].setStringPainted(true);
		}
		tab_label[(tab_label.length-1)/2] = new JLabel("   TOTAL :");
		tab_label[tab_label.length-1] = new JLabel(total/total_occurance*100+"% ("+new Integer(total)+"/"+ new Integer(total_occurance)+")");
		tab_progressbar[tab_progressbar.length-1] = new JProgressBar();
		tab_progressbar[tab_progressbar.length-1].setValue(total/total_occurance*100);
		tab_progressbar[tab_progressbar.length-1].setStringPainted(true);
		

		setTitle("Calibration");
		setSize(520,835);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		JPanel panel_principal = new JPanel();
		JPanel panel_grid = new JPanel(new GridLayout(38,3,10,0));
		JPanel panel_boutons = new JPanel();
		
		for(int i = 0; i< tab_occurance.length+1; i++){
			panel_grid.add(tab_label[i]);
			panel_grid.add(tab_progressbar[i]);
			panel_grid.add(tab_label[i+tab_occurance.length+1]);
		}
		

		bouton_refresh = new JButton("Relancer la calibration");
		bouton_retour = new JButton("Retour au jeu");
		panel_boutons.add(bouton_refresh);
		panel_boutons.add(bouton_retour);
		bouton_refresh.addActionListener(this);
		bouton_retour.addActionListener(this);
		
		panel_principal.add(panel_grid);
		panel_principal.add(panel_boutons);
		add(panel_principal);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == bouton_refresh){
			int tab_occurance[] = roulette.testRoulette();
			int total_occurance = roulette.getTotalOccurance();
			int total = 0;
			for(int i = 0; i< tab_occurance.length; i++){
				double m = ((double)(int)((((double) tab_occurance[i]/ (double) total_occurance))*100*100))/100;
				total+=tab_occurance[i];
				tab_label[i].setText("   Numero   "+i);
				tab_label[i+tab_occurance.length+1].setText(m+"% ("+new Integer(tab_occurance[i])+"/"+ new Integer(total_occurance)+")   ");
				tab_progressbar[i].setValue((int) m);
			}
			tab_label[(tab_label.length-1)/2].setText("   TOTAL :");
			tab_label[tab_label.length-1].setText(total/total_occurance*100+"% ("+new Integer(total)+"/"+ new Integer(total_occurance)+")");
			tab_progressbar[tab_progressbar.length-1].setValue(total/total_occurance*100);
		}
		else if(source == bouton_retour){
			this.setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		new FenetreCalibration();
	}

	public void afficherMasquer() {
		this.setVisible(!this.isVisible());
	}
}

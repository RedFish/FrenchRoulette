package swing;

import kinectsensor.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JFrame;

public class FenetreMenu extends JFrame implements ActionListener {
	private JButton bouton_un_joueur;
	private JButton bouton_deux_joueurs;
	private JButton bouton_kinect;
	private JButton bouton_lasoiree;
	private JButton bouton_quitter;
	
	public FenetreMenu(){
		setTitle("Menu");
		setSize(800,640);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLayeredPane panel_principal = new JLayeredPane(); 
		
		ImagePanel panel_image = new ImagePanel(new ImageIcon(getClass().getResource("roulette_menu.jpeg")).getImage());

		JLayeredPane panel_boutons = new JLayeredPane();
		panel_boutons.setBounds(260, 175, 300, 500);

		bouton_un_joueur = new JButton("Un joueur");
		bouton_deux_joueurs = new JButton("Deux joueurs");
		bouton_kinect = new JButton("Kinect");
		bouton_lasoiree = new JButton("La soirée");
		bouton_quitter = new JButton("Quitter");
		panel_boutons.add(bouton_un_joueur);
		panel_boutons.add(bouton_deux_joueurs);
		panel_boutons.add(bouton_kinect);
		panel_boutons.add(bouton_lasoiree);
		panel_boutons.add(bouton_quitter);
		bouton_un_joueur.setBounds(0, 10, 130, 50);
		bouton_deux_joueurs.setBounds(150, 10, 130, 50);
		bouton_kinect.setBounds(0, 80, 130, 50);
		bouton_lasoiree.setBounds(150, 80, 130, 50);
		bouton_quitter.setBounds(60, 150, 130, 50);
		bouton_un_joueur.addActionListener(this);
		bouton_deux_joueurs.addActionListener(this);
		bouton_kinect.addActionListener(this);
		bouton_lasoiree.addActionListener(this);
		bouton_quitter.addActionListener(this);

		panel_principal.add(panel_image,new Integer(0));
		panel_principal.add(panel_boutons,new Integer(1));

		add(panel_principal);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == bouton_un_joueur){
			new FenetreUnJoueur();
		}
		else if(source == bouton_deux_joueurs){
			new FenetreDeuxJoueurs();
		}
		else if(source == bouton_kinect){
			JFrame f =new JFrame("Kinect tracker");
	        f.setLayout(new BorderLayout());
	        f.setSize(640,570);
	        AveragePointTracking embed = new AveragePointTracking();
	        f.add(embed, BorderLayout.CENTER);
	        embed.init(f);
	        f.setVisible(true);
	        
			new FenetreUnJoueur();
		}
		else if(source == bouton_lasoiree){
			new FenetreLaSoiree();
		}
		else if(source == bouton_quitter){
			System.exit(0);
		}
	}
}

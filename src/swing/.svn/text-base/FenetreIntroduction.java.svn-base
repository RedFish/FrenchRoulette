package swing;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class FenetreIntroduction extends JWindow {

	public FenetreIntroduction() {
		setSize(800, 504);//On lui donne une taille pour qu'on puisse la voir
		
		ImagePanel image_panel = new ImagePanel(new ImageIcon(getClass().getResource("roulette_intro.jpeg")).getImage());
		add(image_panel);
		
		
		setLocationRelativeTo(null);
		setVisible(true);//On la rend visible
	}

}

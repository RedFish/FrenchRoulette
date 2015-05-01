package swing;

/*
 * BlinkingLabel.java  1.0
 * 
 * Copyright (c) 1999 Emmanuel PUYBARET - eTeks.
 * All Rights Reserved.
 *
 */
 
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
 
// Classe de label clignotant
public class BlinkingLabel extends JLabel
{  
  Color defaultColor;   // Couleur par défaut du label
  Color blinkingColor;  // Couleur de clignotement
  Timer timer;          // Timer déclenchant des tics
 
  // Constructeur 
  public BlinkingLabel (JLabel l, Color blinkingColor) 
  {	
    super (l.getText());    
    this.blinkingColor = blinkingColor;
    this.defaultColor  = l.getForeground ();
    this.setFont(l.getFont());
	Font newLabelFont=new Font(l.getFont().getName(),Font.BOLD,l.getFont().getSize()+1);  
	this.setFont(newLabelFont);
	this.setHorizontalAlignment(l.getHorizontalAlignment());
 
    // Création et lancement du timer
    timer = createTimer ();
    timer.start ();
  }
  
  // Méthode renvoyant un timer prêt à démarrer
  private Timer createTimer ()
  {
    // Création d'une instance de listener 
    // associée au timer
    ActionListener action = new ActionListener ()
      {
        // Méthode appelée à chaque tic du timer
        public void actionPerformed (ActionEvent event)
        {
          // Inversion de la couleur
          if (getForeground ().equals (defaultColor))            
            setForeground (blinkingColor);
          else
            setForeground (defaultColor);
        }
      };
      
    // Création d'un timer qui génère un tic
    // chaque 750 millième de seconde
    return new Timer (750, action);
  }  
}
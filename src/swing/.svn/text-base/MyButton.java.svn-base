package swing;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
 
public class MyButton extends JButton { // !!! on doit étendre le composant dans lequel on veut insérer une image de fond
 
   private Image img;
   private String imageName;
 
   //Un constructeur pour choisir plus simplement l'image
   public MyButton(String imageName) {
       img = new ImageIcon(getClass().getResource(imageName)).getImage();
   }
 
   //On doit redéfinir la méthode paintComponent() pour les composant swing !!! et paint() pour awt
   @Override
   protected void paintComponent(Graphics g) {
       //super.paintComponent(g);
       if (img == null) return;
       g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
       getIcon().paintIcon(this, g, (getWidth()-getIcon().getIconWidth())/2, (getHeight()-getIcon().getIconHeight())/2);
 
   }
 
}
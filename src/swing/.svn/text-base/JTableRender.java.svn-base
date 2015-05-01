package swing;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import jeu.Numero;

/**
 * Définir l'affichage dans un JTable
 * @author 
 */
public class JTableRender extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		
		if (component instanceof JLabel) {
			JLabel label = (JLabel) component;

			try{
				Numero n = new Numero(Integer.decode(label.getText()));
				label.setHorizontalAlignment(CENTER);
				Font police = new Font("Arial", Font.BOLD, 18);
				label.setFont(police);
				label.setForeground(Color.WHITE);

				if (n.estRouge()){
					component.setBackground(Color.RED);
				}
				else if (n.estNoir()){ 
					component.setBackground(Color.BLACK);
				}

			}
			catch(Exception e){
				component.setBackground(Color.DARK_GRAY);
			}
			
			if(column==0){
				Font police = new Font("Arial", Font.BOLD, 15);
				label.setHorizontalAlignment(CENTER);
				component.setFont(police);

				if(label.getText().equals("0")){
					Font police1 = new Font("Arial", Font.BOLD, 18);
					component.setFont(police1);
					component.setForeground(Color.WHITE);
					component.setBackground(new Color(0,150,0));
				}
				if(label.getText().equals("Rouge")){
					component.setForeground(Color.WHITE);
					component.setBackground(Color.RED);
				}
				else if(label.getText().equals("Noir")){
					component.setForeground(Color.WHITE);
					component.setBackground(Color.BLACK);
				}
				else if(label.getText().contains("à")||label.getText().contains("air")||label.getText().equals("Manque")||label.getText().equals("Passe")){
					component.setForeground(Color.BLACK);
					component.setBackground(Color.LIGHT_GRAY);
				}
			}
		}
		
		if(row==24){
			Font police = new Font("Arial", Font.BOLD, 15);
			component.setFont(police);
			component.setForeground(Color.BLACK);
			component.setBackground(Color.LIGHT_GRAY);
		}

		return component;
	}
}

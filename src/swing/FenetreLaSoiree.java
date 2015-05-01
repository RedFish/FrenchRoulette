package swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import simulation.LaSoiree;
public class FenetreLaSoiree extends JFrame implements ActionListener {
	private JButton bouton_refresh;
	private JButton bouton_retour;
	private JTextArea txt_console;

	public FenetreLaSoiree(){
		setTitle("Simulation de la soirée");
		setSize(630,550);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLayout(new BorderLayout(2, 2));


		// Create an instance of javax.swing.JTextArea control
		txt_console = new JTextArea();
		txt_console.setEditable(false);

		// Now create a new TextAreaOutputStream to write to our JTextArea control and wrap a
		// PrintStream around it to support the println/printf methods.
		PrintStream out = new PrintStream( new TextAreaOutputStream( txt_console ) );
		// redirect standard output stream to the TextAreaOutputStream
		System.setOut(out);		
		txt_console.setLineWrap(true);
		JScrollPane sbrText = new JScrollPane(txt_console);
		sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		JPanel panel_boutons = new JPanel();
		bouton_refresh = new JButton("Relancer la simulation");
		bouton_retour = new JButton("Retour au menu");
		panel_boutons.add(bouton_refresh);
		panel_boutons.add(bouton_retour);
		bouton_refresh.setBounds(60, 10, 130, 50);
		bouton_retour.setBounds(60, 80, 130, 50);
		bouton_refresh.addActionListener(this);
		bouton_retour.addActionListener(this);
		
		add(sbrText, BorderLayout.CENTER);
		add(panel_boutons, BorderLayout.SOUTH);

		new LaSoiree();

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == bouton_refresh){
			txt_console.setText("");
			new LaSoiree();
		}
		else if(source == bouton_retour){
			this.setVisible(false);
		}
	}
}

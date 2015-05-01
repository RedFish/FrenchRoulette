package kinectsensor;

import processing.core.*; 
import processing.xml.*; 

import org.openkinect.*; 
import org.openkinect.processing.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AveragePointTracking extends PApplet implements Runnable  {

	// Daniel Shiffman
	// Tracking the average location beyond a given depth threshold
	// Thanks to Dan O'Sullivan
	// http://www.shiffman.net
	// https://github.com/shiffman/libfreenect/tree/master/wrappers/java/processing

	// Showing how we can farm all the kinect stuff out to a separate class
	public KinectTracker tracker;
	// Kinect Library object
	public Kinect kinect;
	public JFrame frame = null; //Fenetre dans laquelle apparait la video kinect
	float deg = 15; // Start at 15 degrees
	
	public void init(JFrame j){
		super.init();
		frame=j;
	}

	public void setup() {
		try{
			size(640,550);
			kinect = new Kinect(this);
			tracker = new KinectTracker(this);
			kinect.tilt(deg);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, "Périphérique Kinect Introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
			stop();
			if (frame != null) {
				frame.setVisible(false);
			}
		}
	}

	public void draw() {
		background(255);

		// Run the tracking analysis
		tracker.track();
		// Show the image
		tracker.display();

		// Let's draw the raw location
		PVector v1 = tracker.getPos();
		fill(100,250,50,200);
		noStroke();
		ellipse(v1.x,v1.y,20,20);

		/*
		// Let's draw the "lerped" location
		PVector v2 = tracker.getLerpedPos();
		fill(50,100,250,200);
		noStroke();
		ellipse(v2.x,v2.y,20,20);
		 */

		// Display some info
		int t = tracker.getThreshold()-tracker.getThresholdInit();
		String clic = "Clic automatique : ";
		if(tracker.isAuto_click()){
			clic+=" Activé (CTRL pour desactiver)";
		}
		else{
			clic+=" Desactivé (CTRL pour activer)";
		}
		fill(0);
		text("Sensibilité : "+t+" (GAUCHE pour diminuer, DROITE pour augmenter)\t\t\tECHAPE pour quitter\nInclinaison : "+deg+"� (BAS pour descendre, HAUT pour monter)\n"+clic+"\nFramerate: " + (int)frameRate,10,500);
	}

	public void keyPressed() {
		int t = tracker.getThreshold();
		if (key == CODED) {
			if (keyCode == RIGHT) {
				t+=5;
				tracker.setThreshold(t);
			} 
			else if (keyCode == LEFT) {
				t-=5;
				tracker.setThreshold(t);
			}
			else if (keyCode == UP) {
				deg++;
				deg = constrain(deg,0,30);
				kinect.tilt(deg);
			}
			else if (keyCode == DOWN) {
				deg--;
				deg = constrain(deg,0,30);
				kinect.tilt(deg);
			}
			else if(keyCode == processing.core.PConstants.CONTROL){
				tracker.setAuto_click(!tracker.isAuto_click());
			}
			else if(keyCode == processing.core.PConstants.ESC){
				stop();
				if (frame != null) {
					frame.setVisible(false);
				}
			}
		}
	}

	public void stop() {
		super.stop();
	}
	
	public void setWait(boolean b){
		tracker.setWait(b);
	}
	
	public boolean isWait(){
		return tracker.isWait();
	}
}

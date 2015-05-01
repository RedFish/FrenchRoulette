package kinectsensor;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class testKinect {
	
	public static void main(String[] args) {
		JFrame f =new JFrame("Kinect tracker");
        f.setLayout(new BorderLayout());
        f.setSize(640,570);
        AveragePointTracking embed = new AveragePointTracking();
        f.add(embed, BorderLayout.CENTER);
        embed.init(f);
        f.setVisible(true);
	}

}

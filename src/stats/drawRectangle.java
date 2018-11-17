package stats;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class drawRectangle extends JPanel{

	 public void paintComponent(Graphics g) {
		 System.out.println("DRAW");
		    super.paintComponent(g);
		    Graphics2D g2d = (Graphics2D) g;

		    g2d.setColor(new Color(212, 212, 212));
		    g2d.drawRect(10, 15, 90, 60);


		    g2d.setColor(Color.GREEN);
		    g2d.fillRect(700, 195, 90, 60);

		  }
	
}

package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.util.Random;

import javax.swing.JComponent;

import Graphe.Arete;
import Graphe.Sommet;

public class DessinGrapheKruskal extends JComponent {
	/**
	 * 
	 */
	private int nb_points;
	private Arete[] aretes;
	private Arete[] areteN;
	
	private static final long serialVersionUID = 1L;

	public DessinGrapheKruskal(int nb, Arete[] arete, Arete[] areteN) {
		this.aretes = arete;
		this.areteN = areteN;
		this.nb_points = nb;
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D gr = (Graphics2D)g;
		Random r = new Random();
		String[] num = new String[nb_points+1];
		String[] nom = {"", "Bourse", "Opera", "Etoile", "République", "St-Lazare", "Louvre", "Neuilly"};
		
		Sommet xy[] = new Sommet[nb_points+1];
		for(int i=1; i<=nb_points; i++) {
			num[i] = String.valueOf(i);
			int x = r.nextInt(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width - 70);
			int y = r.nextInt(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height - 70);
	        int j = 1;
	        while (j < i && Math.abs(xy[j].getD_x() - x) >= 50 && Math.abs(xy[j].getD_y() - y) >= 50)
	            j++;
	        if (j == i)
	        	xy[i] = new Sommet(x, y, i);
	        else
	            i--;
		}
		gr.setFont(new Font("Calibri", 1, 18));
		for(int i=1; i<=nb_points; i++) {
			xy[i].draw(gr, Color.BLACK);
		}
		for(int i=0; i<aretes.length; i++) {
			aretes[i].getD_sommet_depart().moveTo(xy[aretes[i].getD_sommet_depart().getD_numero()].getD_x(), xy[aretes[i].getD_sommet_depart().getD_numero()].getD_y());
			aretes[i].getD_sommet_arrive().moveTo(xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_x(), xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_y());
		}		
		
		for(int i=0; i<aretes.length; i++) {
			gr.setColor(Color.RED);
			gr.drawString(nom[aretes[i].getD_sommet_depart().getD_numero()], xy[aretes[i].getD_sommet_depart().getD_numero()].getD_x(), xy[aretes[i].getD_sommet_depart().getD_numero()].getD_y());
			gr.drawString(nom[aretes[i].getD_sommet_arrive().getD_numero()], xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_x(), xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_y());
			
			aretes[i].draw(gr);
			
			gr.setColor(Color.darkGray);
			
		}	
		
	}
}

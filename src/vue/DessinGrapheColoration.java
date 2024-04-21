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

public class DessinGrapheColoration extends JComponent {
	/**
	 * 
	 */
	private int nb_points;
	private Arete[] aretes;
	private int[] coloration;
	int nb_couleur;
	private static final long serialVersionUID = 1L;

	public DessinGrapheColoration(int nb, Arete[] arete, int[] coloration, int nb_chromatique) {
		this.aretes = arete;
		this.nb_points = nb;
		this.coloration = coloration;
		this.nb_couleur = nb_chromatique;
	}
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D gr = (Graphics2D)g;
		Random r = new Random();
		String[] num = new String[nb_points+1];
		
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
		
		Color[] couleur = new Color[nb_couleur+1];
		for(int i=1; i<=nb_couleur; i++) {
			int rouge = r.nextInt(255);
			int vert = r.nextInt(255);
			int blue = r.nextInt(255);
			int j=1;
			while(j<i && !couleur[j].equals(new Color(rouge, vert, blue)))
				j++;
			if(j == i)
				couleur[i] = new Color(rouge, vert, blue);
			else
				i--;
		}
		for(int i=1; i<=nb_points; i++) {
			xy[i].draw(gr, couleur[coloration[i]]);
		}
		for(int i=0; i<aretes.length; i++) {
			aretes[i].getD_sommet_depart().moveTo(xy[aretes[i].getD_sommet_depart().getD_numero()].getD_x(), xy[aretes[i].getD_sommet_depart().getD_numero()].getD_y());
			aretes[i].getD_sommet_arrive().moveTo(xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_x(), xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_y());
		}
		gr.setColor(Color.BLACK);
		for(int i=0; i<aretes.length; i++) {
			gr.setColor(Color.RED);
			gr.drawString(num[aretes[i].getD_sommet_depart().getD_numero()], xy[aretes[i].getD_sommet_depart().getD_numero()].getD_x(), xy[aretes[i].getD_sommet_depart().getD_numero()].getD_y());
			gr.drawString(num[aretes[i].getD_sommet_arrive().getD_numero()], xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_x(), xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_y());
			//gr.drawLine(xy[aretes[i].getD_sommet_depart().getD_numero()].getD_x()+5, xy[aretes[i].getD_sommet_depart().getD_numero()].getD_y()+5, xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_x()+5, xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_y()+5);
			aretes[i].draw(gr, Color.BLACK, false);
		}	
		
	}
}

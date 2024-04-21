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

public class DessinGraphe extends JComponent {
	/**
	 * 
	 */
	private int nb_points;
	private Arete[] aretes;
	private String TypeGraphe;
	private static final long serialVersionUID = 1L;

	public DessinGraphe(int nb, Arete[] arete, String Type) {
		this.aretes = arete;
		this.nb_points = nb;
		this.TypeGraphe = Type;
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
		//gr.setColor(Color.BLACK);
		//gr.drawArc(200, 200, 50, 50, 10, 90);
		for(int i=1; i<=nb_points; i++) {
			xy[i].draw(gr, Color.BLACK);
		}
		for(int i=0; i<aretes.length; i++) {
			aretes[i].getD_sommet_depart().moveTo(xy[aretes[i].getD_sommet_depart().getD_numero()].getD_x(), xy[aretes[i].getD_sommet_depart().getD_numero()].getD_y());
			aretes[i].getD_sommet_arrive().moveTo(xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_x(), xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_y());
		}
		
		String[] poids = new String[aretes.length];
		for(int i=0; i<aretes.length; i++) {
			gr.setColor(Color.BLACK);
			poids[i] = String.valueOf(aretes[i].getD_poids());
			//gr.drawString(num[aretes[i].getD_sommet_depart().getD_numero()], xy[aretes[i].getD_sommet_depart().getD_numero()].getD_x(), xy[aretes[i].getD_sommet_depart().getD_numero()].getD_y());
			//gr.drawString(num[aretes[i].getD_sommet_arrive().getD_numero()], xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_x(), xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_y());
			//gr.drawLine(xy[aretes[i].getD_sommet_depart().getD_numero()].getD_x()+5, xy[aretes[i].getD_sommet_depart().getD_numero()].getD_y()+5, xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_x()+5, xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_y()+5);
			
			gr.setColor(Color.darkGray);
			if(TypeGraphe.toLowerCase().equals("nov") || TypeGraphe.toLowerCase().equals("ov")) {
					gr.drawString(poids[i], (xy[aretes[i].getD_sommet_depart().getD_numero()].getD_x()+xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_x())/2, (xy[aretes[i].getD_sommet_depart().getD_numero()].getD_y()+xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_y())/2);
					aretes[i].draw(gr, Color.BLACK, true);
			}
			else
				aretes[i].draw(gr, Color.BLACK, false);
			if(TypeGraphe.toLowerCase().equals("ov") || TypeGraphe.toLowerCase().equals("onv")) {
				int mx1 = (xy[aretes[i].getD_sommet_depart().getD_numero()].getD_x()+xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_x())/2;
				int my1 = (xy[aretes[i].getD_sommet_depart().getD_numero()].getD_y()+xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_y())/2;
				if(xy[aretes[i].getD_sommet_depart().getD_numero()].getD_x() < xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_x()) {
					gr.setColor(Color.RED);
					gr.drawString(">", mx1, my1+10);
				}
				else {
					gr.setColor(Color.RED);
					gr.drawString("<", mx1, my1+10);
				}
			}

		}	
		
	}
}

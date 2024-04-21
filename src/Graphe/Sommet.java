package Graphe;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Objects;

public class Sommet {
	private int d_x, d_y;
	private int d_numero;
	
	public Sommet(int x, int y, int numero){
		d_x = x;
		d_y = y; 
		d_numero = numero;
	}
	
	public Sommet(){
		this(0);
	}

	public Sommet(int numero){
		this(0,0, numero);
	}

	public int getD_x()
	{
	    return d_x;
	}

	public int getD_y()
	{
	    return d_y;
	}
	
	public void setD_x(int d_x) {
		this.d_x = d_x;
	}

	public void setD_y(int d_y) {
		this.d_y = d_y;
	}

	public int getD_numero()
	{
	    return d_numero;
	}
	public void setD_numero(int numero)
	{
	    d_numero = numero;
	}

	public void moveTo(int x, int y)
	{
	    d_x = x;
	    d_y = y;
	}

	private void move(int dx, int dy)
	{
	    d_x += dx;
	    d_y += dy;
	}

	private void print()
	{
	    System.out.print("("+d_x+", "+d_y+")");
	}
	public void draw(Graphics2D gr, Color couleur) {
		gr.setColor(couleur);
		gr.drawString(d_numero+"", getD_x(), getD_y());
		gr.fillOval(getD_x(), getD_y(), 30, 30);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(d_numero, d_x, d_y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sommet other = (Sommet) obj;
		return d_numero == other.d_numero && d_x == other.d_x && d_y == other.d_y;
	}
	
}

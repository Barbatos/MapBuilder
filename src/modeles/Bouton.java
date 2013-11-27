package modeles;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
 
public class Bouton extends JButton implements MouseListener{
	private static final long serialVersionUID = 1L;
	private String nom;
	private int x;
	private int y;
	private int hauteur;
	private int largeur;

	public Bouton(String nom, int x, int y, int largeur, int hauteur){
		super(nom);
		this.nom = nom;
		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this. hauteur = hauteur;
		this.addMouseListener(this);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(new Color(200, 200, 200));
		g.fillRect(this.x, this.y, this.largeur, this.hauteur);

		FontMetrics fm = g.getFontMetrics();
		int h = fm.getHeight();
		int l = fm.stringWidth(this.nom);

		g.setColor(new Color(45, 45, 45));
	    g.drawString(this.nom, x - l / 2 + this.largeur / 2, y + h / 4 + this.hauteur / 2);
	}
	
	public void mouseClicked(MouseEvent event){
	}

	public void mouseEntered(MouseEvent event){
	}

	public void mouseExited(MouseEvent event){
	}

	public void mousePressed(MouseEvent event){
	}

	public void mouseReleased(MouseEvent event){		
	}

}
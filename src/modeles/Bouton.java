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

	public Bouton(String nom, int x, int y, int hauteur, int largeur){
		super(nom);
		this.nom = nom;
		this.x = x;
		this.y = y;
		this. hauteur = hauteur;
		this.largeur = largeur;
		this.addMouseListener(this);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(new Color(200, 200, 200));
		g.fillRect(x, y, hauteur, largeur);

		FontMetrics fm = g.getFontMetrics();
		int h = fm.getHeight();
		int l = fm.stringWidth(this.nom);

	    g.drawString(this.nom, this.getWidth() / 2 - (l / 2), (this.getHeight() / 2) + (h / 4));
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
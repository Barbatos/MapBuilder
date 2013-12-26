package vues;

import java.awt.Adjustable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import modeles.Ligne;

public class HorairesPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public final static int WIDTH = 1000;
	public final static int HEIGHT = 500;
	
	private JFrame fenetre;
	private Ligne ligne;
	private int scrollX = 0;
	private int scrollY = 0;
	
	public HorairesPanel(Ligne ligne){
		this.ligne = ligne;
		int nbHoraireMax = 0;

	    setLayout(new BorderLayout());
	    
	    if((this.ligne.getListeStations().size() + 1) * 30 + 15 > HEIGHT){
	    	JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL, 0, HEIGHT, 0, (this.ligne.getListeStations().size() + 1) * 30);
	    	vbar.addAdjustmentListener(new ajustement());
	    	add(vbar, BorderLayout.EAST);
	    }

	    for(int i = 0;i < this.ligne.getListeStations().size();i++){
	    	if(this.ligne.getStation(i).getListeHoraires().size() > nbHoraireMax){
	    		nbHoraireMax = this.ligne.getStation(i).getListeHoraires().size();
	    	}
	    }
	    
	    System.out.println(nbHoraireMax);
	    
	    if(180 + nbHoraireMax * 90 + 45 > WIDTH){
	    	JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL, 0, WIDTH, 0, 180 + nbHoraireMax * 90 + 45);
	    	hbar.addAdjustmentListener(new ajustement());
	    	add(hbar, BorderLayout.SOUTH);
	    }

		fenetre = new JFrame();
		
		// On définit le titre de la fenêtre
		fenetre.setTitle("Horaires " + ligne.getNom());

		// Taille par défaut
		fenetre.setSize(WIDTH, HEIGHT);

		// On centre la fenêtre
		fenetre.setLocationRelativeTo(null);

		// On empêche le redimensionnement de la fenêtre
		fenetre.setResizable(false);
		
		// Import de la classe Affichage
		fenetre.setContentPane(this);
		
		fenetre.setVisible(true);
	}
	
	class ajustement implements AdjustmentListener{
		public void adjustmentValueChanged(AdjustmentEvent e){
			if(e.getAdjustable().getOrientation() == JScrollBar.HORIZONTAL){
				scrollX = e.getValue() * (-1);
			}
			else if(e.getAdjustable().getOrientation() == JScrollBar.VERTICAL){
				scrollY = e.getValue() * (-1);
			}
			
			
				
			fenetre.repaint();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(new Color(175, 175, 175));
		
		for(int i = 0;i < this.ligne.getListeStations().size();i++){
			g.setColor(new Color(10, 10, 10));
			g.drawRect(scrollX, i * 30 + scrollY, 180, 30);
			
			int h = g.getFontMetrics().getHeight();
			int l = g.getFontMetrics().stringWidth(this.ligne.getStation(i).getNom());
			
			g.drawString(this.ligne.getStation(i).getNom(), 0 - l / 2 + 180 / 2 + scrollX, (i * 30) + h / 4 + 30 / 2 + scrollY);
			
			for(int j = 0;j < this.ligne.getStation(i).getListeHoraires().size();j++){
				this.ligne.getStation(i).getListeHoraires().elementAt(j).dessinerHoraire(g, 180 + j * 90 + scrollX, i * 30 + scrollY);
			}
		}
	}
	
}

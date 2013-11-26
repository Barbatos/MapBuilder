package controleurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import modeles.*;
import vues.*;

public class Controleur {
	private Vue vue;
	private MouseListener mouseListener;
	
	public Controleur(Vue _vue){
		this.vue = _vue;
	}
	
	public void initialiser(){
		
		mouseListener = new MouseListener(){
			public void mouseClicked(MouseEvent event){
				System.out.println("click. x:"+event.getX()+", y:"+event.getY());
			}
			
			public void mouseEntered(MouseEvent event){}
			public void mouseExited(MouseEvent event){}
			public void mouseReleased(MouseEvent event){}
			public void mousePressed(MouseEvent event){}
			
		};
		
		this.vue.getPanel().addMouseListener(mouseListener);
	}
}

/*
 * This software is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.

 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author Bardoll
 * @author Charles "Barbatos" Duprey <barbatos@f1m.fr>
 * @author Soullessoni
 * @author Thornydre
 * 
 * @copyright (C) 2013 - 2014 Dat Java DreamTeam
 * 
 */

package main;

import javax.swing.SwingUtilities;

import controleurs.Controleur;
 /**
  * Le projet est lance grace a cette classe
  *
  * @version 1.0
  */
public class MapBuilder {
	/**
	 * Methode main
	 *
	 * @param args[] 
	 */
	public static void main(String args[]){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			 /**
			  * Methode de lancement
			  */
			public void run(){
				Controleur controleur = new Controleur();
				controleur.initialiser();
			}
		});
	}
}

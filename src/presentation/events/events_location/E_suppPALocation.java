package presentation.events.events_location;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import dao.Location_dao;
import metier.Location;
import presentation.F_activite;
import presentation.F_gestionLocation;
import presentation.exceptions.location.NoRowSelectedException;

public class E_suppPALocation implements ActionListener{
	private Location l;

	public E_suppPALocation(Location l) {
		this.l = l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			if(F_activite.table.getSelectedRow() == -1)
				throw new NoRowSelectedException();
			
			else {
				//System.out.println(l.getIdLocation());
				LocalDate dateLoc = LocalDate.parse(l.getDate_locat());
				LocalDate endLoc = LocalDate.parse(l.getDate_locat()).plusDays(l.getDuree_prev());
				LocalDate now = java.time.LocalDate.now();
				
		        if (now.isAfter(dateLoc)) {
		        	if (now.isBefore(endLoc)) {
		        		JOptionPane.showMessageDialog(null,"Impossible!\nLocation déjà en cours","Alerte",JOptionPane.ERROR_MESSAGE);
		        	}
		        	else {
		        	JOptionPane.showMessageDialog(null,"Impossible!\nLocation archivée","Alerte",JOptionPane.ERROR_MESSAGE);
		        	}
		        	
		        } else {
		        	Location_dao D_location = new Location_dao();
						
					int r = D_location.delete(l);
					
					if(r==1) 
						JOptionPane.showMessageDialog(null,"Suppression avec succés","Message",JOptionPane.INFORMATION_MESSAGE);
		        }     
			}
				
		}
		catch(NoRowSelectedException E) {
			JOptionPane.showMessageDialog(null,E.getMessage(),"Alerte",JOptionPane.WARNING_MESSAGE);
		}
	}	
		
}

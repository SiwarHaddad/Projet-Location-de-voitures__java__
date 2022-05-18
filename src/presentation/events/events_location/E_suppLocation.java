package presentation.events.events_location;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.Location_dao;
import metier.Location;
import presentation.F_gestionLocation;
import presentation.exceptions.location.NoRowSelectedException;

public class E_suppLocation implements ActionListener{
	private Location l;

	public E_suppLocation(Location l) {
		this.l = l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			if(F_gestionLocation.table.getSelectedRow() == -1)
				throw new NoRowSelectedException();
			
			else {
				System.out.println(l.getIdLocation());
				//Client client = new Client(f);
				Location_dao D_location = new Location_dao();
				
				int r = D_location.delete(l);
				
				if(r==1) 
					JOptionPane.showMessageDialog(null,"Suppression avec succés","Message",JOptionPane.INFORMATION_MESSAGE);
			}
				
		}
		catch(NoRowSelectedException E) {
			JOptionPane.showMessageDialog(null,E.getMessage(),"Alerte",JOptionPane.WARNING_MESSAGE);
		}
	}	
		
}

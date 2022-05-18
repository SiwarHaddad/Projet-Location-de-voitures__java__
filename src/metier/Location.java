package metier;

public class Location {
	private int idLocation;
	private int idVoiture;
	private int idClient;
	private String date_locat;
	private int duree_prev;
	private Double total_locat;
	
	public Location() {
		
	}

	public Location(int idLocation) {super();
		this.idLocation = idLocation;
	}

	public Location(int idVoiture, int idClient, String date_locat, int duree_prev, Double total_locat) {
		this.idVoiture = idVoiture;
		this.idClient = idClient;
		this.date_locat = date_locat;
		this.duree_prev = duree_prev;
		this.total_locat = total_locat;
	}

	public Location(int idLocation, int idVoiture, int idClient, String date_locat, int duree_prev,
			Double total_locat) {
		this.idLocation = idLocation;
		this.idVoiture = idVoiture;
		this.idClient = idClient;
		this.date_locat = date_locat;
		this.duree_prev = duree_prev;
		this.total_locat = total_locat;
	}

	public int getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(int idLocation) {
		this.idLocation = idLocation;
	}

	public int getIdVoiture() {
		return idVoiture;
	}

	public void setIdVoiture(int idVoiture) {
		this.idVoiture = idVoiture;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getDate_locat() {
		return date_locat;
	}

	public void setDate_locat(String date_locat) {
		this.date_locat = date_locat;
	}

	public int getDuree_prev() {
		return duree_prev;
	}

	public void setDuree_prev(int duree_prev) {
		this.duree_prev = duree_prev;
	}

	public Double getTotal_locat() {
		return total_locat;
	}

	public void setTotal_locat(Double total_locat) {
		this.total_locat = total_locat;
	}
	
	
}

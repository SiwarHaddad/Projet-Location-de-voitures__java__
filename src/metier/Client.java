package metier;

public class Client {
	private int idClient;
	private String nomClient;
	private String prenomClient;
	private String mdpClient;
	private String pseudo;
	private String telClient;
	private String adresseClient;
	
	//constructors
	public Client() {
		
	}
	public Client(int idClient) {
		this.idClient = idClient;
	}

	public Client(String nomClient, String prenomClient, String mdpClient, String pseudo, String telClient) {
		super();
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.mdpClient = mdpClient;
		this.pseudo = pseudo;
		this.telClient = telClient;
	}
	public Client(String nomClient, String prenomClient, String mdpClient, String pseudo, String telClient,
			String adresseClient) {
		super();
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.mdpClient = mdpClient;
		this.pseudo = pseudo;
		this.telClient = telClient;
		this.adresseClient = adresseClient;
	}

	public Client(int idClient, String nomClient, String prenomClient, String pseudo,
			String telClient, String adresseClient) {

		this.idClient = idClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.pseudo = pseudo;
		this.telClient = telClient;
		this.adresseClient = adresseClient;
	}

	
	public Client(int idClient, String nomClient, String prenomClient, String mdpClient, String pseudo,
			String telClient, String adresseClient) {

		this.idClient = idClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.mdpClient = mdpClient;
		this.pseudo = pseudo;
		this.telClient = telClient;
		this.adresseClient = adresseClient;
	}

	
	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getPrenomClient() {
		return prenomClient;
	}

	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}

	public String getMdpClient() {
		return mdpClient;
	}

	public void setMdpClient(String mdpClient) {
		this.mdpClient = mdpClient;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getTelClient() {
		return telClient;
	}

	public void setTelClient(String telClient) {
		this.telClient = telClient;
	}

	public String getAdresseClient() {
		return adresseClient;
	}

	public void setAdresseClient(String adresseClient) {
		this.adresseClient = adresseClient;
	}
	
	
}

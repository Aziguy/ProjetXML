/**
 * 
 */
package vn.edu.vnu.ifi.xml.domaine;

/**
 * Cette classe contient toutes les informations / attribut d'un client
 * Elle permettra de cr�er un objet de type Client
 * @author KENGNI Hippolyte
 * @version 0.1
 */
public class Clients {
	
	private String idClients;
	private String noms;
	private String prenoms;
	private String dateNaissance;
	private String telephone;
	private String adresse;
	private String Gestionnaires_idGestionnaires;

	/**
	 * Constructeur par d�faut de la classe Clients
	 */
	public Clients() {
		super();
	}


	/**
	 * Constructeur de la classe Clients qui prend en param�tre tous les attributs de celle-ci
	 * @param idClients
	 * @param noms
	 * @param prenoms
	 * @param dateNaissance
	 * @param telephone
	 * @param adresse
	 */
	public Clients(String idClients, String noms, String prenoms, String dateNaissance, String telephone, String adresse) {
		super();
		this.idClients = idClients;
		this.noms = noms;
		this.prenoms = prenoms;
		this.dateNaissance = dateNaissance;
		this.telephone = telephone;
		this.adresse = adresse;
	}
	
	/**
	 * Constructeur de la classe Clients qui prend en param�tre 5 attributs de Clients
	 * @param idClients
	 * @param noms
	 * @param prenoms
	 * @param telephone
	 * @param adresse
	 */
	public Clients(String idClients, String noms, String prenoms, String telephone, String adresse) {
		super();
		this.idClients = idClients;
		this.noms = noms;
		this.prenoms = prenoms;
		this.telephone = telephone;
		this.adresse = adresse;
	}


	//GETTER AND SETTER


	/**
	 * Cette m�thode permet d'obtenir l'id d'un Clients
	 * @return the idClients
	 */
	public String getIdClients() {
		return idClients;
	}


	/**
	 * Cette m�thode permet de modifier l'id d'un Clients
	 * @param idClients the idClients to set
	 */
	public void setIdClients(String idClients) {
		this.idClients = idClients;
	}


	/**
	 * Cette m�tode permet d'obtenir le noms d'un Clients
	 * @return the noms
	 */
	public String getNoms() {
		return noms;
	}


	/**
	 * Cette m�thode permet de modifier le noms d'un Clients
	 * @param noms the noms to set
	 */
	public void setNoms(String noms) {
		this.noms = noms;
	}


	/**
	 * Cette m"thode permet d'obtenir le prenoms d'un Clients
	 * @return the prenoms
	 */
	public String getPrenoms() {
		return prenoms;
	}


	/**
	 * Cette m�thode permet de modifier le prenoms d'un Clients
	 * @param prenoms the prenoms to set
	 */
	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}


	/**
	 * Cette m�thode permet d'obtenir la date de naissance d'un Clients
	 * @return the dateNaissance
	 */
	public String getDateNaissance() {
		return dateNaissance;
	}


	/**
	 * Cette m�thode permet de modifier la date de naissance d'un Clients
	 * @param dateNaissance the dateNaissance to set
	 */
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	/**
	 * Cette m�thode permet d'obtenir le t�l�phone d'un Clients
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}


	/**
	 * Cette m�thode permet modifier le t�l�phone d'un Clients
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	/**
	 * Cette m�thode permet d'obtenir l'adresse d'un Clients
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}


	/**
	 * Cette m�thode permet de modifier l'adresse d'un Clients
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	/**
	 * Cette m�thode permet d'obtenir l'identifiant d'un Gestionnaire
	 * @return the gestionnaires_idGestionnaires
	 */
	public String getGestionnaires_idGestionnaires() {
		return Gestionnaires_idGestionnaires;
	}


	/**
	 * Cette m�thode permet de modifier l'identifiant d'un Gestionnaires
	 * @param gestionnaires_idGestionnaires the gestionnaires_idGestionnaires to set
	 */
	public void setGestionnaires_idGestionnaires(String gestionnaires_idGestionnaires) {
		Gestionnaires_idGestionnaires = gestionnaires_idGestionnaires;
	}
	

	//METHODE toString
	
	/**
	 * Cette m�thode permet de connaitre l'�tat de l'objet Clients 
	 */
	@Override
	public String toString() {
		return  noms + " " + prenoms ;
	}

}

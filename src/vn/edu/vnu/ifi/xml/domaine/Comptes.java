/**
 * 
 */
package vn.edu.vnu.ifi.xml.domaine;

/**
 * Cette classe contient toutes les informations / attribut d'un comptes
 * Elle permettra de créer un objet de type Comptes
 * @author KENGNI Hippolyte
 * @version 0.1
 */
public class Comptes {
	
	private int numComptes;
	private int numIdent;
	private float solde;
	private String TypesComptes_idTypesComptes;
	private String Clients_idClients;
	private String Gestionnaires_idGestionnaires;

	/**
	 * Constructeur par défaut de la classe Comptes
	 */
	public Comptes() {
		super();
	}
	
	
	/**
	 * Constructeur de la classe qui prend en paramètre tous les attributs de la classe
	 * @param numComptes
	 * @param numIdent
	 * @param solde
	 * @param typesComptes_idTypesComptes
	 * @param clients_idClients
	 * @param gestionnaires_idGestionnaires
	 */
	public Comptes(int numComptes, int numIdent, float solde, String typesComptes_idTypesComptes,
			String clients_idClients, String gestionnaires_idGestionnaires) {
		super();
		this.numComptes = numComptes;
		this.numIdent = numIdent;
		this.solde = solde;
		this.TypesComptes_idTypesComptes = typesComptes_idTypesComptes;
		this.Clients_idClients = clients_idClients;
		this.Gestionnaires_idGestionnaires = gestionnaires_idGestionnaires;
	}


	//GETTER AND SETTERS
	
	/**
	 * Cette méthode permet d'obtenir le numéro de compte
	 * @return the numComptes
	 */
	public int getNumComptes() {
		return numComptes;
	}

	/**
	 * Cette méthode permet de modifier le numéro d'un compte
	 * @param numComptes the numComptes to set
	 */
	public void setNumComptes(int numComptes) {
		this.numComptes = numComptes;
	}

	/**
	 * Cette permet d'obtenir l'identifiant d'un compte
	 * @return the numIdent
	 */
	public int getNumIdent() {
		return numIdent;
	}

	/**
	 * Cette méthode permet de modifier l'identifiant d'un compte
	 * @param numIdent the numIdent to set
	 */
	public void setNumIdent(int numIdent) {
		this.numIdent = numIdent;
	}

	/** 
	 * Cette méthode permet d'obtenir le solde d'un compte
	 * @return the solde
	 */
	public float getSolde() {
		return solde;
	}

	/**
	 * Cette méthode permet de modifier le solde d'un compte
	 * @param solde the solde to set
	 */
	public void setSolde(float solde) {
		this.solde = solde;
	}

	/**
	 * Cette méthode permet d'obtenir le type d'un compte
	 * @return the typesComptes_idTypesComptes
	 */
	public String getTypesComptes_idTypesComptes() {
		return TypesComptes_idTypesComptes;
	}

	/**
	 * Cette méthode permet de modifier le type d'un compte
	 * @param typesComptes_idTypesComptes the typesComptes_idTypesComptes to set
	 */
	public void setTypesComptes_idTypesComptes(String typesComptes_idTypesComptes) {
		TypesComptes_idTypesComptes = typesComptes_idTypesComptes;
	}

	/**
	 * Cette méthode permet d'obtenir l'id d'un Clients
	 * @return the clients_idClients
	 */
	public String getClients_idClients() {
		return Clients_idClients;
	}

	/**
	 * Cette méthode permet de modifier l'id d'un Clients
	 * @param clients_idClients the clients_idClients to set
	 */
	public void setClients_idClients(String clients_idClients) {
		Clients_idClients = clients_idClients;
	}

	/**
	 * Cette méthode permet d'obtenir l'id d'un Gestionnaires
	 * @return the gestionnaires_idGestionnaires
	 */
	public String getGestionnaires_idGestionnaires() {
		return Gestionnaires_idGestionnaires;
	}

	/**
	 * Cette méthode permet de modifier l'id d'un Gestionnaire
	 * @param gestionnaires_idGestionnaires the gestionnaires_idGestionnaires to set
	 */
	public void setGestionnaires_idGestionnaires(String gestionnaires_idGestionnaires) {
		Gestionnaires_idGestionnaires = gestionnaires_idGestionnaires;
	}
	
	//METHODE toString()
	
	/**
	 * Cette méthode permet de connaitre l'état d'un objet de type Comptes
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return numComptes + " " + Clients_idClients ;
	}
	
}

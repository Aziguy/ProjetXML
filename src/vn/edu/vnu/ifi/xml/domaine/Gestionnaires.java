/**
 * 
 */
package vn.edu.vnu.ifi.xml.domaine;

/**
 * Cette classe contient toutes les informations / attribut d'un gestionnaires
 * Elle permettra de créer un objet de type Gestionnaires
 * @author KENGNI Hippolyte
 *@version 0.1 
 */
public class Gestionnaires extends Clients {
	
	private String idGestionnaires;
	private String dateEmbauche;

	/**
	 * Constructeur par défaut de la classe Gestionnaires
	 */
	public Gestionnaires() {
		super();
	}

	/**
	 * Constructeur de la classe Gestionnaires qui prend en paramètre tous les attributs de la classe 
	 * @param idClients
	 * @param noms
	 * @param prenoms
	 * @param telephone
	 * @param adresse
	 * @param idGestionnaires 
	 * @param dateEmbauche 
	 */
	public Gestionnaires(String idClients, String noms, String prenoms, String telephone, String adresse, String idGestionnaires, String dateEmbauche) {
		super(idClients, noms, prenoms, telephone, adresse);
		this.idGestionnaires = idGestionnaires;
		this.dateEmbauche = dateEmbauche;
	}
	
	//GETTERS AND SETTERS

	/**
	 * Cette méthode permet d'obtenir l'id d'un Gestionnaire
	 * @return the idGestionnaires
	 */
	public String getIdGestionnaires() {
		return idGestionnaires;
	}

	/**
	 * Cette méthode permet de modifier l'id d'un Gestionnaires
	 * @param idGestionnaires the idGestionnaires to set
	 */
	public void setIdGestionnaires(String idGestionnaires) {
		this.idGestionnaires = idGestionnaires;
	}

	/**
	 * Cette méthode permet d'obtenir la date d'embauche d'un Gestionnaire
	 * @return the dateEmbauche
	 */
	public String getDateEmbauche() {
		return dateEmbauche;
	}

	/**
	 * Cette méthode permet de modifier la date d'embauche d'un Gestionnaire
	 * @param dateEmbauche the dateEmbauche to set
	 */
	public void setDateEmbauche(String dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

	/**
	 * Cette méthode permet de connaitre l'état de l'objet Gestionnaires
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getNoms() + " " + getPrenoms();
	}

	
	
}

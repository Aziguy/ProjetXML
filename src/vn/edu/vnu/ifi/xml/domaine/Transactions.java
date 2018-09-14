/**
 * 
 */
package vn.edu.vnu.ifi.xml.domaine;

/**
 * Cette classe contient toutes les informations / attribut d'une Transactions
 * Elle permettra de créer un objet de type Transactions
 * @author KENGNI Hippolyte
 * @version 0.1
 */
public class Transactions {
	
	private String idTransaction;
	private int compte;
	private String date;
	private String typeTransaction;
	private float montant;
	private String Gestionnaires_idGestionnaires;

	/**
	 * Constructeur par défaut de la classe Transaction
	 */
	public Transactions() {
		super();
	}

	/**
	 * Constructeur de la classe Transactions prend en paramètre tous les attibuts de la classe
	 * @param idTransaction
	 * @param date
	 * @param typeTransaction
	 * @param montant
	 * @param gestionnaires_idGestionnaires
	 */
	public Transactions(String idTransaction, String date, String typeTransaction, float montant,
			String gestionnaires_idGestionnaires) {
		super();
		this.idTransaction = idTransaction;
		this.date = date;
		this.typeTransaction = typeTransaction;
		this.montant = montant;
		this.Gestionnaires_idGestionnaires = gestionnaires_idGestionnaires;
	}
	
	//GETTER AND SETTERS

	/**
	 * Cette méthode permet d'obtenir l'id d'une transaction
	 * @return the idTransaction
	 */
	public String getIdTransaction() {
		return idTransaction;
	}

	/**
	 * Cette méthode permet de modifier l'id d'une transaction
	 * @param idTransaction the idTransaction to set
	 */
	public void setIdTransaction(String idTransaction) {
		this.idTransaction = idTransaction;
	}

	/**
	 * Cette méthode permet d'obtenir le compte de la transaction
	 * @return the compte
	 */
	public int getCompte() {
		return compte;
	}

	/**
	 * Cette méthode permet de modifier le compte de la transaction
	 * @param compte the compte to set
	 */
	public void setCompte(int compte) {
		this.compte = compte;
	}

	/**
	 * Cette méthode permet d'obtenir la date d'une transaction
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Cette méthode permet de modifier la date d'une transaction
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Cette méthode permet d'obtenir la type d'une transaction
	 * @return the typeTransaction
	 */
	public String getTypeTransaction() {
		return typeTransaction;
	}

	/**
	 * Cette méthode permet de modifier le type d'une transaction
	 * @param typeTransaction the typeTransaction to set
	 */
	public void setTypeTransaction(String typeTransaction) {
		this.typeTransaction = typeTransaction;
	}

	/**
	 * Cette méthode permet d'obtenir le montant d'une transaction
	 * @return the montant
	 */
	public float getMontant() {
		return montant;
	}

	/**
	 * Cette méthode permet de modifier le montant d'une transaction
	 * @param montant the montant to set
	 */
	public void setMontant(float montant) {
		this.montant = montant;
	}

	/**
	 * Cette méthode permet d'obtenir l'identifiant d'un gestionnaire
	 * @return the gestionnaires_idGestionnaires
	 */
	public String getGestionnaires_idGestionnaires() {
		return Gestionnaires_idGestionnaires;
	}

	/**
	 * Cette méthode permet de modifier l'identifiant d'un gestionnaire
	 * @param gestionnaires_idGestionnaires the gestionnaires_idGestionnaires to set
	 */
	public void setGestionnaires_idGestionnaires(String gestionnaires_idGestionnaires) {
		Gestionnaires_idGestionnaires = gestionnaires_idGestionnaires;
	}

	//METHODE toString()
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Transactions [idTransaction=" + idTransaction + ", date=" + date + ", typeTransaction="
				+ typeTransaction + ", montant=" + montant + ", Gestionnaires_idGestionnaires="
				+ Gestionnaires_idGestionnaires + "]";
	}

}

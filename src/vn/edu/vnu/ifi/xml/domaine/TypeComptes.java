/**
 * 
 */
package vn.edu.vnu.ifi.xml.domaine;

/**
 * @author KENGNI Hippolyte
 *
 */
public class TypeComptes {
	private String idTypeComptes;
	private String libelle;
	private int tauxInteret;

	/**
	 * Constructeur par défaut de la class TypeComptes
	 */
	public TypeComptes() {
		super();
	}
	
	//GETTEURS AND SETTEURS DE LA CLASS

	/**
	 * Cette méthode permet d'obtenir l'idendifiant d'un compte
	 * @return the idTypeComptes
	 */
	public String getIdTypeComptes() {
		return idTypeComptes;
	}

	/**
	 * Cette méthode permet de modifier l'identifiant d'un compte
	 * @param idTypeComptes the idTypeComptes to set
	 */
	public void setIdTypeComptes(String idTypeComptes) {
		this.idTypeComptes = idTypeComptes;
	}

	/**
	 * Cette méthode permet d'obtenir le libellé d'un compte
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Cette méthode permet de modifier le libellé d'un compte
	 * @param libelleComptes the libelleComptes to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * Cette méthode permet d'obtenir un taux d'interet d'un compte
	 * @return the tauxInteret
	 */
	public int getTauxInteret() {
		return tauxInteret;
	}

	/**
	 * Cette méthode permet de modifier le taux d'intérêt d'un compte
	 * @param tauxInteret the tauxInteret to set
	 */
	public void setTauxInteret(int tauxInteret) {
		this.tauxInteret = tauxInteret;
	}

	/**
	 * Cette méthode permet de connaitre l'état de l'objet
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return libelle;
	}
	
}

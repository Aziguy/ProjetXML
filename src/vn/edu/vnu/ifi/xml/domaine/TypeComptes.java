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
	 * Constructeur par d�faut de la class TypeComptes
	 */
	public TypeComptes() {
		super();
	}
	
	//GETTEURS AND SETTEURS DE LA CLASS

	/**
	 * Cette m�thode permet d'obtenir l'idendifiant d'un compte
	 * @return the idTypeComptes
	 */
	public String getIdTypeComptes() {
		return idTypeComptes;
	}

	/**
	 * Cette m�thode permet de modifier l'identifiant d'un compte
	 * @param idTypeComptes the idTypeComptes to set
	 */
	public void setIdTypeComptes(String idTypeComptes) {
		this.idTypeComptes = idTypeComptes;
	}

	/**
	 * Cette m�thode permet d'obtenir le libell� d'un compte
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Cette m�thode permet de modifier le libell� d'un compte
	 * @param libelleComptes the libelleComptes to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * Cette m�thode permet d'obtenir un taux d'interet d'un compte
	 * @return the tauxInteret
	 */
	public int getTauxInteret() {
		return tauxInteret;
	}

	/**
	 * Cette m�thode permet de modifier le taux d'int�r�t d'un compte
	 * @param tauxInteret the tauxInteret to set
	 */
	public void setTauxInteret(int tauxInteret) {
		this.tauxInteret = tauxInteret;
	}

	/**
	 * Cette m�thode permet de connaitre l'�tat de l'objet
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return libelle;
	}
	
}

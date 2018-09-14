/**
 * 
 */
package vn.edu.vnu.ifi.xml.domaine;

/**
 * @author KENGNI Hippolyte
 *
 */
public class Students {
	
	private String matricule;
	private String noms;
	private String prenoms;
	private String dateNaissance;
	private String lieuNaissance;
	private String adresse;
	private String telephone;
	private String niveau;
	private String police;

	/**
	 * Constructeur par défaut de la class Students
	 */
	public Students() {
		
	}

	/**
	 * Constructeur de la class Students avec tous les paramètres
	 * @param matricule
	 * @param noms
	 * @param prenoms
	 * @param dateNaissance
	 * @param lieuNaissance
	 * @param adresse
	 * @param telephone
	 * @param niveau
	 * @param police
	 */
	public Students(String matricule, String noms, String prenoms, String dateNaissance, String lieuNaissance,
			String adresse, String telephone, String niveau, String police) {
		super();
		this.matricule = matricule;
		this.noms = noms;
		this.prenoms = prenoms;
		this.dateNaissance = dateNaissance;
		this.lieuNaissance = lieuNaissance;
		this.adresse = adresse;
		this.telephone = telephone;
		this.niveau = niveau;
		this.police = police;
	}

	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * @return the noms
	 */
	public String getNoms() {
		return noms;
	}

	/**
	 * @param noms the noms to set
	 */
	public void setNoms(String noms) {
		this.noms = noms;
	}

	/**
	 * @return the prenoms
	 */
	public String getPrenoms() {
		return prenoms;
	}

	/**
	 * @param prenoms the prenoms to set
	 */
	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}

	/**
	 * @return the dateNaissance
	 */
	public String getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * @param dateNaissance the dateNaissance to set
	 */
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/**
	 * @return the lieuNaissance
	 */
	public String getLieuNaissance() {
		return lieuNaissance;
	}

	/**
	 * @param lieuNaissance the lieuNaissance to set
	 */
	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the niveau
	 */
	public String getNiveau() {
		return niveau;
	}

	/**
	 * @param niveau the niveau to set
	 */
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	/**
	 * @return the police
	 */
	public String getPolice() {
		return police;
	}

	/**
	 * @param police the police to set
	 */
	public void setPolice(String police) {
		this.police = police;
	}

	/* 
	 * Cette méthode permet de connaitre l'état en mémoire d'un objet students
	 */
	@Override
	public String toString() {
		return "Students [matricule=" + matricule + ", noms=" + noms + ", prenoms=" + prenoms + ", dateNaissance="
				+ dateNaissance + ", lieuNaissance=" + lieuNaissance + ", adresse=" + adresse + ", telephone="
				+ telephone + ", niveau=" + niveau + ", police=" + police + "]";
	}
	
}

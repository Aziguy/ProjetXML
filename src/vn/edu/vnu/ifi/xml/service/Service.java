package vn.edu.vnu.ifi.xml.service;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vn.edu.vnu.ifi.xml.dao.IDao;
import vn.edu.vnu.ifi.xml.domaine.Clients;
import vn.edu.vnu.ifi.xml.domaine.Comptes;
import vn.edu.vnu.ifi.xml.domaine.Gestionnaires;
import vn.edu.vnu.ifi.xml.domaine.Students;
import vn.edu.vnu.ifi.xml.domaine.Transactions;
import vn.edu.vnu.ifi.xml.domaine.TypeComptes;
import vn.edu.vnu.ifi.xml.utilitaire.DOMHelper;

/**
 * Cette Class contient toutes les m�thodes dont fera appel l'utilisateur
 * 
 * @author KENGNI Hippolyte
 * @version 0.1 Promo22xml
 */
/**
 * @author KENGNI Hippolyte
 *
 */
public class Service implements IService {

	private IDao dao;

	/**
	 * Constructeur par d�faut de la classe Service
	 */
	public Service() {

	}

	public Service(IDao dao) {
		super();
		this.dao = dao;
	}

	/**
	 * Cette m�thode permet de se connecter � la base de donn�es via la couche dao
	 * 
	 * @param login
	 * @param pwd
	 * @return 1 si la connexion est ok et 0 sinon
	 */
	public int connexionService(String login, String pwd) {
		return dao.connexionInterfaceDao(login, pwd);
	}

	/**
	 * Cette m�thode permet d'afficher le nom de l'utilisateur connect� via la
	 * couche dao
	 * 
	 * @param login
	 * @param pwd
	 * @return le nom et le pr�nom de l'utilisateur
	 */
	public String afficherMessageService(String recLogin) {
		return dao.afficheMessageDao(recLogin);
	}
	
	/** 
	 * Cette m�thode permet de g�n�rer un ensemble de caract�re et de nombre pour le matricule
	 * @return saltStr
	 */
	public String genererCaractereMatricule() {
		String CARACTERES = "A1B2C3D4E5F6G7H8I9J0KLMNOPQRSTUVWXYZ";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 3) { // taille de la valeur � retourner
			int index = (int) (rnd.nextFloat() * CARACTERES.length());
			salt.append(CARACTERES.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}
	
	/** 
	 * Cette m�thode permet de g�n�rer un ensemble de caract�re et de nombre pour l'assurance
	 * @return saltStr
	 */
	public String genererCaracterePolice() {
		String CARACTERES = "ABCDEFGHIJKLMNOP1Q2R3S4T5U6V7W8X9Y0Z";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 6) { // taille de la valeur � retourner
			int index = (int) (rnd.nextFloat() * CARACTERES.length());
			salt.append(CARACTERES.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}
	
	/** 
	 * Cette m�thode permet de g�n�rer la matricule d'un �tudiant
	 * @return student.getMatricule()
	 */
	public String genererMatriculeEtudiant(Students student) {
		int curDate = Calendar.getInstance().get(Calendar.YEAR);

		if (student.getNoms().length() < 3) {
			student.setMatricule(genererCaractereMatricule() + "-" + student.getNoms() + "-" + curDate);
		} else {
			student.setMatricule(genererCaractereMatricule() + "-" + student.getNoms().substring(0, 3) + "-" + curDate);
		}
		return student.getMatricule();
	}

	/** 
	 * Cette m�thode permet de g�n�rer la police d'assurance d'un �tudiant
	 * @return the matricule
	 */
	public String genererPoliceAssurance(Students student) {
		int curDate = Calendar.getInstance().get(Calendar.YEAR);

		if (student.getNoms().length() < 3) {
			student.setPolice(genererCaracterePolice() + "." + student.getNoms() + "." + curDate);
		} else {
			student.setPolice(genererCaracterePolice() + "." + student.getNoms().substring(0, 3) + "." + curDate);
		}
		return student.getPolice();
	}
	
	/**
	 * Cette m�thode permet d'enregistrer un �tudiant dans un fichier xml
	 * @return 
	 */
	public int createStudentsService(Students student) {
		try {
			Document document = DOMHelper.getDocument("src\\vn\\edu\\vnu\\ifi\\xml\\datas\\Students.xml");
			Element etudiants = document.getDocumentElement();
			//Create student tag
			Element etudiant = document.createElement("student");
			//Create matricule tag
			Element matricule = document.createElement("matricule");
			matricule.appendChild(document.createTextNode(student.getMatricule()));
			etudiant.appendChild(matricule);
			//Create noms tag
			Element noms = document.createElement("noms");
			noms.appendChild(document.createTextNode(student.getNoms()));
			etudiant.appendChild(noms);
			//Create prenoms tag
			Element prenoms = document.createElement("prenoms");
			prenoms.appendChild(document.createTextNode(student.getPrenoms()));
			etudiant.appendChild(prenoms);
			//Create dateNaiss tag
			Element dateNaiss = document.createElement("dateNaiss");
			dateNaiss.appendChild(document.createTextNode(student.getDateNaissance()));
			etudiant.appendChild(dateNaiss);
			//Create lieuNaiss tag
			Element lieuNaiss = document.createElement("lieuNaiss");
			lieuNaiss.appendChild(document.createTextNode(student.getLieuNaissance()));
			etudiant.appendChild(lieuNaiss);
			//Create adresse tag
			Element adresse = document.createElement("adresse");
			adresse.appendChild(document.createTextNode(student.getAdresse()));
			etudiant.appendChild(adresse);
			//Create telephone tag
			Element telephone = document.createElement("telephone");
			telephone.appendChild(document.createTextNode(student.getTelephone()));
			etudiant.appendChild(telephone);
			//Create niveau tag
			Element niveau = document.createElement("niveau");
			niveau.appendChild(document.createTextNode(student.getNiveau()));
			etudiant.appendChild(niveau);
			//Create police tag
			Element police = document.createElement("police");
			police.appendChild(document.createTextNode(student.getPolice()));
			etudiant.appendChild(police);
			etudiants.appendChild(etudiant);
			//Write to file
			DOMHelper.saveXMLContent(document, "src\\vn\\edu\\vnu\\ifi\\xml\\datas\\Students.xml");
		} catch (Exception e) {
			return 0; // retourne "0" lorsqu'il ya une ereur
		}
		return 1; // retourne "1" lorsque l'enregistrement dans le fichier xml est r�ussi
		
	}
	
	/**
	 * Cette m�thode permet de r�cup�rer la liste de tous les �tudiants du fichier xml
	 * @return listeEtudiants
	 */
	public Vector<Object> getAllStudentsService(){
		Vector<Object> listeEtudiants = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse("src\\vn\\edu\\vnu\\ifi\\xml\\datas\\Students.xml");

			NodeList listEtudiants = doc.getElementsByTagName("student");
			NodeList nl = doc.getElementsByTagName("matricule");
			NodeList n2 = doc.getElementsByTagName("noms");
			NodeList n3 = doc.getElementsByTagName("prenoms");
			NodeList n4 = doc.getElementsByTagName("dateNaiss");
			NodeList n5 = doc.getElementsByTagName("lieuNaiss");
			NodeList n6 = doc.getElementsByTagName("adresse");
			NodeList n7 = doc.getElementsByTagName("telephone");
			NodeList n8 = doc.getElementsByTagName("niveau");
			NodeList n9 = doc.getElementsByTagName("police");
			
			String data1 = null, data2 = null, data3 = null, data4 = null, data5 = null, data6 = null, data7 = null, data8 = null, data9 = null;
			listeEtudiants = new Vector<Object>();
			
			for (int i = 0; i < listEtudiants.getLength(); i++) {
				data1 = nl.item(i).getFirstChild().getTextContent();
				data2 = n2.item(i).getFirstChild().getTextContent();
				data3 = n3.item(i).getFirstChild().getTextContent();
				data4 = n4.item(i).getFirstChild().getTextContent();
				data5 = n5.item(i).getFirstChild().getTextContent();
				data6 = n6.item(i).getFirstChild().getTextContent();
				data7 = n7.item(i).getFirstChild().getTextContent();
				data8 = n8.item(i).getFirstChild().getTextContent();
				data9 = n9.item(i).getFirstChild().getTextContent();
				
				String line = data1 + " " + data2 + " " + data3 + " " + data4 + " " + data5 + " " + data6 + " " + data7 + " " + data8 + " " + data9;
				StringTokenizer st2 = new StringTokenizer(line, " ");
				while (st2.hasMoreTokens())
					listeEtudiants.addElement(st2.nextToken());
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeEtudiants;
		
	}
	
	/**
	 * Cette m�thode permet de supprimer un �tudiant dans du fichier xml
	 */
	public int removeStudentsService(String matricule) {
		try {
			Document document = DOMHelper.getDocument("src\\vn\\edu\\vnu\\ifi\\xml\\datas\\Students.xml");
			NodeList nodeList = document.getElementsByTagName("student");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element etudiant = (Element) nodeList.item(i);
				if (etudiant.getElementsByTagName("matricule").item(0).getTextContent().equals(matricule)) {
					etudiant.getParentNode().removeChild(etudiant);
				}
			}
			//Write to file
			DOMHelper.saveXMLContent(document, "src\\vn\\edu\\vnu\\ifi\\xml\\datas\\Students.xml");
		} catch (Exception e) {
			return 0; // retourne "0" lorsqu'il ya une ereur
		}
		return 1; // retourne "1" lorsque la suppression dans le fichier xml est r�ussi
		
	}
	
	/**
	 * Cette m�thode permet de modifier un �tudiant du fichier xml
	 * @return 
	 */
	public int editStudentsService(Students student) {
		try {
			Document document = DOMHelper.getDocument("src\\vn\\edu\\vnu\\ifi\\xml\\datas\\Students.xml");
			NodeList nodeList = document.getElementsByTagName("student");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element etudiant = (Element) nodeList.item(i);
				if (etudiant.getElementsByTagName("matricule").item(0).getTextContent().equals(student.getMatricule())) {
					etudiant.getElementsByTagName("noms").item(0).setTextContent(student.getNoms());
					etudiant.getElementsByTagName("prenoms").item(0).getTextContent().equals(student.getPrenoms());
					etudiant.getElementsByTagName("dateNaiss").item(0).getTextContent().equals(student.getDateNaissance());
					etudiant.getElementsByTagName("lieuNaiss").item(0).getTextContent().equals(student.getLieuNaissance());
					etudiant.getElementsByTagName("adresse").item(0).getTextContent().equals(student.getAdresse());
					etudiant.getElementsByTagName("telephone").item(0).getTextContent().equals(student.getTelephone());
					etudiant.getElementsByTagName("niveau").item(0).getTextContent().equals(student.getNiveau());
					etudiant.getElementsByTagName("police").item(0).getTextContent().equals(student.getPolice());
				}
			}
			//Write to file
			DOMHelper.saveXMLContent(document, "src\\vn\\edu\\vnu\\ifi\\xml\\datas\\Students.xml");
		} catch (Exception e) {
			return 0; // retourne "0" lorsqu'il ya une ereur
		}
		return 1; // retourne "1" lorsque la modification dans le fichier xml est r�ussi
		
	}
	
	/** 
	 * Cette m�thode permet de convertir le fichier xml des �tudiants en un fivhier html
	 * @return boolean
	 */
	public boolean printStudentHtml() {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File("src\\vn\\edu\\vnu\\ifi\\xml\\datas\\Students.xsl")));
			StreamSource streamSource = new StreamSource(new File("src\\vn\\edu\\vnu\\ifi\\xml\\datas\\Students.xml"));
			StreamResult streamResultat = new StreamResult(new File("src\\vn\\edu\\vnu\\ifi\\xml\\datas\\Students.html"));
			transformer.transform(streamSource, streamResultat);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;// si la conversion est un �chec
		}
		return true;// si la conversion est un succ�s
	}
	
	/** 
	 * Cette m�thode permet de rechercher un �tudiant du fichier xml
	 * @return listeEtudiantTrouve
	 */
	public Vector<Object> rechercherStudentService(String search){
		Vector<Object> listeEtudiantTrouve = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse("src\\vn\\edu\\vnu\\ifi\\xml\\datas\\Students.xml");

			NodeList listEtudiants = doc.getElementsByTagName("student");
			NodeList nl = doc.getElementsByTagName("matricule");
			NodeList n2 = doc.getElementsByTagName("noms");
			NodeList n3 = doc.getElementsByTagName("prenoms");
			NodeList n4 = doc.getElementsByTagName("dateNaiss");
			NodeList n5 = doc.getElementsByTagName("lieuNaiss");
			NodeList n6 = doc.getElementsByTagName("adresse");
			NodeList n7 = doc.getElementsByTagName("telephone");
			NodeList n8 = doc.getElementsByTagName("niveau");
			NodeList n9 = doc.getElementsByTagName("police");
			
			String data1 = null, data2 = null, data3 = null, data4 = null, data5 = null, data6 = null, data7 = null, data8 = null, data9 = null;
			listeEtudiantTrouve = new Vector<Object>();
			
			for (int i = 0; i < listEtudiants.getLength(); i++) {
				Element etudiant = (Element) listEtudiants.item(i);
				if (etudiant.getElementsByTagName("noms").item(0).getTextContent().equals(search)) {
					data1 = nl.item(i).getFirstChild().getTextContent();
					data2 = n2.item(i).getFirstChild().getTextContent();
					data3 = n3.item(i).getFirstChild().getTextContent();
					data4 = n4.item(i).getFirstChild().getTextContent();
					data5 = n5.item(i).getFirstChild().getTextContent();
					data6 = n6.item(i).getFirstChild().getTextContent();
					data7 = n7.item(i).getFirstChild().getTextContent();
					data8 = n8.item(i).getFirstChild().getTextContent();
					data9 = n9.item(i).getFirstChild().getTextContent();
					
					String line = data1 + " " + data2 + " " + data3 + " " + data4 + " " + data5 + " " + data6 + " " + data7 + " " + data8 + " " + data9;
					StringTokenizer st2 = new StringTokenizer(line, " ");
					while (st2.hasMoreTokens())
						listeEtudiantTrouve.addElement(st2.nextToken());
				}
				
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeEtudiantTrouve;
		
	}
	

	/**
	 * Cette m�thode permet d'afficher la liste des clients de la base de donn�es
	 * 
	 * @return une liste de clients
	 */
	public List<Clients> getAllClientsService() {
		return dao.getAllDaoClients();
	}

	/**
	 * Cette m�thode permet d'enregistrer un client dans la BD
	 * 
	 * @param client
	 * @return 1 si l'enregistrement est ok et 0 si echec lors de l'enregistrement
	 */
	public int enregistrerClientsService(Clients client) {
		return dao.enregistrerDaoClients(client);
	}

	/**
	 * Cette m�thode permet de modifier un client s�lectionn� en BD
	 * 
	 * @param client
	 */
	public int modifierClientsService(Clients client) {
		return dao.modifierDaoClients(client);
	}

	/**
	 * Cette m�thode permet de rechercher un client en BD
	 * 
	 * @param search
	 * @return une liste de client
	 */
	public List<Clients> rechercherClientsService(String search) {
		return dao.rechercherDaoClients(search);
	}

	/**
	 * Cette m�thode permet de supprimer un client s�lectionn� en BD
	 * 
	 * @param client
	 */
	public int supprimerClientsService(Clients client) {
		return dao.supprimerDaoClients(client);
	}

	/**
	 * METHODES LIEES AUX GESTIONAIRES
	 */

	/**
	 * Cette m�thode permet de r�cup�rer la liste des Gestionnaires en BD
	 */
	public List<Gestionnaires> getAllGestionnairesService() {
		return dao.getAllGestionnairesDao();
	}

	/**
	 * ICI LES METHODES LIEES AUX COMPTES
	 */

	/**
	 * M�thode qui permet de g�n�rer un ensemble de 05 chiffres pour le num�ro de
	 * compte d'un client
	 * 
	 * @return numero les chiffres g�n�r�s
	 */
	public int genererNumeroCompte() {
		int valeurGeneree = 0;
		String CARACTERES = "1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 5) { // taille de la valeur � retourner
			int index = (int) (rnd.nextFloat() * CARACTERES.length());
			salt.append(CARACTERES.charAt(index));
		}
		String saltStr = salt.toString();
		try {
			valeurGeneree = Integer.parseInt(saltStr);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, saltStr + " n'est pas un int", "Attention!",
					JOptionPane.WARNING_MESSAGE);
		}
		return valeurGeneree;

	}

	/**
	 * M�thode qui permet de g�n�rer un ensemble de 05 chiffres pour le num�ro de
	 * compte d'un client
	 * 
	 * @return numero les chiffres g�n�r�s
	 */
	public int genererIdentCompte() {
		int valeurIdent = 0;
		String CARACTERES = "1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 5) { // taille de la valeur � retourner
			int index = (int) (rnd.nextFloat() * CARACTERES.length());
			salt.append(CARACTERES.charAt(index));
		}
		String saltStr = salt.toString();

		try {
			valeurIdent = Integer.parseInt(saltStr);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, saltStr + " n'est pas un int", "Attention!",
					JOptionPane.WARNING_MESSAGE);
		}

		return valeurIdent;

	}

	/**
	 * Cette m�thode permet d'afficher la liste des type de comptes en BD
	 * 
	 * @return une liste de type de compte
	 */
	public List<TypeComptes> getAllTypeComptesService() {
		return dao.getAllDaoTypeComptes();
	}

	/**
	 * Cette m�thode permet d'afficher la liste des comptes en BD
	 * 
	 * @return une liste de compte
	 */
	public List<Comptes> getAllComptesService() {
		return dao.getAllDaoComptes();
	}

	/**
	 * Cette m�thode permet d'enregistrer un compte en BD
	 * 
	 * @param compte
	 * @return 1 si l'enregistre est un succ�s 0 si �chec
	 */
	public int enregistrerComptesService(Comptes compte) {
		return dao.enregistrerDaoComptes(compte);
	}

	/**
	 * Cette m�thode permet de modifier un compte en BD
	 * 
	 * @param compte
	 * @return 1 si modification succ�s 0 si �chec
	 */
	public int modifierComptesServices(Comptes compte) {
		return dao.modifierDaoCompte(compte);
	}

	/**
	 * Cette m�thode permet de supprimer le compte d'un client
	 * 
	 * @param compte
	 * @return 0 si �chec lors de la suppression 1 si succ�s
	 */
	public int supprimerComptesService(Comptes compte) {
		return dao.supprimerDaoCompte(compte);
	}

	/**
	 * Cette m�thode permet de rechercher un compte en BD
	 * 
	 * @param search
	 * @return une liste de compte
	 */
	public List<Comptes> rechercherComptesService(String search) {
		return dao.rechercherDaoComptes(search);
	}
	
	/**
	 * Cette m�thode permet de calculer et mettre � jour le solde de tous les comptes enn BD
	 * @see vn.edu.vnu.ifi.xml.service.IService#calculInteretComptesService(int, float)
	 */
	public int calculInteretComptesService(int numCompte, float interet) {
		return dao.calculInteretDaoComptes(numCompte, interet);
	}

	/**
	 * ICI LES METHODES LIEES AUX TRANSACTIONS
	 */

	/**
	 * Cette m�thode permet d'afficher la liste des comptes en seuil en BD
	 * 
	 * @return une liste de compte seuil
	 */
	public List<Comptes> getAllComptesSeuilService() {
		return dao.getAllDaoComptesSeuil();
	}

	/**
	 * Cette m�thode permet de r�cup�rer le solde d'un compte en BD
	 * 
	 * @return le solde du compte donn� en param�tre.
	 */
	public float getSoldeComptesService(int compte) {
		return dao.afficherSoldeDao(compte);
	}

	/**
	 * Cette m�thode permet d'afficher la liste des transactions
	 * 
	 * @return une liste de transactions
	 */
	public List<Transactions> getAllTransactionsService() {
		return dao.getAllDaoTransactions();
	}

	/**
	 * Cette m�thode permet d'enregistrer une transaction en BD
	 * 
	 * @param transaction
	 * @return 1 si l'enregistre est un succ�s 0 si �chec
	 */
	public int enregistrerTransactionsService(Transactions transaction) {
		return dao.enregistrerDaoTransactions(transaction);
	}

	/**
	 * Cette m�thode permet de faire le d�bit du solde d'un compte dans la BD
	 * 
	 * @param transaction
	 * @return 1 si la mise � jour est un succ�s 0 si �chec
	 */
	public int faireCreditTransactionsService(float montantCredit, int numCompte) {
		return dao.faireCreditDaoTransactions(montantCredit, numCompte);
	}

	/**
	 * Cette m�thode permet de faire le d�bit du solde d'un compte dans la BD
	 * 
	 * @param transaction
	 * @return 1 si la mise � jour est un succ�s 0 si �chec
	 */
	public int faireDebitTransactionsService(float montantCredit, int numCompte) {
		return dao.faireDebitDaoTransactions(montantCredit, numCompte);
	}

	/**
	 * Cette m�thode permet de rechercher un compte seuil en BD
	 * 
	 * @param search
	 * @return une liste de compte seuil
	 */
	public List<Transactions> rechercherTransactionsService(String search) {
		return dao.rechercherDaoTransactions(search);
	}

}
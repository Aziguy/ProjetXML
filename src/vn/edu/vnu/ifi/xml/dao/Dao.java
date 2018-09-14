/**
 * 
 */
package vn.edu.vnu.ifi.xml.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnu.ifi.xml.domaine.Clients;
import vn.edu.vnu.ifi.xml.domaine.Comptes;
import vn.edu.vnu.ifi.xml.domaine.Gestionnaires;
import vn.edu.vnu.ifi.xml.domaine.Transactions;
import vn.edu.vnu.ifi.xml.domaine.TypeComptes;
import vn.edu.vnu.ifi.xml.utilitaire.ConnectionMYSQL;

/**
 * Cette Class implémente toutes les signatures des méthodes de l'interface IDao
 * 
 * @version 0.1 Promo22Bank
 * @author KENGNI Hippolyte
 */
public class Dao implements IDao {
	// Déclaration des variables que nous allons manipuler

	/**
	 * Constructeur de ma classe ne prend aucun paramétre
	 */
	public Dao() {
		super();
	}

	/**
	 * Cette méthode permet de se connecter à la base de données afin d'avoir accès
	 * à l'application
	 * 
	 * @param login
	 * @param pwd
	 * @return 1 si la connexion est ok et 0 sinon
	 */
	public int connexionInterfaceDao(String login, String pwd) {

		try {

			Connection connection = ConnectionMYSQL.getInstance();

			Statement st = connection.createStatement();

			String requete = "SELECT * FROM connexion WHERE ( (login = '" + login + "') AND (password = '" + pwd
					+ "') )";

			ResultSet rs = st.executeQuery(requete);

			if (rs.next()) {

				return 1;

			} else {

				return 0;
			}

		} catch (Exception e) {
			System.out.println("--> Exception : " + e);
		}
		return 1;
	}

	/**
	 * Cette méthode permet d'afficher un message à la connection de l'utilisateur
	 * sur la page d'accueil
	 * 
	 * @return message
	 */
	public String afficheMessageDao(String recLogin) {

		// Cette varaible contiendra le résultat de la requete de sélection
		String message = null;

		try {

			Connection connection = ConnectionMYSQL.getInstance();

			Statement st = connection.createStatement();

			String requete = "SELECT * FROM connexion WHERE login = '" + recLogin + "'";

			ResultSet rs = st.executeQuery(requete);

			if (rs.next()) {

				String t1 = rs.getString("nomComplet");

				message = t1;
				// lnom.setText(t1 + " " + t2);

				// type.setText(t3);
			}

		} catch (Exception e) {
			System.out.println("--> Exception : " + e);
		}

		return message;
	}

	/**
	 * ICI TOUTES LES METHODES LIEES AUX CLIENTS
	 */

	/**
	 * Cette méthode permet d'afficher la liste de tous les client en BD
	 * 
	 * @return List<Clients> maListeClients
	 */
	public List<Clients> getAllDaoClients() {
		// création d'une collection de Clients
		List<Clients> maListeClients = new ArrayList<Clients>();

		try {

			// Etape 1 : récupération de la connexion
			Connection cn = ConnectionMYSQL.getInstance();

			// Etape 2 : préparation requête
			Statement st = cn.createStatement();

			String sql = "SELECT clients.idClients, clients.noms, clients.prenoms, clients.dateNaissance, clients.telephone, clients.adresse, gestionnaires.noms AS Gestionnaires_idGestionnaires FROM clients INNER JOIN gestionnaires ON clients.Gestionnaires_idGestionnaires = gestionnaires.idGestionnaires ORDER BY clients.noms";

			// Etape 3 : exécution requéte
			ResultSet rs = st.executeQuery(sql);

			// Etape 4 :parcours Resultset (optionnel)
			while (rs.next()) {
				Clients client = new Clients();
				client.setIdClients(rs.getString("idClients"));
				client.setNoms(rs.getString("noms"));
				client.setPrenoms(rs.getString("prenoms"));
				client.setDateNaissance(rs.getString("dateNaissance"));
				client.setTelephone(rs.getString("telephone"));
				client.setAdresse(rs.getString("adresse"));
				client.setGestionnaires_idGestionnaires(rs.getString("Gestionnaires_idGestionnaires"));
				maListeClients.add(client);
			}

			// Etape 5 : gestion des exceptions et libération 'automatique' des ressources
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return maListeClients;
	}

	/**
	 * Cette méthode permet d'enregistrer un client en BD
	 * 
	 * @return 0 si échec et 1 si succès
	 * @param clients
	 */
	public int enregistrerDaoClients(Clients client) {
		try {

			// Etape 1 : récupération de la connexion
			Connection cn = ConnectionMYSQL.getInstance();

			// Etape 2 : Prépare requête
			String sql = "INSERT INTO `clients` (`noms`,`prenoms`,`dateNaissance`,`telephone`,`adresse`,`Gestionnaires_idGestionnaires`) "
					+ "VALUES(?,?,?,?,?,?)";

			// Etape 3 : Création d'un preparedStatement
			PreparedStatement ps = cn.prepareStatement(sql);

			ps.setString(1, client.getNoms());
			ps.setString(2, client.getPrenoms());
			ps.setString(3, client.getDateNaissance());
			ps.setString(4, client.getTelephone());
			ps.setString(5, client.getAdresse());
			ps.setString(6, client.getGestionnaires_idGestionnaires());

			// Etape 4 : exécution requête
			ps.executeUpdate();

			// Etape 5 : gestion des exceptions et libération 'automatique' des ressources
		} catch (SQLException e) {
			e.printStackTrace();
			return 0; // retourne "0" lorsqu'il ya une ereur
		}
		return 1; // retourne "1" lorsque l'enregistrement en BD est réussi
	}

	/**
	 * Cette méthode permet de supprimer un client sélectionné en BD
	 * 
	 * @return 0 si échec et 1 si succès
	 * @param clients
	 */
	public int supprimerDaoClients(Clients client) {
		try {

			// Etape 1 : récupération de la connexion
			Connection cn = ConnectionMYSQL.getInstance();

			// Etape 2 : Prépare requête
			String reqDel = "DELETE from clients WHERE idClients = ?";

			// Etape 3 : Création d'un preparedStatement
			PreparedStatement ps = cn.prepareStatement(reqDel);

			ps.setString(1, client.getIdClients());

			// Etape 4 : exécution requête
			ps.executeUpdate();

			// Etape 5 : gestion des exceptions et libération 'automatique' des ressources
		} catch (SQLException e) {
			e.printStackTrace();
			return 0; // retourne "0" lorsqu'il ya une ereur
		}
		return 1; // retourne "1" lorsque la suppression en BD est réussi
	}

	/**
	 * Cette méthode permet de modifier un patient sélectionné en BD
	 * 
	 * @param clients
	 * @return 0 si échec et 1 si succès
	 */
	public int modifierDaoClients(Clients client) {

		try {

			// Etape 1 : récupération de la connexion
			Connection cn = ConnectionMYSQL.getInstance();

			// Etape 2 : Prépare requête
			String reqMod = "UPDATE `clients` SET `noms`= ?,`prenoms`= ?,`dateNaissance`= ?,`telephone`= ?,`adresse`= ?,`Gestionnaires_idGestionnaires`= ? WHERE idClients ='"
					+ client.getIdClients() + "'";

			// Etape 3 : Création d'un preparedStatement
			PreparedStatement ps = cn.prepareStatement(reqMod);

			ps.setString(1, client.getNoms());
			ps.setString(2, client.getPrenoms());
			ps.setString(3, client.getDateNaissance());
			ps.setString(4, client.getTelephone());
			ps.setString(5, client.getAdresse());
			ps.setString(6, client.getGestionnaires_idGestionnaires());

			// Etape 4 : exécution requête
			ps.executeUpdate();

			// Etape 5 : gestion des exceptions et libération 'automatique' des ressources
		} catch (SQLException e) {
			e.printStackTrace();
			return 0; // retourne "0" lorsqu'il ya une ereur
		}
		return 1; // retourne "1" lorsque la modification en BD est réussi
	}

	/**
	 * Cette méthode permet de rechercher un client en BD
	 * 
	 * @param search
	 * @return List<Clients> maListeClients
	 */
	public List<Clients> rechercherDaoClients(String search) {
		// création d'une collection de patient
		List<Clients> maListeClientsTrouver = new ArrayList<Clients>();

		try {

			// Etape 1 : récupération de la connexion
			Connection cn = ConnectionMYSQL.getInstance();

			// Etape 2 : préparation requête
			Statement st = cn.createStatement();

			String reqSearch = "SELECT clients.idClients, clients.noms, clients.prenoms, clients.dateNaissance, clients.telephone, clients.adresse, gestionnaires.noms AS Gestionnaires_idGestionnaires FROM clients INNER JOIN gestionnaires ON clients.Gestionnaires_idGestionnaires = gestionnaires.idGestionnaires WHERE clients.noms = '"
					+ search + "' ORDER BY clients.noms";

			// Etape 3 : exécution requéte
			ResultSet rs = st.executeQuery(reqSearch);

			// Etape 4 :parcours Resultset (optionnel)
			while (rs.next()) {
				Clients client = new Clients();
				client.setIdClients(rs.getString("idClients"));
				client.setNoms(rs.getString("noms"));
				client.setPrenoms(rs.getString("prenoms"));
				client.setDateNaissance(rs.getString("dateNaissance"));
				client.setTelephone(rs.getString("telephone"));
				client.setAdresse(rs.getString("adresse"));
				client.setGestionnaires_idGestionnaires(rs.getString("Gestionnaires_idGestionnaires"));
				maListeClientsTrouver.add(client);

			}

			// Etape 5 : gestion des exceptions et libération 'automatique' des ressources
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return maListeClientsTrouver; // retourne une liste lorsque la recherche en BD a réussi
	}

	/**
	 * METHODES LIEES AUX GESTIONNAIRES
	 */

	/**
	 * @return List<Gestionnaires> maListeGestionnaires
	 */
	@Override
	public List<Gestionnaires> getAllGestionnairesDao() {
		// création d'une collection de Gestionnaires
		List<Gestionnaires> maListeGestionnaires = new ArrayList<Gestionnaires>();

		try {

			// Etape 1 : récupération de la connexion
			Connection cn = ConnectionMYSQL.getInstance();

			// Etape 2 : préparation requête
			Statement st = cn.createStatement();

			String sql = "SELECT * FROM gestionnaires ";

			// Etape 3 : exécution requéte
			ResultSet rs = st.executeQuery(sql);

			// Etape 4 :parcours Resultset (optionnel)
			while (rs.next()) {
				Gestionnaires gestionnaire = new Gestionnaires();
				gestionnaire.setIdGestionnaires(rs.getString("idGestionnaires"));
				gestionnaire.setNoms(rs.getString("noms"));
				gestionnaire.setPrenoms(rs.getString("prenoms"));
				gestionnaire.setDateEmbauche(rs.getString("dateEmbauche"));
				gestionnaire.setTelephone(rs.getString("telephone"));
				gestionnaire.setAdresse(rs.getString("adresse"));
				maListeGestionnaires.add(gestionnaire);
			}

			// Etape 5 : gestion des exceptions et libération 'automatique' des ressources
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return maListeGestionnaires;
	}
	
	/**
	 * ICI TOUTES LES METHODES LIEES AUX TRANSACTIONS
	 */
	
	/**
	 * Cette méthode permet de récupérer tous les types de compte en BD
	 * 
	 * @return maListeTypeComptes
	 */
	public List<TypeComptes> getAllDaoTypeComptes() {
		// création d'une collection de Clients
		List<TypeComptes> maListeTypeComptes = new ArrayList<TypeComptes>();
		try {

			// Etape 1 : récupération de la connexion
			Connection cn = ConnectionMYSQL.getInstance();

			// Etape 2 : préparation requête
			Statement st = cn.createStatement();

			String sql = "SELECT * FROM typecomptes ";

			// Etape 3 : exécution requéte
			ResultSet rs = st.executeQuery(sql);

			// Etape 4 :parcours Resultset (optionnel)
			while (rs.next()) {
				TypeComptes typeComptes = new TypeComptes();
				typeComptes.setIdTypeComptes(rs.getString("idTypeComptes"));
				typeComptes.setLibelle(rs.getString("libelle"));
				typeComptes.setTauxInteret(rs.getInt("tauxInteret"));
				maListeTypeComptes.add(typeComptes);
			}

			// Etape 5 : gestion des exceptions et libération 'automatique' des ressources
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return maListeTypeComptes;
	}

	/**
	 * Cette méthode permet de récupérer tous les comptes de la BD dont le solde est
	 * inférieur à 500€
	 * 
	 * @return List<Comptes> maListeComptesSeuil
	 */
	public List<Comptes> getAllDaoComptesSeuil() {
		// création d'une collection de Clients
		List<Comptes> maListeComptesSeuil = new ArrayList<Comptes>();

		try {

			// Etape 1 : récupération de la connexion
			Connection cn = ConnectionMYSQL.getInstance();

			// Etape 2 : préparation requête
			Statement st = cn.createStatement();
			String espace = " ";

			String sql = "SELECT numComptes, numIdent, solde, CONCAT(clients.noms,'"+ espace +"',clients.prenoms) AS Clients_idClients, CONCAT(gestionnaires.noms,'" + espace + "',gestionnaires.prenoms) AS Gestionnaires_idGestionnaires, typeComptes.libelle AS TypeComptes_idTypeComptes FROM comptes, clients, gestionnaires, typeComptes WHERE comptes.Clients_idClients = clients.idClients AND clients.Gestionnaires_idGestionnaires = gestionnaires.idGestionnaires AND comptes.TypeComptes_idTypeComptes = typeComptes.idTypeComptes AND solde < 500";

			// Etape 3 : exécution requéte
			ResultSet rs = st.executeQuery(sql);

			// Etape 4 :parcours Resultset (optionnel)
			while (rs.next()) {
				Comptes compte = new Comptes();
				compte.setNumComptes(rs.getInt("numComptes"));
				compte.setNumIdent(rs.getInt("numIdent"));
				compte.setSolde(rs.getFloat("solde"));
				compte.setClients_idClients(rs.getString("Clients_idClients"));
				compte.setGestionnaires_idGestionnaires(rs.getString("Gestionnaires_idGestionnaires"));
				compte.setTypesComptes_idTypesComptes(rs.getString("TypeComptes_idTypeComptes"));
				maListeComptesSeuil.add(compte);
			}

			// Etape 5 : gestion des exceptions et libération 'automatique' des ressources
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return maListeComptesSeuil;
	}

	/**
	 * Cette méthode permet de récupérer toutes les transactions de la BD
	 * 
	 * @return List<Transactions> maListeTransactions
	 */
	public List<Transactions> getAllDaoTransactions() {
		// création d'une collection de transactions
		List<Transactions> maListeTransactions = new ArrayList<Transactions>();
		String espace = " ";

		try {

			// Etape 1 : récupération de la connexion
			Connection cn = ConnectionMYSQL.getInstance();

			// Etape 2 : préparation requête
			Statement st = cn.createStatement();

			String sql = "SELECT transactions.`idTransactions`, transactions.`numComptes`, transactions.`date`, transactions.`typeTransaction`, transactions.`montant`, CONCAT(gestionnaires.`noms`,'"+ espace +"',gestionnaires.`prenoms`) AS Gestionnaires_idGestionnaires FROM transactions, gestionnaires WHERE gestionnaires.`idGestionnaires` = Gestionnaires_idGestionnaires ";

			// Etape 3 : exécution requéte
			ResultSet rs = st.executeQuery(sql);

			// Etape 4 :parcours Resultset (optionnel)
			while (rs.next()) {
				Transactions transaction = new Transactions();
				transaction.setIdTransaction(rs.getString("idTransactions"));
				transaction.setCompte(rs.getInt("numComptes"));
				transaction.setDate(rs.getString("date"));
				transaction.setTypeTransaction(rs.getString("typeTransaction"));
				transaction.setMontant(rs.getFloat("montant"));
				transaction.setGestionnaires_idGestionnaires(rs.getString("Gestionnaires_idGestionnaires"));
				maListeTransactions.add(transaction );
			}

			// Etape 5 : gestion des exceptions et libération 'automatique' des ressources
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return maListeTransactions;
	}

	/**
	 * Cette méthode permet d'enregistre une opération dans la BD
	 * 
	 * @return 0 si échec et 1 si succès lors de l'enregistrement
	 * @param transaction
	 */
	public int enregistrerDaoTransactions(Transactions transaction) {
		try {
			// Etape 1 : récupération de la connexion
			Connection cn = ConnectionMYSQL.getInstance();

			// Etape 2 : Prépare requête
			String sql = "INSERT INTO `transactions` (`numComptes`,`date`,`typeTransaction`,`montant`,`Gestionnaires_idGestionnaires`) VALUES (?, ?, ?, ?, ?)";

			// Etape 3 : Création d'un preparedStatement
			PreparedStatement ps = cn.prepareStatement(sql);

			ps.setInt(1, transaction.getCompte());
			ps.setString(2, transaction.getDate());
			ps.setString(3, transaction.getTypeTransaction());
			ps.setFloat(4, transaction.getMontant());
			ps.setString(5, transaction.getGestionnaires_idGestionnaires());

			// Etape 4 : exécution requête
			ps.executeUpdate();

			// Etape 5 : gestion des exceptions et libération 'automatique' des ressources
		} catch (SQLException e) {
			e.printStackTrace();
			return 0; // retourne "0" lorsqu'il ya une ereur
		}
		return 1; // retourne "1" lorsque l'enregistrement en BD est réussi
	}
	
	/**
	 * Cette méthode permet de faire le débit du solde d'un compte dans la BD
	 * 
	 * @return 0 si échec et 1 si succès lors de la modification
	 * @param montantCredit, numCompte
	 */
	public int faireDebitDaoTransactions(float montantCredit, int numCompte) {
		try {
			// Etape 1 : récupération de la connexion
			Connection cn = ConnectionMYSQL.getInstance();

			// Etape 2 : Prépare requête
			String sql = "UPDATE `comptes` SET `solde` = ('" + montantCredit + "') WHERE numComptes = '" + numCompte + "'";
			
			// Etape 3 : Création d'un preparedStatement
			PreparedStatement ps = cn.prepareStatement(sql);

//			ps.setInt(1, transaction.getCompte());
//			ps.setString(2, transaction.getDate());
//			ps.setString(3, transaction.getTypeTransaction());
//			ps.setFloat(4, transaction.getMontant());
//			ps.setString(5, transaction.getGestionnaires_idGestionnaires());

			// Etape 4 : exécution requête
			ps.executeUpdate();

			// Etape 5 : gestion des exceptions et libération 'automatique' des ressources
		} catch (SQLException e) {
			e.printStackTrace();
			return 0; // retourne "0" lorsqu'il ya une ereur
		}
		return 1; // retourne "1" lorsque l'opération en BD est réussi
	}
	
	/**
	 * Cette méthode permet de faire le crédit du solde d'un compte dans la BD
	 * 
	 * @return 0 si échec et 1 si succès lors de la maj
	 * @param montantCredit, numCompte
	 */
	public int faireCreditDaoTransactions(float montantCredit, int numCompte) {
		try {
			// Etape 1 : récupération de la connexion
			Connection cn = ConnectionMYSQL.getInstance();
			//String reqMod = "UPDATE comptes SET solde = ?, dateOpen = ?, Clients_idClients = ?, typeComptes_idTypeComptes = ?  WHERE  numeroCpte ='" + compte.getNumeroCpte() + "'";
			// Etape 2 : Prépare requête
			String sql = "UPDATE `comptes` SET `solde` = ('" + montantCredit + "') WHERE numComptes = '" + numCompte + "'";
			
			// Etape 3 : Création d'un preparedStatement
			PreparedStatement ps = cn.prepareStatement(sql);
//			Comptes cptes = new Comptes();
//			ps.setInt(1, cptes.getNumComptes());
//			ps.setInt(2, cptes.getNumIdent());
//			ps.setFloat(3, cptes.getSolde());
//			ps.setString(4, cptes.getClients_idClients());
//			ps.setString(5, cptes.getGestionnaires_idGestionnaires());
//			ps.setString(6, cptes.getTypesComptes_idTypesComptes());
//			//ps.setString(2, transaction.getDate());
//			//ps.setString(3, transaction.getTypeTransaction());
//			//ps.setFloat(4, transaction.getMontant());
//			//ps.setString(5, transaction.getGestionnaires_idGestionnaires());

			// Etape 4 : exécution requête
			ps.executeUpdate();

			// Etape 5 : gestion des exceptions et libération 'automatique' des ressources
		} catch (SQLException e) {
			e.printStackTrace();
			return 0; // retourne "0" lorsqu'il ya une ereur
		}
		return 1; // retourne "1" lorsque l'operation en BD est réussi
	}

	/**
	 * Cette méthode permet d'afficher le solde d'un compte en BD
	 * 
	 * @param compte
	 * @return soldeCpte
	 */
	public float afficherSoldeDao(int compte) {

		// Cette varaible contiendra le résultat de la requete de sélection
		float soldeCpte = 0;

		try {

			Connection connection = ConnectionMYSQL.getInstance();

			Statement st = connection.createStatement();

			String requete = "SELECT solde FROM comptes WHERE numComptes = '" + compte + "'";

			ResultSet rs = st.executeQuery(requete);

			if (rs.next()) {

				float t1 = rs.getFloat("solde");

				soldeCpte = t1;

			}

		} catch (Exception e) {
			System.out.println("--> Exception : " + e);
		}

		return soldeCpte;
	}
	
	
	/**
	 * ICI TOUTES LES METHODES LIEES AUX COMPTES
	 */

	/**
	 * Cette méthode permet de récupérer tous les comptes de la BD
	 * 
	 * @return List<Comptes> maListeCompte
	 */
	public List<Comptes> getAllDaoComptes() {
		// création d'une collection de Clients
		List<Comptes> maListeComptes = new ArrayList<Comptes>();

		try {

			// Etape 1 : récupération de la connexion
			Connection cn = ConnectionMYSQL.getInstance();

			// Etape 2 : préparation requête
			Statement st = cn.createStatement();
			String espace = " ";

			String sql = "SELECT numComptes, numIdent, solde, CONCAT(clients.noms,'"+ espace +"',clients.prenoms) AS Clients_idClients, CONCAT(gestionnaires.noms,'" + espace + "',gestionnaires.prenoms) AS Gestionnaires_idGestionnaires, typeComptes.libelle AS TypeComptes_idTypeComptes FROM comptes, clients, gestionnaires, typeComptes WHERE comptes.Clients_idClients = clients.idClients AND clients.Gestionnaires_idGestionnaires = gestionnaires.idGestionnaires AND comptes.TypeComptes_idTypeComptes = typeComptes.idTypeComptes";
			
			// Etape 3 : exécution requéte
			ResultSet rs = st.executeQuery(sql);

			// Etape 4 :parcours Resultset (optionnel)
			while (rs.next()) {
				Comptes compte = new Comptes();
				compte.setNumComptes(rs.getInt("numComptes"));
				compte.setNumIdent(rs.getInt("numIdent"));
				compte.setSolde(rs.getFloat("solde"));
				compte.setClients_idClients(rs.getString("Clients_idClients"));
				compte.setClients_idClients(rs.getString("Clients_idClients"));
				compte.setGestionnaires_idGestionnaires(rs.getString("Gestionnaires_idGestionnaires"));
				compte.setTypesComptes_idTypesComptes(rs.getString("TypeComptes_idTypeComptes"));
				maListeComptes.add(compte);
			}
	
			// Etape 5 : gestion des exceptions et libération 'automatique' des ressources
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return maListeComptes;
	}

	/**
	 * Cette méthode permet d'enregistre un compte dans la BD
	 * 
	 * @return 0 si échec et 1 si succès lors de l'enregistrement
	 * @param compte
	 */
	public int enregistrerDaoComptes(Comptes compte) {
		try {

			// Etape 1 : récupération de la connexion
			Connection cn = ConnectionMYSQL.getInstance();

			// Etape 2 : Prépare requête
			String sql = "INSERT INTO `comptes` (`numComptes`,`numIdent`,`solde`,`Clients_idClients`,`Gestionnaires_idGestionnaires`,`TypeComptes_idTypeComptes`) VALUES (?,?,?,?,?,?)";

			// Etape 3 : Création d'un preparedStatement
			PreparedStatement ps = cn.prepareStatement(sql);

			ps.setInt(1, compte.getNumComptes());
			ps.setInt(2, compte.getNumIdent());
			ps.setFloat(3, compte.getSolde());
			ps.setString(4, compte.getClients_idClients());
			ps.setString(5, compte.getGestionnaires_idGestionnaires());
			ps.setString(6, compte.getTypesComptes_idTypesComptes());

			// Etape 4 : exécution requête
			ps.executeUpdate();

			// Etape 5 : gestion des exceptions et libération 'automatique' des ressources
		} catch (SQLException e) {
			e.printStackTrace();
			return 0; // retourne "0" lorsqu'il ya une ereur
		}
		return 1; // retourne "1" lorsque l'enregistrement en BD est réussi
	}

	/**
	 * Cette méthode permet de supprimer un compte sélectionné en BD
	 * 
	 * @return 1 si succès et 0 si echec de suppression
	 * @param compte
	 */
	public int supprimerDaoCompte(Comptes compte) {
		try {

			// Etape 1 : récupération de la connexion
			Connection cn = ConnectionMYSQL.getInstance();

			// Etape 2 : Prépare requête
			String reqDel = "DELETE from comptes WHERE numComptes = ?";

			// Etape 3 : Création d'un preparedStatement
			PreparedStatement ps = cn.prepareStatement(reqDel);

			ps.setInt(1, compte.getNumComptes());

			// Etape 4 : exécution requête
			ps.executeUpdate();

			// Etape 5 : gestion des exceptions et libération 'automatique' des ressources
		} catch (SQLException e) {
			e.printStackTrace();
			return 0; // retourne "0" lorsqu'il ya une ereur
		}
		return 1; // retourne "1" lorsque la suppression en BD est réussi
	}

	/**
	 * Cette méthode permet modfier un compte sélectioné en BD
	 * 
	 * @return 0 si échec et 1 si succès de modification
	 * @param compte
	 */
	public int modifierDaoCompte(Comptes compte) {
		try {

			// Etape 1 : récupération de la connexion
			Connection cn = ConnectionMYSQL.getInstance();

			// Etape 2 : Prépare requête
			String reqMod = "UPDATE comptes SET numComptes = ?, numIdent = ?, solde = ?, Clients_idClients = ?, Gestionnaires_idGestionnaires = ?, TypeComptes_idTypeComptes = ?  WHERE  numComptes = '" + compte.getNumComptes() + "'";

			// Etape 3 : Création d'un preparedStatement
			PreparedStatement ps = cn.prepareStatement(reqMod);

			ps.setInt(1, compte.getNumComptes());
			ps.setInt(2, compte.getNumIdent());
			ps.setFloat(3, compte.getSolde());
			ps.setString(4, compte.getClients_idClients());
			ps.setString(5, compte.getGestionnaires_idGestionnaires());
			ps.setString(5, compte.getTypesComptes_idTypesComptes());

			// Etape 4 : exécution requête
			ps.executeUpdate();

			// Etape 5 : gestion des exceptions et libération 'automatique' des ressources
		} catch (SQLException e) {
			e.printStackTrace();
			return 0; // retourne "0" lorsqu'il ya une ereur
		}
		return 1; // retourne "1" lorsque la modification en BD est réussi
	}

	/**
	 * Cette méthode permet de recherche un compte seuil en BD
	 * 
	 * @return List<Comptes> maListeComptesSeuilTrouver
	 * @param search
	 */
	public List<Transactions> rechercherDaoTransactions(String search) {
		// création d'une collection de patient
		List<Transactions> maListeTransactionsTrouver = new ArrayList<Transactions>();

		try {

			// Etape 1 : récupération de la connexion
			Connection cn = ConnectionMYSQL.getInstance();

			// Etape 2 : préparation requête
			Statement st = cn.createStatement();

			String espace = "  ";

			String reqSearch = "SELECT transactions.`idTransactions`, transactions.`numComptes`, transactions.`date`, transactions.`typeTransaction`, transactions.`montant`, CONCAT(gestionnaires.`noms`,'" + espace + "',gestionnaires.`prenoms`) AS Gestionnaires_idGestionnaires FROM transactions, gestionnaires WHERE gestionnaires.`idGestionnaires` = Gestionnaires_idGestionnaires AND `numComptes` = '" + search + "' ";

			// Etape 3 : exécution requéte
			ResultSet rs = st.executeQuery(reqSearch);

			// Etape 4 :parcours Resultset (optionnel)
			while (rs.next()) {
				
				Transactions transaction = new Transactions();
				transaction.setIdTransaction(rs.getString("idTransactions"));
				transaction.setCompte(Integer.parseInt(rs.getString("numComptes")));
				transaction.setDate(rs.getString("date"));
				transaction.setTypeTransaction(rs.getString("typeTransaction"));
				transaction.setMontant(rs.getFloat("montant"));
				transaction.setGestionnaires_idGestionnaires(rs.getString("Gestionnaires_idGestionnaires"));
				maListeTransactionsTrouver.add(transaction);
			}

			// Etape 5 : gestion des exceptions et libération 'automatique' des ressources
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return maListeTransactionsTrouver; // retourne une liste lorsque la recherche en BD a réussi
	}

	/**
	 * Cette méthode permet de recherche un compte en BD
	 * 
	 * @return List<Comptes> maListeCompte
	 * @param search
	 */
	public List<Comptes> rechercherDaoComptes(String search) {
		// création d'une collection de patient
		List<Comptes> maListeComptesTrouver = new ArrayList<Comptes>();

		try {

			// Etape 1 : récupération de la connexion
			Connection cn = ConnectionMYSQL.getInstance();

			// Etape 2 : préparation requête
			Statement st = cn.createStatement();

			String espace = "  ";

			String reqSearch = "SELECT numComptes, numIdent, solde, CONCAT(clients.noms,'"+ espace +"',clients.prenoms) AS Clients_idClients, CONCAT(gestionnaires.noms,'" + espace + "',gestionnaires.prenoms) AS Gestionnaires_idGestionnaires, typeComptes.libelle AS TypeComptes_idTypeComptes FROM comptes, clients, gestionnaires, typeComptes WHERE comptes.Clients_idClients = clients.idClients AND clients.Gestionnaires_idGestionnaires = gestionnaires.idGestionnaires AND comptes.TypeComptes_idTypeComptes = typeComptes.idTypeComptes AND numComptes = '" + search + "'";

			// Etape 3 : exécution requéte
			ResultSet rs = st.executeQuery(reqSearch);

			// Etape 4 :parcours Resultset (optionnel)
			while (rs.next()) {
				
				Comptes compte = new Comptes();
				compte.setNumComptes(rs.getInt("numComptes"));
				compte.setNumIdent(rs.getInt("numIdent"));
				compte.setSolde(rs.getFloat("solde"));
				compte.setClients_idClients(rs.getString("Clients_idClients"));
				compte.setGestionnaires_idGestionnaires(rs.getString("Gestionnaires_idGestionnaires"));
				compte.setTypesComptes_idTypesComptes(rs.getString("TypeComptes_idTypeComptes"));
				maListeComptesTrouver.add(compte);
			}

			// Etape 5 : gestion des exceptions et libération 'automatique' des ressources
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return maListeComptesTrouver; // retourne une liste lorsque la recherche en BD a réussi
	}
	
	/**
	 * Cette méthode permet de mettre à jour le solde d'un compte dans la BD avec les intérêts
	 * 
	 * @return 0 si échec et 1 si succès lors de la maj
	 * @param numCompte, interet
	 */
	public int calculInteretDaoComptes(int numCompte, float interet) {
		try {
			// Etape 1 : récupération de la connexion
			Connection cn = ConnectionMYSQL.getInstance();

			// Etape 2 : Prépare requête
			String sql = "UPDATE `comptes` SET `solde` = ('" + interet + "') WHERE numComptes = '" + numCompte + "'";
			
			// Etape 3 : Création d'un preparedStatement
			PreparedStatement ps = cn.prepareStatement(sql);

//			ps.setInt(1, transaction.getCompte());
//			ps.setString(2, transaction.getDate());
//			ps.setString(3, transaction.getTypeTransaction());
//			ps.setFloat(4, transaction.getMontant());
//			ps.setString(5, transaction.getGestionnaires_idGestionnaires());

			// Etape 4 : exécution requête
			ps.executeUpdate();

			// Etape 5 : gestion des exceptions et libération 'automatique' des ressources
		} catch (SQLException e) {
			e.printStackTrace();
			return 0; // retourne "0" lorsqu'il ya une ereur
		}
		return 1; // retourne "1" lorsque la maj en BD est réussi
	}

}

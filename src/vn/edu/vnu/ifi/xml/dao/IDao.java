/**
 * 
 */
package vn.edu.vnu.ifi.xml.dao;

import java.util.List;

import vn.edu.vnu.ifi.xml.domaine.Clients;
import vn.edu.vnu.ifi.xml.domaine.Comptes;
import vn.edu.vnu.ifi.xml.domaine.Gestionnaires;
import vn.edu.vnu.ifi.xml.domaine.Transactions;
import vn.edu.vnu.ifi.xml.domaine.TypeComptes;

/**
 * Cette Class contient toutes les interfaces implémentés par la Class Dao
 * 
 * @author KENGNI Hippolyte
 * @version 0.1 Promo22Bank
 */
/**
 * @author KENGNI Hippolyte
 *
 */
public interface IDao {

	public int connexionInterfaceDao(String login, String pwd);

	public String afficheMessageDao(String recLogin);

	/**
	 * ICI TOUS LES INTERFACES LIES AUX CLIENTS
	 */

	public List<Clients> getAllDaoClients();

	public int enregistrerDaoClients(Clients clients);

	public int supprimerDaoClients(Clients clients);

	public int modifierDaoClients(Clients clients);

	public List<Clients> rechercherDaoClients(String search);

	/**
	 * ICI TOUS LES INTERFACES LIEES AU GESTIONNAIRES
	 */

	public List<Gestionnaires> getAllGestionnairesDao();

	/**
	 * ICI LES INTERFACES LIEES AUX TRANSACTIONS
	 */

	public List<Comptes> getAllDaoComptesSeuil();

	public List<Transactions> rechercherDaoTransactions(String search);

	public float afficherSoldeDao(int compte);

	public List<Transactions> getAllDaoTransactions();

	public int enregistrerDaoTransactions(Transactions transaction);

	public int faireDebitDaoTransactions(float montantCredit, int numCompte);

	public int faireCreditDaoTransactions(float montantCredit, int numCompte);

	/**
	 * ICI LES INTERFACES LIEES AUX COMPTES
	 */

	public List<TypeComptes> getAllDaoTypeComptes();

	public List<Comptes> getAllDaoComptes();

	public int enregistrerDaoComptes(Comptes compte);

	public int supprimerDaoCompte(Comptes compte);

	public int modifierDaoCompte(Comptes compte);

	public List<Comptes> rechercherDaoComptes(String search);
	
	public int calculInteretDaoComptes(int numCompte, float interet);

}

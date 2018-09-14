/**
 * 
 */
package vn.edu.vnu.ifi.xml.domaine;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import vn.edu.vnu.ifi.xml.dao.IDao;
import vn.edu.vnu.ifi.xml.service.IService;

/**
 * @author KENGNI Hippolyte
 *
 */
public class ClientsModel extends AbstractTableModel {

	// Déclaration des propriétés et variables de class
	private final String[] entetes = { "identifiant", "Noms", "Prénoms", "Date de Naissance", "Téléphone", "Adresse", "Gestionnaire" };
	private List<Clients> maListeClients;
	private IService service;
	private IDao dao;

	/**
	 * Constructeur de la class AgencesModel
	 * 
	 * @param maListeClients
	 */
	public ClientsModel(List<Clients> maListeClients) {
		super();
		this.maListeClients = maListeClients;
	}

	/**
	 * Cette méthode permet d'obtenir une liste d'agences
	 * 
	 * @return the maListeClients
	 */
	public List<Clients> getMaListeClients() {
		return maListeClients;
	}

	/**
	 * Cette méthode permet de modifier une liste d'agences
	 * 
	 * @param maListeClients
	 *            the maListeAgences to set
	 */
	public void setMaListeClients(List<Clients> maListeClients) {
		this.maListeClients = maListeClients;
	}

	/**
	 * @return taille de l'entete
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return entetes.length;
	}

	/**
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 * @return entetes[columnIndex]
	 * @param columnIndex
	 */
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	/**
	 * @see javax.swing.table.TableModel#getRowCount()
	 * @return la taille de la liste
	 */
	public int getRowCount() {
		return maListeClients.size();
	}

	/**
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 * @param rowIndex
	 *            et columnIndex
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {

		// Identifiant du client
		case 0:
			return maListeClients.get(rowIndex).getIdClients();

		case 1:
			// Noms	 du client
			return maListeClients.get(rowIndex).getNoms();

		case 2:
			// prénoms du client
			return maListeClients.get(rowIndex).getPrenoms();

		case 3:
			// date de naissance du client
			return maListeClients.get(rowIndex).getDateNaissance();

		case 4:
			// telephone du client
			return maListeClients.get(rowIndex).getTelephone();

		case 5:
			// adresse du client
			return maListeClients.get(rowIndex).getAdresse();

		case 6:
			// gestionnaire du client
			return maListeClients.get(rowIndex).getGestionnaires_idGestionnaires();

		default:
			throw new IllegalArgumentException();

		}
	}

}

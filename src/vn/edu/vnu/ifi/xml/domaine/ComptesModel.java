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
public class ComptesModel extends AbstractTableModel {

	// D�claration des propri�t�s et variables de class

	private final String[] entetes = { "N� compte", "N� Identifiant", "Solde", "Clients", "Gestionnaire", "Type de compte" };
	private List<Comptes> maListeComptes;
	private IService service;
	private IDao dao;

	/**
	 * Constructeur de la class ComptesModel
	 * 
	 * @param maListeComptes
	 */
	public ComptesModel(List<Comptes> maListeComptes) {
		super();
		this.maListeComptes = maListeComptes;
	}

	/**
	 * Cette m�thode permet d'obtenir une liste de comptes
	 * 
	 * @return the maListeComptes
	 */
	public List<Comptes> getMaListeClients() {
		return maListeComptes;
	}

	/**
	 * Cette m�thode permet de modifier une liste de comptes
	 * 
	 * @param maListeComptes
	 *            the maListeComptes to set
	 */
	public void setMaListeClients(List<Comptes> maListeComptes) {
		this.maListeComptes = maListeComptes;
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
		return maListeComptes.size();
	}

	/**
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 * @param rowIndex
	 *            et columnIndex
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {

		// Num�ro du compte
		case 0:
			return maListeComptes.get(rowIndex).getNumComptes();

		case 1:
			// Num�ro d'identifiant du compte
			return maListeComptes.get(rowIndex).getNumIdent();

		case 2:
			// Solde du compte
			return maListeComptes.get(rowIndex).getSolde();

		case 3:
			// Noms du client
			return maListeComptes.get(rowIndex).getClients_idClients();

		case 4:
			// Gestionnaire du compte
			return maListeComptes.get(rowIndex).getGestionnaires_idGestionnaires();
			
		case 5:
			// Type de compte
			return maListeComptes.get(rowIndex).getTypesComptes_idTypesComptes();

		default:
			throw new IllegalArgumentException();

		}
	}

}
